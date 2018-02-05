/**
 * 08722 Data Structures for Application Programmers.
 *
 * Lab 6. Comparing BST with Ordered Array and Linked List
 *
 * Simple Binary Search Tree interface
 * @author Junrong Huang
 */

public class BST implements BSTInterface {
	/**
	 * Node class to be built in the BST
	 * In the lab6, we consider key = value for the case
	 * [63, 27, 80, 51, 70, 92, 13, 33, 58, 26, 60, 57, 82]
	 */
	private static class Node {
		private int key;
		private double value;
		private Node left, right;

		/**
		 * Constructor
		 */
		Node(int k, double v) {
			this.key = k;
			this.value = v;
			this.left = null;
			this.right = null;
		}
	}

	private Node root;
	private Node current;
	private Node track;
	private boolean isLeft;

	/**
	 * Constructor without given input
	 * i.e. build a root node
	 */
	public BST() {
		root = null;
	}

	/**
	* Finds key in the tree.
	* @param key to find
	* @return boolean value (true when found)
	*/
	@Override
	public boolean find(int k) {
		if (root == null) {
			return false;
		}
		current = root;

		while (current.key != k) {
			track = current;
			if (current.key < k) {
				isLeft = true;
				current = current.left;
			} else {
				isLeft = false;
				current = current.right;
			}
			if (current == null) {
				return false;
			}
		}
		return true;
	}


	/**
	 * Inserts a new key into the tree.
	 * @param key key to add
	 */
	@Override
	public void insert(int k, double v) {
		Node newNode = new Node(k, v);
		if (root == null) {
			root = newNode;
			return;
		}

		track = root;
		current = root;

		while (true) {
			if (current.key == k) {
				return;
			}
			track = current;
			if (current.key < k) {
				current = current.left;
				if (current == null) {
					track.left = newNode;
					return;
				}
			} else {
				current = current.right;
				if (current == null) {
					track.right = newNode;
					return;
				}
			}
		}
	}


	/**
	 * Deletes a key (node) from the tree.
	 * @param key key to delete
	 */
	@Override
	public void delete(int k) {
		track = root;
		current = root;


		// Case 1: not found
		if (!find(k)) {
			return;
		}

		// Case 2: the node is a leaf
		if (current.left == null && current.right == null) {
			if (current == root) {
				root = null;
			} else if (isLeft) {
				track.left = null;
			} else {
				track.right = null;
			}
		}
		// Case 3: the node has one child
		else if (current.left == null) {
			if (current == root) {
				root = current.right;
			} else if (isLeft) {
				track.left = current.right;
			} else {
				track.right = current.right;
			}
		} else if (current.right == null) {
			if (current == root) {
				root = current.left;
			} else if (isLeft) {
				track.left = current.left;
			} else {
				track.right = current.left;
			}
		}
		// Case 4: the node has 2 children
		else {
			Node successor = getSuccessor(current);
			if (current == root) {
				root = successor;
			} else if (isLeft) {
				track.left = successor;
			} else {
				track.right = successor;
			}
			successor.left = current.left;
		}
	}

	/**
	 * To find the successor of the node to be deleted
	 * Successor means the smallest node(key) in the right sub-tre
	 * of the node to be deleted, i.e. it does not have left child
	 */
	private Node getSuccessor(Node toDelete) {

		Node successor = toDelete;
		Node suc_track = toDelete;
		Node suc_current = toDelete.right;

		while (current != null) {
			suc_track = successor;
			successor = suc_current;
			suc_current = suc_current.left;
		}

		if (successor != toDelete.right) {
			suc_current.left = successor.right;
			successor.right = toDelete.right;
		}
		return successor;
	}

	/**
	 * Traverses the tree in an ascending order based on keys.
	 */
	@Override
	public void traverse() {
		inOrderHelper(root);

	}

	/**
	 * in order traversing recursion helper
	 */
	private void inOrderHelper(Node toVisit) {
		if (toVisit != null) {
			inOrderHelper(toVisit.left);
			System.out.print(toVisit.key + " " + toVisit.value + "|");
			inOrderHelper(toVisit.right);
		}
	}


	public void main(String[] args) {

	}
}

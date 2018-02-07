/**
 * 08722 Data Structures for Application Programmers.
 *
 * Homework 6. BST class
 * @author Junrong Huang
 */

import java.util.Comparator;
import java.util.Iterator;
import java.util.*;

/**
* @param T: any type of data
*/
public class BST<T extends Comparable<T>> implements Iterable<T>, BSTInterface<T> {

    private Node<T> root;
    private Comparator<T> comparator;

    /**
     * Constructor with no parameter
     */
    public BST() {
        this(null);
        // this.root = null;
    }

    /**
     * Constructor with a comparator
     *
     * @param comparator: the comparator to compare T
     */
    public BST(Comparator<T> comp) {
        this.comparator = comp;
        this.root = null;
    }

    /**
     * Get the comparator of the BST class
     * @return comparator of this class
     */
    public Comparator<T> comparator() {
        return this.comparator;
    }

    /**
     * Get the root data of the BST class
     * @return T: root.data
     */
    public T getRoot() {
        if (this.root == null) {
            return null;
        }
        return root.data;
    }

    /**
     * Get the height of the BST
     * @return int: the height of the data
     */
    public int getHeight() {
        return getHeight_recursive(this.root);
    }

    /**
     * getHeight_recursive
     * @param node: current node
     * @return int: the height of the data
     */
    private int getHeight_recursive(Node<T> node) {
        // Base case
        if (node == null || (node.left == null && node.right == null)) {
            return 0;
        }
        // Recursive case
        int leftH = getHeight_recursive(node.left);
        int rightH = getHeight_recursive(node.right);
        return 1 + Math.max(leftH, rightH);
    }

    /**
     * Get number of nodes of the BST
     * @return number of nodes of the BST
     */
    public int getNumberOfNodes() {
        return getNumberOfNodes_recursive(this.root);
    }

    /**
     * getNumberOfNodes_recursive
     * @param node: current node
     * @return int: the number of the nodes in the BST
     */
    private int getNumberOfNodes_recursive(Node<T> node) {
        // Base case
        if (node == null) {
            return 0;
        }
        // Recursive case
        int leftN = getNumberOfNodes_recursive(node.left);
        int rightN = getNumberOfNodes_recursive(node.right);
        return 1 + leftN + rightN;
    }

    /**
     * Search whether the node exists in the BST
     * @param toSearch: data object to be searched
     * @return T: if exist, return toSearch, if not, return null
     */
    @Override
    public T search(T toSearch) {
        return search_recursive(this.root, toSearch);
    }

    /**
     * search_recursive
     * @param node: current node
     * @param toSearch: data object to be searched
     * @return T: if exist, return toSearch, if not, return null
     */
    private T search_recursive(Node<T> node, T toSearch) {
        // Base case
        if (node == null) {
            return null;
        }
        // Recursive case
        int compareresult = node.data.compareTo(toSearch);
        if (compareresult == 0) {
            return toSearch;
        } else if (compareresult > 0) {
            return search_recursive(node.left, toSearch);
        } else {
            return search_recursive(node.right, toSearch);
        }
    }

    /**
     * Insert a node to the BST
     * @param toInsert: data object to be inserted
     */
    @Override
    public void insert(T toInsert) {
        // empity BST
        if (this.root == null) {
            this.root = new Node<T>(toInsert);
            return;
        }
        insert_recursive(null, root, toInsert);
    }

    /**
     * insert_recursive
     * @param parent: parent Node
     * @param node: current Node
     * @param toInsert: data object to be inserted
     */
    private void insert_recursive(Node<T> parent, Node<T> node, T toInsert) {
        // Base case
        if (node == null) {
            int parent_compare = parent.data.compareTo(toInsert);
            if (parent_compare > 0) {
                parent.left = new Node<T>(toInsert);
            } else if (parent_compare < 0) {
                parent.right = new Node<T>(toInsert);
            } else {
                return;
            }
        }
        // Recursive case
        int compareresult = node.data.compareTo(toInsert);
        if (compareresult > 0) {
            insert_recursive(node, node.left, toInsert);
        } else if (compareresult < 0) {
            insert_recursive(node, node.right, toInsert);
        } else {
            return;
        }

    }

    /**
     * Create an iterator for the BST
     * @return an in-order iterator
     */
    @Override
    public Iterator<T> iterator() {
        // TODO implement this
        return new inorderIterator();
    }

    /**
     * In-order iterator class
     */
    private class inorderIterator implements Iterator<T> {

        /**
         * Stack in use to store the data
         */
        private Queue<Node<T>> queue = new ArrayDeque<Node<T>>();
        private Node<T> nextNode;

        /**
         * Constructor
         */
        public inorderIterator() {
            if (root == null) {
                nextNode = root;
            }
            Stack<Node<T>> tempstack = new Stack<Node<T>>();
            Node<T> currNode = root;
            while (currNode != null || !tempstack.isEmpty()) {
                while (currNode != null) {
                    tempstack.push(currNode);
                    currNode = currNode.left;
                }
                currNode = tempstack.pop();
                this.queue.offer(currNode);
                currNode = currNode.right;
            }
            this.nextNode = this.queue.poll();
        }

        /**
         * Whether the iterator has the next node
         * @return true/false
         */
        @Override
        public boolean hasNext() {
            return this.nextNode != null;
        }

        /**
         * Read the next node of the iterator
         * @return next node.data
         */
        @Override
        public T next() {
            if (!hasNext()) {
                return null;
            }
            T result = this.nextNode.data;
            this.nextNode = this.queue.poll();
            return result;
        }

        @Override
        public void remove() {
            return;
        }
    }


    /**
     * Node class for the BST
     * @param T: datatypes
     */
    private static class Node<T> {
        private T data;
        private Node<T> left;
        private Node<T> right;

        /**
         * Constructor without left, right children
         * @param d the data of the new node
         */
        public Node(T d) {
            this.data = d;
            this.left = null;
            this.right = null;
        }

        /**
         * Constructor with left, right children
         * @param d the data of the new node
         * @param l left node
         * @param r right node
         */
        public Node(T d, Node<T> l, Node<T> r) {
            this.data = d;
            this.left = l;
            this.right = r;
        }

        /**
         * Convert the current node to String
         * @return string representation of current node
         */
        @Override
        public String toString() {
            return data.toString();
        }

    }

}

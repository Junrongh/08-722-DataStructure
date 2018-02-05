/**
 * 08722 Data Structures for Application Programmers.
 *
 * Homework 6. BST class
 * @author Junrong Huang
 */

import java.util.Comparator;
import java.util.Iterator;

/**
* @param T: any type of data
*/
public class BST<T extends Comparable<T>> implements Iterable<T>, BSTInterface<T> {
    
    // Node: private static nested class
    private static class Node<T> {
        private T data;
        private Node<T> left;
        private Node<T> right;

        /**
         * Constructor without left, right children
         * @param d the data of the new node.
         */
        public Node(T d) {
            this.data = d;
            this.left = null;
            this.right = null;
        }

        /**
         * Constructor with left, right children
         * @param d the data of the new node.
         * @param l left node.
         * @param r right node.
         */
        public Node(T d, Node<T> l, Node<T> r) {
            this.data = d;
            this.left = l;
            this.right = r;
        }

        /**
         * Convert the current node to String.
         * @return string representation of current node.
         */
        @Override
        public String toString() {
            return data.toString();
        }

    }


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
        comparator = comp;
        root = null;
    }

    public Comparator<T> comparator() {
        return comparator;
    }

    public T getRoot() {
        // TODO implement this
        if (root == null) {
            return null;
        }
        return root.data;
    }

    public int getHeight() {
        // TODO implement this recursively
    }

    public int getNumberOfNodes() {
        // TODO implement this recursively
    }

    @Override
    public T search(T toSearch) {
        // TODO implement this recursively
    }

    @Override
    public void insert(T toInsert) {
        // TODO implement this recursively
    }

    @Override
    public Iterator<T> iterator() {
        // TODO implement this
    }

    private static class Node<T> {
        private T data;
        private Node<T> left;
        private Node<T> right;

        Node(T d) {
            this(d, null, null);
        }

        Node(T d, Node<T> l, Node<T> r) {
            data = d;
            left = l;
            right = r;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

}

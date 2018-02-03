/**
 * 08-722 Data Structures for Application Programmers.
 * Homework 3 SortedLinkedList Implementation with Recursion
 *
 * @author Junrong Huang
 */

public class SortedLinkedList implements MyListInterface {
    /**
    * Node class
    */
    private static class Node {
        /**
         * Instance Variable, next node of the current node.
         * Instance Variable, value of the node.
         */
        private Node next;
        private String value;

        /**
         * Constructor.
         * @param val value for the node
         * @param nextNode next node
         */
        Node(String value, Node nextnode) {
            this.value = value;
            this.next = nextnode;
        }

        /**
         * Overrides the toString method.
         * return the String description of the node
         */
        @Override
        public String toString() {
            return this.value;
        }
    }
    private Node head;

    /**
     * Constructor without/with given input String array
     * @param strs input String array
     * @param i index of array
     * Use function SortedLinkedList_recur to implement recursion
     */
    public SortedLinkedList() {
        this.head = null;
    }

    public SortedLinkedList(String[] strs) {
        if (strs == null || strs.length == 0) {
            this.head = null;
            return;
        }
        SortedLinkedList_recur(strs, 0);
    }

    private void SortedLinkedList_recur(String[] strs, int i) {
        if (i == strs.length) {
            return;
        }
        add(strs[i]);
        SortedLinkedList_recur(strs, i + 1);
    }

    /**
    * Inserts a new String.
    * No duplicates allowed and maintain the order in ascending order.
    * @param value String to be added.
    * Use function add_recur to implement recursion
    */
    @Override
    public void add(String value) {
        if (value == null) {
            return;
        }
        if (contains(value)) {
            return;
        }
        if (!value.matches("[a-zA-Z]+")) {
            return;
        }
        if (head == null) {
            this.head = new Node(value, null);
            return;
        }
        add_recur(this.head, value);
    }
    private void add_recur(Node node, String value) {
        if (node.next == null) {
            if (node == this.head && node.value.compareTo(value) > 0) {
                this.head = new Node(value, node);
            } else {
                node.next = new Node(value, null);
            }
            return;
        }
        int res = node.next.value.compareTo(value);
        if (res > 0) {
            if (node == this.head && node.value.compareTo(value) > 0) {
                this.head = new Node(value, node);
            } else {
                node.next = new Node(value, node.next);
            }
        } else {
            add_recur(node.next, value);
        }
    }


    /**
     * Checks the size (number of data items) of the list.
     * @return the size of the list
     * Use function size_recur to implement recursion
     */
    @Override
    public int size() {
        return size_recur(this.head, 0);
    }
    private int size_recur(Node node, int num) {
        if (node == null) {
            return num;
        }
        return size_recur(node.next, num + 1);
    }



    /**
     * Displays the values of the list.
     * Use function display_recur to implement recursion
     */
    @Override
    public void display() {
        String print = display_recur(this.head, "[");
        System.out.println(print + "]");
    }
    private String display_recur(Node node, String print) {
        if (node == null) {
            return print;
        }
        if (node.next == null) {
            print += node.value;
        } else {
            print += node.value + ", ";
        }
        return display_recur(node.next, print);
    }

    /**
     * Returns true if the key value is in the list.
     * @param key String key to search
     * @return true if found, false if not found
     * Use function contains_recur to implement recursion
     */
    @Override
    public boolean contains(String key) {
        return contains_recur(this.head, key);
    }

    private boolean contains_recur(Node node, String key) {
        if (node == null) {
            return false;
        }
        if (node.value.equals(key)) {
            return true;
        }
        return contains_recur(node.next, key);
    }

    /**
     * Returns true is the list is empty.
     * @return true if it is empty, false if it is not empty
     */
    @Override
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Removes and returns the first String object of the list.
     * @return String object that is removed. If the list is empty, returns null
     */
    @Override
    public String removeFirst() {
        if (head == null) {
            return null;
        }
        String rem = head.value;
        head = head.next;
        return rem;
    }

    /**
     * Removes and returns String object at the specified index.
     * @param index index to remove String object
     * @return String object that is removed
     * @throws RuntimeException if invalid index value is passed
     * Use function removeAt_recur to implement recursion
     */
    @Override
    public String removeAt(int index) {
        if (index < 0 || index >= size() || head == null) {
            return null;
        }
        if (index == 0) {
            return removeFirst();
        }
        return removeAt_recur(this.head, index - 1);

    }
    private String removeAt_recur(Node node, int index) {
        if (index == 0){
            String rem = node.next.value;
            node.next = node.next.next;
            return rem;
        }
        return removeAt_recur(node.next, index - 1);
    }
}



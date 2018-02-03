/**
 * 08-722 Data Structures for Application Programmers.
 * Homework 3 SortedLinkedList Implementation with Recursion
 * Andrew ID: mteng
 * @author Teng Ma
 */
public class SortedLinkedList implements MyListInterface {
    private static class Node {
        private Node next;
        private String data;

        Node(String d, Node n) {
            this.data = d;
            this.next = n;
        }
    }

    private Node head;
    private int count;
    private Node curr;
    private Node prev;

    public SortedLinkedList() {
        head = null;
    }

    public SortedLinkedList(String[] unsorted) {
        prev = null;
        init(prev, unsorted, 0);
    }

    @Override
    public void add(String value) {
        if (value == null) {
            return;
        }
        if (isEmpty()) {
            head = new Node(value, null);
            return;
        }
        if (contains(value)) {
            return;
        }
        Node tmp = head;
        prev = null;
        add(prev, tmp, value);
        return;

    }

    public void init(Node node, String[] unsorted, int temp) {
        if (unsorted.length == 0 || unsorted == null) {
            return;
        }
        if (temp >= unsorted.length) {
            return;
        }
        add(unsorted[temp]);
        temp++;
        init(node, unsorted, temp);

    }

    public void add(Node pre, Node cur, String value) {
        Node temp;
        if (isEmpty()) {
            temp = new Node(value, null);
            this.head = temp;
            return;
        }
        if (cur == null) {
            pre.next = new Node(value, null);
            return;

        }
        if (cur.data.compareTo(value) > 0) {
            temp = new Node(value, cur);
            if (pre == null) {
                head = temp;
            } else {
                pre.next = temp;
            }
            return;
        } else {
            pre = cur;
            cur = cur.next;
            add(pre, cur, value);
        }
    }

    @Override
    public int size() {
        curr = head;
        int numb = 0;
        return size(curr, numb);

    }

    public int size(Node cur, int numb) {
        if (cur == null) {
            return numb;
        } else {
            numb++;
            return size(cur.next, numb);
        }

    }

    @Override
    public void display() {
        if (isEmpty()) {
            System.out.print("[]");
        }
        System.out.print("[");
        display(head);
    }

    public void display(Node cur) {
        if (cur == null) {
            curr = head;
            prev = null;
            return;
        } else {
            System.out.print(cur.data);
            if (cur.next != null) {
                System.out.print(", ");
                display(cur.next);
            } else {
                System.out.println("]");
            }
        }
    }

    public boolean contains(String key) {
        if (isEmpty()) {
            return false;
        } else {
            if (curr == null) {
                curr = head;
                prev = null;
                return false;
            }
            if (curr.data.compareTo(key) == 0) {
                curr = head;
                prev = null;
                return true;
            } else {
                curr = curr.next;
                return contains(key);
            }
        }
    }

    public boolean isEmpty() {
        return head == null;
    }

    public String removeFirst() {
        if (isEmpty()) {
            return null;
        } else {
            String val = head.data;
            head = head.next;
            return val;
        }
    }

    public String removeAt(int index) {

        if (isEmpty()) {
            return null;
        } else {
            if (index == 0) {
                return removeFirst();
            }
            if (curr == null) {
                curr = head;
                prev = null;
                return null;
            } else {
                count = count + 1;
                if (index < 0 || count < index) {
                    throw new RuntimeException("invalid index value");
                } else if (index == count) {
                    String val = curr.data;
                    count = 0;
                    prev.next = curr.next;
                    curr = head;
                    return val;
                } else {
                    prev = null;
                    curr = curr.next;
                    count++;
                }
            }
        }
        return null;
    }
}

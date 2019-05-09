/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */


import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private final Node nil;
    private int size;


    private class Node {
        private Node prev;
        private Node next;
        private final Item item;

        public Node(Node prev, Item item, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    public Deque() {
        nil = new Node(null, null, null);
        first = nil;
        last = nil;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (item == null)
            throw new IllegalArgumentException();
        Node newNode = new Node(nil, item, first);
        if (isEmpty()) {
            first = newNode;
            last = newNode;
        }
        else {
            first.prev = newNode;
            first = newNode;
        }
        size++;
    }

    public void addLast(Item item) {
        if (item == null)
            throw new IllegalArgumentException();
        Node newNode = new Node(last, item, nil);
        if (isEmpty()) {
            first = newNode;
            last = newNode;
        }
        else {
            last.next = newNode;
            last = newNode;
        }
        size++;
    }

    public Item removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException();
        Item item = first.item;
        first = first.next;
        if (first.item == null) {
            last = nil;
        }
        else {
            first.prev = nil;
        }
        size--;
        return item;
    }

    public Item removeLast() {
        if (isEmpty())
            throw new NoSuchElementException();
        Item item = last.item;
        last = last.prev;
        if (last.item == null)
            first = nil;
        else
            last.next = nil;
        size--;
        return item;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current.next != null;
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        // implemented in TestQueue.java
    }
}

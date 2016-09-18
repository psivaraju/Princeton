package week2;


import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Deque Non-iterator operations - Constant worst-case time Iterator constructor
 * - Constant worst-case time Other iterator operations - Constant worst-case
 * time Non-iterator memory use Linear in current # of items Memory per iterator
 * Constant
 * 
 * @author Pramod Sivaraju
 *
 * @param <Item>
 */
public class Deque<Item> implements Iterable<Item> {

    private int size;

    private class Node {
        private Item item;
        private Node next;
        private Node prev;

        public Node(Item item) {
            this.item = item;
        }
    }

    private Node first;
    private Node last;

    public Deque() {
        first = null;
        last = null;
        size = 0;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException("Cannot add null to the Deque");
        }

        Node newNode = new Node(item);
        newNode.next = this.first;
        newNode.prev = null;
        if (this.first != null) {
            this.first.prev = newNode;
        }
        if (this.last == null) {
            this.last = newNode;
        }

        this.first = newNode;
        size++;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException("Cannot add null to the Deque");
        }

        Node newNode = new Node(item);
        newNode.prev = this.last;
        newNode.next = null;
        if (this.last != null) {
            this.last.next = newNode;
        }
        if (this.first == null) {
            this.first = newNode;
        }

        this.last = newNode;
        size++;
    }

    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Cannot remove item from empty deque");
        }

        Node temp = this.first;
        this.first = this.first.next;
        if (this.first != null) {
            this.first.prev = null;
        } else {
            this.last = null;
        }
        temp.next = null;
        size--;
        return temp.item;
    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Cannot remove item from empty deque");
        }

        Node temp = this.last;
        this.last = this.last.prev;

        if (this.last != null) {
            this.last.next = null;
        } else {
            this.first = null;
        }

        temp.prev = null;
        size--;
        return temp.item;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {

        private Node current = first;

        @Override
        public boolean hasNext() {

            return (current != null);
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Node temp = current;
            current = current.next;
            return temp.item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove is not supported");
        }

    }

    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<Integer>();
        for (int index = 0; index <= 100; index++) {
            deque.addFirst(index);
            deque.addLast(index);
        }
        System.out.println("\nSize after adds :: " + deque.size());

        for (int index = 0; index <= 100; index++) {
            System.out.print(deque.removeFirst() + " ");
        }

        System.out.println("\nSize after removes :: " + deque.size());

        for (int index = 0; index <= 100; index++) {
            System.out.print(deque.removeLast() + " ");
        }

        System.out.println("\nSize after removes :: " + deque.size());

        for (int index = 0; index <= 100; index++) {
            deque.addLast(index);
            deque.addFirst(index);
        }

        for (int index = 0; index <= 100; index++) {
            System.out.print(deque.removeLast() + " ");
        }

        System.out.println("\nSize after removes :: " + deque.size());

        for (int index = 0; index <= 100; index++) {
            System.out.print(deque.removeFirst() + " ");
        }

        System.out.println("\nSize after removes :: " + deque.size());

        for (int index = 0; index <= 100; index++) {
            deque.addFirst(index);
            deque.addLast(index);
        }
        System.out.println("\nSize after adds :: " + deque.size());

        Iterator<Integer> it = deque.iterator();

        System.out.println("Iterating...");
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }

    }
}
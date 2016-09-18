package week2;


import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] itemArray;
    private int size;
    private int first;
    private int last;

    public RandomizedQueue() {
        initQueue(0, (Item[]) new Object[2]);
    }

    private void initQueue(int sz, Item[] inputArray) {
        this.itemArray = inputArray;
        this.size = sz;
        this.first = 0;
        this.last = size - 1;
    }

    public boolean isEmpty() {
        return size == 0 ? true : false;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException("Null cannot be added to the randomized queue");
        }
        itemArray[++last] = item;
        size++;
        if (size == itemArray.length) {
            resizeArray(itemArray.length * 2);
        }
    }

    public Item dequeue() {
        int randomNum;
        Item retValue;
        if (size == 0) {
            throw new NoSuchElementException("Trying to dequeue an empty queue");
        }
        randomNum = generateRandomNumber();
        retValue = itemArray[randomNum];
        itemArray[randomNum] = itemArray[last];
        itemArray[last--] = null;
        size--;
        if (size > 0 && size == itemArray.length / 4) {
            resizeArray(itemArray.length / 2);
        }
        return retValue;

    }

    private void resizeArray(int capacity) {
        assert capacity >= size;
        final Item[] tempArray = (Item[]) new Object[capacity];
        int index = 0;
        for (Item item : itemArray) {
            if (item != null) {
                tempArray[index++] = item;
            }
        }
        initQueue(index, tempArray);
    }

    private int generateRandomNumber() {
        return StdRandom.uniform(first, size);
    }

    public Item sample() {
        if (size == 0) {
            throw new NoSuchElementException("Trying to dequeue an empty queue");
        }
        return itemArray[generateRandomNumber()];
    }

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        final private Item[] shuffledArray;
        private int index;

        public RandomizedQueueIterator() {
            index = 0;
            shuffledArray = (Item[]) new Object[size];
            initShuffledArray();
        }

        private void initShuffledArray() {
            int i = 0;
            for (Item item : itemArray) {
                if (item != null) {
                    shuffledArray[i++] = item;
                }
            }
            StdRandom.shuffle(shuffledArray);
        }

        public boolean hasNext() {
            return (index < shuffledArray.length);
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No next element");
            }
            return shuffledArray[index++];
        }

        public void remove() {
            throw new UnsupportedOperationException("remove not supported");
        }
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> randomQueue = new RandomizedQueue<Integer>();
        for (int index = 0; index <= 100; index++) {
            randomQueue.enqueue(index);
        }

        System.out.println("size after enquue" + randomQueue.size());

        System.out.println("Now Sampling");
        for (int index = 0; index <= 100; index++) {
            System.out.print(randomQueue.sample() + " ");
        }

        System.out.println("\nNow Dequing");
        for (int index = 0; index <= 100; index++) {
            System.out.print(randomQueue.dequeue() + " ");
        }
        System.out.println("size after Deque" + randomQueue.size());

        for (int index = 0; index <= 100; index++) {
            randomQueue.enqueue(index);
        }
        System.out.println("size after enquue" + randomQueue.size());

        System.out.println("Now Sampling");
        for (int index = 0; index <= 100; index++) {
            System.out.print(randomQueue.sample() + " ");
        }

        Iterator<Integer> randomQueueIterator = randomQueue.iterator();
        System.out.println("\nNow Iterating");
        while (randomQueueIterator.hasNext()) {
            System.out.print(randomQueueIterator.next() + " ");
        }

        System.out.println("\nNow Dequing");
        for (int index = 0; index <= 100; index++) {
            System.out.print(randomQueue.dequeue() + " ");
        }
        System.out.println("size after Deque" + randomQueue.size());

    }
}

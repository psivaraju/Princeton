package week4;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdRandom;

public class MaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int size = 0;

    private static final int INITIAL_CAP = 2;

    public MaxPQ() {
        pq = (Key[]) new Comparable[INITIAL_CAP];

    }

    public MaxPQ(Key[] a) {
        for (Key key : a) {
            insert(key);
        }
    }

    public void insert(final Key v) {
        pq[++size] = v;
        swim(size);
        if ((size + 1) == pq.length) {
            resizeArray(pq.length * 2);
        }
    }

    private void resizeArray(int capacity) {

        assert capacity >= size + 1;

        pq = Arrays.copyOf(pq, capacity);
    }

    private void swim(final int pos) {
        int current = pos;
        while (current > 1 && pq[current].compareTo(pq[current / 2]) != -1) {
            exch(current, current / 2);
            current = current / 2;
        }
    }

    private void exch(final int src, final int dest) {
        Key temp = pq[src];
        pq[src] = pq[dest];
        pq[dest] = temp;
    }

    public Key delMax() {
        Key temp = pq[1];
        exch(1, size--);
        sink(1);
        return temp;
    }

    private void sink(final int pos) {
        int current = pos;

        while (2 * current <= size) {
            int j = 2 * current;
            if (less(pq[j], pq[j + 1])) {
                j++;
            }

            if (!less(pq[current], pq[j]))
                break;
            exch(current, j);
            current = j;

        }
    }

    private boolean less(Key key1, Key key2) {
        if (key1.compareTo(key2) == -1)
            return true;
        else
            return false;
    }

    boolean isEmpty() {
        return size == 0;
    }

    Key max() {
        return pq[1];
    }

    int size() {
        return size;
    }

    public static void main(String[] args) {
        MaxPQ<Integer> maxpq = new MaxPQ<Integer>();

        int limit = StdRandom.uniform(100);

        for (int i = 0; i < limit; i++) {
            maxpq.insert(StdRandom.uniform(1000000));
        }
        
        System.out.println("size of heap is :: "+maxpq.size());
        System.out.println(" ... Fetching in order ....");
        for (int i = 0; i < limit; i++) {
            System.out.println(maxpq.delMax());
        }
        
        System.out.println("size of heap is :: "+maxpq.size());
    }

}

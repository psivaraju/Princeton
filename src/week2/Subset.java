package week2;


import edu.princeton.cs.algs4.StdIn;

public class Subset {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> rQueue = new RandomizedQueue<String>();
        
        String temp = null;
        while (!StdIn.isEmpty()) {
            rQueue.enqueue(StdIn.readString());
        }

        for (int i = 0; i < k; i++) {
            System.out.println(rQueue.dequeue());
        }

    }
}

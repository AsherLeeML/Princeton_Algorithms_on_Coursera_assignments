/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        if (k <= 0) return;
        int counter = 0;
        RandomizedQueue<String> rQ = new RandomizedQueue<String>();

        while (!StdIn.isEmpty()) {
            String tmp = StdIn.readString();
            counter++;
            if (rQ.size() >= k) {
                if (StdRandom.uniform(counter) < k) {
                    rQ.dequeue();
                }
                else
                    continue;
            }
            rQ.enqueue(tmp);
        }
        for (int i = 0; i < k; i++)
            StdOut.println(rQ.dequeue());
    }
}


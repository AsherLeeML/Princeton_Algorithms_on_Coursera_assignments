/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] rQ;
    private int size;
    private int capacity;

    public RandomizedQueue() {
        capacity = 2;
        size = 0;
        resize();
    }

    private void resize() {
        Item[] newItems = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++)
            newItems[i] = rQ[i];
        for (int i = size; i < capacity; i++)
            newItems[i] = null;
        rQ = newItems;
    }


    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        if (item == null)
            throw new IllegalArgumentException();
        if (size == capacity) {
            capacity *= 2;
            resize();
        }
        rQ[size++] = item;
    }

    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException();
        if (size == capacity / 4) {
            capacity /= 2;
            resize();
        }
        int idx = StdRandom.uniform(size);
        Item tmp = rQ[idx];
        rQ[idx] = rQ[--size];
        return tmp;
    }

    public Item sample() {
        if (isEmpty())
            throw new NoSuchElementException();
        int idx = StdRandom.uniform(size);
        return rQ[idx];
    }

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int[] random;
        private int current;

        public RandomizedQueueIterator() {
            random = new int[size];
            for (int i = 0; i < size; i++)
                random[i] = i;
            StdRandom.shuffle(random);
            current = 0;
        }

        public boolean hasNext() {
            return current < random.length;
        }

        public Item next() {
            if (hasNext()) {
                return rQ[random[current++]];
            }
            else
                throw new NoSuchElementException();

        }
    }

    public static void main(String[] args) {
        // to-be-done.

    }
}

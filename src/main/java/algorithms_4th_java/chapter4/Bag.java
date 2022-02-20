package algorithms_4th_java.chapter4;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * ±³°ü
 *
 * @param <Item>
 */
public class Bag<Item> implements Iterable<Item> {
    private Node first = null;
    private int n = 0;

    public Bag() {
    }

    public boolean isEmpty() {
        return this.first == null;
    }

    public int size() {
        return this.n;
    }

    public void add(Item item) {
        Node oldfirst = this.first;
        this.first = new Node();
        this.first.item = item;
        this.first.next = oldfirst;
        ++this.n;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator(this.first);
    }

    public static void main(String[] args) {
        Bag<String> bag = new Bag<>();

        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            bag.add(item);
        }

        StdOut.println("size of bag = " + bag.size());

//        while(i$.hasNext()) {
//            String s = (String)i$.next();
//            StdOut.println(s);
//        }
        for (String s : bag) {
            StdOut.println(s);
        }

    }

    private static class ListIterator<Item> implements Iterator<Item> {
        private Node<Item> current;

        public ListIterator(Node<Item> first) {
            this.current = first;
        }

        @Override
        public boolean hasNext() {
            return this.current != null;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Item next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            } else {
                Item item = this.current.item;
                this.current = this.current.next;
                return item;
            }
        }
    }

    private static class Node<Item> {
        private Item item;
        private Node<Item> next;

        private Node() {
        }
    }
}
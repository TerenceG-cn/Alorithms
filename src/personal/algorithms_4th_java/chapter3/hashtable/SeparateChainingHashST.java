package personal.algorithms_4th_java.chapter3.hashtable;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.SequentialSearchST;

import java.util.ArrayList;
import java.util.List;

/**
 * 基于拉链法的散列表
 *
 * @param <Key>
 * @param <Value>
 */
public class SeparateChainingHashST<Key, Value> {
    private int N;//键值对总数
    private int M;//散列表的大小
    private SequentialSearchST<Key, Value>[] st;//存放链表对象的数组。SequentialSearchST是一个无序符号表

    public SeparateChainingHashST() {
        this(997);
    }

    public SeparateChainingHashST(int M) {
        this.M = M;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
        for (int i = 0; i < M; i++)
            st[i] = new SequentialSearchST<>();
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fff_fff_f) % M;
    }

    public Value get(Key key) {
        return st[hash(key)].get(key);
    }

    public void put(Key key, Value value) {
        st[hash(key)].put(key, value);
        //++N;//可能要加
    }

    public Iterable<Key> keys() {//3.4.19
        Queue<Key> keys = new Queue<Key>();
        for (SequentialSearchST<Key, Value> keyValueSequentialSearchST : st) {
            Iterable<Key> queue = keyValueSequentialSearchST.keys();
            for (Key key : queue) {
                keys.enqueue(key);
            }
        }
        return keys;
    }

    public void delete(Key key) {
        st[hash(key)].delete(key);
    }
}

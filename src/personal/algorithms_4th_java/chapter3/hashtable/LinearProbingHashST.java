package personal.algorithms_4th_java.chapter3.hashtable;

import java.util.Objects;

/**
 * 基于线性探测表的符号表
 *
 * @param <Key>
 * @param <Value>
 */
public class LinearProbingHashST<Key, Value> {
    private int N;
    private int M = 16;//线性探测表的大小
    private Key[] keys;
    private Value[] vals;

    public LinearProbingHashST() {
        keys = (Key[]) new Objects[M];
        vals = (Value[]) new Objects[M];
        //this(M); 不能这样写，会初始化失败
    }

    public LinearProbingHashST(int cap) {
        keys = (Key[]) new Objects[cap];
        vals = (Value[]) new Objects[cap];
        M = cap;
    }

    private boolean contains(Key key) {
        for (int i = hash(key); keys[i] != null; i = (i + 1) % M) {
            if (keys[i].equals(key))
                return true;
        }
        return false;
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fff_fff_f) % M;
    }

    private void resize(int cap) {
        LinearProbingHashST<Key, Value> t = new LinearProbingHashST<>(cap);
        for (int i = 0; i < M; i++)
            if (keys[i] != null)
                t.put(keys[i], vals[i]);
        keys = t.keys;
        vals = t.vals;
        M = t.M;
    }

    public void put(Key key, Value value) {
        if (N >= M / 2) resize(2 * M);

        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % M) {
            if (keys[i].equals(key)) {
                vals[i] = value;
                return;
            }
        }
        keys[i] = key;
        vals[i] = value;
        N++;
    }

    public Value get(Key key) {
        for (int i = hash(key); keys[i] != null; i = (i + 1) % M) {
            if (keys[i].equals(key))
                return vals[i];
        }
        return null;
    }

    public void delete(Key key) {
        if (!contains(key)) return;
        int i = hash(key);
        while (!key.equals(keys[i]))
            i = (i + 1) % M;
        keys[i] = null;
        vals[i] = null;
        i = (i + 1) % M;
        while (keys[i] != null) {//把删除键值对之后的重新插入
            Key keyToRedo = keys[i];
            Value valToRedo = vals[i];
            keys[i] = null;
            vals[i] = null;
            N--;
            put(keyToRedo, valToRedo);
            i = (i + 1) % M;
        }
        if (N > 0 && N == M / 8) resize(M / 2);
    }
}

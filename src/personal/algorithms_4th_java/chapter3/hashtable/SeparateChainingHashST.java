package personal.algorithms_4th_java.chapter3.hashtable;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.SequentialSearchST;

import java.util.ArrayList;
import java.util.List;

/**
 * ������������ɢ�б�
 * @param <Key>
 * @param <Value>
 */
public class SeparateChainingHashST<Key,Value> {
    private int N;//��ֵ������
    private int M;//ɢ�б�Ĵ�С
    private SequentialSearchST<Key,Value>[] st;//��������������顣SequentialSearchST��һ��������ű�

    public SeparateChainingHashST(){
        this(997);
    }
    public SeparateChainingHashST(int M){
        this.M=M;
        st=(SequentialSearchST<Key,Value>[])new SequentialSearchST[M];
        for(int i=0;i<M;i++)
            st[i]=new SequentialSearchST<>();
    }

    private int hash(Key key){
        return (key.hashCode()&0x7fff_fff_f)%M;
    }
    private Value get(Key key){
        return st[hash(key)].get(key);
    }
    private void put(Key key,Value value){
        st[hash(key)].put(key,value);
    }

    public Iterable<Key> keys(){
        Queue<Key> keys = new Queue<Key>();
        for (SequentialSearchST<Key, Value> keyValueSequentialSearchST : st) {
            Iterable<Key> queue = keyValueSequentialSearchST.keys();
            for (Key key : queue) {
                keys.enqueue(key);
            }
        }
        return keys;
    }
}

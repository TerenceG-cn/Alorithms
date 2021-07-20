package algorithms_4th_java.chapter4.graph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;

/**
 * @author TerenceG
 * @classname SymbolGraph
 * @description 符号图
 * @lastmodifydate 2021/7/20
 */
public class SymbolGraph {
    //符号名->索引
    private ST<String,Integer> st;
    private String[] keys;
    //图
    private Graph G;

    public SymbolGraph(String stream,String sp){
        st=new ST<String, Integer>();
        In in = new In(stream);
        //第一遍读取读取字符串，分割，建立索引 st
        while(in.hasNextLine()){
            String[] a=in.readLine().split(sp);
            for(int i=0;i<a.length;i++){
                if(!st.contains(a[i])){
                    st.put(a[i],st.size());
                }
            }
        }
        //keyset-> Set集合 -> 数组 keys
        keys = new String[st.size()];
        for(String name:st.keys()){
            keys[st.get(name)]=name;
        }

        //第二遍读取字符串，建立图 G
        G=new Graph(st.size());
        in = new In(stream);
        while(in.hasNextLine()){
            String[] a=in.readLine().split(sp);
            int v = st.get(a[0]);
            for(int i=1;i<a.length;i++){
                G.addEdge(v,st.get(a[i]));
            }
        }
    }

    public boolean contains(String s){
        return st.contains(s);
    }

    public int index(String s){
        return st.get(s);
    }

    public String name(int v){
        return keys[v];
    }

    public Graph G() {
        return G;
    }
}
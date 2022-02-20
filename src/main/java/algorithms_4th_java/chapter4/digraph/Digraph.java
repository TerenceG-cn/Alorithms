package algorithms_4th_java.chapter4.digraph;


import algorithms_4th_java.chapter4.Bag;
import edu.princeton.cs.algs4.In;

import java.util.NoSuchElementException;

/**
 * @author TerenceG
 * @classname Digraph
 * @description 有向图
 * @lastmodifydate 2021/7/22
 */
public class Digraph {
    private final int V;
    private int E;
    private Bag<Integer>[] adj;

    private int[] indegree;        // indegree[v] = indegree of vertex v

    public Digraph(int V){
        this.V=V;
        this.E=0;
        adj=(Bag<Integer>[])new Bag[V];
        for(int v=0;v<V;v++){
            adj[v]=new Bag<Integer>();
        }
    }

    public int V(){
        return this.V;
    }

    public int E(){
        return E;
    }

    public Iterable<Integer> adj(int v){
        return adj[v];
    }

    public void addEdge(int v,int w){
        adj[v].add(w);
        E++;
    }
    //反向图
    public Digraph reverse(){
        Digraph r=new Digraph(V);
        for(int v=0;v<V;v++){
            for(int w:adj[v]){
                r.addEdge(w,v);
            }
        }
        return r;
    }


    public Digraph(In in) {
        try {
            this.V = in.readInt();
            if (V < 0) throw new IllegalArgumentException("number of vertices in a Digraph must be nonnegative");
            indegree = new int[V];
            adj = (Bag<Integer>[]) new Bag[V];
            for (int v = 0; v < V; v++) {
                adj[v] = new Bag<Integer>();
            }
            int E = in.readInt();
            if (E < 0) throw new IllegalArgumentException("number of edges in a Digraph must be nonnegative");
            for (int i = 0; i < E; i++) {
                int v = in.readInt();
                int w = in.readInt();
                addEdge(v, w);
            }
        }
        catch (NoSuchElementException e) {
            throw new IllegalArgumentException("invalid input format in Digraph constructor", e);
        }
    }

}
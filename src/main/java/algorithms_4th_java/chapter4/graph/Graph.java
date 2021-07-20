package algorithms_4th_java.chapter4.graph;

import edu.princeton.cs.algs4.In;

/**
 * @author TerenceG
 * @lastmodifydate 2021/4/5
 * @description: 无向图
 * @Param: * @param null
 * @Return:
 */
public class Graph {
    /**
     * 顶点个数
     */
    private final int V;
    /**
     * 边个数
     */
    private int E;
    /**
     * 邻接矩阵
     */
    private Bag<Integer>[] adj;

    public Graph(int V) {
        this.V = V;
        this.E = 0;
        //创建邻接表
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Integer>();
        }
    }

    public Graph(In in) {
        this(in.readInt());
        int E = in.readInt();
        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            addEdge(v, w);
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

}

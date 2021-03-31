package algorithms_4th_java.chapter4.dfs;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;
import algorithms_4th_java.chapter4.graph.Graph;

import java.util.Iterator;

/**
 * 深度优先搜索实现寻找路径
 */
public class DepthFirstPaths {
    private boolean[] marked;//是否在这个顶点上调用过dfs
    private int[] edgeTo;//记录从起点s到一个顶点v的已知路径上的最后一个顶点
    private final int s;//起点

    public DepthFirstPaths(Graph G, int s) {
        this.s = s;
        this.edgeTo = new int[G.V()];
        this.marked = new boolean[G.V()];
        this.validateVertex(s);
        this.dfs(G, s);
    }

    private void dfs(Graph G, int v) {
        this.marked[v] = true;

        for (int w : G.adj(v)) {
            if (!this.marked[w]) {//由边v-w第一次访问w时，将edgeTo[w]设为v来记录路径Path
                this.edgeTo[w] = v;
                this.dfs(G, w);
            }
        }

    }

    public boolean hasPathTo(int v) {
        this.validateVertex(v);
        return this.marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        this.validateVertex(v);
        if (!this.hasPathTo(v)) {
            return null;
        } else {
            Stack<Integer> path = new Stack();

            for (int x = v; x != this.s; x = this.edgeTo[x]) {
                path.push(x);
            }

            path.push(this.s);
            return path;
        }
    }

    private void validateVertex(int v) {
        int V = this.marked.length;
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
        }
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        Graph G = new Graph(in);
        int s = Integer.parseInt(args[1]);
        DepthFirstPaths dfs = new DepthFirstPaths(G, s);

        for (int v = 0; v < G.V(); ++v) {
            if (!dfs.hasPathTo(v)) {
                StdOut.printf("%d to %d:  not connected\n", new Object[]{s, v});
            } else {
                StdOut.printf("%d to %d:  ", new Object[]{s, v});
                Iterator i$ = dfs.pathTo(v).iterator();

                while (i$.hasNext()) {
                    int x = (Integer) i$.next();
                    if (x == s) {
                        StdOut.print(x);
                    } else {
                        StdOut.print("-" + x);
                    }
                }

                StdOut.println();
            }
        }

    }
}

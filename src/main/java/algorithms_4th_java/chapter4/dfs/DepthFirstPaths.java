package algorithms_4th_java.chapter4.dfs;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;
import algorithms_4th_java.chapter4.graph.Graph;

import java.util.Iterator;

/**
 *@classname         DepthFirstPaths
 *@description       �����������ʵ��Ѱ��·��
 *@author            TerenceG
 *@lastmodifydate    2021/4/5
 */
public class DepthFirstPaths {
    /**
     * �Ƿ�����������ϵ��ù�dfs
     */
    private boolean[] marked;
    /**
     * ��¼�����s��һ������v����֪·���ϵ����һ������
     */
    private int[] edgeTo;
    /**
     * ���
     */
    private final int s;

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
            // �ɱ�v-w��һ�η���wʱ����edgeTo[w]��Ϊv����¼·��Path
            if (!this.marked[w]) {
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

package algorithms_4th_java.chapter4.bfs;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.Queue;
import algorithms_4th_java.chapter4.graph.Graph;


/**
 * 使用广度优先搜索查找图中的路径
 */
public class BreadthFirstPaths {
    /**
     * 是否在这个顶点上调用过dfs
     */
    private boolean[] marked;
    /**
     * 记录从起点s到一个顶点v的已知路径上的最后一个顶点
     */
    private int[] edgeTo;
    /**
     * 起点
     */
    private final int s;

    public BreadthFirstPaths(Graph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        bfs(G,s);
    }

    private void bfs(Graph G, int s) {
        Queue<Integer> queue = new Queue<>();
        //标记起点
        marked[s] = true;
        //把起点加入队列
        queue.enqueue(s);
        while (!queue.isEmpty()) {
            //从队列中删除最近添加的元素
            int v = queue.dequeue();
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    marked[w] = true;
                    queue.enqueue(w);
                }
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    /**
     * @author TerenceG
     * @lastmodifydate 2021/3/31
     * @description: 同dfs
     * @Param: * @param v
     * @Return: java.lang.Iterable<java.lang.Integer>
     */
    public Iterable<Integer> pathTo(int v) {
        this.validateVertex(v);

        if (this.hasPathTo(v)) {
            Stack<Integer> path = new Stack();

            for (int x = v; x != this.s; x = this.edgeTo[x]) {
                path.push(x);
            }

            path.push(this.s);
            return path;
        } else {
            return null;
        }
    }

    private void validateVertex(int v) {
        int V = this.marked.length;
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
        }
    }

}

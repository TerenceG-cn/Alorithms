package personal.algorithms_4th_java.chapter4.dfs;


import edu.princeton.cs.algs4.StdOut;
import personal.algorithms_4th_java.chapter4.graph.Graph;

public class DepthFirstSearch {
    private boolean[] marked;//记录和起点联通的所有点，是否在这个顶点伤调用过dfs
    private int count;

    public DepthFirstSearch(Graph G, int s) {
        this.marked = new boolean[G.V()];
        this.validateVertex(s);
        this.dfs(G, s);
    }

    /**
     * 访问一个顶点时
     * 1.将他标记为true
     * 2.递归访问他所有没有标记过（false)f的邻居节点
     *
     * @param G
     * @param v
     */
    private void dfs(Graph G, int v) {
//        System.out.println("联通"+v);
//        for(int i=0;i<marked.length;i++)
//            System.out.print(marked[i]+" ");
//        System.out.println();
        ++this.count;
        this.marked[v] = true;

        for (int w : G.adj(v)) {
            //System.out.println(v+"-"+w);
            if (!this.marked[w]) {
                this.dfs(G, w);
            }
        }

    }

    /**
     * 判断v和s是联通的吗
     *
     * @param v
     * @return
     */
    public boolean marked(int v) {
        this.validateVertex(v);
        return this.marked[v];
    }

    public int count() {
        return this.count;
    }

    /**
     * 验证顶点v是否合法，在0~V-1之中
     *
     * @param v
     */
    private void validateVertex(int v) {
        int V = this.marked.length;
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
        }
    }

    public static void main(String[] args) {
        //In in = new In(args[0]);
        Graph G = new Graph(6);
        G.addEdge(0, 5);
        G.addEdge(2, 4);
        G.addEdge(2, 3);
        G.addEdge(1, 2);
        G.addEdge(0, 1);
        G.addEdge(3, 4);
        G.addEdge(3, 5);
        G.addEdge(0, 2);
        //int s = Integer.parseInt(args[1]);
        int s = 0;//起点
        DepthFirstSearch search = new DepthFirstSearch(G, s);

        for (int v = 0; v < G.V(); ++v) {
            if (search.marked(v)) {
                StdOut.print(v + " ");
            }
        }

        StdOut.println();
        if (search.count() != G.V()) {//dfs mark次数不等于顶点数，说明图不连通
            StdOut.println("NOT connected");
        } else {
            StdOut.println("connected");
        }

    }
}

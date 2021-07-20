package algorithms_4th_java.chapter4.graph.dfs;


import edu.princeton.cs.algs4.StdOut;
import algorithms_4th_java.chapter4.graph.Graph;

public class DepthFirstSearch {
    /** ��¼�������ͨ�����е㣬�Ƿ�����������˵��ù�dfs*/
    private boolean[] marked;
    private int count;

    public DepthFirstSearch(Graph G, int s) {
        this.marked = new boolean[G.V()];
        this.validateVertex(s);
        this.dfs(G, s);
    }

    /**
     * @author TerenceG
     * @lastmodifydate 2021/4/5
     * @description: ����һ������ʱ,
     * 1.�������Ϊtrue
     * 2.�ݹ����������û�б�ǹ���false)f���ھӽڵ�
     * @Param: * @param G * @param v
     * @Return: void
     */
    private void dfs(Graph G, int v) {
        ++this.count;
        this.marked[v] = true;

        for (int w : G.adj(v)) {
            if (!this.marked[w]) {
                this.dfs(G, w);
            }
        }

    }

    /**
     * �ж�v��s����ͨ����
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
     * ��֤����v�Ƿ�Ϸ�����0~V-1֮��
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
        //���
        //int s = Integer.parseInt(args[1]);
        int s = 0;
        DepthFirstSearch search = new DepthFirstSearch(G, s);

        for (int v = 0; v < G.V(); ++v) {
            if (search.marked(v)) {
                StdOut.print(v + " ");
            }
        }

        StdOut.println();
        //dfs mark���������ڶ�������˵��ͼ����ͨ
        if (search.count() != G.V()) {
            StdOut.println("NOT connected");
        } else {
            StdOut.println("connected");
        }

    }
}

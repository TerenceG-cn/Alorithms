package personal.algorithms_4th_java.chapter4;
import edu.princeton.cs.algs4.Stack;
import personal.algorithms_4th_java.chapter4.graph.Graph;


/**
 * 使用广度优先搜索查找图中的路径
 */
public class BreadthFirstPaths {
    private boolean[] marked;//是否在这个顶点上调用过dfs
    private int[] edgeTo;//记录从起点s到一个顶点v的已知路径上的最后一个顶点
    private final int s;//起点

    public BreadthFirstPaths(Graph G,int s){
        marked=new boolean[G.V()];
        edgeTo=new int[G.V()];
        this.s=s;
        //bfs(G,s);
    }

    private void bfs(Graph G,int s){
        Queue<Integer> queue=new Queue<>();
        marked[s]=true;//标记起点
        queue.enqueue(s);//把起点加入队列
        while(!queue.isEmpty()){
            int v=queue.dequeue();//从队列中删除最近添加的元素
            for(int w:G.adj(v)){
                if(!marked[w]){
                    edgeTo[w]=v;
                    marked[w]=true;
                    queue.enqueue(w);
                }
            }
        }
    }

    public boolean hasPathTo(int v){
        return marked[v];
    }

    //同dfs
    public Iterable<Integer> pathTo(int v) {
        this.validateVertex(v);
        if (!this.hasPathTo(v)) return null;
        else {
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

}

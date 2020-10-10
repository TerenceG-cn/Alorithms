package personal.algorithms_4th_java.chapter4;
import edu.princeton.cs.algs4.Stack;
import personal.algorithms_4th_java.chapter4.graph.Graph;


/**
 * ʹ�ù��������������ͼ�е�·��
 */
public class BreadthFirstPaths {
    private boolean[] marked;//�Ƿ�����������ϵ��ù�dfs
    private int[] edgeTo;//��¼�����s��һ������v����֪·���ϵ����һ������
    private final int s;//���

    public BreadthFirstPaths(Graph G,int s){
        marked=new boolean[G.V()];
        edgeTo=new int[G.V()];
        this.s=s;
        //bfs(G,s);
    }

    private void bfs(Graph G,int s){
        Queue<Integer> queue=new Queue<>();
        marked[s]=true;//������
        queue.enqueue(s);//�����������
        while(!queue.isEmpty()){
            int v=queue.dequeue();//�Ӷ�����ɾ�������ӵ�Ԫ��
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

    //ͬdfs
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

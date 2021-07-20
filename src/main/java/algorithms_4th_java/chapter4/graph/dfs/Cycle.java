package algorithms_4th_java.chapter4.graph.dfs;

import algorithms_4th_java.chapter4.graph.Graph;
//ʹ�����������������ͼ������ʾ��
/**
 * @author TerenceG
 * @classname Cycle
 * @description G �� �޻�ͼ�𣿣����費�����Ի���ƽ�бߣ�todo
 * @lastmodifydate 2021/7/20
 */
public class Cycle {
    private boolean[] marked;
    private boolean hasCycle;
    public Cycle(Graph G){
        marked = new boolean[G.V()];
        for(int s=0;s<G.V();s++){
            if(!marked[s]){
                dfs(G,s,s);
            }
        }
    }

    private void dfs(Graph G,int v,int u){
        marked[v]=true;
        for(int w:G.adj(v)){
            if(!marked[w]){
                dfs(G,w,v);
            }else if(w!=u){
                hasCycle=true;
            }
        }
    }

    public boolean hasCycle(){
        return hasCycle;
    }

}
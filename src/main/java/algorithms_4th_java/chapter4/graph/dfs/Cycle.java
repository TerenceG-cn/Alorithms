package algorithms_4th_java.chapter4.graph.dfs;

import algorithms_4th_java.chapter4.graph.Graph;
//使用深度优先搜索处理图的其他示例
/**
 * @author TerenceG
 * @classname Cycle
 * @description G 是 无环图吗？（假设不存在自环和平行边）todo
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
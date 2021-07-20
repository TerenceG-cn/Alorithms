package algorithms_4th_java.chapter4.graph.dfs;

import algorithms_4th_java.chapter4.graph.Graph;

/**
 * @author TerenceG
 * @classname TwoColor
 * @description G是二分图吗？（双色问题）
 * @lastmodifydate 2021/7/20
 */
public class TwoColor {
    private boolean[] marked;
    private boolean[] color;
    private boolean isTwoColorable = true;

    public TwoColor(Graph G) {
        marked = new boolean[G.V()];
        color = new boolean[G.V()];
        for (int s = 0; s < G.V(); s++) {
            if (!marked[s]) {
                dfs(G, s);
            }
        }
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            } else if (color[w] = color[v]) {
                isTwoColorable = false;
            }
        }
    }

    public boolean isTwoColorable() {
        return isTwoColorable;
    }
}
package algorithms_4th_java.chapter1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * @author TerenceG
 * @classname UF
 * @description unon-find实现 p138
 * @lastmodifydate 2021/4/4
 */
public class UF {
    /**
     * 分量id（以触点作为索引）
     */
    private int[] id;
    /**
     * 分量数量
     */
    private int count;

    public UF(int N) {
        //初始化分量id的数据
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    public int count() {
        return count;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    //quick-find实现
    public int find(int p) {
        return id[p];
    }

    public void union(int p, int q) {
        //将p和q归并到相同的分量中
        int pId = find(p);
        int qId = find(q);
        //如果p，q已经在相同的分量之中则不需要采取任何措施
        if (pId == qId) {
            return;
        }
        //将p的分量重命名为q的名称
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pId) {
                id[i] = pId;
            }
        }
        count--;
    }

    public static void main(String[] args) {
        //解决由StdIn得到的动态连通性问题
        int N = StdIn.readInt();
        UF uf = new UF(N);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (uf.connected(p, q)) {
                continue;
            }
            uf.union(p, q);
            StdOut.println(uf.count() + "components");
        }
    }
}
package algorithms_4th_java.chapter1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * @author TerenceG
 * @classname UF
 * @description unon-findʵ�� p138
 * @lastmodifydate 2021/4/4
 */
public class UF {
    /**
     * ����id���Դ�����Ϊ������
     */
    private int[] id;
    /**
     * ��������
     */
    private int count;

    public UF(int N) {
        //��ʼ������id������
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

    //quick-findʵ��
    public int find(int p) {
        return id[p];
    }

    public void union(int p, int q) {
        //��p��q�鲢����ͬ�ķ�����
        int pId = find(p);
        int qId = find(q);
        //���p��q�Ѿ�����ͬ�ķ���֮������Ҫ��ȡ�κδ�ʩ
        if (pId == qId) {
            return;
        }
        //��p�ķ���������Ϊq������
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pId) {
                id[i] = pId;
            }
        }
        count--;
    }

    public static void main(String[] args) {
        //�����StdIn�õ��Ķ�̬��ͨ������
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
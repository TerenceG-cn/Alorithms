package IntroductionToAlgorithms.chapter15_dynamic_programming;

import edu.princeton.cs.algs4.In;

import java.util.HashMap;
import java.util.Map;

/**
 * 切割钢条问题
 */
public class CUT_ROD {
    /**
     * p206，朴素递归算法，运行时间o(2^n)
     *
     * @param p 钢条价格数组
     * @param n 钢条长度
     * @return q 最大价格
     */
    public static int cut_rod(int[] p, int n) {
        int q = -1;
        if (n == 0) return 0;
        for (int i = 1; i <= Math.min(p.length - 1, n); i++) {
            q = Math.max(q, p[i] + cut_rod(p, n - i));
        }
        return q;
    }

    /**
     * p207,带备忘的自顶向下法,O(n^2)
     *
     * @param p 钢条价格数组
     * @param n 钢条长度
     * @return q 最大价格
     */
    public static int memoized_cut_rod(int[] p, int n) {
        int r[] = new int[n + 1];//辅助数组初始化为-∞，保存子问题的解
        for (int i = 0; i < n + 1; i++)
            r[i] = -1;
        return memoized_cut_rod_aux(p, n, r);
    }

    private static int memoized_cut_rod_aux(int[] p, int n, int[] r) {
        int q = -1;
        if (r[n] >= 0) return r[n];
        if (n == 0) q = 0;
        else {
            for (int i = 1; i <= Math.min(p.length - 1, n); i++)
                q = Math.max(q, p[i] + memoized_cut_rod_aux(p, n - i, r));
        }
        r[n] = q;
        return q;
    }

    /**
     * 练习15.1-4
     * @param p
     * @param n
     * @return 最大收益 和 切割方案
     */
    public static ResultSet extended_memoized_cut_rod(int[] p, int n) {
        int s[] = new int[n + 1];
        int r[] = new int[n + 1];//辅助数组初始化为-∞，保存子问题的解
        for (int i = 0; i < n + 1; i++)
            r[i] = -1;
        extended_memoized_cut_rod_aux(p, n, r, s);
        return new ResultSet(r, s);
    }
    private static int extended_memoized_cut_rod_aux(int[] p, int n, int[] r, int[] s) {
        int q = -1;
        if (r[n] >= 0) return r[n];
        if (n == 0) q = 0;
        else {
            for (int i = 1; i <= Math.min(p.length - 1, n); i++) {
                //System.out.println(i);
                int tmp = p[i] + extended_memoized_cut_rod_aux(p, n - i, r, s);//会出现栈越界 todo
                if (q < tmp) {
                    q = tmp;
                    s[n] = i;
                }
            }
        }
        r[n] = q;
        return q;
    }


    /**
     * 自底向上,O(n^2)
     *
     * @param p 钢条价格数组
     * @param n 钢条长度
     * @return 最大价格
     */
    public int bottom_up_cut_rod(int[] p, int n) {
        int r[] = new int[n + 1];
        r[0] = 0;
        for (int j = 1; j <= n; j++) {
            int q = -1;
            for (int i = 1; i <= Math.min(j, p.length - 1); i++)
                q = Math.max(q, p[i] + r[j - i]);
            r[j] = q;
        }
        return r[n];
    }

    /**
     * @param p
     * @param n
     * @return r[n]:最优收益值 s[n]:最优解对应的第一段钢条的切割长度
     */
    public ResultSet extended_bottom_up_cut_rod(int[] p, int n) {
        int r[] = new int[n + 1];
        int s[] = new int[n + 1];
        r[0] = 0;
        for (int j = 1; j <= n; j++) {
            int q = -1;
            for (int i = 1; i <= Math.min(j, p.length - 1); i++)
                if (q < (p[i] + r[j - i])) {
                    q = p[i] + r[j - i];
                    s[j] = i;
                }
            r[j] = q;
        }

        return new ResultSet(r, s);
    }

    private static class ResultSet {
        private int[] r;
        private int[] s;

        public ResultSet(int[] r, int[] s) {
            this.r = r;
            this.s = s;
        }

        public int[] getR() {
            return r;
        }

        public void setR(int[] r) {
            this.r = r;
        }

        public int[] getS() {
            return s;
        }

        public void setS(int[] s) {
            this.s = s;
        }
    }

    public void print_cut_solution(int[] p, int n) {
        //ResultSet rAnds=extended_bottom_up_cut_rod(p,n);
        ResultSet rAnds = extended_memoized_cut_rod(p, n);
        System.out.println("最大收益：" + rAnds.getR()[n]);
        System.out.print("切割方案：");
        while (n > 0) {
            System.out.print(rAnds.getS()[n] + " ");
            n = n - rAnds.getS()[n];
        }
        System.out.println();
    }


    public static void main(String[] args) {
        int[] p = {0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30};
//        for(int n=0;n<41;n++)
//            System.out.println("r"+n+"="+cut_rod(p,n));
//        for(int n=0;n<41;n++)
//            System.out.println("r"+n+"="+memoized_cut_rod(p,n));
//            System.out.println("r"+n+"="+new CUT_ROD().bottom_up_cut_rod(p,n));
//            new CUT_ROD().print_cut_solution(p,n);
        //new CUT_ROD().print_cut_solution(p,10);
        new CUT_ROD().print_cut_solution(p, 6978);//6978?会栈越界
    }
}

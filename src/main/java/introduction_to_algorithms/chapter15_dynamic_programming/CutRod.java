package introduction_to_algorithms.chapter15_dynamic_programming;

/**
 * @author TerenceG
 * @classname CutRod
 * @description 切割钢条问题
 * @lastmodifydate 2021/3/23
 */
public class CutRod {
    /**
     * p206，朴素递归算法，运行时间o(2^n)
     *
     * @param p 钢条价格数组
     * @param n 钢条长度git
     * @return q 最大价格
     */
    public static int cutRod(int[] p, int n) {
        int q = -1;
        if (n == 0) {
            return 0;
        }
        for (int i = 1; i <= Math.min(p.length - 1, n); i++) {
            q = Math.max(q, p[i] + cutRod(p, n - i));
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
    public static int memoizedCutRod(int[] p, int n) {
        //辅助数组初始化为-∞，保存子问题的解
        int r[] = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            r[i] = -1;
        }
        return memoizedCutRodAux(p, n, r);
    }

    private static int memoizedCutRodAux(int[] p, int n, int[] r) {
        int q = -1;
        if (r[n] >= 0) {
            return r[n];
        }
        if (n == 0) {
            q = 0;
        } else {
            for (int i = 1; i <= Math.min(p.length - 1, n); i++) {
                q = Math.max(q, p[i] + memoizedCutRodAux(p, n - i, r));
            }
        }
        r[n] = q;
        return q;
    }

    /**
     * 练习15.1-4
     *
     * @param p
     * @param n
     * @return 最大收益 和 切割方案
     */
    public static ResultSet extendedMemoizedCutRod(int[] p, int n) {
        int s[] = new int[n + 1];
        //辅助数组初始化为-∞，保存子问题的解
        int r[] = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            r[i] = -1;
        }
        extended_memoized_cut_rod_aux(p, n, r, s);
        return new ResultSet(r, s);
    }

    private static int extended_memoized_cut_rod_aux(int[] p, int n, int[] r, int[] s) {
        int q = -1;
        if (r[n] >= 0) {
            return r[n];
        }
        if (n == 0) {
            q = 0;
        } else {
            for (int i = 1; i <= Math.min(p.length - 1, n); i++) {
                //会出现栈越界 todo
                int tmp = p[i] + extended_memoized_cut_rod_aux(p, n - i, r, s);
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
            for (int i = 1; i <= Math.min(j, p.length - 1); i++) {
                q = Math.max(q, p[i] + r[j - i]);
            }
            r[j] = q;
        }
        return r[n];
    }

    /**
     * 自底向上的扩展版本
     *
     * @param p
     * @param n
     * @return r[n]:最优收益值 s[n]:最优解对应的第一段钢条的切割长度
     */
    public ResultSet extendedBottomUpCutRod(int[] p, int n) {
        int r[] = new int[n + 1];
        int s[] = new int[n + 1];
        r[0] = 0;
        for (int j = 1; j <= n; j++) {
            int q = -1;
            for (int i = 1; i <= Math.min(j, p.length - 1); i++) {
                if (q < (p[i] + r[j - i])) {
                    q = p[i] + r[j - i];
                    s[j] = i;
                }
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

    public void print_cut_solution(int[] p, int n, ResultSet rAnds) {
        //ResultSet rAnds=extended_bottom_up_cut_rod(p,n);
        //ResultSet rAnds = extended_memoized_cut_rod(p, n);
        System.out.println("最大收益：" + rAnds.getR()[n]);
        System.out.print("切割方案：");
        while (n > 0) {
            System.out.print(rAnds.getS()[n] + " ");
            n = n - rAnds.getS()[n];
        }
        System.out.println();
    }

    /**
     * 15.1-3
     *
     * @param p
     * @param n
     * @param c 每次切割的成本
     * @return
     */
    public ResultSet cost_bottom_up_cut_rod(int[] p, int n, int c) {
        int r[] = new int[n + 1];
        int s[] = new int[n + 1];
        r[0] = 0;
        for (int j = 1; j <= n; j++) {
            int q = -1;
            for (int i = 1; i <= Math.min(j, p.length - 1); i++) {
                if (q < (p[i] + r[j - i])) {
                    q = p[i] + r[j - i];
                    s[j] = i;
                }
            }
            //todo
            if (j < p.length && q == p[j]) {
                r[j] = q;
            } else {
                r[j] = q - c;
            }
        }

        return new ResultSet(r, s);
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
//        new CUT_ROD().print_cut_solution(p, 6978);//6978?会栈越界
        CutRod cutRod = new CutRod();
        ResultSet res = cutRod.cost_bottom_up_cut_rod(p, 23, 3);
        cutRod.print_cut_solution(p, 23, res);

    }
}

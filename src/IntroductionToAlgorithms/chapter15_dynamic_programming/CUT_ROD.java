package IntroductionToAlgorithms.chapter15_dynamic_programming;

/**
 * p206,切割钢条问题
 */
public class CUT_ROD {
    /**
     *
     * @param p 钢条价格数组
     * @param n 钢条长度
     * @return q 最大价格
     *
     * 朴素递归算法，运行时间o(2^n)
     */
    public static int cut_rod(int[] p,int n){
        int q=-1;
        if(n==0) return 0;
        for(int i=1;i<=Math.min(p.length-1,n);i++){
            q=Math.max(q,p[i]+cut_rod(p,n-i));
        }
        return q;
    }

    /**
     * p207,带备忘的自顶向下法
     * @param p
     * @param n
     * @return
     */
    public static int memoized_cut_rod(int[] p,int n){
        int r[]=new int[n+1];
        for(int i=0;i<n+1;i++)
            r[i]=-1;
        return memoized_cut_rod_aux(p,n,r);
    }
    private static int memoized_cut_rod_aux(int[] p,int n,int[] r){
        int q=-1;
        //System.out.println("r[n]="+r[n]);
        if(r[n]>=0) return r[n];
        if(n==0) q=0;
        else{
            for(int i=1;i<=Math.min(p.length-1,n);i++)
                q=Math.max(q,p[i]+memoized_cut_rod_aux(p,n-i,r));
        }
        r[n]=q;
        //System.out.println("q="+q);
        return q;
    }

    public static void main(String[] args) {
        int[] p={0,1,5,8,9,10,17,17,20,24,30};
//        for(int n=0;n<41;n++)
//            System.out.println("r"+n+"="+cut_rod(p,n));
        for(int n=0;n<41;n++)
            System.out.println("r"+n+"="+memoized_cut_rod(p,n));
    }
}

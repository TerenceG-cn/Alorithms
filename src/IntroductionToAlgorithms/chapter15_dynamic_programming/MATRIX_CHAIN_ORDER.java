package IntroductionToAlgorithms.chapter15_dynamic_programming;
/**�������˷�*/
public class MATRIX_CHAIN_ORDER {
    public static void matrix_chain_order(int[] p){
        int n=p.length-1;
        int[][] m=new int[n][n];
        int[][] s=new int[n][n];

        for(int i=0;i<n;i++)
            m[i][i]=0;
        for(int l=1;l<n;l++)//l�Ǿ��󳤶�
            for(int i=0;i<n-l+1;i++) {
                int j = i + l - 1;
                m[i][j]=Integer.MAX_VALUE;
                for(int k=i;i<j;k++) {
                    int q = m[i][k] + m[k + 1][j] + p[i - 1] * p[k] * p[j];
                    if(q<m[i][j]){
                        m[i][j]=q;
                        s[i][j]=k;
                    }
                }
        }
    }
}

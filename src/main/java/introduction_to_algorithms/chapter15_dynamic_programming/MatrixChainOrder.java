package introduction_to_algorithms.chapter15_dynamic_programming;

import java.util.Arrays;

/**矩阵链乘法*/
public class MatrixChainOrder{
    public static void matrixChainOrder(int[] p) {
        int n = p.length - 1;
        int j=0;
        int[][] m = new int[n + 1][n + 1];
        int[][] s = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            m[i][i] = 0;
        }
        //l是矩阵长度 todo
        for (int l = 2; l <= n; l++) {
            for (int i = 1; i <= n - l + 1; i++) {
                j = i + l - 1;
                m[i][j] = Integer.MAX_VALUE;
                System.out.println(i+" "+j);
                for (int k = i; k <= j-1; k++) {
                    System.out.println(i+" "+j);
                    int q = m[i][k] + m[k + 1][j] + p[i-1] * p[k] * p[j];
                    if (q < m[i][j]) {
                        System.out.println(i+" "+j);
                        m[i][j] = q;
                        s[i][j] = k;
                    }
                    //printM(m);
                }
            }
        }
        printM(m);printM(s);

    }

    private static void printM(int[][] m){
        for(int i=0;i<m.length;i++)
        {System.out.println(Arrays.toString(m[i]));}

//        System.out.println(Arrays.deepToString(m));
//        System.out.println("------------------------");
    }
}


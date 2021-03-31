package introduction_to_algorithms.chapter15_dynamic_programming;
/**矩阵链乘法*/
public class MatrixChainOrder{
    public static void matrixChainOrder(int[] p) {
        int n = p.length - 1;
        int[][] m = new int[n][n];
        int[][] s = new int[n][n];

        for (int i = 0; i < n; i++) {
            m[i][i] = 0;
        }
        //l是矩阵长度
        for (int l = 1; l < n; l++) {
            for (int i = 0; i < n - l + 1; i++) {
                int j = i + l - 1;
                m[i][j] = Integer.MAX_VALUE;
                for (int k = i; i < j; k++) {
                    int q = m[i][k] + m[k + 1][j] + p[i - 1] * p[k] * p[j];
                    if (q < m[i][j]) {
                        m[i][j] = q;
                        s[i][j] = k;
                    }
                }
            }
        }
    }
}


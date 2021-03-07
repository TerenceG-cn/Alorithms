package IntroductionToAlgorithms.chapter15_dynamic_programming;

public class Fibonaccisequence {

    /**
     * 15.1-5 O(n) 自底向上
     * @param n
     * @return 求第n个斐波那契数
     */
    public static int getFibo(int n){
        int r[] = new int[n + 1];
        r[0]=0;r[1]=1;
        for (int j = 2; j <= n; j++) {
            r[j]=r[j-1]+r[j-2];
        }
        return r[n];
    }

    public static void main(String[] args) {
        System.out.println(getFibo(10));
    }
}

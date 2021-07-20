package introduction_to_algorithms.chapter15_dynamic_programming;

/**
 *@classname         FibonacciSequence
 *@description       쳲�������
 *@author            TerenceG
 *@lastmodifydate    2021/3/23
 */
public class FibonacciSequence {

    /**
     * @author          TerenceG
     * @lastmodifydate  2021/3/23
     * @description:    15.1-5 O(n) �Ե�����,���n��쳲�������
     * @Param:       * @param n
     * @Return:     int
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

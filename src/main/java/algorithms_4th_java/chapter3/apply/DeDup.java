package algorithms_4th_java.chapter3.apply;

import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * DeDup������������SET����HashSET��ȥ���������е��ظ���ĳ���
 */
public class DeDup {
    private DeDup() {}

    public static void main(String[] args) {
        SET<String> set = new SET<String>();
        while (!StdIn.isEmpty()) {
            String key = StdIn.readString();
            if (!set.contains(key)) {
                set.add(key);
                StdOut.println(key);
            }
        }
    }
}

package personal.algorithms_4th_java.chapter1;

import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class BruteForceSearch {
	public static int rank(int key,int []a) {
		for(int i=0;i<a.length;i++)
			if(a[i]==key) return i;
		return -1;
	}
	public static void main(String[] args) {
		@SuppressWarnings("deprecation")
		int[] whitelist=In.readInts("./data/largeW.txt");
		Arrays.sort(whitelist);
		System.out.println(whitelist.length);
		while(!StdIn.isEmpty()) {//读取键值，如果不存在于白名单中则将其打印
			int key=StdIn.readInt();
//			System.out.println("key="+key);
//			System.out.println(rank(key, whitelist));
			if(rank(key, whitelist)<0)
				StdOut.println(key);
		}
	}
}

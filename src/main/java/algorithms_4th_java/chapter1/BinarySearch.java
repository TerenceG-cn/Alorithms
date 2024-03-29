package algorithms_4th_java.chapter1;
import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
public class BinarySearch {

	public static int rank(int key,int[] a) {//数组必须是有序的
		int lo=0;
		int hi=a.length-1;
		while(lo<=hi) {//被查找的键要么不存在，要么必然存在于a[lo...hi]之中
			int mid=lo+(hi-lo)/2;
			//System.out.println("mid="+mid);
			if(key<a[mid]) hi=mid-1;
			else if(key>a[mid]) lo=mid+1;
			else return mid;
		}
		return -1;
	}
	
	public static void main(String[] args) {
		@SuppressWarnings("deprecation")
		int[] whitelist=In.readInts("./data/largeW.txt");
		Arrays.sort(whitelist);
		while(!StdIn.isEmpty()) {//读取键值，如果不存在于白名单中则将其打印
			int key=StdIn.readInt();
//			System.out.println("key="+key);
//			System.out.println(rank(key, whitelist));
			if(rank(key, whitelist)<0)
				StdOut.println(key);
		}
	}

}

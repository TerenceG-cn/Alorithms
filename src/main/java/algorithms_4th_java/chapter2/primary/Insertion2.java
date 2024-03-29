package algorithms_4th_java.chapter2.primary;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Insertion2 {
	public static void sort(Comparable[] a) {
		int N=a.length;
//		for(int n=N-1;n>0;n--)//For 8 random Double:Insertion is   0.5 times faster than Insertion2
//			if(less(a[n], a[0]))
//				exch(a, n, 0);
        for(int i = N - 1; i > 0; i--) {//For 8 random Double:Insertion is   0.5 times faster than Insertion2
            if(less(a[i], a[i-1])) {
                exch(a, i, i-1);
            }
        }
		//show(a);
		for(int i=2;i<N;i++) {
			for(int j=i;less(a[j],a[j-1]);j--) {
				exch(a,j,j-1);
			}	
		}
	}
	private static boolean less(Comparable v,Comparable w) {
		return v.compareTo(w)<0;
	}
	private static void exch(Comparable[] a,int i,int j) {
		Comparable t=a[i]; a[i]=a[j]; a[j]=t;
	}
	private static void show(Comparable[] a) {
		for(int i=0;i<a.length;i++)
			StdOut.print(a[i]+" ");
		StdOut.println();
	}
	public static boolean isSorted(Comparable[] a) {
		for(int i=1;i<a.length;i++)
			if(less(a[i], a[i-1])) return false;
		return true;
	}
	public static void main(String[] args) {
		@SuppressWarnings("deprecation")
		String[] a=In.readStrings();
		//System.out.println("NMSL");
		sort(a);
		assert isSorted(a);
		show(a);
	}

}

package personal.chapter2.mergesort;

import edu.princeton.cs.algs4.StdOut;

/*
 * author Tce
 * 
 * Merge 原地归并的一种实现，分治，自顶向下以66
 */
public class Merge {
	
	private static Comparable[] aux;

	public static void sort(Comparable[] a) {
		aux = new Comparable[a.length];
		sort(a, 0, a.length - 1);
	}

	private static void sort(Comparable[] a, int lo, int hi) {
		// TODO Auto-generated method stub
		if (hi <= lo)
			return;
		int mid = lo + (hi - lo) / 2;
		sort(a, lo, mid);
		sort(a, mid + 1, hi);
		merge(a, lo, mid, hi);
	}

	public static void merge(Comparable[] a, int lo, int mid, int hi) {
		int i = lo, j = mid + 1;
		for (int k = lo; k <= hi; k++)
			aux[k] = a[k];
		for (int k = lo; k <= hi; k++)
			if (i > mid)
				a[k] = aux[j++];
			else if (j > hi)
				a[k] = aux[i++];
			else if (less(aux[j], aux[i]))
				a[k] = aux[j++];
			else
				a[k] = aux[i++];
	}

	private static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}

	private static void show(Comparable[] a) {
		for (int i = 0; i < a.length; i++)
			StdOut.print(a[i] + " ");
		StdOut.println();
	}

	public static void main(String[] args) {
		System.err.println("nmbdhs");
		Integer a[] = { 1, 3, 4, 6, 8, 2, 9, 5, 34, 7, 4, 3, 26, 5, 4, 8, 45, 27, 36, 0, 85, 19 };
		sort(a);
		show(a);
	}
}

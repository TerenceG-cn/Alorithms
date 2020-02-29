package personal.algorithms_4th_java.chapter2.mergesort;


import edu.princeton.cs.algs4.StdOut;

public class MergeBU {
	public static Comparable[] aux;

	public static void sort(Comparable[] a) {
		int N = a.length;
		aux = new Comparable[N];

		for (int sz = 1; sz < N; sz *= 2)
			for (int lo = 0; lo < N - sz; lo+=sz * 2) {// lo=0
				merge(a, lo, lo + sz - 1, Math.min(lo + 2 * sz - 1, N - 1));
				//show(a);
			}
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
		//Integer a[] = { 1, 3, 4, 6, 8, 2, 9, 5, 34, 7, 4, 3, 26, 5, 4, 8, 45, 27, 36, 0, 85, 19 };
		Character a[]= {'A','E'};
		sort(a);
		show(a);
	}
}

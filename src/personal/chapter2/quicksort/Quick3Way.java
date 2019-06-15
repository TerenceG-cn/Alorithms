package personal.chapter2.quicksort;

import personal.chapter2.primary.Example;

/*
 * 三切分的快速排序
 */
public class Quick3Way {
	public static void sort(Comparable[] a, int lo, int hi) {
		if (hi <= lo)
			return;
		int lt = lo, i = lo + 1, gt = hi;
		Comparable v = a[lo];
		while (i <= gt) {
			int cmp = a[i].compareTo(v);
			if (cmp > 0)
				Example.exch(a, i, gt--);
			else if (cmp < 0)
				Example.exch(a, i++, lt++);
			else
				i++;
		}
		sort(a, lo, lt - 1);
		sort(a, gt + 1, hi);
	}

	public static void main(String[] args) {
		Integer a[] = { 1, 3, 4, 6, 8, 2, 9, 5, 34, 7, 4, 3, 26, 5, 4, 8, 45, 27, 36, 0, 85, 19 };
		Quick3Way.sort(a, 0, a.length - 1);
		Example.show(a);

	}
}

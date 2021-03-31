package algorithms_4th_java.chapter2.quicksort;

import algorithms_4th_java.chapter2.primary.Example;

/*
 * ����
 * 
 * �Ľ�Ч�ʵĲ��ԣ�
 * 1.С������ò��룬���ڴ�����ݹ������㷨��������ô��
 */
public class Quick {
	public void sort(Comparable[] a) {
		sort(a, 0, a.length - 1);
	}

	private void sort(Comparable[] a, int lo, int hi) {
		if (lo >= hi) return;//if(hi<=lo+M) ��Ӧ�Ľ�����1��MΪ1������ ��5~15
		int j = partition(a, lo, hi);
		sort(a, lo, j - 1);
		sort(a, j + 1, hi);
	}

	private int partition(Comparable[] a, int lo, int hi) {
		int i = lo;
		int j = hi + 1;
		while (true) {
			while (Example.less(a[++i], a[lo]))
				if (i >= hi)
					break;
			while (Example.less(a[lo], a[--j]))
				if (j <= lo-1)
					break;
			if (i >= j)
				break;
			Example.exch(a, i, j);
		}
		return j;
	}

	public static void main(String[] args) {
		Integer a[] = { 1, 3, 4, 6, 8, 2, 9, 5, 34, 7, 4, 3, 26, 5, 4, 8, 45, 27, 36,0, 85, 19 };
		new Quick().sort(a);
		Example.show(a);

	}
}

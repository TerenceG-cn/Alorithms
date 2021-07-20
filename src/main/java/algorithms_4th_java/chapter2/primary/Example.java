package algorithms_4th_java.chapter2.primary;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdOut;
//排序算法的模板

/**
 * @author Terence
  * 方法名less，判断对象v和w的大小，返回值true=>v<w;false=>v>=w。
  * 方法名exch(a,i,j)，对数组a,a[i]和a[j]的值互换
 */
public class Example {

	public static void sort(Comparable[] a) {
		int N=a.length;
		for(int i=1;i<N;i++) {
			for(int j=i;j>0&&less(a[j],a[j-1]);j--) {
				exch(a,j,j-1);
//				System.out.println("可能的交换结束");
			}
		}
	}
	public static  boolean less(Comparable v,Comparable w) {
		return v.compareTo(w)<0;
	}
	public static void exch(Comparable[] a,int i,int j) {
		Comparable t=a[i]; a[i]=a[j]; a[j]=t;
	}
	public static void show(Comparable[] a) {
		for(int i=0;i<a.length;i++)
			StdOut.print(a[i]+" ");
		StdOut.println();
	}
	public static boolean isSorted(Comparable[] a) {
		for(int i=1;i<a.length;i++)
			if(less(a[i], a[i-1])) return false;
		return true;
	}
	
	//2.1.16:在check中将原数组复制一份，然后用sort对原数组进行排序，
	//然后用Array.sort对数组复本排序，然后对比两个排序后的数组元素，
	//全部对应元素相同时返回true，遇到不相同时返回false。
	public static boolean check(Comparable[] a) {
		Comparable[] copy=new Comparable[a.length];
		for(int i=0;i<a.length;i++)
			copy[i]=a[i];
		sort(a);
		Arrays.sort(copy); 
		for(int j=0;j<a.length;j++)
			if(a[j]!=copy[j])
				return false; 
		return true;
	}
	
	public static void main(String[] args) {
//		@SuppressWarnings("deprecation")
//		String[] a=In.readStrings();
//		sort(a);
//		assert isSorted(a);
//		show(a);
		Comparable[] a= {11,2,4,6,1,3,7};
		if(check(a))
			System.out.println("true!");
		else {
			System.out.println("false!");
		}
	}
}

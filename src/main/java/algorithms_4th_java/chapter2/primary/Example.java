package algorithms_4th_java.chapter2.primary;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdOut;
//�����㷨��ģ��

/**
 * @author Terence
  * ������less���ж϶���v��w�Ĵ�С������ֵtrue=>v<w;false=>v>=w��
  * ������exch(a,i,j)��������a,a[i]��a[j]��ֵ����
 */
public class Example {

	public static void sort(Comparable[] a) {
		int N=a.length;
		for(int i=1;i<N;i++) {
			for(int j=i;j>0&&less(a[j],a[j-1]);j--) {
				exch(a,j,j-1);
//				System.out.println("���ܵĽ�������");
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
	
	//2.1.16:��check�н�ԭ���鸴��һ�ݣ�Ȼ����sort��ԭ�����������
	//Ȼ����Array.sort�����鸴������Ȼ��Ա���������������Ԫ�أ�
	//ȫ����ӦԪ����ͬʱ����true����������ͬʱ����false��
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

package algorithms_4th_java.chapter2.primary;
import java.util.ArrayList;
import java.util.List;

//希尔排序 数组大小按3的幂次递增
import edu.princeton.cs.algs4.StdOut;

public class Shell {
	private static int count=0;//记录比较次数
	public static void sort(Comparable[] a) {
		//将a升序排列
		int N=a.length;
		int h=1;
		while(h<N/3) 
			h=3*h+1;
		while(h>=1) {
			//将数组变为h有序
			for(int i=h;i<N;i++) {
				//将a[i]插入到a[i-1]，a[i-2*h],a[i-3*h]...之中
				for(int j=i;j>=h&&less(a[j],a[j-h]);j-=h)
						exch(a,j,j-h);
			}
			h=h/3;
		}
	}
	
	public static void sort2(Comparable[] a) {
		//将递增数列h存储在数组里
		int N=a.length;
		List<Integer> hArray=new ArrayList<>();
		int h=1;
		while(h<N/3) { 
			hArray.add(h);
			h=3*h+1;
		}
		hArray.add(h);
		for(int k=hArray.size()-1;k>=0;k--) {
			h=hArray.get(k);
			for(int i=h;i<N;i++) {
				for(int j=i;j>=h&&less(a[j], a[j-h]);j-=h)
					exch(a, j, j-h);
			}
		}
	}
	
	public static void sort3(Comparable[] a) {
		//sort2改
		int N=a.length;
		List<Integer> hArray=new ArrayList<>();
		int h=1;
		while(h<N/3) { 
			hArray.add(h);
			h=3*h+1;
		}
		hArray.add(h);
		for(int k=hArray.size()-1;k>=0;k--) {
			h=hArray.get(k);
			for(int i=h;i<N;i++) {
				for(int j=i;j>=h&&less2(a[j], a[j-h]);j-=h)
					exch(a, j, j-h);
			}
			System.out.printf("递增序列%d的比较次数为：%d,比较次数和数组的比值为%f\n",h,count,(float)count/h);
		}
		
	}
	
	private static boolean less(Comparable v,Comparable w) {
		return v.compareTo(w)<0;
	}
	private static boolean less2(Comparable v,Comparable w) {
		count++;
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
		/*@SuppressWarnings("deprecation")
		String[] a=In.readStrings();*/
		System.out.println("nmsl");
		Integer  a[]= {1,3,4,6,8,2,9,5,34,7,4,3,26,5,4,8,45,27,36,0,85,19};
		sort3(a);
		//assert isSorted(a);
		show(a);
	}
}

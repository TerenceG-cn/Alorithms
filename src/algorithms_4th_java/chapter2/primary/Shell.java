package algorithms_4th_java.chapter2.primary;
import java.util.ArrayList;
import java.util.List;

//ϣ������ �����С��3���ݴε���
import edu.princeton.cs.algs4.StdOut;

public class Shell {
	private static int count=0;//��¼�Ƚϴ���
	public static void sort(Comparable[] a) {
		//��a��������
		int N=a.length;
		int h=1;
		while(h<N/3) 
			h=3*h+1;
		while(h>=1) {
			//�������Ϊh����
			for(int i=h;i<N;i++) {
				//��a[i]���뵽a[i-1]��a[i-2*h],a[i-3*h]...֮��
				for(int j=i;j>=h&&less(a[j],a[j-h]);j-=h)
						exch(a,j,j-h);
			}
			h=h/3;
		}
	}
	
	public static void sort2(Comparable[] a) {
		//����������h�洢��������
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
		//sort2��
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
			System.out.printf("��������%d�ıȽϴ���Ϊ��%d,�Ƚϴ���������ı�ֵΪ%f\n",h,count,(float)count/h);
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

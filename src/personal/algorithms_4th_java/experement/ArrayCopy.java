package personal.algorithms_4th_java.experement;

public class ArrayCopy {
	public static void main(String[] args) {
		int a[]= {3,2,1};
		int b[]=a;
		b[0]=23;
		for(int i=0;i<a.length;i++) {
			System.out.println(a[i]+" "+b[i]);
		}
	}
}

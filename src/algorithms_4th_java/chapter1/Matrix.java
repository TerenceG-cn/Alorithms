package algorithms_4th_java.chapter1;

public class Matrix {
	
	public static double dot(double[] x,double[] y) {//向量点乘
		if(x.length!=y.length) {
			System.err.println("向量x和向量y长度必须一样！");
			return 0.0;
		}
		double result=0.0;
		for(int i=0;i<x.length;i++) {
			result+=x[i]*y[i];
		}
		return result;
	}
	public static double[][] transpose(double[][] a){//矩阵的转置
		int m=a.length;
		int n=a[0].length;
		double[][] result=new double[n][m];
		for(int i=0;i<n;i++)
			for(int j=0;j<m;j++)
				result[i][j]=a[j][i];
		return result;
	}
	
	public static double[][] mult(double[][] a,double[][] b){//矩阵和矩阵之积
		if(a[0].length!=b.length) {
			System.err.println("矩阵A的列数必须等于矩阵B的行数！");
			return null;
		}
		int p=a[0].length;
		int m=a.length;
		int n=b[0].length;
		double[][] b1=transpose(b);
		double c[][]=new double[m][n];
		for(int i=0;i<m;i++)
			for(int j=0;j<n;j++) {
				c[i][j]=dot(a[i], b1[j]);
			}
		return c;
	}
	public static void main(String[] args) {
//		double[] x= {1.0,1.0,1.0};
//		double[] y= {2.0,3.0,4.0,5.0};
//		System.out.println(dot(x,y));
		
//		double[][] a= {{1,2},{2,3},{3,4}};
//		double[][] aT=transpose(a);
//		for(int i=0;i<a.length;i++) {
//			for(int j=0;j<a[0].length;j++) {
//				System.out.print(a[i][j]+" ");
//			}
//			System.out.println();
//		}
//		for(int i=0;i<aT.length;i++) {
//			for(int j=0;j<aT[0].length;j++) {
//				System.out.print(aT[i][j]+" ");
//			}
//			System.out.println();
//		}
		double[][] a= {{1},{2}};
		double[][] b= {{3,2}};
		double[][] c=mult(a, b);
		for(int i=0;i<c.length;i++) {
			for(int j=0;j<c[0].length;j++) {
				System.out.print(c[i][j]+" ");
			}
			System.out.println();
		}
	}

}

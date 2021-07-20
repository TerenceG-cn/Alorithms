package algorithms_4th_java.chapter1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

//	public static void shuffle(double[] a) {
//		int N=a.length;
//		for(int i=0;i<N;i++) {
//			//将a[i]与a[i...N-1]中任意一个元素交换
//			int r = i+StdRandom.uniform(N-i);
//			double temp=a[i];
//			a[i]=a[r];
//			a[r]=temp;
//		}
//	}
public class ShuffleTest {

	public static class RandomArray {
		private int M = 0;
		private int[] a = null;

		public int[] getA() {
			return a;
		}

		public void setA(int[] a) {
			this.a = a;
		}

		public RandomArray(int M) {// 每次打乱之前将数组重新初始化
			this.M = M;
			this.a = new int[M];
			for (int i = 0; i < M; i++) {
				a[i] = i;
			}
		}
	}

	public static class RandomChecker {
		private double[][] matrix = null;

		public double[][] getMatrix() {
			return matrix;
		}

		private int M = 0;
		private int N = 0;

		public RandomChecker(int M, int N) {
			this.M = M;
			this.N = N;
			matrix = new double[M][];
			for (int i = 0; i < matrix.length; i++) {
				matrix[i] = new double[M];
			}
		}

		public void execute() {
			for (int n = 0; n < this.N; n++) {
				RandomArray c = new RandomArray(M);
				int[] a = c.getA();
				StdRandom.shuffle(a); // between i到N-i
				for (int k = 0; k < a.length; k++) {
					int i = a[k];
					int j = k;
					matrix[i][j] += 1;
				}

			}
		}

		public void print() {
			for (int i = 0; i < M; i++) {
				for (int j = 0; j < M; j++) {
					StdOut.print(matrix[i][j] + "\t");
				}
				StdOut.print("\n");
			}
		}
	}

	public static class BadRandomChecker {
		private double[][] matrix = null;

		public double[][] getMatrix() {
			return matrix;
		}

		private int M = 0;
		private int N = 0;

		public BadRandomChecker(int M, int N) {
			this.M = M;
			this.N = N;
			matrix = new double[M][];
			for (int i = 0; i < matrix.length; i++) {
				matrix[i] = new double[M];
			}
		}

		public void execute() {
			for (int n = 0; n < this.N; n++) {
				RandomArray c = new RandomArray(M);
				int[] a = c.getA();
				shuffle(a);
				for (int k = 0; k < a.length; k++) {
					int i = a[k];
					int j = k;
					matrix[i][j] += 1;
				}

			}
		}

		public void print() {
			for (int i = 0; i < M; i++) {
				for (int j = 0; j < M; j++) {
					StdOut.print(matrix[i][j] + "\t");
				}
				StdOut.print("\n");
			}
		}

		public static void shuffle(int[] a) {
			int N = a.length;
			for (int i = 0; i < N; i++) {
				int r = StdRandom.uniform(N - i); // between 0 and N-1
				int temp = a[i];
				a[i] = a[r];
				a[r] = temp;
			}
		}
	}

	public static void main(String[] args) {
		StdOut.println("Please input M: ");
		int M = StdIn.readInt();
		StdOut.println("Please input N: ");
		int N = StdIn.readInt();
		StdOut.println("Random Check: ");
		RandomChecker rc = new RandomChecker(M, N);
		rc.execute();
		rc.print();

		StdOut.println("Bad Random: ");
		BadRandomChecker br = new BadRandomChecker(M, N);
		br.execute();
		br.print();
	}
}
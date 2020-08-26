package personal.algorithms_4th_java.chapter2.primary;

import java.util.Random;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
//�Ƚ����������㷨��Ч��
import edu.princeton.cs.algs4.Stopwatch;

/**
 * @author gch ����Ч�ʱȽ�
 */
public class SortCompare {
	public static double time(String alg, Double[] a) {
		Stopwatch timer = new Stopwatch();
		if (alg.equals("Insertion"))
			Insertion.sort(a);
		if (alg.equals("Insertion2"))
			Insertion2.sort(a);
		if (alg.equals("Selection"))
			Selection.sort(a);
		if (alg.equals("Shell"))
			// Shell.sort(a);
			Shell.sort3(a);
		// ...
		return timer.elapsedTime();
	}

	public static double timeRandomInput(String alg, int N, int T) {
		// ʹ���㷨alg��T������ΪN����������
		double total = 0.0;
		Double[] a = new Double[N];
		for (int t = 0; t < T; t++) {
			for (int i = 0; i < N; i++)
				a[i] = StdRandom.uniform();
			total += time(alg, a);
		}
		return total;
	}

	public static double timeRandomInput2(String alg, int N, int T) {
		// timeRandomInput�ģ�ֻ֧��Ԫ����3��ֵ��
		double total = 0.0;
		Double[] a = new Double[N];
		Double[] enumD = { 0.0, -1.0, 1.0 };
		Random random = new Random();
		for (int t = 0; t < T; t++) {
			for (int i = 0; i < N; i++)
				a[i] = enumD[random.nextInt(3)];
			/*
			 * StdOut.printf("["); for (int n = 0; n < N; n++) { StdOut.printf("%.1f,",
			 * a[n]); } StdOut.printf("]\n");
			 */
			total += time(alg, a);
		}
		return total;
	}

	/**
	 * ���ߣ�����_���� ��Դ��CSDN
	 * ԭ�ģ�https://blog.csdn.net/qq_18433441/article/details/77963324
	 * ��Ȩ����������Ϊ����ԭ�����£�ת���븽�ϲ������ӣ�
	 */
	public static double timeEqualInput(String alg, int N, int T) {
		// ʹ���㷨alg, ��T������ΪN����ֵȫ����ȵ���������������ʱ��
		double total = 0.0;
		Double[] a = new Double[N];
		for (int i = 0; i < N; i++) {
			// ��������Ԫ�ض���ȣ���ʹ�����Ҳ��ȣ�����ֻ��ֵһ��
			a[i] = 1.0;
		}
		for (int t = 0; t < T; t++) {
			total += time(alg, a);
		}
		return total;
	}

	/**
	 * ���ߣ�����_���� ��Դ��CSDN
	 * ԭ�ģ�https://blog.csdn.net/qq_18433441/article/details/77963324
	 * ��Ȩ����������Ϊ����ԭ�����£�ת���븽�ϲ������ӣ�
	 */
	public static double timeInverseInput(String alg, int N, int T) {
		// ʹ���㷨alg����T������ΪN��������������������ʱ��
		double total = 0.0;
		Double[] a = new Double[N];
		for (int t = 0; t < T; t++) {
			for (int i = 0; i < N; i++) {
				a[i] = 1.0 * (N - i);
			}
			total += time(alg, a);
		}
		return total;
	}

	public static void main(String[] args) {
		/**
		 * �Ƚ�ѡ������Ͳ��������Ч��
		*/
		System.out.println("tbl");
		String alg1 = args[0];
		String alg2 = args[1];
		int N = Integer.parseInt(args[2]);
		int T = Integer.parseInt(args[3]);
		double t1 = timeRandomInput(alg1, N, T);
		double t2 = timeRandomInput(alg2, N, T);
		StdOut.printf("For %d random Double\n    %s is", N, alg1);
		StdOut.printf("   %.1f times faster than %s\n", t2 / t1, alg2);

		/**
		 * ���ߣ�����_���� ��Դ��CSDN
		 * ԭ�ģ�https://blog.csdn.net/qq_18433441/article/details/77963324
		 * ��Ȩ����������Ϊ����ԭ�����£�ת���븽�ϲ������ӣ� String alg1 = args[0]; // String alg1 =
		 * StdIn.readString(); String alg2 = args[1]; // String alg2 =
		 * StdIn.readString(); int N = StdIn.readInt(); int T = StdIn.readInt(); //
		 * ����Ԫ�����������£������������ѡ������ double t1 = timeRandomInput(alg1, N, T); double t2 =
		 * timeRandomInput(alg2, N, T); StdOut.printf("for %d random Doules\n %s is", N,
		 * alg1); StdOut.printf(" Insertion time:%f,Selection time:%f\n", t1, t2);
		 * StdOut.printf(" %.1f times faster than %s\n", t2 / t1, alg2);
		 * 
		 * // ����Ԫ�ض���ȵ�����£������������ѡ������ double t3 = timeEqualInput(alg1, N, T); double t4 =
		 * timeEqualInput(alg2, N, T); StdOut.printf("for %d equal Doules\n %s is", N,
		 * alg1); StdOut.printf(" Insertion time:%f,Selection time:%f\n", t3, t4);
		 * StdOut.printf(" %.1f times faster than %s\n", t4 / t3, alg2); //
		 * Ϊʲô���е���������ͬʱ������������죿 // // ����Ԫ�ض���ͬʱ // // �������򣬱Ƚϴ���N-1����������0 // //
		 * ѡ�����򣬱Ƚϴ���N��N-1��/2����������0
		 * 
		 * // ����Ԫ�����������£�������������ѡ������ double t5 = timeInverseInput(alg1, N, T); double t6
		 * = timeInverseInput(alg2, N, T); StdOut.printf("for %d inverse Doules\n %s
		 * is", N, alg1); StdOut.printf(" Insertion time:%f,Selection time:%f\n", t5,
		 * t6); StdOut.printf(" %.1f times faster than %s\n", t6 / t5, alg2); //
		 * Ϊʲô�����������飬ѡ��������죿 // // ��������ʱ // //
		 * �������������������N��N-1��/2�ԣ����Խ���������N��N-1��/2���Ƚϴ������ڵ��ڽ���������С�ڵ��� // // ��������+N-1�� // //
		 * ѡ�����򣬲��������������ģ��Ƚϴ�������N��N-1��/2����ʱ��������ΪN-1��
		 * 
		 */

		/*
		 * //��ϰ2.1.8 ����Ԫ��ֻ����������ֵ��ʹ�ò�������������һ��������������ʱ�������ԵĻ�ʽƽ������ģ����ǽ�������֮��ģ� while(true)
		 * { System.out.printf("�����������СN���������T:\n"); int N = StdIn.readInt(); int T =
		 * StdIn.readInt(); double t7 = timeRandomInput2("Insertion", N, T);
		 * StdOut.printf("Insertion time:%f\n\n", t7); }
		 */
		// ��ϰ2.1.12
//		while (true) {
//			System.out.printf("�����������СN���������T:\n");
//			int N = StdIn.readInt();
//			int T = StdIn.readInt();
//			double t8 = timeRandomInput("Shell", N, T);
//			StdOut.printf("Shell time:%f\n\n", t8);
//		}
	}
}

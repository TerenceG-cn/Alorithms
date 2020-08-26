package personal.algorithms_4th_java.chapter2.primary;

import java.util.Random;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
//比较两种排序算法的效率
import edu.princeton.cs.algs4.Stopwatch;

/**
 * @author gch 排序效率比较
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
		// 使用算法alg将T个长度为N的数组排序
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
		// timeRandomInput改，只支持元素有3种值。
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
	 * 作者：子衿_青青 来源：CSDN
	 * 原文：https://blog.csdn.net/qq_18433441/article/details/77963324
	 * 版权声明：本文为博主原创文章，转载请附上博文链接！
	 */
	public static double timeEqualInput(String alg, int N, int T) {
		// 使用算法alg, 将T个长度为N的数值全部相等的数组排序，所花的时间
		double total = 0.0;
		Double[] a = new Double[N];
		for (int i = 0; i < N; i++) {
			// 由于数组元素都相等，即使排序后，也相等，所以只赋值一次
			a[i] = 1.0;
		}
		for (int t = 0; t < T; t++) {
			total += time(alg, a);
		}
		return total;
	}

	/**
	 * 作者：子衿_青青 来源：CSDN
	 * 原文：https://blog.csdn.net/qq_18433441/article/details/77963324
	 * 版权声明：本文为博主原创文章，转载请附上博文链接！
	 */
	public static double timeInverseInput(String alg, int N, int T) {
		// 使用算法alg，将T个长度为N的逆序数组排序，所花的时间
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
		 * 比较选择排序和插入排序的效率
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
		 * 作者：子衿_青青 来源：CSDN
		 * 原文：https://blog.csdn.net/qq_18433441/article/details/77963324
		 * 版权声明：本文为博主原创文章，转载请附上博文链接！ String alg1 = args[0]; // String alg1 =
		 * StdIn.readString(); String alg2 = args[1]; // String alg2 =
		 * StdIn.readString(); int N = StdIn.readInt(); int T = StdIn.readInt(); //
		 * 数组元素随机的情况下，插入排序快于选择排序 double t1 = timeRandomInput(alg1, N, T); double t2 =
		 * timeRandomInput(alg2, N, T); StdOut.printf("for %d random Doules\n %s is", N,
		 * alg1); StdOut.printf(" Insertion time:%f,Selection time:%f\n", t1, t2);
		 * StdOut.printf(" %.1f times faster than %s\n", t2 / t1, alg2);
		 * 
		 * // 数组元素都相等的情况下，插入排序快于选择排序 double t3 = timeEqualInput(alg1, N, T); double t4 =
		 * timeEqualInput(alg2, N, T); StdOut.printf("for %d equal Doules\n %s is", N,
		 * alg1); StdOut.printf(" Insertion time:%f,Selection time:%f\n", t3, t4);
		 * StdOut.printf(" %.1f times faster than %s\n", t4 / t3, alg2); //
		 * 为什么所有的主键都相同时，插入排序更快？ // // 数组元素都相同时 // // 插入排序，比较次数N-1，交换次数0 // //
		 * 选择排序，比较次数N（N-1）/2，交换次数0
		 * 
		 * // 数组元素逆序的情况下，插入排序慢于选择排序 double t5 = timeInverseInput(alg1, N, T); double t6
		 * = timeInverseInput(alg2, N, T); StdOut.printf("for %d inverse Doules\n %s
		 * is", N, alg1); StdOut.printf(" Insertion time:%f,Selection time:%f\n", t5,
		 * t6); StdOut.printf(" %.1f times faster than %s\n", t6 / t5, alg2); //
		 * 为什么对于逆序数组，选择排序更快？ // // 数组逆序时 // //
		 * 插入排序，由于逆序对有N（N-1）/2对，所以交换次数是N（N-1）/2。比较次数大于等于交换次数，小于等于 // // 交换次数+N-1。 // //
		 * 选择排序，不管输入是怎样的，比较次数都是N（N-1）/2。此时交换次数为N-1。
		 * 
		 */

		/*
		 * //练习2.1.8 假设元素只可能有三种值，使用插入排序处理这样一个随机数组的运行时间是线性的花式平方级别的？或是介于两者之间的？ while(true)
		 * { System.out.printf("请输入数组大小N和数组个数T:\n"); int N = StdIn.readInt(); int T =
		 * StdIn.readInt(); double t7 = timeRandomInput2("Insertion", N, T);
		 * StdOut.printf("Insertion time:%f\n\n", t7); }
		 */
		// 练习2.1.12
//		while (true) {
//			System.out.printf("请输入数组大小N和数组个数T:\n");
//			int N = StdIn.readInt();
//			int T = StdIn.readInt();
//			double t8 = timeRandomInput("Shell", N, T);
//			StdOut.printf("Shell time:%f\n\n", t8);
//		}
	}
}

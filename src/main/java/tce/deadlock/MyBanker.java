package tce.deadlock;

/**
 * 银行家算法
 * 
 * @author 10352
 *
 */
public class MyBanker {
	private int rn;// 资源数
	private int pn;// 进程数
	private int available[];// 可利用资源
	private int max[][];// 最大需求矩阵nxm
	private int allocation[][];// 分配矩阵
	private int need[][];// 需求矩阵

	private int request[][];// 进程pi的请求向量
	private int[] work;

	public MyBanker(int r, int p, int[] available, int[][] max, int[][] allocation) {
		this.rn = r;
		this.pn = p;
		this.available = available.clone();
		this.max = max.clone();
		this.allocation = allocation.clone();
		this.need = new int[pn][rn];
		this.request = new int[pn][rn];
		for (int i = 0; i < pn; i++)
			for (int j = 0; j < rn; j++) {
				this.need[i][j] = max[i][j] - allocation[i][j];
			}
		this.work = new int[pn];
	}

	/**
	 * 打印当前状态
	 */
	public void printState() {
		System.out.print("当前系统各类资源剩余：");
		for (int i = 0; i < rn; i++)
			System.out.print("\t" + available[i]);
		System.out.println();
		System.out.println("当前系统资源情况：\nPID\t\tMAX\t\tAllocation\tNeed");
		for (int i = 0; i < pn; i++) {
			System.out.print("P" + i + "\t\t");
			for (int j = 0; j < rn; j++)
				System.out.print(max[i][j] + " ");
			System.out.print("\t\t");
			for (int j = 0; j < rn; j++)
				System.out.print(allocation[i][j] + " ");
			System.out.print("\t\t");
			for (int j = 0; j < rn; j++)
				System.out.print(need[i][j] + " ");
			System.out.print("\t\t");
			System.out.println();
		}
	}

	public void setRequest(int[] rp, int pid) {
		this.request[pid] = rp;
		StringBuilder sb = new StringBuilder("进程P" + pid + "对各资源请求Request：(");
		for (int i = 0; i < rp.length; i++) {
			sb.append(rp[i] + " ,");
		}
		System.out.println(sb.deleteCharAt(sb.lastIndexOf(",")).append(")"));
		if (bankerAlorithms(pid)) {
			printState();
			System.out.println("现在进入安全算法：");
			securityAlgorithm();
		}
	}

	private boolean bankerAlorithms(int pid) {
		int[] tmp_available = available;
		int[][] tmp_allocation = allocation;
		int[][] tmp_need = need;
		for (int i = 0; i < rn; i++) {
			if (request[pid][i] > tmp_need[pid][i]) {
				System.out.println("进程P" + pid + "请求已经超出最大需求量Need.");
				return false;
			} else {
				tmp_need[pid][i] -= request[pid][i];
			}
			if (request[pid][i] > tmp_available[i]) {
				System.out.println("当前没有足够的资源可分配，进程P" + pid + "需等待。");
				return false;
			} else {
				tmp_available[i] -= request[pid][i];
			}
			tmp_allocation[pid][i] += request[pid][i];
		}
		available = tmp_available;
		need = tmp_need;
		allocation = tmp_allocation;
		return true;
	}

	private void securityAlgorithm() {
		boolean[] finish = new boolean[pn];// 初始化Finish
		int countP = 0;// 完成进程数
		int[] safe = new int[pn];// 安全序列
		int idxS = 0;// 安全序列下标
		for (int i = 0; i < rn; i++) {
			work[i] = available[i]; // work数组初始化为Available
		}
		for (int i = 0; i < rn; i++) {
			System.out.print(work[i]+" ");
		}
		System.out.println();
		for (int i = 0; i < pn; i++) { // finish数组全部初始化为false
			finish[i] = false;
		}
		while (countP < pn) {
			for (int i = 0; i < rn; i++) {
				if (finish[countP]) { // 如果进程j的finish为true，j++判断下一进程
					countP++;
					break;
				} else if (need[countP][i] > work[i]) { // 如果need不能被满足，j++判断下一进程
					countP++;
					break;
				} else if (i == rn - 1) {
					for (int a = 0; a < rn; a++) {
						work[a] += allocation[countP][a]; // 如果进程countP的need全部被work满足，更改need
					}
					for (int j = 0; j < rn; j++) {
						System.out.print(work[j]+" ");
					}
					System.out.println();
					finish[countP] = true;
					safe[idxS++] = countP;
					countP = 0; // 从最小进程再开始判断
				}
			}
		}
		for (int i = 0; i < pn; i++) {
			if (finish[i] == false) {
				System.out.println("申请后状态不安全！");
				break;
			} else if (i == pn - 1) { // 当所有进程的finish全为true，输出安全队列
				System.out.println("申请后状态安全，安全队列为：");
				for (int j = 0; j < pn; j++) {
					System.out.println(safe[j]);
				}
			}
		}

	}

	public static void main(String[] args) {
		// 进程1的需求向量
		int[] req1 = { 0, 0, 0 };
		// 可利用资源
		int available[] = { 2, 3, 3 };
		// 进程的最大需求
		int maxRequest[][] = { { 5, 5, 9 }, { 5, 3, 6 }, { 4, 0, 11 }, { 4, 2, 5 }, { 4, 2, 4 } };
		// 进程已经占有（分配）资源
		int allocation[][] = { { 2, 1, 2 }, { 4, 0, 2 }, { 4, 0, 5 }, { 2, 0, 4 }, { 3, 1, 4 } };
		MyBanker banker = new MyBanker(available.length, maxRequest.length, available, maxRequest, allocation);
		banker.printState();
		banker.setRequest(req1, 0);
	}
}

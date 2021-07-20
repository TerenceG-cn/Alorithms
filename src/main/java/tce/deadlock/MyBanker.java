package tce.deadlock;

/**
 * ���м��㷨
 * 
 * @author 10352
 *
 */
public class MyBanker {
	private int rn;// ��Դ��
	private int pn;// ������
	private int available[];// ��������Դ
	private int max[][];// ����������nxm
	private int allocation[][];// �������
	private int need[][];// �������

	private int request[][];// ����pi����������
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
	 * ��ӡ��ǰ״̬
	 */
	public void printState() {
		System.out.print("��ǰϵͳ������Դʣ�ࣺ");
		for (int i = 0; i < rn; i++)
			System.out.print("\t" + available[i]);
		System.out.println();
		System.out.println("��ǰϵͳ��Դ�����\nPID\t\tMAX\t\tAllocation\tNeed");
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
		StringBuilder sb = new StringBuilder("����P" + pid + "�Ը���Դ����Request��(");
		for (int i = 0; i < rp.length; i++) {
			sb.append(rp[i] + " ,");
		}
		System.out.println(sb.deleteCharAt(sb.lastIndexOf(",")).append(")"));
		if (bankerAlorithms(pid)) {
			printState();
			System.out.println("���ڽ��밲ȫ�㷨��");
			securityAlgorithm();
		}
	}

	private boolean bankerAlorithms(int pid) {
		int[] tmp_available = available;
		int[][] tmp_allocation = allocation;
		int[][] tmp_need = need;
		for (int i = 0; i < rn; i++) {
			if (request[pid][i] > tmp_need[pid][i]) {
				System.out.println("����P" + pid + "�����Ѿ��������������Need.");
				return false;
			} else {
				tmp_need[pid][i] -= request[pid][i];
			}
			if (request[pid][i] > tmp_available[i]) {
				System.out.println("��ǰû���㹻����Դ�ɷ��䣬����P" + pid + "��ȴ���");
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
		boolean[] finish = new boolean[pn];// ��ʼ��Finish
		int countP = 0;// ��ɽ�����
		int[] safe = new int[pn];// ��ȫ����
		int idxS = 0;// ��ȫ�����±�
		for (int i = 0; i < rn; i++) {
			work[i] = available[i]; // work�����ʼ��ΪAvailable
		}
		for (int i = 0; i < rn; i++) {
			System.out.print(work[i]+" ");
		}
		System.out.println();
		for (int i = 0; i < pn; i++) { // finish����ȫ����ʼ��Ϊfalse
			finish[i] = false;
		}
		while (countP < pn) {
			for (int i = 0; i < rn; i++) {
				if (finish[countP]) { // �������j��finishΪtrue��j++�ж���һ����
					countP++;
					break;
				} else if (need[countP][i] > work[i]) { // ���need���ܱ����㣬j++�ж���һ����
					countP++;
					break;
				} else if (i == rn - 1) {
					for (int a = 0; a < rn; a++) {
						work[a] += allocation[countP][a]; // �������countP��needȫ����work���㣬����need
					}
					for (int j = 0; j < rn; j++) {
						System.out.print(work[j]+" ");
					}
					System.out.println();
					finish[countP] = true;
					safe[idxS++] = countP;
					countP = 0; // ����С�����ٿ�ʼ�ж�
				}
			}
		}
		for (int i = 0; i < pn; i++) {
			if (finish[i] == false) {
				System.out.println("�����״̬����ȫ��");
				break;
			} else if (i == pn - 1) { // �����н��̵�finishȫΪtrue�������ȫ����
				System.out.println("�����״̬��ȫ����ȫ����Ϊ��");
				for (int j = 0; j < pn; j++) {
					System.out.println(safe[j]);
				}
			}
		}

	}

	public static void main(String[] args) {
		// ����1����������
		int[] req1 = { 0, 0, 0 };
		// ��������Դ
		int available[] = { 2, 3, 3 };
		// ���̵��������
		int maxRequest[][] = { { 5, 5, 9 }, { 5, 3, 6 }, { 4, 0, 11 }, { 4, 2, 5 }, { 4, 2, 4 } };
		// �����Ѿ�ռ�У����䣩��Դ
		int allocation[][] = { { 2, 1, 2 }, { 4, 0, 2 }, { 4, 0, 5 }, { 2, 0, 4 }, { 3, 1, 4 } };
		MyBanker banker = new MyBanker(available.length, maxRequest.length, available, maxRequest, allocation);
		banker.printState();
		banker.setRequest(req1, 0);
	}
}

package personal.algorithms_4th_java.chapter2.maxPQ;

/**
 * @author tce E-mail:
 * @version Create Time��2019��6��4�� ����11:49:08 Description����������ʵ�����ȶ���
 */
public class UnorderedArrayImplPriorityQueue<Key extends Comparable<Key>> {
	private Key[] pq = (Key[]) new Comparable[1];
	private int len = 0;

	public UnorderedArrayImplPriorityQueue() {
	}

	public UnorderedArrayImplPriorityQueue(int max) {
		pq = (Key[]) new Comparable[max];
		len = max;
	}

	public UnorderedArrayImplPriorityQueue(Key[] a) {
		pq = (Key[]) new Comparable[a.length];
		len = a.length;
		for (int i = 0; i < a.length; i++)
			pq[i] = a[i];
	}

	public boolean isEmpty() {
		return len == 0;
	}

	public int size() {
		return len;
	}

	private void resize(int max) {
		// ��ջ�ƶ���һ����СΪmax������
		Key[] tmp = (Key[]) new Comparable[max];
		for (int i = 0; i < pq.length; i++) {
			tmp[i] = pq[i];
		}
		pq = tmp;
	}

	public void insert(Key v) {
		if (len == pq.length)
			resize(2 * pq.length);
		pq[len++] = v;
	}

	public int max() {
		int tmp = 0;
		for (int i = 1; i < len; i++)
			if (pq[tmp].compareTo(pq[i]) >= 0)
				;
			else
				tmp = i;
		return tmp;
	}

	public Key delMax() {
		if(isEmpty()) {
			System.err.println("mq is NULL!");
			return null;
		}
		int i = max();
		Key tmp = (Key)pq[i];
		pq[i] = pq[--len];
		pq[len] = null;
		if (len > 0 && len == pq.length / 4)
			resize(pq.length / 2);
		return tmp;
	}
}

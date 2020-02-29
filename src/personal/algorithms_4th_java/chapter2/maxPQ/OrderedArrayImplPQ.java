package personal.algorithms_4th_java.chapter2.maxPQ;

import java.util.Comparator;

/**
* @author tce E-mail:
* @version Create Time��2019��6��14�� ����4:25:44
* Description:��������ʵ�����ȶ���
*/
public class OrderedArrayImplPQ <Key extends Comparable<Key>>{
	private Key[] pq = (Key[]) new Comparable[1];
	private int len = 0;
	
	public OrderedArrayImplPQ(Key[] a) {
		pq = (Key[]) new Comparator[a.length];
		len=a.length;
		for(int i=0;i<len-1;i++)
			for(int j=i;j>0&&less(pq[j],pq[j-1]);j--) {
				exch(pq,j,j-1);
			}
		
	}
	
	public void insert(Key v) {//��Ҫ�Ż�,��ʵֱ�Ӳ���������У��ҷ���
		if (len == pq.length)
			resize(2 * pq.length);
		pq[len++]=v;
		for(int i=0;i<len-1;i++)
			for(int j=i;j>0&&less(pq[j],pq[j-1]);j--) {
				exch(pq,j,j-1);
			}
	}
	
	public boolean isEmpty() {
		return len == 0;
	}

	public int size() {
		return len;
	}
	
	public Key max() {
		return pq[len-1];
	}
	
	public Key delMax() {
		Key key=pq[--len];
		pq[len]=null;
		if (len > 0 && len == pq.length / 4)
			resize(pq.length / 2);
		return key;
	}
	
	
	private void resize(int max) {
		// ��ջ�ƶ���һ����СΪmax������
		Key[] tmp = (Key[]) new Comparable[max];
		for (int i = 0; i < pq.length; i++) {
			tmp[i] = pq[i];
		}
		pq = tmp;
	}
	private static boolean less(Comparable v,Comparable w) {
		return v.compareTo(w)<0;
	}
	private static void exch(Comparable[] a,int i,int j) {
		Comparable t=a[i]; a[i]=a[j]; a[j]=t;
	}
	
}

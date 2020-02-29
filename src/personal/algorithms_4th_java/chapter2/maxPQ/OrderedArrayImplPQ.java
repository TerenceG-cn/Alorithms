package personal.algorithms_4th_java.chapter2.maxPQ;

import java.util.Comparator;

/**
* @author tce E-mail:
* @version Create Time：2019年6月14日 下午4:25:44
* Description:有序数组实现优先队列
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
	
	public void insert(Key v) {//需要优化,其实直接插入排序就行，我服了
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
		// 将栈移动到一个大小为max的数组
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

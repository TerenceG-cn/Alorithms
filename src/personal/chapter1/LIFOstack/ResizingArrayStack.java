package personal.chapter1.LIFOstack;

import java.awt.event.ItemEvent;
import java.util.Iterator;

/**
 * @author tce E-mail:
 * @version Create Time��2019��6��4�� ����2:31:14 Description:LIFO,��̬���������С��ʵ��
 */
public class ResizingArrayStack<Item> implements Iterable<Item> {
	private Item[] a = (Item[]) new Object[1];// ջԪ��
	private int N = 0;// Ԫ������

	public boolean isEmpty() {
		return N == 0;
	}

	public int size() {
		return N;
	}

	private void resize(int max) {
		// ��ջ�ƶ���һ����СΪmax������
		Item[] tmp = (Item[]) new Object[max];
		for (int i = 0; i < a.length; i++) {
			tmp[i] = a[i];
		}
		a = tmp;
	}

	public void push(Item item) {
		// ��Ԫ����ӵ�ջ��
		if (N == a.length)
			resize(2 * a.length);
		a[N++] = item;
	}

	public Item pop() {
		// ��ջ��ɾ��Ԫ��
		Item item = a[--N];
		a[N] = null;// ����������룿 1.3.2.4
		if (N > 0 && N == a.length / 4)
			resize(a.length / 2);
		return item;
	}

	private class ReverseArrayIterator implements Iterator<Item> {
		// ֧�ֺ���ȳ��ĵ���
		private int i = N;

		public boolean hasNext() {
			return i > 0;
		}

		public Item next() {
			return a[--N];
		}

		public void remove() {

		}
	}

	@Override
	public Iterator<Item> iterator() {
		// TODO Auto-generated method stub
		return new ReverseArrayIterator();
	}
}

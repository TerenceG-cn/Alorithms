package algorithms_4th_java.chapter1.LIFOstack;

import java.util.Iterator;



/**
 * @author tce E-mail:
 * @version Create Time��2019��6��15�� ����11:52:49 Description������ʵ����ѹջ
 */
public class ListImplStack<Item> implements Iterable<Item> {
	private Node first;// ջ��
	private int N; // Ԫ������

	private class Node {
		// �����˽��� Ƕ����
		Item item;
		Node next;
	}

	public boolean isEmpty() {
		return first == null;
	}

	@Override
	public Iterator<Item> iterator() {
		return new ListIterator();
	}

	private class ListIterator implements Iterator<Item> {

		private Node current = first;

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public Item next() {
			Item item = current.item;
			current = current.next;
			return item;
		}
		
		public void remove() {
			
		}

	}

	public int size() {
		return N;
	}

	public void push(Item item) {
		// ��ջ�����Ԫ��
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.next = oldfirst;
		N++;
	}

	public Item pop() {
		// ��ջ��ɾ��Ԫ��
		Item item = first.item;
		first = first.next;
		N--;
		return item;
	}

}

package algorithms_4th_java.chapter1.LIFOstack;

import java.util.Iterator;



/**
 * @author tce E-mail:
 * @version Create Time：2019年6月15日 上午11:52:49 Description；链表实现下压栈
 */
public class ListImplStack<Item> implements Iterable<Item> {
	private Node first;// 栈顶
	private int N; // 元素数量

	private class Node {
		// 定义了结点的 嵌套类
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
		// 向栈顶添加元素
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.next = oldfirst;
		N++;
	}

	public Item pop() {
		// 从栈顶删除元素
		Item item = first.item;
		first = first.next;
		N--;
		return item;
	}

}

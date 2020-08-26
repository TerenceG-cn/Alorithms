package personal.algorithms_4th_java.chapter1.LIFOstack;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * @author tce E-mail:
 * @version Create Time��2019��6��4�� ����2:22:48 Description:����ջ����ѹջ������ʵ��
 */
public class FixedCapcityStackOfStrings<Item> {
	private Item[] a;// stack entries
	private int N;// size
	
	public static void main(String[] args) {
		FixedCapcityStackOfStrings<String> s=new FixedCapcityStackOfStrings<>(100);
		while(!StdIn.isEmpty()) {
			String item=StdIn.readString();
			if(!item.equals("-"))
					s.push(item);
			else if(item.isEmpty())
				StdOut.print(s.pop()+" ");
		}
		StdOut.println("("+s.size()+" left on stack");
	}
	public FixedCapcityStackOfStrings(int cap) {//����ָ����С��ջ
		a = (Item[]) new Object[cap];
	}

	public boolean isEmpty() {//�п�
		return N == 0;
	}

	public int size() {//��ȡ��С
		return N;
	}

	public void push(Item item) {//ѹ��ջ
		a[N++] = item;
	}

	public Item pop() {//����ջ
		return a[--N];
	}

}

package personal.algorithms_4th_java.chapter1.LIFOstack;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * @author tce E-mail:
 * @version Create Time：2019年6月4日 下午2:22:48 Description:定容栈，下压栈的数组实现
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
	public FixedCapcityStackOfStrings(int cap) {//创建指定大小的栈
		a = (Item[]) new Object[cap];
	}

	public boolean isEmpty() {//判空
		return N == 0;
	}

	public int size() {//获取大小
		return N;
	}

	public void push(Item item) {//压入栈
		a[N++] = item;
	}

	public Item pop() {//弹出栈
		return a[--N];
	}

}

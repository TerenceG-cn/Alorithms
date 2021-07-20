package algorithms_4th_java.chapter3.BalancedBinaryTree;

import java.util.NoSuchElementException;

//�����,ʵ��2-3��
public class RedBlackBST<Key extends Comparable<Key>, Value> {
	private Node root;

	private static final boolean RED = true;
	private static final boolean BLACK = false;

	private class Node {
		private Key key; // key
		private Value val; // associated data
		private Node left, right; // links to left and right subtrees
		private boolean color; // ָ��ý������ӵ���ɫ
		private int size; // �����еĽ������

		public Node(Key key, Value val, boolean color, int size) {
			this.key = key;
			this.val = val;
			this.color = color;
			this.size = size;
		}
	}

	private boolean isRed(Node x) {
		if (x == null)
			return false;
		return x.color == RED;
	}

	private int size(Node x) {
		if (x == null)
			return 0;
		return x.size;
	}

	public int size() {
		return size(root);
	}
	public boolean isEmpty() {
		return root == null;
	}

	//����key�õ�value
	public Value get(Key key) {
		if (key == null) throw new IllegalArgumentException("argument to get() is null");
		return get(root, key);
	}

	// value associated with the given key in subtree rooted at x; null if no such key
	private Value get(Node x, Key key) {
		while (x != null) {
			int cmp = key.compareTo(x.key);
			if      (cmp < 0) x = x.left;
			else if (cmp > 0) x = x.right;
			else              return x.val;
		}
		return null;
	}

	public boolean contains(Key key) {
		return get(key) != null;
	}

	// ����ת
	Node rotateLeft(Node h) {
		Node x = h.right;
		h.right = x.left;
		x.left = h;
		x.color = h.color;
		h.color = RED;
		x.size = h.size;
		h.size = 1 + size(h.left) + size(h.right);
		return x;
	}

	// ����ת
	Node rotateRight(Node h) {
		Node x = h.left;
		h.left = x.right;
		x.right = h;
		x.color = h.color;
		h.color = RED;
		x.size = h.size;
		h.size = 1 + size(h.left) + size(h.right);
		return x;
	}

	// ��ɫת��
	void flipColors(Node h) {
		h.color = RED;
		h.left.color = BLACK;
		h.right.color = BLACK;
	}

	// ����
	public void put(Key key, Value value) {
		// ����key���ҵ��������ֵ������Ϊ���½�һ�����
		root = put(root, key, value);
		root.color = BLACK;
	}

	private Node put(Node h, Key key, Value value) {
		if (h == null) // ��׼�Ĳ���������͸��ڵ��ú���������
			return new Node(key, value, RED, 1);//�²�����һ���Ǻ�ɫ�����ƻ���ɫƽ��

		int cmp = key.compareTo(h.key);
		if (cmp < 0)
			h.left = put(h.left, key, value);//�ȵ�ǰ�ڵ�С ȥ��������
		else if (cmp > 0)
			h.right = put(h.right, key, value);//�ȵ�ǰ�ڵ�� ȥ��������
		else
			h.val = value;//�ҶԶ�Ӧkeyֵ����value

		if (isRed(h.right) && !isRed(h.left))
			h = rotateLeft(h);
		if (isRed(h.left) && isRed(h.left.left))
			h = rotateRight(h);
		if (isRed(h.left) && isRed(h.right))
			flipColors(h);

		h.size = size(h.left) + size(h.right) + 1;
		return h;
	}


	//ɾ����С��
	public void deleteMin() {
		if (isEmpty()) throw new NoSuchElementException("BST underflow");

		// root.left,root.right���Ǻڽڵ㣬root��Ϊ��ڵ�
		if (!isRed(root.left) && !isRed(root.right))
			root.color = RED;

		root = deleteMin(root);
		if (!isEmpty()) root.color = BLACK;
		// assert check();
	}

	// delete the key-value pair with the minimum key rooted at h
	private Node deleteMin(Node h) {
		if (h.left == null)
			return null;

		if (!isRed(h.left) && !isRed(h.left.left))
			h = moveRedLeft(h);

		h.left = deleteMin(h.left);
		return balance(h);
	}
	private Node moveRedLeft(Node h) {
		// assert (h != null);
		// assert isRed(h) && !isRed(h.left) && !isRed(h.left.left);

		flipColors(h);
		if (isRed(h.right.left)) {
			h.right = rotateRight(h.right);
			h = rotateLeft(h);
			flipColors(h);
		}
		return h;
	}
	private Node moveRedRight(Node h) {
		// assert (h != null);
		// assert isRed(h) && !isRed(h.right) && !isRed(h.right.left);
		flipColors(h);
		if (isRed(h.left.left)) {
			h = rotateRight(h);
			flipColors(h);
		}
		return h;
	}

	// restore red-black tree invariant
	private Node balance(Node h) {
		// assert (h != null);

		if (isRed(h.right))                      h = rotateLeft(h);
		if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
		if (isRed(h.left) && isRed(h.right))     flipColors(h);

		h.size = size(h.left) + size(h.right) + 1;
		return h;
	}

	public static void main(String[] args){
	}

}

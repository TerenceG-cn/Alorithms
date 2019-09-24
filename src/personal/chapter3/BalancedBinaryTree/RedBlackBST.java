package personal.chapter3.BalancedBinaryTree;

//������������������2-3��
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
			return new Node(key, value, RED, 1);
		int cmp = key.compareTo(h.key);
		if (cmp < 0)
			h.left = put(h.left, key, value);
		else if (cmp > 0)
			h.right = put(h.right, key, value);
		else
			h.val = value;

		if (isRed(h.right) && !isRed(h.left))
			h = rotateLeft(h);
		if (isRed(h.left) && isRed(h.left.left))
			h = rotateRight(h);
		if (isRed(h.left) && isRed(h.right))
			flipColors(h);

		h.size = size(h.left) + size(h.right) + 1;
		return h;
	}

}

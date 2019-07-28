package personal.chapter3.bst;

/*基于二叉查找树的符号表*/
public class BST<Key extends Comparable<Key>, Value> {
	private Node root; // 二叉树根节点

	private class Node {
		private Key key; // 键
		private Value val; // 值
		private Node left, right; // 指向子树的连接
		private int N; // 以该节点为根的子树个数

		private Node(Key key, Value val, int N) {
			this.key = key;
			this.val = val;
			this.N = N;
		}
	}

	public int size() {
		return size(root);
	}

	private int size(Node x) {
		if (x == null)
			return 0;
		else
			return x.N;
	}

	public Value get(Key key) {
		return get(root, key);
	}

	private Value get(Node x, Key key) {
		// 在以x为根节点的子树中查找并返回key所对应的值
		// 如果没找到则返回null
		if (x == null)
			return null;
		int cmp = key.compareTo(x.key);
		if (cmp < 0)
			return get(x.left, key);
		else if (cmp > 0)
			return get(x.right, key);
		else
			return x.val;
	}

	public void put(Key key, Value val) {
		// 查找key，找到则更新它的值，否则为它创建一个新节点
		root = put(root, key, val);
	}

	private Node put(Node x, Key key, Value val) {
		// 如果key存在于以x为根节点的子树中则更新它的值，否则插入新节点
		if (x == null)
			return new Node(key, val, 1);
		int cmp = key.compareTo(x.key);
		if (cmp < 0)
			return put(x.left, key, val);
		else if (cmp > 0)
			return put(x.right, key, val);
		else
			x.val = val;
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}

	public Key min() {
		return min(root).key;
	}

	private Node min(Node x) {
		if (x == null)
			return null;
		if (x.left == null)
			return x;
		else
			return x.left;
	}

	// 不大于key的最大值
	public Key floor(Key key) {
		Node x = floor(root, key);
		if (x == null)
			return null;
		return x.key;
	}

	private Node floor(Node x, Key key) {
		if (x == null)
			return null;
		int cmp = key.compareTo(x.key);
		if (cmp == 0)
			return x;
		else if (cmp < 0)
			return floor(x.left, key);
		else {
			Node t = floor(x.right, key);
			if (t == null)
				return x;
			else
				return t;
		}
	}

	public Key max() {
		return max(root).key;
	}

	private Node max(Node x) {
		if (x == null)
			return null;
		if (x.right == null)
			return x;
		else
			return x.right;
	}

	// 不小于key的最小值
	public Key ceiling(Key key) {
		Node x = ceiling(root, key);
		if (x == null)
			return null;
		return x.key;
	}

	private Node ceiling(Node x, Key key) {
		if (x == null)
			return null;
		int cmp = key.compareTo(x.key);
		if (cmp == 0)
			return x;
		else if (cmp > 0)
			return ceiling(x.right, key);
		else {
			Node t = floor(x.left, key);
			if (t == null)
				return x;
			else
				return t;
		}
	}
	
	public Key select(int k) {
		return select(root, k).key;
	}
	private Node select(Node x,int k) {
		//返回排名为k的节点
		if(x==null) return null;
		int t=size(x.left);
		if(t>k) return select(x.left, k);
		else if(t<k) return select(x.right, k-t-1);
		else return x;
	}
	
	public int rank(Key key) {
		return rank(key, root);
	}
	private int rank(Key key, Node x) {
		//返回以x为根节点的子树中小于x.key的键的数量
		if(x==null)	return 0;
		int cmp=key.compareTo(x.key);
		if(cmp<0) return rank(key, x.left);
		else if(cmp>0) return 1+size(x.left)+size(x.right);
		else return size(x.left);
		
	}
	
	public void deleteMin() {
		root = deleteMin(root);
	}
	private Node deleteMin(Node x) {
		if(x.left==null) return x.right;
		x.left = deleteMin(x.left);
		x.N=size(x.left)+size(x.right)+1;
		return x;
	}
	
}

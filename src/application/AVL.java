package application;

public class AVL<T extends Comparable<T>> extends BST<T> {

	private TNode<T> rebalance(TNode<T> node) {
		int difference = getHeightDifference(node);
		if (difference > 1) { // addition was in node's left subtree
			if (getHeightDifference(node.getLeft()) > 0)
				node = rotateRight(node);
			else
				node = rotateLeftRight(node);
		} else if (difference < -1) { // addition was in node's right subtree
			if (getHeightDifference(node.getRight()) < 0)
				node = rotateLeft(node);
			else
				node = rotateRightLeft(node);
		}
		return node;
	}

	public void insert(T data) {
		if (isEmpty())
			root = new TNode<>(data);
		else {
			TNode<T> rootNode = root;
			addEntry(data, rootNode);
			root = rebalance(rootNode);
		}
	}

	public void addEntry(T data, TNode<T> root) {
		assert root != null;
		if (data.compareTo((T) root.getData()) < 0) { // right into left subtree
			if (root.hasLeft()) {
				TNode<T> leftChild = root.getLeft();
				addEntry(data, leftChild);
				root.setLeft(rebalance(leftChild));
			} else
				root.setLeft(new TNode<T>(data));
		} else { // right into right subtree
			if (root.hasRight()) {
				TNode<T> rightChild = root.getRight();
				addEntry(data, rightChild);
				root.setRight(rebalance(rightChild));
			} else
				root.setRight(new TNode<T>(data));
		}
	}

	public TNode<T> delete(T data) {
		TNode<T> temp = super.delete(data);
		if (temp != null) {
			TNode<T> rootNode = root;
			root = rebalance(rootNode);
		}
		return temp;
	}

	public int height(TNode<T> node) {
		if (node == null)
			return 0;
		return 1 + Math.max(height(node.getLeft()), height(node.getRight()));
	}

	public int getHeightDifference(TNode<T> root) {
		if (root != null)
			return height(root.getLeft()) - height(root.getRight());
		return 0;
	}

	private TNode<T> rotateRight(TNode<T> root) {
		TNode<T> c = root.getLeft();
		root.setLeft(c.getRight());
		c.setRight(root);
		return c;
	}

	private TNode<T> rotateLeft(TNode<T> root) {
		TNode<T> c = root.getRight();
		root.setRight(c.getLeft());
		c.setLeft(root);
		return c;
	}

	private TNode<T> rotateRightLeft(TNode<T> root) {
		TNode<T> c = root.getRight();
		root.setRight(rotateRight(c));
		return rotateLeft(root);
	}

	private TNode<T> rotateLeftRight(TNode<T> root) {
		TNode<T> c = root.getLeft();
		root.setLeft(rotateLeft(c));
		return rotateRight(root);
	}
}

package application;

public class BST<T extends Comparable<T>> {
	protected TNode<T> root;

	public void insert(T data) {
		if (root == null)
			root = new TNode(data);
		else
			insert(data, root);
	}

	private void insert(T data, TNode node) {
		if (data.compareTo((T) node.getData()) >= 0) { // insert into right subtree
			if (!node.hasRight())
				node.setRight(new TNode<>(data));
			else
				insert(data, node.getRight());
		} else {

			// insert into left subtree
			if (!node.hasLeft())
				node.setLeft(new TNode<>(data));
			else
				insert(data, node.getLeft());
		}
	}

	public void traverseInOrder() {
		traverseInOrder(root);
	}

	private void traverseInOrder(TNode node) {
		if (node != null) {
			if (node.getLeft() != null)
				traverseInOrder(node.getLeft());
			System.out.println(node + " ");
			if (node.getRight() != null)
				traverseInOrder(node.getRight());
		}
	}

	public TNode<T> find(T data) {
	    return find(data, root);
	}

	private TNode<T> find(T data, TNode<T> node) {
	    if (node != null) {
	        int comp = node.getData().compareTo(data);
	        if (comp == 0)
	            return node;
	        else if (comp > 0)
	            return find(data, node.getLeft());  // No need to check node.hasLeft()
	        else
	            return find(data, node.getRight()); // No need to check node.hasRight()
	    }
	    return null;
	}

	public TNode<T> delete(T data) {
		TNode current = root;
		TNode parent = root;
		boolean isLeftChild = false;

		if (root == null)
			return null;// tree is empty
		while (current != null && !current.getData().equals(data)) {
			parent = current;
			if (data.compareTo((T) current.getData()) < 0) {
				current = current.getLeft();
				isLeftChild = true;
			} else {
				current = current.getRight();
				isLeftChild = false;
			}
		}
		if (current == null)
			return null; // node to be deleted not found

		// case 1: node is a leaf
		if (!current.hasLeft() && !current.hasRight()) {
			if (current == root) // tree has one node
				root = null;
			else {
				if (isLeftChild)
					parent.setLeft(null);
				else
					parent.setRight(null);
			}
			return current;
		}
		// other cases

		if (current.hasLeft() && !current.hasRight()) { // current has left child only
			if (current == root) {
				root = current.getLeft();
			} else if (isLeftChild) {
				parent.setLeft(current.getLeft());
			} else {
				parent.setLeft(current.getLeft());
			}
		} else if (current.hasRight() && !current.hasLeft()) { // current has right child only
			if (current == root) {
				root = current.getRight();
			} else if (isLeftChild) {
				parent.setRight(current.getRight());
			} else {
				parent.setRight(current.getRight());
			}
			return current;
		} else {
			TNode successor = getSuccessor(current);
			if (current == root)
				root = successor;
			else if (isLeftChild) {
				parent.setLeft(successor);
			} else {
				parent.setRight(successor);
			}
			successor.setLeft(current.getLeft());
			return current;
		}
		return null;

	}

	private TNode<T> getSuccessor(TNode node) {
		TNode parentOfSuccessor = node;
		TNode successor = node;
		TNode current = node.getRight();
		while (current != null) {
			parentOfSuccessor = successor;
			successor = current;
			current = current.getLeft();
		}
		if (successor != node.getRight()) { // fix successor connections
			parentOfSuccessor.setLeft(successor.getRight());
			successor.setRight(node.getRight());
		}
		return successor;
	}

	public TNode<T> largest() {
		return largest(root);
	}

	private TNode<T> largest(TNode node) {
		if (node != null) {
			if (!node.hasRight())
				return (node);
			return largest(node.getRight());
		}
		return null;
	}

	public TNode<T> smallest() {
		return smallest(root);
	}

	private TNode<T> smallest(TNode node) {
		if (node != null) {
			if (!node.hasLeft())
				return (node);
			return smallest(node.getLeft());
		}
		return null;
	}

	public int height() {
		return height(root);
	}

	private int height(TNode<T> node) {
		if (node == null)
			return 0;
		if (node.isLeaf())
			return 1;
		int left = 0;
		int right = 0;
		if (node.hasLeft())
			left = height(node.getLeft());
		if (node.hasRight())
			right = height(node.getRight());
		return 1 + ((left > right) ? left : right);
	}

	public TNode<T> takeRoot() {
		if (root != null)
			return root;
		return null;
	}

	public boolean isEmpty() {
		if (root != null)
			return false;
		else
			return true;
	}

	public int size() {
		if (root != null)
			return size(root, 0);
		return 0;
	}

	private int size(TNode node, int count) {
		if (node != null) {
			count++;
			if (node.getLeft() != null)
				count = size(node.getLeft(), count);
			if (node.getRight() != null)
				count = size(node.getRight(), count);
		}

		return count;
	}

}

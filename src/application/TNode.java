package application;

public class TNode<T extends Comparable<T>> implements Comparable<TNode<T>> {

	private TNode<T> left;
	private TNode<T> right;
	private int height;
	private T data;

	public TNode(T data) {
		super();
		this.data = data;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public TNode<T> getLeft() {
		return left;
	}

	public void setLeft(TNode<T> left) {
		this.left = left;
	}

	public TNode<T> getRight() {
		return right;
	}

	public void setRight(TNode<T> right) {
		this.right = right;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public boolean hasLeft() {
		return left != null;
	}

	public boolean hasRight() {
		return right != null;
	}

	public boolean isLeaf() {
		return (left == null && right == null);
	}

	@Override
	public String toString() {
		return data + "";
	}

	@Override
	public int compareTo(TNode<T> o) {

		return 0;
	}

}
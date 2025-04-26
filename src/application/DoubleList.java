package application;

public class DoubleList<T extends Comparable<T>> {

	DNode<T> head;
	DNode<T> tail;

	public DoubleList() {
		head = null;
		tail = null;
	}

	// Insert at the end
	public void insertAtEnd(T value) {
		DNode<T> newNode = new DNode(value);
		if (head == null) {
			head = tail = newNode;
		} else {
			tail.setNext(newNode);
			newNode.setPrev(tail);
			tail = newNode;
		}
	}

	public void clear() {
		head = null;
		tail = null;
	}

}

package application;

public class Queue<T extends Comparable<T>> {

	private LinkedList<T> list = new LinkedList<>();

	public void enqueue(T data) {
		SNode<T> newNode = new SNode<>(data);
		if (isEmpty())
			list.setFirstItem(newNode);
		else
			list.lastItem().setNext(newNode);
		list.setLastItem(newNode);
	}

	public T dequeue() {
		T temp = getFront();
		if (!isEmpty())
			list.setFirstItem(list.firstItem().getNext());
		if (list.firstItem() == null)
			list.setLastItem(null);
		return temp;
	}

	public T getFront() {
		if (!isEmpty())
			return list.firstItem().getData();
		return null;
	}

	public boolean isEmpty() {
		return list.firstItem() == null && list.lastItem() == null;
	}

	public void clear() {
		list.setFirstItem(null);
		list.setLastItem(null);
	}
}

package application;

public class LinkedList<T extends Comparable<T>> {

	private SNode<T> head;
	private SNode<T> tail;

	public void insert(T data) {
		SNode<T> node = new SNode<>(data);

		if (head == null) {
			head = node;
			tail = node;
		} else {
			SNode<T> prev = null;
			SNode<T> curr = head;
			for (; curr != null && curr.getData().compareTo(data) < 0; prev = curr, curr = curr.getNext())
				;
			if (prev == null) { // Case 1
				node.setNext(head);
				head = node;
			} else if (curr == null) { // Case 2
				prev.setNext(node);
				tail = node;
			} else { // Case 3
				node.setNext(curr);
				prev.setNext(node);
			}
		}
	}

	public SNode<T> delete(T data) {
		SNode<T> prev = null;
		SNode<T> curr = head;

		while (curr != null) {
			if (curr.getData().equals(data))
				break;
			prev = curr;
			curr = curr.getNext();
		}
		if (curr != null && curr.getData().equals(data)) {
			if (head == curr) {
				head = curr.getNext();
				if (head == null)
					tail = null;
			} else if (curr == tail) {
				prev.setNext(null);
				tail = prev;
			} else
				prev.setNext(curr.getNext());
			return curr;
		}

		return null;
	}

	public SNode<T> find(T data) {
		SNode<T> curr = head;
		while (curr != null) {
			if (curr.getData().equals(data)) {// if the location found record name return it;
				return curr;
			}
			curr = curr.getNext();
		}
		return null;
	}

	public int lenght() {
		SNode<T> curr = head;
		int count = 0;
		while (curr != null) {
			count++;
			curr = curr.getNext();
		}
		return count;
	}

	public SNode<T> firstItem() {
		if (head != null)
			return head;
		return null;
	}

	public SNode<T> lastItem() {
		if (tail != null)
			return tail;
		return null;
	}

	public void setFirstItem(SNode<T> node) {
		head = node;

	}

	public void setLastItem(SNode<T> node) {
		tail = node;
	}

	public void tranverse() {
		SNode<T> c = head;
		System.out.print("head-->");
		while (c != null) {
			System.out.print(c + "-->");
			c = c.getNext();
		}
		System.out.println("null");
	}

	public void clear() {
		head = null;
		tail = null;
	}
}

package application;



public class HNode<T extends Comparable<T>> {
	
	enum Flag {
		E, D, F
	}
	
	private T data;
	private Flag flag;
	 private HNode<T> next;
	 
	public HNode(T data, Flag flag) {
		this.data = data;
		this.flag=flag;
	}

	public HNode(T data) {
		super();
		this.data = data;
	}

	@Override
	public String toString() {
		return data + "" + flag;
	}

	public Flag getFlag() {
		return flag;
	}

	public void setFlag(Flag flag) {
		this.flag = flag;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public HNode<T> getNext() {
		return next;
	}

	public void setNext(HNode<T> next) {
		this.next = next;
	}
	
}

package application;

public class Date implements Comparable<Date> {

	private String date;
	private AVL<Martyr> martyrTree = new AVL<>();

	public Date() {
	}

	public Date(String date) {
		super();
		this.date = date;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public AVL<Martyr> getMartyrTree() {
		return martyrTree;
	}

	public void setMartyrTree(AVL<Martyr> martyrTree) {
		this.martyrTree = martyrTree;
	}

	@Override
	public String toString() {
		return date + "";
	}

	@Override
	public int compareTo(Date o) {
		return date.compareTo(o.date);
	}

	@Override
	public int hashCode() {
		String a[] = date.split("/");
		int hash = Integer.parseInt(a[2]);
		return hash;

	}

	public void addMartyr(Martyr martyr) {
		
			martyrTree.insert(martyr);
			
	}

}

package application;

import application.HNode.Flag;

public class HashTabel<T extends Comparable<T>> {

	private HNode<T>[] table;
	private int m = 11, size = 0;

	public HashTabel() {
		super();
		table = new HNode[m];
		for (int i = 0; i < table.length; i++) {
			table[i] = new HNode<T>(null, Flag.E);
		}
	}

	public HNode<T> add(T data) {
		if (size >= m / 2) {
			rehash();
		}

		int hash = Math.abs(data.hashCode());
		int index = hash % m;

		int i = 0;
		while (table[index] != null && table[index].getFlag() == Flag.F) {
			if (table[index].getData().compareTo(data) == 0) {
				return table[index];
			}
			index = Math.abs((index + i * i) % m);
			i++;
		}

		HNode<T> newNode = new HNode(data, Flag.F);
		table[index] = newNode;
		size++;
		return table[index];
	}

	public HNode<T> delete(T data) {
		HNode<T> deleted = find(data);
		if (deleted != null) {
			deleted.setFlag(Flag.D);
			size--;
		}
		return deleted;
	}

	public HNode<T> find(T data) {

		int hash = Math.abs(data.hashCode());
		int index = hash % m;

		int i = 0;

		while (table[index] != null && i < m) {
			HNode<T> node = table[index];
			if (node.getFlag() == Flag.F && node.getData().compareTo(data) == 0) {
				return node;
			}
			index = Math.abs((index + i * i) % m);
			i++;
		}

		return null;
	}

	private void rehash() {
		int newM = m;
		HNode<T>[] newTable = table;

		m *= 2;
		while (!isPrime(m)) {
			m++;
		}

		table = new HNode[m];

		for (int i = 0; i < m; i++)
			table[i] = new HNode<>(null, Flag.E);
		size = 0;

		for (int i = 0; i < newM; i++)
			if (newTable[i].getFlag() == Flag.F)
				add(newTable[i].getData());
	}

	private boolean isPrime(int n) {
		for (int i = 2; i * i <= n; i++)
			if (n % i == 0)
				return false;
		return true;
	}

	public void traverse() {

		int cont = 0;
		for (int i = 0; i < m; i++) {
			if (table[i].getData() != null) {
				System.out.print(table[i] + " - ");
				cont++;
			}
		}
	}

	public int lenght() {
		int cont = 0;
		int size = table.length;
		while (size != 0) {
			cont++;
			size--;
		}
		return cont;
	}
	
	

	public HNode<T>[] getTable() {
		return table;
	}

	public void setTable(HNode<T>[] table) {
		this.table = table;
	}

	public int getM() {
		return m;
	}

	public void setM(int m) {
		this.m = m;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

}

package application;

import application.HNode.Flag;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.util.ArrayList;
import javafx.scene.control.cell.PropertyValueFactory;

public class Methods {
	private HashTabel<Date> table = new HashTabel<>();
	private LinkedList<District> districtList = new LinkedList<>();

	public Methods() {

	}

	public HashTabel<Date> getTable() {
		return table;
	}

	public void setTable(HashTabel<Date> table) {
		this.table = table;
	}

	public LinkedList<District> getDistrictList() {
		return districtList;
	}

	public void setDistrictList(LinkedList<District> districtList) {
		this.districtList = districtList;
	}

	public int total(Date date) {
		HNode<Date> findDate = table.find(date);
		if (findDate == null)
			return 0;
		return findDate.getData().getMartyrTree().size();

	}

	public double avaregeAge(Date date) {
		HNode<Date> findDate = table.find(date);
		if (findDate == null)
			return 0;
		TNode<Martyr> martyrs = findDate.getData().getMartyrTree().takeRoot();
		double age = avg(martyrs);
		double avg = age / total(date);
		return avg;

	}

	private double avg(TNode<Martyr> node) {
		if (node == null) {
			return 0;
		}
		return node.getData().getAge() + avg(node.getLeft()) + avg(node.getRight());
	}

	public int maleMartyrs(Date date) {
		HNode<Date> findDate = table.find(date);
		if (findDate == null) {
			return 0;
		}
		TNode<Martyr> martyrs = findDate.getData().getMartyrTree().takeRoot();
		return maleCount(martyrs);
	}

	private int maleCount(TNode<Martyr> node) {
		if (node == null) {
			return 0;
		}
		int count = 0;
		if (node.getData().getGender().equals("M")) {
			count = 1;
		}
		return count + maleCount(node.getLeft()) + maleCount(node.getRight());
	}

	public int femaleMartyrs(Date date) {
		HNode<Date> findDate = table.find(date);
		if (findDate == null) {
			return 0;
		}
		TNode<Martyr> martyrs = findDate.getData().getMartyrTree().takeRoot();
		return femaleCount(martyrs);
	}

	private int femaleCount(TNode<Martyr> node) {
		if (node == null) {
			return 0;
		}
		int count = 0;
		if (node.getData().getGender().equals("F")) {
			count = 1;
		}
		return count + femaleCount(node.getLeft()) + femaleCount(node.getRight());
	}

	public String maxDistrict(Date date) {
		HNode<Date> findDate = table.find(date);
		if (findDate == null) {
			return null;
		}
		TNode<Martyr> martyrs = findDate.getData().getMartyrTree().takeRoot();
		if (martyrs == null) {
			return null;
		}

		ArrayList<String> districts = new ArrayList<>();
		ArrayList<Integer> districtCount = new ArrayList<>();
		martyrsInSpasificDistrict(martyrs, districts, districtCount);

		return findMaxDistrict(districts, districtCount);
	}

	private void martyrsInSpasificDistrict(TNode<Martyr> node, ArrayList<String> districts,
			ArrayList<Integer> districtCount) {
		if (node == null) {
			return;
		}

		String district = node.getData().getDistrict();
		int index = districts.indexOf(district);
		if (index == -1) {
			districts.add(district);
			districtCount.add(1);
		} else {
			districtCount.set(index, districtCount.get(index) + 1);
		}

		martyrsInSpasificDistrict(node.getLeft(), districts, districtCount);
		martyrsInSpasificDistrict(node.getRight(), districts, districtCount);
	}

	private String findMaxDistrict(ArrayList<String> districts, ArrayList<Integer> districtCount) {
		String maxDistrict = null;
		int maxCount = 0;

		for (int i = 0; i < districts.size(); i++) {
			if (districtCount.get(i) > maxCount) {
				maxCount = districtCount.get(i);
				maxDistrict = districts.get(i);
			}
		}

		return maxDistrict;
	}

	public String maxLocation(Date date) {
		HNode<Date> findDate = table.find(date);
		if (findDate == null) {
			return null;
		}
		TNode<Martyr> martyrs = findDate.getData().getMartyrTree().takeRoot();
		if (martyrs == null) {
			return null;
		}

		ArrayList<String> locations = new ArrayList<>();
		ArrayList<Integer> locationCount = new ArrayList<>();
		martyrInSpasificLocation(martyrs, locations, locationCount);

		return findMaxLocation(locations, locationCount);
	}

	private void martyrInSpasificLocation(TNode<Martyr> node, ArrayList<String> locations,
			ArrayList<Integer> locationCount) {
		if (node == null) {
			return;
		}

		String location = node.getData().getLocation();
		int index = locations.indexOf(location);
		if (index == -1) {
			locations.add(location);
			locationCount.add(1);
		} else {
			locationCount.set(index, locationCount.get(index) + 1);
		}

		martyrInSpasificLocation(node.getLeft(), locations, locationCount);
		martyrInSpasificLocation(node.getRight(), locations, locationCount);
	}

	private String findMaxLocation(ArrayList<String> locations, ArrayList<Integer> locationCount) {
		String maxLocation = null;
		int maxCount = 0;

		for (int i = 0; i < locations.size(); i++) {
			if (locationCount.get(i) > maxCount) {
				maxCount = locationCount.get(i);
				maxLocation = locations.get(i);
			}
		}

		return maxLocation;
	}

	public TableView<String> printDates() {
		TableView<String> tableView = new TableView<>();
		TableColumn<String, String> dateColumn = new TableColumn<>("Date");
		dateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()));

		dateColumn.setPrefWidth(200);

		tableView.getColumns().addAll(dateColumn);

		for (int i = 0; i < table.lenght(); i++) {
			if (table.getTable()[i] != null && table.getTable()[i].getFlag() == Flag.F) {
				tableView.getItems().add(table.getTable()[i].getData().getDate());

			}

		}
		return tableView;

	}

	public TableView<Date> printAllDates() {
		TableView<Date> tableView2 = new TableView<>();
		TableColumn<Date, String> allDatesColumn = new TableColumn<>("All Dates");
		allDatesColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

		allDatesColumn.setPrefWidth(200);

		tableView2.getColumns().addAll(allDatesColumn);

		for (int i = 0; i < table.lenght(); i++) {
			if (table.getTable()[i] != null
					&& (table.getTable()[i].getFlag() == Flag.F || table.getTable()[i].getFlag() == Flag.E|| table.getTable()[i].getFlag() == Flag.D)) {
				tableView2.getItems().add(table.getTable()[i].getData());
			}
		}
		return tableView2;

	}

	public boolean updateMartyr(Date date, Martyr oldMartyr, Martyr newMartyr) {
		HNode<Date> findDate = table.find(date);
		TNode<Martyr> findOldMartyr = findDate.getData().getMartyrTree().find(oldMartyr);
		TNode<Martyr> findNewMartyr = findDate.getData().getMartyrTree().find(newMartyr);
		if (findOldMartyr.equals(findNewMartyr) || (findOldMartyr != null && findNewMartyr == null)) {
			findDate.getData().getMartyrTree().delete(findOldMartyr.getData());
			findDate.getData().addMartyr(newMartyr);
			return true;
		}
		return false;

	}

	DoubleList<String> datesList = new DoubleList<>();
	private DNode<String> node = datesList.head;

	public DNode<String> getNode() {
		return node;
	}

	public void setNode(DNode<String> node) {
		this.node = node;
	}

	public void fullTheList() {
		for (int i = 0; i < table.lenght(); i++) {
			if (table.getTable()[i] != null && table.getTable()[i].getFlag() == Flag.F) {
				datesList.insertAtEnd(table.getTable()[i].getData().getDate());
			}
		}
		node = datesList.head;

	}

	public TableView<Martyr> printMartyrs(Date date) {

		TableView<Martyr> printMartyr = new TableView<Martyr>();

		TableColumn<Martyr, String> nameColumn = new TableColumn<>("Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

		TableColumn<Martyr, Integer> ageColumn = new TableColumn<>("Age");
		ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));

		TableColumn<Martyr, Integer> locationColumn = new TableColumn<>("location");
		locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));

		TableColumn<Martyr, Integer> districtColumn = new TableColumn<>("district");
		districtColumn.setCellValueFactory(new PropertyValueFactory<>("district"));

		TableColumn<Martyr, Integer> genderColumn = new TableColumn<>("gender");
		genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));

		TableColumn<Martyr, Integer> dateColumn = new TableColumn<>("date");
		dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

		printMartyr.getColumns().addAll(nameColumn, ageColumn, locationColumn, districtColumn, genderColumn,
				dateColumn);

		HNode<Date> findDate = table.find(date);
		TNode<Martyr> martyrs = findDate.getData().getMartyrTree().takeRoot();
		ObservableList<Martyr> data = FXCollections.observableArrayList();
		if (martyrs == null)
			return printMartyr;
		Queue<TNode<Martyr>> q = new Queue<>();
		q.enqueue(martyrs);
		while (!q.isEmpty()) {

			TNode<Martyr> node = q.dequeue();
			data.add(node.getData());
			if (node.getRight() != null) {
				q.enqueue(node.getRight());
			}
			if (node.getLeft() != null) {
				q.enqueue(node.getLeft());
			}

		}
		printMartyr.setItems(data);
		return printMartyr;

	}

	public TableView<Martyr> sortByAge(Date date) {
		TableView<Martyr> sortedMartyrs = new TableView<Martyr>();

		HNode<Date> findDate = table.find(date);
		int size = findDate.getData().getMartyrTree().size();

		TNode<Martyr> martyrs = findDate.getData().getMartyrTree().takeRoot();
		if (martyrs == null)
			return sortedMartyrs;
		Martyr[] martyrsArray = new Martyr[size + 1];
		martyrsArray(martyrs, martyrsArray, 1);
		heapSort(martyrsArray);

		TableColumn<Martyr, String> nameColumn = new TableColumn<>("Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

		TableColumn<Martyr, Integer> ageColumn = new TableColumn<>("Age");
		ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));

		TableColumn<Martyr, Integer> locationColumn = new TableColumn<>("location");
		locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));

		TableColumn<Martyr, Integer> districtColumn = new TableColumn<>("district");
		districtColumn.setCellValueFactory(new PropertyValueFactory<>("district"));

		TableColumn<Martyr, Integer> genderColumn = new TableColumn<>("gender");
		genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));

		TableColumn<Martyr, Integer> dateColumn = new TableColumn<>("date");
		dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

		sortedMartyrs.getColumns().addAll(nameColumn, ageColumn, locationColumn, districtColumn, genderColumn,
				dateColumn);

		sortedMartyrs.getItems().addAll(martyrsArray);
		return sortedMartyrs;

	}

	private int martyrsArray(TNode<Martyr> node, Martyr[] martyrsArray, int index) {
		if (node == null)
			return index;

		martyrsArray[index++] = node.getData();
		index = martyrsArray(node.getLeft(), martyrsArray, index);
		index = martyrsArray(node.getRight(), martyrsArray, index);
		return index;
	}

	public void heapSort(Martyr[] martyrArray) {
		int N = martyrArray.length - 1;
		Martyr temp;

		heapify(martyrArray);

		while (N > 1) {
			temp = martyrArray[1];
			martyrArray[1] = martyrArray[N];
			martyrArray[N] = temp;
			N--;
			int k = 1;
			while (2 * k <= N) {
				int j = 2 * k;
				if (j < N && martyrArray[j].getAge() < martyrArray[j + 1].getAge())
					j++;
				if (martyrArray[k].getAge() >= martyrArray[j].getAge())
					break;
				temp = martyrArray[k];
				martyrArray[k] = martyrArray[j];
				martyrArray[j] = temp;
				k = j;
			}
		}
	}

	private void heapify(Martyr[] martyrsArray) {
		int N = martyrsArray.length - 1, i = N / 2;
		Martyr temp;
		while (i-- > 0) {
			int k = i + 1;
			while (2 * k <= N) {
				int j = 2 * k;
				if (j < N && martyrsArray[j].getAge() < martyrsArray[j + 1].getAge())
					j++;
				if (martyrsArray[k].getAge() >= martyrsArray[j].getAge())
					break;
				temp = martyrsArray[k];
				martyrsArray[k] = martyrsArray[j];
				martyrsArray[j] = temp;
				k = j;
			}
		}
	}

	public void updateMartyrDate(Date newDate) {
		HNode<Date> find = table.find(newDate);
		TNode<Martyr> martyrs = find.getData().getMartyrTree().takeRoot();
		if (martyrs == null)
			return;
		updateMartyrDate(martyrs, newDate);

	}

	private void updateMartyrDate(TNode<Martyr> node, Date date) {
		if (node == null)
			return;
		node.getData().setDate(date.getDate());
		updateMartyrDate(node.getLeft(), date);
		updateMartyrDate(node.getRight(), date);
	}

	public boolean update(Date oldData, Date newData) {
		HNode<Date> findOld = table.find(oldData);
		HNode<Date> findNew = table.find(newData);

		if (findOld != null && findNew == null) {
			HNode<Date> node = findOld;
			table.delete(oldData);
			node.getData().setDate(newData.getDate());
			table.add(node.getData());

			return true;
		}
		return false;

	}

}

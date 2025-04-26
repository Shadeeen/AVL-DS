package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;

import java.util.Scanner;

import application.HNode.Flag;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Main extends Application {
	private VBox vb;
	private Stage stage;
	private FileChooser fileChooser;
	private File selectedFile;
	private File file;
	private Button fileButton;
	private Button dateScreen;
	private Button writeInFileButton;
	private Label massege;
	private Label pray;
	private File writeInFile;

	Methods m = new Methods();
	MartyrScreen martyrS = new MartyrScreen();

	public void start(Stage primaryStage) {
		try {

			ImageView imageView = new ImageView("p.jpg");
			StackPane stackPane = new StackPane();
			stackPane.getChildren().addAll(imageView, firstScreen());
			MartyrScreen mar = new MartyrScreen();
			Scene scene = new Scene(stackPane, 300, 350);
			primaryStage.setScene(scene);
			primaryStage.show();

			DateScreen dateS = new DateScreen();
			Scene dateScene = new Scene(dateS.datePane(), 800, 400);
			Stage dateStage = new Stage();
			dateStage.setScene(dateScene);
			dateScreen.setOnAction(e -> {
				m.fullTheList();
				dateStage.show();
				primaryStage.close();
			});

			dateS.getNextDateButton().setOnAction(e -> {
				if (m.getNode().getNext() != null) {
					m.setNode(m.getNode().getNext());
					Date d = new Date(m.getNode().getData());
					HNode<Date> findDate = m.getTable().find(d);
					if (findDate != null) {

						dateS.getDateName().setText(findDate.getData().getDate());
						dateS.getTotalResult().setText(m.total(findDate.getData()) + "");
						dateS.getAgeAvaregeResult().setText(m.avaregeAge(findDate.getData()) + "");
						dateS.getMaleResult().setText(m.maleMartyrs(findDate.getData()) + "");
						dateS.getFemaleResult().setText(m.femaleMartyrs(findDate.getData()) + "");
						dateS.getMaxDistrictResult().setText(m.maxDistrict(findDate.getData()));
						dateS.getMaxLocationResult().setText(m.maxLocation(findDate.getData()));
					}
				}
			});

			dateS.getPrevDateButton().setOnAction(e -> {
				if (m.getNode().getPrev() != null) {
					m.setNode(m.getNode().getPrev());
					Date d = new Date(m.getNode().getData());
					HNode<Date> findDate = m.getTable().find(d);
					if (findDate != null) {
						dateS.getDateName().setText(findDate.getData().getDate());
						dateS.getTotalResult().setText(m.total(findDate.getData()) + "");
						dateS.getAgeAvaregeResult().setText(m.avaregeAge(findDate.getData()) + "");
						dateS.getMaleResult().setText(m.maleMartyrs(findDate.getData()) + "");
						dateS.getFemaleResult().setText(m.femaleMartyrs(findDate.getData()) + "");
						dateS.getMaxDistrictResult().setText(m.maxDistrict(findDate.getData()));
						dateS.getMaxLocationResult().setText(m.maxLocation(findDate.getData()));
					}
				}
			});

			dateS.getPrintDatesWithEmpty().setOnAction(e -> {
				Scene printDates = new Scene(m.printAllDates(), 200, 400);
				Stage print = new Stage();
				print.setScene(printDates);
				print.show();
			});

			dateS.getPrintDates().setOnAction(e -> {
				Scene printDates = new Scene(m.printDates(), 200, 400);
				Stage print = new Stage();
				print.setScene(printDates);
				print.show();
			});

			dateS.getAddDateRadioButton().setOnAction(e -> {
				dateS.getAddDateRadioButton().setSelected(false);
				if (dateS.getAddDatePicker().getValue() == null) {
					dateS.getDoneAddDateLabel().setText("enter date!!");
					return;
				} else if (dateS.getAddDatePicker().getValue().isAfter(LocalDate.now())) {
					dateS.getDoneAddDateLabel().setText("invaild date!!!");
					return;
				}
				String date = dateS.getAddDatePicker().getValue() + "";
				String a[] = date.split("-");
				date = a[1] + "/" + a[2] + "/" + a[0];

				Date findDate = new Date(date);

				if (m.getTable().find(findDate) != null) {
					dateS.getDoneAddDateLabel().setText(date + " already exist");
				} else {
					m.getTable().add(findDate);
					dateS.getDoneAddDateLabel().setText(findDate.getDate() + " added");
					m.datesList.clear();
					m.fullTheList();

					dateS.getDateName().setText("Date");
					dateS.getTotalResult().setText("");
					dateS.getAgeAvaregeResult().setText("");
					dateS.getMaleResult().setText("");
					dateS.getFemaleResult().setText("");
					dateS.getMaxDistrictResult().setText("");
					dateS.getMaxLocationResult().setText("");
				}
				dateS.getAddDatePicker().setValue(null);
			});

			dateS.getDeleteDateRadioButton().setOnAction(e -> {
				dateS.getDeleteDateRadioButton().setSelected(false);
				if (dateS.getAddDatePicker().getValue() == null) {
					dateS.getDoneAddDateLabel().setText("enter date!!");
					return;
				} else if (dateS.getAddDatePicker().getValue().isAfter(LocalDate.now())) {
					dateS.getDoneAddDateLabel().setText("invaild date!!!");
					return;
				}
				String date = dateS.getAddDatePicker().getValue() + "";
				String a[] = date.split("-");
				date = a[1] + "/" + a[2] + "/" + a[0];

				Date findDate = new Date(date);
				if (m.getTable().delete(findDate) != null) {
					dateS.getDoneAddDateLabel().setText(findDate + " deleted!!!");
					m.datesList.clear();
					m.fullTheList();
					dateS.getDateName().setText("Date");
					dateS.getTotalResult().setText("");
					dateS.getAgeAvaregeResult().setText("");
					dateS.getMaleResult().setText("");
					dateS.getFemaleResult().setText("");
					dateS.getMaxDistrictResult().setText("");
					dateS.getMaxLocationResult().setText("");

				} else
					dateS.getDoneAddDateLabel().setText(findDate + " not exist!!!");
				dateS.getAddDatePicker().setValue(null);
			});

			dateS.getUpdateDateRadioButton().setOnAction(e -> {
				dateS.getUpdateDateRadioButton().setSelected(false);
				if (dateS.getUpdateDatePicker().getValue() == null
						|| dateS.getUpdateToDatePicker().getValue() == null) {
					dateS.getDoneAddDateLabel().setText("enter the dates");
					return;
				} else if (dateS.getUpdateDatePicker().getValue().isAfter(LocalDate.now())
						|| dateS.getUpdateToDatePicker().getValue().isAfter(LocalDate.now())) {
					dateS.getDoneAddDateLabel().setText("invaild date!!!");
					return;
				}
				String oldDate = dateS.getUpdateDatePicker().getValue() + "";
				String a[] = oldDate.split("-");
				oldDate = a[1] + "/" + a[2] + "/" + a[0];

				String newDate = dateS.getUpdateToDatePicker().getValue() + "";
				String a2[] = newDate.split("-");
				newDate = a2[1] + "/" + a2[2] + "/" + a2[0];

				Date old = new Date(oldDate);
				Date neW = new Date(newDate);
				if (m.update(old, neW)) {
					m.updateMartyrDate(neW);
					dateS.getDoneAddDateLabel().setText(oldDate + " updated");
					m.datesList.clear();
					m.fullTheList();
					dateS.getDateName().setText("Date");
					dateS.getTotalResult().setText("");
					dateS.getAgeAvaregeResult().setText("");
					dateS.getMaleResult().setText("");
					dateS.getFemaleResult().setText("");
					dateS.getMaxDistrictResult().setText("");
					dateS.getMaxLocationResult().setText("");
				} else
					dateS.getDoneAddDateLabel().setText(" not updated!!!");
				dateS.getUpdateDatePicker().setValue(null);
				dateS.getUpdateToDatePicker().setValue(null);

			});

			Scene MartyrScene = new Scene(martyrS.martyrPane(), 600, 400);
			Stage martyrStage = new Stage();
			martyrStage.setScene(MartyrScene);

			martyrS.getDistrictsCo().setOnAction(e -> {
				District d = new District(martyrS.getDistrictsCo().getValue());
				SNode<District> findDistrict = m.getDistrictList().find(d);
				if (d != null) {
					martyrS.getLocationsCo().getItems().clear();
					SNode<Location> locations = findDistrict.getData().getLocationList().firstItem();
					while (locations != null) {
						martyrS.getLocationsCo().getItems().add(locations.getData().getLocation());
						locations = locations.getNext();
					}
				}
			});

			dateS.getLoadMartyrScreen().setOnAction(e -> {
				if (dateS.getDateName().getText().trim().equalsIgnoreCase("date")) {
					return;
				}
				if (m.getNode() != null) {
					martyrS.getMartyrLabel().setText(dateS.getDateName().getText() + " Martyrs");
					martyrStage.show();
				}
			});

			martyrS.getSize().setOnAction(e -> {
				if (dateS.getDateName().getText().trim().equalsIgnoreCase("date")) {
					return;
				}
				Date date = new Date(dateS.getDateName().getText().trim());
				HNode<Date> findDate = m.getTable().find(date);
				martyrS.getSizeField().setText(findDate.getData().getMartyrTree().size() + "");
			});

			martyrS.getHeight().setOnAction(e -> {
				if (dateS.getDateName().getText().trim().equalsIgnoreCase("date")) {
					return;
				}
				Date date = new Date(dateS.getDateName().getText().trim());
				HNode<Date> findDate = m.getTable().find(date);
				martyrS.getHeightField().setText(findDate.getData().getMartyrTree().height() + "");
			});

			martyrS.getOkMartyrButton().setOnAction(e -> {
				m.datesList.clear();
				m.fullTheList();

				dateS.getDateName().setText("Date");
				dateS.getTotalResult().setText("");
				dateS.getAgeAvaregeResult().setText("");
				dateS.getMaleResult().setText("");
				dateS.getFemaleResult().setText("");
				dateS.getMaxDistrictResult().setText("");
				dateS.getMaxLocationResult().setText("");

				martyrStage.close();

			});

			martyrS.getPrint().setOnAction(e -> {
				if (dateS.getDateName().getText().trim().equalsIgnoreCase("date")) {
					return;
				}
				Date date = new Date(dateS.getDateName().getText().trim());
				if (m.printMartyrs(date) != null) {
					Scene printMartyrs = new Scene(m.printMartyrs(date), 600, 400);
					Stage print = new Stage();
					print.setScene(printMartyrs);
					print.show();
				}
			});

			martyrS.getSortMartyrButton().setOnAction(e -> {
				if (dateS.getDateName().getText().trim().equalsIgnoreCase("date")) {
					return;
				}
				Date date = new Date(dateS.getDateName().getText().trim());
				if (m.printMartyrs(date) != null) {
					Scene sortedMartyrs = new Scene(m.sortByAge(date), 600, 400);
					Stage print = new Stage();
					print.setScene(sortedMartyrs);
					print.show();
				}
			});

			martyrS.getAddMartyrButton().setOnAction(e -> {
				if (martyrS.getNameTextField().getText().isBlank() || martyrS.getAgeTextField().getText().isBlank()
						|| martyrS.getDistrictsCo().getValue() == null || martyrS.getLocationsCo().getValue() == null) {
					martyrS.getMassegeMartyrLabel().setText("enter all the information!!!");
					return;
				}
				String name = martyrS.getNameTextField().getText().trim();
				int age = 0;
				try {
					age = Integer.parseInt(martyrS.getAgeTextField().getText().trim());
					if (age < 0 || age > 120) {
						martyrS.getMassegeMartyrLabel().setText("invaild age!!!");
						return;
					}
				} catch (NumberFormatException f) {
					martyrS.getMassegeMartyrLabel().setText("invaild age!!!");
					return;
				}
				String gender = "";
				if (martyrS.getMaleMartyrRadioButton().isSelected()) {
					gender = "M";
				} else if (martyrS.getFemaleMartyrRadioButton().isSelected()) {
					gender = "F";
				}
				Date date = new Date(dateS.getDateName().getText().trim());
				String district = martyrS.getDistrictsCo().getValue();
				String location = martyrS.getLocationsCo().getValue();
				Martyr martyr = new Martyr(name, date.getDate(), age, location, district, gender);
				if (m.getTable().find(date).getData().getMartyrTree().find(martyr) == null) {
					m.getTable().find(date).getData().addMartyr(martyr);
					martyrS.getMassegeMartyrLabel().setText("done");
				} else
					martyrS.getMassegeMartyrLabel().setText("the martyr exist!!");
				martyrS.clean();

			});

			martyrS.getDeleteMartyrButton().setOnAction(e -> {
				if (martyrS.getNameTextField().getText().isBlank() || martyrS.getDistrictsCo().getValue() == null) {
					martyrS.getMassegeMartyrLabel().setText("enter name and district to delete ");
					return;
				}
				String name = martyrS.getNameTextField().getText().trim();
				String district = martyrS.getDistrictsCo().getValue();
				Martyr deleteM = new Martyr(name, district);
				Date date = new Date(dateS.getDateName().getText().trim());
				if (m.getTable().find(date).getData().getMartyrTree().delete(deleteM) != null) {
					martyrS.getMassegeMartyrLabel().setText("done");

				} else
					martyrS.getMassegeMartyrLabel().setText("not exist!!!");
				martyrS.clean();
			});

			Martyr oldMartyr = new Martyr();
			martyrS.getDoneUpdeteButton().setOnAction(e -> {
				if (martyrS.getUpateNameTextField().getText().isBlank()
						|| martyrS.getDistrictsCo().getValue() == null) {
					martyrS.getMassegeMartyrLabel().setText("enter old martyr name and district");
					return;
				}
				String oldName = martyrS.getUpateNameTextField().getText().trim();
				oldMartyr.setName(oldName);
				oldMartyr.setDistrict(martyrS.getDistrictsCo().getValue());
				Date date = new Date(dateS.getDateName().getText().trim());
				if (m.getTable().find(date).getData().getMartyrTree().find(oldMartyr) != null) {
					Martyr find = m.getTable().find(date).getData().getMartyrTree().find(oldMartyr).getData();
					martyrS.getNameTextField().setText(find.getName());
					martyrS.getAgeTextField().setText(find.getAge() + "");
					String gender = find.getGender();
					if (gender.equals("M"))
						martyrS.getMaleMartyrRadioButton().setSelected(true);
					if (gender.equals("F"))
						martyrS.getFemaleMartyrRadioButton().setSelected(true);
					martyrS.getDistrictsCo().setValue(find.getDistrict());
					martyrS.getLocationsCo().setValue(find.getLocation());
					martyrS.getMassegeMartyrLabel().setText("change what you want..");

				} else
					martyrS.getMassegeMartyrLabel().setText(oldName + " not exist");
				martyrS.getUpateNameTextField().clear();

			});

			martyrS.getUpdateMartyrButton().setOnAction(e -> {
				if (martyrS.getNameTextField().getText().isBlank() || martyrS.getAgeTextField().getText().isBlank()
						|| martyrS.getDistrictsCo().getValue() == null || martyrS.getLocationsCo().getValue() == null) {
					martyrS.getMassegeMartyrLabel().setText("enter all the information!!!");
					return;
				}
				Martyr old = oldMartyr;
				if (oldMartyr == null)
					return;

				String name = martyrS.getNameTextField().getText().trim();
				int age = 0;
				try {
					age = Integer.parseInt(martyrS.getAgeTextField().getText().trim());
					if (age < 0 || age > 120) {
						martyrS.getMassegeMartyrLabel().setText("invaild age!!!");
						return;
					}
				} catch (NumberFormatException f) {
					martyrS.getMassegeMartyrLabel().setText("invaild age!!!");
					return;
				}
				String gender = "";
				if (martyrS.getMaleMartyrRadioButton().isSelected()) {
					gender = "M";
				} else if (martyrS.getFemaleMartyrRadioButton().isSelected()) {
					gender = "F";
				}
				Date date = new Date(dateS.getDateName().getText().trim());
				String district = martyrS.getDistrictsCo().getValue();
				String location = martyrS.getLocationsCo().getValue();
				Martyr newMartyr = new Martyr(name, date.getDate(), age, location, district, gender);
				if (m.updateMartyr(date, oldMartyr, newMartyr)) {
					martyrS.getMassegeMartyrLabel().setText(oldMartyr.getName() + " updated");

				} else
					martyrS.getMassegeMartyrLabel()
							.setText(oldMartyr.getName() + " not updated(new one already exist)");
				martyrS.clean();
			});
			dateS.getBackTOFirstScreen().setOnAction(e -> {
				dateStage.close();
				primaryStage.show();

			});
			writeInFileButton.setOnAction(e -> {
				writeOnFile(m.getTable());
				massege.setText("Saved!!!");
			});

		} catch (Exception e) {

		}
	}

	public static void main(String[] args) {
		try {
			launch(args);
		} catch (NullPointerException e) {
		}

	}

	public void read() {
		try {
			Scanner scan = new Scanner(file);
			scan.nextLine();
			String date = null;
			Martyr martyr = null;
			Date martyrDate = null;
			Location location = null;
			District district = null;
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				if (!line.isEmpty()) {
					String a[] = line.split(",");
					if (!a[2].equals("")) {
						String name = a[0];
						date = a[1];
						date = editDate(date);
						int age = Integer.parseInt(a[2]);
						String locationName = a[3];
						String districtName = a[4];
						String gender = a[5];
						martyr = new Martyr(name, date, age, locationName, districtName, gender);
						location = new Location(locationName);
						district = new District(districtName);
						martyrDate = new Date(date);
					}

					HNode<Date> add = m.getTable().add(martyrDate);
					if (add != null) {
						if (add.getData().getMartyrTree().find(martyr) == null) {
							add.getData().addMartyr(martyr);
						}
					}

					if (m.getDistrictList().find(district) != null) {
						if (m.getDistrictList().find(district).getData().getLocationList().find(location) != null) {
						} else
							m.getDistrictList().find(district).getData().addLocation(location);
					} else if (m.getDistrictList().find(district) == null) {
						m.getDistrictList().insert(district);
						district.addLocation(location);
					}
				}
			}
			Date d = new Date("01/20/2024");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public VBox firstScreen() {
		vb = new VBox(20);
		massege = new Label();
		dateScreen = new Button(" go to date screen!!! ");
		dateScreen.setDisable(true);
		dateScreen.setStyle("-fx-text-fill:grey;-fx-font-weight: bold;");
		writeInFileButton = new Button("sava the changes to file!!!");
		writeInFileButton.setStyle("-fx-text-fill:grey;-fx-font-weight: bold;");
		writeInFileButton.setDisable(true);
		fileButton = new Button("        choose a file...       ");
		fileButton.setStyle("-fx-text-fill:grey;-fx-font-weight: bold;");
		pray = new Label("{وَمَا النَّصْرُ إِلَّا مِنْ عِنْدِ اللَّهِ الْعَزِيزِ الْحَكِيمِ}");
		pray.setFont(new Font(15));
		pray.setStyle("-fx-text-fill:white;-fx-font-weight: bold;-fx-font-size: 12pt;");
		stage = new Stage();
		fileChooser = new FileChooser();
		// create file chooser and put the details
		fileButton.setOnAction(e -> {
			fileChooser.setInitialDirectory(new File("C:\\"));
			fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("csv file", "*.csv"));
			selectedFile = fileChooser.showOpenDialog(stage);
			try {
				file = new File(selectedFile.getPath());
				dateScreen.setDisable(false);
				writeInFileButton.setDisable(false);
				fileButton.setDisable(true);
				read();

				SNode<District> districts = m.getDistrictList().firstItem();
				while (districts != null) {
					martyrS.getDistrictsCo().getItems().add(districts.getData().getDistrict());
					districts = districts.getNext();
				}
				massege.setText("");

			} catch (NullPointerException e1) {
				massege.setText("you should choose file!!!");
				massege.setStyle("-fx-font-weight: bold;-fx-font-size: 12pt;");
				massege.setTextFill(Color.RED);
			}

		});
		vb.setAlignment(Pos.CENTER);
		vb.getChildren().addAll(pray, fileButton, dateScreen, writeInFileButton, massege);
		return vb;

	}

	public void writeOnFile(HashTabel<Date> table) {
		writeInFile = new File("DataUpdate.csv");
		try (PrintWriter write = new PrintWriter(writeInFile)) {
			write.println("Name" + "," + "event" + "," + "Age" + "," + "location" + "," + "district" + "," + "gender");
			for (int i = 0; i < table.getM(); i++) {
				if (table.getTable()[i] != null && table.getTable()[i].getFlag() == Flag.F
						&& table.getTable()[i].getData().getMartyrTree().takeRoot() != null)
					allTheMartyrs(table.getTable()[i], table.getTable()[i].getData().getMartyrTree().takeRoot(), write);
			}
		} catch (FileNotFoundException e) {
			massege.setText("Close the file");
		}

	}

	public void allTheMartyrs(HNode<Date> date, TNode<Martyr> martyr, PrintWriter write) {
		if (martyr == null)
			return;
		write.println((martyr.getData().getName() + "," + martyr.getData().getDate() + "," + martyr.getData().getAge()
				+ "," + martyr.getData().getLocation() + "," + martyr.getData().getDistrict() + ","
				+ martyr.getData().getGender()));
		allTheMartyrs(date, martyr.getLeft(), write);
		allTheMartyrs(date, martyr.getRight(), write);
	}

	// method to edit the date to be like date picker date
	public String editDate(String date) {
		String[] newDate = date.split("/");
		if (newDate.length == 3) {
			String day = null;
			if (newDate[0].length() == 1) {
				day = "0" + newDate[0];
			} else {
				day = newDate[0];
			}
			String month = null;
			if (newDate[1].length() == 1) {
				month = "0" + newDate[1];
			} else {
				month = newDate[1];
			}
			return day + "/" + month + "/" + newDate[2];
		}
		return date;
	}

}

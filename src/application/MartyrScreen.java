package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class MartyrScreen {
	private Label martyrLabel;

	private BorderPane bpMartyr;

	private GridPane gpMartyr;

	private Label martyrName;
	private Label martyrAge;
	private Label martyrGender;
	private Label martyrDistrict;
	private Label martyrLocation;

	private TextField nameTextField;

	private HBox hbRadio;
	private RadioButton maleMartyrRadioButton;
	private RadioButton femaleMartyrRadioButton;
	private TextField ageTextField;

	private HBox hbButtonsMartyr;
	private Button addMartyrButton;
	private Button deleteMartyrButton;

	private VBox vupdate;
	private Button doneUpdeteButton;
	private TextField upateNameTextField;
	private Button updateMartyrButton;
	private Button sortMartyrButton;

	private Button okMartyrButton;
	private Label massegeMartyrLabel;

	private ToggleGroup genderGroup;

	private ComboBox<String> districtsCo;
	private ComboBox<String> locationsCo;

	VBox vsh;
	private Button size;
	private Button height;
	private Button print;

	private TextField sizeField;
	private TextField heightField;

	public MartyrScreen() {

		bpMartyr = new BorderPane();

		gpMartyr = new GridPane(10, 10);

		martyrName = new Label("the name:");
		martyrName.setStyle("-fx-font-weight: bold;-fx-font-size: 8pt;");
		martyrAge = new Label("the age:");
		martyrAge.setStyle("-fx-font-weight: bold;-fx-font-size: 8pt;");
		martyrGender = new Label("the gender:");
		martyrGender.setStyle("-fx-font-weight: bold;-fx-font-size: 8pt;");
		martyrDistrict = new Label("district:");
		martyrDistrict.setStyle("-fx-font-weight: bold;-fx-font-size: 8pt;");
		martyrLocation = new Label("location:");
		martyrLocation.setStyle("-fx-font-weight: bold;-fx-font-size: 8pt;");

		gpMartyr.add(martyrName, 0, 0);
		gpMartyr.add(martyrAge, 0, 1);
		gpMartyr.add(martyrGender, 0, 2);
		gpMartyr.add(martyrDistrict, 0, 3);
		gpMartyr.add(martyrLocation, 0, 4);

		nameTextField = new TextField();
		nameTextField.setPromptText("enter name!!!");
		ageTextField = new TextField();
		ageTextField.setPromptText("enter age!!!");

		genderGroup = new ToggleGroup();
		hbRadio = new HBox(15);
		maleMartyrRadioButton = new RadioButton("M");
		maleMartyrRadioButton.setSelected(true);
		maleMartyrRadioButton.setToggleGroup(genderGroup);
		femaleMartyrRadioButton = new RadioButton("F");
		femaleMartyrRadioButton.setToggleGroup(genderGroup);
		hbRadio.getChildren().addAll(maleMartyrRadioButton, femaleMartyrRadioButton);

		districtsCo = new ComboBox<>();
		locationsCo = new ComboBox<>();

		gpMartyr.add(nameTextField, 1, 0);
		gpMartyr.add(ageTextField, 1, 1);
		gpMartyr.add(hbRadio, 1, 2);
		gpMartyr.add(districtsCo, 1, 3);
		gpMartyr.add(locationsCo, 1, 4);

		gpMartyr.setAlignment(Pos.CENTER);

		bpMartyr.setCenter(gpMartyr);

		martyrLabel = new Label("Martyr Screen");
		martyrLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 30));
		martyrLabel.setAlignment(Pos.CENTER);

		bpMartyr.setTop(martyrLabel);
		bpMartyr.setMargin(martyrLabel, new Insets(20));
		bpMartyr.setAlignment(martyrLabel, Pos.CENTER);

		hbButtonsMartyr = new HBox(20);
		addMartyrButton = new Button("add");
		deleteMartyrButton = new Button("delete");
		updateMartyrButton = new Button("update");
		sortMartyrButton = new Button("sort");

		vupdate = new VBox(5);
		upateNameTextField = new TextField();
		upateNameTextField.setPromptText("OldName");
		upateNameTextField.setPrefWidth(12);
		doneUpdeteButton = new Button("ok");
		vupdate.getChildren().addAll(upateNameTextField, doneUpdeteButton, updateMartyrButton);

		vsh = new VBox(4);
		sizeField = new TextField();
		sizeField.setPrefWidth(9);
		size = new Button("size");
		height = new Button("height");
		print = new Button("print");

		vsh.getChildren().addAll(sizeField, size);

		VBox vh = new VBox(4);
		heightField = new TextField();
		heightField.setPrefWidth(9);
		vh.getChildren().addAll(heightField, height);

		hbButtonsMartyr.getChildren().addAll(addMartyrButton, deleteMartyrButton, vupdate, sortMartyrButton, vsh, vh,
				print);
		hbButtonsMartyr.setAlignment(Pos.CENTER);

		bpMartyr.setBottom(hbButtonsMartyr);
		bpMartyr.setMargin(hbButtonsMartyr, new Insets(20));

		okMartyrButton = new Button(" ok ");
		massegeMartyrLabel = new Label();
		massegeMartyrLabel.setStyle("-fx-font-weight: bold;-fx-font-size: 8pt;");
		massegeMartyrLabel.setTextFill(Color.RED);
		gpMartyr.add(massegeMartyrLabel, 1, 5);

		bpMartyr.setRight(okMartyrButton);
		bpMartyr.setAlignment(okMartyrButton, Pos.BOTTOM_RIGHT);

	}

	public BorderPane martyrPane() {
		return bpMartyr;

	}
	public void clean() {
		nameTextField.clear();
		ageTextField.clear();
	}

	public TextField getNameTextField() {
		return nameTextField;
	}

	public void setNameTextField(TextField nameTextField) {
		this.nameTextField = nameTextField;
	}

	public RadioButton getMaleMartyrRadioButton() {
		return maleMartyrRadioButton;
	}

	public void setMaleMartyrRadioButton(RadioButton maleMartyrRadioButton) {
		this.maleMartyrRadioButton = maleMartyrRadioButton;
	}

	public RadioButton getFemaleMartyrRadioButton() {
		return femaleMartyrRadioButton;
	}

	public void setFemaleMartyrRadioButton(RadioButton femaleMartyrRadioButton) {
		this.femaleMartyrRadioButton = femaleMartyrRadioButton;
	}

	public TextField getAgeTextField() {
		return ageTextField;
	}

	public void setAgeTextField(TextField ageTextField) {
		this.ageTextField = ageTextField;
	}

	public Button getAddMartyrButton() {
		return addMartyrButton;
	}

	public void setAddMartyrButton(Button addMartyrButton) {
		this.addMartyrButton = addMartyrButton;
	}

	public Button getDeleteMartyrButton() {
		return deleteMartyrButton;
	}

	public void setDeleteMartyrButton(Button deleteMartyrButton) {
		this.deleteMartyrButton = deleteMartyrButton;
	}

	public Button getDoneUpdeteButton() {
		return doneUpdeteButton;
	}

	public void setDoneUpdeteButton(Button doneUpdeteButton) {
		this.doneUpdeteButton = doneUpdeteButton;
	}

	public TextField getUpateNameTextField() {
		return upateNameTextField;
	}

	public void setUpateNameTextField(TextField upateNameTextField) {
		this.upateNameTextField = upateNameTextField;
	}

	public Button getUpdateMartyrButton() {
		return updateMartyrButton;
	}

	public void setUpdateMartyrButton(Button updateMartyrButton) {
		this.updateMartyrButton = updateMartyrButton;
	}

	public Button getSortMartyrButton() {
		return sortMartyrButton;
	}

	public void setSortMartyrButton(Button sortMartyrButton) {
		this.sortMartyrButton = sortMartyrButton;
	}

	public Button getOkMartyrButton() {
		return okMartyrButton;
	}

	public void setOkMartyrButton(Button okMartyrButton) {
		this.okMartyrButton = okMartyrButton;
	}

	public ComboBox<String> getDistrictsCo() {
		return districtsCo;
	}

	public void setDistrictsCo(ComboBox<String> districtsCo) {
		this.districtsCo = districtsCo;
	}

	public ComboBox<String> getLocationsCo() {
		return locationsCo;
	}

	public void setLocationsCo(ComboBox<String> locationsCo) {
		this.locationsCo = locationsCo;
	}

	public Button getSize() {
		return size;
	}

	public void setSize(Button size) {
		this.size = size;
	}

	public Button getHeight() {
		return height;
	}

	public void setHeight(Button height) {
		this.height = height;
	}

	public Button getPrint() {
		return print;
	}

	public void setPrint(Button print) {
		this.print = print;
	}

	public TextField getSizeField() {
		return sizeField;
	}

	public void setSizeField(TextField sizeField) {
		this.sizeField = sizeField;
	}

	public TextField getHeightField() {
		return heightField;
	}

	public void setHeightField(TextField heightField) {
		this.heightField = heightField;
	}

	public Label getMartyrLabel() {
		return martyrLabel;
	}

	public void setMartyrLabel(Label martyrLabel) {
		this.martyrLabel = martyrLabel;
	}

	public Label getMassegeMartyrLabel() {
		return massegeMartyrLabel;
	}

	public void setMassegeMartyrLabel(Label massegeMartyrLabel) {
		this.massegeMartyrLabel = massegeMartyrLabel;
	}

}

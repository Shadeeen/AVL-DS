package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class DateScreen {
	private BorderPane bp;

	private VBox vbscreen;

	private Label dateName;

	private HBox hbconect;

	private VBox vbLabels;
	private Label ageAverage;
	private Label male;
	private Label female;
	private Label total;
	private Label maxLocation;
	private Label maxDistrict;

	private VBox vbresult;
	private Label ageAvaregeResult;
	private Label maleResult;
	private Label femaleResult;
	private Label totalResult;
	private Label maxLocationResult;
	private Label maxDistrictResult;

	private HBox hbnextPrev;
	private Button nextDateButton;
	private Button prevDateButton;

	private Button backTOFirstScreen;

	private VBox radioBox;

	private VBox addBox;
	private ToggleGroup DateGroup;
	private RadioButton addDateRadioButton;
	private RadioButton deleteDateRadioButton;
	private RadioButton updateDateRadioButton;
	private DatePicker addDatePicker;
	private Label doneAddDateLabel;
	private DatePicker updateDatePicker;
	private DatePicker updateToDatePicker;

	private Button loadMartyrScreen;

	private Button printDatesWithEmpty;
	private Button printDates;

	public DateScreen() {
		bp = new BorderPane();

		vbscreen = new VBox(5);

		dateName = new Label("Date");
		dateName.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 30));
		dateName.setAlignment(Pos.CENTER);

		hbconect = new HBox(20);

		vbLabels = new VBox(10);
		ageAverage = new Label("->age average:");
		ageAverage.setStyle("-fx-font-weight: bold;-fx-font-size: 10pt;");
		male = new Label("->number of males:");
		male.setStyle("-fx-font-weight: bold;-fx-font-size: 10pt;");
		female = new Label("->number of females:");
		female.setStyle("-fx-font-weight: bold;-fx-font-size: 10pt;");
		total = new Label("->total martyrs:");
		total.setStyle("-fx-font-weight: bold;-fx-font-size: 10pt;");
		maxDistrict = new Label("->max District:");
		maxDistrict.setStyle("-fx-font-weight: bold;-fx-font-size: 10pt;");
		maxLocation = new Label("->max location:");
		maxLocation.setStyle("-fx-font-weight: bold;-fx-font-size: 10pt;");

		printDates = new Button("print Dates");
		printDates.setShape(new Circle(30));
		printDates.setStyle("-fx-text-fill: black;-fx-font-size: 8pt;");

		printDatesWithEmpty = new Button("print All Dates");
		printDatesWithEmpty.setShape(new Circle(30));
		printDatesWithEmpty.setStyle("-fx-text-fill: black;-fx-font-size: 8pt;");

		vbLabels.getChildren().addAll(total, ageAverage, male, female, maxDistrict, maxLocation, printDates,
				printDatesWithEmpty);

		vbresult = new VBox(10);
		ageAvaregeResult = new Label();
		ageAvaregeResult.setStyle("-fx-font-weight: bold;-fx-font-size: 10pt;");
		maleResult = new Label();
		maleResult.setStyle("-fx-font-weight: bold;-fx-font-size: 10pt;");
		femaleResult = new Label();
		femaleResult.setStyle("-fx-font-weight: bold;-fx-font-size: 10pt;");
		totalResult = new Label();
		totalResult.setStyle("-fx-font-weight: bold;-fx-font-size: 10pt;");
		maxDistrictResult = new Label();
		maxDistrictResult.setStyle("-fx-font-weight: bold;-fx-font-size: 10pt;");
		maxLocationResult = new Label();
		maxLocationResult.setStyle("-fx-font-weight: bold;-fx-font-size: 10pt;");

		vbresult.getChildren().addAll(totalResult, ageAvaregeResult, maleResult, femaleResult, maxDistrictResult,
				maxLocationResult);

		hbconect.getChildren().addAll(vbLabels, vbresult);

		nextDateButton = new Button("  next-->  ");
		nextDateButton.setShape(new Circle(30));
		nextDateButton.setStyle("-fx-text-fill: black;-fx-font-size: 8pt;");

		prevDateButton = new Button("<--previous");
		prevDateButton.setShape(new Circle(10));
		prevDateButton.setStyle("-fx-text-fill: black;-fx-font-size: 8pt;");

		hbnextPrev = new HBox(30);
		hbnextPrev.getChildren().addAll(prevDateButton, nextDateButton);
		hbnextPrev.setAlignment(Pos.CENTER);

		loadMartyrScreen = new Button("to Martyrs");
		loadMartyrScreen.setStyle("-fx-color:red;-fx-text-fill: black;-fx-font-size: 8pt;");
		loadMartyrScreen.setShape(new Polygon(30, 20, 20, 50, 10, 20));

		vbscreen.getChildren().addAll(loadMartyrScreen, dateName, hbconect, hbnextPrev);
		vbscreen.setAlignment(Pos.CENTER);

		bp.setMargin(vbscreen, new Insets(5));

		bp.setCenter(vbscreen);

		radioBox = new VBox(10);
		DateGroup = new ToggleGroup();
		addDateRadioButton = new RadioButton("add new Date");
		addDateRadioButton.setToggleGroup(DateGroup);
		addDateRadioButton.setStyle("-fx-font-weight: bold;-fx-font-size: 10pt;");
		deleteDateRadioButton = new RadioButton("delete this Date");
		deleteDateRadioButton.setToggleGroup(DateGroup);
		deleteDateRadioButton.setStyle("-fx-font-weight: bold;-fx-font-size: 10pt;");
		updateDateRadioButton = new RadioButton("update this Date");
		updateDateRadioButton.setToggleGroup(DateGroup);
		updateDateRadioButton.setStyle("-fx-font-weight: bold;-fx-font-size: 10pt;");

		addBox = new VBox(5);
		addDatePicker = new DatePicker();
		addDatePicker.setPromptText("to add and delete");
		addDatePicker.setEditable(false);
		doneAddDateLabel = new Label();
		doneAddDateLabel.setStyle("-fx-font-weight: bold;-fx-font-size: 8pt;");

		updateDatePicker = new DatePicker();
		updateDatePicker.setEditable(false);
		updateDatePicker.setPromptText("update date");
		updateToDatePicker = new DatePicker();
		updateToDatePicker.setPromptText(" To...");
		updateToDatePicker.setEditable(false);
		addBox.getChildren().addAll(deleteDateRadioButton, addDateRadioButton, addDatePicker,updateDateRadioButton, updateDatePicker,
				updateToDatePicker, doneAddDateLabel);
		

		backTOFirstScreen = new Button("back");
		backTOFirstScreen.setStyle("-fx-text-fill: black;-fx-font-size: 10pt;");
		backTOFirstScreen.setAlignment(Pos.CENTER);

		radioBox.getChildren().add(addBox);
		radioBox.setAlignment(Pos.CENTER_LEFT);

		bp.setTop(backTOFirstScreen);
		bp.setRight(radioBox);
		bp.setMargin(radioBox, new Insets(20));

	}

	public BorderPane datePane() {
		bp.setStyle("-fx-background:white");
		return bp;

	}

	public Label getDateName() {
		return dateName;
	}

	public void setDateName(Label dateName) {
		this.dateName = dateName;
	}

	public Label getAgeAvaregeResult() {
		return ageAvaregeResult;
	}

	public void setAgeAvaregeResult(Label ageAvaregeResult) {
		this.ageAvaregeResult = ageAvaregeResult;
	}

	public Label getMaleResult() {
		return maleResult;
	}

	public void setMaleResult(Label maleResult) {
		this.maleResult = maleResult;
	}

	public Label getFemaleResult() {
		return femaleResult;
	}

	public void setFemaleResult(Label femaleResult) {
		this.femaleResult = femaleResult;
	}

	public Label getTotalResult() {
		return totalResult;
	}

	public void setTotalResult(Label totalResult) {
		this.totalResult = totalResult;
	}

	public Label getMaxLocationResult() {
		return maxLocationResult;
	}

	public void setMaxLocationResult(Label maxLocationResult) {
		this.maxLocationResult = maxLocationResult;
	}

	public Label getMaxDistrictResult() {
		return maxDistrictResult;
	}

	public void setMaxDistrictResult(Label maxDistrictResult) {
		this.maxDistrictResult = maxDistrictResult;
	}

	public Button getNextDateButton() {
		return nextDateButton;
	}

	public void setNextDateButton(Button nextDateButton) {
		this.nextDateButton = nextDateButton;
	}

	public Button getPrevDateButton() {
		return prevDateButton;
	}

	public void setPrevDateButton(Button prevDateButton) {
		this.prevDateButton = prevDateButton;
	}

	public Button getBackTOFirstScreen() {
		return backTOFirstScreen;
	}

	public void setBackTOFirstScreen(Button backTOFirstScreen) {
		this.backTOFirstScreen = backTOFirstScreen;
	}

	public RadioButton getAddDateRadioButton() {
		return addDateRadioButton;
	}

	public void setAddDateRadioButton(RadioButton addDateRadioButton) {
		this.addDateRadioButton = addDateRadioButton;
	}

	public RadioButton getDeleteDateRadioButton() {
		return deleteDateRadioButton;
	}

	public void setDeleteDateRadioButton(RadioButton deleteDateRadioButton) {
		this.deleteDateRadioButton = deleteDateRadioButton;
	}

	public RadioButton getUpdateDateRadioButton() {
		return updateDateRadioButton;
	}

	public void setUpdateDateRadioButton(RadioButton updateDateRadioButton) {
		this.updateDateRadioButton = updateDateRadioButton;
	}

	public DatePicker getAddDatePicker() {
		return addDatePicker;
	}

	public void setAddDatePicker(DatePicker addDatePicker) {
		this.addDatePicker = addDatePicker;
	}

	public Label getDoneAddDateLabel() {
		return doneAddDateLabel;
	}

	public void setDoneAddDateLabel(Label doneAddDateLabel) {
		this.doneAddDateLabel = doneAddDateLabel;
	}

	public Button getLoadMartyrScreen() {
		return loadMartyrScreen;
	}

	public void setLoadMartyrScreen(Button loadMartyrScreen) {
		this.loadMartyrScreen = loadMartyrScreen;
	}

	public Button getPrintDatesWithEmpty() {
		return printDatesWithEmpty;
	}

	public void setPrintDatesWithEmpty(Button printDatesWithEmpty) {
		this.printDatesWithEmpty = printDatesWithEmpty;
	}

	public Button getPrintDates() {
		return printDates;
	}

	public void setPrintDates(Button printDates) {
		this.printDates = printDates;
	}

	public DatePicker getUpdateDatePicker() {
		return updateDatePicker;
	}

	public void setUpdateDatePicker(DatePicker updateDatePicker) {
		this.updateDatePicker = updateDatePicker;
	}

	public DatePicker getUpdateToDatePicker() {
		return updateToDatePicker;
	}

	public void setUpdateToDatePicker(DatePicker updateToDatePicker) {
		this.updateToDatePicker = updateToDatePicker;
	}

}

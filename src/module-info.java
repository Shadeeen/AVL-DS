module Project3Data {
	requires javafx.controls;
	
	//opens application to javafx.graphics, javafx.fxml;
	 opens application to javafx.base, javafx.controls;
	 exports application;
}

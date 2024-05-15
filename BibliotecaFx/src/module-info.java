module BibliotecaFx {
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.fxml;
	requires javafx.base;
	requires java.sql;
	
	opens model to javafx.base;
	opens controller to javafx.fxml;
	opens application to javafx.graphics, javafx.fxml;
}

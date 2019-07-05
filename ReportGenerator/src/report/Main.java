package report;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import report.util.Excel;
import report.util.FileCount;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;


public class Main extends Application {

	private Stage primaryStage;
	private AnchorPane mainLayout;
	//private TabPane mainLayout;
		
	public Main() {
		FileCount.fileRead();
		//Excel e = new Excel();
		//e.read();
	}
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Student Report Generator");
		showMainView();
	}
	
	private void showMainView() throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("GUImain.fxml"));
		//loader.setLocation(Main.class.getResource("Tabs.fxml"));
		mainLayout = loader.load();
		Scene scene = new Scene(mainLayout);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
	
}

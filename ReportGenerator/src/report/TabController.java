package report;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import report.util.Const;
import report.util.Excel;

public class TabController {
	@FXML
    private TabPane tabPane ;
	@FXML
	private AnchorPane anchorPane2;


	@FXML
    private void initialize() {
		
		initializeTabPane();
		//reportTextArea.setWrapText(true);
		//initializeGridPane();
		
	}
	
private void initializeTabPane() 
	{
	for(int i=0; i<Const.excelFiles.size(); i++)
	{
		Excel.read(Const.excelFiles.get(i));
		FXMLLoader loader = new FXMLLoader(getClass().getResource("GUIMain.fxml"));
		Tab tab = new Tab(Const.excelFiles.get(i));
		try {
			tab.setContent(loader.load());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		tabPane.getTabs().add(tab);
	
	}
}
}

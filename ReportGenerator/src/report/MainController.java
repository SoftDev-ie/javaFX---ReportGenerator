package report;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.text.StrSubstitutor;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import report.model.Item;
import report.util.Const;
import report.util.Excel;
import report.util.FileCount;

public class MainController {
	@FXML
    private TabPane tabPane ;	
	@FXML
	private AnchorPane anchorPane;
	@FXML
	private TextField firstNameTextField;
	@FXML
	private TextField lastNameTextField;
	@FXML
	private Label reportLabel;
	@FXML
	private TextArea reportTextArea;
	@FXML
	private Label pathLabel;
	@FXML
	private Label excelLabel;
	@FXML
	private Button excelButton;
	
	private ArrayList<GridPane> gridList = new ArrayList<>();
	private int amount;
	@FXML
    private void initialize() {
		
		initializeTabPane();
		reportTextArea.setWrapText(true);
		
		
	}
	private void initializeTabPane() {
//		pathLabel.setText(Const.path);
//		excelLabel.setText(Const.excelFiles.toString());
//		pathLabel.setVisible(true);
//		excelLabel.setVisible(true);
		
//		for(int i=0; i<Const.excelFiles.size(); i++)
//		{
//			ObservableList<Item> itemList = Excel.read(Const.excelFiles.get(i));
//			//FXMLLoader loader = new FXMLLoader(getClass().getResource("GUIMain.fxml"));
//			Tab tab = new Tab(Const.excelFiles.get(i));
//			try {
//				tab.setContent(initializeGridPane(itemList));
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			tabPane.getTabs().add(tab);
//		
//		}
}
	
	private GridPane initializeGridPane(ObservableList<Item> itemData) {
		GridPane iitemGridPane = new GridPane();
		
		for(int i=0; i<itemData.size(); i++){
            Label itemLabel = new Label();
            itemLabel.setText(itemData.get(i).getTitle());
            itemLabel.setAlignment(Pos.CENTER);
            itemLabel.setMinWidth(120);
            GridPane.setHalignment(itemLabel, HPos.RIGHT);
            GridPane.setFillWidth(itemLabel, true);
            itemLabel.setMaxWidth(Double.MAX_VALUE);
            ComboBox<String> markComboBox = new ComboBox<>();
            markComboBox.getItems().addAll(Const.CHOICES);
            markComboBox.setMinWidth(80);
            GridPane.setHalignment(markComboBox, HPos.RIGHT);
            GridPane.setFillWidth(markComboBox, true);
            
            TextField customText1 = new TextField();
            GridPane.setHgrow(customText1, Priority.ALWAYS);
            TextField customText2 = new TextField();
            GridPane.setHgrow(customText2, Priority.ALWAYS);
            TextField customText3 = new TextField();
            GridPane.setHgrow(customText3, Priority.ALWAYS);
            
            //add them to the GridPane
            iitemGridPane.add(itemLabel, 0, i); //  (child, columnIndex, rowIndex)
            iitemGridPane.add(markComboBox , 1, i);
            iitemGridPane.add(customText1,2, i);
            iitemGridPane.add(customText2,3, i);
            iitemGridPane.add(customText3,4, i);

            // margins 
            GridPane.setMargin(itemLabel, new Insets(5));
            GridPane.setMargin(markComboBox, new Insets(5));
            GridPane.setMargin(customText1, new Insets(5));
            GridPane.setMargin(customText2, new Insets(5));
            GridPane.setMargin(customText3, new Insets(5));
         }
		//iitemGridPane.setAlignment(Pos.CENTER);
		gridList.add(iitemGridPane);
		return iitemGridPane;
	}
	
	@FXML
		private void generateReport() {
			String name = firstNameTextField.getText();
			String lastName = lastNameTextField.getText();
			reportLabel.setText("Student Report for: " + name + " " + lastName);
			
			String text = "";
			
			int start = 0;
			for(int a=0;a < gridList.size();a++) {
				int counter = 0;
				amount=start + Const.size.get(a);

				GridPane iitemGridPane = gridList.get(a);
				
				for(int i=start; i<amount; i++){
					String itemName = "", choice = null;
					String custom1 = "", custom2 = "", custom3 = "";
					if(iitemGridPane.getChildren().get(counter) instanceof Label) {
						Label l = (Label)iitemGridPane.getChildren().get(counter);
						itemName = l.getText();
					}
					if(iitemGridPane.getChildren().get(counter + 1) instanceof ComboBox) {
						ComboBox<String> c = (ComboBox<String>)iitemGridPane.getChildren().get(counter + 1);
						choice = c.getValue();
					}
					if(iitemGridPane.getChildren().get(counter + 2) instanceof TextField) {
						TextField t = (TextField)iitemGridPane.getChildren().get(counter + 2);
						custom1 = t.getText();
					}
					if(iitemGridPane.getChildren().get(counter + 3) instanceof TextField) {
						TextField t = (TextField)iitemGridPane.getChildren().get(counter + 3);
						custom2 = t.getText();
					}
					if(iitemGridPane.getChildren().get(counter + 4) instanceof TextField) {
						TextField t = (TextField)iitemGridPane.getChildren().get(counter + 4);
						custom3 = t.getText();
					}
					Item it = Const.itemData.get(i);
					if(it.getTitle() != null &&it.getTitle().equals(itemName) && choice != null) {
						text += it.getValues().get(Const.CHOICES.indexOf(choice)) + "\n";
						if(!custom1.equals("") && custom1 != null) {
							text += custom1 + "\n";
						}
						if(!custom2.equals("") && custom2 != null) {
							text += custom2 + "\n";
						}
						if(!custom3.equals("") && custom3 != null) {
							text += custom3 + "\n";
						}
					}
					counter+=5;
				}
			 start = amount;
			}
			Map placeholder = new HashMap();
			placeholder.put("name", name);
			StrSubstitutor sub = new StrSubstitutor(placeholder);
			String resolvedText = sub.replace(text);
			reportTextArea.setText(resolvedText);	
	}

	@FXML
	private void clearFields() {
		firstNameTextField.setText("");
		lastNameTextField.setText("");
		reportLabel.setText("Student Report for: ");
		reportTextArea.setText("");
		tabPane.getTabs().clear();
		gridList.clear();
		initializeTabPane();
		
		
	}
	@FXML
	private void saveReport() {
		if(!reportTextArea.getText().equals("") && reportTextArea.getText() != null) {
			FileChooser fileChooser = new FileChooser();
			 
            //Set extension filter
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
            fileChooser.getExtensionFilters().add(extFilter);
           
            Stage stage = (Stage) anchorPane.getScene().getWindow();
            //Show save file dialog
            File file = fileChooser.showSaveDialog(stage);
           
            if(file != null){
                saveFile(reportTextArea.getText(), file);
            }
		}
	}
	private void saveFile(String content, File file){
        try {
            FileWriter fileWriter = null;
            
            fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException ex) {
            System.out.println("Cannot Save File");
        }
        
    }
	@FXML
	private void getExcel() {
		System.out.println("working");
		Stage stage = (Stage) anchorPane.getScene().getWindow();
		 DirectoryChooser directoryChooser = new DirectoryChooser();
		 File selectedDirectory = directoryChooser.showDialog(stage);
		 if(selectedDirectory != null) {
			 Const.path = selectedDirectory.getAbsolutePath();
			 FileCount.fileRead(selectedDirectory.getAbsolutePath());
			 
			 for(int i=0; i<Const.excelFiles.size(); i++)
				{
					ObservableList<Item> itemList = Excel.read(Const.excelFiles.get(i));
					//FXMLLoader loader = new FXMLLoader(getClass().getResource("GUIMain.fxml"));
					Tab tab = new Tab(Const.excelFiles.get(i));
					try {
						tab.setContent(initializeGridPane(itemList));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					tabPane.getTabs().add(tab);
				
				}
		 }
		 
		 //pathLabel.setText(selectedDirectory.getAbsolutePath());

	}
}

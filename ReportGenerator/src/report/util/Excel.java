package report.util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import report.model.Item;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Excel {

	//private static final String FILE_PATH = "C:\\Users\\Chris\\Desktop\\schoolprogram";
	private static final String FILE_PATH = System.getProperty("user.dir");
	
    
	
    public static ObservableList<Item> read(String fileName) {
    	ObservableList<Item> itemData = FXCollections.observableArrayList();
        try {
            FileInputStream excelFile = new FileInputStream(new File(FILE_PATH+ File.separator +fileName));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();
            
            while (iterator.hasNext()) {
            	String title = null;
            	ArrayList<String> values = new ArrayList<>();
                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();
                boolean firstCol = true;
                while (cellIterator.hasNext()) {

                    Cell currentCell = cellIterator.next();
                    if(firstCol) {
                    	title = currentCell.getStringCellValue();
                    	firstCol = false;
                    }
                    else {
                    	values.add(currentCell.getStringCellValue());
                    } 
                }
                if(title != null && !title.equals(""))
                	itemData.add(new Item(title, values));
            }
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            //Excel file not found, display error dialog
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Excel document not found");

            alert.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Const.itemData.addAll(itemData);
        Const.size.add(itemData.size());
        return itemData;
    }
}
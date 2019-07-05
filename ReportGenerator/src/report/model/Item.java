package report.model;

import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Item {
	private final StringProperty title;
	private ArrayList<String> values;
	
	public Item(String title, ArrayList<String> values) {
		this.title = new SimpleStringProperty(title);
		this.values = values;
	}
	
	public String getTitle() {
		return title.get();
	}
	
	public void setTitle(String title) {
		this.title.set(title);
	}
	
	public StringProperty titleProperty() {
		return title;
	}
	
	public ArrayList<String> getValues(){
		return values;
	}
	
	public void setValues(ArrayList<String> values) {
		this.values = values;
	}
}

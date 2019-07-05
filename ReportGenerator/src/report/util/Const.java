package report.util;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import report.model.Item;

public class Const {
	public static ObservableList<Item> itemData = FXCollections.observableArrayList();
	public static final ArrayList<String> CHOICES = new ArrayList<>(Arrays.asList("100-80", "79-70", "69-60", "59-50", "49-40", "<39"));
	public static ArrayList<String> excelFiles = new ArrayList<>();
	public static ArrayList<Integer> size = new ArrayList<>();
}

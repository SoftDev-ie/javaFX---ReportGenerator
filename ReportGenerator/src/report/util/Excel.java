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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;

public class Excel {

	//private static final String FILE_PATH = System.getProperty("user.dir");
	//private static final String FILE_PATH = System.getProperty("user.home")+File.separator+"Desktop"+File.separator+"schoolprogram";
    
	
    public static ObservableList<Item> read(String fileName) {
    	ObservableList<Item> itemData = FXCollections.observableArrayList();
        try {
            FileInputStream excelFile = new FileInputStream(new File(Const.path+ File.separator +fileName));
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
/*
public class ApplicationDirectories {
    
    private static final Path config;

    private static final Path data;

    private static final Path cache;

    static {
        String os = System.getProperty("os.name");
        String home = System.getProperty("user.home");

        if (os.contains("Mac")) {
            config = Paths.get(home, "Library", "Application Support");
            data = config;
            cache = config;
        } else if (os.contains("Windows")) {
            String version = System.getProperty("os.version");
            if (version.startsWith("5.")) {
                config = getFromEnv("APPDATA", false,
                    Paths.get(home, "Application Data"));
                data = config;
                cache = Paths.get(home, "Local Settings", "Application Data");
            } else {
                config = getFromEnv("APPDATA", false,
                    Paths.get(home, "AppData", "Roaming"));
                data = config;
                cache = getFromEnv("LOCALAPPDATA", false,
                    Paths.get(home, "AppData", "Local"));
            }
        } else {
            config = getFromEnv("XDG_CONFIG_HOME", true,
                Paths.get(home, ".config"));
            data = getFromEnv("XDG_DATA_HOME", true,
                Paths.get(home, ".local", "share"));
            cache = getFromEnv("XDG_CACHE_HOME", true,
                Paths.get(home, ".cache"));
        }
    }

    /** Prevents instantiation. */
   // private ApplicationDirectories() {
   // }

    /**
     * Retrieves a path from an environment variable, substituting a default
     * if the value is absent or invalid.
     *
     * @param envVar name of environment variable to read
     * @param mustBeAbsolute whether enviroment variable's value should be
     *                       considered invalid if it's not an absolute path
     * @param defaultPath default to use if environment variable is absent
     *                    or invalid
     *
     * @return environment variable's value as a {@code Path},
     *         or {@code defaultPath}
     */
  /*  private static Path getFromEnv(String envVar,
                                   boolean mustBeAbsolute,
                                   Path defaultPath) {
        Path dir;
        String envDir = System.getenv(envVar);
        if (envDir == null || envDir.isEmpty()) {
            dir = defaultPath;
        } else {
            dir = Paths.get(envDir);
            if (mustBeAbsolute && !dir.isAbsolute()) {
                dir = defaultPath;
            }
        }
        return dir;
    }*/

    /**
     * Returns directory where the native system expects an application
     * to store configuration files for the current user.  No attempt is made
     * to create the directory, and no checks are done to see if it exists.
     *
     * @param appName name of application
     */
  /*  public static Path configDir(String appName)
    {
        return config.resolve(appName);
    }*/
package application;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class StartupGUIController {
	public static String printerCSVPath;
	public static String tonerCSVPath;
	
	@FXML private Button printerCSVbutton;
	@FXML private Button tonerCSVbutton;
	@FXML private Label printerCSVdisplay;
	@FXML private Label tonerCSVdisplay;
	@FXML private Button continueButton;
	
	public void printerCSVbuttonPushed() {
		printerCSVPath = Main.fileBrowser();
		if(printerCSVPath == null)
			printerCSVdisplay.setText(getFileName(printerCSVPath));
		else
			printerCSVdisplay.setText("Default");
	}
	public void tonerCSVbuttonPushed() {
		tonerCSVPath = Main.fileBrowser();
		if(tonerCSVPath == null)
			tonerCSVdisplay.setText(getFileName(tonerCSVPath));
		else
			tonerCSVdisplay.setText("Default");
	}
	public void continuePressed(ActionEvent event) throws IOException {
		boolean bool1 = printerCSVLocate(printerCSVPath);
		boolean bool2 = tonerCSVLocate(tonerCSVPath);
		
		if(bool1 && bool2) {
			Parent root2 = FXMLLoader.load(getClass().getResource("Sample.fxml"));
			Scene mainScene = new Scene(root2);
			
			Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
			
			window.setScene(mainScene);
			window.show();
		}else {
			continueButton.setText("Invalid CSV(s)");
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			continueButton.setText("Continue");
		}
	}
	
	public String getFileName(String path) {
		File tempFile = new File(path);
		return tempFile.getName();
	}
	
	
	
	
	static public boolean printerCSVLocate(String location) {
		if(!(location == null)){
			String fName = new String();
			File f = new File(location);
			if(f.exists()) {
				fName = f.getName();
				if(fName.endsWith(".csv")) {
					if(Main.csvVerify(f, "printer")) {
						System.out.println("File exists and is a CSV. Content also verified.");
						Main.fileReader(f, "printer");
						return true;
					}else
						System.out.println("Error: File content mismatch.");
				}
				else
					System.out.println("Error: File is not a CSV.");
			}else {
				if(printerCSVPath.equals("")){
					System.out.println("Default printer CSV selected");
					Main.fileReader(new File("printers.csv"), "printer");
					return true;
				}else
					System.out.println("Error: File does not exist.");
			}
		//Takes a file location
		//locates file and returns true
		//or returns false if file is non-existant or is not a CSV
		return false;
		}else {
			System.out.println("Default printer CSV selected");
			Main.fileReader(new File("printers.csv"), "printer");
			return true;
		}
	}
	static public boolean tonerCSVLocate(String location) {
		if(!(location == null)){
			String fName = new String();
			File f = new File(location);
			if(f.exists()) {
				fName = f.getName();
				if(fName.endsWith(".csv")) {
					if(Main.csvVerify(f, "toner")) {
						System.out.println("File exists and is a CSV. Content also verified.");
						Main.fileReader(f, "toner");
						return true;
					}else
						System.out.println("Error: File content mismatch.");
				}
				else
					System.out.println("Error: File is not a CSV.");
			}else {
				if(tonerCSVPath.equals("")){
					System.out.println("Default toner CSV selected");
					Main.fileReader(new File("toners.csv"), "toner");
					return true;
				}else
					System.out.println("Error: File does not exist.");
			}
			//Takes a file location
			//locates file and returns true
			//or returns false if file is non-existant or is not a CSV
			return false;
		}else {
			System.out.println("Default toner CSV selected");
			Main.fileReader(new File("toners.csv"), "toner");
			return true;
		}
	}
}

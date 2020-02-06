package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CSVController {
	public static String printerCSVPath;
	public static String tonerCSVPath;
	
	@FXML private Button printerCSVbutton;
	@FXML private Button tonerCSVbutton;
	@FXML private Button continueButton;
	@FXML private Button replacePrinterCSVButton;
	@FXML private Button replaceTonerCSVButton;
	@FXML private Button savePrinterFileButton;
	@FXML private Button saveTonerFileButton;
	@FXML private Label printerCSVdisplay;
	@FXML private Label tonerCSVdisplay;
	@FXML private TextField PrinterFileName;
	@FXML private TextField TonerFileName;
	
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
	
	public void replacePrinterCSVButtonPushed() {
		updateCSV(Main.fileBrowser(), "printer");
	}
	public void replaceTonerCSVButtonPushed() {
		updateCSV(Main.fileBrowser(),"toner");
	}
	//Core of save functions comes from https://www.baeldung.com/java-csv
	public void savePrinterFileButtonPushed() throws FileNotFoundException {
		if(!PrinterFileName.getText().isEmpty()) {
			List<String[]> dataLines = new ArrayList<>();
			dataLines.add(new String[] {"Bar Code","Description","Category","Location","Serial Num","Manufacturer","Division","Department","Campus","Status"});
			for(Printer a : PrinterTableController.printers) 
				dataLines.add(new String[] {Integer.toString(a.getBarCode()), a.getDescription(), a.getCategory(), a.getLocation(), a.getSerialNum(), a.getManuName(), a.getDivision(), a.getDepartment(), a.getCampus(), a.getStatus()});
			
			File csvOutputFile = new File("SaveFiles\\" + PrinterFileName.getText() + ".csv");
		    try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
		        dataLines.stream()
		          .map(this::convertToCSV)
		          .forEach(pw::println);
		    }
		}
	}
	public void saveTonerFileButtonPushed() throws FileNotFoundException {
		if(!TonerFileName.getText().isEmpty()) {
			List<String[]> dataLines = new ArrayList<>();
			dataLines.add(new String[] {"Printer Model","Brand","Model","Printers","Min Stock","Cur Stock","Order","Needed"});
			for(Toner a : TonerTableController.toners)
				dataLines.add(new String[] {a.getPrinterModel(), a.getBrand(), a.getModel(), Integer.toString(a.getPrinters()), Integer.toString(a.getMinStock()), Integer.toString(a.getCurStock()), a.getOrder(), Integer.toString(a.getNeeded())});
			File csvOutputFile = new File("SaveFiles\\" + TonerFileName.getText() + ".csv");
		    try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
		        dataLines.stream()
		          .map(this::convertToCSV)
		          .forEach(pw::println);
		    }
		}
	}
	
	public String convertToCSV(String[] data) {
	    return Stream.of(data)
	      .map(this::escapeSpecialCharacters)
	      .collect(Collectors.joining(","));
	}
	public String escapeSpecialCharacters(String data) {
	    String escapedData = data.replaceAll("\\R", " ");
	    if (data.contains(",") || data.contains("\"") || data.contains("'")) {
	        data = data.replace("\"", "\"\"");
	        escapedData = "\"" + data + "\"";
	    }
	    return escapedData;
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
	
	static public void updateCSV(String fileLocation, String type) {
		File infile = new File(fileLocation);
		if(infile.exists() && infile.getName().endsWith(".csv")) {
			 if(Main.csvVerify(infile, "printer") || Main.csvVerify(infile, "toner")) {
				FileInputStream instream = null;
				FileOutputStream outstream = null;
			 
		    	try{
		    		File outfile = null;
		    		if(type.equals("Printer"))
		    	    	outfile =new File("printers.csv");
		    	    else if(type.equals("Toner"))
		    	    	outfile =new File("toners.csv");
		    	    else {
		    	    	System.out.println("CSV Update Error: Invalid type");
		    	    	return;
		    	    }
		    	    instream = new FileInputStream(infile);
		    	    outstream = new FileOutputStream(outfile);
		 
		    	    byte[] buffer = new byte[1024];
		 
		    	    int length;
		    	    /*copying the contents from input stream to
		    	     * output stream using read and write methods
		    	     */
		    	    while ((length = instream.read(buffer)) > 0){
		    	    	outstream.write(buffer, 0, length);
		    	    }
	
		    	    //Closing the input/output file streams
		    	    instream.close();
		    	    outstream.close();
	
		    	    System.out.println("CSV replaced successfully!!");
			 
		    	}catch(IOException ioe){
		    		ioe.printStackTrace();
		    	}
			 }else {
				 System.out.println("Verification error");
				 return;
			 }
		}else {
			System.out.println("Invalid file selected");
			return;
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

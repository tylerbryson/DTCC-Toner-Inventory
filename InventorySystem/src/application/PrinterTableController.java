package application;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import javax.print.DocFlavor.URL;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class PrinterTableController implements Initializable{
	@FXML private TextField barCodeField;
	@FXML private TextField descriptionField;
	@FXML private TextField categoryField;
	@FXML private TextField locationField;
	@FXML private TextField serialNumField;
	@FXML private TextField manuField;
	@FXML private TextField divisionField;
	@FXML private TextField departmentField;
	@FXML private TextField campusField;
	@FXML private TextField statusField;
	
	@FXML private Button backButton;
	@FXML private Button addButton;
	@FXML private Button removeButton;
	@FXML private Button searchButton;
	@FXML private Button convertButton;
	@FXML private Button refreshButton;
	
	@FXML private TableView<Printer> tableView;
	@FXML private TableColumn<Printer, Integer> barCodeColumn;
	@FXML private TableColumn<Printer, String> descriptionColumn;
	@FXML private TableColumn<Printer, String> categoryColumn;
	@FXML private TableColumn<Printer, String> locationColumn;
	@FXML private TableColumn<Printer, String> serialNumColumn;
	@FXML private TableColumn<Printer, String> manuColumn;
	@FXML private TableColumn<Printer, String> divisionColumn;
	@FXML private TableColumn<Printer, String> departmentColumn;
	@FXML private TableColumn<Printer, String> campusColumn;
	@FXML private TableColumn<Printer, String> statusColumn;
	
	public static ObservableList<Printer> printers = FXCollections.observableArrayList();
	public static ObservableList<Toner> convertedToners = FXCollections.observableArrayList();
	
	@Override
	public void initialize(java.net.URL arg0, ResourceBundle arg1) {
		barCodeColumn.setCellValueFactory(new PropertyValueFactory<Printer, Integer>("barCode"));
		descriptionColumn.setCellValueFactory(new PropertyValueFactory<Printer, String>("description"));
		categoryColumn.setCellValueFactory(new PropertyValueFactory<Printer, String>("category"));
		locationColumn.setCellValueFactory(new PropertyValueFactory<Printer, String>("location"));
		serialNumColumn.setCellValueFactory(new PropertyValueFactory<Printer, String>("serialNum"));
		manuColumn.setCellValueFactory(new PropertyValueFactory<Printer, String>("manuName"));
		divisionColumn.setCellValueFactory(new PropertyValueFactory<Printer, String>("division"));
		departmentColumn.setCellValueFactory(new PropertyValueFactory<Printer, String>("department"));
		campusColumn.setCellValueFactory(new PropertyValueFactory<Printer, String>("campus"));
		statusColumn.setCellValueFactory(new PropertyValueFactory<Printer, String>("status"));
		
		tableView.setItems(getPrinters());
	}
	
	public ObservableList<Printer> getPrinters(){
		for(Printer a : Main.printerlist) {
			printers.add(a);
		}
		//printers.add(new Printer(10166,"5350DN","LASER PRINTER","MODULAR 1","28HT6L1","DELL","INSTRUCTION","NURSING","STANTON","ACTIVE"));
		return printers;
	}
	
	public void backButtonPushed(ActionEvent event) throws IOException {
		Parent root2 = FXMLLoader.load(getClass().getResource("Sample.fxml"));
		Scene mainScene = new Scene(root2);
		
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(mainScene);
		window.show();
	}
	
	public void addButtonPushed() {
		if(barCodeField.getText().isEmpty() ||
		   descriptionField.getText().isEmpty() ||
		   categoryField.getText().isEmpty() ||
		   locationField.getText().isEmpty() ||
		   serialNumField.getText().isEmpty() ||
		   manuField.getText().isEmpty() ||
		   divisionField.getText().isEmpty() ||
		   departmentField.getText().isEmpty() ||
		   campusField.getText().isEmpty() ||
		   statusField.getText().isEmpty()) {
			addButton.setText("Fields must be filled");
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			addButton.setText("Add");
		}else {
			try{
			Printer tempPrinter = new Printer(Integer.valueOf(barCodeField.getText()),
											  descriptionField.getText(),
											  categoryField.getText(),
											  locationField.getText(),
											  serialNumField.getText(),
											  manuField.getText(),
											  divisionField.getText(),
											  departmentField.getText(),
											  campusField.getText(),
											  statusField.getText());
			printers.add(tempPrinter);
			tableView.setItems(printers);
			}
			catch(NumberFormatException ex) {
				System.out.println("String where integer is expected");
			}
		}
	}
	public void removeButtonPushed() {
		if(!(tableView.getSelectionModel().getSelectedItem() == null)) {
			Printer tempPrinter = tableView.getSelectionModel().getSelectedItem();
			List<Integer> intList = new ArrayList<>();
			for(Printer a : printers)
				if (tempPrinter.equals(a))
					intList.add(printers.indexOf(a));
			for(int a : intList)
				printers.remove(a);
		}
	}
	public void searchButtonPushed() {
		ObservableList<Printer> searchPrinters = FXCollections.observableArrayList();
		for(Printer a : printers)
			searchPrinters.add(a);
		List<Integer> intList = new ArrayList<>();
		try {
			if(!barCodeField.getText().isEmpty()) 
				for(Printer a : searchPrinters) 
					if(a.getBarCode() != Integer.valueOf(barCodeField.getText()))
						intList.add(searchPrinters.indexOf(a));
		}catch(NumberFormatException ex) {
		}
		if(!descriptionField.getText().isEmpty())
			for(Printer a : searchPrinters) 
				if(!a.getDescription().equals(descriptionField.getText()))
					intList.add(searchPrinters.indexOf(a));
		if(!categoryField.getText().isEmpty())
			for(Printer a : searchPrinters) 
				if(!a.getCategory().equals(categoryField.getText()))
					intList.add(searchPrinters.indexOf(a));
		if(!locationField.getText().isEmpty()) 
			for(Printer a : searchPrinters) 
				if(!a.getLocation().equals(locationField.getText()))
					intList.add(searchPrinters.indexOf(a));
		if(!serialNumField.getText().isEmpty())
			for(Printer a : searchPrinters) 
				if(!a.getSerialNum().equals(serialNumField.getText()))
					intList.add(searchPrinters.indexOf(a));
		if(!manuField.getText().isEmpty())
			for(Printer a : searchPrinters) 
				if(!a.getManuName().equals(manuField.getText()))
					intList.add(searchPrinters.indexOf(a));
		if(!divisionField.getText().isEmpty())
			for(Printer a : searchPrinters) 
				if(!a.getDivision().equals(divisionField.getText()))
					intList.add(searchPrinters.indexOf(a));
		if(!departmentField.getText().isEmpty())
			for(Printer a : searchPrinters) 
				if(!a.getDepartment().equals(departmentField.getText()))
					intList.add(searchPrinters.indexOf(a));
		if(!campusField.getText().isEmpty())
			for(Printer a : searchPrinters) 
				if(!a.getCampus().equals(campusField.getText()))
					intList.add(searchPrinters.indexOf(a));
		if(!statusField.getText().isEmpty())
			for(Printer a : searchPrinters) 
				if(!a.getStatus().equals(statusField.getText()))
					intList.add(searchPrinters.indexOf(a));
		List<Integer> intList2 = new ArrayList<>();
		for(int i : intList) 
			if(!intList2.contains(i))
				intList2.add(i);
		Collections.sort(intList2, Collections.reverseOrder());
		for(int i : intList2)
			searchPrinters.remove(searchPrinters.get(i));
		tableView.setItems(searchPrinters);
	}
	public void convertButtonPushed(ActionEvent event) throws IOException {
		if(!(tableView.getSelectionModel().getSelectedItem() == null)) {
			convertedToners.clear();
			Main.fileReader(new File("crosswalk.csv"), "cross");
			for(int i = 0; i < Main.convT.size(); i++) 
				Main.convT.set(i, Main.convT.get(i).replaceAll("\"", ""));
			
			Printer tempPrinter = tableView.getSelectionModel().getSelectedItem();
			ArrayList<Toner> convList = convertToToner(tempPrinter);
			for(Toner a : convList)
				convertedToners.add(a);
			Parent root3 = FXMLLoader.load(getClass().getResource("TonerTableOnly.fxml"));
			Scene mainScene = new Scene(root3);
			
			Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
			
			window.setScene(mainScene);
			window.show();
		}else
			System.out.println("Must select a menu item");
	}
	
	public static ArrayList<Toner> convertToToner(Printer tempPrinter) {
		ArrayList<Toner> tonerList = new ArrayList<Toner>();
		String printerDesc = tempPrinter.getDescription();
		int index = -1;
		for(String a : Main.convP)
			if(a.equals(printerDesc))
				index = Main.convP.indexOf(a);
		if(index == -1) {
			System.out.println("Conversion error: invalid printer input");
			return tonerList;
		}
		String[] tonerString = Main.convT.get(index).split(" ");
		for(String a : tonerString) 
			for(Toner b : Main.tonerlist) 
				if(a.equals(b.getModel()))
						tonerList.add(b);
		//Takes a printer and returns the corresponding toner(s)
		return tonerList;
	}
	
	public void refreshButtonPushed() {
		tableView.setItems(printers);
	}
	
}

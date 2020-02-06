package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

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

public class TonerTableController implements Initializable{
	@FXML private TextField printerModelField;
	@FXML private TextField brandField;
	@FXML private TextField modelField;
	@FXML private TextField printersField;
	@FXML private TextField minStockField;
	@FXML private TextField curStockField;
	@FXML private TextField orderField;
	@FXML private TextField neededField;
	
	@FXML private Button backButton;
	@FXML private Button addButton;
	@FXML private Button removeButton;
	@FXML private Button searchButton;
	@FXML private Button convertButton;
	@FXML private Button refreshButton;
	@FXML private Button mustOrderButton;
	
	@FXML private TableView<Toner> tableView;
	@FXML private TableColumn<Toner, Integer> printersColumn;
	@FXML private TableColumn<Toner, Integer> minStockColumn;
	@FXML private TableColumn<Toner, Integer> curStockColumn;
	@FXML private TableColumn<Toner, Integer> neededColumn;
	@FXML private TableColumn<Toner, String> printerModelColumn;
	@FXML private TableColumn<Toner, String> brandColumn;
	@FXML private TableColumn<Toner, String> modelColumn;
	@FXML private TableColumn<Toner, String> orderColumn;
	
	public static ObservableList<Toner> toners = FXCollections.observableArrayList();
	public static ObservableList<Printer> convertedPrinters = FXCollections.observableArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		printersColumn.setCellValueFactory(new PropertyValueFactory<Toner, Integer>("printers"));
		minStockColumn.setCellValueFactory(new PropertyValueFactory<Toner, Integer>("minStock"));
		curStockColumn.setCellValueFactory(new PropertyValueFactory<Toner, Integer>("curStock"));
		neededColumn.setCellValueFactory(new PropertyValueFactory<Toner, Integer>("needed"));
		printerModelColumn.setCellValueFactory(new PropertyValueFactory<Toner, String>("printerModel"));
		brandColumn.setCellValueFactory(new PropertyValueFactory<Toner, String>("brand"));
		modelColumn.setCellValueFactory(new PropertyValueFactory<Toner, String>("model"));
		orderColumn.setCellValueFactory(new PropertyValueFactory<Toner, String>("order"));
		
		tableView.setItems(getToners());
	}
	
	public ObservableList<Toner> getToners(){
		for(Toner a : Main.tonerlist) {
			toners.add(a);
		}
		//printers.add(new Printer(10166,"5350DN","LASER PRINTER","MODULAR 1","28HT6L1","DELL","INSTRUCTION","NURSING","STANTON","ACTIVE"));
		return toners;
	}
	
	public void backButtonPushed(ActionEvent event) throws IOException {
		Parent root2 = FXMLLoader.load(getClass().getResource("Sample.fxml"));
		Scene mainScene = new Scene(root2);
		
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(mainScene);
		window.show();
	}
	
	public void addButtonPushed() {
		if(printerModelField.getText().isEmpty() ||
		   brandField.getText().isEmpty() ||
		   modelField.getText().isEmpty() ||
		   printersField.getText().isEmpty() ||
		   minStockField.getText().isEmpty() ||
		   curStockField.getText().isEmpty() ||
		   orderField.getText().isEmpty() ||
		   neededField.getText().isEmpty()){
			addButton.setText("Fields must be filled");
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			addButton.setText("Add");
		}else {
			try{
			Toner tempPrinter = new Toner(printerModelField.getText(),
										  brandField.getText(),
										  modelField.getText(),
										  Integer.valueOf(printersField.getText()),
										  Integer.valueOf(minStockField.getText()),
										  Integer.valueOf(curStockField.getText()),
										  orderField.getText(),
										  Integer.valueOf(neededField.getText()));
			toners.add(tempPrinter);
			tableView.setItems(toners);
			}
			catch(NumberFormatException ex) {
				System.out.println("String where integer is expected");
			}
		}
	}
	public void removeButtonPushed() {
		if(!(tableView.getSelectionModel().getSelectedItem() == null)) {
			Toner tempToner = tableView.getSelectionModel().getSelectedItem();
			List<Integer> intList = new ArrayList<>();
			for(Toner a : toners)
				if (tempToner.equals(a))
					intList.add(toners.indexOf(a));
			for(int a : intList)
				toners.remove(a);
		}
	}
	public void searchButtonPushed() {
		ObservableList<Toner> searchToners = FXCollections.observableArrayList();
		for(Toner a : toners)
			searchToners.add(a);
		List<Integer> intList = new ArrayList<>();
		try {
			if(!printersField.getText().isEmpty()) 
				for(Toner a : searchToners) 
					if(a.getPrinters() != Integer.valueOf(printersField.getText()))
						intList.add(searchToners.indexOf(a));
		}catch(NumberFormatException ex) {
		}
		try {
			if(!minStockField.getText().isEmpty())
				for(Toner a : searchToners) 
					if(a.getMinStock() != Integer.valueOf(minStockField.getText()))
						intList.add(searchToners.indexOf(a));
		}catch(NumberFormatException ex) {
		}
		try {
			if(!curStockField.getText().isEmpty())
				for(Toner a : searchToners) 
					if(a.getCurStock() != Integer.valueOf(curStockField.getText()))
						intList.add(searchToners.indexOf(a));
		}catch(NumberFormatException ex) {
		}
		try {
			if(!neededField.getText().isEmpty()) 
				for(Toner a : searchToners) 
					if(a.getNeeded() != Integer.valueOf(neededField.getText()))
						intList.add(searchToners.indexOf(a));
		}catch(NumberFormatException ex) {
		}
		if(!printerModelField.getText().isEmpty())
			for(Toner a : searchToners) 
				if(!a.getPrinterModel().equals(printerModelField.getText()))
					intList.add(searchToners.indexOf(a));
		if(!brandField.getText().isEmpty())
			for(Toner a : searchToners) 
				if(!a.getBrand().equals(brandField.getText()))
					intList.add(searchToners.indexOf(a));
		if(!modelField.getText().isEmpty())
			for(Toner a : searchToners) 
				if(!a.getModel().equals(modelField.getText()))
					intList.add(searchToners.indexOf(a));
		if(!orderField.getText().isEmpty())
			for(Toner a : searchToners) 
				if(!a.getOrder().equals(orderField.getText()))
					intList.add(searchToners.indexOf(a));
		List<Integer> intList2 = new ArrayList<>();
		for(int i : intList) 
			if(!intList2.contains(i))
				intList2.add(i);
		Collections.sort(intList2, Collections.reverseOrder());
		for(int i : intList2)
			searchToners.remove(searchToners.get(i));
		tableView.setItems(searchToners);
	}
	public void convertButtonPushed(ActionEvent event) throws IOException {
		if(!(tableView.getSelectionModel().getSelectedItem() == null)) {
			convertedPrinters.clear();
			Main.fileReader(new File("crosswalk.csv"), "cross");
			for(int i = 0; i < Main.convT.size(); i++) 
				Main.convT.set(i, Main.convT.get(i).replaceAll("\"", ""));
			
			Toner tempToner = tableView.getSelectionModel().getSelectedItem();
			ArrayList<Printer> convList = convertToPrinter(tempToner);
			for(Printer a : convList)
				convertedPrinters.add(a);
			Parent root3 = FXMLLoader.load(getClass().getResource("PrinterTableOnly.fxml"));
			Scene mainScene = new Scene(root3);
			
			Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
			
			window.setScene(mainScene);
			window.show();
		}else
			System.out.println("Must select a menu item");
	}
	
	public static ArrayList<Printer> convertToPrinter(Toner tempToner) {
		ArrayList<Printer> printerList = new ArrayList<Printer>();
		List<Integer> intList = new ArrayList<>();
		for(int z = 0; z < Main.convT.size(); z++) {
			String[] tonerString = Main.convT.get(z).split(" ");
			for(String b : tonerString)
				if(b.equals(tempToner.getModel()))
					intList.add(z);
		}
		if(intList.isEmpty()) {
			System.out.println("Conversion error: invalid toner input");
			return printerList;
		}
		for(int index : intList) {
			for(Printer a : Main.printerlist)
				if(a.getDescription().equals(Main.convP.get(index)))
					printerList.add(a);
		}
		//Takes a toner and returns the corresponding printer
		return printerList;
	}
	
	public void refreshButtonpushed() {
		tableView.setItems(toners);
	}
	public void mustOrderButtonPushed() {
		ObservableList<Toner> orderToners = FXCollections.observableArrayList();
		for(Toner a : toners) 
			if(a.getCurStock() < a.getMinStock()) 
				orderToners.add(a);
		tableView.setItems(orderToners);
	}
}

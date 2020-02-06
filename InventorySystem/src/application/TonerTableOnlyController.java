package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class TonerTableOnlyController implements Initializable{
	@FXML private Button backButton;
	@FXML private TableView<Toner> tableView;
	@FXML private TableColumn<Toner, Integer> printersColumn;
	@FXML private TableColumn<Toner, Integer> minStockColumn;
	@FXML private TableColumn<Toner, Integer> curStockColumn;
	@FXML private TableColumn<Toner, Integer> neededColumn;
	@FXML private TableColumn<Toner, String> printerModelColumn;
	@FXML private TableColumn<Toner, String> brandColumn;
	@FXML private TableColumn<Toner, String> modelColumn;
	@FXML private TableColumn<Toner, String> orderColumn;
	
	private ObservableList<Toner> toners = FXCollections.observableArrayList();

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
		for(Toner a : PrinterTableController.convertedToners) {
			toners.add(a);
		}
		//printers.add(new Printer(10166,"5350DN","LASER PRINTER","MODULAR 1","28HT6L1","DELL","INSTRUCTION","NURSING","STANTON","ACTIVE"));
		return toners;
	}
	
	public void backButtonPushed(ActionEvent event) throws IOException {
		Parent root2 = FXMLLoader.load(getClass().getResource("PrinterTable.fxml"));
		Scene mainScene = new Scene(root2);
		
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(mainScene);
		window.show();
	}
}

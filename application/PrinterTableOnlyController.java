package application;

import java.io.IOException;
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

public class PrinterTableOnlyController implements Initializable{
	@FXML private Button backButton;
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
	
	private ObservableList<Printer> printers = FXCollections.observableArrayList();
	
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
		for(Printer a : TonerTableController.convertedPrinters) {
			printers.add(a);
		}
		//printers.add(new Printer(10166,"5350DN","LASER PRINTER","MODULAR 1","28HT6L1","DELL","INSTRUCTION","NURSING","STANTON","ACTIVE"));
		return printers;
	}
	
	public void backButtonPushed(ActionEvent event) throws IOException {
		Parent root2 = FXMLLoader.load(getClass().getResource("TonerTable.fxml"));
		Scene mainScene = new Scene(root2);
		
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(mainScene);
		window.show();
	}
}

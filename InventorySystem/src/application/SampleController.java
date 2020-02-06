package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SampleController {
	@FXML private Button csvButton;
	@FXML private Button printerButton;
	@FXML private Button tonerButton;
	
	public void csvButtonPressed(ActionEvent event) throws IOException {
		Parent root2 = FXMLLoader.load(getClass().getResource("CSV.fxml"));
		Scene mainScene = new Scene(root2);
		
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(mainScene);
		window.show();
	}
	public void printerButtonPressed(ActionEvent event) throws IOException {
		Parent root2 = FXMLLoader.load(getClass().getResource("PrinterTable.fxml"));
		Scene mainScene = new Scene(root2);
		
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(mainScene);
		window.show();
	}
	public void tonerButtonPressed(ActionEvent event) throws IOException {
		Parent root2 = FXMLLoader.load(getClass().getResource("TonerTable.fxml"));
		Scene mainScene = new Scene(root2);
		
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(mainScene);
		window.show();
	}
}

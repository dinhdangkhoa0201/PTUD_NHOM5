package control;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class RegisterControl implements Initializable{
	@FXML JFXTextField txtEmail;
	@FXML JFXPasswordField txtPassword;
	@FXML JFXPasswordField txtRePassword;
	@FXML JFXButton btnSignUp;
	@FXML JFXButton btnCancel;
	@FXML JFXButton btnCloseWindown;

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	}
	
	@FXML
	private void handleButtonAction(MouseEvent e) {
		if(e.getSource() == btnSignUp) {
			System.out.println("Sign up");
		}
		else if(e.getSource() == btnCancel) {
			try {
				Node node = (Node) e.getSource();
				Stage stage = (Stage) node.getScene().getWindow();
				stage.close();
				
				
				Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/gui/Login.fxml")));
				stage.setScene(scene);
				stage.show();
			} catch (Exception e2) {
				// TODO: handle exception
				System.out.println(e2.getMessage());
			}
		}
		else if(e.getSource() == btnCloseWindown) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Do you want to exit?");
			alert.setContentText("Are you sure?");
			
			ButtonType yesBtn = new ButtonType("Yes", ButtonData.YES);
			ButtonType noBtn = new ButtonType("No", ButtonData.NO);
			
			alert.getButtonTypes().setAll(yesBtn, noBtn);
			
			if(alert.showAndWait().get() == yesBtn)
				System.exit(0);
			else
				alert.close();
		}
	}
}

package control;

import java.net.URL;
import java.util.List;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class LoginControl implements Initializable{

	@FXML private Label lblError;
	@FXML private JFXTextField txtUser;
	@FXML private JFXPasswordField txtPassword;
	@FXML private JFXButton btnSignIn;
	@FXML private JFXButton btnSignUp;
	@FXML private JFXButton btnCloseWindown;
	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}
	@FXML
	void handleButtonAction(MouseEvent e) {
		if(e.getSource() == btnSignIn) {
			if(login()) {
				try {
					lblError.setTextFill(Color.GREEN);
					lblError.setText("Login Success.. Please waiting...!");

					Node node = (Node) e.getSource();
					Stage stage = (Stage) node.getScene().getWindow();
					stage.close();


					Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/gui/Menu.fxml")));
					stage.setScene(scene);
					stage.show();

				} catch (Exception e2) {
					// TODO: handle exception
					System.out.println(e2.getMessage());
					System.out.println("Error SignIn");
				}
			}
			else {
				lblError.setTextFill(Color.TOMATO);
				lblError.setText("This account is Invalid");

				txtPassword.setText("");
			}
		}
		else if(e.getSource() == btnSignUp) {
			try {
				Node node = (Node) e.getSource();
				Stage stage = (Stage) node.getScene().getWindow();
				stage.close();

				Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/gui/Register.fxml")));
				stage.setScene(scene);
				stage.show();
			} catch (Exception e2) {
				// TODO: handle exception
				System.out.println(e2.getMessage());
				System.out.println("Error Register");
			}
		}
		else if(e.getSource() == btnCloseWindown) {
			Alert alert = new Alert(AlertType.WARNING);
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

	public LoginControl() {
		// TODO Auto-generated constructor stub

	}

	public boolean login() {
		String user = txtUser.getText().toString();
		String password = txtPassword.getText().toString();
		if(user.equalsIgnoreCase("khoacyruss") && password.equalsIgnoreCase("123456")) 
			return true;
		return false;
	}

}

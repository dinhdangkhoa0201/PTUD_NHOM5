package control;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class MenuControl implements Initializable{
    private static final String IDLE_BUTTON_STYLE = "-fx-background-color: transparent;";
    private static final String HOVERED_BUTTON_STYLE = "-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color;";
    
    @FXML JFXButton btnNhanVien;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		btnNhanVien.setStyle(IDLE_BUTTON_STYLE);
		btnNhanVien.setOnMouseEntered(e -> btnNhanVien.setStyle(HOVERED_BUTTON_STYLE));
		btnNhanVien.setOnMouseExited(e -> btnNhanVien.setStyle(IDLE_BUTTON_STYLE));
	}
	
}

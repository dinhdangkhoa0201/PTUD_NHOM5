package control;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.Year;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import database.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ThemKhachHangControl implements Initializable{

	@FXML JFXButton btnClose;
	@FXML JFXButton btnThem;
	
	@FXML TextField txtHoTen;
	@FXML JFXComboBox<String> cbxGioiTinh;
	@FXML JFXDatePicker txtDate;
	@FXML TextField txtEmail;
	@FXML TextField txtDiaChi;
	@FXML TextField txtSoDienThoai;
	
	private Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	private ObservableList<String> gioiTinh = FXCollections.observableArrayList("Nam", "Nữ");
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		con = database.Database.getInStance().getConnection();
		setValues();
	}
	private void setValues() {
		cbxGioiTinh.setItems(gioiTinh);
	}
	@FXML
	private void handlButtonAction(MouseEvent e) {
		if(e.getSource() == btnClose) {
			System.out.println("Exit");
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Do you want to exit?");
			alert.setContentText("Are you sure?");

			ButtonType yesBtn = new ButtonType("Yes", ButtonData.YES);
			ButtonType noBtn = new ButtonType("No", ButtonData.NO);

			alert.getButtonTypes().setAll(yesBtn, noBtn);

			if(alert.showAndWait().get() == yesBtn) {
				Node node = (Node) e.getSource();
				Stage stage = (Stage) node.getScene().getWindow();
				stage.close();
			}
			else
				alert.close();
		}
		else if(e.getSource() == btnThem) {
			String hoTenKH = txtHoTen.getText();
			String gioiTinh = cbxGioiTinh.getSelectionModel().toString();
			Date ngaySinh = Date.valueOf(txtDate.getValue());
			String email = txtEmail.getText();
			String soDienThoai = txtSoDienThoai.getText();
			String diaChi = txtDiaChi.getText();
			con = Database.getInStance().getConnection();
			PreparedStatement preparedStatement = null;
			String sql = "insert into KhachHang values (dbo.PhatSinhMaKH(), ?, ?, ?, ?, ?, ?)";
			try {
				preparedStatement = con.prepareStatement(sql);
				preparedStatement.setString(1, hoTenKH);
				if(gioiTinh.equals("Nam"))
					preparedStatement.setBoolean(2, false);
				else
					preparedStatement.setBoolean(2, true);
				preparedStatement.setDate(3, ngaySinh);
				preparedStatement.setString(4, email);
				preparedStatement.setString(5, soDienThoai);
				preparedStatement.setString(6, diaChi);
				System.out.println(preparedStatement.execute());
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	private static void alertError(String txt) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error Dialog");
		alert.setHeaderText(txt);
		alert.showAndWait();
	}
	private boolean kiemTraDuLieu() {
		boolean kt = true;
		String hoTenKH = txtHoTen.getText();
		String gioiTinh = cbxGioiTinh.getSelectionModel().toString();
		Date ngaySinh = Date.valueOf(txtDate.getValue());
		String email = txtEmail.getText();
		String soDienThoai = txtSoDienThoai.getText();
		String diaChi = txtDiaChi.getText();
		
		if(hoTenKH.equals("")) {
			alertError("Chưa nhập họ và tên");
			txtHoTen.requestFocus();
			kt = false;
		}
		else if(!hoTenKH.matches("^[A-Z]\\w+(\\s  [A-Z]\\w)*$")) {
			alertError("Nhập sai định dạng họ và tên. Tên phải bắt đầu bằng ký tự hoa ở mỗi từ theo sau là ký tự thường, không chứa số và ký tự đặc biệt");
			txtHoTen.requestFocus();
			kt = false;
		}	
		else if(gioiTinh.equals("")) {
			alertError("Chưa chọn giới tính");
			kt = false;
		}
		else if(hoTenKH.equals("")) {
			alertError("Chưa chọn ngày sinh");
			kt = false;
		}
		else if(LocalDate.now().getYear() - ngaySinh.toLocalDate().getYear() <18) {
			alertError("Chưa đủ 18 tuổi");
			kt = false;
		}
		else if(email.equals("")) {
			alertError("Chưa nhập email");
			txtEmail.requestFocus();
			kt = false;
		}
		else if(soDienThoai.equals("")) {
			alertError("Chưa nhập số điện thoại");
			txtSoDienThoai.requestFocus();
			kt = false;
		}
		else if(diaChi.equals("")) {
			alertError("Chưa nhập địa chỉ");
			txtDiaChi.requestFocus();
			kt = false;
		}
		return kt;
	}
	
}

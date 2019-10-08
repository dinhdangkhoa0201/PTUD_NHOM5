package control;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import entities.NhanVien;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class QuanLyNhanVienControl implements Initializable{
	@FXML JFXButton btnBack;
	@FXML JFXButton btnClose;
	@FXML JFXButton btnReLoad;
	@FXML JFXButton btnAdd;
	
	@FXML private TableView<NhanVien> tableNhanVien;
	@FXML private TableColumn<NhanVien, String> col_manv;
	@FXML private TableColumn<NhanVien, String> col_hoten;
	@FXML private TableColumn<NhanVien, String> col_gioitinh;
	@FXML private TableColumn<NhanVien, String> col_ngaysinh;
	@FXML private TableColumn<NhanVien, String> col_ngayvaolam;
	@FXML private TableColumn<NhanVien, String> col_diachi;
	@FXML private TableColumn<NhanVien, String> col_email;
	@FXML private TableColumn<NhanVien, String> col_dienthoai;

	
	private Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	private ObservableList<NhanVien> data;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		con = database.Database.getInStance().getConnection();
		data = FXCollections.observableArrayList();
		setCellTable();
		loadDataFromDatabase();
	}
	
	private void setCellTable() {
		col_manv.setCellValueFactory(new PropertyValueFactory<>("maNV"));
		col_hoten.setCellValueFactory(new PropertyValueFactory<>("hoTen"));
		col_gioitinh.setCellValueFactory(new PropertyValueFactory<>("gioiTinh"));
		col_ngaysinh.setCellValueFactory(new PropertyValueFactory<>("ngaySinh"));
		col_ngayvaolam.setCellValueFactory(new PropertyValueFactory<>("ngayVaoLam"));
		col_diachi.setCellValueFactory(new PropertyValueFactory<>("diaChi"));
		col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
		col_dienthoai.setCellValueFactory(new PropertyValueFactory<>("soDienThoai"));
	}
	
	private void loadDataFromDatabase() {
		tableNhanVien.getItems().clear();
		try {
			ps = con.prepareStatement("SELECT * FROM NhanVien");
			rs = ps.executeQuery();
			while(rs.next()) {
				String maNV = rs.getString(1);
				String hoTen = rs.getString(2);
				System.out.println(hoTen);
				boolean bit = rs.getBoolean(3);
				String gioiTinh = "";
				if(bit == true)
					gioiTinh = "Nữ";
				else
					gioiTinh = "Nam";
				LocalDate ngaySinh = rs.getDate(4).toLocalDate();
				LocalDate ngayVaoLam = rs.getDate(5).toLocalDate();
				String diaChi = rs.getString(6);
				String email = rs.getString(7);
				String soDienThoai = rs.getString(8);
				NhanVien nhanVien = new NhanVien(maNV, hoTen, gioiTinh, ngaySinh, ngayVaoLam, diaChi, email, soDienThoai);
				data.add(nhanVien);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		tableNhanVien.setItems(data);
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
		else if(e.getSource() == btnBack) {
			try {
				Node node = (Node) e.getSource();
				Stage stage = (Stage) node.getScene().getWindow();
				stage.close();

				Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/gui/Menu.fxml")));
				stage.setScene(scene);
				stage.show();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
				System.out.println("Button Back");
			}
		}
		else if(e.getSource() == btnReLoad) {
			System.out.println("Reload");
			loadDataFromDatabase();
		}
		else if(e.getSource() == btnAdd) {
			try {
				Node node = (Node) e.getSource();
				Stage stage = (Stage) node.getScene().getWindow();
				
				Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/gui/ThemKhachHang.fxml")));
				stage.setScene(scene);
				stage.show();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
				System.out.println("Button Add");
			}
		}
	}

}

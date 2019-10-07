package control;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import entities.KhachHang;
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

public class QuanLyKhachHangControl implements Initializable{
	@FXML private JFXButton btnBack;
	@FXML private JFXButton btnClose;
	@FXML private JFXButton btnReLoad;
	
	@FXML private TableView<KhachHang> tableKhachHang;
	@FXML private TableColumn<KhachHang, String> col_makh;
	@FXML private TableColumn<KhachHang, String> col_hoten;
	@FXML private TableColumn<KhachHang, String> col_gioitinh;
	@FXML private TableColumn<KhachHang, String> col_ngaysinh;
	@FXML private TableColumn<KhachHang, String> col_email;
	@FXML private TableColumn<KhachHang, String> col_dienthoai;
	@FXML private TableColumn<KhachHang, String> col_diachi;
	
	private ObservableList<KhachHang> data;
	private Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		con = database.Database.getInStance().getConnection();
		data = FXCollections.observableArrayList();
		setCellTable();
		loadDataFromDatabase();
	}
	
	private void setCellTable() {
		col_makh.setCellValueFactory(new PropertyValueFactory<>("maKH"));
		col_hoten.setCellValueFactory(new PropertyValueFactory<>("hoTen"));
		col_gioitinh.setCellValueFactory(new PropertyValueFactory<>("gioiTinh"));
		col_ngaysinh.setCellValueFactory(new PropertyValueFactory<>("ngaySinh"));
		col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
		col_dienthoai.setCellValueFactory(new PropertyValueFactory<>("soDienThoai"));
		col_diachi.setCellValueFactory(new PropertyValueFactory<>("diaChi"));
	}
	
	private void loadDataFromDatabase() {
		tableKhachHang.getItems().clear();
		try {
			ps = con.prepareStatement("SELECT * FROM KhachHang");
			rs = ps.executeQuery();
			while(rs.next()) {
				String maKH = rs.getString(1);
				String hoTen = rs.getString(2);
				System.out.println(hoTen);
				boolean bit = rs.getBoolean(3);
				String gioiTinh = "";
				if(bit == true)
					gioiTinh = "Nữ";
				else
					gioiTinh = "Nam";
				LocalDate ngaySinh = rs.getDate(4).toLocalDate();
				String diaChi = rs.getString(5);
				String email = rs.getString(6);
				String soDienThoai = rs.getString(7);
				KhachHang khachHang = new KhachHang(maKH, hoTen, gioiTinh, ngaySinh, email, soDienThoai, diaChi);
				data.add(khachHang);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		tableKhachHang.setItems(data);
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
	}
	
//	public QuanLyKhachHangControl() {
//		// TODO Auto-generated constructor stub
//		loadDataFromDatabase();
//	}
//	
//	public static void main(String[] args) {
//		new QuanLyKhachHangControl();
//	}

}

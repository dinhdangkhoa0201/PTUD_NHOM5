package entities;

import java.time.LocalDate;

public class KhachHang {
	private String maKH;
	private String hoTenKH;
	private String gioiTinh;
	private LocalDate ngaySinh;
	private String email;
	private String soDienThoai;
	private String diaChi;
	
	public KhachHang(String maKH, String hoTenKH, String gioiTinh, LocalDate ngaySinh, String email, String soDienThoai,
			String diaChi) {
		super();
		this.maKH = maKH;
		this.hoTenKH = hoTenKH;
		this.gioiTinh = gioiTinh;
		this.ngaySinh = ngaySinh;
		this.email = email;
		this.soDienThoai = soDienThoai;
		this.diaChi = diaChi;
	}
	public KhachHang() {
		super();
	}
	public String getMaKH() {
		return maKH;
	}
	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}
	public String getTenKH() {
		return hoTenKH;
	}
	public void setTenKH(String hoTenKH) {
		this.hoTenKH = hoTenKH;
	}
	public String getGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	public LocalDate getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(LocalDate ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSoDienThoai() {
		return soDienThoai;
	}
	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	@Override
	public String toString() {
		return "KhachHang [tenKH=" + hoTenKH + ", gioiTinh=" + gioiTinh + ", ngaySinh=" + ngaySinh + ", email=" + email
				+ ", soDienThoai=" + soDienThoai + ", diaChi=" + diaChi + "]";
	}
	
	
}

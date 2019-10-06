create database HKVTravel
go 

use HKVTravel
go

create table NhanVien
(
	MaNV char(10) primary key,
	HoTenNV nvarchar(MAX) not null,
	GioiTinh bit,
	NgaySinh date,
	NgayVaoLam date,
	Email varchar(MAX),
	DienThoai varchar(12)
)
go

create table KhachHang
(
	MAKH char(10) primary key,
	HoTenKH nvarchar(MAX) not null,
	GioiTinh bit,
	NgaySinh date,
	CMND nvarchar(12),
	DiaChi nvarchar(MAX),
	Email varchar(MAX),
	DienThoai varchar(12)
)
go

create table UserPassword
(
	ID int primary key,
	Password varchar(20),
	UserNameNV char(10),
	UserNameKH char(10),
	constraint FK_User_KhachHang foreign key (UserNameKH) references KhachHang(MaKH),
	constraint FK_User_NhanVien foreign key (UserNameNV) references NhanVien(MaNV) 
)
go

create table KhachHangLienQuan
(
	ID int primary key,
	TenKHLQ nvarchar(MAX),
	GioiTinh bit,
	NgaySinh date,
	MaKH char(10),
	constraint FK_KhangHangLienQuan_KhachHang foreign key (MaKH) references  KhachHang(MaKH)
)
go

create table Tour
(
	MaTour char(10) primary key,
	TenTour nvarchar(MAX) not null,
	NgayKhoiHanh date,
	NgayKetThuc date,
	GiaVe money,
	GiaVeTreEm money
)
go

create table ChiTietTour
(
	MaChiTietTour char(10) primary key,
	MoTa nvarchar(MAX) not null,
	LichTrinh nvarchar(MAX)
	constraint FK_ChiTietTour_Tour foreign key (MaChiTietTour) references Tour(MaTour)
)
go

create table HoaDon
(
	MaHD char(10) primary key,
	MaKH char(10),
	MaNV char(10),
	MaTour char(10),
	NgayLapHoaDon date,
	constraint FK_HoaDon_KhachHang foreign key (MaKH) references KhachHang(MaKH),
	constraint FK_HoaDon_NhanVien foreign key (MaNV) references NhanVien(MaNV),
	constraint FK_HoaDon_Tour foreign key (MaTour) references Tour(MaTour)
)
go

use master
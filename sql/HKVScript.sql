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
	DiaChi nvarchar(MAX),
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
	DiaChi nvarchar(MAX),
	Email varchar(MAX),
	DienThoai varchar(12)
)
go

create table UserPassword
(
	Password varchar(20),
	UserNameNV char(10),
	UserNameKH char(10),
	constraint FK_User_KhachHang foreign key (UserNameKH) references KhachHang(MaKH),
	constraint FK_User_NhanVien foreign key (UserNameNV) references NhanVien(MaNV) 
)
go

alter table UserPassword add ID integer identity not null
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


create function PhatSinhMaKH()
returns int
as
	begin
		declare @tong int
		select @tong = (select COUNT(*) from KhachHang)
		set @tong = @tong + 1
	return @tong
end
go

select dbo.PhatSinhMaKH()

insert KhachHang values
(dbo.PhatSinhMaKH(), N'Đinh Đăng Khoa', 0, '1999/10/2', N'Củ Chi', 'dinhdangkhoa0201@gmail.com', '0937602105')
go

create function dbo.PhatSinhMaNV()
returns int
as 
	begin
	declare @tong int
	select @tong = (select COUNT(*) from NhanVien) + 1
	return @tong
end
go

insert NhanVien values
(dbo.PhatSinhMaNV(), N'Phan Sang Vô', 0, '1999/6/6', GETDATE(), N'Bạc Liêu','votech99@gmail.com', '1234567890')
go

insert UserPassword (UserNameNV, UserNameKH, Password) values
(null, 1, '123456'),
(1, null, '123456')
go


use master
go
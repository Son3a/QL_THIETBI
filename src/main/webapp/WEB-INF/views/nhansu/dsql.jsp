<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<base href="${pageContext.servletContext.contextPath}/">
<%@include file="../../../commons/links.jsp"%>
<link href="resources/css/dsthietbi.css" rel="stylesheet"
	type="text/css">
<title>Danh sách thiết bị</title>
<script>
window.addEventListener('load', function() {
	if ("${role}" != "admin") {
		document.getElementById("qlpm").style.display = "";
		document.getElementById("ntb").style.display = "";
		document.getElementById("tltb").style.display = "";
		document.getElementById("qltb").style.display = "";
		document.getElementById("qlnm").style.display = "";
		document.getElementById("qlnv").style.display = "none";
		document.getElementById("qlql").style.display = "none";
	}
	if ("${role}" == "admin") {
		document.getElementById("qlpm").style.display = "";
		document.getElementById("ntb").style.display = "";
		document.getElementById("tltb").style.display = "";
		document.getElementById("qltb").style.display = "";
		document.getElementById("qlnm").style.display = "";
		document.getElementById("qlnv").style.display = "";
		document.getElementById("qlql").style.display = "";
	}
})
</script>
<script>
	$(document).ready(function() {
		$('[data-toggle="tooltip"]').tooltip();
		// Animate select box length
		var searchInput = $(".search-box input");
		var inputGroup = $(".search-box .input-group");
		var boxWidth = inputGroup.width();
		searchInput.focus(function() {
			inputGroup.animate({
				width : "300"
			});
		}).blur(function() {
			inputGroup.animate({
				width : boxWidth
			});
		});
	});
</script>
</head>
<body>
	<nav class="navbar" style="background: #4293f5;">
		<a class="navbar-brand" href="phieumuon/dsphieumuon.htm" style="color: white;"> <img
			src="resources/img/khac/logo.png" width="30" height="30"
			class="d-inline-block align-top" alt=""> HỆ THỐNG QUẢN LÍ THIẾT
			BỊ HỌC VIỆN CƠ SỞ
		</a>
		<div style="float=right; color:white;">
			Xin chào, ${account_cur.username} | <a style="text-decoration:none; color:red;" href="nv/changePassword.htm">Đổi mật khẩu</a> |<a style="text-decoration:none; color:red;" href="logout.htm">Thoát</a>
		</div>
	</nav>
	<div class="d-md-flex">
		<nav id="sidebar">
			<div class="sidebar-header">
				<h3 style="color: red; font-size: 18px;">Học viện Cơ Sơ</h3>
			</div>

			<ul class="list-unstyled components">
				<c:choose>
					<c:when test="${role.equals('staff')}">
						<p style="color: yellow; font-size: 15px; text-align: center;">Nhân
					Viên</p>
					</c:when>
					<c:otherwise>
						<p style="color: yellow; font-size: 15px; text-align: center;">Quản lí</p>
					</c:otherwise>
				</c:choose>
				<li id="qlpm" class="active"><a
					href="phieumuon/dsphieumuon.htm">Quản lí phiếu mượn</a>
				<li id="ntb"><a href="phieunhap/dspn.htm">Quản lí phiếu
						nhập</a></li>
				<li id="tltb"><a href="ptl/dsptl.htm">Quản lí phiếu thanh
						lý</a>
				<li id="tltb"><a href="thietbi/dsthietbi.htm">Quản lí thiết
						bị</a>
				<li id="qlnm"><a href="nguoimuon/dsnm.htm">Quản lí người
						mượn</a></li>
				<li id="qlnv"><a href="nv/qlnhanvien.htm">Quản lí nhân viên</a></li>
				<li id="qlql"><a href="ql/qlquanli.htm">Quản lí quản lí</a></li>
			</ul>
		</nav>
		<div class="table-responsive">
			<div class="table-wrapper">
				<div class="table-title">
					<div class="row">
						<div class="col-4">
							<div class="show-entries">
								<span>Show</span> <select>
									<option>5</option>
									<option>10</option>
									<option>15</option>
									<option>20</option>
								</select> <span>entries</span>
							</div>
						</div>
						<div class="col-4">
							<h2 class="text-center">
								Danh sách <b>Quản lí</b>
							</h2>
						</div>
						<div class="col-4">
							<a id="btn-check" href="#bd-example-modal-lg" id="insert_btn"
								class="btn btn-primary" data-toggle="modal"
								data-target="#bd-example-modal-lg" type="button"
								style="float: right; width: fit-content;">Thêm quản lí</a>

						</div>
					</div>
				</div>
				<table class="table table-bordered">
					<thead>
						<tr>
							<th class="table-plus datatable-nosort">Mã người QL</th>
							<th>Họ và Tên</th>
							<th>Ngày sinh</th>
							<th>Giới tính</th>
							<th>Ngày tiếp nhận</th>
							<th>Hành động</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="quanli" items="${listQuanLi}" varStatus="row">
							<tr>
								<td class="table-plus">${quanli.maql}</td>
								<td>${quanli.ho} ${quanli.ten}</td>
								<td>${quanli.ngaysinh}</td>
								<td>${quanli.gioitinh}</td>
								<td>${quanli.ngaytiepnhan}</td>
								<td>
									<div class="row clearfix btn-list">

										<form action="ql/qlquanli/delete.htm" method="post">
											<!-- Dùng để hiển thị tên lên form -->
											<input type="hidden" name="ten" value="${quanli.ten}" />

											<!-- Dùng để gửi về controller -->
											<input type="hidden" name="maql" value="${quanli.maql}" />
											<button type="submit" style="display: none"
												class="submit_del_btn"></button>
										</form>
										<button class="btn btn-danger delete_btn"
											data-toggle="tooltip" data-placement="top" title="Xoá"
											type="button">
											<i class="material-icons">delete</i>
										</button>
									</div> <!-- 											</div> -->
								</td>
							</tr>
						</c:forEach>
					</tbody>

				</table>
				<div class="clearfix">
					<div class="hint-text">
						Showing <b>5</b> out of <b>25</b> entries
					</div>
					<ul class="pagination">
						<li class="page-item disabled"><a href="#">Previous</a></li>
						<li class="page-item"><a href="#" class="page-link">1</a></li>
						<li class="page-item"><a href="#" class="page-link">2</a></li>
						<li class="page-item active"><a href="#" class="page-link">3</a></li>
						<li class="page-item"><a href="#" class="page-link">4</a></li>
						<li class="page-item"><a href="#" class="page-link">5</a></li>
						<li class="page-item"><a href="#" class="page-link">Next</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>

	<!-- 	======================   Thêm Người Quản Lý ============================ -->
	<div class="modal fade" id="bd-example-modal-lg" tabindex="-1"
		role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-body">
					<!-- Default Basic Forms Start -->
					<div class="pd-20 card-box mb-30">
						<div class="clearfix">
							<div class="pull-left">
								<h4 class="text-blue h4">Thêm người quản lí</h4>
								<p class="mb-30">[!] Vui lòng điền đầy đủ thông tin</p>
							</div>
						</div>
						<form:form action="ql/qlquanli.htm" method="post">
							<div class="form-group row">
								<label class="col-sm-12 col-md-2 col-form-label">Ngày
									tiếp nhận</label>
								<div class="col-sm-12 col-md-10">
									<input class="form-control" name="ngaytiepnhan"
										placeholder="Chọn ngày sinh" type="date" />
								</div>
							</div>
							<div class="form-group row">
								<label class="col-sm-12 col-md-3 col-form-label">Nhân
									viên</label>
								<div class="col-sm-12 col-md-8">
									<select name="manv" class="selectpicker form-control"
										style="width: 100%; height: 38px;">
										<option value="" label="-Vui lòng chọn 1-" />
										<c:forEach items="${nhanViens}" var="nv">
											<option value="${nv.manv}">${nv.manv}</option>
										</c:forEach>
									</select>
								</div>
							</div>


							<div class="modal-footer">
								<button type="button" class="btn btn-secondary"
									data-dismiss="modal">Đóng</button>
								<button type="submit" class="btn btn-success">Thêm</button>
							</div>
						</form:form>
					</div>
					<!-- Default Basic Forms Start -->
				</div>
			</div>
		</div>
	</div>

	<!-- HIỂN THỊ THÔNG BÁO THÊM THÀNH CÔNG / THẤT BẠI -->
	<c:if test="${insert || update || delete}">
		<script type="text/javascript">
			$(document).ready(function() {
				show_success();

			});
		</script>
	</c:if>
	<!-- HIỂN THỊ THÔNG BÁO CÓ LỖI XẢY RA-->
	<c:if test="${insert == false || update == false || delete == false}">
		<script type="text/javascript">
			$(document).ready(function() {	
				show_error();
			});
		</script>
	</c:if>


	<script type="text/javascript">
		
		//NẾU CLICK NÚT XOÁ
		$('.delete_btn').on('click',function(){
			let ten = $(this).parent().find("input[name='ten']").val();
			let delete_btn = $(this).parent().find('.submit_del_btn');
			const re = confirm("Ban co muon xoa?")
				//Nếu nút đồng ý được nhấn
				if (re) {
					delete_btn.click();
				}
			
		})
		//THÔNG BÁO THÀNH CÔNG
		function show_success() {
			alert("Thao tác thành công! :)) ")
		}

		//THÔNG BÁO LỖI
		function show_error() {
			alert("Thao tác thất bại! :(( ")
		}
		
		function show_error_constraint() {
            alert("Không thể xóa vì nhân viên đã có lập phiếu mượn hoặc phiếu thanh lí hoặc phiếu nhập! :(( ")
        }
	</script>
	<c:if test="${error_constraint}">
		<script type="text/javascript">
			$(document).ready(function() {
				show_error_constraint();

			});
		</script>
	</c:if>
</body>
</html>
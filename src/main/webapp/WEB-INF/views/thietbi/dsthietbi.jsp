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
			src="resources/img/khac/logo.png" width="30"
			height="30" class="d-inline-block align-top" alt=""> HỆ THỐNG QUẢN LÍ THIẾT BỊ HỌC VIỆN CƠ SỞ
		</a>
		<div style="float=right; color:white;">
			Xin chào, ${account_cur.username} | <a style="text-decoration:none; color:red;" href="nv/changePassword.htm">Đổi mật khẩu</a> |<a style="text-decoration:none; color:red;" href="logout.htm">Thoát</a>
		</div>
	</nav>
	<div class="d-md-flex">
		<nav id="sidebar">
			<div class="sidebar-header">
				<h3 style="color:red; font-size: 18px;">Học viện Cơ Sơ</h3>
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
				<li id="qlpm" class="active"><a href="phieumuon/dsphieumuon.htm">Quản lí phiếu mượn</a>
				<li id="ntb"><a href="phieunhap/dspn.htm">Quản lí phiếu nhập</a></li>
				<li id="tltb"><a href="ptl/dsptl.htm">Quản lí phiếu thanh lý</a>
				<li id="qltb"><a href="thietbi/dsthietbi.htm">Quản lí thiết bị</a>
				<li id="qlnm"><a href="nguoimuon/dsnm.htm">Quản lí người mượn</a></li>
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
								Danh sách <b>Thiết bị</b>
							</h2>
						</div>
						<div class="col-4">
							<a id="btn-check" href="#exampleModal" id="insert_btn"
								class="btn btn-primary" data-toggle="modal"
								datas-target="#exampleModalCenter" type="button"
								style="float: right; width: fit-content;">Nhập thiết bị</a>

						</div>
					</div>
				</div>
				<table class="table table-bordered">
					<thead class="table-info">
						<tr>
							<th>Hình ảnh</th>
							<th class="table-plus">Mã thiết bị</th>
							<th>Tên Thiết Bị</th>
							<th>Loại</th>
							<th>Ghi chú</th>
							<th class="pull-right"></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="thietbi" items="${listThietbi}">
							<tr>
								<td><img
									src="resources/img/thietbi/${thietbi.hinh}"
									style="width: 100px; height: 100px"></td>
								<td class="table-plus">${thietbi.matb}</td>
								<td>
									<p
										style="width: 150px; overflow: hidden; text-overflow: ellipsis; white-space: nowrap;">${thietbi.ten}</p>
								</td>
								<td>${thietbi.loaitb}</td>
								<td>
									<p
										style="width: 150px; overflow: hidden; text-overflow: ellipsis; white-space: nowrap;">${thietbi.phong}</p>
								</td>
								<td class="pull-right">
									<div class="row clearfix">
										<div class="col-sm-12 col-md-6">
											<form
												action="thietbi/dsthietbi/edit/${thietbi.matb.trim()}.htm">
												<button class="btn  btn-info bg-edit text-white"
													type="submit" data-toggle="tooltip" data-placement="top"
													title="Sửa">
													<i class="material-icons">edit</i>
												</button>
												<!--  -->
											</form>
										</div>
										<div class="col-sm-12 col-md-6">
											<c:set var="tt" value="0" />
											<c:if test="${!thietbi.tinhtrang.equals(tt)}">
												<form
													action="thietbi/dsthietbi/delete.htm"
													method="post" hidden="true">
													<!-- Dùng để hiển thị tên lên form -->
													<input type="hidden" name="ten" value="${thietbi.ten}" />
													<!-- Dùng để gửi về controller -->
													<input type="hidden" name="id" value="${thietbi.matb}" />
													<button type="submit" class="submit_del_btn"></button>
												</form>
												<button class="btn btn-danger delete_btn"
													data-toggle="tooltip" data-placement="top" title="Xoá"
													type="button">
													<i class="material-icons">delete</i>
												</button>
											</c:if>
											<c:if test="${thietbi.tinhtrang.equals(tt)}">
												<button class="btn btn-secondary" data-toggle="tooltip"
													data-placement="top" title="Xoá" type="button">
													<i class="material-icons">delete</i>
												</button>
											</c:if>
										</div>
									</div>
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

	<div class="modal fade" id="exampleModal"
		tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel"
		aria-hidden="true">
		<div class="modal-dialog modal-xl modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-body">
					<!-- Default Basic Forms Start -->
					<div class="pd-20 card-box mb-30">
						<div class="clearfix">
							<div class="pull-left">
								<h3 class="text-info h3">Thêm thiết bị</h3>
								<p class="mb-30">[!] Vui lòng điền đầy đủ thông tin</p>
							</div>
						</div>
						<form:form
							action="thietbi/dsthietbi/insert.htm"
							modelAttribute="thietbi_moi" method="post"
							enctype="multipart/form-data">
							<div class="row">
								<div class="col-sm-12 col-md-6">
									<div class="form-group row">
										<label class="col-sm-12 col-md-3 col-form-label">Chọn
											ảnh</label>
										<div class="col-sm-12 col-md-8">
											<input name="photo" class="form-control" type="file"
												placeholder="Chọn ảnh thiết bị" required="required" />
										</div>
									</div>
								</div>
								<div class="col-sm-12 col-md-6">
									<div class="form-group row">
										<label class="col-sm-12 col-md-3 col-form-label">Mã
											thiết bị</label>
										<div class="col-sm-12 col-md-9">
										
											<form:input class="form-control" type="text" path="matb"
												placeholder="Nhập mã thiết bị"
												required="required"/>
											<form:errors path="matb" />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-sm-12 col-md-3 col-form-label">Tên
											thiết bị</label>
										<div class="col-sm-12 col-md-9">
											<form:input path="ten" class="form-control" type="text"
												placeholder="Nhập tên thiết bị" />
											<form:errors path="ten" />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-sm-12 col-md-3 col-form-label">Loại</label>
										<div class="col-sm-12 col-md-9">
											<form:input class="form-control" type="text" path="loaitb"
												placeholder="Nhập mã thiết bị"
												required="required"/>
										</div>
									</div>
									<div class="form-group row">
										<label class="col-sm-12 col-md-3 col-form-label">Ghi
											chú</label>
										<div class="col-sm-12 col-md-9">
											<form:input type="text" path="phong" class="form-control"
												placeholder="Phòng"/>
										</div>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-bs-dismiss="modal">Đóng</button>
										<button type="submit" class="btn btn-info">Thêm</button>
									</div>
								</div>
							</div>
						</form:form>
					</div>
					<!-- Default Basic Forms Start -->
				</div>
			</div>
		</div>
	</div>

	<button hidden="true" id="update_modal_btn" data-toggle="modal"
		data-target="#bd-edit-modal-lg"></button>
	<!--======================================================== Dùng để update ============================================================ -->
	<div class="modal fade" id="bd-edit-modal-lg"
		tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel"
		aria-hidden="true">
		<div class="modal-dialog modal-lg modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-body">
					<!-- Default Basic Forms Start -->
					<div class="pd-20 card-box mb-30">
						<div class="clearfix">
							<div class="pull-left">
								<h4 class="text-blue h4">Sửa thiết bị</h4>
								<p class="mb-30">[!] Vui lòng điền đầy đủ thông tin</p>
							</div>
						</div>
						<form:form
							action="thietbi/dsthietbi/update.htm"
							modelAttribute="thietbi_sua" method="post"
							enctype="multipart/form-data">
							<div class="row">
								<div class="col-sm-12 col-md-6">
									<div class="form-group row">
										<label class="col-sm-12 col-md-3 col-form-label">Chọn
											ảnh</label>
										<div class="col-sm-12 col-md-8">
											<img
												src="resources/img/thietbi/${thietbi_sua.hinh}"
												style="width: 250px; height: 200px" /> <input name="photo"
												class="form-control" type="file"
												placeholder="Chọn ảnh thiết bị" />
											<form:input path="hinh" class="form-control"
												style="display: none" />
										</div>
									</div>
								</div>
								<div class="col-sm-12 col-md-6">
									<div class="form-group row">
										<label class="col-sm-12 col-md-3 col-form-label">Mã
											thiết bị</label>
										<div class="col-sm-12 col-md-9">
											<input class="form-control" value="${thietbi_sua.matb}"
												readonly />
											<form:input class="form-control" type="text" path="matb"
												placeholder="Nhập mã thiết bị" style="display: none" />
											<form:errors path="matb" />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-sm-12 col-md-3 col-form-label">Tên
											thiết bị</label>
										<div class="col-sm-12 col-md-9">
											<form:input path="ten" class="form-control" type="text"
												placeholder="Nhập tên thiết bị" />
											<form:errors path="ten" />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-sm-12 col-md-3 col-form-label">Loại</label>
										<div class="col-sm-12 col-md-9">
											<%-- <form:select class="selectpicker form-control" path="loaitb"
												style="width: 100%; height: 38px;">
												<form:option value="" label="-Vui lòng chọn 1-" />
												<form:options items="${loaiThietBis}" itemValue="loaitb"
													itemLabel="loaitb" />
											</form:select> --%>
											<form:input path="loaitb" class="form-control" value="${thietbi_sua.loaitb}"
												/>
										</div>
									</div>
									<div class="form-group row">
										<label class="col-sm-12 col-md-3 col-form-label">Loại</label>
										<div class="col-sm-12 col-md-9">
											<form:select class="selectpicker form-control" path="tinhtrang"
												style="width: 100%; height: 38px;">
												<c:if test="${thietbi_sua.tinhtrang = '0' }">
													<form:option value="0" label="Còn mới"/>
												</c:if>
												<c:if test="${thietbi_sua.tinhtrang = '1' }">
													<form:option value="1" label="Cũ"/>
												</c:if>
												<form:option value="0" label="Còn mới"/>
												<form:option value="1" label="Cũ"/>
											</form:select>
										</div>
									</div>
									<div class="form-group row">
										<label class="col-sm-12 col-md-3 col-form-label">Ghi
											chú</label>
										<div class="col-sm-12 col-md-9">
											<form:input path="phong" class="form-control" type="text"
												placeholder="phong" />
										</div>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-dismiss="modal">Đóng</button>
										<button type="submit" class="btn btn-info">Sửa</button>
									</div>
								</div>
							</div>
						</form:form>
					</div>
					<!-- Default Basic Forms Start -->
				</div>
			</div>
		</div>
	</div>
	<!-- DÙNG ĐỂ SHOW FORM EDIT -->
	<c:if test="${form_edit_loaiTB}">
		<script type="text/javascript">
			$(document).ready(function() {
				$('#dsLoaiTbBtn').click();
				console.log("Hiển thị edit form")
			});
		</script>
	</c:if>
	<c:if test="${form_edit}">
		<script type="text/javascript">
			$(document).ready(function() {
				$('#update_modal_btn').click();
				console.log("Hiển thị edit form")
			});
		</script>
	</c:if>
	<!-- DÙNG ĐỂ SHOW FORM DELETE -->
	<c:if test="${form_del}">
		<script type="text/javascript">
			$(document).ready(function() {
				$('#update_modal_btn').click();
				console.log("Hiển thị del form")
			});
		</script>
	</c:if>
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
		$('.delete_btn').on('click', function() {
			let ten = $(this).parent().find("input[name='ten']").val();
			let delete_btn = $(this).parent().find('.submit_del_btn');
			const re = confirm("Ban co muon xoa?");
			//Nếu nút đồng ý được nhấn
			if (re) {
				delete_btn.click();
			}

		});

		//NẾU CLICK NÚT XOÁ LOẠI THIẾT BỊ
		$('.tableLoaiThietBi').on('click', '.delete_btn', function() {
			let ten = $(this).parent().find("input[name='ten']").val();
			let delete_btn = $(this).parent().find('.submit_del_btn');
			const re = confirm("Ban co muon xoa?")
			//Nếu nút đồng ý được nhấn
			if (re) {
				delete_btn.click();
			}

		});

		//THÔNG BÁO THÀNH CÔNG
		function show_success() {
			alert("Thao tác thành công! :)) ")
		}

		//THÔNG BÁO LỖI
		function show_error() {
			alert("Thao tác thất bại! :(( ")
		}
		
		function show_error_constraint() {
	        alert("Không thể xóa vì thiết bị đã có phiếu mượn! :(( ")
	    }
	</script>
	<!-- IMPORT EXCEL -->
	<script type="text/javascript">
		$('.import-excel').on('click', function() {
			let inputFile = $(this).parent().find("input[type='file']")
			let btnSubmit = $(this).parent().find("input[type='submit']")

			inputFile.on('change', function() {
				btnSubmit.click()
			})
			inputFile.click();
		})
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
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
<link href="resources/css/dsnm.css" rel="stylesheet" type="text/css">
<title>Danh sách người mượn</title>
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
		<a class="navbar-brand" href="phieumuon/dsphieumuon.htm"
			style="color: white;"> <img src="resources/img/khac/logo.png"
			width="30" height="30" class="d-inline-block align-top" alt="">
			HỆ THỐNG QUẢN LÍ THIẾT BỊ HỌC VIỆN CƠ SỞ
		</a>
		<div style="color: white;">
			Xin chào, ${account_cur.username} | <a
				style="text-decoration: none; color: red;"
				href="nv/changePassword.htm">Đổi mật khẩu</a> |<a
				style="text-decoration: none; color: red;" href="logout.htm">Thoát</a>
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
						<p style="color: yellow; font-size: 15px; text-align: center;">Quản
							lí</p>
					</c:otherwise>
				</c:choose>
				<li id="qlpm" class="active"><a
					href="phieumuon/dsphieumuon.htm">Quản lí phiếu mượn</a>
				<li id="ntb"><a href="phieunhap/dspn.htm">Quản lí phiếu
						nhập</a></li>
				<li id="tltb"><a href="ptl/dsptl.htm">Quản lí phiếu thanh
						lý</a>
				<li id="qltb"><a href="thietbi/dsthietbi.htm">Quản lí thiết
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
								Danh sách <b>Người mượn</b>
							</h2>
						</div>
						<div class="col-4">
							<a id="btn-check" href="#bd-example-modal-lg" id="insert_btn"
								class="btn btn-primary" data-toggle="modal"
								datas-target="#bd-example-modal-lg" type="button"
								style="float: right; width: fit-content;">Thêm người mượn</a>

						</div>
					</div>
				</div>
				<table class="table table-bordered">
					<thead class="table-info">
						<tr>
							<th class="table-plus datatable-nosort">Mã NM</th>
							<th>Họ và tên</th>
							<!-- 									<th>Giới tính</th> -->
							<!-- 									<th>Ngày sinh</th> -->
							<th>Địa chỉ</th>

							<th>SDT</th>

							<!-- 									<th>Ghi chú</th> -->
						</tr>
					</thead>
					<tbody>
						<c:forEach var="nguoimuon" items="${listNguoiMuon}"
							varStatus="row">
							<tr>

								<td class="table-plus">${nguoimuon.manm}</td>
								<td><p
										style="width: 150px; overflow: hidden; text-overflow: ellipsis; white-space: nowrap;">${nguoimuon.ho}
										${nguoimuon.ten}</p></td>
								<%-- 										<td>${nguoimuon.gioitinh}</td> --%>
								<%-- 										<td>${nguoimuon.ngaysinh}</td> --%>
								<td><p
										style="width: 150px; overflow: hidden; text-overflow: ellipsis; white-space: nowrap;">${nguoimuon.diachi}</p></td>
								<td>${nguoimuon.sdt}</td>
								<td>
									<!-- 												<div class=""> -->
									<form action="nguoimuon/dsnm/edit/${nguoimuon.manm.trim()}.htm">
										<button class="btn btn-info bg-edit " type="submit"
											data-toggle="tooltip" data-placement="top" title="Sửa">
											<i class="material-icons">edit</i>
										</button>
										<!--  -->
									</form>
								</td>
								<td>
									<form action="nguoimuon/dsnm/delete.htm" method="post">
										<!-- Dùng để hiển thị tên lên form -->
										<input type="hidden" name="ten" value="${nguoimuon.ten}" />

										<!-- Dùng để gửi về controller -->
										<input type="hidden" name="manm" value="${nguoimuon.manm}" />
										<button type="submit" style="display: none"
											class="submit_del_btn"></button>

										<button class="btn btn-danger delete_btn"
											data-toggle="tooltip" data-placement="top" title="Xoá"
											type="button">
											<i class="material-icons">delete</i>
										</button>
									</form> <!-- 											</div> -->
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

	<!-- 	======================   Thêm Người mượn ============================ -->
	<div class="modal fade" id="bd-example-modal-lg" tabindex="-1"
		role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-xl modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header">
					<div class="clearfix">
						<div class="pull-left">
							<h3 class="text-info">Thêm người mượn</h3>
							<p class="mb-30">[!] Vui lòng điền đầy đủ thông tin</p>
						</div>
					</div>
				</div>
				<div class="modal-body">
					<!-- Default Basic Forms Start -->
					<div class="pd-20 card-box mb-30">
						<form:form action="nguoimuon/dsnm/insert.htm" onsubmit="return validate()"
							modelAttribute="nguoimuon_moi" method="post">
							<div class="row">
								<div class="col-sm-12 col-md-6">
									<div class="form-group row">
										<label class="col-sm-12 col-md-3 col-form-label">Mã
											người mượn</label>
										<div class="col-sm-12 col-md-9">
											<form:errors path="manm" />
											<%-- 											<input class="form-control" value="${maNguoiMuon}" --%>
											<!-- 												readonly /> -->
											<form:input class="form-control" type="text" path="manm"
												value="${maNguoiMuon}" placeholder="Nhập mã người mượn"
												required="required" />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-sm-12 col-md-3 col-form-label">Họ và
											chữ lót</label>
										<div class="col-sm-12 col-md-9">
											<form:errors path="ho" />
											<form:input class="form-control" type="text" path="ho"
												placeholder="Nhập họ và chữ lót" required="required" />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-sm-12 col-md-3 col-form-label">Tên</label>
										<div class="col-sm-12 col-md-9">
											<form:errors path="ten" />
											<form:input class="form-control" type="text" path="ten"
												placeholder="Nhập tên" required="required" />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-sm-12 col-md-3 col-form-label">Giới
											tính</label>
										<div class="col-sm-12 col-md-9">
											<form:errors path="gioitinh" />
											<form:select path="gioitinh"
												class="selectpicker form-control"
												style="width: 100%; height: 38px;" required="required">
												<form:option value="" label="-Vui lòng chọn 1-" />
												<form:options items="${gioiTinhs}" />
											</form:select>
										</div>
									</div>
									<div class="form-group row">
										<label class="col-sm-12 col-md-3 col-form-label">Ngày
											sinh</label>
										<div class="col-sm-12 col-md-9">
											<form:errors path="ngaysinh" />
											<form:input class="form-control" path="ngaysinh"
												placeholder="Chọn ngày sinh" type="date" required="required" />
										</div>
									</div>
								</div>
								<div class="col-sm-12 col-md-6">

									<div class="form-group row">
										<label class="col-sm-12 col-md-3 col-form-label">Địa
											chỉ</label>
										<div class="col-sm-12 col-md-9">
											<form:errors path="diachi" />
											<form:input class="form-control" type="text" path="diachi"
												placeholder="Nhập địa chỉ" required="required" />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-sm-12 col-md-3 col-form-label">Số
											điện thoại</label>
										<div class="col-sm-12 col-md-9">
											<form:input id="sdttext" path="sdt" class="form-control"
												type="text" placeholder="Nhập số điện thoại"
												required="required" />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-sm-12 col-md-3 col-form-label">CMND</label>
										<div class="col-sm-12 col-md-9">
											<form:input id="cmndtext" path="cmnd" class="form-control" type="text"
												placeholder="Nhập CMND" required="required" />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-sm-12 col-md-3 col-form-label">Ghi
											chú</label>
										<div class="col-sm-12 col-md-9">
											<form:input id="cmndtext" class="form-control" type="text"
												path="ghichu" placeholder="Nhập ghi chú" />
										</div>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-dismiss="modal">Đóng</button>
										<button type="submit"
											class="btn btn-info">Thêm</button>
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

	<div class="modal fade" id="bd-edit-modal-lg" tabindex="-1"
		role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-xl modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header">
					<div class="clearfix">
						<div class="pull-left">
							<h3 class="text-info">Cập nhật người mượn</h3>
							<p class="mb-30">[!] Vui lòng điền đầy đủ thông tin</p>
						</div>
					</div>
				</div>
				<div class="modal-body">
					<!-- Default Basic Forms Start -->
					<div class="pd-20 card-box mb-30">
						<form:form action="nguoimuon/dsnm/update.htm" onsubmit="return validateSua()"
							modelAttribute="nguoimuon_sua" method="post">
							<div class="row">
								<div class="col-sm-12 col-md-6">
									<div class="form-group row">
										<label class="col-sm-12 col-md-3 col-form-label">Mã
											người mượn</label>
										<div class="col-sm-12 col-md-9">
											<form:errors path="manm" />
											<input class="form-control" value="${nguoimuon_sua.manm}"
												readonly />
											<form:input class="form-control" type="text" path="manm"
												placeholder="Nhập mã người mượn" required="required"
												style="display: none" />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-sm-12 col-md-3 col-form-label">Họ và
											chữ lót</label>
										<div class="col-sm-12 col-md-9">
											<form:errors path="ho" />
											<form:input class="form-control" type="text" path="ho"
												placeholder="Nhập họ và chữ lót" required="required" />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-sm-12 col-md-3 col-form-label">Tên</label>
										<div class="col-sm-12 col-md-9">
											<form:errors path="ten" />
											<form:input class="form-control" type="text" path="ten"
												placeholder="Nhập tên" required="required" />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-sm-12 col-md-3 col-form-label">Giới
											tính</label>
										<div class="col-sm-12 col-md-9">
											<form:errors path="gioitinh" />
											<form:select path="gioitinh"
												class="selectpicker form-control"
												style="width: 100%; height: 38px;" required="required">
												<form:option value="" label="-Vui lòng chọn 1-" />
												<form:options items="${gioiTinhs}" />
											</form:select>
										</div>
									</div>
									<div class="form-group row">
										<label class="col-sm-12 col-md-3 col-form-label">Ngày
											sinh</label>
										<div class="col-sm-12 col-md-9">
											<form:errors path="ngaysinh" />
											<form:input class="form-control" path="ngaysinh"
												placeholder="Chọn ngày sinh" type="date" required="required" />
										</div>
									</div>
								</div>
								<div class="col-sm-12 col-md-6">

									<div class="form-group row">
										<label class="col-sm-12 col-md-3 col-form-label">Địa
											chỉ</label>
										<div class="col-sm-12 col-md-9">
											<form:errors path="diachi" />
											<form:input class="form-control" type="text" path="diachi"
												placeholder="Nhập địa chỉ" required="required" />
										</div>
									</div>

									<div class="form-group row">
										<label class="col-sm-12 col-md-3 col-form-label">Số
											điện thoại</label>
										<div class="col-sm-12 col-md-9">
											<form:errors path="sdt" />
											<form:input id="sdttextSua" value="${nguoimuon_sua.sdt }"
												path="sdt" class="form-control" type="text"
												placeholder="Nhập số điện thoại" required="required" />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-sm-12 col-md-3 col-form-label">CMND</label>
										<div class="col-sm-12 col-md-9">
											<form:input id="cmndtextSua" value="${nguoimuon_sua.cmnd }"
												path="cmnd" class="form-control" type="text"
												placeholder="Nhập CMND" required="required" />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-sm-12 col-md-3 col-form-label">Ghi
											chú</label>
										<div class="col-sm-12 col-md-9">
											<form:input class="form-control" type="text" path="ghichu"
												placeholder="Nhập ghi chú" />
										</div>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-dismiss="modal">Đóng</button>
										<button type="submit"
											class="btn btn-info">Sửa</button>
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
	<c:if test="${form_edit}">
		<script type="text/javascript">
			$(document).ready(function() {
				$('#update_modal_btn').click();
				console.log("Hiển thị edit form")
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
	<c:if test="${error_constraint}">
		<script type="text/javascript">
			$(document).ready(function() {
				show_error_constraint();

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
			alert("Không thể xóa vì người mượn đã có phiếu mượn! :(( ")
		}
		function limit_cmnd(element) {
			var max_chars = 12;

			if (element.value.length > max_chars) {
				element.value = element.value.substr(0, max_chars);
			}
		}
		function limit_sdt(element) {
			var max_chars = 10;

			if (element.value.length > max_chars) {
				element.value = element.value.substr(0, max_chars);
			}
		}

		function validate() {
			let inputtxt = document.getElementById('sdttext').value
			console.log(inputtxt)
			let phoneno = /((09|03|07|08|05)+([0-9]{8})\b)/g;
			if (!inputtxt.match(phoneno)) {
				alert("Số diện thoại không hợp lệ");
				return false;
			}
			let ChungMinhPattern = /[0-9]{9}/;

			let ChungMinh = document.getElementById('cmndtext').value;
			console.log(ChungMinh )
			if (!ChungMinh.match(ChungMinhPattern)) {
				alert("Số chứng minh nhân dân phải đủ 9 chữ số");
				return false;
			}
			return true;
		}

		function validateSua() {
			let inputtxt = document.getElementById('sdttextSua').value
			console.log(inputtxt)
			let phoneno = /((09|03|07|08|05)+([0-9]{8})\b)/g;
			if (!inputtxt.match(phoneno)) {
				alert("Số diện thoại không hợp lệ");
				return false;
			}
			let ChungMinhPattern = /[0-9]{9}/;

			let ChungMinh = document.getElementById('cmndtextSua').value;
			console.log(ChungMinh )
			if (!ChungMinh.match(ChungMinhPattern)) {
				alert("Số chứng minh nhân dân phải đủ 9 chữ số");
				return false;
			}
			return true;
		}
	</script>
</body>
</html>
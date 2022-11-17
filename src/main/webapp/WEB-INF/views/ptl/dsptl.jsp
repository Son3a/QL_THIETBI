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
<link href="resources/css/dsptl.css" rel="stylesheet" type="text/css">
<title>Danh sách phiếu thanh lý</title>
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
						<p style="color: yellow; font-size: 15px; text-align: center;">Quản lí</p>
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
								Danh sách <b>Phiếu thanh lý</b>
							</h2>
						</div>
						<div class="col-4">
							<a id="btn-check" href="#exampleModalCenter" id="insert_btn"
								class="btn btn-primary" data-toggle="modal"
								datas-target="#exampleModalCenter" type="button"
								style="float: right; width: fit-content;">Tạo phiếu thanh lý</a>

						</div>
					</div>
				</div>
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>Mã Phiếu Thanh Lý</th>
							<th>Ngày Thanh Lý</th>
							<th>Nhân Viên Xử Lý</th>
							<th>Ghi chú</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="ptl" items="${listPhieuThanhLy}">

							<tr>
								<td class="table-plus mapn">${ptl.maptl}</td>
								<td>${ptl.tgthanhly}</td>
								<c:set var="hoten"
									value="${ptl.nhanvien_thanhly.ho} ${ptl.nhanvien_thanhly.ten}" />
								<td>${hoten}</td>

								<!-- Ghi chú -->
								<td><c:choose>
										<c:when test="${ptl.ghichu.isEmpty() || ptl.ghichu.isBlank()}">
											<label>-</label>
										</c:when>
										<c:otherwise>
											<label>${ptl.ghichu}</label>
										</c:otherwise>
									</c:choose></td>
								<td><c:choose>
										<c:when test="${ptl.tgthanhly == null}">

											<a data-toggle="tooltip"
												href="ptl/dsptl/edit/${ptl.maptl}.htm" data-placement="top"
												title="Sửa" style="color: blue"><i class="fas fa-edit"></i></a>
											<!-- XOÁ -->
											<form style="display: none;"
												action="ptl/dsptl/delete.htm" method="post">
												<input type="hidden" name="maptl" value="${ptl.maptl}" />
												<button type="submit" style="display: none"
													class="submit_del_btn"></button>
											</form>
											<a class="delete_btn" type="button"> <i
												data-toggle="tooltip" data-placement="top" title="Xoá"
												style="color: red"><i class="fas fa-trash"></i></i>
											</a>
										</c:when>
										<c:otherwise>
											<a data-toggle="tooltip"
												href="ptl/dsptl/edit/${ptl.maptl}.htm" data-placement="top"
												title="Xem chi tiết" style="color: blue"><i
												class="fas fa-info"></i></a>
										</c:otherwise>
									</c:choose></td>
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

	<!-- Modal -->
	<div class="modal fade" id="exampleModalCenter" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalCenterTitle"
		aria-hidden="true">
		<div class="modal-dialog modal-xl">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLongTitle">Thêm phiếu thanh lý</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="pd-20 card-box mb-30">
						<form:form action="ptl/dsptl/insert.htm" method="post"
							modelAttribute="phieuthanhly_them" class="modal-content">
							<div class="modal-body">
								<div class="invoice-header text-center">
									<h4 class="text-info h4">THÊM PHIẾU THANH LÝ</h4>
								</div>
								<!-- Nhập thông tin cơ bản phiếu nhập -->
								<div class=" row">
									<div class="col form-group">
										<div class="row">
											<label class="col-sm-4 col-md-4 col-form-label">Mã
												phiếu thanh lý</label>
											<div class="col-sm-4 col-md-8">
												<input class="form-control" type="text" name="maptl"
													value="${newID}" readonly="readonly" />
											</div>
										</div>
										<div class="row py-2">
											<label class="col-sm-4 col-md-4 col-form-label">Ngày
												thanh lý: </label>
											<div class="col-sm-4 col-md-8">
												<input name="thoigian" class="form-control" type="date"
													value="${today}" readonly="readonly" />
											</div>
										</div>
									</div>
									<div class=" col form-group">
										<div class="row">
											<label class="col-sm-6 col-md-4 col-form-label">Nhân
												Viên xử lý:</label>
											<div class="col-sm-4 col-md-8">
												<input class="col-sm-4 col-md-8 form-control" type="text"
													name="nhanvien_thanhly.manv" value="${nv.manv}"
													readonly="readonly" />
											</div>
										</div>
										<div class="row py-2">
											<label class="col-sm-4 col-md-4 col-form-label">Ghi
												chú:</label>
											<div class="col-sm-4 col-md-8">
												<input class="form-control col-sm-4 col-md-8" name="ghichu"
													placeholder="Nhập ghi chú" />
											</div>
										</div>
									</div>
								</div>
								<hr>
								<h5 class="h5 invoice-header text-center">DANH SÁCH THIẾT
									BỊ THANH LÝ</h5>
								<!-- Nhập thông tin cơ bản phiếu nhập -->
								<table class="table dsThietbi_table">
									<thead>
										<tr>
											<th>Thiết bị</th>
											<th>Đơn giá</th>
											<th></th>
										</tr>
									</thead>
									<tbody>
									</tbody>
									<tfoot>
										<tr>
											<th></th>
											<th></th>
											<th class="tongcong"></th>
										</tr>
										<tr id="hidden-row" hidden=true>
											<td><select class="form-control" name="matb">
													<c:forEach var="tb" items="${listThietbi}">
														<option value="${tb.matb}">${tb.ten}</option>
													</c:forEach>
											</select></td>
											<td style="display: none;"><input type="number" min="1"
												name="soluong" class=" form-control" value="1"
												onchange="tinhtong($(this).parents('tbody'))" required /></td>
											<td><input type="number" min="0" step="0.01"
												name="dongia" class=" form-control"
												onchange="tinhtong($(this).parents('tbody'))" required /></td>
											<td><a
												class="material-icons text-info btn-close-item  text-right"
												type="button"> <i class="fas fa-trash-alt"></i>
											</a></td>
										</tr>
									</tfoot>
								</table>
								<div class="row form-group them-tb-nhap">
									<button type='button' class="form-control text-info ">
										<i class="fas fa-plus-circle"></i> Thêm thiết bị nhập
									</button>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary"
									data-bs-dismiss="modal">Đóng</button>
								<input type="hidden" name="trangthai" />
								<button type="submit" class="btn btn-primary luu-btn">Lưu
									tạm</button>
								<input type="submit" hidden="hidden" />
								<button type="button" class="btn btn-success them-btn">Thêm</button>
							</div>
						</form:form>
					</div>
				</div>

			</div>
		</div>
	</div>

	<button hidden="true" id="update_modal_btn" data-toggle="modal"
		data-target="#modal_pn_sua"></button>
	<div class="modal fade" id="modal_pn_sua" tabindex="-1" role="dialog"
		aria-labelledby="myLargeModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg modal-dialog-centered">
			<form:form action="ptl/dsptl/update.htm" method="post"
				class="modal-content" modelAttribute="phieuthanhly_sua">
				<c:if test="${form_edit}">
					<%@include file="/WEB-INF/views/thanhliComponent/form_edit_ptl.jsp"%>
				</c:if>
				<c:if test="${form_info}">
					<%@include file="/WEB-INF/views/thanhliComponent/form_info_ptl.jsp"%>
				</c:if>
			</form:form>
		</div>
	</div>

	<!-- DÙNG ĐỂ SHOW FORM EDIT -->
	<c:if test="${form_edit || form_info}">
		<script type="text/javascript">
			$(document).ready(function() {
				$('#update_modal_btn').click();
				console.log("Hiển thị edit form")
				tinhtong($('#modal_pn_sua').find('tbody'))

			});
		</script>
	</c:if>
	<!-- Dùng để tính tổng tiền chi tiết phiếu -->
	<script type="text/javascript">
		function tinhtong(e) {
			let soluong = $(this).parents('tr').find("input[name='soluong']")
					.val()
			let dongia = $(this).parents('tr').find("input[name='dongia']")
					.val()
			if (dongia < 0 || soluong < 0)
				return false;

			let tongcong = 0;
			console.log(e)
			console.log(soluong)
			console.log(dongia)
			e.find('tr').each(function(index, tr) {
				let soluong = $(this).find("input[name='soluong']").val()
				let dongia = $(this).find("input[name='dongia']").val()
console.log(soluong)
			console.log(dongia)
				tongcong += soluong * dongia
			});

			$('.tongcong').text('Tổng cộng: ' + tongcong + ' VNĐ');
		}

		$("a[href='#exampleModalCenter']").on('click', function() {
			tinhtong($('#exampleModalCenter').find('tbody'))
		})
	</script>
	<!-- DÙNG ĐỂ SHOW FORM DELETE -->
	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							//NẾU CLICK NÚT XOÁ
							$('.delete_btn')
									.on(
											'click',
											function() {
												let delete_btn = $(this).parent().find('.submit_del_btn');
												const re =confirm('Bạn có muốn xóa?');
													//Nếu nút đồng ý được nhấn
													if (re) {
														delete_btn.click();
													}

											})
						});
	</script>
	<!-- HIỂN THỊ THÔNG BÁO THÊM THÀNH CÔNG / THẤT BẠI -->
	<script type="text/javascript">
		//THÔNG BÁO THÀNH CÔNG
		function show_success() {
			alert("Thao tác thành công! :)) ");
		}

		//THÔNG BÁO LỖI
		function show_error() {
			alert("Thao tác thất bại! :(( ");
		}
	</script>
	<script type="text/javascript">
		//CLICK THÊM DÒNG MỚI		
		$('.them-tb-nhap').on('click', function() {
			let new_row = document.createElement('tr');
			new_row.innerHTML = $('#hidden-row').html()
			$(this).parent().find('tbody').append(new_row);
		})

		//ClICK XOÁ 1 DÒNG THIẾT BỊ
		$('.dsThietbi_table').on('click', '.btn-close-item', function() {
			$(this).parents('tr').remove();
		})

		//CLICK NÚT THÊM PHIẾU NHẬP
		$('.them-btn').on('click', function() {
			let trangthai = $(this).parent().find("input[name='trangthai']")
			const re = confirm("Tạo phiếu nhập?")
			//Nếu nút đồng ý được nhấn
			if (re) {
				const today = new Date();
				const yyyy = today.getFullYear();
				let mm = today.getMonth() + 1; // Months start at 0!
				let dd = today.getDate();

				if (dd < 10)
					dd = '0' + dd;
				if (mm < 10)
					mm = '0' + mm;

				const now = dd + '-' + mm + '-' + yyyy;

				trangthai.val(now)
				$('#hidden-row').remove()
				$(this).parent().find("input[type='submit']").click();
				console.log(trangthai.val())
			}

		});

		//CLICK LƯU PHIẾU NHẬP
		$('.luu-btn').on('click', function() {
			let trangthai = $(this).parent().find("input[name='trangthai']")
			trangthai.val("cho")
			$('#hidden-row').remove()
			console.log(trangthai.val())
		});

		//THÔNG BÁO THÀNH CÔNG
		function show_success() {
			alert("Thao tác thành công! :)) ");
		}

		//THÔNG BÁO LỖI
		function show_error(content) {
			alert("Thao tác thất bại! :(( ")
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
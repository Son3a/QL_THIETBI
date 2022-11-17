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
<link href="resources/css/dsphieumuon.css" rel="stylesheet"
	type="text/css">
<title>Danh sách phiếu mượn</title>
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
<style>
	.widthset{
		margin-left: 189px;
	}
</style>
</head>
<body>
	<!-- Image and text -->
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
								Danh sách <b>Phiếu mượn</b>
							</h2>
						</div>
						<div class="col-4">
							<button id="btn-check" data-toggle="modal"
								data-target="#exampleModalCenter" type="button"
								class="btn btn-primary"
								style="float: right; width: fit-content;">Tạo phiếu
								mượn</button>
						</div>
					</div>
				</div>
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>#</th>
							<th>Mã phiếu mượn <i class="fa fa-sort"></i></th>
							<th>Người mượn</th>
							<th>Thời gian mượn <i class="fa fa-sort"></i></th>
							<th>Tình trạng</th>
							<th>Phòng</th>
							<th>Nhân viên lập phiếu <i class="fa fa-sort"></i></th>
							<th>Thao tác</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="i" items="${listPhieuMuon}" varStatus="st">
							<tr>
								<td>${st.count}</td>
								<td>${i.mapm}</td>
								<td>${i.nm.ho} ${i.nm.ten}</td>
								<td>${i.tgmuon}</td>
								<td>
									<c:if test="${i.trangthai ==1 }">
										Đã trả
									</c:if>
									<c:if test="${i.trangthai ==0 }">
										Chưa trả
									</c:if>
								</td>
								<td>${i.phong}</td>
								<td>${i.nhanvien_pm.manv}</td>
								<td>
									<c:if test="${i.trangthai ==0 }">
										<a
									href="phieumuon/dsphieumuon/detail/${i.mapm.trim()}.htm"
									class="view" title="View" data-toggle="tooltip"><i
										class="fas fa-info"></i></a> <a
									href="phieumuon/dsphieumuon/edit/${i.mapm.trim()}.htm"
									class="edit" title="Edit" data-toggle="tooltip"><i
										class="fas fa-edit"></i></a>
									<form style="display: none;"
										action="phieumuon/dsphieumuon/delete.htm" method="post">
										<input type="hidden" name="mapm" value="${i.mapm}" />
										<button type="submit" style="display: none"
											class="submit_del_btn"></button>
									</form> <a class="delete delete_btn" title="Delete"
									data-toggle="tooltip"><i class="fas fa-trash-alt"></i></a>
									</c:if>
									<c:if test="${i.trangthai ==1 }">
										<a
									href="phieumuon/dsphieumuon/detail/${i.mapm.trim()}.htm"
									class="view" title="View" data-toggle="tooltip"><i
										class="fas fa-info"></i></a> 
									</c:if>
									
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

	<!-- Modal -->
	<div class="modal fade" id="exampleModalCenter" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalCenterTitle"
		aria-hidden="true">
		<div class="modal-dialog modal-xl">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLongTitle">Thêm phiếu mượn</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="pd-20 card-box mb-30">
						<form:form action="phieumuon/dsphieumuon/insert.htm"
							modelAttribute="phieumuon_moi" method="post">
							<!-- 							=================================== cột 1 ========================================= -->
							<div class="row">
								<div class="col-sm-12 col-md-6">
									<div class="form-group row py-2">
										<label class="col-sm-4 col-md-4 col-form-label">Mã
											phiếu mượn</label>
										<div class="col-sm-4 col-md-8">
											<form:errors path="mapm" />
											<input class="form-control" name="mapm_temp"
												value="${maphieumuon}" readonly />
											<form:input class="form-control" type="text" path="mapm"
												value="${maphieumuon}" placeholder="Nhập mã phiếu mượn"
												required="required" style="display: none" />
										</div>
									</div>
									<div class="form-group row py-2">
										<label class="col-sm-4 col-md-4 col-form-label">Người
											mượn</label>
										<input required="required" style="margin-left: 15px;height: 38px;"  type="text" size="25" ID="searchFor" onkeyup="searchSelect(this.id,'searchIn')">
										<br />
										<br />
										<div class="widthset col-sm-4 col-md-8">
											<!-- 											Đối tượng này dễ sai khi không .manm -->
											<form:errors path="nm" />
											<form:select onkeyup="document.getElementById('btn-them').disabled = false;" class="selectpicker form-control" path="nm.manm"
												required="required"
												multiple="multiple" name="searchIn" id="searchIn" onchange="binding()"
												>
												<form:option value="" label="Chọn người mượn:">
												</form:option>
												<c:forEach var="nm" items="${listNguoiMuons}">
													<form:option value="${nm.manm }"
														label="${nm.manm } - ${nm.ho } ${nm.ten }"></form:option>
												</c:forEach>
											</form:select>
										</div>
									</div>
									<div class="form-group row py-2">
										<label class="col-sm-4 col-md-4 col-form-label">Nhân
											viên</label>
										<div class="col-sm-4 col-md-8">
											<form:errors path="nhanvien_pm.manv" />
											<c:if
												test="${accountLogined.listNhanVien.get(0).isadmin!=null}">
												<form:select class="selectpicker form-control"
													path="nhanvien_pm.manv" style="width: 100%;height: 38px;"
													required="required">

													<form:option
														value="${account_cur.listNhanVien.get(0).manv}"
														label="${account_cur.listNhanVien.get(0).ho} ${account_cur.listNhanVien.get(0).ten}">
													</form:option>
													<c:forEach var="nv" items="${listNhanViens}">
														<form:option value="${nv.manv }"
															label="${nv.ho } ${nv.ten }"></form:option>
													</c:forEach>
												</form:select>
											</c:if>
											<c:if
												test="${accountLogined.listNhanVien.get(0).isadmin==null}">
												<form:select class="selectpicker form-control"
													path="nhanvien_pm.manv" style="width: 100%;height: 38px;"
													required="required">
													<form:option
														value="${account_cur.listNhanVien.get(0).manv}"
														label="${account_cur.listNhanVien.get(0).ho} ${account_cur.listNhanVien.get(0).ten}">
													</form:option>
												</form:select>
											</c:if>
										</div>
									</div>
									<div class="form-group row py-2">
										<label class="col-sm-4 col-md-4 col-form-label">Phòng</label>
										<div class="col-sm-4 col-md-8">
											<form:errors path="phong" />
											<form:select class="selectpicker form-control"
													path="phong" style="width: 100%;height: 38px;"
													required="required" id="phong" onChange="filter()">
													<option></option>
													<c:forEach items="${listPhong}" var="p">
														<option value="${p}">${p}</option>
													</c:forEach>
											</form:select>
										</div>
									</div>
									<div class="form-group row py-2">
										<label class="col-sm-4 col-md-4 col-form-label">Thời
											gian mượn</label>
										<div class="col-sm-4 col-md-8">
											<form:input class="form-control" path="tgmuon"
												value="${today}" readonly="true" type="date"
												required="required" />
										</div>
									</div>
									<div class="form-group row py-2">
										<label class="col-sm-4 col-md-4 col-form-label">Ghi
											chú</label>
										<div class="col-sm-4 col-md-8">
											<%-- <form:errors path="diachi" /> --%>
											<form:input class="form-control" type="text" path="ghichu"
												placeholder="Nhập ghi chú" />
										</div>
									</div>
								</div>
								<!-- 							====================================== Cột 2 =================================================== -->
								<div class="col-sm-12 col-md-6" id='parent-element'>
									<div class="hidden-element" style="display: none">
										<div class="form-group row py-1 px-1">
											<label class="col-sm-4 col-md-2 col-form-label">Thiết
												bị </label>
											<div class="col-md-5">
												<select id="filterTB" class="form-control" name="replacehere"
													style="width: 100%; height: 45px;">
													<c:forEach var="tb" items="${loaiThietBis}">
														<option value="${tb.matb}">${tb.ten}</option>
													</c:forEach>
												</select>
												<select  id="filterTBHidden" class="form-control"
													style="width: 100%; height: 45px; display:none;">
													<c:forEach var="tb" items="${loaiThietBis}">
														<option value="${tb.matb}">${tb.phong}</option>
													</c:forEach>
												</select>
											</div>
											<div class="col-md-2">
												<a onClick="del()"
													class="material-icons text-info btn-close-item"
													type="button" style="margin-top: 10px;"> <i
													class="fas fa-trash-alt" style="color: red;"></i>
												</a>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12 col-md-6"></div>
								<div class="col-sm-12 col-md-6 align-self-end py-2">
									<button id="disabled-add" onClick="add()" type='button'
										class="form-control text-info " id='btn-add-element'>
										<i class="fas fa-plus-circle"></i> Thêm thiết bị mượn
									</button>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary"
									data-dismiss="modal">Close</button>
								<button id="btn-them" type="submit" class="btn btn-primary">Save
									changes</button>
							</div>
						</form:form>
					</div>
				</div>

			</div>
		</div>
	</div>

	<button hidden="true" id="update_modal_btn" data-toggle="modal"
		data-target="#exampleModal1"></button>
	<div class="modal fade " id="exampleModal1" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-xl">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLongTitle">Sửa phiếu mượn</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="pd-20 card-box mb-30">
						<form:form action="phieumuon/dsphieumuon/update.htm"
							modelAttribute="phieumuon_sua" method="post">
							<!-- 							=================================== cột 1 ========================================= -->
							<div class="row">
								<div class="col-sm-12 col-md-6">
									<div class="form-group row py-2">
										<label class="col-sm-4 col-md-4 col-form-label">Mã
											phiếu mượn</label>
										<div class="col-sm-4 col-md-8">

											<input class="form-control" value="${phieumuon_sua.mapm}"
												readonly />
											<form:input class="form-control" type="text" path="mapm"
												placeholder="Nhập mã phiếu mượn" style="display: none" />
										</div>
									</div>
									<div class="form-group row py-2">
										<label class="col-sm-4 col-md-4 col-form-label">Người
											mượn</label>
										<input value="${phieumuon_sua.getNm().getManm()}" required="required" style="margin-left: 15px;height: 38px;"  type="text" size="25" ID="searchForSua" onkeyup="searchSelectSua(this.id,'searchInSua')">
										<div class="widthset col-sm-4 col-md-8">
											<!-- 											Đối tượng này dễ sai khi không .manm -->
											<form:errors path="nm" />
											<form:select onChange="document.getElementById('btn-them-sua').disabled = false; bindingSua()" class="selectpicker form-control" path="nm.manm"
												
												multiple="multiple" name="searchInSua" id="searchInSua" 
												>
												<form:option value="" label="Chọn người mượn:">
												</form:option>
												<c:forEach var="nm" items="${listNguoiMuons}">
													<form:option value="${nm.manm }"
														label="${nm.manm } - ${nm.ho } ${nm.ten }"></form:option>
												</c:forEach>
											</form:select>
										</div>
									</div>
									<div class="form-group row py-2">
										<label class="col-sm-4 col-md-4 col-form-label">Nhân
											viên</label>
										<div class="col-sm-4 col-md-8">
											<form:errors path="nhanvien_pm.manv" />
											<c:if
												test="${accountLogined.listNhanVien.get(0).isadmin!=null}">
												<form:select class="selectpicker form-control"
													path="nhanvien_pm.manv" style="width: 100%;height: 38px;"
													required="required">
													<%-- 													<<form:option value="${account_db.nhanviens.get(0).manv}" label="${account_db.nhanviens.get(0).ho} ${account_db.nhanviens.get(0).ten}"></form:option> --%>
													<c:forEach var="nv" items="${listNhanViens}">
														<form:option value="${nv.manv }"
															label="${nv.ho } ${nv.ten }"></form:option>
													</c:forEach>
												</form:select>
											</c:if>
											<c:if
												test="${accountLogined.listNhanVien.get(0).isadmin==null}">
												<form:select class="selectpicker form-control"
													path="nhanvien_pm.manv" style="width: 100%;height: 38px;"
													required="required">
													<form:option value="${phieumuon_sua.nhanvien_pm.manv}"
														label="${phieumuon_sua.nhanvien_pm.ho} ${phieumuon_sua.nhanvien_pm.ten}"></form:option>
												</form:select>
											</c:if>
										</div>
									</div>
									<div class="form-group row py-2">
										<label class="col-sm-4 col-md-4 col-form-label">Phòng</label>
										<div class="col-sm-4 col-md-8">
											<input id="phongSua" value="${phieumuon_sua.phong }" type="text" readonly />
										</div>
									</div>
									<div class="form-group row py-2">
										<label class="col-sm-4 col-md-4 col-form-label">Thời
											gian mượn</label>
										<div class="col-sm-4 col-md-8">
											<form:input class="form-control" path="tgmuon"
												readonly="true" type="date" />
										</div>
									</div>
									<div class="form-group row py-2">
										<label class="col-sm-4 col-md-4 col-form-label">Ghi
											chú</label>
										<div class="col-sm-4 col-md-8">
											<%-- 									<form:errors path="diachi" /> --%>
											<form:input class="form-control" type="text" path="ghichu"
												placeholder="Nhập ghi chú" />
										</div>
									</div>
								</div>
								<!-- 							====================================== Cột 2 =================================================== -->
								<div class="col-sm-12 col-md-6" id='parent-element-update'>
									<div class="hidden-element-update" style="display: none;">
										<div class="form-group row py-1 px-1">
											<label class="col-sm-4 col-md-2 col-form-label">Thiết
												bị </label>
											<div class="col-md-5">
												<select id="filterTBSua" class="form-control" name="replacehereSua"
													style="width: 100%; height: 45px;">
													<c:forEach var="tb" items="${loaiThietBis}">
														<option value="${tb.matb}">${tb.ten}</option>
													</c:forEach>
												</select>
												<select id="filterTBHiddenSua" class="form-control"
													style="width: 100%; height: 45px; display:none;">
													<c:forEach var="tb" items="${loaiThietBis}">
														<option value="${tb.matb}">${tb.phong}</option>
													</c:forEach>
												</select>
											</div>
											<div class="col-md-5">
												<select class="form-control" name="tgtrarp"
													style="width: 100%; height: 45px; display:none;">
													
														<option value="0" selected="selected">Chưa trả</option>
														<option value="1">Đã trả</option>
													
								
												
												</select>
												
											</div>
											<div class="col-md-2">
												<a onClick="delSua()"
													class="material-icons text-info btn-close-item"
													type="button" style="margin-top: 10px;"> <i
													class="fas fa-trash-alt" style="color: red;"></i>
												</a>
											</div>
										</div>
									</div>
									<c:forEach var="sl" items="${phieumuon_sua.ct_phieumuons}"
										varStatus="count">
										<div id="itemSua${count.count}"
											class="form-group row py-1 px-1">
											<label class="col-sm-4 col-md-2 col-form-label">Thiết
												bị </label>
											<div class="col-md-5">

												<select class="form-control" name="thietBiSua"
													style="width: 100%; height: 45px;">
													<option value="${sl.thietbi_muon.matb}">${sl.thietbi_muon.ten}</option>
													<c:forEach var="tb" items="${loaiThietBis}">
														<c:if test="${tb.tinhtrang.equals('0')}">
															<option value="${tb.matb}">${tb.ten}</option>
														</c:if>			
													</c:forEach>
												</select>


												<%-- 														<form:errors path="loai.id" /> --%>
											</div>
											<div class="col-md-5">

												<select onchange="document.getElementById('btn-them-sua').disabled = false;" class="form-control" name="tgtra"
													style="width: 100%; height: 45px;">
													<c:if test="${sl.tgtra == NULL }">
														<option value="0" selected="selected">Chưa trả</option>
														<option value="1">Đã trả</option>
													</c:if>
													<c:if test="${sl.tgtra != NULL }">
														<option value="0">Chưa trả</option>
														<option value="1" selected="selected">Đã trả</option>
													</c:if>
												</select>


												<%-- 														<form:errors path="loai.id" /> --%>
											</div>
											<div class="col-md-2">
												<a onClick="delSua('itemSua${count.count}')"
													class="material-icons text-info btn-close-item"
													type="button" style="margin-top: 10px;"> <i
													class="fas fa-trash-alt" style="color: red;"></i>
												</a>
											</div>
										</div>
									</c:forEach>

								</div>
							</div>
							<div class="row">
								<div class="col-sm-12 col-md-6"></div>
								<div class="col-sm-12 col-md-6 align-self-end py-2">
									<button id="disabled-add-sua" onClick="addSua()" type='button'
										class="form-control text-info " id='btn-add-element'>
										<i class="fas fa-plus-circle"></i> Thêm thiết bị mượn
									</button>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary"
									data-dismiss="modal">Close</button>
								<button id="btn-them-sua" type="submit" class="btn btn-primary">Save
									changes</button>
							</div>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<button hidden="true" id="detail_modal_btn" data-toggle="modal"
		data-target="#bd-detail-modal-lg"></button>
	<div class="modal fade " id="bd-detail-modal-lg" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-xl">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLongTitle">Thông tin phiếu mượn</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="pd-20 card-box mb-30">
						<form:form action="#"
							modelAttribute="phieumuon_chitiet" method="post">
							<!-- 							=================================== cột 1 ========================================= -->
							<div class="row">
								<div class="col-sm-12 col-md-6">
									<div class="form-group row py-2">
										<label class="col-sm-4 col-md-4 col-form-label">Mã
											phiếu mượn</label>
										<div class="col-sm-4 col-md-8">

											<input class="form-control" value="${phieumuon_chitiet.mapm}"
												readonly />
											<form:input class="form-control" type="text" path="mapm"
												placeholder="Nhập mã phiếu mượn" style="display: none" />
										</div>
									</div>
									<div class="form-group row py-2">
										<label class="col-sm-4 col-md-4 col-form-label">Người
											mượn</label>
										<div class="col-sm-4 col-md-8">
											<!-- 											Đối tượng này dễ sai khi không .manm -->
											<form:errors path="nm" />
											<form:select class="selectpicker form-control" path="nm.manm"
												style="width: 100%;height: 38px;" required="required">
												<%-- 												<form:option value="${phieumuon_sua.nm.manm}" label="${phieumuon_sua.nm.ho} ${phieumuon_sua.nm.ten}"></form:option> --%>
												<c:forEach var="nm" items="${listNguoiMuons}">
													<form:option value="${nm.manm }"
														label="${nm.ho } ${nm.ten }"></form:option>
												</c:forEach>
											</form:select>
										</div>
									</div>
									<div class="form-group row py-2">
										<label class="col-sm-4 col-md-4 col-form-label">Nhân
											viên</label>
										<div class="col-sm-4 col-md-8">
											<form:errors path="nhanvien_pm.manv" />
											<c:if
												test="${accountLogined.listNhanVien.get(0).isadmin!=null}">
												<form:select class="selectpicker form-control"
													path="nhanvien_pm.manv" style="width: 100%;height: 38px;"
													required="required">
													<%-- 													<<form:option value="${account_db.nhanviens.get(0).manv}" label="${account_db.nhanviens.get(0).ho} ${account_db.nhanviens.get(0).ten}"></form:option> --%>
													<c:forEach var="nv" items="${listNhanViens}">
														<form:option value="${nv.manv }"
															label="${nv.ho } ${nv.ten }"></form:option>
													</c:forEach>
												</form:select>
											</c:if>
											<c:if
												test="${accountLogined.listNhanVien.get(0).isadmin==null}">
												<form:select class="selectpicker form-control"
													path="nhanvien_pm.manv" style="width: 100%;height: 38px;"
													required="required">
													<form:option value="${phieumuon_chitiet.nhanvien_pm.manv}"
														label="${phieumuon_chitiet.nhanvien_pm.ho} ${phieumuon_chitiet.nhanvien_pm.ten}"></form:option>
												</form:select>
											</c:if>
										</div>
									</div>
									<div class="form-group row py-2">
										<label class="col-sm-4 col-md-4 col-form-label">Phòng</label>
										<div class="col-sm-4 col-md-8">
											<form:errors path="phong" />
											<form:input class="form-control" path="phong"
												placeholder="Nhập phòng học" type="text" required="required" />
										</div>
									</div>
									<div class="form-group row py-2">
										<label class="col-sm-4 col-md-4 col-form-label">Thời
											gian mượn</label>
										<div class="col-sm-4 col-md-8">
											<form:input class="form-control" path="tgmuon"
												readonly="true" type="date" />
										</div>
									</div>
									<div class="form-group row py-2">
										<label class="col-sm-4 col-md-4 col-form-label">Ghi
											chú</label>
										<div class="col-sm-4 col-md-8">
											<%-- 									<form:errors path="diachi" /> --%>
											<form:input class="form-control" type="text" path="ghichu"
												placeholder="Nhập ghi chú" />
										</div>
									</div>
								</div>
								<!-- 							====================================== Cột 2 =================================================== -->
								<div class="col-sm-12 col-md-6" id='parent-element-update'>
									<c:forEach var="sl" items="${phieumuon_chitiet.ct_phieumuons}"
										varStatus="count">
										<div id="itemSua${count.count}"
											class="form-group row py-1 px-1">
											<label class="col-sm-4 col-md-2 col-form-label">Thiết
												bị </label>
											<div class="col-md-5">

												<select class="form-control"
													style="width: 100%; height: 45px;">
													<option value="${sl.thietbi_muon.matb}">${sl.thietbi_muon.ten}</option>
													<c:forEach var="tb" items="${loaiThietBis}">
														<option value="${tb.matb}">${tb.ten}</option>
													</c:forEach>
												</select>


												<%-- 														<form:errors path="loai.id" /> --%>
											</div>
											<div class="col-md-5">

												<select class="form-control"
													style="width: 100%; height: 45px;">
													<c:if test="${sl.tgtra == NULL }">
														<option value="0" selected="selected">Chưa trả</option>
														<option value="1">Đã trả</option>
													</c:if>
													<c:if test="${sl.tgtra != NULL }">
														<option value="0">Chưa trả</option>
														<option value="1" selected="selected">Đã trả</option>
													</c:if>
												</select>


												<%-- 														<form:errors path="loai.id" /> --%>
											</div>
										</div>
									</c:forEach>

								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary"
									data-dismiss="modal">Close</button>
							</div>
						</form:form>
					</div>
				</div>

			</div>
		</div>
	</div>

	<c:if test="${insert}">
		<script type="text/javascript">
        $(document).ready(function () {
            alert("Thêm thành công!")
        });
    </script>
	</c:if>

	<c:if test="${insert || update || delete}">
		<script type="text/javascript">
        $(document).ready(function () {
            alert("Thao tác thành công! :)) ");

        });
    </script>
	</c:if>
	<c:if test="${insert == false || update == false || delete == false}">
		<script type="text/javascript">
        $(document).ready(function () {
            alert("Thao tác thất bại! :(( ");
        });
    </script>
	</c:if>
	<script type="text/javascript">
		function popupFormEdit() {

	        $(document).ready(function () {
	        $('#update_modal_btn').click();
	                console.log("Hiển thị edit form")
	        });
        }	
	
		var index = 0;
		document.getElementById('btn-them').disabled = true;
		function add() {
			const ele = document.querySelector('.hidden-element');
            const parent = document.getElementById('parent-element');
            let htmlEle = ele.innerHTML;
            htmlEle = htmlEle.replace("del()", "del('" + "item" + (index +1)+ "')" );
            htmlEle = htmlEle.replace("replacehere", "thietBi" );
            
            const newEle = document.createElement('div');
            newEle.setAttribute("id", "item"+(index+1));
            newEle.innerHTML = htmlEle;
            // add event delete
            parent.appendChild(newEle);
            index = index + 1;
            document.getElementById('btn-them').disabled = false;
		}
		function del(id) {
			const target = document.getElementById(id);
			target.remove();
			index = index - 1;
			if(index == 0)document.getElementById('btn-them').disabled = true;
		}
		
		
		var indexSua = ${slThietBiSua};
		document.getElementById('btn-them-sua').disabled = true;
		function addSua() {
			const ele = document.querySelector('.hidden-element-update');
            const parent = document.getElementById('parent-element-update');
            let htmlEle = ele.innerHTML;
            htmlEle = htmlEle.replace("delSua()", "delSua('" + "itemSua" + (indexSua +1)+ "')" );
            htmlEle = htmlEle.replace("replacehereSua", "thietBiSua" );
            htmlEle = htmlEle.replace("tgtrarp", "tgtra" );
            
            const newEle = document.createElement('div');
            newEle.setAttribute("id", "itemSua"+(indexSua+1));
            newEle.innerHTML = htmlEle;
            // add event delete
            parent.appendChild(newEle);
            indexSua = indexSua + 1;
            document.getElementById('btn-them-sua').disabled = false;
		}
		function delSua(id) {
			const target = document.getElementById(id);
			target.remove();
			indexSua = indexSua - 1;
			if(indexSua == ${slThietBiSua})document.getElementById('btn-them-sua').disabled = true;
		}
		const ele = document.querySelector('#parent-element');
		const hiddenPick = ele.innerHTML
		$('#disabled-add').prop("disabled", true)
		function filter() {
			document.getElementById("parent-element").innerHTML ="";
			document.getElementById("parent-element").innerHTML = hiddenPick
			
	        var keyword = document.getElementById("phong").value;
	        var selectTB = document.getElementById("filterTB");
	        var selectTBHidden = document.getElementById("filterTBHidden");
			
	        let dangmuon = 0;
	        for (var i = 0; i < selectTBHidden.length; i++) {
	            var txt = selectTBHidden.options[i].text;
	            if (!txt.trim().match(keyword.trim())) {
	            	console.log(123)
	                $(selectTB.options[i]).attr('disabled', 'disabled').hide();
	                $(selectTBHidden.options[i]).attr('disabled', 'disabled').hide();
	            } else {
	                $(selectTB.options[i]).removeAttr('disabled').show();
	                $(selectTBHidden.options[i]).removeAttr('disabled').show();
	                dangmuon++;
	            }

	        }
	        if(dangmuon == 0) {
	        	alert("Kho thiết bị dành cho phòng "+keyword.trim() +" hiện đang trống!")
	        	return;
	        }
	        $('#disabled-add').prop("disabled", false)
	    }
		
		$('#disabled-add-sua').prop("disabled", true)
		function filterSua() {		
	        var keyword = document.getElementById("phongSua").value;
	        console.log(phongSua)
	        var selectTBSua = document.getElementById("filterTBSua");
	        var selectTBHiddenSua = document.getElementById("filterTBHiddenSua");
			
	        let dangmuon = 0;
	        for (var i = 0; i < selectTBHiddenSua.length; i++) {
	            var txt = selectTBHiddenSua.options[i].text;
	            if (!txt.trim().match(keyword.trim())) {
	            	console.log(123)
	                $(selectTBSua.options[i]).attr('disabled', 'disabled').hide();
	                $(selectTBHiddenSua.options[i]).attr('disabled', 'disabled').hide();
	            } else {
	                $(selectTBSua.options[i]).removeAttr('disabled').show();
	                $(selectTBHiddenSua.options[i]).removeAttr('disabled').show();
	                dangmuon++;
	            }

	        }
	        if(dangmuon == 0) {
	        	alert("Kho thiết bị dành cho phòng "+keyword.trim() +" hiện đang trống!")
	        	return;
	        }
	        $('#disabled-add-sua').prop("disabled", false)
	    }
		
		function searchSelect(searchForID, searchInID) {
		    var input = document.getElementById(searchForID).value.toLowerCase();
		    var output = document.getElementById(searchInID).options;
		    let nmTonTai = false;
			let checkTonTai = 0;
		    for (var i = 0; i < output.length; i++) {
		        if (output[i].text.toLowerCase().indexOf(input) < 0) {
		            output[i].style.display = "none";
		            output[i].setAttribute('style', 'display:none');
		            checkTonTai++;
		        } else {
		            output[i].style.display = "";
		            output[i].setAttribute('style', 'display:');
		        }
		    }
		    if(checkTonTai == output.length) nmTonTai = false;
		    else nmTonTai = true;
		    if(!nmTonTai || index==0)document.getElementById('btn-them').disabled = true;
		    else document.getElementById('btn-them').disabled = false;
		}
		
		function searchSelectSua(searchForID, searchInID) {
		    var inputSua = document.getElementById(searchForID).value.toLowerCase();
		    var outputSua = document.getElementById(searchInID).options;
		    let nmTonTai = false;
			let checkTonTai = 0;
		    for (var i = 0; i < outputSua.length; i++) {
		        if (outputSua[i].text.toLowerCase().indexOf(inputSua) < 0) {
		            outputSua[i].style.display = "none";
		            outputSua[i].setAttribute('style', 'display:none');
		            checkTonTai++;
		        } else {
		            outputSua[i].style.display = "";
		            outputSua[i].setAttribute('style', 'display:');
		        }
		    }
		    if(checkTonTai == outputSua.length) nmTonTai = false;
		    else nmTonTai = true;
		    if(!nmTonTai && indexSua == ${slThietBiSua})document.getElementById('btn-them-sua').disabled = true;
		    else document.getElementById('btn-them-sua').disabled = false;
		}
		
		function binding() {
			let txt = document.getElementById('searchIn').value
			console.log(txt)
			document.getElementById('searchFor').value = txt
		}
		function bindingSua() {
			let txt = document.getElementById('searchInSua').value
			console.log(txt)
			document.getElementById('searchForSua').value = txt
		}
</script>
	<script>
$('.delete_btn').on('click',function(){
	let ten = $(this).parent().find("input[name='ten']").val();
	let delete_btn = $(this).parent().find('.submit_del_btn');
	const re =confirm('Bạn có muốn xóa?');
		//Nếu nút đồng ý được nhấn
		if (re) {
			delete_btn.click();
		}
	
}) 
</script>
	<c:if test="${form_edit}">
		<script>
        popupFormEdit();
        filterSua();
    </script>
	</c:if>
	<c:if test="${form_detail}">
		<script type="text/javascript">
			$(document).ready(function() {
				$('#detail_modal_btn').click();
				console.log("Hiển thị edit form")
			});
		</script>
	</c:if>
</body>
</html>
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
<title>Đổi mật khẩu</title>
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
			document.getElementById("btn-check").style.display = "none";
		}
	})
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

		<div class="col-md-6 col-lg-5"
			style="margin-left: 300px; margin-top: 50px;">
			<div class="login-box bg-while box-shadow border-radius-10 ">
				<form:form action="nv/changePassword.htm"
					modelAttribute="accountChange" method="post">
					<div class="text-dark">
						<div class="col-sm-4 col-md-12">
							<div class="row">
								<label class="col-sm-4 col-md-6">Password</label>
								<div class="input-group custom">
									<form:input path="password" type="password"
										class="form-control form-control-lg" placeholder="**********"
										required="required" />
									<div class="input-group-append custom">
										<span class="input-group-text"><i
											class="dw dw-padlock1"></i></span>
									</div>
								</div>
							</div>

						</div>
						<div class="col-sm-4 col-md-12">
							<div class="row">
								<label class="col-sm-4 col-md-6">New Password</label>
								<div class="input-group custom">
									<input name="newPassword" type="password"
										class="form-control form-control-lg" placeholder="**********"
										required="required" />
									<div class="input-group-append custom">
										<span class="input-group-text"><i
											class="dw dw-padlock1"></i></span>
									</div>
								</div>
							</div>

						</div>
						<div class="col-sm-4 col-md-12">
							<div class="row">
								<label class="col-sm-4 col-md-6">Confirm Password</label>
								<div class="input-group custom">
									<input name="confirmPassword" type="password"
										class="form-control form-control-lg" placeholder="**********"
										required="required" />
									<div class="input-group-append custom">
										<span class="input-group-text"><i
											class="dw dw-padlock1"></i></span>
									</div>
								</div>
							</div>

						</div>


					</div>
					<!-- 							=================================== cột 1 ========================================= -->

					<div class="modal-footer">
						<input class="btn btn-info btn-lg btn-block" type="submit"
							value="Thay đổi">
						<div class="col-sm-6 col-md-12">
							<a href="phieumuon">Quay lại</a>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>



	<c:if test="${changeSuccess}">
		<script type="text/javascript">
			$(document)
					.ready(
							function() {
								show_success();
								setTimeout(
										function() {
											window.location = "${pageContext.servletContext.contextPath}/login";
										}, 5000);
							});
		</script>
	</c:if>
	<c:if test="${changeSuccess==false}">
		<script type="text/javascript">
			$(document).ready(function() {
				show_error();
			});
		</script>
	</c:if>

	<script type="text/javascript">
		//THÔNG BÁO LỖI
		function show_success() {
			alert("Thao tác thành công! :)) ")
		}

		//THÔNG BÁO LỖI
		function show_error() {
			alert("Thao tác thất bại! :(( ")
		}
	</script>
</body>
</html>
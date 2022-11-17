<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<div class="modal-body">
		<div class="invoice-header text-center">
			<h4 class="text-blue h4">SỬA PHIẾU NHẬP</h4>
		</div>
		<!-- Nhập thông tin cơ bản phiếu nhập -->
		<div class=" row">
			<div class="col form-group">
				<div class="row">
					<label class="col-sm-4 col-md-4 col-form-label">Mã phiếu
						nhập:</label>
					<div class="col-sm-4 col-md-8">
						<form:input class="form-control" type="text" path="mapn"
							placeholder="Nhập mã phiếu nhập" readonly="true" />
					</div>
				</div>
			</div>
			<div class=" col form-group">
				<div class="row">
					<label class="col-sm-4 col-md-4 col-form-label">Nhà cung
						cấp: </label>
					<div class="col-sm-4 col-md-8">
						<form:select class="selectpicker form-control" path="ncc.mancc"
							style="width: 100%;height: 38px;" required="required">
							<c:forEach var="ncc" items="${listNcc}">
								<form:option value="${ncc.mancc }" label="${ncc.ten}"></form:option>
							</c:forEach>
						</form:select>
					</div>
				</div>
			</div>
			<div class=" col form-group">
				<div class="row">
					<label class="col-sm-4 col-md-4 col-form-label">Nhân Viên
						xử lý:</label>
					<div class="col-sm-4 col-md-8">
						<input class="col-sm-4 col-md-4 form-control" type="text"
							name="nhanvien.manv" value="${nv.manv}" readonly="readonly" />
					</div>
				</div>
			</div>
		</div>
		<hr>
		<h5 class="h5 invoice-header text-center">DANH SÁCH THIẾT BỊ NHẬP</h5>
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
				<c:forEach items="${phieunhap_sua.ct_phieunhaps}" var="ct_pn">
					<tr>
						<td><select class="form-control" name="matb" required="required"
							style="width: auto;">
								<c:set var="matb" value="${ct_pn.thietbi.matb}" />
								<option value="${ct_pn.thietbi.matb}"
									label="${ct_pn.thietbi.ten}" />
								<c:forEach var="tb" items="${listThietbi}">
									<c:if test="${matb.equals(tb.matb) == false }">
										<option value="${tb.matb}">${tb.ten}</option>
									</c:if>
								</c:forEach>
						</select></td>
						<td style="display: none;"><input type="number" min="1"
							value="1" name="soluongnhap" class=" form-control"
							onchange="tinhtong($(this).parents('tbody'))" required /></td>
						<td><input type="number" min="0" step="0.01" name="dongia"
							value="${ct_pn.dongia}" class=" form-control"
							onchange="tinhtong($(this).parents('tbody'))" required /></td>
						<td><a
							class="material-icons text-info btn-close-item  text-right"
							type="button"> <i class="fas fa-trash-alt" style="color: red;"></i> </a></td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<th></th>
					<th></th>
					<th class="tongcong"></th>
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
		<button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
		<input type="hidden" name="trangthai" />
		<button type="submit" class="btn btn-primary luu-btn">Lưu tạm</button>
		<input type="submit" hidden="hidden" />
		<button type="button" class="btn btn-success them-btn">Xác
			Nhận</button>
	</div>
</body>
</html>
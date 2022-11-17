<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <div class="modal-body">
            <div class="invoice-header text-center">
                <h4 class="text-blue h4">CHI TIẾT PHIẾU NHẬP</h4>
            </div>
            <!-- Nhập thông tin cơ bản phiếu nhập -->
            <div class=" row">
                <div class="col form-group">
                    <div class="row">
                        <label class="col-sm-4 col-md-4 col-form-label">Mã phiếu
                            thanh lý</label>
                        <div class="col-sm-4 col-md-8">
                            <form:input class="form-control" type="text" path="mapn"
                                        placeholder="Nhập mã phiếu nhập" readonly="true" />
                        </div>
                    </div>
                    <div class="row">
                        <label class="col-sm-4 col-md-4 col-form-label">Ngày
                            nhập: </label>
                        <div class="col-sm-4 col-md-8">
                            <form:input path="tgnhap" class="form-control"
                                        readonly="true" type="date" />
                        </div>
                    </div>
                </div>
                <div class=" col form-group">
                    <div class="row">
                        <label class="col-sm-6 col-md-4 col-form-label">Nhân Viên xử lý:</label>
                        <div class="col-sm-4 col-md-8">
                            <input class="col-sm-4 col-md-8 form-control" type="text"
                                   name="nhanvien.manv"
                                   value="${phieunhap_sua.nhanvien.manv}"
                                   readonly="readonly" />
                        </div>
                    </div>
                </div>
            </div>
            <hr>
            <h5 class="h5 invoice-header text-center">DANH SÁCH THIẾT BỊ
                NHẬP</h5>
            <!-- Nhập thông tin cơ bản phiếu nhập -->
            <table class="table dsThietbi_table">
                <thead>
                    <tr>
                        <th>Thiết bị</th>
                        <th>Đơn giá</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${phieunhap_sua.ct_phieunhaps}" var="ct_pn">
                    <tr>
                        <td>
                            <input type="text" class=" form-control" name="matb"
                                   value="${ct_pn.thietbi.ten}" readonly="readonly" />
                        </td>
                        <td style="display: none;"><input type="number" min="1"
							value="1" name="soluongnhap" class=" form-control"
							onchange="tinhtong($(this).parents('tbody'))" required /></td>
                        <td>
                            <input type="number" min="0" step="0.01" name="dongia"
                                   value="${ct_pn.dongia}" class=" form-control" readonly="true"/>
                        </td>
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
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
        </div>
    </body>
</html>
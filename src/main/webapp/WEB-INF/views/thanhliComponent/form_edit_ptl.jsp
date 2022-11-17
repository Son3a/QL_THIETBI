<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <div class="modal-body">
            <div class="invoice-header text-center">
                <h4 class="text-blue h4">CHI TIẾT PHIẾU THANH LÝ</h4>
            </div>
            <!-- Nhập thông tin cơ bản phiếu nhập -->
            <div class=" row">
                <div class="col form-group">
                    <div class="row">
                        <label class="col-sm-4 col-md-4 col-form-label">Mã phiếu
                            thanh lý</label>
                        <div class="col-sm-4 col-md-8">
                            <form:input class="form-control" type="text" path="maptl"
                                        placeholder="Nhập mã phiếu mượn" readonly="true" />
                        </div>
                    </div>
                    <div class="row">
                        <label class="col-sm-4 col-md-4 col-form-label">Ngày thanh
                            lý: </label>
                        <div class="col-sm-4 col-md-8">
                            <input name="thoigian" class="form-control" readonly="readonly"
                                   type="date" value="${today}" />
                        </div>
                    </div>
                </div>
                <div class=" col form-group">
                    <div class="row">
                        <label class="col-sm-6 col-md-4 col-form-label">Nhân Viên
                            chỉnh sửa:</label>
                        <div class="col-sm-4 col-md-8">
                            <input class="col-sm-4 col-md-8 form-control" type="text"
                                   name="nhanvien_thanhly.manv" value="${nv.manv}"
                                   readonly="readonly" />
                        </div>
                    </div>
                    <div class="row">
                        <label class="col-sm-4 col-md-4 col-form-label">Ghi chú:</label>

                        <div class="col-sm-4 col-md-8">
                            <form:textarea class="col-sm-4 col-md-8" type="text"
                                           style="border-color: lightgrey;border-radius: 3px;" path="ghichu"
                                           rows="1" placeholder="Nhập ghi chú" />
                        </div>
                    </div>
                </div>
            </div>
            <hr>
            <h5 class="h5 invoice-header text-center">DANH SÁCH THIẾT BỊ
                THANH LÝ</h5>
            <!-- Nhập thông tin cơ bản phiếu nhập -->
            <table class="table dsThietbi_table">
                <thead>
                    <tr>
                        <th>Thiết bị</th>
                        <th>Số lượng</th>
                        <th>Đơn giá</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${phieuthanhly_sua.ct_phieuthanhlys}" var="ct_ptl">
                    <tr>
                        <td>
                            <select class="form-control" name="matb" style="width: auto;">
                                <c:set var="matb" value="${ct_ptl.thietbi_thanhly.matb}" />
                                <option value="${ct_ptl.thietbi_thanhly.matb}"
                                        label="${ct_ptl.thietbi_thanhly.ten}" />
                                <c:forEach var="tb" items="${listThietbi}">
                                    <c:if test="${matb.equals(tb.matb) == false }">
                                        <option value="${tb.matb}">${tb.ten}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </td>
                        <td style="display:none;">
                            <input type="number" min="1" name="soluong"
                                   value="1" class=" form-control"
                                   onchange="tinhtong($(this).parents('tbody'))" required/>
                        </td>
                        <td>
                            <input type="number" min="0" step="0.01" name="dongia"
                                   value="${ct_ptl.dongia}" class=" form-control"
                                   onchange="tinhtong($(this).parents('tbody'))" required/>
                        </td>
                        <td>
                            <a class="material-icons text-info btn-close-item  text-right"
                               type="button"> <i class="fas fa-trash-alt"></i> </a>
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
            <div class="row form-group them-tb-nhap">
                <button type='button' class="form-control text-info ">
                    <i class="fas fa-plus-circle"></i>
                    Thêm thiết bị nhập
                </button>
            </div>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
            <input type="hidden" name="trangthai" />
            <button type="submit" class="btn btn-primary luu-btn">Lưu tạm</button>
            <input type="submit" hidden="hidden" />
            <button type="button" class="btn btn-success them-btn">Xác nhận</button>
        </div>
    </body>
</html>
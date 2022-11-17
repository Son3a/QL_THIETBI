package qltb.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import qltb.dao.AccountDAO;
import qltb.dao.NguoiMuonDAO;
import qltb.dao.NhanVienDAO;
import qltb.dao.QuanLiDAO;
import qltb.entity.*;

@Transactional
@Controller
@RequestMapping("/ql/")
public class QuanLiController {

	@Autowired
	SessionFactory factory;

	@ModelAttribute("quanli_moi")
	public QUANLI quanli_moi() {
		return new QUANLI();
	}

	@ModelAttribute("quanli_sua")
	public QUANLI quanli_sua() {
		return new QUANLI();
	}

	@ModelAttribute("nhanViens")
	public List<NHANVIEN> getNhanviens() {
		return new NhanVienDAO().loadDanhSachNhanVien(factory);
	}

	@RequestMapping(value = "qlquanli", method = RequestMethod.GET)
	public String home(ModelMap model) {
		model.addAttribute("listQuanLi", new QuanLiDAO().loadDanhSachQuanLi(factory));
		return "nhansu/dsql";
	}

	@RequestMapping(value = "qlquanli", method = RequestMethod.POST)
	public String insert(ModelMap model, @RequestParam("manv") String manv,
			@RequestParam("ngaytiepnhan") String ntn
			) {
		NHANVIEN nv = new NhanVienDAO().getById(factory, manv.trim());
		System.out.print(nv.getAcc().getUsername());
		QUANLI quanli_moi = new QUANLI();
		quanli_moi.setMaql(manv);
		quanli_moi.setHo(nv.getHo());
		quanli_moi.setTen(nv.getTen());
		quanli_moi.setNgaysinh(nv.getNgaysinh());
		quanli_moi.setGioitinh(nv.getGioitinh());
		quanli_moi.setNgaytiepnhan(Date.valueOf(ntn));
		
		model.addAttribute("quanli_moi", quanli_moi);
		model.addAttribute("insert", new QuanLiDAO().save(factory, quanli_moi));
		
		new AccountDAO().changeStaff(factory, nv.getAcc().getUsername().trim());
		return "redirect: http://localhost:8080/QL_THIETBI/ql/qlquanli.htm";
	}

	@RequestMapping(value = "qlquanli/delete", method = RequestMethod.POST)
	public String del(ModelMap model, @RequestParam("maql") String maql) {
		System.out.println("maql = " + maql);
		if(new QuanLiDAO().checkCoNhanVien(factory, maql)) {
    		model.addAttribute("error_constraint", true);
    		return home(model);
    	}
		QUANLI quanli_xoa = new QUANLI();
		quanli_xoa.setMaql(maql);
		NHANVIEN nv = new NhanVienDAO().getById(factory, maql.trim());
		new AccountDAO().changeAdmin(factory, nv.getAcc().getUsername().trim());
		model.addAttribute("delete", new QuanLiDAO().delete(factory, quanli_xoa));
		return home(model);
	}
}

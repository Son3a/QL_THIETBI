/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qltb.controller;

import qltb.dao.CT_PhieuNhapDAO;
import qltb.dao.CT_PhieuThanhLiDAO;
import qltb.dao.PhieuNhapDAO;
import qltb.dao.PhieuThanhLiDAO;
import qltb.dao.ThietBiDAO;
import qltb.entity.*;
import java.sql.Date;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author HOME
 */
@Controller
@RequestMapping("/ptl/")
public class ThanhLiController {

	@Autowired
	SessionFactory factory;

	@ModelAttribute("listThietbi")
	public List<THIETBI> getListTB() {
		List<THIETBI> list = new ThietBiDAO().getAll(factory);
		List<THIETBI> listDangMuon = new ThietBiDAO().getAllDangMuon(factory);
		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < listDangMuon.size(); j++)
				if (list.get(i).getMatb().trim().equals(listDangMuon.get(j).getMatb().trim())) {
					list.remove(i);
				}
		}
		return list;
	}

	@ModelAttribute("phieuthanhly_them")
	public PHIEUTHANHLY getPhieuThanhLyThem() {
		return new PHIEUTHANHLY();
	}

	@ModelAttribute("phieuthanhly_sua")
	public PHIEUTHANHLY getPhieuThanhLySua() {
		return new PHIEUTHANHLY();
	}

	@ModelAttribute("today")
	public LocalDate getToday() {
		return LocalDate.now();
	}

	@ModelAttribute("nv")
	public NHANVIEN getNv(HttpSession session, ModelMap model) {
		if (session.getAttribute("account_cur") == null)
			return null;
		ACCOUNT account = (ACCOUNT) session.getAttribute("account_cur");
		// return new NhanVienDAO().getByUserName(account.getUsername(), factory);
		if (account.getListNhanVien().isEmpty()) {
			return null;
		}
		if (account.getUsername().equals("admin")) {
			model.addAttribute("admin", true);
		}
		model.addAttribute("current_user", account.getListNhanVien().get(0).getManv());
		return account.getListNhanVien().get(0);
	}

	@ModelAttribute("newID")
	public String getRandomMa() {
		List<PHIEUTHANHLY> list = new PhieuThanhLiDAO().getAll(factory);
		int ma = 1001;
		String id = "ptl" + ma;
		if (list.isEmpty()) {
			return id;
		}
		int[] arr = new int[list.size()];
		for (int i = 0; i < list.size(); i++) {
			arr[i] = Integer.parseInt(list.get(i).getMaptl().trim().substring(3));
		}
		Arrays.sort(arr);
		System.out.println(Arrays.toString(arr));
		return "ptl" + (arr[list.size() - 1] + 1);
	}

	@RequestMapping("dsptl")
	public String home(ModelMap model, HttpServletRequest rq) {
		HttpSession session = rq.getSession();
		if (session.getAttribute("account_cur") == null) {
			System.out.print("login");
			return "redirect: http://localhost:8080/QL_THIETBI/login.htm";
		}
		model.addAttribute("listPhieuThanhLy", new PhieuThanhLiDAO().getAll(factory));
		System.out.println("ptl" + new PhieuThanhLiDAO().getAll(factory).size());
		return "ptl/dsptl";
	}

	public List<CT_PHIEUTHANHLY> removeDuplicate(List<String> matbs, List<Integer> soluongnhaps, List<Double> dongias,
			PHIEUTHANHLY phieuthanhly) {
		if (matbs.size() < 1 || soluongnhaps.size() < 1 || dongias.size() < 1) {
			return null;
		}

		boolean kq = true;

		// Gop phan tu bi trung ten
		// Duyet tat ca phan tu
		for (int i = 0; i < matbs.size() - 1; i++) {
			for (int j = i + 1; j < matbs.size(); j++) {
				// neu trung ten
				if (matbs.get(i).equals(matbs.get(j))) {
					// remove thiet bi trung
					matbs.remove(j);

					// cá»™ng dá»“n sá»‘ lÆ°á»£ng vÃ  Ä‘Æ¡n giÃ¡
					int soLuong = soluongnhaps.get(i) + soluongnhaps.get(j);
					soluongnhaps.set(i, soLuong);
					Double donGia = dongias.get(i) + dongias.get(j);
					dongias.set(i, donGia);

					// remove sl vÃ  Ä‘Æ¡n giÃ¡ cua thiet bi trung
					soluongnhaps.remove(j);
					dongias.remove(j);
				}
			}
		}

		List<CT_PHIEUTHANHLY> listCt_ptl = new ArrayList<CT_PHIEUTHANHLY>();
		for (int i = 0; i < matbs.size(); i++) {
			CT_PHIEUTHANHLY ct_ptl = new CT_PHIEUTHANHLY();
			ct_ptl.setPhieuthanhly(phieuthanhly);
			ct_ptl.setThietbi_thanhly(new ThietBiDAO().getById2thanhly(matbs.get(i), factory));
			ct_ptl.setDongia(dongias.get(i));
			listCt_ptl.add(ct_ptl);
		}

		if (!kq) {
			return null;
		}

		return listCt_ptl;
	}

	@RequestMapping(value = "dsptl/insert", method = RequestMethod.POST)
	public RedirectView insert(@ModelAttribute("phieuthanhly_them") PHIEUTHANHLY phieuthanhly_them, HttpSession session,
			@RequestParam("matb") List<String> matbs, @RequestParam("trangthai") String tt,
			@RequestParam("soluong") List<Integer> soluongnhaps, @RequestParam("dongia") List<Double> dongias,
			final RedirectAttributes model) {

		Boolean kq = false;

		List<CT_PHIEUTHANHLY> listCt_ptl = removeDuplicate(matbs, soluongnhaps, dongias, phieuthanhly_them);
		for (int i = 0; i < listCt_ptl.size(); i++) {
			THIETBI temp = new ThietBiDAO().getById(listCt_ptl.get(i).getThietbi_thanhly().getMatb().trim(), factory);
			THIETBI temp1 = new THIETBI();
			temp1.setMatb(temp.getMatb());
			temp1.setTen(temp.getTen());
			temp1.setTinhtrang("3");
			temp1.setLoaitb(temp.getLoaitb());
			temp1.setHinh(temp.getHinh());
			temp1.setPhong(temp.getPhong());
			System.out.print("temp" + temp.getMatb());
			new ThietBiDAO().update(factory, temp1);
		}
		if (listCt_ptl != null) {
			phieuthanhly_them.setCt_phieuthanhlys(listCt_ptl);
			if (tt.equals("cho") == false) {
				phieuthanhly_them.setTgthanhly(Date.valueOf(LocalDate.now()));
				kq = new PhieuThanhLiDAO().saveOrUpdate(factory, phieuthanhly_them);
				kq = new PhieuThanhLiDAO().update1(factory, phieuthanhly_them);
				return new RedirectView("http://localhost:8080/QL_THIETBI/ptl/dsptl.htm");
			}
			kq = new PhieuThanhLiDAO().saveOrUpdate(factory, phieuthanhly_them);
		}

		return new RedirectView("http://localhost:8080/QL_THIETBI/ptl/dsptl.htm");
	}

	@RequestMapping("dsptl/edit/{id}")
	public RedirectView edit(@PathVariable String id, RedirectAttributes model, HttpServletRequest rq) {
		HttpSession session = rq.getSession();
		if (session.getAttribute("account_cur") == null) {
			System.out.print("login");
			return new RedirectView("http://localhost:8080/QL_THIETBI/login.htm");
		}
		PHIEUTHANHLY phieuthanhly_sua = new PhieuThanhLiDAO().getById(id, factory);
		model.addFlashAttribute("phieuthanhly_sua", phieuthanhly_sua);
		if (phieuthanhly_sua.getTgthanhly() != null) {
			model.addFlashAttribute("form_info", true);
		} else {
			model.addFlashAttribute("form_edit", true);
		}
		return new RedirectView("http://localhost:8080/QL_THIETBI/ptl/dsptl.htm");
	}

	@RequestMapping(value = "dsptl/update", method = RequestMethod.POST)
	public RedirectView update(@ModelAttribute("phieuthanhly_sua") PHIEUTHANHLY phieuthanhly_sua,
			@RequestParam("trangthai") String tt, @RequestParam("matb") List<String> matbs,
			@RequestParam("soluong") List<Integer> soluongnhaps, @RequestParam("dongia") List<Double> dongias,
			final RedirectAttributes model, HttpSession session) {

		Boolean kq = false;
		List<CT_PHIEUTHANHLY> listCt_ptl = removeDuplicate(matbs, soluongnhaps, dongias, phieuthanhly_sua);
		for (int i = 0; i < listCt_ptl.size(); i++) {
			THIETBI temp = new ThietBiDAO().getById(listCt_ptl.get(i).getThietbi_thanhly().getMatb().trim(), factory);
			THIETBI temp1 = new THIETBI();
			temp1.setMatb(temp.getMatb());
			temp1.setTen(temp.getTen());
			temp1.setTinhtrang("3");
			temp1.setLoaitb(temp.getLoaitb());
			temp1.setHinh(temp.getHinh());
			temp1.setPhong(temp.getPhong());
			System.out.print("temp" + temp.getMatb());
			new ThietBiDAO().update(factory, temp1);
		}
		if (listCt_ptl != null) {
			phieuthanhly_sua.setCt_phieuthanhlys(listCt_ptl);
			if (tt.equals("cho") == false) {
				phieuthanhly_sua.setTgthanhly(Date.valueOf(LocalDate.now()));
				kq = new PhieuThanhLiDAO().saveOrUpdate(factory, phieuthanhly_sua);
				kq = new PhieuThanhLiDAO().update1(factory, phieuthanhly_sua);
				return new RedirectView("http://localhost:8080/QL_THIETBI/ptl/dsptl.htm");
			}
			kq = new PhieuThanhLiDAO().saveOrUpdate(factory, phieuthanhly_sua);
		}

		return new RedirectView("http://localhost:8080/QL_THIETBI/ptl/dsptl.htm");
	}

	@RequestMapping("dsptl/delete")
	public String delete(ModelMap model, @RequestParam("maptl") String maptl, HttpSession session,
			HttpServletRequest rq) {
		System.out.println("hellopm");
		PHIEUTHANHLY phieumuon_xoa = new PhieuThanhLiDAO().getById(maptl, factory);
		for (CT_PHIEUTHANHLY elem : phieumuon_xoa.getCt_phieuthanhlys()) {
			new CT_PhieuThanhLiDAO().delete(factory, elem);
		}
		PHIEUTHANHLY phieumuon_canxoa = new PHIEUTHANHLY();
		phieumuon_canxoa.setMaptl(maptl.trim());
		model.addAttribute("delete", new PhieuThanhLiDAO().delete(factory, phieumuon_canxoa));

		return home(model, rq);
	}
}

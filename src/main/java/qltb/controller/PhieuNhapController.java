/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qltb.controller;

import qltb.dao.CT_PhieuMuonDAO;
import qltb.dao.CT_PhieuNhapDAO;
import qltb.dao.NhaCungCapDAO;
import qltb.dao.PhieuMuonDAO;
import qltb.dao.PhieuNhapDAO;
import qltb.dao.ThietBiDAO;
import qltb.entity.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
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
@RequestMapping("/phieunhap/")
public class PhieuNhapController {

    @Autowired
    SessionFactory factory;

    public String msg = "Thao tÃ¡c tháº¥t báº¡i";

    @ModelAttribute("listThietbi")
    public List<THIETBI> getListTB() {
        return new ThietBiDAO().getAllDoiNhap(factory);
    }
    
    @ModelAttribute("listNcc")
    public List<NHACUNGCAP> getListNCC() {
        return new NhaCungCapDAO().loadNcc(factory);
    }

    @ModelAttribute("phieunhap_them")
    public PHIEUNHAP getPhieuNhapThem() {
        return new PHIEUNHAP();
    }

    @ModelAttribute("phieunhap_sua")
    public PHIEUNHAP getPhieuNhapSua() {
        return new PHIEUNHAP();
    }

    @ModelAttribute("today")
    public LocalDate getToday() {
        return LocalDate.now();
    }

    @ModelAttribute("nv")
    public NHANVIEN getNv(HttpSession session) {
    	if(session.getAttribute("account_cur") == null) return null;
        ACCOUNT account = (ACCOUNT) session.getAttribute("account_cur");
        if (account.getListNhanVien().isEmpty()) {
            return null;
        }
        return account.getListNhanVien().get(0);
    }

    @ModelAttribute("newID")
    public String getRandomMa() {

        List<PHIEUNHAP> list = new PhieuNhapDAO().getAll(factory);
        int id = 1001;
        if (list.isEmpty()) {
            return "pn" + id;
        }
        String newMAPN = list.get(list.size() - 1).getMapn().substring(2);
        id = Integer.parseInt(newMAPN.trim()) + 1;
        return "pn" + id;
    }

    public List<CT_PHIEUNHAP> removeDuplicate(List<String> matbs, List<Integer> soluongnhaps, List<Double> dongias,
            PHIEUNHAP phieunhap) {
        if (matbs.size() < 1 || soluongnhaps.size() < 1 || dongias.size() < 1) {
            return null;
        }
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

        List<CT_PHIEUNHAP> listCt_pn = new ArrayList<CT_PHIEUNHAP>();
        for (int i = 0; i < matbs.size(); i++) {
            CT_PHIEUNHAP ct_pn = new CT_PHIEUNHAP();
            ct_pn.setPhieunhap(phieunhap);
            ct_pn.setThietbi(new ThietBiDAO().getById2nhap(matbs.get(i), factory));
            ct_pn.setDongia(dongias.get(i));
            listCt_pn.add(ct_pn);
        }

        return listCt_pn;
    }

    @RequestMapping(value="dspn")
    public String phieunhap(ModelMap model, HttpServletRequest rq) {
    	HttpSession session = rq.getSession();
    	if(session.getAttribute("account_cur") == null) {
    		System.out.print("login");
    		return "redirect: http://localhost:8080/QL_THIETBI/login.htm";
    	}
        model.addAttribute("listPhieuNhap", new PhieuNhapDAO().getAll(factory));
        return "phieunhap/dspn";
    }

    @RequestMapping(value = "dspn/insert", method = RequestMethod.POST)
    public RedirectView insert(@ModelAttribute("phieunhap_them") PHIEUNHAP phieunhap_them, @RequestParam("trangthai") String tt,
            @RequestParam("matb") List<String> matbs, @RequestParam("soluongnhap") List<Integer> soluongnhaps,
            @RequestParam("dongia") List<Double> dongias, final RedirectAttributes model, HttpSession session) {

    	 Boolean kq = false;
         List<CT_PHIEUNHAP> listCt_pn = removeDuplicate(matbs, soluongnhaps, dongias, phieunhap_them);
         System.out.println(listCt_pn.size());
         for(int i=0; i<listCt_pn.size();i++) {
        	 THIETBI temp = new ThietBiDAO().getById(listCt_pn.get(i).thietbi.getMatb().trim(), factory);
        	 THIETBI temp1 = new THIETBI();
        	 temp1.setMatb(temp.getMatb());
        	 temp1.setTen(temp.getTen());
        	 temp1.setTinhtrang("0");
        	 temp1.setLoaitb(temp.getLoaitb());
        	 temp1.setHinh(temp.getHinh());
        	 temp1.setPhong(temp.getPhong());
        	 System.out.print("temp"+temp.getMatb());
        	 new ThietBiDAO().update(factory, temp1);
         }
         // Có đủ dữ liệu thì mới thêm
         if (listCt_pn != null) {
             phieunhap_them.setCt_phieunhaps(listCt_pn);
             phieunhap_them.setNhanvien(getNv(session));
             if (tt.equals("cho") == false) {
            	 kq = new PhieuNhapDAO().saveOrUpdate(factory, phieunhap_them);
                 phieunhap_them.setTgnhap(Date.valueOf(LocalDate.now()));
                 kq = new PhieuNhapDAO().update1(factory, phieunhap_them);
                 return new RedirectView("http://localhost:8080/QL_THIETBI/phieunhap/dspn.htm");
             }
             kq = new PhieuNhapDAO().saveOrUpdate(factory, phieunhap_them);
         }
        return new RedirectView("http://localhost:8080/QL_THIETBI/phieunhap/dspn.htm");
    }

    @RequestMapping("dspn/edit/{id}")
    public RedirectView edit(@PathVariable String id, RedirectAttributes model, HttpServletRequest rq) {
    	HttpSession session = rq.getSession();
    	if(session.getAttribute("account_cur") == null) {
    		System.out.print("login");
    		return new RedirectView("http://localhost:8080/QL_THIETBI/login.htm");
    	}
    	PHIEUNHAP phieunhap_sua = new PhieuNhapDAO().getById(id, factory);
        model.addFlashAttribute("phieunhap_sua", phieunhap_sua);
        if (phieunhap_sua.getTgnhap() != null) {
            model.addFlashAttribute("form_info", true);
        } else {
            model.addFlashAttribute("form_edit", true);
        }
        return new RedirectView("http://localhost:8080/QL_THIETBI/phieunhap/dspn.htm");
    }

    @RequestMapping(value = "dspn/update", method = RequestMethod.POST)
    public RedirectView update(@ModelAttribute("phieunhap_them") @Valid PHIEUNHAP phieunhap_sua, @RequestParam("trangthai") String tt,
            @RequestParam("matb") List<String> matbs, @RequestParam("soluongnhap") List<Integer> soluongnhaps,
            @RequestParam("dongia") List<Double> dongias, final RedirectAttributes model, HttpSession session) {
    	Boolean kq = false;
   	 	System.out.println(phieunhap_sua);
   	 	System.out.println("matb"+matbs.size());
        List<CT_PHIEUNHAP> listCt_pn = removeDuplicate(matbs, soluongnhaps, dongias, phieunhap_sua);
        for(int i=0; i<listCt_pn.size();i++) {
       	 THIETBI temp = new ThietBiDAO().getById(listCt_pn.get(i).thietbi.getMatb().trim(), factory);
       	 THIETBI temp1 = new THIETBI();
       	 temp1.setMatb(temp.getMatb());
       	 temp1.setTen(temp.getTen());
       	 temp1.setTinhtrang("0");
       	 temp1.setLoaitb(temp.getLoaitb());
       	 temp1.setHinh(temp.getHinh());
       	 temp1.setPhong(temp.getPhong());
       	 System.out.print("temp"+temp.getMatb());
       	 new ThietBiDAO().update(factory, temp1);
        }
        System.out.println(listCt_pn.size());
        // Có đủ dữ liệu thì mới thêm
        if (listCt_pn != null) {
            phieunhap_sua.setCt_phieunhaps(listCt_pn);
            phieunhap_sua.setNhanvien(getNv(session));
            if (tt.equals("cho") == false) {
                phieunhap_sua.setTgnhap(Date.valueOf(LocalDate.now()));
                kq = new PhieuNhapDAO().saveOrUpdate(factory, phieunhap_sua);
                kq = new PhieuNhapDAO().update1(factory, phieunhap_sua);
                return new RedirectView("http://localhost:8080/QL_THIETBI/phieunhap/dspn.htm");
            }
            kq = new PhieuNhapDAO().saveOrUpdate(factory, phieunhap_sua);
        }
       return new RedirectView("http://localhost:8080/QL_THIETBI/phieunhap/dspn.htm");
    	
    }

    @RequestMapping(value = "dspn/delete", method = RequestMethod.POST)
    public String delete(ModelMap model, @RequestParam("mapn") String mapn, HttpSession session,
            HttpServletRequest rq) {
    	System.out.println("hellopm");
        PHIEUNHAP phieumuon_xoa = new PhieuNhapDAO().getById(mapn, factory);
            for (CT_PHIEUNHAP elem : phieumuon_xoa.getCt_phieunhaps()) {
                new CT_PhieuNhapDAO().delete(factory, elem);
            }
        PHIEUNHAP phieumuon_canxoa = new PHIEUNHAP();
        phieumuon_canxoa.setMapn(mapn.trim());
        model.addAttribute("delete", new PhieuNhapDAO().delete(factory, phieumuon_canxoa));
        
        return phieunhap(model, rq);
    }
}

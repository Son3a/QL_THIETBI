/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qltb.controller;

import qltb.dao.NguoiMuonDAO;
import qltb.entity.*;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author HOME
 */
@Controller
@RequestMapping("/nguoimuon/")
public class NguoiMuonController {

    @Autowired
    SessionFactory factory;


    public String getRandomMa() {
        List<NGUOIMUON> list = new NguoiMuonDAO().getAll(factory);
        int ma = 1001;
        if (list.isEmpty()) {
            return "nm" + ma;
        }
        String newMAPM = list.get(list.size() - 1).getManm().trim().substring(2);
        ma = Integer.parseInt(newMAPM) + 1;
        return "nm" + ma;
    }

    @ModelAttribute("nguoimuon_moi")
    public NGUOIMUON nguoimuon_moi() {
        return new NGUOIMUON();
    }

    @ModelAttribute("nguoimuon_sua")
    public NGUOIMUON nguoimuon_sua() {
        return new NGUOIMUON();
    }


    @ModelAttribute("gioiTinhs")
    public List<String> getGioiTinhs() {
        List<String> list = new ArrayList<>();
        list.add("Nam");
        list.add("Nữ");
        return list;
    }

    @RequestMapping(value = "dsnm", method = RequestMethod.GET)
    public String home(ModelMap model, HttpServletRequest rq) {
    	HttpSession session = rq.getSession();
    	if(session.getAttribute("account_cur") == null) {
    		System.out.print("login");
    		return "redirect: http://localhost:8080/QL_THIETBI/login.htm";
    	}
        model.addAttribute("listNguoiMuon", new NguoiMuonDAO().getAll(factory));
        model.addAttribute("maNguoiMuon", getRandomMa());
        return "nguoimuon/dsnm";
    }

    @RequestMapping(value = "dsnm/insert", method = RequestMethod.POST)
    public String insert(ModelMap model, HttpServletRequest rq, @ModelAttribute("nguoimuon_moi") NGUOIMUON nguoimuon_moi) {
        model.addAttribute("nguoimuon_moi", nguoimuon_moi);

        nguoimuon_moi.setTen(new NguoiMuonDAO().chuanHoaTen(nguoimuon_moi.getTen()));
        nguoimuon_moi.setHo(new NguoiMuonDAO().chuanHoaTen(nguoimuon_moi.getHo()));

        model.addAttribute("insert", new NguoiMuonDAO().save(factory, nguoimuon_moi));        // cÃ´ng

        return home(model,rq);
    }

    @RequestMapping(value = "dsnm/edit/{id}")
    public RedirectView show_form_edit(RedirectAttributes model, HttpServletRequest rq, @ModelAttribute("nguoimuon_sua") NGUOIMUON nguoimuon_sua,
            @PathVariable String id) {
    	HttpSession session = rq.getSession();
    	if(session.getAttribute("account_cur") == null) {
    		return new RedirectView("http://localhost:8080/QL_THIETBI/login.htm");
    	}
        System.out.println("id = " + id);
        model.addFlashAttribute("form_edit", true);
        model.addFlashAttribute("nguoimuon_sua", new NguoiMuonDAO().getById(id, factory));
        return new RedirectView("http://localhost:8080/QL_THIETBI/nguoimuon/dsnm.htm");
    }

    // UPDATE
    @RequestMapping(value = "dsnm/update", method = RequestMethod.POST)
    public RedirectView update(RedirectAttributes model, @ModelAttribute("nguoimuon_sua") NGUOIMUON nguoimuon_sua) {

        nguoimuon_sua.setTen(new NguoiMuonDAO().chuanHoaTen(nguoimuon_sua.getTen()));
        nguoimuon_sua.setHo(new NguoiMuonDAO().chuanHoaTen(nguoimuon_sua.getHo()));

        model.addAttribute("update", new NguoiMuonDAO().update(factory, nguoimuon_sua));
        return new RedirectView("http://localhost:8080/QL_THIETBI/nguoimuon/dsnm.htm");
    }

    @RequestMapping(value = "dsnm/delete", method = RequestMethod.POST)
    public String del(ModelMap model, HttpServletRequest rq, @RequestParam("manm") String manm) {
    	if(new NguoiMuonDAO().checkCoPhieuMuon(factory, manm)) {
    		model.addAttribute("error_constraint", true);
    		return home(model,rq);
    	}
        System.out.println("manm = " + manm);
        NGUOIMUON nguoimuon_xoa = new NGUOIMUON();
        nguoimuon_xoa.setManm(manm);
        model.addAttribute("delete", new NguoiMuonDAO().del(factory, nguoimuon_xoa));
        return home(model,rq);
    }

}

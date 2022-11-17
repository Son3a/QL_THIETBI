/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qltb.controller;

import qltb.dao.QuanLiDAO;
import qltb.dao.ThietBiDAO;
import qltb.entity.*;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author HOME
 */
@Controller
@RequestMapping("/thietbi/")
public class ThietBiController {

    public ThietBiController() {
        super();
    }

    @Autowired
    SessionFactory factory;

    @ModelAttribute("thietbi_moi")
    public THIETBI thietbi_moi() {
        return new THIETBI();
    }
    

    @ModelAttribute("thietbi_sua")
    public THIETBI thietbi_sua() {
        return new THIETBI();
    }


    @RequestMapping(value = "dsthietbi", method = RequestMethod.GET)
    public String home(ModelMap model, HttpServletRequest rq) {
    	HttpSession session = rq.getSession();
    	if(session.getAttribute("account_cur") == null) {
    		System.out.print("login");
    		return "redirect: http://localhost:8080/QL_THIETBI/login.htm";
    	}
        model.addAttribute("listThietbi", new ThietBiDAO().getAll1(factory));
        return "thietbi/dsthietbi";
    }

    @RequestMapping(value = "dsthietbi/insert", method = RequestMethod.POST)
    public String insert(ModelMap model, @ModelAttribute("thietbi_moi") THIETBI thietbi_moi,
            HttpServletRequest rq, @RequestParam("photo") MultipartFile photo) {
    	
        System.out.println("hello");
        String path = new String();
        path = rq.getServletContext().getRealPath("");
        System.out.println(path);
        path = path.substring(0, path.indexOf(".metadata"))  + "QL_THIETBI\\src\\main\\webapp\\resources\\img\\thietbi\\";
        System.out.println(path);
        int kqSave = saveFile(path, photo, rq);
        if (kqSave > 0) {
            thietbi_moi.setHinh(photo.getOriginalFilename());
        } else if (kqSave < 0 || photo.isEmpty()) {
            model.addAttribute("insert", false);
            return home(model,rq);
        }
        thietbi_moi.setTinhtrang("2");
        model.addAttribute("insert", new ThietBiDAO().save(factory, thietbi_moi));
        return home(model,rq);
    }

    @RequestMapping(value = "dsthietbi/edit/{id}")
    public RedirectView show_form_edit(RedirectAttributes model, @ModelAttribute("thietbi_sua") THIETBI thietbi_sua,
            @PathVariable String id, HttpServletRequest rq) {
    	HttpSession session = rq.getSession();
        if(session.getAttribute("account_cur") == null) return new RedirectView("http://localhost:8080/QL_THIETBI/login.htm");
    	model.addFlashAttribute("form_edit", true);
        model.addFlashAttribute("thietbi_sua", new ThietBiDAO().getById(id, factory));
        return new RedirectView("http://localhost:8080/QL_THIETBI/thietbi/dsthietbi.htm");
    }

    // UPDATE
    @RequestMapping(value = "dsthietbi/update", method = RequestMethod.POST)
    public RedirectView update(@ModelAttribute("thietbi_sua") THIETBI thietbi_sua, HttpServletRequest rq,
            @RequestParam("photo") MultipartFile photo, RedirectAttributes model) {
        model.addFlashAttribute("thietbi_sua", thietbi_sua);
        String path = rq.getServletContext().getRealPath("");
        path = path.substring(0, path.indexOf(".metadata"))  + "\\QL_THIETBI\\" + "\\src\\main\\webapp\\resources\\img\\thietbi\\";
        int kqSave = saveFile(path, photo, rq);
        if (kqSave > 0) {
            thietbi_sua.setHinh(photo.getOriginalFilename());
        } else if (kqSave < 0) {
            model.addFlashAttribute("update", false);
            return new RedirectView("http://localhost:8080/QL_THIETBI/thietbi/dsthietbi.htm");
        }
        model.addFlashAttribute("update", new ThietBiDAO().update(factory, thietbi_sua));

        return new RedirectView("http://localhost:8080/QL_THIETBI/thietbi/dsthietbi.htm");
    }

    private int saveFile(String path, MultipartFile photo, HttpServletRequest rq) {
        if (!photo.isEmpty()) {
            try {

                String photoPath = rq.getServletContext()
                        .getRealPath("/resources/img/thietbi/" + photo.getOriginalFilename());
//                byte[] bytes = photo.getBytes();
//                BufferedOutputStream outstream = new BufferedOutputStream(new FileOutputStream(new File(photoPath)));
//                outstream.write(bytes);
//                outstream.flush();
//                outstream.close();
                photo.transferTo(new File(path + photo.getOriginalFilename()));
                return 1;
            } catch (Exception e) {
                e.printStackTrace();
                return -1;
            }
        }
        return 0;
    }

    // DELETE
    @RequestMapping(value = "dsthietbi/delete", method = RequestMethod.POST)
    public String del(ModelMap model, @RequestParam("id") String id, HttpServletRequest rq) {
        System.out.println("id = " + id);
        if(new ThietBiDAO().checkCoPhieuMuon(factory, id)) {
    		model.addAttribute("error_constraint", true);
    		return home(model, rq);
    	}
        THIETBI thietbi_xoa = new THIETBI();
        thietbi_xoa.setMatb(id);
        model.addAttribute("delete", new ThietBiDAO().delete(factory, thietbi_xoa));
        return home(model, rq);
    }

    // IMPORT EXCEL
    private boolean checkThietbi(THIETBI thietbi) {
        if (thietbi.getTen().isEmpty() || thietbi.getHinh().isEmpty()) {
            return true;
        }
        return false;
    }
}

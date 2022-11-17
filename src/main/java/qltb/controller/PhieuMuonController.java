/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qltb.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import qltb.dao.CT_PhieuMuonDAO;
import qltb.dao.NguoiMuonDAO;
import qltb.dao.NhanVienDAO;
import qltb.dao.PhieuMuonDAO;
import qltb.dao.ThietBiDAO;
import qltb.entity.ACCOUNT;
import qltb.entity.CT_PHIEUMUON;
import qltb.entity.NGUOIMUON;
import qltb.entity.NHANVIEN;
import qltb.entity.PHIEUMUON;
import qltb.entity.THIETBI;

@Transactional
@Controller
@RequestMapping("/phieumuon/")
public class PhieuMuonController {

    @Autowired
    SessionFactory factory;

    public String generateMAPM() {
        List<PHIEUMUON> list = new PhieuMuonDAO().getAllID(factory);
        int id = 0;
        if (list.isEmpty()) {
            return "pm" + id;
        }
        String newMAPM = list.get(list.size()-1).getMapm().trim().substring(2);
        id = Integer.parseInt(newMAPM) + 1;
        return "pm" + id;
    }

    @ModelAttribute("today")
    public LocalDate getToday() {
        return LocalDate.now();
    }
    
    @ModelAttribute("slThietBiSua")
    public int sl() {
        return 0;
    }

    @ModelAttribute("phieumuon_moi")
    public PHIEUMUON phieumuon_moi() {
        return new PHIEUMUON();
    }

    @ModelAttribute("phieumuon_sua")
    public PHIEUMUON phieumuon_sua() {
        return new PHIEUMUON();
    }
    @ModelAttribute("phieumuon_chitiet")
	public PHIEUMUON phieumuon_chitiet() {
		return new PHIEUMUON();
	}

    @ModelAttribute("listNhanViens")
    public List<NHANVIEN> getListNhanViens() {
        List<NHANVIEN> list = new NhanVienDAO().loadDanhSachNhanVien(factory);
        return list;
    }

    @ModelAttribute("listNguoiMuons")
    public List<NGUOIMUON> getListNguoiMuon() {
        List<NGUOIMUON> list = new NguoiMuonDAO().getAll(factory);
        return list;
    }

    @ModelAttribute("loaiThietBis")
    public List<THIETBI> getLoaiThietBis() {
    	List<THIETBI> list = new ThietBiDAO().getAll(factory);
        List<THIETBI> listDangMuon = new ThietBiDAO().getAllDangMuon(factory);
        List<THIETBI> re = new ArrayList<THIETBI>();
        boolean kt = false;
        for(int i=0;i<list.size();i++) {
        	for(int j=0; j<listDangMuon.size();j++) 
	        	if(list.get(i).getMatb().trim().equals(listDangMuon.get(j).getMatb().trim())) {
	        		kt = true;
	        		break;
	        	}
        	if(kt==false) re.add(list.get(i));
        	kt=false;
        }
        return re;
    }
    
    @ModelAttribute("listPhong")
    public List<String> getPhong() {
    	return new ThietBiDAO().getAllPhong(factory);
    }

    @RequestMapping(value = "dsphieumuon", method = RequestMethod.GET)
    public String dsphieumuon(ModelMap model, HttpSession session, HttpServletRequest request) {
    	session = request.getSession();
    	if(session.getAttribute("account_cur") == null) {
    		System.out.print("login");
    		return "redirect: http://localhost:8080/QL_THIETBI/login.htm";
    	}
    	ACCOUNT account = (ACCOUNT) session.getAttribute("account_input");
        model.addAttribute("account_input", account);
        model.addAttribute("listPhieuMuon", new PhieuMuonDAO().getAll(factory));
        model.addAttribute("maphieumuon", generateMAPM());
        model.addAttribute("indexValue", 0);
        return "phieumuon/dsphieumuon";
    }
    
    @RequestMapping(value = "dsphieumuon/insert", method = RequestMethod.POST)
    public String insert(ModelMap model, @ModelAttribute("phieumuon_moi") PHIEUMUON phieumuon_moi,
    		HttpSession session, HttpServletRequest rq) {

    	String[] listTb = rq.getParameterValues("thietBi");
    	System.out.println("list:"+listTb[0]);
    	new PhieuMuonDAO().save(factory, phieumuon_moi);
    	
    	System.out.println("listTB"+listTb.length);
    	if(listTb.length < 2) {
    		System.out.println("save"+new CT_PhieuMuonDAO().save(factory, new CT_PHIEUMUON(new ThietBiDAO().getById(listTb[0], factory), phieumuon_moi)));
    	}
    	else
	    	for(int i = 1; i < listTb.length; i++) {
	    		System.out.println("save"+new CT_PhieuMuonDAO().save(factory, new CT_PHIEUMUON(new ThietBiDAO().getById(listTb[i], factory), phieumuon_moi)));
	    	}

    	model.addAttribute("insert", true);
        return dsphieumuon(model, session, rq);

    }
    
    @RequestMapping(value = "dsphieumuon/edit/{mapm}", method = RequestMethod.GET)
    public String show_form_edit(ModelMap model, @ModelAttribute("phieumuon_sua") PHIEUMUON phieumuon_sua,
            @PathVariable("mapm") String mapm, HttpSession session, HttpServletRequest request) {
    	session = request.getSession();
    	if(session.getAttribute("account_cur") == null) {
    		System.out.print("login");
    		return "redirect: http://localhost:8080/QL_THIETBI/login.htm";
    	}
    	model.addAttribute("form_edit", true);
        phieumuon_sua = new PhieuMuonDAO().getById(mapm.trim(), factory);
        System.out.println("phieumuonsua"+phieumuon_sua.getCt_phieumuons().size());
        model.addAttribute("indexValue", 0);
        model.addAttribute("slThietBiSua", phieumuon_sua.getCt_phieumuons().size());
        model.addAttribute("phieumuon_sua", phieumuon_sua);
        return dsphieumuon(model, session, request);
    }
    @RequestMapping(value = "dsphieumuon/update", method = RequestMethod.POST)
    public String update(ModelMap model, @ModelAttribute("phieumuon_sua") PHIEUMUON phieumuon_sua,
            HttpSession session, HttpServletRequest rq, @RequestParam("tgtra") List<String> tgtra) {
    	String[] listTb = rq.getParameterValues("thietBiSua");
    	String[] listTbs = rq.getParameterValues("tgtra");
        PHIEUMUON phieumuon_cansua = new PhieuMuonDAO().getById(phieumuon_sua.getMapm(), factory);
        phieumuon_cansua.setNm(new NguoiMuonDAO().getById(phieumuon_sua.getNm().getManm(), factory));
        
        for (CT_PHIEUMUON ele : phieumuon_cansua.getCt_phieumuons()) {
            new CT_PhieuMuonDAO().delete(factory, ele);
        }
        System.out.println("do dai tb sua"+listTb.length);
        for(int i =0;i<listTb.length;i++) System.out.println(listTb[i]);
        for(int i = 0; i < tgtra.size(); i++) {
        	System.out.println(tgtra.get(i));
    	}
        System.out.println("thoigiantra"+listTbs.length);
        int kiemTraDaTraHet = 0;
        for(int i = 0; i < listTb.length; i++) {
        	CT_PHIEUMUON temp = new CT_PHIEUMUON(new ThietBiDAO().getById(listTb[i], factory), phieumuon_sua);
        	
        	if(tgtra.get(i).equals("1")) {
        		temp.setTgtra(Date.valueOf(LocalDate.now()).toString());
        		kiemTraDaTraHet+=1;
        		System.out.print("da tra");
        	}
        	System.out.println("thay doi ct phieu muon"+new CT_PhieuMuonDAO().save(factory, temp));
    	}
        System.out.print("kttra"+kiemTraDaTraHet+""+listTb.length);
        if(kiemTraDaTraHet == listTb.length) {
        	phieumuon_cansua.setTrangthai(1);
        	new PhieuMuonDAO().update(factory, phieumuon_cansua);
        }
        else {
        	phieumuon_cansua.setTrangthai(0);
        	new PhieuMuonDAO().update(factory, phieumuon_cansua);
        }
        model.addAttribute("update", true);
        return dsphieumuon(model, session, rq);
    }
    
    @RequestMapping(value = "dsphieumuon/detail/{mapm}", method = RequestMethod.GET)
	public String show_form_detail(ModelMap model, @ModelAttribute("phieumuon_chitiet") PHIEUMUON phieumuon_chitiet,
			@PathVariable("mapm") String mapm, HttpSession session, HttpServletRequest rq) {
		model.addAttribute("form_detail", true);
		phieumuon_chitiet = new PhieuMuonDAO().getById(mapm.trim(), factory);
		System.out.print("detail"+phieumuon_chitiet.getCt_phieumuons().size());
		model.addAttribute("indexValue", 0);
		model.addAttribute("slThietBiChiTiet", phieumuon_chitiet.getCt_phieumuons().size());
		model.addAttribute("phieumuon_chitiet", phieumuon_chitiet);
		return dsphieumuon(model, session, rq);
	}
    
    // Delete
    @RequestMapping(value = "dsphieumuon/delete", method = RequestMethod.POST)
    public String delete(ModelMap model, @RequestParam("mapm") String mapm, HttpSession session,
            HttpServletRequest rq) {
    	System.out.println("hellopm");
        PHIEUMUON phieumuon_xoa = new PhieuMuonDAO().getById(mapm.trim(), factory);
            for (CT_PHIEUMUON elem : phieumuon_xoa.getCt_phieumuons()) {
                new CT_PhieuMuonDAO().delete(factory, elem);
            }
            PHIEUMUON phieumuon_canxoa = new PHIEUMUON();
            phieumuon_canxoa.setMapm(mapm.trim());
            model.addAttribute("delete", new PhieuMuonDAO().delete(factory, phieumuon_canxoa));
        
        return dsphieumuon(model, session, rq);
    }
}

package qltb.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
import org.springframework.web.multipart.MultipartFile;

import qltb.dao.AccountDAO;
import qltb.dao.NguoiMuonDAO;
import qltb.dao.NhanVienDAO;
import qltb.entity.*;

@Transactional
@Controller
@RequestMapping("/nv")
public class NhanVienController {

	@Autowired
	SessionFactory factory;

	public String getRandomMa() {
		List<NHANVIEN> list = new NhanVienDAO().loadDanhSachNhanVien(factory);
		int id = 1001;
		if (list.isEmpty()) {
            return "nv" + id;
        }
		String newMANV = list.get(list.size() - 1).getManv().substring(2);
	    id = Integer.parseInt(newMANV.trim()) + 1;
	    return "nv" + id;

	}
	
	@ModelAttribute("nhanvien_moi")
	public NHANVIEN nhanvien_moi() {
		return new NHANVIEN();
	}
	
	@ModelAttribute("maQuanLi")
	public String maQuanLi(HttpSession session) {
		ACCOUNT acc =(ACCOUNT) session.getAttribute("account_cur");
		return new NhanVienDAO().getByUsername(factory, acc.getUsername()).getManv().trim();
	}

	@ModelAttribute("nhanvien_sua")
	public NHANVIEN nhanvien_sua() {
		return new NHANVIEN();
	}

	@ModelAttribute("gioiTinhs")
	public List<String> getGioiTinhs() {
		List<String> list = new ArrayList<>();
		list.add("Nam");
		list.add("Nữ");
		return list;
	}

	@RequestMapping(value = "qlnhanvien", method = RequestMethod.GET)
	public String home(ModelMap model) {
		model.addAttribute("listNhanVien", new NhanVienDAO().loadDanhSachNhanVien(factory));
		model.addAttribute("maNhanVien",getRandomMa());
		return "nhansu/dsnv";
	}

	@RequestMapping(value = "qlnhanvien", method = RequestMethod.POST)
	public String insert(ModelMap model, @ModelAttribute("nhanvien_moi") NHANVIEN nhanvien_moi,
			@RequestParam("username") String username, @RequestParam("password") String password,
			@RequestParam("gmail") String gmail, @RequestParam("sdt") String sdt, HttpServletRequest rq,
			@RequestParam("photo") MultipartFile photo) {
		
		if (photo.isEmpty()) {
			System.out.println("hello");
			model.addAttribute("insert", false);
			model.addAttribute("nhanvien_moi", nhanvien_moi);
			return "redirect: http://localhost:8080/QL_THIETBI/nv/qlnhanvien.htm";
		} else {

				String path = rq.getServletContext().getRealPath("");
				path = path.substring(0, path.indexOf(".metadata"))  + "\\QL_THIETBI\\" + "\\src\\main\\webapp\\resources\\img\\nv\\";
				saveFile(path, photo, rq);
				
				nhanvien_moi.setHinh(photo.getOriginalFilename());
				ACCOUNT account_moi = new ACCOUNT();
				account_moi.setGmail(gmail);
				password = new AccountDAO().getMd5(password);
				account_moi.setPassword(password);
				PHANQUYEN qp = new PHANQUYEN();
				
				qp.setMapq("staff");
				account_moi.setPhanQuyen(qp);
				account_moi.setSdt(sdt);
				account_moi.setUsername(username);
				
				ACCOUNT account_temp = new AccountDAO().getByUserName(factory, username.trim());
				if(account_temp != null) {
					System.out.println("hello1");
					model.addAttribute("accountTonTai", true);
					model.addAttribute("nhanvien_moi", nhanvien_moi);
					return "redirect: http://localhost:8080/QL_THIETBI/nv/qlnhanvien.htm";
				}
				
				System.out.print(new AccountDAO().save(factory, account_moi));

				nhanvien_moi.setAcc(account_moi);
				nhanvien_moi.setTen( new NguoiMuonDAO().chuanHoaTen(nhanvien_moi.getTen()));
				nhanvien_moi.setHo( new NguoiMuonDAO().chuanHoaTen(nhanvien_moi.getHo()));
				
				model.addAttribute("insert", new NhanVienDAO().save(factory, nhanvien_moi));
				
				return "redirect: http://localhost:8080/QL_THIETBI/nv/qlnhanvien.htm";
		}
	}

	@RequestMapping(value = "qlnhanvien/edit/{id}", method = RequestMethod.GET)
	public String show_form_edit(ModelMap model, @ModelAttribute("nhanvien_sua") NHANVIEN nhanvien_sua,
			@PathVariable String id) {
		System.out.println("id = " + id);
		model.addAttribute("form_edit", true);
		model.addAttribute("nhanvien_sua", new NhanVienDAO().getById(factory, id));
		return home(model);
	}

	// UPDATE
	@RequestMapping(value = "qlnhanvien/update", method = RequestMethod.POST)
	public String update(ModelMap model, @ModelAttribute("nhanvien_sua") NHANVIEN nhanvien_sua,
			HttpServletRequest rq, @RequestParam("photo") MultipartFile photo) {
		model.addAttribute("nhanvien_sua", nhanvien_sua);
		
		NHANVIEN nhanvien_db = (NHANVIEN) new NhanVienDAO().getById(factory, nhanvien_sua.getManv());
		
		if (photo.isEmpty()) {
			ACCOUNT account_sua = nhanvien_sua.getAcc();
			account_sua.setGmail(nhanvien_sua.getAcc().getGmail());
			if(nhanvien_db.getAcc().getPassword().compareTo(nhanvien_sua.getAcc().getPassword())==0){
				account_sua.setPassword(nhanvien_sua.getAcc().getPassword());
			}
			else {
				String password = new AccountDAO().getMd5(nhanvien_sua.getAcc().getPassword());
				account_sua.setPassword(password);
			}
			account_sua.setPhanQuyen(new AccountDAO().getByUserName(factory, nhanvien_sua.getAcc().getUsername()).getPhanQuyen());
			account_sua.setSdt(nhanvien_sua.getAcc().getSdt());
			account_sua.setUsername(nhanvien_sua.getAcc().getUsername());
			
			System.out.print(nhanvien_sua.getAcc().getSdt());

			System.out.print(new AccountDAO().update(factory, account_sua));

			nhanvien_sua.setAcc(account_sua);
			
			nhanvien_sua.setTen( new NguoiMuonDAO().chuanHoaTen(nhanvien_sua.getTen()));
			nhanvien_sua.setHo( new NguoiMuonDAO().chuanHoaTen(nhanvien_sua.getHo()));
			
			model.addAttribute("update", new NhanVienDAO().update(factory, nhanvien_sua)); // Xá»­ lÃ½ thÃ´ng bÃ¡o thÃªm thÃ nh																			// cÃ´ng
			model.addAttribute("nhanvien_sua", new NHANVIEN());
			return "redirect: http://localhost:8080/QL_THIETBI/nv/qlnhanvien.htm";
		} else {

				String path = rq.getServletContext().getRealPath("");

				path = path.substring(0, path.indexOf(".metadata"))  + "\\QL_THIETBI\\" + "\\src\\main\\webapp\\resources\\img\\nv\\";
				int kqSave = saveFile(path, photo, rq);
				if (kqSave > 0)
					nhanvien_sua.setHinh(photo.getOriginalFilename());
				ACCOUNT account_sua = nhanvien_sua.getAcc();
				account_sua.setGmail(nhanvien_sua.getAcc().getGmail());
				
				if(nhanvien_db.getAcc().getPassword().compareTo(nhanvien_sua.getAcc().getPassword())==0){
					account_sua.setPassword(nhanvien_sua.getAcc().getPassword());
				}
				else {
					String password = new AccountDAO().getMd5(nhanvien_sua.getAcc().getPassword());
					account_sua.setPassword(password);
				}
				
				account_sua.setPhanQuyen(nhanvien_sua.getAcc().getPhanQuyen());
				account_sua.setSdt(nhanvien_sua.getAcc().getSdt());
				account_sua.setUsername(nhanvien_sua.getAcc().getUsername());

				new AccountDAO().update(factory, account_sua);

				nhanvien_sua.setAcc(account_sua);
				
				nhanvien_sua.setTen( new NguoiMuonDAO().chuanHoaTen(nhanvien_sua.getTen()));
				nhanvien_sua.setHo( new NguoiMuonDAO().chuanHoaTen(nhanvien_sua.getHo()));
				nhanvien_sua.setCmnd(nhanvien_sua.getCmnd());
				
				model.addAttribute("update", new NhanVienDAO().update(factory, nhanvien_sua));
				model.addAttribute("nhanvien_sua", new NHANVIEN());
				return "redirect: http://localhost:8080/QL_THIETBI/nv/qlnhanvien.htm";
		}
	}

	@RequestMapping(value = "qlnhanvien/delete", method = RequestMethod.POST)
	public String del(ModelMap model, @RequestParam("manv") String manv) {
		System.out.println("manv = " + manv);
		if(new NhanVienDAO().checkCoPhieuNhap(factory, manv) || new NhanVienDAO().checkCoPhieuMuon(factory, manv)
				|| new NhanVienDAO().checkCoPhieuTL(factory, manv)) {
    		model.addAttribute("error_constraint", true);
    		return home(model);
    	}
		NHANVIEN nhanvien_xoa = new NHANVIEN();
		nhanvien_xoa.setManv(manv);
		NHANVIEN nhanvien = new NHANVIEN();
		nhanvien = (NHANVIEN) new NhanVienDAO().getById(factory, manv);
		ACCOUNT account_xoa = new ACCOUNT();
		account_xoa.setUsername(nhanvien.getAcc().getUsername());
		model.addAttribute("delete", new NhanVienDAO().delete(factory, nhanvien_xoa));

		model.addAttribute("delete", new AccountDAO().delete(factory, account_xoa));
		
		return home(model);
	}
	
	private int saveFile(String path, MultipartFile photo, HttpServletRequest rq) {
		if (!photo.isEmpty()) {
			try {

				String photoPath = rq.getServletContext()
						.getRealPath("/resources/img/nv/" + photo.getOriginalFilename());
				byte[] bytes = photo.getBytes();
				BufferedOutputStream outstream = new BufferedOutputStream(new FileOutputStream(new File(photoPath)));
				outstream.write(bytes);
				outstream.flush();
				outstream.close();
				photo.transferTo(new File(path + photo.getOriginalFilename()));
				return 1; 
			} catch (Exception e) {
				e.printStackTrace();
				return -1;
			}
		}
		return 0;
	}
	
	@RequestMapping(value = "changePassword", method = RequestMethod.GET)
	public String changePassWord(ModelMap model) {
		model.addAttribute("accountChange", new ACCOUNT());
		return "PW";
	}
	@RequestMapping(value = "changePassword", method = RequestMethod.POST)
	public String changePassWord(ModelMap model, @ModelAttribute("accountChange") ACCOUNT accountChange,
			@RequestParam("newPassword") String newPassword, @RequestParam("confirmPassword") String confirmPassword,
			HttpSession session) {
		ACCOUNT acc = (ACCOUNT) session.getAttribute("account_cur");
		accountChange.setUsername(acc.getUsername());
		accountChange.setPhanQuyen(acc.getPhanQuyen());
		ACCOUNT testAccount = new AccountDAO().getByUserName(factory, accountChange.getUsername());
		
		String passwordHash = new AccountDAO().getMd5(accountChange.getPassword());
		
		if(testAccount!=null) {
			if(confirmPassword.compareTo(newPassword)==0 && testAccount.getPassword().compareTo(passwordHash)==0) {
				confirmPassword = new AccountDAO().getMd5(confirmPassword);
						
				testAccount.setPassword(confirmPassword);
				new AccountDAO().update(factory, testAccount);
				model.addAttribute("changeSuccess", true);
			}
			else {
				model.addAttribute("changeSuccess", false);
				return changePassWord(model);
			}
		}
		else {
			model.addAttribute("changeSuccess", false);
			model.addAttribute("message", "Username không tồn tại");
			return changePassWord(model);
		}
		model.addAttribute("changeSuccess", true);
		session.setAttribute("role", null);
		session.setAttribute("user", null);
		return "redirect: http://localhost:8080/QL_THIETBI/nv/changePassword.htm";
	}
}

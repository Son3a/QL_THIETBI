/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qltb.controller;

import qltb.controller.*;
import qltb.dao.AccountDAO;
import qltb.entity.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author HOME
 */
@Transactional
@Controller
@RequestMapping("/")
public class LoginController {

    @Autowired
    SessionFactory factory;
    
    @ModelAttribute("account_input")
    public ACCOUNT account() {
        return new ACCOUNT();
    }

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getIndex(HttpSession session, ModelMap model) {
        session.setAttribute("role", null);
        session.setAttribute("account", null);
        return "login";
    }
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session, ModelMap model) {
    	session.removeAttribute("account_cur");
    	session.removeAttribute("accountLogined");
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute("account_input") ACCOUNT accountInputed, HttpServletRequest rq, HttpSession session, ModelMap model) {
        ACCOUNT account_query = new AccountDAO().getByUserName(factory, accountInputed.getUsername());
        if (accountInputed.getUsername().equals("") || accountInputed.getPassword().equals("")) {
            model.addAttribute("message", "Vui lòng điền đủ thông tin!");
            return getIndex(session, model);
        }
        String hashPW = new AccountDAO().getMd5(accountInputed.getPassword());
        System.out.println(account_query == null || (account_query.getPassword().equals(hashPW) == false));
        if (account_query == null || (account_query.getPassword().equals(hashPW) == false) || (accountInputed.getUsername().equals(account_query.getUsername().trim()) == false)) {
            model.addAttribute("message", "Sai thông tin!");
            return getIndex(session, model);
        }

        session.setAttribute("accountLogined", accountInputed);

        if (account_query.getPhanQuyen().getMapq().equals("admin")) {
            session.setAttribute("role", "admin");
            session.setAttribute("account_cur", account_query);
            return "redirect: phieumuon/dsphieumuon.htm";
        }

        if (account_query.getPhanQuyen().getMapq().equals("staff")) {
            session.setAttribute("role", "staff");
            session.setAttribute("account_cur", account_query);
            return "redirect: phieumuon/dsphieumuon.htm";
        }
        return "redirect: phieumuon/dsphieumuon";
    }

}

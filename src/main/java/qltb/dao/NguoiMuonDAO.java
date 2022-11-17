/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qltb.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.Query;
import org.springframework.transaction.annotation.Transactional;
import qltb.entity.*;

/**
 *
 * @author HOME
 */
@Transactional
public class NguoiMuonDAO {

    public List<NGUOIMUON> getAll(SessionFactory factory) {
        Session session;
        try {
            session = factory.getCurrentSession();
        } catch (HibernateException e) {
            session = factory.openSession();
        }
        String hql = "from NGUOIMUON";
        Query query = session.createQuery(hql);
        List<NGUOIMUON> list = (List<NGUOIMUON>) query.list();
        return list;
    }

    public boolean checkCoPhieuMuon(SessionFactory factory, String id) {
        Session session;
        try {
            session = factory.getCurrentSession();
        } catch (HibernateException e) {
            session = factory.openSession();
        }
        String hql = "from PHIEUMUON where MANM =:id";
        Query query = session.createQuery(hql);
        query.setParameter("id", id.trim());
        
        if(query.list().size() == 0)return false;
        else return true;
    }
    
    public Boolean save(SessionFactory factory, NGUOIMUON newNguoimuon) {
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        try {
            session.save(newNguoimuon);
            t.commit();
            return true;

        } catch (Exception e) {
            t.rollback();
            e.printStackTrace();
            return false;

        } finally {
            session.close();
        }
    }

    public Boolean update(SessionFactory factory, NGUOIMUON updateNguoimuon) {
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        try {
            session.update(updateNguoimuon);
            t.commit();
            return true;

        } catch (Exception e) {
            t.rollback();
            e.printStackTrace();
            return false;

        } finally {
            session.close();
        }
    }

    public Boolean del(SessionFactory factory, NGUOIMUON delNguoimuon) {
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        Boolean result = true;
        try {
            session.delete(delNguoimuon);
            t.commit();
            result = true;

        } catch (Exception e) {
            t.rollback();
            e.printStackTrace();
            result = false;

        } finally {
            session.close();
        }

        return result;
    }

    public NGUOIMUON getById(String id, SessionFactory factory) {
        Session session;
        try {
            session = factory.getCurrentSession();
        } catch (HibernateException e) {
            session = factory.openSession();
        }
        NGUOIMUON list = (NGUOIMUON) session.get(NGUOIMUON.class, id);
        return list;
    }

    public String chuanHoa(String str) {
        str = str.trim();
        str = str.replaceAll("\\s+", " ");
        return str;
    }

    public String chuanHoaTen(String str) {

        str = chuanHoa(str);
        String temp[] = str.split(" ");
        str = ""; // ? ^-^
        for (int i = 0; i < temp.length; i++) {
            str += String.valueOf(temp[i].charAt(0)).toUpperCase() + temp[i].substring(1).toLowerCase();
            if (i < temp.length - 1) // ? ^-^
            {
                str += " ";
            }
        }
        return str;
    }

    public NGUOIMUON getByCMND(String cmnd, SessionFactory factory) {
        Session session;
        try {
            session = factory.getCurrentSession();
        } catch (HibernateException e) {
            session = factory.openSession();
        }
        String hql = "from NGUOIMUON where cmnd = :cmnd";
        Query query = session.createQuery(hql);
        query.setParameter("cmnd", cmnd);
        if (query.list().isEmpty()) {
            return null;
        }
        return (NGUOIMUON) query.list().get(0);
    }
}

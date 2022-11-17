/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qltb.dao;

import qltb.entity.*;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author HOME
 */
@Repository
@Transactional
public class QuanLiDAO {

    public List<QUANLI> loadDanhSachQuanLi(SessionFactory factory) {
        Session session;

        try {
            session = factory.getCurrentSession();
        } catch (HibernateException e) {
            session = factory.openSession();
        }
        String hql = "from QUANLI";

        Query query = session.createQuery(hql);
        List<QUANLI> list = query.list();
        return list;
    }

    public Boolean save(SessionFactory factory, QUANLI newQuanli) {
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        try {
            session.save(newQuanli);
            t.commit();
            return true;

        } catch (Exception e) {
            t.rollback();
            return false;

        } finally {
            session.close();
        }
    }
    
    public boolean checkCoNhanVien(SessionFactory factory, String id) {
        Session session;
        try {
            session = factory.getCurrentSession();
        } catch (HibernateException e) {
            session = factory.openSession();
        }
        String hql = "from NHANVIEN where maql =:id";
        Query query = session.createQuery(hql);
        query.setParameter("id", id.trim());
        
        if(query.list().size() == 0)return false;
        else return true;
    }

    public Boolean update(SessionFactory factory, QUANLI updateQuanli) {
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        try {
            session.update(updateQuanli);
            t.commit();
            return true;

        } catch (Exception e) {
            t.rollback();
            return false;

        } finally {
            session.close();
        }
    }

    public Boolean delete(SessionFactory factory, QUANLI delQuanli) {
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        try {
            session.delete(delQuanli);;
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

    public QUANLI getById(String id, SessionFactory factory) {
        Session session;
        try {
            session = factory.getCurrentSession();
        } catch (HibernateException e) {
            session = factory.openSession();
        }
        String hql = "from QUANLI where maql = :id";
        Query query = session.createQuery(hql);
        query.setParameter("id", id.toString());
        QUANLI list =(QUANLI) query.list().get(0);
        return list;
    }
}

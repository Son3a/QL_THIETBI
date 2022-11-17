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
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author HOME
 */
@Transactional
public class PhieuMuonDAO {

    public List<PHIEUMUON> getAll(SessionFactory factory) {
        Session session;
        try {
            session = factory.getCurrentSession();
        } catch (Exception e) {
            session = factory.openSession();
        }
        String hql = "FROM PHIEUMUON order by TGMUON desc";
        Query query = session.createQuery(hql);
        List<PHIEUMUON> list = query.list();
        return list;
    }
    
    public List<PHIEUMUON> getAllID(SessionFactory factory) {
        Session session;
        try {
            session = factory.getCurrentSession();
        } catch (Exception e) {
            session = factory.openSession();
        }
        String hql = "FROM PHIEUMUON";
        Query query = session.createQuery(hql);
        List<PHIEUMUON> list = query.list();
        return list;
    }

    public Boolean save(SessionFactory factory, PHIEUMUON newPhieumuon) {
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        try {
            session.save(newPhieumuon);
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

    public PHIEUMUON getById(String id, SessionFactory factory) {
        Session session;
        try {
            session = factory.getCurrentSession();
        } catch (HibernateException e) {
            session = factory.openSession();
        }
        String hql = "from PHIEUMUON where mapm = :id";
        Query query = session.createQuery(hql);
        query.setParameter("id", id);
        if (query.list().isEmpty()) {
            return null;
        }
        PHIEUMUON list = (PHIEUMUON) query.list().get(0);
        return list;
    }

    public Boolean update(SessionFactory factory, PHIEUMUON upPhieumuon) {
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        try {
            session.update(upPhieumuon);
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

    public Boolean delete(SessionFactory factory, PHIEUMUON delPhieumuon) {
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        try {
            session.delete(delPhieumuon);
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
}

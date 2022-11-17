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
public class PhieuThanhLiDAO {

    public List<PHIEUTHANHLY> getAll(SessionFactory factory) {
        Session session;
        try {
            session = factory.getCurrentSession();
        } catch (HibernateException e) {
            session = factory.openSession();
        }
        String hql = "from PHIEUTHANHLY order by tgthanhly desc, maptl desc";
        Query query = session.createQuery(hql);
        List<PHIEUTHANHLY> list = query.list();
        return list;
    }

    public Boolean update1(SessionFactory factory, PHIEUTHANHLY PHIEUTHANHLY) {
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        try {
            session.update(PHIEUTHANHLY);
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
    
    public Boolean saveOrUpdate(SessionFactory factory, PHIEUTHANHLY PHIEUTHANHLY) {
        Session session;
        try {
            session = factory.getCurrentSession();
            System.out.println("session current");
        } catch (HibernateException e) {
            session = factory.openSession();
            System.out.println("session opened");
        }
        Transaction t = session.beginTransaction();

        Boolean result = true;
        try {
            session.saveOrUpdate(PHIEUTHANHLY);
            for (CT_PHIEUTHANHLY ct_ptl : PHIEUTHANHLY.getCt_phieuthanhlys()) {
                session.saveOrUpdate(ct_ptl);
            }
            t.commit();
            result = true;

        } catch (Exception e) {
            t.rollback();
            result = false;

        } finally {
            session.close();
        }

        return result;
    }

    public Boolean deletect_ptl_by_MAPTL(SessionFactory factory, PHIEUTHANHLY PHIEUTHANHLY) {
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        String hql = "delete from CT_PHIEUTHANHLY where phieuthanhly = :PHIEUTHANHLY";
        try {
            Query query = session.createQuery(hql);
            query.setParameter("PHIEUTHANHLY", PHIEUTHANHLY);
            if (query.executeUpdate() < 0) {
                return false;
            }
            t.commit();

        } catch (Exception e) {
            t.rollback();
            return false;

        } finally {
            session.close();
        }
        return true;
    }

    public Boolean update(SessionFactory factory, PHIEUTHANHLY PHIEUTHANHLY) {

        if (!deletect_ptl_by_MAPTL(factory, PHIEUTHANHLY)) {
            return false;
        }
        return saveOrUpdate(factory, PHIEUTHANHLY);
    }

    public Boolean delete(SessionFactory factory, PHIEUTHANHLY PHIEUTHANHLY) {
        Boolean result = false;
        if (!deletect_ptl_by_MAPTL(factory, PHIEUTHANHLY)) {
            return result;
        }
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        try {
            session.delete(PHIEUTHANHLY);
            t.commit();
            result = true;

        } catch (Exception e) {
            t.rollback();
            result = false;
        } finally {
            session.close();
        }
        return result;
    }

    public PHIEUTHANHLY getById(String id, SessionFactory factory) {
        Session session = factory.openSession();
        String hql = "from PHIEUTHANHLY where maptl = :id";
        Query query = session.createQuery(hql);
        query.setParameter("id", id);
        List<PHIEUTHANHLY> list = query.list();
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public PHIEUTHANHLY getById2delete(String id, SessionFactory factory) {
        Session session = factory.openSession();
        String hql = "from PHIEUTHANHLY where maptl = :id";
        Query query = session.createQuery(hql);
        query.setParameter("id", id);
        List<PHIEUTHANHLY> list = query.list();
        if (list.isEmpty()) {
            return null;
        }
        session.close();
        return list.get(0);
    }
}

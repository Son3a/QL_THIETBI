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
public class PhieuNhapDAO {

    public List<PHIEUNHAP> getAll(SessionFactory factory) {
        Session session;
        try {
            session = factory.getCurrentSession();
        } catch (HibernateException e) {
            session = factory.openSession();
        }
        String hql = "from PHIEUNHAP";
        Query query = session.createQuery(hql);
        List<PHIEUNHAP> list = query.list();
        return list;
    }

    public Boolean update1(SessionFactory factory, PHIEUNHAP phieunhap) {
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        try {
            session.update(phieunhap);
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
    
    public Boolean saveOrUpdate(SessionFactory factory, PHIEUNHAP phieunhap) {
        Session session;
        try {
            session = factory.getCurrentSession();
            System.out.println("session current");
        } catch (HibernateException e) {
            session = factory.openSession();
            e.printStackTrace();
            System.out.println("session opened");
        }
        Transaction t = session.beginTransaction();

        Boolean result = true;
        try {
            session.saveOrUpdate(phieunhap);
            for (CT_PHIEUNHAP ct_pn : phieunhap.getCt_phieunhaps()) {
                session.saveOrUpdate(ct_pn);
            }

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

    public Boolean deleteCT_PN_by_MAPN(SessionFactory factory, PHIEUNHAP phieunhap) {
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        String hql = "delete from CT_PHIEUNHAP where phieunhap = :phieunhap";

        try {
            Query query = session.createQuery(hql);
            query.setParameter("phieunhap", phieunhap);

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

    public Boolean update(SessionFactory factory, PHIEUNHAP phieunhap) {

        if (!deleteCT_PN_by_MAPN(factory, phieunhap)) {
            return false;
        }
        return saveOrUpdate(factory, phieunhap);
    }

    public Boolean delete(SessionFactory factory, PHIEUNHAP phieunhap) {
        Boolean result = false;
        if (!deleteCT_PN_by_MAPN(factory, phieunhap)) {
            return result;
        }
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        try {
            session.delete(phieunhap);
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

    public PHIEUNHAP getById(String id, SessionFactory factory) {
        Session session = factory.openSession();
        String hql = "from PHIEUNHAP where mapn = :id";
        Query query = session.createQuery(hql);
        query.setParameter("id", id);
        List<PHIEUNHAP> list = query.list();
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public PHIEUNHAP getById2delete(String id, SessionFactory factory) {
        Session session = factory.openSession();
        String hql = "from PHIEUNHAP where mapn = :id";
        Query query = session.createQuery(hql);
        query.setParameter("id", id);
        List<PHIEUNHAP> list = query.list();
        if (list.isEmpty()) {
            return null;
        }
        session.close();
        return list.get(0);
    }
}

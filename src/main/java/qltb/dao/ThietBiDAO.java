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

/**
 *
 * @author HOME
 */
public class ThietBiDAO {
	
	public List<THIETBI> getAll1(SessionFactory factory) {
        Session session;
        try {
            session = factory.getCurrentSession();
        } catch (HibernateException e) {
            session = factory.openSession();
        }
        String hql = "from THIETBI order by matb desc";
        Query query = session.createQuery(hql);
        
		List<THIETBI> list = query.list();
        return list;
    }
	
	public List<THIETBI> getAllDoiNhap(SessionFactory factory) {
        Session session;
        try {
            session = factory.getCurrentSession();
        } catch (HibernateException e) {
            session = factory.openSession();
        }
        String hql = "from THIETBI where tinhtrang = '2'";
        Query query = session.createQuery(hql);
        
		List<THIETBI> list = query.list();
        return list;
    }
	
    public List<THIETBI> getAll(SessionFactory factory) {
        Session session;
        try {
            session = factory.getCurrentSession();
        } catch (HibernateException e) {
            session = factory.openSession();
        }
        String hql = "from THIETBI where tinhtrang = '0' and PHONG is not null order by matb desc";
        Query query = session.createQuery(hql);
        
		List<THIETBI> list = query.list();
        return list;
    }
    
    public List<THIETBI> getAllDangMuon(SessionFactory factory) {
        Session session;
        try {
            session = factory.getCurrentSession();
        } catch (HibernateException e) {
            session = factory.openSession();
        }
        String hql = "from THIETBI where matb in (SELECT thietbi_muon.matb FROM CT_PHIEUMUON where TGTRA is null) and tinhtrang = '0'";;
        Query query = session.createQuery(hql);
        
		List<THIETBI> list = query.list();
        return list;
    }

    public Boolean save(SessionFactory factory, THIETBI newThietbi) {
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        try {
            session.save(newThietbi);
            t.commit();
            return true;
        } catch (Exception e) {
            t.rollback();
            return false;
        } finally {
            session.close();
        }
    }

    
    public Boolean update(SessionFactory factory, THIETBI updateThietbi) {
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        try {
            session.update(updateThietbi);
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

    public Boolean delete(SessionFactory factory, THIETBI delThietbi) {
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        try {
            session.delete(delThietbi);;
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
    
    public boolean checkCoPhieuMuon(SessionFactory factory, String id) {
        Session session;
        try {
            session = factory.getCurrentSession();
        } catch (HibernateException e) {
            session = factory.openSession();
        }
        String hql = "from CT_PHIEUMUON where matb =:id";
        Query query = session.createQuery(hql);
        query.setParameter("id", id.trim());
        
        if(query.list().size() == 0)return false;
        else return true;
    }
    
    public List<String> getAllPhong(SessionFactory factory) {
        Session session;
        try {
            session = factory.getCurrentSession();
        } catch (HibernateException e) {
            session = factory.openSession();
        }
        String hql = "select distinct tb.phong from THIETBI tb where tb.phong is not null";
        Query query = session.createQuery(hql);
        
		List<String> list = query.list();
        return list;
    }

    public THIETBI getById(String id, SessionFactory factory) {
        Session session;
        try {
            session = factory.getCurrentSession();
        } catch (HibernateException e) {
            session = factory.openSession();
        }
        String hql = "from THIETBI where matb = :id";
        Query query = session.createQuery(hql);
        query.setParameter("id", id);
        if (query.list().isEmpty()) {
            return null;
        }
        THIETBI list =(THIETBI) query.list().get(0);
        
        return list;
    }

    public List<THIETBI> getByName(String name, SessionFactory factory) {
        Session session;
        try {
            session = factory.getCurrentSession();
        } catch (HibernateException e) {
            session = factory.openSession();
        }
        String hql = "from THIETBI where ten = :name";
        Query query = session.createQuery(hql);
        @SuppressWarnings("unchecked")
		List<THIETBI> list = query.list();
        session.close();
        return list;
    }

    public THIETBI getById2thanhly(String id, SessionFactory factory) {
        Session session;
        try {
            session = factory.getCurrentSession();
        } catch (HibernateException e) {
            session = factory.openSession();
        }
        String hql = "from THIETBI where matb = :id";
        Query query = session.createQuery(hql);
        query.setParameter("id", id);
        THIETBI thietbi = null;
        if (query.list().isEmpty()) {
            return thietbi;
        } else {
            thietbi =(THIETBI) query.list().get(0);
        }
        session.close();
        return thietbi;
    }

    public THIETBI getById2nhap(String id, SessionFactory factory) {
        Session session;
        try {
            session = factory.getCurrentSession();
        } catch (HibernateException e) {
            session = factory.openSession();
        }
        String hql = "from THIETBI where matb = :id";
        Query query = session.createQuery(hql);
        query.setParameter("id", id);
        THIETBI thietbi = null;
        if (query.list().isEmpty()) {
            return thietbi;
        } else {
            thietbi =(THIETBI) query.list().get(0);
        }

        session.close();
        return thietbi;
    }
}

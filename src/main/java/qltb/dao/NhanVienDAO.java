/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qltb.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import qltb.entity.*;

@Repository
@Transactional
public class NhanVienDAO {

    public List<NHANVIEN> loadDanhSachNhanVien(SessionFactory factory) {
        Session session;
        try {
            session = factory.getCurrentSession();
        } catch (Exception e) {
            session = factory.openSession();
        }
        String hql = "from NHANVIEN";
        Query query = session.createQuery(hql);
        @SuppressWarnings("unchecked")
		List<NHANVIEN> list = (List<NHANVIEN>) query.list();
        return list;
    }

    public Boolean save(SessionFactory factory, NHANVIEN newNhanvien) {
        Session session;
        session = factory.openSession();
        Transaction t = session.beginTransaction();
        try {
            session.save(newNhanvien);
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

    
    
    
    public boolean checkCoPhieuTL(SessionFactory factory, String id) {
        Session session;
        try {
            session = factory.getCurrentSession();
        } catch (HibernateException e) {
            session = factory.openSession();
        }
        String hql = "from PHIEUTHANHLY where nhanvien_thanhly.manv =:id";
        Query query = session.createQuery(hql);
        query.setParameter("id", id.trim());
        
        if(query.list().size() == 0)return false;
        else return true;
    }
    
    public boolean checkCoPhieuNhap(SessionFactory factory, String id) {
        Session session;
        try {
            session = factory.getCurrentSession();
        } catch (HibernateException e) {
            session = factory.openSession();
        }
        String hql = "from PHIEUNHAP where nhanvien.manv =:id";
        Query query = session.createQuery(hql);
        query.setParameter("id", id.trim());
        
        if(query.list().size() == 0)return false;
        else return true;
    }
    
    public boolean checkCoPhieuMuon(SessionFactory factory, String id) {
        Session session;
        try {
            session = factory.getCurrentSession();
        } catch (HibernateException e) {
            session = factory.openSession();
        }
        String hql = "from PHIEUMUON where manv =:id";
        Query query = session.createQuery(hql);
        query.setParameter("id", id.trim());
        
        if(query.list().size() == 0)return false;
        else return true;
    }
    
    public Boolean delete(SessionFactory factory, NHANVIEN delNhanvien) {
        Session session;
        session = factory.openSession();
        Transaction t = session.beginTransaction();
        try {
            session.delete(delNhanvien);
            t.commit();
            return true;
        } catch (Exception e) {
            t.rollback();
            return false;
        } finally {
            session.close();
        }
    }

    public Boolean update(SessionFactory factory, NHANVIEN updateNhanvien) {
        Session session;
        session = factory.openSession();
        Transaction t = session.beginTransaction();
        try {
            session.update(updateNhanvien);
            t.commit();
            return true;
        } catch (Exception e) {
            t.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public List<NHANVIEN> getByName(SessionFactory factory, String name) {
        Session session;
        try {
            session = factory.getCurrentSession();
        } catch (Exception e) {
            session = factory.openSession();
        }
        String hlq = "from NHANVIEN where TEN = :name";
        Query query = session.createQuery(hlq);
        query.setParameter("name", name);
        if (query.list().isEmpty()) {
            return null;
        }
        @SuppressWarnings("unchecked")
		List<NHANVIEN> list = query.list();
        session.close();
        return list;
    }

    public NHANVIEN getById(SessionFactory factory, String id) {
        Session session;
        try {
            session = factory.getCurrentSession();
        } catch (Exception e) {
            session = factory.openSession();
        }
        String hlq = "from NHANVIEN where MANV = :id";
        Query query = session.createQuery(hlq);
        query.setParameter("id", id);
        if (query.list().isEmpty()) {
            return null;
        }
        @SuppressWarnings("unchecked")
		NHANVIEN list =(NHANVIEN) query.list().get(0);
        return list;
    }
    
    public NHANVIEN getByUsername(SessionFactory factory, String id) {
        Session session;
        try {
            session = factory.getCurrentSession();
        } catch (Exception e) {
            session = factory.openSession();
        }
        String hlq = "from NHANVIEN where username = :id";
        Query query = session.createQuery(hlq);
        query.setParameter("id", id);
        if (query.list().isEmpty()) {
            return null;
        }
        @SuppressWarnings("unchecked")
		NHANVIEN list =(NHANVIEN) query.list().get(0);
        return list;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qltb.dao;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import qltb.entity.*;

/**
 *
 * @author HOME
 */
@Repository
@Transactional
public class AccountDAO {

    public List<ACCOUNT> loadAccounts(SessionFactory factory) {
        Session session;
        try {
            session = factory.getCurrentSession();
        } catch (Exception e) {
            session = factory.openSession();
        }
        String hql = "from ACCOUNT";
        Query query = session.createQuery(hql);
        @SuppressWarnings("unchecked")
		List<ACCOUNT> listAccount = query.list();
        return listAccount;
    }

    public Boolean save(SessionFactory factory, ACCOUNT newAccount) {
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        try {
            session.save(newAccount);
            t.commit();
            return true;
        } catch (Exception e) {
        	e.printStackTrace();
            t.rollback();
            return false;
        } finally {
            session.close();
        }
    }
    
    public Boolean changeStaff(SessionFactory factory, String username) {
    	Session session;
        session = factory.openSession();
        String hql = "UPDATE ACCOUNT a set a.phanQuyen = 'admin' where a.username =:username ";
        Query query = session.createQuery(hql);
        query.setParameter("username", username);
       
        query.executeUpdate();
        return true;
    }
    
    public Boolean changeAdmin(SessionFactory factory, String username) {
    	Session session;
        session = factory.openSession();
        String hql = "UPDATE ACCOUNT a set a.phanQuyen = 'staff' where a.username =:username ";
        Query query = session.createQuery(hql);
        query.setParameter("username", username);
       
        query.executeUpdate();
        return true;
    }

    public Boolean delete(SessionFactory factory, ACCOUNT delAccount) {
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        try {
            session.delete(delAccount);
            t.commit();
            return true;
        } catch (Exception e) {
            t.rollback();
            return false;
        } finally {
            session.close();
        }
    }

    public Boolean update(SessionFactory factory, ACCOUNT updateAccount) {
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        try {
            session.update(updateAccount);
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

    public List<ACCOUNT> getByName(SessionFactory factory, String name) {
        Session session;
        try {
            session = factory.getCurrentSession();
        } catch (Exception e) {
            session = factory.openSession();
        }
        String hlq = "from ACCOUNT where TEN = :name";
        Query query = session.createQuery(hlq);
        query.setParameter("name", name);
        if (query.list().isEmpty()) {
            return null;
        }
        @SuppressWarnings("unchecked")
		List<ACCOUNT> list = query.list();
        session.close();
        return list;
    }

    public ACCOUNT getByUserName(SessionFactory factory, String userName) {
        Session session;
        try {
            session = factory.getCurrentSession();
        } catch (Exception e) {
            session = factory.openSession();
        }
        String hlq = "from ACCOUNT where USERNAME = :userName";
        Query query = session.createQuery(hlq);
        query.setParameter("userName", userName);
        if (query.list().isEmpty()) {
            return null;
        }
        ACCOUNT result = (ACCOUNT) query.list().get(0);
        return result;
    }
    
    public String getMd5(String input) {
        try {

            MessageDigest md = MessageDigest.getInstance("MD5");

            byte[] messageDigest = md.digest(input.getBytes());

            BigInteger no = new BigInteger(1, messageDigest);

            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}

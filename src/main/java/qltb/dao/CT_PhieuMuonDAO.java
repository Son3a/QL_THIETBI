/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qltb.dao;

import qltb.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import qltb.entity.*;
/**
 *
 * @author HOME
 */
public class CT_PhieuMuonDAO {

    public Boolean save(SessionFactory factory, CT_PHIEUMUON newCt_phieumuon) {
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        try {
            session.save(newCt_phieumuon);
            t.commit();
            return true;
        } catch (Exception e) {
            t.rollback();
            return false;
        } finally {
            session.close();
        }
    }

    public Boolean update(SessionFactory factory, CT_PHIEUMUON updateCt_phieumuon) {
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        try {
            session.update(updateCt_phieumuon);
            t.commit();
            return true;
        } catch (Exception e) {
            t.rollback();
            return false;
        } finally {
            session.close();
        }
    }

    public Boolean delete(SessionFactory factory, CT_PHIEUMUON delCt_phieumuon) {
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        try {
            session.delete(delCt_phieumuon);
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

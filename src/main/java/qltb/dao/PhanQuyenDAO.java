/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qltb.dao;

import qltb.entity.*;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author HOME
 */
@Repository
@Transactional
public class PhanQuyenDAO {

    public List<PHANQUYEN> getAll(SessionFactory factory) {
        Session session = factory.getCurrentSession();
        String hql = "FROM PHANQUYEN";
        Query query = session.createQuery(hql);
        List<PHANQUYEN> list = query.list();
        return list;
    }
}

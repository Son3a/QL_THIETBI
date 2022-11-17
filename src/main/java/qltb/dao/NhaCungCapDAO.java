package qltb.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import qltb.entity.NHACUNGCAP;

public class NhaCungCapDAO {
	public List<NHACUNGCAP> loadNcc(SessionFactory factory) {
        Session session;
        try {
            session = factory.getCurrentSession();
        } catch (Exception e) {
            session = factory.openSession();
        }
        String hql = "from NHACUNGCAP";
        Query query = session.createQuery(hql);
        @SuppressWarnings("unchecked")
		List<NHACUNGCAP> listAccount = query.list();
        return listAccount;
    }
}

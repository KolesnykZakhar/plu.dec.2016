package dao.category;


import dao.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    @Override
    public Category selectById(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return (Category) session.byId(Category.class).getReference(id);
    }

    @Override
    public void insert(Category category) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(category);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(Category category) {
        throw new UnsupportedOperationException("not initialized");

    }

    @Override
    public void remove(Category category) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(category);
        transaction.commit();
        session.close();
    }

    @Override
    public List<Category> list() {
        throw new UnsupportedOperationException("not initialized");

    }
}

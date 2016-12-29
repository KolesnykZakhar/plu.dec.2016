package dao.product;

import dao.HibernateUtil;
import dao.user.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ProductDaoImpl implements ProductDao {
    @Override
    public Product selectById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return (Product) session.byId(Product.class).getReference(id);    }

    @Override
    public void insert(Product product) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(product);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(Product product) {
        throw new UnsupportedOperationException("not initialized");
    }

    @Override
    public void remove(Product product) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(product);
        transaction.commit();
        session.close();
    }

    @Override
    public List<Product> list() {
        throw new UnsupportedOperationException("not initialized");
    }
}

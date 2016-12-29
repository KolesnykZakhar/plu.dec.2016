package dao.product;

import dao.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class ProductDaoImpl implements ProductDao {
    @Override
    public Product selectById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return (Product) session.byId(Product.class).getReference(id);
    }

    @Override
    public void insert(Product product) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.save(product);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public void update(Product product) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.merge(product);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public void remove(Product product) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.delete(product);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public List<Product> list() {
        return HibernateUtil.getSessionFactory().openSession().
                createCriteria(Product.class).list();
    }
}

package dao.product;

import dao.HibernateUtil;
import dao.product.category.Category;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class ProductDaoImpl implements ProductDao {
    @Override
    public Product selectById(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Product product = (Product) session.createCriteria(Product.class)
                .add(Restrictions.eq("idProduct", id)).uniqueResult();
        session.close();
        return product;
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
                createCriteria(Product.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }

    @Override
    public List<Product> listByCategory(Category category) {
        return HibernateUtil.getSessionFactory().openSession().
                createCriteria(Product.class).add(Restrictions.eq("category", category))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }
}

package dao.product;

import dao.HibernateUtil;
import dao.product.category.Category;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.stream.Collectors;

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
        return (List<Product>) HibernateUtil.getSessionFactory().openSession().
                createCriteria(Product.class).list().stream().distinct().collect(Collectors.toList());
    }

    @Override
    public List<Product> listByCategory(Category category) {
        return (List<Product>) HibernateUtil.getSessionFactory().openSession().
                createCriteria(Product.class)
                .list().stream().filter(product -> ((Product) product).getCategory().equals(category))
                .distinct().collect(Collectors.toList());
        // TODO: 02.01.2017 implement by sql request
    }
}

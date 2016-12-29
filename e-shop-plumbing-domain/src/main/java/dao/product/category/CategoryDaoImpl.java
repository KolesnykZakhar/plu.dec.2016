package dao.product.category;


import dao.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    @Override
    public Category selectById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Category category = (Category) session.createCriteria(Category.class)
                .add(Restrictions.eq("idCategory", id)).uniqueResult();
        session.close();
        return category;
    }

    @Override
    public void insert(Category category) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.save(category);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public void update(Category category) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.merge(category);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public void remove(Category category) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.delete(category);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public List<Category> list() {
        return HibernateUtil.getSessionFactory().openSession().
                createCriteria(Category.class).list();
    }
}

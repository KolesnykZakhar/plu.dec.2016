package dao.user;

import dao.HibernateUtil;
import org.hibernate.Session;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public User selectById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return (User) session.byId(User.class).getReference(id);
    }

    @Override
    public List<User> list() {
        return HibernateUtil.getSessionFactory().openSession().
                createCriteria(User.class).list();
    }

    @Override
    public void update(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.merge(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public void remove(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.delete(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public void insert(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}

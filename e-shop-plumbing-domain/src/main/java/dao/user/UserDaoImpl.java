package dao.user;

import dao.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.stream.Collectors;

public class UserDaoImpl implements UserDao {
    @Override
    public User selectById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        User user = (User) session.createCriteria(User.class)
                .add(Restrictions.eq("idUser", id)).uniqueResult();
        session.close();
        return user;
    }

    @Override
    public List<User> list() {
        return (List<User>) HibernateUtil.getSessionFactory().openSession().
                createCriteria(User.class).list().stream().distinct().collect(Collectors.toList());
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

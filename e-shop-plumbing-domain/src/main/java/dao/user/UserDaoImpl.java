package dao.user;

import dao.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public User selectById(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        User user = (User) session.createCriteria(User.class)
                .add(Restrictions.eq("idUser", id)).uniqueResult();
        session.close();
        return user;
    }

    @Override
    public User selectByPhone(String phone) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        User user = (User) session.createCriteria(User.class)
                .add(Restrictions.eq("phone", phone)).uniqueResult();
        session.close();
        return user;
    }

    @Override
    public User selectByEmail(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        User user = (User) session.createCriteria(User.class)
                .add(Restrictions.eq("email", email)).uniqueResult();
        session.close();
        return user;
    }

    @Override
    public User selectByLogin(String login) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        User user = (User) session.createCriteria(User.class)
                .add(Restrictions.eq("loginUser", login)).uniqueResult();
        session.close();
        return user;
    }

    @Override
    public List<User> listOfRegisteredUsers() {
        // TODO: 02.01.2017 maybe in future to be needing to divide the table
        return HibernateUtil.getSessionFactory().openSession().
                createCriteria(User.class).add(Restrictions.isNotNull("loginUser"))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }

    @Override
    public List<User> list() {
        return HibernateUtil.getSessionFactory().openSession().
                createCriteria(User.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
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

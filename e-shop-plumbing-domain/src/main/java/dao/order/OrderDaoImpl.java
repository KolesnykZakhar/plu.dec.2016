package dao.order;

import dao.HibernateUtil;
import dao.user.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class OrderDaoImpl implements OrderDao {

    @Override
    public Order selectById(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Order order = (Order) session.createCriteria(Order.class)
                .add(Restrictions.eq("idOrder", id)).uniqueResult();
        session.close();
        return order;
    }

    @Override
    public List<Order> listOrders() {
        return HibernateUtil.getSessionFactory().openSession().
                createCriteria(Order.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }

    @Override
    public List<Order> listActualOrders() {
        return HibernateUtil.getSessionFactory().openSession().
                createCriteria(Order.class).add(Restrictions.eq("actualOrder", true))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }

    @Override
    public List<Order> listOrdersByUser(User user) {
        return HibernateUtil.getSessionFactory().openSession().
                createCriteria(Order.class).add(Restrictions.eq("user", user))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }

    @Override
    public List<Order> listActualOrdersByUser(User user) {
        return HibernateUtil.getSessionFactory().openSession().
                createCriteria(Order.class).add(Restrictions.eq("user", user)).add(Restrictions.eq("actualOrder", true))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }

    @Override
    public void insert(Order order) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        try {
            session.save(order);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public void remove(Order order) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.delete(order);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public void update(Order order) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.merge(order);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}
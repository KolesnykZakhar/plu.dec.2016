package dao.order;

import dao.HibernateUtil;
import dao.user.User;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class OrderDaoImpl implements OrderDao {

    @Override
    public Order selectById(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Order order = (Order) session.createCriteria(Order.class)
                .add(Restrictions.eq("idProduct", id)).uniqueResult();
        session.close();
        return order;
    }

    @Override
    public List<Order> listOrdersByUser(User user) {
        throw new NullPointerException();
    }

    @Override
    public List<Order> listActualOrdersByUser(User user) {
        throw new NullPointerException();
    }

    @Override
    public void insert(Order order) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.save(order);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public void remove(Order order) {
        throw new NullPointerException();

    }

    @Override
    public void update(Order order) {
        throw new NullPointerException();

    }
}

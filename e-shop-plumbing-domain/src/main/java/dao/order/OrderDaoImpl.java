package dao.order;

import dao.HibernateUtil;
import dao.user.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class OrderDaoImpl implements OrderDao {

    @Override
    public Order selectById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Order order = (Order) session.createCriteria(Order.class)
                .add(Restrictions.eq("idOrder", id)).uniqueResult();
        session.close();
        return order;
    }

    @Override
    public List<Order> listOrders() {
        // TODO: 30.12.2016 doing by sql request
        return (List<Order>) HibernateUtil.getSessionFactory().openSession().
                createCriteria(Order.class).list().stream().distinct().collect(Collectors.toList());
    }

    @Override
    public List<Order> listActualOrders() {
        // TODO: 30.12.2016 doing by sql request
        return (List<Order>) HibernateUtil.getSessionFactory().openSession().
                createCriteria(Order.class).list().stream().filter(order -> ((Order) order).getActualOrder().equals(Boolean.TRUE))
                .distinct().collect(Collectors.toList());
    }

    @Override
    public List<Order> listOrdersByUser(User user) {
        //        return (List<Order>) HibernateUtil.getSessionFactory().openSession().
//                createCriteria(Order.class).add(Restrictions.eq("idUser", user.getIdUser()))
//                .list().stream().distinct().collect(Collectors.toList());
        // TODO: 30.12.2016 doing by sql request
        return (List<Order>) HibernateUtil.getSessionFactory().openSession().
                createCriteria(Order.class).list().stream().distinct()
                .filter(new Predicate() {
                    @Override
                    public boolean test(Object o) {
                        return ((Order) o).getUser().getIdUser().equals(user.getIdUser());
                    }
                }).collect(Collectors.toList());
    }

    @Override
    public List<Order> listActualOrdersByUser(User user) {
        return (List<Order>) HibernateUtil.getSessionFactory().openSession().
                createCriteria(Order.class)
                .list().stream().filter(order -> ((Order) order).getActualOrder().equals(Boolean.TRUE)
                        && ((Order) order).getUser().getIdUser().equals(user.getIdUser()))
                .distinct().collect(Collectors.toList());
        // TODO: 30.12.2016 doing by sql request
//        return (List<Order>) HibernateUtil.getSessionFactory().openSession().
//                createCriteria(Order.class).add(Restrictions.eq("idUser", user.getIdUser()))
//                .list().stream().filter(order -> ((Order) order).getActualOrder().equals(Boolean.TRUE))
//                .distinct().collect(Collectors.toList());
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

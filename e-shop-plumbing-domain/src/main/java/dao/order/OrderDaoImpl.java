package dao.order;

import dao.HibernateUtil;
import dao.TransactionUtil;
import dao.user.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
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
    @SuppressWarnings("unchecked")
    public List<Order> listOrders() {
        return HibernateUtil.getSessionFactory().openSession().
                createCriteria(Order.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Order> listActualOrders() {
        return HibernateUtil.getSessionFactory().openSession().
                createCriteria(Order.class).add(Restrictions.eq("actualOrder", true))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Order> listOrdersByUser(User user) {
        return HibernateUtil.getSessionFactory().openSession().
                createCriteria(Order.class).add(Restrictions.eq("user", user))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Order> listActualOrdersByUser(User user) {
        return HibernateUtil.getSessionFactory().openSession().
                createCriteria(Order.class).add(Restrictions.eq("user", user)).add(Restrictions.eq("actualOrder", true))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }

    @Override
    public void insert(Order order) {
        TransactionUtil.doTransaction(() -> TransactionUtil.session.save(order));
    }

    @Override
    public void remove(Order order) {
        TransactionUtil.doTransaction(() -> TransactionUtil.session.delete(order));
    }

    @Override
    public void update(Order order) {
        TransactionUtil.doTransaction(() -> TransactionUtil.session.merge(order));
    }
}
package dao.user;

import dao.HibernateUtil;
import dao.product.Product;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.engine.spi.PersistenceContext;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class UserDaoImpl implements UserDao {
    @Override
    public User selectById(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return (User) session.byId(User.class).getReference(id);
//        throw new UnsupportedOperationException("not initialized");
    }

    @Override
    public List<User> list() {
//        Session session= HibernateUtil.getSessionFactory().openSession();
//        return (User) session.;
        throw new UnsupportedOperationException("not initialized");
    }

    @Override
    public void update(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(user);
        transaction.commit();
        session.close();
    }

    @Override
    public void remove(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(user);
        transaction.commit();
        session.close();
    }

    @Override
    public void insert(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(user);
        transaction.commit();
        session.close();
    }

//    @Override
//    public Set<Product> wishList(User user) {
//        return user.getWishList()/*.stream().distinct().collect(Collectors.toSet())*/;
//    }
//
//    @Override
//    public Map<Product, Integer> shippingBasket(User user) {
//        return user.getShoppingBasket();
////        throw new UnsupportedOperationException("not initialized");
//    }
}

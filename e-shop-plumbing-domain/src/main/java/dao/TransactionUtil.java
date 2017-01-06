package dao;

import org.hibernate.Session;

public class TransactionUtil {
    public static Session session;

    public static void doTransaction(Transaction transaction) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            transaction.transact();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public interface Transaction {
        void transact();
    }
}

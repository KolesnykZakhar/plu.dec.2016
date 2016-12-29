package dao.product.category;


import dao.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    @Override
    public Category selectById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
//        return (Category) session.createCriteria(Category.class)
//                .add(Restrictions.eq("idCategory", id))
//                .uniqueResult();
        return (Category) session.byId(Category.class).getReference(id);
    }

    @Override
    public void insert(Category category) {
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        Transaction transaction = session.beginTransaction();
//        session.persist(category);
//        transaction.commit();
//        session.close();

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try{
            session.save(category);
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            throw new RuntimeException("Category is not saved to DB");
        }
    }

    @Override
    public void update(Category category) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try{
            session.merge(category);
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            throw new RuntimeException("Category is not updated in DB");
        }

    }

    @Override
    public void remove(Category category) {
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        Transaction transaction = session.beginTransaction();
//        session.delete(category);
//        transaction.commit();
//        session.close();
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try{
            session.delete(category);
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            throw new RuntimeException("Category is not removed from DB");
        }
    }

    @Override
    public List<Category> list() {
        return HibernateUtil.getSessionFactory().openSession().
                createCriteria(Category.class).list();
    }
}

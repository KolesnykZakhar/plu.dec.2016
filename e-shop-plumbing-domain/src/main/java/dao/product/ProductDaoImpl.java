package dao.product;

import dao.HibernateUtil;
import dao.TransactionUtil;
import dao.product.category.Category;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class ProductDaoImpl implements ProductDao {
    @Override
    public Product selectById(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Product product = (Product) session.createCriteria(Product.class)
                .add(Restrictions.eq("idProduct", id)).uniqueResult();
        session.close();
        return product;
    }

    @Override
    public void insert(Product product) {
        TransactionUtil.doTransaction(() -> TransactionUtil.session.save(product));
    }

    @Override
    public void update(Product product) {
        TransactionUtil.doTransaction(() -> TransactionUtil.session.merge(product));
    }

    @Override
    public void remove(Product product) {
        TransactionUtil.doTransaction(() -> TransactionUtil.session.delete(product));
    }

    @Override
    public List<Product> list() {
        return HibernateUtil.getSessionFactory().openSession().
                createCriteria(Product.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }

    @Override
    public List<Product> listByCategory(Category category) {
        return HibernateUtil.getSessionFactory().openSession().
                createCriteria(Product.class).add(Restrictions.eq("category", category))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }
}

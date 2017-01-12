package dao.product.category;


import dao.HibernateUtil;
import dao.TransactionUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    @Override
    public Category selectById(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Category category = (Category) session.createCriteria(Category.class)
                .add(Restrictions.eq("idCategory", id)).uniqueResult();
        session.close();
        return category;
    }

    @Override
    public void insert(Category category) {
        TransactionUtil.doTransaction(() -> TransactionUtil.session.save(category));
    }

    @Override
    public void update(Category category) {
        TransactionUtil.doTransaction(() -> TransactionUtil.session.merge(category));
    }

    @Override
    public void remove(Category category) {
        TransactionUtil.doTransaction(() -> TransactionUtil.session.delete(category));
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Category> list() {
        return HibernateUtil.getSessionFactory().openSession().
                createCriteria(Category.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }
}

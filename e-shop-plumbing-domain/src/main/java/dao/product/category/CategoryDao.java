package dao.product.category;

import java.util.List;

public interface CategoryDao {
    Category selectById(Long id);

    void insert(Category category);

    void update(Category category);

    void remove(Category category);

    List list();
}

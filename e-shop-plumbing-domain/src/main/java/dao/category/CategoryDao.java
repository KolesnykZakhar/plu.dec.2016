package dao.category;

import java.util.List;

public interface CategoryDao {
    Category selectById(Integer id);

    void insert(Category category);

    void update(Category category);

    void remove(Category category);

    List<Category> list();
}

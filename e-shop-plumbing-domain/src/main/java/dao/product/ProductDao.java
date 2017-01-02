package dao.product;

import java.util.List;

public interface ProductDao {

    Product selectById(Integer id);

    void insert(Product product);

    void update(Product product);

    void remove(Product product);

    List<Product> list();
}

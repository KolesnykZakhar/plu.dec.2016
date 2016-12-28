package dao.product;

import java.util.List;
import java.util.Map;

public interface ProductDao {

    Product selectById(Integer id);

    void insert(Product product);

    void update(Product product);

    void remove(Product product);

    List<Product> list();
}

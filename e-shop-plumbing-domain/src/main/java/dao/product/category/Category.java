package dao.product.category;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "categories")
public class Category implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "idCategory")
    private Integer idCategory;

    @Column(name = "nameCategory", unique = true, nullable = false)
    private String nameCategory;


    @Override
    public int hashCode() {
        return idCategory;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Category)) {
            return false;
        }
        Category order = (Category) obj;
        return order.idCategory != null && order.idCategory.equals(idCategory);
    }

    @Override
    public String toString() {
        return nameCategory + ", №: " + idCategory;
    }

    public Category() {
    }

    public Integer getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Integer idCategory) {
        this.idCategory = idCategory;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String name) {
        this.nameCategory = name;
    }
}

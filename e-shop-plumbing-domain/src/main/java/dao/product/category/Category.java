package dao.product.category;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue
    @Column(name = "idCategory")
    private Long idCategory;

    @Column(name = "nameCategory", unique = true, nullable = false)
    private String nameCategory;

    public Category() {
    }

    public Long getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Long idCategory) {
        this.idCategory = idCategory;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String name) {
        this.nameCategory = name;
    }
}

package dao.product;

import dao.product.category.Category;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Map;

@Entity
@Table(name = "product")
public class Product implements Serializable{

    @Id
    @GeneratedValue
    @Column(name = "idProduct")
    private Long idProduct;

    @Column(name = "price")
    private double price;

    @Column(name = "amount")
    private int amount;

    @NotEmpty
    @Column(name = "nameProduct")
    private String nameProduct;

    @ManyToOne
    @JoinTable(name = "category_product",
            joinColumns = {@JoinColumn(name = "idProduct")},
            inverseJoinColumns = {@JoinColumn(name = "idCategory")})
    private Category category;

    @ElementCollection(fetch=FetchType.EAGER)
    @JoinTable(name = "description_product",
            joinColumns = @JoinColumn(name = "idProduct"))
    @MapKeyColumn(name = "type_characteristic")
    private Map<String, String> description;

    @ElementCollection(fetch=FetchType.EAGER)
    @JoinTable(name = "pictures_product",
            joinColumns = @JoinColumn(name = "idProduct"))
    @MapKeyColumn(name = "picture_number")
    private Map<Integer, byte[]> pictures;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Map<Integer, byte[]> getPictures() {
        return pictures;
    }

    public void setPictures(Map<Integer, byte[]> pictures) {
        this.pictures = pictures;
    }

    public Map<String, String> getDescription() {
        return description;
    }

    public void setDescription(Map<String, String> description) {
        this.description = description;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long id) {
        this.idProduct = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String name) {
        this.nameProduct = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}

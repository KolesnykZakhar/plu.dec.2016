package dao.order;

import dao.product.Product;
import dao.user.User;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Map;

@Entity
@Table(name = "orders")
public class Order implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "idOrder")
    private
    Long idOrder;

    @Column(name = "actualOrder")
    private
    Boolean actualOrder;

    @Column(name = "dateOrder")
    private
    Timestamp dateOrder;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "orders_product",
            joinColumns = @JoinColumn(name = "idOrder"))
    @MapKeyJoinColumn(name = "idProduct")
    @Column(name = "amount")
    private Map<Product, Integer> orderMap;

    public Order() {
    }

    public Timestamp getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(Timestamp dateIrder) {
        this.dateOrder = dateIrder;
    }

    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Map<Product, Integer> getOrderMap() {
        return orderMap;
    }

    public void setOrderMap(Map<Product, Integer> orderMap) {
        this.orderMap = orderMap;
    }

    public Boolean getActualOrder() {
        return actualOrder;
    }

    public void setActualOrder(Boolean actualOrder) {
        this.actualOrder = actualOrder;
    }
}

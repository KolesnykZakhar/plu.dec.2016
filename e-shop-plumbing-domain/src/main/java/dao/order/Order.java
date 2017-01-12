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
    @Column(name = "id_order")
    private
    Integer idOrder;

    @Column(name = "actual_order")
    private
    Boolean actualOrder;

    @Column(name = "date_order")
    private
    Timestamp dateOrder;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "orders_product",
            joinColumns = @JoinColumn(name = "id_order"))
    @MapKeyJoinColumn(name = "id_product")
    @Column(name = "amount")
    private Map<Product, Integer> orderMap;

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Order)) {
            return false;
        }
        Order order = (Order) obj;
        return order.idOrder != null && order.idOrder.equals(idOrder);
    }

    @Override
    public String toString() {
        return "Заказ №: " + idOrder + ", ID заказчика: " + user.getIdUser();
    }

    public Order() {
    }

    public Timestamp getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(Timestamp dateIrder) {
        this.dateOrder = dateIrder;
    }

    public Integer getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Integer idOrder) {
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

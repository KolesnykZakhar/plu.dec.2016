package dao.user;


import dao.product.Product;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "idUser")
    private Integer idUser;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "shopping_basket",
            joinColumns = @JoinColumn(name = "idUser"))
    @MapKeyJoinColumn(name = "idProduct")
    @Column(name = "amount")
    private Map<Product, Integer> shoppingBasket;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "wish_list",
            joinColumns = {@JoinColumn(name = "idUser")},
            inverseJoinColumns = {@JoinColumn(name = "idProduct")})
    private Set<Product> wishList;

    @NotEmpty
    @Column(name = "firstName")
    private String firstName;

    @NotEmpty
    @Column(name = "lastName")
    private String lastName;

    @Email
    @NotEmpty
    @Column(name = "email")
    private String email;

    @NotEmpty
    @Column(name = "phone")
    private String phone;

    @NotEmpty
    @Column(name = "address")
    private String address;

    @Column(name = "loginUser")
    private String loginUser;

    @Column(name = "passwordUser")
    private String passwordUser;

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof User)) {
            return false;
        }
        User order = (User) obj;
        return order.idUser != null && order.idUser.equals(idUser);
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + ", ID: " + idUser;
    }

    public User() {
    }

    public Set<Product> getWishList() {
        return wishList;
    }

    public Map<Product, Integer> getShoppingBasket() {
        return shoppingBasket;
    }

    public void setShoppingBasket(Map<Product, Integer> shoppingBasket) {
        this.shoppingBasket = shoppingBasket;
    }

    public void setWishList(Set<Product> wishList) {
        this.wishList = wishList;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer id) {
        this.idUser = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(String login) {
        this.loginUser = login;
    }

    public String getPasswordUser() {
        return passwordUser;
    }

    public void setPasswordUser(String password) {
        this.passwordUser = password;
    }
}

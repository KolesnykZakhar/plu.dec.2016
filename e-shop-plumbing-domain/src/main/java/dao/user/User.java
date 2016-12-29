package dao.user;


import dao.product.Product;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "idUser")
    private Long idUser;

    @ElementCollection
    @CollectionTable(name = "shopping_basket",
            joinColumns = @JoinColumn(name = "idUser"))
    @MapKeyJoinColumn(name = "idProduct")
    @Column(name = "amount")
    private Map<Product, Integer> shoppingBasket;

    @ManyToMany
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



//    @ManyToOne
//    @JoinTable(name = "login_password",
//            joinColumns = {@JoinColumn(name = "idUser")},
//            inverseJoinColumns = {@JoinColumn(name = "idLoginPassword")})
//    private LoginPassword loginPassword;
//
////    @JoinTable(name = "logins_passwords", joinColumns = {@JoinColumn(name = "idUser")},
////            inverseJoinColumns = {@JoinColumn (name = "loginUser")})
    @Column(name = "loginUser"/*, table = "logins_passwords"*/)
    private String loginUser;
//
////    @JoinTable(name = "logins_passwords", joinColumns = {@JoinColumn(name = "idUser")},
////            inverseJoinColumns = {@JoinColumn (name = "passwordUser")})
    @Column(name = "passwordUser"/*, table = "logins_passwords"*/)
    private String passwordUser;

    public User() {
    }

//    public LoginPassword getLoginPassword() {
//        return loginPassword;
//    }

//    public void setLoginPassword(LoginPassword loginPassword) {
//        this.loginPassword = loginPassword;
//    }
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

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long id) {
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

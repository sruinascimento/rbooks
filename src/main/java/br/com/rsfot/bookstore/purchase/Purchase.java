package br.com.rsfot.bookstore.purchase;

import br.com.rsfot.bookstore.address.Address;
import br.com.rsfot.bookstore.country.Country;
import br.com.rsfot.bookstore.state.State;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "purchases")
public class Purchase {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String document;
    private String phone;
    @Embedded
    private Address address;
    @OneToMany(mappedBy = "purchase", cascade = CascadeType.PERSIST)
    private Set<PurchaseItem> products = new HashSet<>();
    private BigDecimal amount;
    @Column(nullable = false, updatable = false, name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Deprecated
    public Purchase() {
    }

    public Purchase(String name,
                    String email,
                    String document,
                    String phone,
                    String address,
                    String complement,
                    String city,
                    State state,
                    Country country,
                    String cep,
                    @Size(min = 1)
                    Set<PurchaseItem> products,
                    BigDecimal amount) {
        Assert.isTrue(!products.isEmpty(), "A purchase must have at least one product");
        this.name = name;
        this.email = email;
        this.document = document;
        this.phone = phone;
        this.address = new Address(address, complement, city, state, country, cep);
        this.products = products;
        this.products.forEach(it -> it.setPurchase(this));
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDocument() {
        return document;
    }

    public String getPhone() {
        return phone;
    }

    public Address getAddress() {
        return address;
    }

    public Collection<PurchaseItem> getProducts() {
        return Collections.unmodifiableCollection(products);
    }

    public void addItem(PurchaseItem item) {
        this.products.add(item);
    }

    public BigDecimal getAmount() {
        return amount;
    }


    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}

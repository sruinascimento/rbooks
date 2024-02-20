package br.com.rsfot.bookstore.address;

import br.com.rsfot.bookstore.country.Country;
import br.com.rsfot.bookstore.state.State;
import jakarta.persistence.*;

@Embeddable
public class Address {
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String complement;
    @Column(nullable = false)
    private String city;
    @ManyToOne
    @JoinColumn(name = "state_id")
    private State state;
    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;
    @Column(nullable = false)
    private String cep;

    @Deprecated
    public Address() {
    }

    public Address(String address,
                   String complement,
                   String city,
                   State state,
                   Country country,
                   String cep) {
        this.address = address;
        this.complement = complement;
        this.city = city;
        this.state = state;
        this.country = country;
        this.cep = cep;
    }

    public String getAddress() {
        return address;
    }

    public String getComplement() {
        return complement;
    }

    public String getCity() {
        return city;
    }

    public State getState() {
        return state;
    }

    public Country getCountry() {
        return country;
    }

    public String getCep() {
        return cep;
    }

    @Override
    public String toString() {
        return "Address{" +
                "address='" + address + '\'' +
                ", complement='" + complement + '\'' +
                ", city='" + city + '\'' +
                ", state=" + state +
                ", country=" + country +
                ", cep='" + cep + '\'' +
                '}';
    }
}

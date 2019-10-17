package ru.otus.kasymbekovPN.HW10.api.model;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "tDBUser")
public class DBUser {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "dbUser")
    private List<Phone> phones;

    public DBUser() {
    }

    public DBUser(long id, String name, int age, Address address, List<Phone> phones) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.phones = phones;

        for (Phone phone : this.phones) {
            phone.setDbUser(this);
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    @Override
    public String toString() {
        return "DBUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", address=" + address +
                ", phones=" + phones +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DBUser dbUser = (DBUser) o;

        Collections.sort(phones);
        Collections.sort(dbUser.phones);

        boolean phonesEq = true;
        if (phones.size() != dbUser.phones.size()){
            phonesEq = false;
        }
        else {
            for (int i = 0; i < phones.size(); i++){
                phonesEq &= phones.get(i).equals(dbUser.phones.get(i));
            }
        }

        return id == dbUser.id &&
                age == dbUser.age &&
                Objects.equals(name, dbUser.name) &&
                Objects.equals(address, dbUser.address) &&
                phonesEq;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, address, phones);
    }
}
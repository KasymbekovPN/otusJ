package ru.otus.kasymbekovPN.HW10.api.model;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

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
    @JoinColumn(name = "addressDataSet_id")
    private AddressDataSet addressDataSet;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "dbUser")
    private List<PhoneDataSet> phones;

    public DBUser() {
    }

    public DBUser(long id, String name, int age, AddressDataSet addressDataSet, List<PhoneDataSet> phones) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.addressDataSet = addressDataSet;
        this.phones = phones;

        for (PhoneDataSet phone : this.phones) {
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

    public AddressDataSet getAddressDataSet() {
        return addressDataSet;
    }

    public void setAddressDataSet(AddressDataSet addressDataSet) {
        this.addressDataSet = addressDataSet;
    }

    public List<PhoneDataSet> getPhones() {
        return phones;
    }

    public void setPhones(List<PhoneDataSet> phones) {
        this.phones = phones;
    }

    @Override
    public String toString() {
        return "DBUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", addressDataSet=" + addressDataSet +
                ", phones=" + phones +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DBUser dbUser = (DBUser) o;

        //<
        boolean phoneEq = true;

        return id == dbUser.id &&
                age == dbUser.age &&
                Objects.equals(name, dbUser.name) &&
                Objects.equals(addressDataSet, dbUser.addressDataSet) &&
                Objects.equals(phones, dbUser.phones);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, addressDataSet, phones);
    }
}
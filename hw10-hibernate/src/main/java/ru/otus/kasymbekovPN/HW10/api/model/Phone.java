package ru.otus.kasymbekovPN.HW10.api.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tPhones")
public class Phone implements Comparable<Phone> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private long id;

    @Column(name = "phone", nullable = false)
    private String phone;

    @ManyToOne
    @JoinColumn(name = "dBUser_id", nullable = false)
    private DBUser dbUser;

    public Phone() {
    }

    public Phone(long id, String phone) {
        this.id = id;
        this.phone = phone;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public DBUser getDbUser() {
        return dbUser;
    }

    public void setDbUser(DBUser dbUser) {
        this.dbUser = dbUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phone that = (Phone) o;
        return id == that.id &&
                Objects.equals(phone, that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, phone, dbUser);
    }

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                '}';
    }

    @Override
    public int compareTo(Phone o) {
        return phone.compareTo(o.phone);
    }
}

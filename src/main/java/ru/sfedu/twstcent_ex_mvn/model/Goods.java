package ru.sfedu.twstcent_ex_mvn.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "goods")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Goods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private double price;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Goods goods = (Goods) o;
        return Objects.equals(id, goods.id) && Objects.equals(name, goods.name) && Objects.equals(price, goods.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price);
    }
}

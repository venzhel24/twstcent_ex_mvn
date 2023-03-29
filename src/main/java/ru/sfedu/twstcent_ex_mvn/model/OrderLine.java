package ru.sfedu.twstcent_ex_mvn.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "order_lines")
@Getter
@Setter
@NoArgsConstructor
public class OrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goods_id")
    private Goods goods;

    @Column(name = "count")
    private int count;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderLine orderLine = (OrderLine) o;
        return count == orderLine.count && Objects.equals(id, orderLine.id) && Objects.equals(order, orderLine.order) && Objects.equals(goods, orderLine.goods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, order, goods, count);
    }
}

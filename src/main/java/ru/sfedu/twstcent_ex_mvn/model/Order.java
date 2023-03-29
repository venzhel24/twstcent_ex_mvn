package ru.sfedu.twstcent_ex_mvn.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "client")
    private String client;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderLine> orderLines = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(client, order.client) && Objects.equals(date, order.date) && Objects.equals(address, order.address) && Objects.equals(orderLines, order.orderLines);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, client, date, address, orderLines);
    }
}

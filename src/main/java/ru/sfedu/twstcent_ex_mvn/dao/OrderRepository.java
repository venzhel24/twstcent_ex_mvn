package ru.sfedu.twstcent_ex_mvn.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sfedu.twstcent_ex_mvn.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}

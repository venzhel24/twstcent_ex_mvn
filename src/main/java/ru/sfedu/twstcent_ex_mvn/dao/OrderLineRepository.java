package ru.sfedu.twstcent_ex_mvn.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sfedu.twstcent_ex_mvn.model.OrderLine;

@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {
    Boolean deleteByOrderId(long orderId);
}

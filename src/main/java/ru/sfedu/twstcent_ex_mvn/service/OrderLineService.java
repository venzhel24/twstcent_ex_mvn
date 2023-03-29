package ru.sfedu.twstcent_ex_mvn.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sfedu.twstcent_ex_mvn.model.OrderLine;
import ru.sfedu.twstcent_ex_mvn.dao.OrderLineRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OrderLineService {
    @Autowired
    private OrderLineRepository orderLineRepository;

    public List<OrderLine> getAllOrderLines() {
        return orderLineRepository.findAll();
    }

    public Optional<OrderLine> getOrderLineById(Long id) {
        return orderLineRepository.findById(id);
    }

    public OrderLine createOrderLine(OrderLine orderLine) {
        return orderLineRepository.save(orderLine);
    }

    public void deleteOrderLine(OrderLine orderLine) {
        orderLineRepository.delete(orderLine);
    }

    public OrderLine updateOrderLine(Long id, OrderLine updatedOrderLine) {
        Optional<OrderLine> existingOrderLine = orderLineRepository.findById(id);

        if (existingOrderLine.isPresent()) {
            OrderLine orderLine = existingOrderLine.get();
            orderLine.setGoods(updatedOrderLine.getGoods());
            orderLine.setCount(updatedOrderLine.getCount());
            return orderLineRepository.save(orderLine);
        } else {
            return null;
        }
    }
}
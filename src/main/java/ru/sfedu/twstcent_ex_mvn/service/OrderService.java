package ru.sfedu.twstcent_ex_mvn.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sfedu.twstcent_ex_mvn.model.Order;
import ru.sfedu.twstcent_ex_mvn.dao.OrderRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public Order createOrder(Order order) {
        order.setDate(LocalDate.now());
        return orderRepository.save(order);
    }

    public void deleteOrder(Order order) {
        orderRepository.delete(order);
    }

    public Order updateOrder(Long id, Order order) {
        Optional<Order> optionalOrders = orderRepository.findById(id);
        if (optionalOrders.isPresent()) {
            Order existingOrder = optionalOrders.get();
            existingOrder.setClient(order.getClient());
            existingOrder.setAddress(order.getAddress());
            return orderRepository.save(existingOrder);
        } else {
            return null;
        }
    }
}


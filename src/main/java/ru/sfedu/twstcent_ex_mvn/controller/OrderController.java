package ru.sfedu.twstcent_ex_mvn.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sfedu.twstcent_ex_mvn.model.Goods;
import ru.sfedu.twstcent_ex_mvn.model.Order;
import ru.sfedu.twstcent_ex_mvn.service.OrderService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable(value = "id") Long id) {
        Optional<Order> order = orderService.getOrderById(id);

        if (order.isPresent()) {
            return ResponseEntity.ok().body(order.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable(value = "id") Long id) {
        Optional<Order> order = orderService.getOrderById(id);

        if (order.isPresent()) {
            orderService.deleteOrder(order.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable Long id, @RequestBody Order order) {
        return orderService.updateOrder(id, order);
    }
}

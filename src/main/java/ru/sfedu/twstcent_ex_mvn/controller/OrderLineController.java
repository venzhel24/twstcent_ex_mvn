package ru.sfedu.twstcent_ex_mvn.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sfedu.twstcent_ex_mvn.model.Order;
import ru.sfedu.twstcent_ex_mvn.model.OrderLine;
import ru.sfedu.twstcent_ex_mvn.service.OrderLineService;
import ru.sfedu.twstcent_ex_mvn.service.OrderService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orderline")
public class OrderLineController {
    @Autowired
    private OrderLineService orderLineService;

    @GetMapping
    public List<OrderLine> getAllOrders() {
        return orderLineService.getAllOrderLines();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderLine> getOrderLineById(@PathVariable(value = "id") Long id) {
        Optional<OrderLine> orderLine = orderLineService.getOrderLineById(id);

        if (orderLine.isPresent()) {
            return ResponseEntity.ok().body(orderLine.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public OrderLine createOrderLine(@RequestBody OrderLine orderLine) {
        return orderLineService.createOrderLine(orderLine);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderLine(@PathVariable(value = "id") Long id) {
        Optional<OrderLine> orderLine = orderLineService.getOrderLineById(id);

        if (orderLine.isPresent()) {
            orderLineService.deleteOrderLine(orderLine.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public OrderLine updateOrderLine(@PathVariable Long id, @RequestBody OrderLine orderLine) {
        return orderLineService.updateOrderLine(id, orderLine);
    }
}

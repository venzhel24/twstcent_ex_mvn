package ru.sfedu.twstcent_ex_mvn.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.sfedu.twstcent_ex_mvn.model.Goods;
import ru.sfedu.twstcent_ex_mvn.service.GoodsServiceNew;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/good")
public class GoodsController {
    @Autowired
    private GoodsServiceNew goodsService;

    @GetMapping
    public List<Goods> getAllGoods() {
        return goodsService.getAllGoods();
    }

    @GetMapping("/{id}")
    public Optional<Goods> getGoodsById(@PathVariable Long id) {
        return goodsService.getGoodsById(id);
    }

    @PostMapping
    public Goods createGoods(@RequestBody Goods goods) {
        return goodsService.createGoods(goods);
    }

    @PutMapping("/{id}")
    public Goods updateGoods(@PathVariable Long id, @RequestBody Goods goods) {
        return goodsService.updateGoods(id, goods);
    }

    @DeleteMapping("/{id}")
    public void deleteGoods(@PathVariable Long id) {
        goodsService.deleteGoods(id);
    }
}

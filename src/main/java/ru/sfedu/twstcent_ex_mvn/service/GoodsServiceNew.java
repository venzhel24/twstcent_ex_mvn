package ru.sfedu.twstcent_ex_mvn.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sfedu.twstcent_ex_mvn.model.Goods;
import ru.sfedu.twstcent_ex_mvn.dao.GoodsRepository;

import java.util.List;
import java.util.Optional;

@Service
public class GoodsServiceNew {
    @Autowired
    private GoodsRepository goodsRepository;

    public List<Goods> getAllGoods() {
        return goodsRepository.findAll();
    }

    public Optional<Goods> getGoodsById(Long id) {
        return goodsRepository.findById(id);
    }

    public Goods createGoods(Goods goods) {
        return goodsRepository.save(goods);
    }

    public Goods updateGoods(Long id, Goods goods) {
        Optional<Goods> optionalGoods = goodsRepository.findById(id);
        if (optionalGoods.isPresent()) {
            Goods existingGoods = optionalGoods.get();
            existingGoods.setName(goods.getName());
            existingGoods.setPrice(goods.getPrice());
            return goodsRepository.save(existingGoods);
        } else {
            return null;
        }
    }

    public void deleteGoods(Long id) {
        goodsRepository.deleteById(id);
    }
}

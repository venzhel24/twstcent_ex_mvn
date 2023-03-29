package ru.sfedu.twstcent_ex_mvn.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import ru.sfedu.twstcent_ex_mvn.model.Goods;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class GoodsRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private GoodsRepository goodsRepository;

    @Test
    public void testCreateGoods() {
        Goods goods = new Goods(null, "Product 1", 10.0);
        Goods savedGoods = goodsRepository.save(goods);
        assertNotNull(savedGoods.getId());
        assertEquals(savedGoods, goods);
    }

    @Test
    public void testReadGoods() {
        Goods goods = new Goods(null, "Product 1", 10.0);

        entityManager.persist(goods);
        entityManager.flush();

        Optional<Goods> foundGoods = goodsRepository.findById(goods.getId());
        assertTrue(foundGoods.isPresent());
        assertEquals(foundGoods.get(), goods);
    }

    @Test
    public void testUpdateGoods() {
        Goods goods = new Goods(null, "Product 1", 10.0);
        entityManager.persist(goods);
        entityManager.flush();

        goods.setName("Updated Product");
        goods.setPrice(20.0);
        Goods updatedGoods = goodsRepository.save(goods);

        assertEquals(updatedGoods.getName(),"Updated Product");
        assertEquals(updatedGoods.getPrice(), 20.0);
    }

    @Test
    public void testDeleteGoods() {
        Goods goods = new Goods(null,"Product 1", 10.0);

        entityManager.persist(goods);
        entityManager.flush();

        goodsRepository.deleteById(goods.getId());

        Optional<Goods> foundGoods = goodsRepository.findById(goods.getId());
        assertFalse(foundGoods.isPresent());
    }
}
package ru.sfedu.twstcent_ex_mvn.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import ru.sfedu.twstcent_ex_mvn.dao.GoodsRepository;
import ru.sfedu.twstcent_ex_mvn.model.Goods;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class GoodsServiceNewTest {

    @Mock
    private GoodsRepository goodsRepository;

    @InjectMocks
    private GoodsServiceNew goodsService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllGoods() {
        List<Goods> expectedGoodsList = Arrays.asList(
                new Goods(1L, "Product 1", 10.0),
                new Goods(2L, "Product 2", 20.0),
                new Goods(3L, "Product 3", 30.0)
        );

        Mockito.when(goodsRepository.findAll()).thenReturn(expectedGoodsList);

        List<Goods> actualGoodsList = goodsService.getAllGoods();

        Assert.assertEquals(expectedGoodsList.size(), actualGoodsList.size());
        Assert.assertEquals(expectedGoodsList, actualGoodsList);
    }

    @Test
    public void testGetGoodsById() {
        Long id = 1L;
        Optional<Goods> expectedGoods = Optional.of(new Goods(id, "Product 1", 10.0));

        Mockito.when(goodsRepository.findById(id)).thenReturn(expectedGoods);

        Optional<Goods> actualGoods = goodsService.getGoodsById(id);

        Assert.assertTrue(actualGoods.isPresent());
        Assert.assertEquals(expectedGoods.get(), actualGoods.get());
    }

    @Test
    public void testCreateGoods() {
        Goods expectedGoods = new Goods(1L, "Product", 10.0);

        Mockito.when(goodsRepository.save(Mockito.any(Goods.class))).thenReturn(expectedGoods);

        Goods actualGoods = goodsService.createGoods(expectedGoods);

        Assert.assertNotNull(actualGoods);
        Assert.assertEquals(expectedGoods, actualGoods);
    }

    @Test
    public void testUpdateGoods() {
        Long id = 1L;
        Goods existingGoods = new Goods(id, "Product 1", 10.0);
        Goods updatedGoods = new Goods(id, "Updated Product", 20.0);

        Mockito.when(goodsRepository.findById(id)).thenReturn(Optional.of(existingGoods));
        Mockito.when(goodsRepository.save(existingGoods)).thenReturn(updatedGoods);

        Goods actualGoods = goodsService.updateGoods(id, updatedGoods);

        Assert.assertNotNull(actualGoods);
        Assert.assertEquals(updatedGoods, actualGoods);
    }

    @Test
    public void testDeleteGoods() {
        Long id = 1L;

        Mockito.doNothing().when(goodsRepository).deleteById(id);

        goodsService.deleteGoods(id);

        Mockito.verify(goodsRepository, Mockito.times(1)).deleteById(id);
    }

}

package ru.sfedu.twstcent_ex_mvn.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.sfedu.twstcent_ex_mvn.model.Goods;
import ru.sfedu.twstcent_ex_mvn.service.GoodsServiceNew;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@WebMvcTest(GoodsController.class)
public class GoodsControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GoodsServiceNew goodsService;

    @Test
    public void testGetAllGoods() throws Exception {
        List<Goods> goodsList = Arrays.asList(
                new Goods(1L, "Product 1", 10.00),
                new Goods(2L, "Product 2", 20.00),
                new Goods(3L, "Product 3", 30.00)
        );
        given(goodsService.getAllGoods()).willReturn(goodsList);

        mockMvc.perform(get("/api/good"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'id':1,'name':'Product 1','price':10.0},{'id':2,'name':'Product 2','price':20.0},{'id':3,'name':'Product 3','price':30.0}]"));
    }

    @Test
    public void testGetGoodsById() throws Exception {
        Long goodsId = 1L;
        Goods goods = new Goods(goodsId, "Product 1", 10.00);
        given(goodsService.getGoodsById(goodsId)).willReturn(Optional.of(goods));

        mockMvc.perform(get("/api/good/{id}", goodsId))
                .andExpect(status().isOk())
                .andExpect(content().json("{'id':1,'name':'Product 1','price':10.0}"));
    }

    @Test
    public void testCreateGoods() throws Exception {
        Goods goods = new Goods(null, "Product 1", 10.00);
        given(goodsService.createGoods(goods)).willReturn(new Goods(1L, "Product 1", 10.00));

        mockMvc.perform(post("/api/good")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Product 1\",\"price\":10.0}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{'id':1,'name':'Product 1','price':10.0}"));
    }

    @Test
    public void testUpdateGoods() throws Exception {
        Goods goods = new Goods(null, "Test Goods", 10.0);
        ObjectMapper objectMapper = new ObjectMapper();

        String json = objectMapper.writeValueAsString(goods);
        MvcResult createResult = mockMvc.perform(post("/api/good")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andReturn();

        Goods createdGoods = objectMapper.readValue(createResult.getResponse().getContentAsString(), Goods.class);

        createdGoods.setName("Updated Goods");
        createdGoods.setPrice(11.0);

        json = objectMapper.writeValueAsString(createdGoods);
        MvcResult updateResult = mockMvc.perform(put("/api/good/" + createdGoods.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andReturn();

        Goods updatedGoods = objectMapper.readValue(updateResult.getResponse().getContentAsString(), Goods.class);

        assertNotNull(updatedGoods);
        assertEquals(createdGoods.getId(), updatedGoods.getId());
        assertEquals(createdGoods.getName(), updatedGoods.getName());
        assertEquals(createdGoods.getPrice(), updatedGoods.getPrice(), 0.001);
    }

    @Test
    public void testDeleteGoods() throws Exception {
        mockMvc.perform(delete("/api/good/{id}", 1L))
                .andExpect(status().isOk());

        verify(goodsService, times(1)).deleteGoods(1L);
    }
}
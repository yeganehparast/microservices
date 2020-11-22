package com.microservice.assignment.starbux.controller;

import com.microservice.assignment.starbux.TestUtils;
import com.microservice.assignment.starbux.dto.CartDTO;
import com.microservice.assignment.starbux.dto.OrderItemDTO;
import com.microservice.assignment.starbux.dto.ProductDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.List;

import static com.microservice.assignment.starbux.TestUtils.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Tests Endpoints
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ControllersTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    @DisplayName("Check WebApplicationContext to be initialized correctly and carController is instantiated")
    public void checkContext() {
        ServletContext servletContext = wac.getServletContext();

        assertNotNull(servletContext);
        assertTrue(servletContext instanceof MockServletContext);
        assertNotNull(wac.getBean("adminController"));
        assertNotNull(wac.getBean("cartController"));
    }

    public void createProducts() throws Exception {
        List<String> productDTOJsonList = getProductDTOJsonList();
        for (String s : productDTOJsonList) {
            MvcResult mvcResult = mockMvc.perform(post("/v1/admin/product/create")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(s)
                    .accept(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isCreated())
                    .andReturn();
            ProductDTO productDTO = TestUtils.getProductDTO(mvcResult.getResponse().getContentAsString());
            assertNotNull(productDTO);
            assertEquals(productDTO, TestUtils.getProductDTO(s));
        }
    }

    @Test
    @DisplayName("Testing creating an order")
    public void testCreateAnOrder() throws Exception {
        CartDTO client1 = CartDTO.builder().
                clientName("Client1").
                amount(getPriceFromDouble(0)).
                discount(getPriceFromDouble(0)).
                total(getPriceFromDouble(0)).
                items(new ArrayList<>()).build();
        client1.getItems().add(OrderItemDTO.builder().productDTO(getProductDTOFromEndpoint("espresso")).build());
        client1.getItems().add(OrderItemDTO.builder().productDTO(getProductDTOFromEndpoint("tea")).build());
        client1.getItems().add(OrderItemDTO.builder().productDTO(getProductDTOFromEndpoint("lemon")).build());

        MvcResult mvcResult = mockMvc.perform(post("/v1/cart/order")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getCartDTOJson(client1))
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();
        CartDTO cart = getCart(mvcResult.getResponse().getContentAsString());
        assertNotNull(cart);
        assertNotNull(cart.getItems());
        assertNotNull(cart.getAmount());
        assertNotNull(cart.getItems());
        assertNotNull(cart.getDiscount());
        assertEquals(client1.getItems(), cart.getItems());
    }

    private ProductDTO getProductDTOFromEndpoint(String name) throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/v1/admin/product/get/{name}", name)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        return TestUtils.getProductDTO(mvcResult.getResponse().getContentAsString());
    }
}

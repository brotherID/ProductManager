package com.management.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.management.product.dtos.ProductDto;
import com.management.product.enums.InventoryStatus;
import com.management.product.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductControllerImplTest.class)
public class ProductControllerImplTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    public void testAddProduct() throws Exception {

        ProductDto productDto = new ProductDto();
        productDto.setCodeProduct("CodeProductTest1");
        productDto.setNameProduct("nameProductTest1");
        productDto.setDescriptionProduct("DescriptionProductTest1");
        productDto.setImageProduct("ImageProductTest1");
        productDto.setCategoryProduct("CategoryProductTest1");
        productDto.setPriceProduct(456);
        productDto.setQuantityProduct(19);
        productDto.setInternalReferenceProduct("InternalReferenceProductTest1");
        productDto.setShellIdProduct(4444);
        productDto.setInventoryStatus(InventoryStatus.INSTOCK);
        productDto.setRatingProduct(222);


        ObjectMapper objectMapper = new ObjectMapper();
        String productJson = objectMapper.writeValueAsString(productDto);

        when(productService.createProduct(any(ProductDto.class)))
                .thenReturn(productDto);

        mockMvc.perform(post("api/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(productJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nameProduct").value("nameProductTest1"))
                .andExpect(jsonPath("$.descriptionProduct").value("nameProductTest1"))
                .andExpect(jsonPath("$.categoryProduct").value("CategoryProductTest1"))
                .andExpect(jsonPath("$.priceProduct").value(456))
                .andExpect(jsonPath("$.quantityProduct").value(19))
                .andExpect(jsonPath("$.internalReferenceProduct").value("InternalReferenceProductTest1"))
                .andExpect(jsonPath("$.shellIdProduct").value(4444))
                .andExpect(jsonPath("$.inventoryStatus").value(InventoryStatus.INSTOCK))
                .andExpect(jsonPath("$.ratingProduct").value(222));


    }
}

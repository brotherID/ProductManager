package com.management.product.service;

import com.management.product.dtos.ProductDto;
import com.management.product.dtos.ShoppedProductDto;
import com.management.product.entities.Cart;
import com.management.product.entities.Product;
import com.management.product.entities.ShoppedProduct;
import com.management.product.mapper.CartMapper;
import com.management.product.mapper.ProductMapper;
import com.management.product.mapper.ShoppedProductMapper;
import com.management.product.repository.CartRepository;
import com.management.product.repository.ProductRepository;
import com.management.product.repository.ShoppedProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.zalando.problem.Problem;


import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static com.management.product.api.ProductControllerImpl.PRODUCT_NOT_FOUND;
import static org.zalando.problem.Status.CONFLICT;
import static org.zalando.problem.Status.NOT_FOUND;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartServiceImpl implements  CartService{

    public static final String MESSAGE_DETAIL_PRODUCT_ID_NOT_EXIST = "Product with idProduct %s does not exist !";
    public static final String CART_NOT_FOUNDED = "CART NOT FOUNDED";
    public static final String THE_CART_DOES_NOT_EXIST = "The cart does not exist ";
    private final CartRepository cartRepository;
    private final ShoppedProductRepository shoppedProductRepository;
    private final ProductRepository productRepository;
    private final CartMapper cartMapper;
    private final ShoppedProductMapper shoppedProductMapper;
    private final ProductMapper productMapper;



    @Override
    public ShoppedProductDto addProductToCart(Long idProduct) {
        log.info("addProductToCart()[idProduct :{}] ...", idProduct);
        Product productToAdd = productRepository.findById(idProduct).orElseThrow(() -> {
            throw Problem.builder()
                    .withTitle(PRODUCT_NOT_FOUND)
                    .withStatus(NOT_FOUND)
                    .withDetail(String.format(MESSAGE_DETAIL_PRODUCT_ID_NOT_EXIST, idProduct))
                    .build();
        });
        Authentication currentAuth = SecurityContextHolder.getContext().getAuthentication();
        Cart cartOfAuthenticatedUser = getCartOfAuthenticatedUser(currentAuth.getName());
        log.info("addProductToCart()[idProduct :{}] Done", idProduct);
        return addProductToCard(cartOfAuthenticatedUser.getIdCart(),productToAdd.getIdProduct());
    }

    @Override
    public List<ProductDto> getProductsOfCartUser() {
        log.info("getProductsOfCartUser() ...");
        Authentication currentAuth = SecurityContextHolder.getContext().getAuthentication();
        List<ShoppedProduct> shoppedProducts=cartRepository.findByEmail(currentAuth.getName())
                .map(Cart::getIdCart)
                .map(shoppedProductRepository::findByIdCart)
                .orElse(List.of());
        List<ProductDto> productDtoList = shoppedProducts.stream()
                .map(ShoppedProduct::getIdProduct)
                .map(productRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(productMapper::fromEntity)
                .toList();
        log.info("getProductsOfCartUser() Done");
        return productDtoList;
    }


    public Cart getCartOfAuthenticatedUser(String email){
        return  cartRepository.findByEmail(email)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setEmail(email);
                    newCart.setExpirationDate(LocalDateTime.now().plusMinutes(15));
                    return cartRepository.save(newCart);
                });
    }


    public ShoppedProductDto addProductToCard(Long idCart, Long idProduct){
        ShoppedProduct shoppedProduct = new ShoppedProduct();
        shoppedProduct.setIdCart(idCart);
        shoppedProduct.setIdProduct(idProduct);
        return shoppedProductMapper.toShoppedProductDto(shoppedProductRepository.save(shoppedProduct));
    }
}

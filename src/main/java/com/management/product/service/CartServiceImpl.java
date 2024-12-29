package com.management.product.service;

import com.management.product.dtos.cart.ShoppedProductResponse;
import com.management.product.dtos.product.ProductResponse;
import com.management.product.entities.cart.Cart;
import com.management.product.entities.product.Product;
import com.management.product.entities.product.ShoppedProduct;
import com.management.product.enums.InventoryStatus;
import com.management.product.mapper.product.ProductMapper;
import com.management.product.mapper.cart.ShoppedProductMapper;
import com.management.product.repository.cart.user.CartRepository;
import com.management.product.repository.product.ProductRepository;
import com.management.product.repository.cart.product.ShoppedProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.zalando.problem.Problem;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.management.product.web.product.ProductControllerImpl.PRODUCT_NOT_FOUND;
import static org.zalando.problem.Status.CONFLICT;
import static org.zalando.problem.Status.NOT_FOUND;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartServiceImpl implements CartService {
    public static final String MESSAGE_DETAIL_PRODUCT_ID_NOT_EXIST = "Product with idProduct %s does not exist !";
    public static final String TITLE_LOW_QUANTITY = "low quantity";
    public static final String DETAIL_QUANTITY_LESS_QUANTITY_REQUESTED = "the quantity available %d is less than the quantity requested %d";
    private final CartRepository cartRepository;
    private final ShoppedProductRepository shoppedProductRepository;
    private final ProductRepository productRepository;
    private final ShoppedProductMapper shoppedProductMapper;
    private final ProductMapper productMapper;
    @Value("${product.inventory.low.stock}")
    private Integer productInventoryNumber;
    @Value("${cart.expiration.date}")
    private long cartExpirationDate;


    @Override
    public ShoppedProductResponse addProductToCart(Long idProduct, Integer quantityRequested) {
        log.info("addProductToCart()[idProduct :{}] ...", idProduct);
        Product productToAdd = productRepository.findById(idProduct).orElseThrow(() -> {
            throw Problem.builder()
                    .withTitle(PRODUCT_NOT_FOUND)
                    .withStatus(NOT_FOUND)
                    .withDetail(String.format(MESSAGE_DETAIL_PRODUCT_ID_NOT_EXIST, idProduct))
                    .build();
        });
        if (productToAdd.getQuantityProduct() >= quantityRequested) {
            Integer quantityProductToUpdate = productToAdd.getQuantityProduct() - quantityRequested;
            productToAdd.setQuantityProduct(quantityProductToUpdate);
            productToAdd.setInventoryStatus(calculateInventory(quantityProductToUpdate));
            productRepository.save(productToAdd);
        } else {
            throw Problem.builder()
                    .withTitle(TITLE_LOW_QUANTITY)
                    .withStatus(CONFLICT)
                    .withDetail(String.format(DETAIL_QUANTITY_LESS_QUANTITY_REQUESTED, productToAdd.getQuantityProduct(), quantityRequested))
                    .build();
        }

        Authentication currentAuth = SecurityContextHolder.getContext().getAuthentication();
        Cart cartOfAuthenticatedUser = getCartOfAuthenticatedUser(currentAuth.getName());
        log.info("addProductToCart()[idProduct :{}] Done", idProduct);
        return addProductToCard(cartOfAuthenticatedUser.getIdCart(), productToAdd.getIdProduct());
    }

    public InventoryStatus calculateInventory(Integer quantityProductToUpdate) {
        if (quantityProductToUpdate == 0) {
            return InventoryStatus.OUTOFSTOCK;
        } else if (quantityProductToUpdate > productInventoryNumber) {
            return InventoryStatus.INSTOCK;
        } else {
            return InventoryStatus.LOWSTOCK;
        }
    }

    @Override
    public List<ProductResponse> getProductsOfCartUser() {
        log.info("getProductsOfCartUser() ...");
        Authentication currentAuth = SecurityContextHolder.getContext().getAuthentication();
        List<ShoppedProduct> shoppedProducts = cartRepository.findByEmail(currentAuth.getName())
                .map(Cart::getIdCart)
                .map(shoppedProductRepository::findByIdCart)
                .orElse(List.of());
        List<ProductResponse> productDtoList = shoppedProducts.stream()
                .map(ShoppedProduct::getIdProduct)
                .map(productRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(productMapper::toProductResponse)
                .toList();
        log.info("getProductsOfCartUser() Done");
        return productDtoList;
    }


    public Cart getCartOfAuthenticatedUser(String email) {
        return cartRepository.findByEmail(email)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setEmail(email);
                    newCart.setExpirationDate(LocalDateTime.now().plusMinutes(cartExpirationDate));
                    return cartRepository.save(newCart);
                });
    }


    public ShoppedProductResponse addProductToCard(Long idCart, Long idProduct) {
        ShoppedProduct shoppedProduct = new ShoppedProduct();
        shoppedProduct.setIdCart(idCart);
        shoppedProduct.setIdProduct(idProduct);
        return shoppedProductMapper.toShoppedProductResponse(shoppedProductRepository.save(shoppedProduct));
    }
}

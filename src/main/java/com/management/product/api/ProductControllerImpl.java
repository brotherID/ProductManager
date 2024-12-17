package com.management.product.api;

import com.management.product.dtos.ProductDto;
import com.management.product.entities.Wishlist;
import com.management.product.service.CartService;
import com.management.product.service.ProductService;
import com.management.product.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.zalando.problem.Problem;

import java.net.URI;
import java.util.List;

import static org.zalando.problem.Status.NOT_FOUND;
import static org.zalando.problem.Status.UNAUTHORIZED;


@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
public class ProductControllerImpl implements ProductController {
    public static final String PRODUCT_NOT_FOUND = "PRODUCT NOT FOUND";
    public static final String UNAUTHORIZED_ACCESS = "Unauthorized Access";
    public static final String THE_USER_IS_NOT_AUTHORIZED = "The user is not authorized.";
    private final ProductService productService;
    private final CartService cartService;
    private final WishlistService wishlistService;




    @Override
    public ResponseEntity<?> addProduct(ProductDto productDto) {


            if(productService.isAdmin()){
                ProductDto product = productService.createProduct(productDto);
                URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .build()
                        .toUri();
                return ResponseEntity
                        .created(location)
                        .body(product);
            }else{

                throw Problem.builder()
                        .withTitle(UNAUTHORIZED_ACCESS)
                        .withStatus(UNAUTHORIZED)
                        .withDetail(THE_USER_IS_NOT_AUTHORIZED)
                        .build();
            }

    }


    @Override
    public ResponseEntity<?> getProducts() {
        try {
            return ResponseEntity.ok(productService.getAllProduct());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("error in getting products : " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> getProductById(Long idProduct) {
        return productService.getProduct(idProduct)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> Problem.builder()
                        .withTitle(PRODUCT_NOT_FOUND)
                        .withStatus(NOT_FOUND)
                        .withDetail(String.format("The product with id %s not founded.", idProduct))
                        .build());
    }


    @Override
    public ResponseEntity<?> updateProductById(Long idProduct, ProductDto productDto) {

            if(productService.isAdmin()){
                return ResponseEntity.ok(productService.updateProductById(idProduct, productDto));
            }else{
                throw Problem.builder()
                        .withTitle(UNAUTHORIZED_ACCESS)
                        .withStatus(UNAUTHORIZED)
                        .withDetail(THE_USER_IS_NOT_AUTHORIZED)
                        .build();
            }

    }

    @Override
    public ResponseEntity<?> deleteProductById(Long  idProduct) {

            if(productService.isAdmin()){
                return ResponseEntity.ok(productService.removeProductById(idProduct));
            }else {
                throw Problem.builder()
                        .withTitle(UNAUTHORIZED_ACCESS)
                        .withStatus(UNAUTHORIZED)
                        .withDetail(THE_USER_IS_NOT_AUTHORIZED)
                        .build();
            }

    }

    @Override
    public ResponseEntity<List<ProductDto>> getProductsOfCartUser() {
        return ResponseEntity.ok(cartService.getProductsOfCartUser());
    }

    @Override
    public ResponseEntity<List<ProductDto>> getProductsOfWishlistUser() {
        return ResponseEntity.ok(wishlistService.getProductsOfWishlistUser());
    }


}

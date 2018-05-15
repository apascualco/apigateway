package com.apascualco.blog.endpoints;

import com.apascualco.blog.domain.Product;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

import static java.util.Objects.nonNull;

@RestController
@RequestMapping("/api/products")
@Api(value = "Product", tags = "products", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductsController {

    private static final Set<Product> products = new HashSet<>();

    static {
        products.add(new Product("One"));
        products.add(new Product("Two"));
    }

    @RequestMapping(
            value ="/list",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Response Hello World")
    })
    public ResponseEntity<Set<Product>> productList(){
        return ResponseEntity.ok().body(products);
    }

    @RequestMapping(
            value ="/add",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Add product")
    })
    @PreAuthorize("hasAuthority('STANDARD_USER')")
    public ResponseEntity<Product> addProduct(
            @RequestParam(value = "product", required = false, defaultValue = "") final String product
    ){
        Product responseProducts = null;
        if(nonNull(product)) {
            responseProducts = new Product(product);
            products.add(responseProducts);
        }
        return nonNull(responseProducts)? ResponseEntity.ok().body(new Product(product)): new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(
            value ="/del",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Del product")
    })
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public ResponseEntity<Product> delProduct(
            @RequestParam(value = "product", required = false, defaultValue = "") final String product
    ){
        int productsSize = products.size();
        Product responseProducts;
        if(nonNull(product)) {
            responseProducts = new Product(product);
            products.remove(responseProducts);
        }
        return productsSize > products.size() ? ResponseEntity.ok().body(new Product(product)): new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}

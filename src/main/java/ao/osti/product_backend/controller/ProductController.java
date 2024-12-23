package ao.osti.product_backend.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import ao.osti.product_backend.models.Product;
import ao.osti.product_backend.repositories.ProductRepository;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @PostMapping("product")
    public ResponseEntity<Product> save(@RequestBody Product product) {

        product = productRepository.save(product);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(product.getId())
                .toUri();

        return ResponseEntity.created(location).body(product);
    }

    @GetMapping("products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found..!"));
        return ResponseEntity.ok(product);
    }

    @GetMapping("products")
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @DeleteMapping("products/{id}")
    public ResponseEntity<Void> removeProduct(@PathVariable int id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found..!"));

        productRepository.delete(product);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product updateProduct) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found..!"));

        product.setName(updateProduct.getName());
        product.setDescription(updateProduct.getDescription());
        product.setPrice(updateProduct.getPrice());
        product.setNewProduct(updateProduct.isNewProduct());
        product.setPromotion(updateProduct.isPromotion());

        productRepository.save(product);

        return ResponseEntity.ok().build();
    }

}

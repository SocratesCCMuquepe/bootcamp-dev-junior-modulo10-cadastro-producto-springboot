package ao.osti.product_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ao.osti.product_backend.models.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}

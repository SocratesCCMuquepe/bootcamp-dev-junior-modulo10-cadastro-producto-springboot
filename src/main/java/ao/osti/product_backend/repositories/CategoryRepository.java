package ao.osti.product_backend.repositories;

import ao.osti.product_backend.models.Category;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}

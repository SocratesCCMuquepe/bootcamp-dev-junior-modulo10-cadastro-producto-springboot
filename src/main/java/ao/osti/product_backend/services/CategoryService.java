package ao.osti.product_backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import ao.osti.product_backend.repositories.CategoryRepository;
import ao.osti.product_backend.models.Category;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Category getById(int id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Categories not found..!"));
        return category;
    }

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

}

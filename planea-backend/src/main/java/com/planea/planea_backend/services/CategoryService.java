package com.planea.planea_backend.services;

import com.planea.planea_backend.entities.Category;
import com.planea.planea_backend.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Service class that manages operations related to {@link Category}.
 * This service provides methods to retrieve, save, and delete categories.
 */
@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * Retrieves all categories from the database.
     *
     * @return a list of all categories.
     */
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    /**
     * Retrieves a category by its ID.
     *
     * @param id the ID of the category to retrieve.
     * @return an {@link Optional} containing the category if found, or an empty
     *         Optional if not found.
     */
    public Optional<Category> findById(Integer id) {
        return categoryRepository.findById(id);
    }

    /**
     * Retrieves a list of categories based on a set of IDs.
     *
     * @param ids the set of category IDs to retrieve.
     * @return a list of categories that match the provided IDs.
     */
    public List<Category> findByIds(Set<Integer> ids) {
        return categoryRepository.findAllById(ids);
    }

    /**
     * Creates or updates a category in the database.
     *
     * @param category the category to save or update.
     * @return the saved or updated category.
     */
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    /**
     * Deletes a category by its ID.
     *
     * @param id the ID of the category to delete.
     */
    public void deleteById(Integer id) {
        categoryRepository.deleteById(id);
    }
}

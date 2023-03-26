package rikkei.academy.controller.song;

import rikkei.academy.model.music.song.Category;
import rikkei.academy.service.song.category.CategoryServiceIMPL;
import rikkei.academy.service.song.category.ICategoryService;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CategoryController {
    private ICategoryService categoryService = new CategoryServiceIMPL();

    public List<Category> showCategory() {
        return categoryService.findAll();
    }

    public void createCategory(Category category) {
        categoryService.save(category);
    }

    public Category detailCategory(int id) {
        return categoryService.findById(id);
    }

    public void updateCategory(int id, Category newCategory) {
        Category category = categoryService.findById(id);
        category.setId(newCategory.getId());
        category.setName(newCategory.getName());
    }

    public void deleteCategory(int id) {
        categoryService.remove(id);
    }

    public List<Category> searchCategory(String nameCategory) {
        List<Category> categories = new ArrayList<>();
        for (Category category : categoryService.findAll()) {
            if (category.getName().toLowerCase().contains(nameCategory.toLowerCase())) {
                categories.add(category);
            }
        }
        return categories;
    }

}

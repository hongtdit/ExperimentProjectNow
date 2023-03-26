package rikkei.academy.service.song.category;

import rikkei.academy.config.Config;
import rikkei.academy.model.music.song.Category;

import java.util.List;

public class CategoryServiceIMPL implements ICategoryService{
    private static final String PATH_FILE_CATEGORY ="src/rikkei/academy/data/song/category.txt";
    private static List<Category> categoryList = new Config<Category>().readFile(PATH_FILE_CATEGORY);
    @Override
    public List<Category> findAll() {
        new Config<Category>().writeFile(PATH_FILE_CATEGORY,categoryList);
        return categoryList;
    }

    @Override
    public void save(Category category) {
        categoryList.add(category);
        new Config<Category>().writeFile(PATH_FILE_CATEGORY,categoryList);

    }

    @Override
    public void remove(int id) {
        for (int i = 0; i < categoryList.size(); i++) {
            if (id== categoryList.get(i).getId()){
                categoryList.remove(i);
                break;
            }

        }
        new Config<Category>().writeFile(PATH_FILE_CATEGORY,categoryList);
    }

    @Override
    public Category findById(int id) {
        for (int i = 0; i < categoryList.size(); i++) {
            if (id==categoryList.get(i).getId()){
                return categoryList.get(i);
            }
        }
        return null;
    }
}

package rikkei.academy.view.viewsong;

import rikkei.academy.atherfuntion.Menu;
import rikkei.academy.config.Config;
import rikkei.academy.controller.account.AccountController;
import rikkei.academy.controller.song.CategoryController;
import rikkei.academy.model.account.Account;
import rikkei.academy.model.account.Role;
import rikkei.academy.model.music.song.Category;
import rikkei.academy.view.Main;
import rikkei.academy.view.viewAccount.LoginView;
import rikkei.academy.view.viewAccount.ViewAfterSignIn;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ViewCategory {
    private CategoryController categoryController = new CategoryController();
    private List<Category> categoryList = categoryController.showCategory();

    public void deleteCategory() {
        System.out.println("Enter id category to delete");
        int id = Config.getIntegerInput();
        if (categoryController.detailCategory(id) == null) {
            System.out.println("id not found");
        }else {
            System.out.println("Enter 1 to delete - Enter 2 not to delete");
            int choice = Config.getIntegerInput();
            switch (choice) {
                case 1:
                    categoryController.deleteCategory(id);
                    System.out.println("Delete success!");
                    new ViewAfterSignIn().viewCategory();
                    break;
                case 2:
                    new ViewAfterSignIn().viewCategory();
                    break;
                default:
                    System.out.println("Invalid not found");
                    deleteCategory();
            }
        }
    }
    public void searchCategory() {
        System.out.println("Enter category name to search");
        String name = Config.getStringInput();
        for (Category category : categoryController.searchCategory(name)) {
            System.out.println(category.getName());
        }
        new ViewAfterSignIn().viewCategory();
    }

    public void createCategory(){
        int id;
        while (true) {
            if (categoryList.size() == 0) {
                id = 1;
            }else{
                id = categoryList.get(categoryList.size() - 1).getId()+1;
            }
            System.out.println("Enter category name");
            String categoryName = Config.scanner().nextLine();
            Category category = new Category(id, categoryName);
            categoryController.createCategory(category);
            System.out.println("show category");
            System.out.println(categoryList);
            System.out.println("Enter any key to continue - Enter quit to Exit");
            String backMenu = Config.scanner().nextLine();
            if(backMenu.equalsIgnoreCase("quit")){
                new ViewAfterSignIn().viewCategory();
                break;
            }
        }
    }
    public void showCategory(){
        System.out.printf("%-5s%-15s%n", "ID", "NAME");
        categoryList.forEach(category -> {
            System.out.printf("%-5s%-15s%n", category.getId(), category.getName());
        });
        new ViewPlaySong().playSongSignIn();
    }
    public void showCategoryHome(){
        Menu menu = new Menu();
        System.out.printf("%-5s%-15s%n", "ID", "NAME");
        categoryList.forEach(category -> {
            System.out.printf("%-5s%-15s%n", category.getId(), category.getName());
        });
        System.out.println("Log in to continue or quit to go back to the page");
        menu.addHeader("Enter choice: ");
        menu.addChoice("1. Go to Login Account");
        menu.addChoice("2. back Menu");
        menu.addPaddingLeft(4);
        menu.print();
        int choice = Config.getIntegerInput();
        switch (choice) {
            case 1:
                new LoginView().menuLogin();
                break;
            case 2:
                new Main().menu();
                break;
            default:
                System.out.println("Invalid not found");
        }
    }
    public void updateCategory(){
        System.out.println("Enter category id to update Category");
        int id = Config.getIntegerInput();
        if (categoryController.detailCategory(id)==null){
            System.out.println("Category not found");
            return;}
        else {
            Category category = categoryController.detailCategory(id);
            System.out.println("Enter category name to update");
            String categoryName = Config.scanner().nextLine();
            if (categoryName.trim().equals("")){
                categoryName = category.getName();
            }
            Category newCategory = new Category(id, categoryName);
            categoryController.updateCategory(id, newCategory);
            System.out.println("Update category success!");
            new ViewAfterSignIn().viewCategory();
        }
    }

}

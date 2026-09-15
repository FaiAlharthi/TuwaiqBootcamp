package com.example.capstone1.Service;


import com.example.capstone1.Model.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CategoryService {
    ArrayList<Category> categories = new ArrayList<>();

    //get all categories
    public ArrayList<Category> getCategories (){
        return categories;
    }

    //add category
    public int addCategory(Category category){
        for(Category categoryItem : categories){
            if(category.getId().equalsIgnoreCase(categoryItem.getId())){
                return -1;
            }
            if(category.getName().equalsIgnoreCase(categoryItem.getName())){
                return -2;
            }
        }
        categories.add(category);
        return 0;
    }

    //update category
    public int updateCategory(String id, Category category){
        for(Category category1 : categories){
            if(category1.getId().equalsIgnoreCase(id)){
                for(Category category2 : categories){
                    if(category2.getId().equalsIgnoreCase(category.getId()) && !(category2.getId().equalsIgnoreCase(id)) ){
                        return -1;
                    }
                    if(category2.getName().equalsIgnoreCase(category.getName())){
                        return -2;
                    }
                }
                categories.set(categories.indexOf(category1), category);
                return 0;
            }
        }
        return -3;
    }

    //delete category
    public boolean deleteCategory(String id){

        for(Category category1 : categories){
            if(category1.getId().equalsIgnoreCase(id)){
                categories.remove(category1);
                return true;
            }
        }
        return false;
    }



}

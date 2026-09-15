package com.example.capstone1.Service;

import com.example.capstone1.Model.Category;
import com.example.capstone1.Model.Merchant;
import com.example.capstone1.Model.MerchantStock;
import com.example.capstone1.Model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ProductService {
    ArrayList<Product> products = new ArrayList<>();

    private final CategoryService categoryService;

    //get all products
    public ArrayList<Product> getProducts (){
        return products;
    }

    //add product
    public int addProducts (Product product) {
        for (Product product1 : products) {
            if (product1.getId().equalsIgnoreCase(product.getId())) {
                return -1;
            }
        }
        for (Category category : categoryService.categories) {
            if (product.getCategoryID().equalsIgnoreCase(category.getId())) {
                products.add(product);
                return 0;
            }
        }

        return -2;
    }

    //update product
    public int updateProduct(String id, Product product){
        for(Product product1 : products){
            if(product1.getId().equalsIgnoreCase(id)){
                for(Product stock2 : products){
                    if(stock2.getId().equalsIgnoreCase(product.getId()) && !(stock2.getId().equalsIgnoreCase(id)) ){
                        return -1;
                    }
                }
                for( Category category : categoryService.categories){
                    if(category.getId().equalsIgnoreCase(product.getCategoryID())){
                        products.set(products.indexOf(product1), product);
                        return 0;
                    }
                }
                return -2;
            }
        }
        return -3;
    }

    //delete product
    public boolean deleteProduct(String id){
        for(Product product : products){
            if(product.getId().equalsIgnoreCase(id)){
                products.remove(product);
                return true;
            }
        }
        return false;
    }

    //search for products within price range in particular Category 4/5
    public ArrayList<Product> productsInRange( String categoryID, double min, double max){
        if( !(min <= max && min >=0)){
            return null; //min should be 0 or more and less than max
        }

        ArrayList<Product> newProducts =new ArrayList<>();
        for(Category category: categoryService.categories){
            if(category.getId().equalsIgnoreCase(categoryID)){
                for(Product product : products){
                    if(product.getCategoryID().equalsIgnoreCase(category.getId())){
                        if(product.getPrice() >= min && product.getPrice() <= max){
                            newProducts.add(product);
                        }
                    }
                }
            }
        }

        return newProducts;
    }


}

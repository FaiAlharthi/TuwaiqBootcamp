package com.example.capstone1.Service;


import com.example.capstone1.Model.Merchant;
import com.example.capstone1.Model.MerchantStock;
import com.example.capstone1.Model.Product;
import com.example.capstone1.Model.User;
import jakarta.validation.constraints.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserService {
    ArrayList<User> users = new ArrayList<>();

//    ArrayList<Product> wishlistProducts = new ArrayList<>();
    ArrayList<String> previousOrders = new ArrayList<>();

    final private MerchantStockService merchantStockService;

    final private ProductService productService;
    final private MerchantService merchantService;


    //get all users
    public ArrayList<User> getUsers (){
        return users;
    }

    //add user
    public int addUser (User user){
        for(User user1 : users){
            if(user1.getId().equalsIgnoreCase(user.getId())){
                return -1;
            }
            if(user1.getEmail().equalsIgnoreCase(user.getEmail())){
                return -2;
            }
        }
        if(user.getRole().equalsIgnoreCase("admin") && user.getBalance()!=0){
            return -3;
        }
        users.add(user);
        return 0;
    }

    //update user
    public int updateUser(String id, User user){

        if(user.getRole().equalsIgnoreCase("admin") && user.getBalance()!=0){
            return -4;
        }

        for(User user1 : users){
            if(user1.getId().equalsIgnoreCase(id)){
                for(User user2 : users){
                    if(user2.getId().equalsIgnoreCase(user.getId()) && !(user2.getId().equalsIgnoreCase(id)) ){
                        return -1;
                    }
                    if(user2.getEmail().equalsIgnoreCase(user.getEmail()) && !(user2.getEmail().equalsIgnoreCase(user1.getEmail())) ){
                        return -2;
                    }
                }
                users.set(users.indexOf(user1), user);
                return 0;
            }
        }

        return -3;
    }

    //delete user
    public boolean deleteUser(String id){
        for(User user : users){
            if(user.getId().equalsIgnoreCase(id)){
                users.remove(user);
                return true;
            }
        }
        return false;
    }


    //user can buy a product
    public int buyProduct(String userID, String productID, String merchantID){
        boolean exist=false;
        User user1 = null;
        Product product1 = null;

        for(User user:users){
            if(user.getId().equalsIgnoreCase(userID)){
                if(user.getRole().equalsIgnoreCase("admin")){
                    return -7;
                }
                exist = true;
                user1 = user;
                break;
            }
        }
        if(!exist){
            return -1;
        }

        exist = false;
        for(Product product : productService.products){
            if(product.getId().equalsIgnoreCase(productID)){
                exist = true;
                product1=product;
                break;
            }
        }

        if(!exist){
            return -2;
        }

        exist = false;

        for(Merchant merchant : merchantService.merchants){
            if(merchant.getId().equalsIgnoreCase(merchantID)){
                exist = true;
                break;
            }
        }

        if(!exist){
            return -3;
        }

        exist = false;

        for(MerchantStock merchantStock : merchantStockService.merchantStocks){
            if(merchantStock.getMerchantID().equalsIgnoreCase(merchantID) && merchantStock.getProductId().equalsIgnoreCase(productID)){
                exist = true;
                if(merchantStock.getStock() <= 0){
                    return -5; // out of stock
                }
                if(user1.getBalance() >= product1.getPrice()){
                    user1.setBalance(user1.getBalance()-product1.getPrice());
                }else{
                    return -6; // balance of user is not enough
                }

                merchantStock.setStock(merchantStock.getStock() - 1);
                break;
            }
        }

        if(!exist){
            return -4; // merchant doesn't sell this product
        }


        previousOrders.add(userID);
        previousOrders.add(productID);

        return 0; //added successfully
    }

    //give gift card ( transfer from your balance to another user balance ) 1/5
    public int giveGiftCard(String senderID, String recipientID, double amount){
        boolean exist = false;
        for(User user : users){
            if(user.getId().equalsIgnoreCase(senderID)){
                exist = true;
                break;
            }
        }
        if(!exist){
            return -1; //sender id not exist
        }

        exist = false;
        for(User user : users){
            if(user.getId().equalsIgnoreCase(recipientID)){
                if(user.getRole().equalsIgnoreCase("admin")){
                    return -4;
                }
                exist = true;
                break;
            }
        }
        if(!exist){
            return -2; //recipient id not exist
        }

        for(User user : users){
            if (user.getId().equalsIgnoreCase(senderID)){
                if(user.getBalance() < amount){
                    return -3; //balance less than the amount you want to transfer
                }
                user.setBalance(user.getBalance()-amount);
            }
            if (user.getId().equalsIgnoreCase(recipientID) && !(senderID.equalsIgnoreCase(recipientID))){
                user.setBalance(user.getBalance()+amount);
            }
        }

        return 0;
    }

    //return a product, 2/5
    public int returnProduct(String userID, String productID){
        boolean exist=false;
        User user1 = null;
        Product product1 = null;

        for(User user:users){
            if(user.getId().equalsIgnoreCase(userID)){
                if(user.getRole().equalsIgnoreCase("admin")){
                    return -3;
                }
                exist = true;
                user1 = user;
                break;
            }
        }
        if(!exist){
            return -1;
        }

        exist = false;
        for(Product product : productService.products){
            if(product.getId().equalsIgnoreCase(productID)){
                exist = true;
                product1=product;
                break;
            }
        }
        if(!exist){
            return -2;
        }

        boolean bought=false;
        for(int i =0; i < previousOrders.size() ; i++){
            if(previousOrders.get(i).equalsIgnoreCase(userID)&&previousOrders.get(i+1).equalsIgnoreCase(productID)){
                bought = true;
            }
        }
        if(!bought){
            return -4; // you didn't buy this product
        }

        for(MerchantStock merchantStock : merchantStockService.merchantStocks){
            if(merchantStock.getProductId().equalsIgnoreCase(productID)){
                user1.setBalance(user1.getBalance()+product1.getPrice());
                merchantStockService.updateStock(productID,merchantStock.getMerchantID(),merchantStock.getStock()+1);
                break;
            }
        }

        return 0; //added successfully
    }

    //display previous orderes of the user 5/5
    public ArrayList<Product> diplayPreviousOrders (String userID){
        ArrayList<Product> boughtProducts = new ArrayList<>();

        for(User user: users){
            if(user.getId().equalsIgnoreCase(userID)){
                for(int i =0; i < previousOrders.size() ; i++){
                    if(previousOrders.get(i).equalsIgnoreCase(userID)){
                        for (Product product: productService.products){
                            if(product.getId().equalsIgnoreCase(previousOrders.get(i+1))){
                                boughtProducts.add(product);
                            }
                        }
                    }
                }
                return boughtProducts;
            }
        }
        return null;
    }


    //extra endpoint
    public ArrayList<User> displayCustomer (String userID){
        ArrayList<User> customers = new ArrayList<>();
        boolean found = false;

        for (User user: users){
            if(user.getId().equalsIgnoreCase(userID)){
                found = true;
                if(!(user.getRole().equalsIgnoreCase("admin"))){
                    return null;
                }
            }
        }
        if(!found){
            return null;
        }

        for (User user: users) {
            if(user.getRole().equalsIgnoreCase("customer")){
                customers.add(user);
            }
        }
        return customers;
    }

}



package com.example.capstone1.Service;

import com.example.capstone1.Model.Merchant;
import com.example.capstone1.Model.MerchantStock;
import com.example.capstone1.Model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class MerchantStockService {
    ArrayList<MerchantStock> merchantStocks = new ArrayList<>();

    final private ProductService productService;
    final private MerchantService merchantService;

    //get all Stocks
    public ArrayList<MerchantStock> getMerchantStocks (){
        return merchantStocks;
    }

    //add Stock
    public int addMerchantStock(MerchantStock merchantStock){
        boolean exist=false;
        for(Product product : productService.products){
            if(product.getId().equalsIgnoreCase(merchantStock.getProductId())){
                exist = true;
                break;
            }
        }

        if(!exist){
            return -2;
        }

        exist = false;
        for(Merchant merchant : merchantService.merchants){
            if(merchant.getId().equalsIgnoreCase(merchantStock.getMerchantID())  ){
                exist = true;
                break;
            }
        }

        if(!exist){
            return -3;
        }

        for(MerchantStock MerchantStock1 : merchantStocks){
            if(merchantStock.getId().equalsIgnoreCase(MerchantStock1.getId())){
                return -1;
                //this stock id already used
            }

            if(merchantStock.getMerchantID().equalsIgnoreCase(MerchantStock1.getMerchantID()) && merchantStock.getProductId().equalsIgnoreCase(MerchantStock1.getProductId())){
                return -4;
            }
        }


        merchantStocks.add(merchantStock);
        return 0;
    }


    //update Stock
    public int updateEntireStock(String id, MerchantStock merchantStock){

        //check product
        boolean exist = false;

        for(Product product : productService.products){
            if(product.getId().equalsIgnoreCase(merchantStock.getProductId())){
                exist = true;
                break;
            }
        }

        if(!exist){
            return -2;
        }

        //check merchant
        exist = false;

        for(Merchant merchant : merchantService.merchants){
            if(merchant.getId().equalsIgnoreCase(merchantStock.getMerchantID())){
                exist = true;
                break;
            }
        }

        if(!exist){
            return -3;
        }

        //find the stock to update
        for(MerchantStock stock1 : merchantStocks){

            if(stock1.getId().equalsIgnoreCase(id)){

                //check Stock ID if already exist
                for(MerchantStock stock2 : merchantStocks){

                    if(!stock2.getId().equalsIgnoreCase(id) && stock2.getId().equalsIgnoreCase(merchantStock.getId())){
                        return -1;
                    }
                }

                // Check duplicate Merchant + Product
                for(MerchantStock stock2 : merchantStocks){

                    if(!stock2.getId().equalsIgnoreCase(id) && stock2.getMerchantID().equalsIgnoreCase(merchantStock.getMerchantID())
                            && stock2.getProductId().equalsIgnoreCase(merchantStock.getProductId())){
                        return -4;
                    }
                }

                merchantStocks.set(merchantStocks.indexOf(stock1), merchantStock);
                return 0;
            }
        }

        return -5;
    }

    //delete Stock
    public boolean deleteMerchantStock(String id){
        for(MerchantStock stock : merchantStocks){
            if(stock.getId().equalsIgnoreCase(id)){
                merchantStocks.remove(stock);
                return true;
            }
        }
        return false;
    }

    // updating merchant stock for a certain product
    public int updateStock(String productID, String merchantID, int stock){
        boolean exist=false;

        for(Product product : productService.products){
            if(product.getId().equalsIgnoreCase(productID)){
                exist=true;
                break;
            }
        }
        if(!exist)
            return -1;

        exist=false;
        for(Merchant merchant:merchantService.merchants){
            if(merchant.getId().equalsIgnoreCase(merchantID)){
                exist=true;
                break;
            }
        }

        if(!exist)
            return -2;

        //check if the merchant owns this product
        for(MerchantStock merchantStock : merchantStocks){
            if(merchantStock.getProductId().equalsIgnoreCase(productID) && merchantStock.getMerchantID().equalsIgnoreCase(merchantID)){
                if(stock <= 10) {
                    return -3;
                }
                merchantStock.setStock(stock);
                break;
            }
        }
        return 0;
    }


    //apply discount 3/5
    public int applyDiscount(String merchantID, String productID, int discountPercentage){

        //check product
        boolean exist = false;

        for(Product product : productService.products){
            if(product.getId().equalsIgnoreCase(productID)){
                exist = true;
                break;
            }
        }

        if(!exist){
            return -1;
        }

        //check merchant
        exist = false;

        for(Merchant merchant : merchantService.merchants){
            if(merchant.getId().equalsIgnoreCase(merchantID)){
                exist = true;
                break;
            }
        }

        if(!exist){
            return -2;
        }

        if(discountPercentage < 5){
            return -3;
        }

        //check if merchant sells this product
        for(MerchantStock merchantStock : merchantStocks){

            if(merchantStock.getMerchantID().equalsIgnoreCase(merchantID)
                    && merchantStock.getProductId().equalsIgnoreCase(productID)){

                for(Product product : productService.products){

                    if(product.getId().equalsIgnoreCase(productID)){

                        double newPrice = product.getPrice()
                                - (product.getPrice() * (discountPercentage / 100.0));

                        product.setPrice(newPrice);

                        return 0;
                    }
                }
            }
        }

        return -4;
    }


}

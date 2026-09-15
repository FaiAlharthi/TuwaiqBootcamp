package com.example.capstone1.Service;

import com.example.capstone1.Model.Merchant;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class MerchantService {

    ArrayList<Merchant> merchants = new ArrayList<>();

    //get all merchants
    public ArrayList<Merchant> getMerchants (){
        return merchants;
    }

    //add category
    public boolean addMerchant(Merchant merchant){
        for(Merchant merchant1 : merchants){
            if(merchant.getId().equalsIgnoreCase(merchant1.getId())){
                return false;
            }
        }
        merchants.add(merchant);
        return true;
    }

    //update Merchant
    public int updateMerchant(String id, Merchant merchant){
        for(Merchant merchant1 : merchants){
            if(merchant1.getId().equalsIgnoreCase(id)){
                for(Merchant merchant2 : merchants){
                    if(merchant2.getId().equalsIgnoreCase(merchant.getId()) && !(merchant2.getId().equalsIgnoreCase(id)) ){
                        return -1;
                    }
                }
                merchants.set(merchants.indexOf(merchant1), merchant);
                return 0;
            }
        }
        return -2;
    }

    //delete Merchant
    public boolean deleteMerchant(String id){
        for(Merchant merchant : merchants){
            if(merchant.getId().equalsIgnoreCase(id)){
                merchants.remove(merchant);
                return true;
            }
        }
        return false;
    }



}

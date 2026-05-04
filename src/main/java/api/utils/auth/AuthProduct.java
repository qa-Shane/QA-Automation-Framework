package api.utils.auth;

import api.menu.ProductMenu;
import api.utils.Product;
import api.utils.Utils;

public class AuthProduct {
    public static boolean isExistingProduct(int id){
        for(Product product : Utils.getProducts()){
            if(product.id() == id)
                return true;
        }
        return false;
    }
    public static boolean isValidProduct(String title){
        for(Product product : Utils.getProducts()){
            if(product.title().equals(title))
                return true;
        }
        return false;
    }
    public static Product getProduct(int id){
        for(Product product : Utils.getProducts()){
            if(product.id() == id)
                return product;
        }
        return null;
    }
}

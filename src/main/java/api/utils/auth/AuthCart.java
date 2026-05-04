package api.utils.auth;

import api.utils.Cart;
import api.utils.Utils;

public class AuthCart {

    public static boolean isExistingCart(int id) {
        for(Cart cart : Utils.getCarts()){
            if(cart.id() == id)
                return true;
        }
        return false;
    }

    public static Cart getCart(int id) {
        for(Cart cart : Utils.getCarts()){
            if(cart.id() == id)
                return cart;
        }
        return null;
    }
}

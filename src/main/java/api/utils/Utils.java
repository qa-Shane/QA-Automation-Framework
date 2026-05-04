package api.utils;

import api.FetchAPI;
import api.utils.user.UserAPI;
import com.fasterxml.jackson.core.type.TypeReference;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.io.IOException;
import java.util.List;

public class Utils {
    public static List<Product> getProducts(){
        Response r = RestAssured.get(Links.PRODUCT_URL.getURL());
        List<Product> products = null;
        try {
            products = FetchAPI.getInstance().getMap().readValue(r.getBody().asString(), new TypeReference<List<Product>>() {});
        }catch(NullPointerException | IOException e) {
            e.printStackTrace();
        }
        return products;
    }

    public static List<UserAPI> getUsers(){
        Response r = RestAssured.get(Links.USERS_URL.getURL());
        List<UserAPI> users = null;
        try {
            users = FetchAPI.getInstance().getMap().readValue(r.getBody().asString(), new TypeReference<List<UserAPI>>() {});
        }catch(NullPointerException | IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    public static List<Cart> getCarts(){
        Response r = RestAssured.get(Links.CART_URL.getURL());
        List<Cart> carts = null;
        try {
            carts = FetchAPI.getInstance().getMap().readValue(r.getBody().asString(), new TypeReference<List<Cart>>() {});
        }catch(NullPointerException | IOException e) {
            e.printStackTrace();
        }
        return carts;
    }
}

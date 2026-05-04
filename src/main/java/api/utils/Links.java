package api.utils;

public enum Links {
    CART_URL("https://fakestoreapi.com/carts"), PRODUCT_URL("https://fakestoreapi.com/products"), USERS_URL("https://fakestoreapi.com/users");

    private String URL;
    Links(String URL){
        this.URL = URL;
    }
    public String getURL(){return URL;}
}

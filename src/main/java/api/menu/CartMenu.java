package api.menu;

import api.input.UserResponse;
import api.utils.*;
import api.utils.auth.AuthCart;
import api.utils.auth.AuthProduct;
import api.utils.auth.AuthUser;
import api.utils.user.User;
import api.utils.user.UserAPI;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.ArrayList;
import java.util.List;

public class CartMenu {
    public static void show(){
        CartMenu cartMenu = new CartMenu();
        boolean isComplete = false;
        while(!isComplete){
            StringBuilder sb = new StringBuilder();
            sb.append("-------------------------\n");
            sb.append("Welcome to the Cart Menu\n");
            sb.append("1. View Carts | 2. Create a new Cart");
            System.out.println(sb);
            String response = UserResponse.ask("What would you like to do? Enter a number");
            try {
                int res = Integer.parseInt(response);
                switch (res){
                    case 1:
                        cartMenu.viewCarts();
                        isComplete = true;
                        break;
                    case 2:
                        cartMenu.createCart();
                        isComplete = true;
                        break;
                    default:
                        break;
                }
            }catch (NumberFormatException _){}
        }
    }

    private void createCart() {
        String email = UserResponse.ask("Enter your email");
        if(AuthUser.isExistingEmail(email)){
            String password = UserResponse.ask("Enter your password");
            for(UserAPI user : Utils.getUsers()){
                if(user.email().equals(email)){
                    User u = new User(user.username(), email, password);
                    int id = 0;
                    if(AuthUser.isValidLogin(u)){
                        List<CartProduct> productList = new ArrayList<>();
                        while(true){
                            String productID = UserResponse.ask("Enter the product ID you want to add | S to stop");
                            try{
                                if(productID.equalsIgnoreCase("S"))
                                    break;
                                id = Integer.parseInt(productID);
                                if(AuthProduct.isExistingProduct(id)){
                                    CartProduct p = new CartProduct(id, 1);
                                    productList.add(p);
                                }
                                System.out.println("Product ID does not exist");
                            }catch (NumberFormatException _){}
                        }
                        Cart cart = new Cart(Utils.getCarts().size() + 1, user.id(), "05-03-2026", productList);
                        Response response = RestAssured.given().body(cart).post(Links.CART_URL.getURL());
                        if(response.getStatusCode() == 201) {
                            System.out.println("Cart created successfully!");
                            Utils.getCarts().add(cart);
                        }
                        else {
                            System.out.println("Something went wrong!");
                            CartMenu.show();
                        }
                    }
                }
            }
        }
        System.out.println("That email does not exist");
        createCart();
    }

    private void viewCarts() {
        boolean isComplete = false;
        while(!isComplete){
            System.out.println("1. View Cart by ID | 2. View all Carts");
            String response = UserResponse.ask("What would you like to do? Enter a number");
            try{
                int res = Integer.parseInt(response);
                switch (res){
                    case 1:
                        int id = 0;
                        while(true){
                            response = UserResponse.ask("What is the ID?");
                            try{
                                id = Integer.parseInt(response);
                                break;
                            }catch (NumberFormatException _){}
                        }
                        if(AuthCart.isExistingCart(id)){
                            Cart cart = AuthCart.getCart(id);
                            System.out.println(cart.toString());
                        }
                        else{
                            System.out.println("That Cart does not exist");
                            response = UserResponse.ask("Would you like to create it? Y/N");
                            if(response.equals("Y")){
                                createCart();
                                isComplete = true;
                                break;
                            }
                            viewCarts();
                        }
                        isComplete = true;
                        break;
                    case 2:
                        for(Cart c : Utils.getCarts()){
                            System.out.println(c.toString());
                        }
                        isComplete = true;
                        break;
                    default:
                        break;
                }
            }catch (NumberFormatException _){}
        }
    }
}

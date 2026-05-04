package api.menu;

import api.input.UserResponse;
import api.utils.Links;
import api.utils.Product;
import api.utils.Utils;
import api.utils.auth.AuthProduct;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class ProductMenu {
    public static void show(){
        ProductMenu productMenu = new ProductMenu();
        boolean isComplete = false;
        while(!isComplete){
            StringBuilder sb = new StringBuilder();
            sb.append("-------------------------\n");
            sb.append("Welcome to the Product Menu\n");
            sb.append("1. View Products | 2. Create a Product");
            System.out.println(sb);
            String response = UserResponse.ask("What would you like to do? Enter a number");
            try {
                int res = Integer.parseInt(response);
                switch (res){
                    case 1:
                        productMenu.viewProducts();
                        isComplete = true;
                        break;
                    case 2:
                        productMenu.createProduct();
                        isComplete = true;
                        break;
                    default:
                        break;
                }
            }catch (NumberFormatException _){}
        }
    }

    private void createProduct() {
        String title = UserResponse.ask("Enter the Product Title");
        if(AuthProduct.isValidProduct(title)){
            System.out.println("That Product already exists!");
            createProduct();
        }
        Map<String, Double> rating = getRandomRating();
        int id = Utils.getProducts().size() + 1;
        float price = 0;
        while(true) {
            try {
                String priceResponse = UserResponse.ask("Enter a price");
                price = Float.parseFloat(priceResponse);
                break;
            }
            catch (NumberFormatException _){}
        }
        String description = UserResponse.ask("Enter a description");
        String category = UserResponse.ask("Enter a category");
        String url = "myproduct.com/"+title.replace(' ', '_')+".png";
        Product product = new Product(id, rating, title, price, description,category,url);
        Response response = RestAssured.given().body(product).post(Links.PRODUCT_URL.getURL());
        if(response.getStatusCode() == 201) {
            System.out.println("Product created successfully!");
            Utils.getProducts().add(product);
        }
        else {
            System.out.println("Something went wrong!");
            ProductMenu.show();
        }
    }

    private Map<String, Double> getRandomRating() {
        Map<String,Double> rating = new HashMap<>();
        double randRating = ThreadLocalRandom.current().nextDouble(0,5.1);
        double randCount = ThreadLocalRandom.current().nextDouble(1,2500);
        rating.put("rate", randRating);
        rating.put("count", (double) Math.round(randCount));
        return rating;
    }

    private void viewProducts() {
        boolean isComplete = false;
        while(!isComplete){
            System.out.println("1. View Product by ID | 2. View all Products");
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
                        if(AuthProduct.isExistingProduct(id)){
                            Product product = AuthProduct.getProduct(id);
                            System.out.println(product.toString());
                        }
                        else{
                            System.out.println("That product does not exist");
                            response = UserResponse.ask("Would you like to create it? Y/N");
                            if(response.equals("Y")){
                                createProduct();
                                isComplete = true;
                                break;
                            }
                            viewProducts();
                        }
                        isComplete = true;
                        break;
                    case 2:
                        for(Product p : Utils.getProducts()){
                            System.out.println(p.toString());
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

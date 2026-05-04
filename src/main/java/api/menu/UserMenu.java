package api.menu;

import api.input.UserResponse;
import api.utils.*;
import api.utils.auth.AuthUser;
import api.utils.user.Address;
import api.utils.user.User;
import api.utils.user.UserAPI;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class UserMenu {
    public static void show(){
        UserMenu userMenu = new UserMenu();
        boolean isComplete = false;
        while(!isComplete){
            StringBuilder sb = new StringBuilder();
            sb.append("-------------------------\n");
            sb.append("Welcome to the User Menu\n");
            sb.append("1. Login | 2. Create User");
            System.out.println(sb);
            String response = UserResponse.ask("What would you like to do? Enter a number");
            try {
                int res = Integer.parseInt(response);
                switch (res){
                    case 1:
                        userMenu.login();
                        isComplete = true;
                        break;
                    case 2:
                        userMenu.createAccount();
                        isComplete = true;
                        break;
                    default:
                        break;
                }
            }catch (NumberFormatException _){}
        }
    }

    private void createAccount(){
        String email = UserResponse.ask("Enter your email");
        if(AuthUser.isExistingEmail(email)){
            while(true) {
                System.out.println("That email already exists...");
                String response = UserResponse.ask("Would you like to log in with that account? Y/N");
                if(response.equals("Y")) {
                    login();
                    break;
                }
                else if(response.equals("N")){
                    createAccount();
                    break;
                }
            }
        }
        String username = UserResponse.ask("Enter your username");
        String password = UserResponse.ask("Enter your password");
        User user = new User(username, email, password);
        boolean isValidUser = AuthUser.isValidUser(user);
        if(!isValidUser){
            while(true) {
                System.out.println("That user already exists...");
                String response = UserResponse.ask("Would you like to log in with that user? Y/N");
                if(response.equals("Y")) {
                    login();
                    break;
                }
                else if(response.equals("N")){
                    createAccount();
                    break;
                }
            }
        }
        else {
            int id = Utils.getUsers().size() + 1;
            String firstName = UserResponse.ask("Enter your First Name");
            String lastName = UserResponse.ask("Enter your Last Name");
            Map<String, String> name = new HashMap<>();
            name.put("firstname", firstName);
            name.put("lastname", lastName);
            String phone = UserResponse.ask("Enter your Phone Number");
            String city = UserResponse.ask("Enter your City");
            String street = UserResponse.ask("Enter your Street Name");
            int number = 0;
            while (true) {
                try {
                    number = Integer.parseInt(UserResponse.ask("Enter your Street Number"));
                    break;
                } catch (NumberFormatException _){}
            }
            String zipcode = UserResponse.ask("Enter your Zip Code");
            Map<String, String> geoLoc = createGeolocation();
            Address address = new Address(city,street, number, zipcode, geoLoc);
            UserAPI newUser = new UserAPI(id, email, username, password, name, address, phone);
            Response response = RestAssured.given().body(newUser).post(Links.USERS_URL.getURL());
            if(response.getStatusCode() == 201) {
                System.out.println("Account created successfully!");
                Utils.getUsers().add(newUser);
            }
            else {
                System.out.println("Something went wrong!");
                UserMenu.show();
            }
        }
    }

    private Map<String,String> createGeolocation() {
        Map<String, String> location = new HashMap<>();
        double randomLat = ThreadLocalRandom.current().nextDouble(-90,91);
        double randomLong = ThreadLocalRandom.current().nextDouble(-180,181);
        location.put("lat", String.format("%.2f", randomLat));
        location.put("long", String.format("%.2f", randomLong));
        return location;
    }

    private void login(){
        String email = UserResponse.ask("Enter your email");
        String username = UserResponse.ask("Enter your username");
        String password = UserResponse.ask("Enter your password");
        User user = new User(username, email, password);
        boolean accessGranted = AuthUser.isValidLogin(user);
        System.out.println("ACCESS: " + accessGranted);
        if(!accessGranted)
            login();
    }
}

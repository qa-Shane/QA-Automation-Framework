package api.utils.auth;

import api.FetchAPI;
import api.utils.user.User;
import api.utils.user.UserAPI;
import api.utils.Utils;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.List;

public class AuthUser {

    public static boolean isExistingEmail(String email){
        List<UserAPI> users = Utils.getUsers();
        for(UserAPI u : users){
            if(email.equals(u.email()))
                return true;
        }
        return false;
    }

    public static boolean isValidUser(User user){
        List<UserAPI> users = Utils.getUsers();
        for(UserAPI u : users){
            if(user.username().equals(u.username()))
                return false;
        }
        return true;
    }
    public static boolean isValidLogin(User user){
        try{
            String userJson = FetchAPI.getInstance().getMap().writeValueAsString(user);
            Response r = RestAssured.given().contentType("application/json").body(userJson).post("https://fakestoreapi.com/auth/login");
            if(r.getStatusCode() == 200)
                return true;
            else
                System.out.println(r.getStatusCode());
        } catch (JsonProcessingException e) {
            System.out.println("Could not generate JSON for this class");
        }
        return false;
    }
}

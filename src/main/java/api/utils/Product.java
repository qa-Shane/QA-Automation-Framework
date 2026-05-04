package api.utils;

import java.util.Map;

public record Product(int id, Map<String, Double> rating, String title, float price, String description, String category, String image) {

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Product ID: " + id + "\nRating: " + rating + "\nTitle: " + title + "\nPrice: " + price + "\nDescription: " + description + "\nCategory: " + category + "\nImage URL: " + image);
        return sb.toString();
    }

}


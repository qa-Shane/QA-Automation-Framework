package api.utils;

import api.utils.auth.AuthProduct;
import java.util.List;

public record Cart(int id, int userId, String date, List<CartProduct> products) {
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("ID: " + id + "\nUser ID: " + userId+"\n");
        for(CartProduct product : products){
            Product p = AuthProduct.getProduct(product.productId());
            sb.append(p.toString()+"\nQuantity: " + product.quantity()+"\n");
        }

        return sb.toString();
    }
}

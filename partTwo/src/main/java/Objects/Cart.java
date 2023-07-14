package Objects;

import java.util.HashMap;

public class Cart {

    private HashMap<Product, Integer> cart;

    public Cart(){
        cart = new HashMap<>();
    }

    public void updateCart(Product id, int number){
        if (number == 0){
            cart.remove(id);
        }else {
            cart.put(id, number);
        }
    }

    public int getCount(Product product){
        if (!cart.containsKey(product))return 0;
        return cart.get(product);
    }

    public int getTotalPrice(){
        int total = 0;
        for (Product a : cart.keySet()){
            total += a.getPrice() * cart.get(a);
        }

        return total;
    }
}

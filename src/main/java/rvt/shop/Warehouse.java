package rvt.shop;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Warehouse {
    private Map<String, Integer> prices;
    private Map<String, Integer> stock;

    public Warehouse() {
        this.prices = new HashMap<>();
        this.stock = new HashMap<>();
    }

    public int price(String product) {
        return this.prices.getOrDefault(product, -99);
    }

    public int stock(String product) {
        return this.stock.getOrDefault(product, 0);
    }

    public void addProduct(String product, int price, int stock) {
        this.prices.put(product, price);
        this.stock.put(product, stock);
    }

    public boolean take(String product) {
        if (this.stock.getOrDefault(product, 0) > 0) {
            this.stock.put(product, this.stock.get(product) - 1);
            return true;
        }
        return false;
    }

    public Set<String> products() {
        return this.prices.keySet();
    }

    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse();
        warehouse.addProduct("Coffee", 5, 10);
        warehouse.addProduct("Tea", 3, 20);

        System.out.println("Price of Coffee: " + warehouse.price("Coffee"));
        System.out.println("Stock of Tea: " + warehouse.stock("Tea"));
        System.out.println("Taking Coffee: " + warehouse.take("Coffee"));
        System.out.println("Stock of Coffee after taking: " + warehouse.stock("Coffee"));
        System.out.println("Products in warehouse: " + warehouse.products());
    }
}

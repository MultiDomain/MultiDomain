package DesignPatterns.BehaviouralPattern;

import java.util.ArrayList;
import java.util.List;

interface Order {
    void execute();
}

class Stock {
    private String name = "abc";
    private int quantity = 10;

    public void buy() {
        System.out.println("Stock [ Name: " + name + " ,Quantity: " + quantity + " ] bought");
    }

    public void sell() {
        System.out.println("Stock [ Name: " + name + " ,Quantity: " + quantity + " ] Sold");
    }
}

class BuyStock implements Order {
    private Stock abcStock;

    public BuyStock(Stock abcStock) {
        this.abcStock = abcStock;
    }

    public void execute() {
        abcStock.buy();
    }

}

class SellStock implements Order{
    private Stock abcStock;

    public SellStock(Stock abcStock){
        this.abcStock = abcStock;
    }
    public void execute(){
        abcStock.sell();
    }
}

class Broker{
    private List<Order> orderlist = new ArrayList<>();
    public void takeOrder (Order order){
        orderlist.add(order);
    }

    public void placeOrder(){
        for(Order ol: orderlist){
            ol.execute();
        }
        orderlist.clear();
    }
}

public class CommandPattern {
    public static void main(String[] args) {
        Stock abcStock = new Stock();
        BuyStock buyStockOrder = new BuyStock(abcStock);
        SellStock sellStockOrder = new SellStock(abcStock);

        Broker broker = new Broker();
        broker.takeOrder(buyStockOrder);
        broker.takeOrder(sellStockOrder);
        broker.placeOrder();
    }
}

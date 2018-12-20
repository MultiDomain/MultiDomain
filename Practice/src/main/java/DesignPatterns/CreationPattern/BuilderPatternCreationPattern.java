package DesignPatterns.CreationPattern;

import java.util.ArrayList;
import java.util.List;

public class BuilderPatternCreationPattern {
    //---------------------Step 7----------------------

    public static void main(String[] args) {
        MealBuilder mealBuilder = new MealBuilder();
        Meal vegMeal = mealBuilder.preparerVegMeal();
        System.out.println("veg Meal");
        vegMeal.showItem();
        System.out.println(" | Total Cost: "+ vegMeal.getCost());

        Meal NonVegMeal = mealBuilder.prepareNonVegMeal();
        System.out.println("\n\n Non-Veg Meal ");
        NonVegMeal.showItem();
        System.out.println(" | Total cost: "+NonVegMeal.getCost());
    }
}
//---------------------Step 1----------------------
interface item {
    public String name();
    public Packing packing();
    public float price();
}
interface Packing {
    public String pack();
}
//---------------------Step 2----------------------
class Wrapper implements Packing{
    @Override
    public String pack() {
        return "Wrapper";
    }
}
class Bottle implements Packing{
    @Override
    public String pack() {
        return "Bottle";
    }
}
//---------------------Step 3----------------------
abstract class Burger implements item{
    public Packing packing(){
        return new Wrapper();
    }
    public abstract float price();
}

abstract class ColdDrink implements item{
    @Override
    public Packing packing() {
        return new Bottle();
    }
    public abstract float price();
}
//---------------------Step 4----------------------

class VegBurger extends Burger{
    @Override
    public String name() {
        return "Veg Burger";
    }
    @Override
    public float price() {
        return 25.0f;
    }
}
class ChickenBurger extends Burger{
    @Override
    public String name() {
        return "Chicken Burger";
    }
    @Override
    public float price() {
        return 50.5f;
    }
}
class Coke extends ColdDrink{
    @Override
    public String name() {
        return "Coke";
    }
    @Override
    public float price() {
        return 30.0f;
    }
}
class Pepsi extends ColdDrink{
    @Override
    public String name() {
        return "Pepsi";
    }
    @Override
    public float price() {
        return 35.0f;
    }
}
//---------------------Step 5----------------------
class Meal {
    private List<item> items = new ArrayList<>();
    public void addItem(item itm){
        items.add(itm);
    }

    public float getCost(){
        float cost = 0.0f;
        for(item item : items){
            cost+= item.price();
        }
        return cost;
    }
    public void showItem(){
        for(item item: items){
            System.out.print(" Item: "+item.name());
            System.out.print(", packing: "+item.packing().pack());
            System.out.print(", price: "+item.price());
            System.out.println();
        }
    }
}
//---------------------Step 6----------------------
class MealBuilder{
    public Meal preparerVegMeal(){
        Meal meal = new Meal();
        meal.addItem(new VegBurger());
        meal.addItem(new Coke());
        return meal;
    }
    public Meal prepareNonVegMeal(){
        Meal meal = new Meal();
        meal.addItem(new ChickenBurger());
        meal.addItem(new Pepsi());
        return meal;
    }
}
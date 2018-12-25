package DesignPatterns.StructuralPattern;

import java.util.HashMap;

interface Shapes {
    void draw();
}

class Circles implements Shapes {

    private String color;
    private int x, y, radius;

    public Circles(String color) {
        this.color = color;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public void draw() {

        System.out.println("Circle: draw() [Color: " + color + " x: " + x + " y: " + y + " radius: " + radius + " ]");
    }
}

class ShapeFactory {
    private static final HashMap circleMap = new HashMap();

    public static Shapes getCircles(String color) {
        Circles circles = (Circles) circleMap.get(color);
        if (circles == null) {
            circles = new Circles(color);
            circleMap.put(color, circles);
            System.out.println("creating circle of color: " + color);
        }

        return circles;
    }
}

public class FlyweightPattern {

    private static final String colors [] = {"Red","Green", "Blue","White", "Bllack"};

    private static String getRandomColor(){
        return colors[(int)Math.random()*colors.length];
    }

    private static int getRandomX(){
        return (int)(Math.random()*100);
    }

    private static int getRandomY(){
        return (int)(Math.random()*100);
    }

    public static void main(String[] args) {
        for(int i = 0;  i <20; i++){
            Circles circles = (Circles) ShapeFactory.getCircles(getRandomColor());
            circles.setX(getRandomX());
            circles.setY(getRandomY());
            circles.setRadius(100);
            circles.draw();
        }
    }

}

package DesignPatterns;

//-------------------------------step 1---------------------------------
interface DrawAPI {
    public void drawCircle(int radius, int x, int y);
}
//-------------------------------step 2---------------------------------
class RedCircle implements DrawAPI {
    public void drawCircle(int radius, int x, int y) {
        System.out.println("Drawing Red Circle: " + radius + " | " + x + " | " + y);
    }
}

class GreenCircle implements DrawAPI {
    public void drawCircle(int radius, int x, int y) {
        System.out.println("Draw Green Circle: " + radius + " | " + x + " | " + y);
    }
}
//-------------------------------step 3---------------------------------
abstract class Shape {
    protected DrawAPI drawAPI;
    protected Shape(DrawAPI drawAPI) {
        this.drawAPI = drawAPI;
    }
    public abstract void draw();
}
//-------------------------------step 4---------------------------------
class Circler extends Shape{
    private int x, y , radius;
    public Circler(int x, int y, int radius, DrawAPI drawAPI){
        super(drawAPI);
        this.x = x;
        this.y = y;
        this.radius = radius;
    }
    public void draw() {
     drawAPI.drawCircle(radius,x,y);
    }
}
//-------------------------------step 5---------------------------------
public class BridgePattern {
    public static void main(String[] args) {
        Shape redCircle  = new Circler(100, 100, 10, new RedCircle());
        Shape greenCircle = new Circler(100, 100, 20, new GreenCircle());

        redCircle.draw();
        greenCircle.draw();
    }
}
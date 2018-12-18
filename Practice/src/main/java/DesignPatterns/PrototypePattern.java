package DesignPatterns;


import java.util.Hashtable;

public class PrototypePattern {
    public static void main(String[] args) {
        ShapeCache.loadCache(); // calls the method and saves the object as a key-value pair in the HashMap

        shape clonedShape1 = (shape) ShapeCache.getShape("1"); //
        System.out.println("Shape : "+clonedShape1.getType());

        shape clonedShape2 = (shape)ShapeCache.getShape("2");
        System.out.println("Shape : "+ clonedShape2.getType());

        shape clonedShape3 = (shape)ShapeCache.getShape("3");
        System.out.println("Shape : "+ clonedShape3.getType());
    }
}

abstract class shape implements Cloneable {
    private String id;
    protected String type;

    abstract void draw();

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public Object clone() {
        Object clone = null;

        try {
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }
}

class Rectangle extends shape {
    public Rectangle() {
        type = "Rectangle";
    }

    public void draw() {
        System.out.println("Inside Rectange :: draw() method");
    }
}

class Square extends shape{
    public Square(){
        type = "Square";
    }
    public void draw(){
        System.out.println("Inside Square :: draw() method");
    }
}

class Circle extends shape{
    public Circle(){
        type = "Circle";
    }
    public void draw(){
        System.out.println("Inside Circle :: draw() method");
    }
}


class ShapeCache{
    private static Hashtable<String,shape> shapeMap = new Hashtable<>();

    public static void loadCache(){
        Circle circle  = new Circle();
        circle.setId("1");
        shapeMap.put(circle.getId(),circle);

        Square square = new Square();
        square.setId("2");
        shapeMap.put(square.getId(), square);

        Rectangle rectangle = new Rectangle();
        rectangle.setId("3");
        shapeMap.put(rectangle.getId(),rectangle);
    }

    public static shape getShape(String shapeId){
        shape cachedShape = shapeMap.get(shapeId);
        return (shape) cachedShape.clone();
    }

}


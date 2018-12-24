package DesignPatterns.StructuralPattern;


interface shape {
    void draw();
}
//---------------------------------------------
class Rectangle implements shape {
    @Override
    public void draw() {
        System.out.println("Shape: Rectangle");
    }
}

class Circle implements shape {
    @Override
    public void draw() {
        System.out.println("Shape: Circle");
    }
}
//-------------------------------------------------------
abstract class ShapeDecorator implements shape {
    protected shape DS;

    public ShapeDecorator(shape decoratedShape) {
        this.DS = decoratedShape;
    }

    @Override
    public void draw() {
        DS.draw();
    }
}
//----------------------------------------------------
class RedShapeDecorator extends ShapeDecorator {
    public RedShapeDecorator(shape decoratedShape) {

        //ShapeDecorator Does not have a default constructor
        super(decoratedShape);
    }

    public void draw() {
        DS.draw();
        setRedBorder(DS);
    }

    private void setRedBorder(shape decoratedShape) {
        System.out.println("Border color: red");
    }
}

public class DecoratorPattern {
    public static void main(String[] args) {
        shape circle = new Circle();
        shape redCircle = new RedShapeDecorator(new Circle());
        shape redRectangle = new RedShapeDecorator(new Rectangle());
        System.out.println("Circle with normal borders");
        circle.draw();
        System.out.println("\n Circle with red border");
        redCircle.draw();
        System.out.println("\n Rectangle with red border");
        redRectangle.draw();
    }
}
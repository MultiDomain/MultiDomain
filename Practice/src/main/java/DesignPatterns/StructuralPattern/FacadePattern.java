package DesignPatterns.StructuralPattern;

interface Shape1{
    void draw();
}

class Rectangle1 implements Shape1{
    @Override
    public void draw() {
        System.out.println("Rectangle :: Draw");
    }
}

class Square1 implements Shape1{
    @Override
    public void draw() {
        System.out.println("Square:: Draw");
    }
}

class Circle1 implements Shape1{
    @Override
    public void draw() {
        System.out.println("Circle:: Draw");
    }
}

class ShapeMaker{
    private Shape1 circle, rectangle, square;

    public ShapeMaker(){
        circle = new Circle1();
        rectangle = new Rectangle1();
        square = new Square1();
    }

    public void drawCircle(){
        circle.draw();
    }
    public void drawRecangle(){
        rectangle.draw();
    }
    public void drawSquare(){
        square.draw();
    }
}

public class FacadePattern {
    public static void main(String[] args) {
        ShapeMaker shapeMaker = new ShapeMaker();
        shapeMaker.drawCircle();
        shapeMaker.drawRecangle();
        shapeMaker.drawSquare();
    }
}

package DesignPatterns.StructuralPattern;

public class DecoratorPattern {

}

interface shape{
    void draw();
}

class Rectangle implements shape{
    @Override
    public void draw() {
        System.out.println("Shape: Rectangle");
    }
}

class Circle implements shape{
    @Override
    public void draw() {
        System.out.println("Shape: Circle");
    }
}


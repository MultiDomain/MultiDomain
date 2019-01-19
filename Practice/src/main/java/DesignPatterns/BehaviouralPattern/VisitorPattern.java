package DesignPatterns.BehaviouralPattern;


interface ComputerPart {
    public void acept(ComputerPartVisitor copmuterPartVisitor);
}

interface ComputerPartVisitor {
    public void visit(Computer computer);

    public void visit(Mouse mouse);

    public void visit(Keyboard keyboard);

    public void visit(Monitor monitor);
}

class Keyboard implements ComputerPart {
    @Override
    public void acept(ComputerPartVisitor copmuterPartVisitor) {
        copmuterPartVisitor.visit(this);
    }
}

class Monitor implements ComputerPart {
    @Override
    public void acept(ComputerPartVisitor copmuterPartVisitor) {
        copmuterPartVisitor.visit(this);
    }
}

class Mouse implements ComputerPart {
    @Override
    public void acept(ComputerPartVisitor copmuterPartVisitor) {
        copmuterPartVisitor.visit(this);
    }
}

class Computer implements ComputerPart {

    ComputerPart[] parts;

    public Computer() {
        parts = new ComputerPart[]{new Mouse(), new Keyboard(), new Monitor()};
    }

    @Override
    public void acept(ComputerPartVisitor copmuterPartVisitor) {

    }
}

class ComputerPartDisplayVisitor implements ComputerPartVisitor {

    @Override
    public void visit(Computer computer) {
        System.out.println("Displaying Computer");
    }

    @Override
    public void visit(Mouse mouse) {
        System.out.println("Displaying Mouse");
    }

    @Override
    public void visit(Keyboard keyboard) {
        System.out.println("Displaying Keyboard");
    }

    @Override
    public void visit(Monitor monitor) {
        System.out.println("Displaying Monitor");
    }
}


public class VisitorPattern {
    public static void main(String[] args) {
        ComputerPart computer = new Computer();
        computer.acept(new ComputerPartDisplayVisitor());
    }
}
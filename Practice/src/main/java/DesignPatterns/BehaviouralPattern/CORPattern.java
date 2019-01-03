package DesignPatterns.BehaviouralPattern;

interface Chain {
    public abstract void setNext(Chain nextInChain);

    public abstract void process(Number req);
}

class Number {
    private int number; // 90

    public Number(int number) { //90
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}

class NegativeProcessor implements Chain {
    private Chain nextInChain;

    public void setNext(Chain c) {//c2
        nextInChain = c;
    }

    @Override
    public void process(Number req) {
        if (req.getNumber() < 0) {
            System.out.println("Negative Processor : " + req.getNumber());
        } else {
            nextInChain.process(req);
        }
    }

}

class ZeroProcess implements Chain {
    private Chain nextInChain;

    @Override
    public void setNext(Chain c) {//c3
        nextInChain = c;
    }

    @Override
    public void process(Number req) {

        if (req.getNumber() == 0) {
            System.out.println("Zero Processor : " + req.getNumber());
        } else {
            nextInChain.process(req);
        }
    }
}

class PositiveProcessor implements Chain {
    private Chain nextInChain;

    @Override
    public void setNext(Chain c) { //c4
        nextInChain = c;
    }

    @Override
    public void process(Number req) {

        if (req.getNumber() > 0) {
            System.out.println("Positive Processor : " + req.getNumber());
        } else {
            nextInChain.process(req);
        }
    }
}

public class CORPattern {
// Chain of Responsibility
    public static void main(String[] args) {
        Chain c1 = new NegativeProcessor();
        Chain c2 = new ZeroProcess();
        Chain c3 = new PositiveProcessor();

        c1.setNext(c2);
        c2.setNext(c3);

        c1.process(new Number(90));
        c1.process(new Number(-50));
        c1.process(new Number(0));
        c1.process(new Number(91));
    }

}

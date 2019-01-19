package DesignPatterns.BehaviouralPattern;

interface Strategy{
    public int doOperation(int num1, int num2);
}

class OperationAdd implements Strategy{
    @Override
    public int doOperation(int num1, int num2) {
        return num1 + num2;
    }
}

class OperationSubtract implements Strategy{
    @Override
    public int doOperation(int num1, int num2) {
        return num1 - num2;
    }
}

class OperationMultiply implements Strategy{
    @Override
    public int doOperation(int num1, int num2) {
        return num1 * num2;
    }
}

class Context1 {
    private Strategy strategy;

    public Context1 (Strategy strategy){
        this.strategy = strategy;
    }
    public int executeStrategy(int num1, int num2){
        return strategy.doOperation(num1, num2);
    }
}

public class StrategyPattern {
    public static void main(String[] args) {
        Context1 context = new Context1(new OperationAdd());
        System.out.println(context.executeStrategy(10,5));

        context = new Context1(new OperationMultiply());
        System.out.println(context.executeStrategy(12,6));

        context = new Context1(new OperationSubtract());
        System.out.println(context.executeStrategy(15,5));
    }
}

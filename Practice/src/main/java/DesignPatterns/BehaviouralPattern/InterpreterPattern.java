package DesignPatterns.BehaviouralPattern;

interface Expression {
    public boolean interpret(String Context);
}

class TerminalExpression implements Expression {

    private String data;

    public TerminalExpression(String data) {
        this.data = data;
    }

    @Override
    public boolean interpret(String Context) {

        if (Context.contains(data)) {
            return true;
        }

        return false;
    }
}

class OrExpression implements Expression {
    private Expression exp1;
    private Expression exp2;

    public OrExpression(Expression exp1, Expression exp2) {
        this.exp1 = exp1;
        this.exp2 = exp2;
    }

    @Override
    public boolean interpret(String Context) {
        return exp1.interpret(Context) || exp2.interpret(Context);
    }
}

class AndExpression implements Expression {
    private Expression exp1;
    private Expression exp2;

    public AndExpression(Expression exp1, Expression exp2) {
        this.exp1 = exp1;
        this.exp2 = exp2;

    }

    @Override
    public boolean interpret(String Context) {
        return exp1.interpret(Context) && exp2.interpret(Context);
    }

}


public interface InterpreterPattern {

    public static Expression getMaleExpression() {
        Expression robert = new TerminalExpression("Robert");
        Expression john = new TerminalExpression("John");

        return new OrExpression(robert, john);

    }

    public static Expression getMarriedWomanExpression(){
        Expression julie = new TerminalExpression("Julie");
        Expression married = new TerminalExpression("Married");

        return new AndExpression(julie, married);
    }

    public static void main(String[] args) {
        Expression isMale = getMaleExpression();
        Expression isMarriedWoman = getMarriedWomanExpression();

        System.out.println(isMale.interpret("John"));
        System.out.println(isMarriedWoman.interpret("Married Julie"));
    }
}

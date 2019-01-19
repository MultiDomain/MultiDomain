package DesignPatterns.BehaviouralPattern;

interface State {
    public void doActions(Context context);
}

class StartState implements State {
    public void doActions(Context context) {
        System.out.println("player is in start state");
        context.setState(this);
    }

    public String toString() {
        return "Start State";
    }
}

class StopState implements State {
    public void doActions(Context context) {
        System.out.println("Player is in stop state");
        context.setState(this);
    }

    public String toString() {
        return "Stop State";
    }
}

class Context {
    private State state;

    public Context() {
        state = null;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}

public class StatePattern {

    public static void main(String[] args) {
        Context context = new Context();

        StartState startState = new StartState();
        startState.doActions(context);

        System.out.println(context.getState().toString());

        StopState stopState = new StopState();
        stopState.doActions(context);

        System.out.println(context.getState().toString());
    }
}

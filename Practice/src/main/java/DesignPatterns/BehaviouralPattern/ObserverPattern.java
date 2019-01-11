package DesignPatterns.BehaviouralPattern;

import java.util.ArrayList;
import java.util.List;

class Subject {
    private List<Observer> observers = new ArrayList<>();
    private int state;

    public int getState(){
        return state;
    }

    public void setState(int state){
        this.state = state;
        notifyAllObservers();
    }

    public void attach (Observer observer){
        observers.add(observer);
    }

    public void notifyAllObservers(){
        for (Observer observer: observers){
            observer.update();
        }
    }
}

abstract class Observer{
    protected Subject subject;
    public abstract void update();
}

class BinaryObserver extends Observer{

    public BinaryObserver(Subject subject){
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        
    }
}

public class ObserverPattern {
}

package DesignPatterns.BehaviouralPattern;

import com.sun.xml.internal.org.jvnet.fastinfoset.sax.FastInfosetReader;

import java.util.ArrayList;
import java.util.List;

class Memento {
    private String state;
    public Memento(String state){
        this.state = state;
    }

    public String getState(){
        return state;
    }
}

class Originator {
    private String state;
    public void setState(String state){
        this.state = state;
    }
    public String getState(){
        return state;
    }

    public Memento saveStatetoMemory(){
        return new Memento(state);
    }

    public void getStateFormMemto(Memento memento){
        state = memento.getState();
    }

}

class CareTaker{
    private List<Memento> mementoList = new ArrayList<>();
    public void add(Memento state){
        mementoList.add(state);
    }
    public Memento get(int index){
        return  mementoList.get(index);
    }
}

public class MementoPattern {
    public static void main(String[] args) {
        Originator originator = new Originator();
        CareTaker careTaker = new CareTaker();

        originator.setState("State#1");
        originator.setState("State#2");
        careTaker.add(originator.saveStatetoMemory());

        originator.setState("State#3");
        careTaker.add(originator.saveStatetoMemory());

        originator.setState("State#4");
        System.out.println("Current State: " + originator.getState());

        originator.getStateFormMemto(careTaker.get(0));
        System.out.println("First State: "+ originator.getState());
        originator.getStateFormMemto(careTaker.get(1));
        System.out.println("Second State: "+originator.getState());

    }

}

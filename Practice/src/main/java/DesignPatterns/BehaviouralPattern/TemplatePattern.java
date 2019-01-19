package DesignPatterns.BehaviouralPattern;

abstract class Game{
    abstract void initialize();
    abstract void startPlay();
    abstract void endPlay();

    public final void play(){
        initialize();
        startPlay();
        endPlay();
    }
}

class cricket extends Game{
    @Override
    void initialize() {
        System.out.println("Cicket Initialized ");
    }

    @Override
    void startPlay() {
        System.out.println("Cricket match stated");
    }

    @Override
    void endPlay() {
        System.out.println("Cricket match ended");
    }
}

class football extends Game{
    @Override
    void initialize() {
        System.out.println("Football game Initialized");
    }

    @Override
    void startPlay() {
        System.out.println("Football match started");
    }

    @Override
    void endPlay() {
        System.out.println("Football match ended");
    }
}

public class TemplatePattern {
    public static void main(String[] args) {
        Game game = new cricket();
        game.play();
        game = new football();
        game.play();
    }
}

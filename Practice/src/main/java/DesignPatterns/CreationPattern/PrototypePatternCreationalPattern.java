package DesignPatterns.CreationPattern;

public class PrototypePatternCreationalPattern {
    public static void main(String[] args) {
        CloneFactory animalMaker = new CloneFactory(); // create object of CloneFactory
        Sheep sally = new Sheep(); // Create Object of Sheep
        Sheep cloneSheep = (Sheep) animalMaker.getClone(sally);

        System.out.println(sally);
        System.out.println(cloneSheep);
        System.out.println("Sally Hashcode:" + System.identityHashCode(System.identityHashCode(sally)));
        System.out.println("Clone Sheep:" + System.identityHashCode(System.identityHashCode(cloneSheep)));
    }
}

interface Animal extends Cloneable {

    public Animal makeCopy(); // Methods Type Animal

}

class Sheep implements Animal {

    public Sheep() {
        System.out.println("Sheep Created");
    }

    public Animal makeCopy() {

        System.out.println("Sheep is being cloned");

        Sheep sheepObj = null;

        try {
            sheepObj = (Sheep) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            System.out.println("Sheep was not cloned!");
        }

        return sheepObj;
    }

    public String toString() {
        return "The Sheep looks Good!";
    }
}

class CloneFactory {
    public Animal getClone(Animal AnimalSample) {
        return AnimalSample.makeCopy();
    }
}
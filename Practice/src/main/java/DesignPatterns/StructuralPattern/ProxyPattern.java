package DesignPatterns.StructuralPattern;

interface Image {
    void displey();
}

class RealImage implements Image {

    private String fileName;

    public RealImage(String fileName) {
        this.fileName = fileName;
        loadFromDisk(fileName);
    }

    @Override
    public void displey() {
        System.out.println("Displaying" + fileName);
    }

    private void loadFromDisk(String fileName) {
        System.out.println("Loading " + fileName);
    }
}

class ProxyImage implements Image {
    private RealImage realImage;
    private String fileName;

    public ProxyImage(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void displey() {
        if (realImage == null) {
            realImage = new RealImage(fileName);
        }
        realImage.displey();
    }
}

public class ProxyPattern {
    public static void main(String[] args) {
        Image image = new ProxyImage("test 10mb.jpg");

        image.displey();
        System.out.println("");
        image.displey();
    }
}

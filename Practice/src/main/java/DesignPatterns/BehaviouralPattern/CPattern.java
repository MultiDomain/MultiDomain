package DesignPatterns.BehaviouralPattern;


interface ElectronicDevice {
    public void on();

    public void off();

    public void volumeUp();

    public void volumeDown();
}

interface Command {
    public void execute();
}

class Television implements ElectronicDevice {

    private int Volume = 0;


    @Override
    public void on() {
        System.out.println("TV is ON ");
    }

    @Override
    public void off() {
        System.out.println("TV is OFF");
    }

    @Override
    public void volumeUp() {
        Volume++;
        System.out.println("Volume is UP");
    }

    @Override
    public void volumeDown() {
        Volume--;
        System.out.println("Volume is DOWN");
    }
}

class TurnTVOn implements Command {

    ElectronicDevice theDevice;

    public TurnTVOn(ElectronicDevice newDevice) {
        theDevice = newDevice;
    }

    @Override
    public void execute() {

        theDevice.on();
    }
}

class TurnTVOff implements Command {
    ElectronicDevice theDevice;

    public TurnTVOff(ElectronicDevice newDevice) {
        theDevice = newDevice;
    }

    @Override
    public void execute() {
        theDevice.off();
    }
}

class TurnTVUp implements Command {
    ElectronicDevice theDevice;

    public TurnTVUp(ElectronicDevice newDevice) {
        theDevice = newDevice;
    }

    @Override
    public void execute() {
        theDevice.volumeUp();
    }
}

class TurnTvDown implements Command {
    ElectronicDevice theDevice;

    public TurnTvDown(ElectronicDevice newDevice) {
        theDevice = newDevice;
    }

    @Override
    public void execute() {
        theDevice.volumeDown();
    }
}

class DeviceButton {
    Command theCommand;

    public DeviceButton(Command newCommand) {
        theCommand = newCommand;
    }

    public void press(){
        theCommand.execute();
    }
}

class TVRemote{
    public static ElectronicDevice getDevice(){
        return new Television();
    }
}


public class CPattern {

    public static void main(String[] args) {
        ElectronicDevice newDevice = TVRemote.getDevice();
        TurnTVOn onCommand = new TurnTVOn(newDevice);
        DeviceButton onPress = new DeviceButton(onCommand);
        onPress.press();

    }

}

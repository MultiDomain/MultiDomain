package DesignPatterns.BehaviouralPattern;

import com.gargoylesoftware.htmlunit.javascript.host.svg.SVGPathSegArcAbs;
import sun.nio.ch.AbstractPollArrayWrapper;
import sun.nio.ch.FileLockImpl;

abstract class AbstractLogger {
    public static int INFO = 1;
    public static int DEBUG = 2;
    public static int ERROR = 3;

    protected int level;

    protected AbstractLogger nextLogger;

    public void setNextLogger(AbstractLogger nextLogger) {
        this.nextLogger = nextLogger;
    }

    public void logMessage(int level, String message) {
        if (this.level <= level) {
            write(message);
        }
        if (nextLogger != null) {
            nextLogger.logMessage(level, message);
        }
    }

    abstract protected void write(String message);
}

class ConsoleLogger extends AbstractLogger{
    public ConsoleLogger(int level){
        this.level = level;
    }
    @Override
    protected void write(String message) {
        System.out.println("Standard Console :: Logger: "+ message);
    }
}

class ErrorLogger extends AbstractLogger{
    public ErrorLogger(int level){
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("File :: Logger: "+ message);
    }
}

class FileLogger extends AbstractLogger{
    public FileLogger(int level){
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("File :: Logger: "+ message);
    }
}


public class ChainOfResponsibilityPattern {
    private  static AbstractLogger getChainLogger(){
        AbstractLogger erroLogger = new ErrorLogger(AbstractLogger.ERROR);
        AbstractLogger fileLogger = new FileLogger(AbstractLogger.DEBUG);
        AbstractLogger consoleLogger = new ConsoleLogger(AbstractLogger.INFO);

        erroLogger.setNextLogger(fileLogger);
        fileLogger.setNextLogger(consoleLogger);

        return erroLogger;
    }

    public static void main(String[] args) {
        AbstractLogger loggerChain = getChainLogger();
        loggerChain.logMessage(AbstractLogger.INFO,"This is an information.");
        loggerChain.logMessage(AbstractLogger.DEBUG,"This is a debug level information.");
        loggerChain.logMessage(AbstractLogger.ERROR,"This is an error infromation.");
    }
}

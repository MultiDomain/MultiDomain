package MentoringPractice;

public class B extends A implements C{

    static {
        System.out.println("This is just a static block");
    }

    {
        System.out.println("This is an instance block");
    }

    int x = 10;

    B(){
       
    }

    void a() {

    }

    @Override
    String msg(String m) {
        return null;
    }

    @Override
    public void MyInterface() {

    }
}

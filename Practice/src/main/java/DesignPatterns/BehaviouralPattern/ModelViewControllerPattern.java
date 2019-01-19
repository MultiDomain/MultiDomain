package DesignPatterns.BehaviouralPattern;

class Student {
    private String rollNo, name ;

    public String getRollNo(){
        return rollNo;
    }

    public void setRollNo(String rollNo){
        this.rollNo = rollNo;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
         this.name = name;
    }
}

class StudentView{
    public void printStudentDetails(String studentName, String studentRollNo){
        System.out.println("Student");
        System.out.println("Name: "+studentName);
        System.out.println("Roll No: "+ studentRollNo);
    }
}

class StudentController{
    private Student model;
    private StudentView view;

    public StudentController(Student model, StudentView view){
        this.model = model;
        this.view = view;
    }

    public void setStudentName(String name ){
        model.setName(name);
    }

    public String getStudentName(){
        return model.getName();
    }

    public void setStudenetRollNo(String rollNo){
         model.setRollNo(rollNo);
    }

    public String getStudentRollNo(){
        return model.getRollNo();
    }

    public void updateView(){
        view.printStudentDetails(model.getName(), model.getRollNo());
    }
}

public class ModelViewControllerPattern {

    private static Student retrieveStudentFromDatabase(){
        Student student = new Student();
        student.setName("Robert");
        student.setRollNo("10");
        return student;
    }

    public static void main(String[] args) {
        Student model = retrieveStudentFromDatabase();
        StudentView view = new StudentView();
        StudentController controller = new StudentController(model, view);
        controller.updateView();
        controller.setStudentName("John");
        controller.updateView();
    }
}

package DesignPatterns.CreationPattern;

import java.util.ArrayList;
import java.util.List;

//------------------------------Step 1----------------------------
class Person {
    private String name, gender, maritalStatus;

    public Person(String name, String gender, String maritalStatus) {
        this.name = name;
        this.gender = gender;
        this.maritalStatus = maritalStatus;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }
}

//------------------------------Step 2----------------------------
interface Criteria {
    public List<Person> meetCriteria(List<Person> personList);
}

//------------------------------Step 3----------------------------
class CriteriaMale implements Criteria {

    @Override
    public List<Person> meetCriteria(List<Person> personList) {
        List<Person> malePerson = new ArrayList<Person>();
        for (Person person : personList) {
            if (person.getGender().equalsIgnoreCase("male")) {
                malePerson.add(person);
            }
        }
        return malePerson;
    }
}

class CriteriaFemale implements Criteria {

    @Override
    public List<Person> meetCriteria(List<Person> personList) {
        List<Person> femalePerson = new ArrayList<Person>();
        for (Person person : personList) {
            if (person.getGender().equalsIgnoreCase("female")) {
                femalePerson.add(person);
            }
        }
        return femalePerson;
    }
}

class CriteriaSingle implements Criteria {

    @Override
    public List<Person> meetCriteria(List<Person> personList) {
        List<Person> singlePerson = new ArrayList<Person>();
        for (Person person : personList) {
            if (person.getMaritalStatus().equalsIgnoreCase("single")) {
                singlePerson.add(person);
            }
        }
        return singlePerson;
    }
}

class AndCriteria implements Criteria {
    private Criteria criteria;
    private Criteria otherCriteria;

    public AndCriteria(Criteria criteria, Criteria otherCriteria) {
        this.criteria = criteria;
        this.otherCriteria = criteria;
    }


    @Override
    public List<Person> meetCriteria(List<Person> personList) {
        List<Person> firstcriteriaPersons = criteria.meetCriteria(personList);
        return otherCriteria.meetCriteria(firstcriteriaPersons);
    }
}

class OrCriteria implements Criteria {
    private Criteria criteria;
    private Criteria otherCriteria;

    public OrCriteria(Criteria criteria, Criteria otherCriteria) {
        this.criteria = criteria;
        this.otherCriteria = otherCriteria;
    }

    @Override
    public List<Person> meetCriteria(List<Person> personList) {
        List<Person> firstCriteriaItem = criteria.meetCriteria(personList);
        List<Person> otherCriteriaItem = criteria.meetCriteria(personList);

        for (Person person : otherCriteriaItem) {
            if (!firstCriteriaItem.contains(person)) {
                firstCriteriaItem.add(person);
            }
        }
        return firstCriteriaItem;
    }
}
//------------------------------Step 4----------------------------


public class FilterPattern {
    public static void main(String[] args) {
        List<Person> persons = new ArrayList<Person>();
        persons.add(new Person("Robert", "male", "single"));
        persons.add(new Person("john", "male", "married"));
        persons.add(new Person("Laura", "female", "married"));
        persons.add(new Person("Diana", "Female", "single"));
        persons.add(new Person("mike", "male", "single"));
        persons.add(new Person("Bobb", "male", "single"));

        Criteria male = new CriteriaMale();
        Criteria female = new CriteriaFemale();
        Criteria single = new CriteriaSingle();
        Criteria singleMale = new AndCriteria(single, male);
        Criteria singleFemale = new OrCriteria(single, female);

        System.out.println("\nMale: ");
        printPerson(male.meetCriteria(persons));
        System.out.println("\nFemale: ");
        printPerson(female.meetCriteria(persons));
        System.out.println("\nSingle Male: ");
        printPerson(singleMale.meetCriteria(persons));
        System.out.println("\nSingle Female: ");
        printPerson(singleFemale.meetCriteria(persons));

    }

    public static void printPerson(List<Person> persons) {
        for (Person person : persons) {

            System.out.println("Person: " + person.getName() + " Gender" + person.getGender() + " Marital Status" + person.getMaritalStatus());
        }
    }
}

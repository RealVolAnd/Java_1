package Homework_6;

public class Dog extends Animal {

    Dog(String name, Gender gender, String color, int runlimit, int swimlimit, float jumplimit) {
        super(name, gender, color, runlimit, swimlimit, jumplimit);
    }

    @Override
    public String getGender() {
        return (super.getGender().equals("MALE") ? "кобель" : "сука");
    }

}

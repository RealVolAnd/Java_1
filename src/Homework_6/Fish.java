package Homework_6;

public class Fish extends Animal {

    Fish(String name, Gender gender, String color, int runlimit, int swimlimit, float jumplimit) {
        super(name, gender, color, runlimit, swimlimit, jumplimit);
    }

    @Override
    public String getGender() {
        return (super.getGender().equals("MALE") ? "рыба" : "рыбка");
    }
}

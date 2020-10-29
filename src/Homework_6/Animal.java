package Homework_6;

public abstract class Animal implements Runnable, Swimmable, Jumpable {
    private String name;
    private Gender gender;
    private String color;
    private int runLimit;
    private int swimLimit;
    private float jumpLimit;

    Animal(String name, Gender gender, String color, int runlimit, int swimlimit, float jumplimit) {
        this.name = name;
        this.gender = gender;
        this.color = color;
        this.runLimit = runlimit;
        this.swimLimit = swimlimit;
        this.jumpLimit = jumplimit;
    }

    public String getName() {
        return this.name;
    }

    public String getGender() { return this.gender.asString; }

    public String getColor() {
        return this.color;
    }


    public boolean run(int len) { return (len <= runLimit); }

    public boolean swim(int len) {
        return (len <= swimLimit);
    }

    public boolean jump(float height) {
        return (height <= jumpLimit);
    }

}

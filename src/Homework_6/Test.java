package Homework_6;

public class Test {
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String BOLD = "\033[1m";
    private static final String RESET= "\033[0m";
    private static int animalCount=7;                           //Количество участников
    private static Animal[] animals = new Animal[animalCount];
    private static int runDistance = 99;                       // Дистанция забега
    private static int swimDistance = 10;                        //Дистанция заплыва
    private static float jumpLevel = 1;                         // Высота планки


    public static void main(String[] args) {

        animals[0] = new Cat("Мурзик", Gender.MALE, "Черный", 200, 0, 2);
        animals[1] = new Cat("Мурзик", Gender.MALE, "Серый", 200, 0, 2);
        animals[2] = new Cat("Мурка", Gender.FEMALE, "Черная", 100, 0, 2.5f);
        animals[3] = new Dog("Барбос", Gender.MALE, "Черный", 500, 10, 0.5f);
        animals[4] = new Dog("Тузик", Gender.MALE, "Серый", 300, 5, 0.5f);
        animals[5] = new Dog("Дина", Gender.FEMALE, "Черная", 400, 20, 1);
        animals[6] = new Fish("Голди", Gender.FEMALE, "Золотая", 0, 200, 1);

        startCompetition();
    }

    private static void startCompetition(){
        String[] compTitle = {"Результаты забега на " + runDistance + "м:", "\nРезультаты заплыва на " + swimDistance + "м:", "\nРезультаты прыжков через препятствие " + jumpLevel + "м:"};

        for (int i = 0; i < 3; i++) {
            System.out.println(BOLD+compTitle[i]+RESET);
            for (Animal animal : animals) {
                System.out.print(animal.getColor() + " " + animal.getGender() + " " + animal.getName());
                if (i == 0) {
                    System.out.println(" \t\t\trun:" + (animal.run(runDistance)?GREEN+"true"+RESET:RED+"false"+RESET));
                } else if (i == 1) {
                    System.out.println(" \t\t\tswim:" + (animal.swim(swimDistance)?GREEN+"true"+RESET:RED+"false"+RESET));
                } else {
                    System.out.println(" \t\t\tjump:" + (animal.jump(jumpLevel)?GREEN+"true"+RESET:RED+"false"+RESET));
                }
            }
        }
    }
}

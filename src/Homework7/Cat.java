package Homework7;

public class Cat {

    private static int index = 0;
    private String name;
    private int appetiteMax;
    private int appetiteCurrent;

    public Cat(int maxAppetite) {
        name = "Кот " + (++index);
        appetiteMax = maxAppetite;
        appetiteCurrent = (int) (Math.random() * appetiteMax);
    }

    public String getName() {
        return name;
    }

    public int getAppetit() {
        return (int) this.appetiteCurrent;
    }

    public int eatFromPlate(Plate plate) {
        int plateFood = plate.getFoodLevel();
        int result;

        if (plateFood >= appetiteCurrent) {
            result = plateFood - appetiteCurrent;
            appetiteCurrent = 0;
        } else {
            appetiteCurrent -= plateFood;
            result = 0;
        }
        return result;
    }

    @Override
    public String toString() {
        int happyLevel = (int) (100 - Math.round((float) appetiteCurrent / appetiteMax * 100));
        return (name + ", аппетит:" + appetiteCurrent + ", доволен на " + happyLevel + "%, ");
    }
}

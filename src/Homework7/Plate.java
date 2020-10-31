package Homework7;

public class Plate {
    private static int plateIndex = 0;
    private String plateName;
    private int plateMaxVolume;
    private int plateFoodLevel;


    public Plate(int maxVolume) {
        plateName = "Миска " + (++plateIndex);
        plateMaxVolume = maxVolume;
        plateFoodLevel = maxVolume;
    }

    public String getPlateName() {
        return plateName;
    }

    public int getFoodLevel() {
        return plateFoodLevel;
    }

    public void setFoodLevel(int foodLevel) {
        plateFoodLevel = foodLevel;
    }

    public void addFoodLevel(int foodLevel) {
        plateFoodLevel += foodLevel;
        System.out.println("+ ДОБАВЛЕНО " + foodLevel + " ПОРЦИЙ КОРМА В " + plateName + ".");
    }

    @Override
    public String toString() {
        return (plateName + ", объем:" + plateMaxVolume + ", порций в миске: " + plateFoodLevel);
    }
}

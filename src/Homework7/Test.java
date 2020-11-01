package Homework7;

import java.util.ArrayList;
import java.util.Comparator;

public class Test {
    private static final String BOLD = "\033[1m";
    private static final String RESET = "\u001B[0m";
    /*
    Принимаем следующие условия:
    1. Количество корма и аппетит котов измеряются порциями корма.
    2. Коты по очереди подходят к мискам. Если в миске закончился корм, то кот переходит к следующей миске и.т.д пока
    не насытится или пока не закончатся миски с кормом. Если корм закончится раньше, чем голодные коты, то
    кормом наполняется столько первых мисок, сколько голодных котов осталось.
    3. Для упрощения мы можем задавать только количество мисок и их максимальный объем.
    4. Все миски изначально заполняются кормом одинаково и на полный объем.
     */
    static final int CATS_COUNT = 5;// Устанавливаем количество котов, которых будем откармливать
    static final int CAT_MAX_APPETITE = 10;//0-кот сыт,10-высший уровень голода при котором кот способен добежать до миски.

    static final int PLATES_COUNT = 5;// Задаем количество мисок. Может больше нет в наличии.
    static final int PLATE_MAX_VOLUME = 5;// Столько порций может вместить одна миска

    static final int PRINT_CATS_INIT_STATE = 0;
    static final int PRINT_CATS_CURRENT_STATE = 1;
    static final int PRINT_CATS_ACTIONS = 2;
    static final int PRINT_PLATES_CURRENT_STATE = 3;

    static ArrayList<Cat> cats = new ArrayList<>();
    static Plate[] plates = new Plate[PLATES_COUNT];

    public static void main(String[] args) {
        catsListInit(); // Запускаем в дом котов....
        platesArrayInit(); // Достаем и наполняем миски....
        printInfo(PRINT_CATS_INIT_STATE);
        printInfo(PRINT_CATS_ACTIONS);
        feedFood(); // Кормим котов пока всех не накормим...
        printInfo(PRINT_CATS_CURRENT_STATE);
        printInfo(PRINT_PLATES_CURRENT_STATE);
    }

    private static void feedFood() {
        //Перебираем котов....
        for (int i = 0; i < CATS_COUNT; i++) {
            int j = 0;
            while (cats.get(i).getAppetit() > 0) {// Если есть голодные коты...
                // Проверяем не закончился ли корм в мисках раньше чем накормили всех котов
                if (j == PLATES_COUNT) {
                    j = 0;
                    /*
                     Если остались голодные коты, то каждому из них по полной миске.
                     А если миска всего одна, то наполняем ее снова.
                     */
                    if(CATS_COUNT - i<=PLATES_COUNT){

                        for (int k = 0; k < (CATS_COUNT - i); k++) {
                            plates[k].addFoodLevel(PLATE_MAX_VOLUME);
                        }

                    } else {
                        for (int k = 0; k < (PLATES_COUNT); k++) {
                            plates[k].addFoodLevel(PLATE_MAX_VOLUME);
                        }
                    }

                }
                //Кот ищет первую не пустую миску....
                if (plates[j].getFoodLevel() > 0) {
                    /*
                    Кот ест из миски сколько ему нужно.
                    Если ему мало - переходит к следующей миске.
                    И так пока не наестся.
                     */
                    plates[j].setFoodLevel(cats.get(i).eatFromPlate(plates[j]));
                    //Мы контролируем процесс...
                    System.out.println("- " + cats.get(i).getName() + " поел из " + plates[j].getPlateName() + ". Остаток в " + plates[j].getPlateName() + ": " + plates[j].getFoodLevel());
                }
                j++;
            }
        }
    }

    private static void catsListInit() {
        /*
        Создаем список котов с разными аппетитами
         */
        for (int i = 0; i < CATS_COUNT; i++) {
            cats.add(new Cat(CAT_MAX_APPETITE));
        }
        /*
        Сортируем котов, потому что самые голодные побегут первыми к мискам.)
         */
        cats.sort(Comparator.comparingInt(Cat::getAppetit).reversed());
    }

    private static void platesArrayInit() {
        /*
        Достаем миски и заполняем их кормом
         */
        for (int i = 0; i < PLATES_COUNT; i++) {
            plates[i] = new Plate(PLATE_MAX_VOLUME);
        }
    }

    private static void printInfo(int action) {
        switch (action) {

            case PRINT_CATS_INIT_STATE:
                System.out.println(BOLD + "ИСХОДНОЕ СОСТОЯНИЕ КОТОВ:" + RESET);
                for (Cat theCat : cats) {
                    System.out.println(theCat.toString());
                }
                break;
            case PRINT_CATS_CURRENT_STATE:
                System.out.println(BOLD + "\nСОСТОЯНИЕ КОТОВ ПОСЛЕ ЕДЫ:" + RESET);
                for (Cat theCat : cats) {
                    System.out.println(theCat.toString());
                }
                break;
            case PRINT_CATS_ACTIONS:
                System.out.println(BOLD + "\nКОРМЛЕНИЕ КОТОВ:" + RESET);
                break;
            case PRINT_PLATES_CURRENT_STATE:
                System.out.println(BOLD + "\nСОСТОЯНИЕ МИСОК:" + RESET);
                for (Plate thePlate : plates) {
                    System.out.println(thePlate.toString());
                }
                break;
            default:
                break;
        }
    }
}

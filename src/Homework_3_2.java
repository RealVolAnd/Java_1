import java.util.ArrayList;

public class Homework_3_2 {
    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";
    public static final String BOLD = "\033[1m";
    public static final String REGUL= "\033[0m";

    public static final String[] words = {"apple", "orange", "lemon", "banana",
            "apricot", "avocado", "broccoli", "carrot", "cherry", "garlic",
            "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut",
            "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};
    public static String[] wordField=new String[15];
    public static String word="";
    public static int wordLen=0;
    public static ArrayList<String> tmpArray;

    public static void main(String[] args) {

       startTheGame();


    }

    private static void startTheGame(){
        int randomArrayIndex=(int)(Math.random()*25);
        word=words[randomArrayIndex];
        wordLen=word.length();
        tmpArray=new ArrayList<>();

        clearWordField();

        fillTmpArray();

        drawHeader();

        drawWordField(0);

    }
    private static void drawWordField(int action){
        switch(action){
            case 0://Initial action
                for (int i = 0; i < 15; i++) wordField[i]="X";
                break;
            case 1://Update action
                break;
            default:
                break;
        }


        System.out.println();

    }


    private static void fillTmpArray(){
        for (int i = 0; i < words.length; i++) {
            tmpArray.add(words[i]);
        }
    }


    private static void drawHeader(){
        print("\u2554");
        drawHLine(30);
        print(BOLD+BLUE+" УГАДАЙ СЛОВО "+RESET+REGUL);
        drawHLine(30);
        println("\u2557");

        print("\u2551");
        print(BLACK+" Я загадаю слово. Вы должны угадать его выбирая из предложенных вариантов."+RESET);
        println("\u2551");

        print("\u2551");
        drawNSpaces(2);
        print(RED+" Вводить можно только номер варианта. Ошибка ввода закроет программу. "+RESET);
        drawNSpaces(2);
        println("\u2551");

        print("\u2551");
        drawNSpaces(25);
        print(BOLD+PURPLE+" СЛЕДИТЕ ЗА ПОДСКАЗКАМИ!"+RESET+REGUL);
        drawNSpaces(25);
        println("\u2551");

        print("\u255a");
        drawHLine(60);
        print(" (RealVolAnd) ");
        println("\u255d");
    }

    private static void print(String str){
        System.out.print(str);
    }

    private static void println(String str){
        System.out.println(str);
    }

    private static void drawHLine(int len){
        for (int i = 0; i < len; i++) {
            print("\u2550");
        }
    }
    private static void drawNSpaces(int len){
        for (int i = 0; i < len; i++) {
            print(" ");
        }
    }

}

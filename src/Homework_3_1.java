import java.util.Scanner;

public class Homework_3_1 {
    public static Scanner scan = new Scanner(System.in);
    private static int tryNum=3;
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


    public static void main(String[] args) {
        try {
            startTheGame();
            while (true) {

                println("Повторить игру еще раз? 1-да/0-нет:");
                checkAnswer(scan.nextInt());
            }
        }catch (Exception e){
            println(BOLD+RED+"Ошибка ввода. Программа завершает работу..."+RESET+REGUL);
            println("");
            System.exit(0);
        }
    }


    private static void startTheGame() throws Exception{
       int i=0;
        int num=100;
            int randomNum=(int)(Math.random()*9);

            drawHeader();

            println(YELLOW + "ПОЕХАЛИ !" + RESET);

            for (i = 3; i > 0; i--) {
                print(RESET);
                print("Попыток осталось - " + BOLD + GREEN + i + RESET + REGUL + ". Введите число от 0 до 9:");

                while(true){
                    num = scan.nextInt();
                    if(num>=0&&num<10) break;
                }
                print(BLUE);
                if (num < randomNum) {
                    println("Загаданое число больше.");
                } else if (num > randomNum) {
                    println("Загаданое число меньше.");
                } else {
                    break;
                }
            }
            print(RESET);
            drawHLine(76);
            println("");
            if (i > 0) {
                drawNSpaces(20);
                println(BOLD + YELLOW + "\u2b50 \u2b50 \u2b50 \u2b50" + GREEN + "  ПОЗДРАВЛЯЮ! ВЫ УГАДАЛИ !!! " + YELLOW + "\u2b50 \u2b50 \u2b50 \u2b50" + RESET + REGUL);
            } else {
                println("\u263a \u263a \u263a "+BOLD + RED + "НА ЭТОТ РАЗ НЕ ПОВЕЗЛО. НЕ РАССТРАИВАЙТЕСЬ. ПОПОРБУЙТЕ ЕЩЕ РАЗ." + RESET + REGUL+" \u263a \u263a \u263a");
            }
            drawHLine(31);
            print(BOLD + BLACK + " ИГРА ОКОНЧЕНА " + RESET + REGUL);
            drawHLine(30);
            println("");
            println("");

    }



    private static void drawHeader(){
        print("\u2554");
        drawHLine(30);
        print(BOLD+BLUE+" УГАДАЙ ЧИСЛО "+RESET+REGUL);
        drawHLine(30);
        println("\u2557");

        print("\u2551");
        drawNSpaces(3);
        print(BLACK+" Я загадаю число от 0 до 9. У вас будет 3 попытки чтобы его угадать."+RESET);
        drawNSpaces(3);
        println("\u2551");

        print("\u2551");
        print(RED+" Вводить можно только цифры. Ошибка ввода приведет к завершению программы "+RESET);
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

    private static void checkAnswer(int answer) throws Exception{
        if(answer==1) {
            startTheGame();
        }else if(answer==0){
            System.exit(0);
        }else{
            println(RED+"Простите, ответ неразборчив. Введите 1 чтобы продолжить игру, или 0 для выхода"+RESET);
        }
    }
}

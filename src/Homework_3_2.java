
import java.util.ArrayList;
import java.util.Scanner;

public class Homework_3_2 {
    private static final String RESET = "\u001B[0m";
    private static final String BLACK = "\u001B[30m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";
    private static final String PURPLE = "\u001B[35m";
    private static final String CYAN = "\u001B[36m";
    private static final String WHITE = "\u001B[37m";
    private static final String BOLD = "\033[1m";
    private static final String REGUL= "\033[0m";

    private static final String[] words = {"apple", "orange", "lemon", "banana",
      "apricot", "avocado", "broccoli", "carrot", "cherry", "garlic",
      "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut",
      "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};

    private static char[] wordField=new char[15];
    private static String word="";
    private static ArrayList<String> tmpArray;
    private static int tryCount=0;

    public static void main(String[] args) {

       startTheGame();


    }

    private static void startTheGame(){

        Scanner scan = new Scanner(System.in);
        int randomArrayIndex=(int)(Math.random()* words.length);//Получаем случайный индекс массива слов
        word=words[randomArrayIndex];//Загадываем слово
        tmpArray=new ArrayList<>();//Временный массив с возможностью удаления со сдвигом

        fillTmpArray();//Заполняем временный массив знаками словами из основного

        drawHeader();//Рисуем заголовок

        resetWordField();//Заполняем игровое поле знаками '?'

        while(tmpArray.size()>0){// В цикле выбираем вариант, проверяем и удаляем из временного массива. Пока не закончатся слова (невозможно)

            tryCount++;

            drawWordField();

            println("");
            println(BOLD+"Варианты:"+REGUL);

            drawMenu();

            println(BOLD+"Введите номер варианта:"+REGUL);

            int select=0;
            while((select<1)||(select>tmpArray.size())){

                try {
                    select = scan.nextInt();
                }catch (Exception e){
                    showError();
                }
            }
            if(checkWord(select)) break;
        }
            showGreeting();//Поздравляем

    }
//Показываем ошибку и выходим
    private static void showError(){
        println("");
        println(BOLD+RED+"Ошибка ввода. Программа завершает работу..."+RESET+REGUL);
        println("");
        System.exit(0);
    }

//Проверяем слово. Если угадано все слово, то возвращаем true
    private static boolean checkWord(int pos){
        boolean b=true;
        String tmpStr=tmpArray.get(pos-1);

        for (int i = 0; i < word.length(); i++) {
            if(word.charAt(i)==tmpStr.charAt(i)){
                wordField[i]=word.charAt(i);
            }
            if(i==tmpStr.length()-1)break;
        }

        tmpArray.remove(pos-1);

        for (int i = 0; i < word.length(); i++) {
            if(wordField[i]=='?') {
                b=false;
                break;
            }

        }
        return b;
    }
//Выводим варианты
    private static void drawMenu(){
        for (int i = 0; i < tmpArray.size(); i++) {
            println((i+1)+"."+tmpArray.get(i));
        }
    }
//Начальная установка игрового поля
    private static void resetWordField(){
        for (int i = 0; i < 15; i++) wordField[i]="?".charAt(0);
    }
//Перерисовываем игровое поле
    private static void drawWordField(){
        println("");
        println(BOLD+"ИГРОВОЕ ТАБЛО"+REGUL);

        drawNSpaces(7);
        print("\u250c");
        drawHLine1(59);
        print("\u2510");

        println("");
        drawNSpaces(7);

        for (int i = 0; i < wordField.length; i++) {
            if(i==0){
                print("\u2502 "+BOLD+((wordField[i]=='?')?BLACK:BLUE)+wordField[i]+RESET+REGUL);
            }else if(i==wordField.length-1){
                println(" \u2502 "+BOLD+((wordField[i]=='?')?BLACK:BLUE)+wordField[i]+RESET+REGUL+" \u2502");
            }else{
                print(" \u2502 "+BOLD+((wordField[i]=='?')?BLACK:BLUE)+wordField[i]+RESET+REGUL);
            }


        }
        drawNSpaces(7);
        print("\u2514");
        drawHLine1(59);
        print("\u2518");
    }
//Начальное заполнение временного массива
    private static void fillTmpArray(){
        for (int i = 0; i < words.length; i++) {
            tmpArray.add(words[i]);
        }
    }

 //Выводим поздравление
    private static void showGreeting(){
        print(RESET);
        drawWordField();
        println("");
        drawHLine2(76);
        println("");
        println("");
        drawNSpaces(20);
        println(BOLD + YELLOW + "\u2b50 \u2b50 \u2b50 \u2b50" + GREEN + "  ПОЗДРАВЛЯЮ! ВЫ УГАДАЛИ !!! " + YELLOW + "\u2b50 \u2b50 \u2b50 \u2b50" + RESET + REGUL);
        println("     Было загадано слово: "+BOLD + BLACK+word+RESET + REGUL+". Количество использованных попыток: "+BOLD + BLACK+tryCount+RESET + REGUL);
        println("");
        drawHLine2(31);
        print(BOLD + BLACK + " ИГРА ОКОНЧЕНА " + RESET + REGUL);
        drawHLine2(30);
        println("");
        println("");
    }
//Рисуем заголовок
    private static void drawHeader(){
        print("\u2554");
        drawHLine2(30);
        print(BOLD+BLUE+" УГАДАЙ СЛОВО "+RESET+REGUL);
        drawHLine2(30);
        println("\u2557");

        print("\u2551");
        print(BLACK+" Я загадаю слово. Вы должны угадать его выбирая из предложенных вариантов."+RESET);
        println("\u2551");

        print("\u2551");
        drawNSpaces(2);
        print(RED+" Вводить нужно только номер варианта. Ошибка ввода закроет программу. "+RESET);
        drawNSpaces(2);
        println("\u2551");

        print("\u2551");
        drawNSpaces(25);
        print(BOLD+PURPLE+" СЛЕДИТЕ ЗА ПОДСКАЗКАМИ!"+RESET+REGUL);
        drawNSpaces(25);
        println("\u2551");

        print("\u255a");
        drawHLine2(60);
        print(" (RealVolAnd) ");
        println("\u255d");
    }

 //Графические функции.
    private static void print(String str){
        System.out.print(str);
    }

    private static void println(String str){
        System.out.println(str);
    }

    private static void drawHLine1(int len){
        for (int i = 0; i < len; i++) {
            print("\u2500");
        }
    }
    private static void drawHLine2(int len){
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

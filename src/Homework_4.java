import java.util.Random;
import java.util.Scanner;

public class Homework_4 {

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

    private static final int FRAME_WIDTH= 77;
    private static final int ALIGN_LEFT=1;
    private static final int ALIGN_CENTER=0;

    private static final int SIZE = 5;
    private static final int DOTS_TO_WIN = 4;
    private static final char DOT_EMPTY = '•';
    private static final char DOT_X = 'X';
    private static final char DOT_O = 'O';

    private static char[][] map = new char[5][5];
    private static String[] vectorS;


    private static Scanner scan = new Scanner(System.in);
    private static Random rand = new Random();
    private static int x=0,y=0;


    public static void main(String[] args) {
        try {
            drawHeader();
            startTheGame();
            while (true) {
                println("Повторить игру еще раз? 1-да/0-нет:");
                checkAnswer(scan.nextInt());
            }
        }catch (Exception e){
            e.printStackTrace();
            println(BOLD+RED+"Ошибка ввода. Программа завершает работу..."+RESET+REGUL);
            println("");
            System.exit(0);
        }
    }



    private static void startTheGame(){
        String xy="";
        int result=99;
        clearMap();
        showMap(map);

        while(result==99){

            xy="";x=0;y=0;

            while(!checkXy(xy)){
                println("Введите координаты ХУ, (Х-строка,У-столбец)");
                xy=scan.nextLine();
            }
            map[x-1][y-1]='X';

            refreshVector();
            showMap(map);

            result=checkWinner();
            if(result!=99) break;
            aiTurn();
            if(result!=99) break;
        }

        showEndMessage(result);
    }

    private static void aiTurn(){

    }

    private static boolean checkXy(String xy){
        boolean b=false;

        try{
            x=Integer.parseInt((xy.substring(0,1)));
            y=Integer.parseInt((xy.substring(1)));
            if((x>0&&x<=SIZE)&&(y>0&&y<=SIZE)) b=true;
            if((map[x-1][y-1])!=DOT_EMPTY) b=false;
        }catch (Exception e){
            b=false;
        }
        return b;
    }

    private static void refreshVector(){

        String sh0=new String(new char[]{map[0][0], map[0][1], map[0][2], map[0][3], map[0][4]});
        String sh1=new String(new char[]{map[1][0],map[1][1],map[1][2],map[1][3],map[1][4]});
        String sh2=new String(new char[]{map[2][0],map[2][1],map[2][2],map[2][3],map[2][4]});
        String sh3=new String(new char[]{map[3][0],map[3][1],map[3][2],map[3][3],map[3][4]});
        String sh4=new String(new char[]{map[4][0],map[4][1],map[4][2],map[4][3],map[4][4]});

        String sv0=new String(new char[]{map[0][0],map[1][0],map[2][0],map[3][0],map[4][0]});
        String sv1=new String(new char[]{map[0][1],map[1][1],map[2][1],map[3][1],map[4][1]});
        String sv2=new String(new char[]{map[0][2],map[1][2],map[2][2],map[3][2],map[4][2]});
        String sv3=new String(new char[]{map[0][3],map[1][3],map[2][3],map[3][3],map[4][3]});
        String sv4=new String(new char[]{map[0][4],map[1][4],map[2][4],map[3][4],map[4][4]});

        String sl1=new String(new char[]{map[1][0],map[2][1],map[3][2],map[4][3]});
        String sl2=new String(new char[]{map[0][0],map[1][1],map[2][2],map[3][3],map[4][4]});
        String sl3=new String(new char[]{map[0][1],map[1][2],map[2][3],map[3][4]});

        String sr1=new String(new char[]{map[3][0],map[2][1],map[1][2],map[0][3]});
        String sr2=new String(new char[]{map[4][0],map[3][1],map[2][2],map[1][3],map[0][4]});
        String sr3=new String(new char[]{map[4][1],map[3][2],map[2][3],map[1][4]});
        vectorS=new String[]{sh0,sh1,sh2,sh3,sh4,sv0,sv1,sv2,sv3,sv4,sl1,sl2,sl3,sr1,sr2,sr3};
    }


    private static int checkWinner(){
        int res=0;
        for (int i = 0; i <SIZE; i++) {
            for (int j = 0; j <SIZE ; j++) {
                if(map[i][j]==DOT_EMPTY) res=99;
            }
        }
        for (int i = 0; i < vectorS.length; i++) {
            if(vectorS[i].contains("XXXX")) res=1;
            if(vectorS[i].contains("OOOO")) res=-1;
        }
        return res;
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

    private static void showErrorAndExit(){
        println("");
        println(BOLD+RED+"Ошибка ввода. Программа завершает работу..."+RESET+REGUL);
        println("");
        System.exit(0);
    }

    private static void clearMap(){
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j]=DOT_EMPTY;
            }
        }
    }

    private static void showMap(char[][] arr) {

        println("\t\t\t\t\t\t  "+BOLD+"ИГРОВОЕ ПОЛЕ"+REGUL);
        println("\t\t\t\t\t "+BLUE+BOLD+"Y>"+REGUL+BLUE+"\t1\t2\t3\t4\t5"+RESET);
        println("\t\t\t\t  "+RED+BOLD+"X"+REGUL);

        for (int i = 0; i < arr.length; i++) {
            System.out.print("\t\t\t\t  "+RED+(i+1)+RESET+"\t");
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print("\t"+arr[i][j]);
            }
            System.out.println();
        }
        println("\t\t\t\t");
    }

    private static void showEndMessage(int whoWin){

        print(RESET);
        println("");
        drawHLine2(76);
        println("");
        println("");
        drawNSpaces(20);
        if(whoWin>0){
            println(BOLD + YELLOW + "\u2b50 \u2b50 \u2b50 \u2b50" + GREEN + "  ПОЗДРАВЛЯЮ! ВЫ УГАДАЛИ !!! " + YELLOW + "\u2b50 \u2b50 \u2b50 \u2b50" + RESET + REGUL);
        }else if(whoWin<0){
            println("\u263a \u263a \u263a "+BOLD + RED + "НА ЭТОТ РАЗ НЕ ПОВЕЗЛО. НЕ РАССТРАИВАЙТЕСЬ. ПОПОРБУЙТЕ ЕЩЕ РАЗ." + RESET + REGUL+" \u263a \u263a \u263a");
        }else{
            println("\u263a \u263a \u263a "+BOLD + YELLOW + "НИЧЬЯ. НЕ ПЛОХО. ПОПОРБУЙТЕ ЕЩЕ РАЗ." + RESET + REGUL+" \u263a \u263a \u263a");
        }
        println("");
        drawHLine2(31);
        print(BOLD + BLACK + " ИГРА ОКОНЧЕНА " + RESET + REGUL);
        drawHLine2(30);
        println("");
        println("");
    }



    private static void drawHeader(){
        print("\u2554");
        drawHLine2(30);
        print(BOLD+BLUE+" КРЕСТИКИ-НОЛИКИ "+RESET+REGUL);
        drawHLine2(30);
        println("\u2557");

        putTextToFrame("Я играю ноликами, вы - крестиками.",ALIGN_CENTER,BLACK);
        putTextToFrame("Победит тот, кто выcтроит четыре крестика или нолика в ряд.",ALIGN_CENTER,BLACK);
        putTextToFrame("Если никто не смог этого сделать, а ходы закончились, то объявляется НИЧЬЯ.",ALIGN_CENTER,BLACK);
        putTextToFrame("Координаты вводятся в виде XY, где Х-строка(1-5),У-стобец(1-5).",ALIGN_CENTER,BLACK);
        putTextToFrame("Ошибка ввода закроет программу!",ALIGN_CENTER,RED);

        print("\u255a");
        drawHLine2(60);
        print(" (RealVolAnd) ");
        drawHLine2(3);
        println("\u255d");
    }

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

    private static void putTextToFrame(String text,int align,String color){
        final int textSize=text.length();
        int paddingL,paddingR;

        if(align==ALIGN_CENTER){
            paddingL= (FRAME_WIDTH-textSize)/2;
            paddingR= (FRAME_WIDTH-textSize)/2;
            paddingR+=FRAME_WIDTH-(paddingR*2+textSize);
        }else{
            paddingL= 2;
            paddingR= (FRAME_WIDTH-textSize-2);
        }

        print("\u2551");
        drawNSpaces(paddingL);
        print(color+text+RESET);
        drawNSpaces(paddingR);
        print("\u2551");
        println("");
    }

}

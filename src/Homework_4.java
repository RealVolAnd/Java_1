import java.lang.reflect.Array;
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

    private static final int MAPSIZE = 3;
    private static final char DOT_EMPTY = '•';
    private static final char DOT_X = 'X';
    private static final char DOT_O = 'O';

    private static char[][] map;
    private static String[] vectorSmall;
    private static String[] vectorSmallIndex;
    private static String[] vectorBig;
    private static String[] vectorBigIndex;


    private static int DOTS_TO_WIN;
    private static Scanner scan = new Scanner(System.in);
    private static Random rand = new Random();
    private static int x=0,y=0;
    private static int diaganalCount=0;

    public static void main(String[] args) throws Exception {

        initTheGame();
        drawHeader();

            while (true){
                startTheGame();
                if(!checkRestart()) break;;
            }
    }


    private static void startTheGame(){
        String xy="";
        int result=99;
        clearMap();
        showMap(map);
        while(result==99){

            humanTurn();
            showMap(map);
            result=checkWinner();
            if(result!=99) break;
            aiTurn();
            showMap(map);
            result=checkWinner();
            if(result!=99) break;
        }
        showEndMessage(result);
    }


    //------------------------------------------ AI TURN ----------------------------------
    private static void aiTurn(){
        int turn;
        refreshVectorBig();

// 1.Проверка наличия выигрышной комбинации




// 1.Обязательная блокировка противника. От стеночки.От нолика
// 1.Выстраивание линии
// 1.Случайный ход

        int rndVector=(int)(Math.random()*vectorBig.length);
        while(vectorBig[rndVector].indexOf(DOT_EMPTY)<0)  {
            rndVector=(int)(Math.random()*vectorBig.length);
        }
        int rndIndex=(int)(Math.random()*MAPSIZE);
        while(vectorBig[rndVector].charAt(rndIndex)!=DOT_EMPTY){
            rndIndex=(int)(Math.random()*MAPSIZE);
        }
        putAxTurnToMap(vectorBigIndex[rndVector],rndIndex);

        //checkWinner();
    }

    private static void putAxTurnToMap(String vector,int idx){
        int aiX=0,aiY=0;
        String[] s=vector.split(" ");
        String sa=s[idx];
        String[] s2=sa.split(",");

            aiX=Integer.parseInt(s2[0]);
            aiY=Integer.parseInt(s2[1]);
        map[aiX][aiY]=DOT_O;
        x=aiX;y=aiY;
    }

//-------------------------------------------- HUMAN TURN ------------------------------------
    private static void humanTurn(){

            x = 0;
            y = 0;
            while ((x < 1)||(x > MAPSIZE)) {
                print("Введите номер строки (1-" + MAPSIZE + ") :");
                while (!scan.hasNextInt()) {
                    print("Ошибка.Повторите ввод:");
                    scan.nextLine();
                }
                x = scan.nextInt();
                scan.nextLine();
            }
        while ((y < 1)||(y > MAPSIZE)) {
                print("Введите номер столбца (1-"+MAPSIZE+") :");
                while (!scan.hasNextInt()) {
                    print("Ошибка.Повторите ввод:");
                    scan.nextLine();
                }
                y = scan.nextInt();
                scan.nextLine();
            }
            x--;
            y--;
        map[x][y]='X';
       // checkWinner();
    }






//-------------------------------------------- CHECK WINNER --------------------------------

    private static int checkWinner(){
        int res=0;

        refreshVectorSmall();

        for (int i = 0; i <MAPSIZE; i++) {
            for (int j = 0; j <MAPSIZE ; j++) {
                if(map[i][j]==DOT_EMPTY) res=99;
            }
        }
        for (int i = 0; i < vectorSmall.length; i++) {
            if(MAPSIZE==3){
                if(vectorSmall[i].contains("XXX")) res=1;
                if(vectorSmall[i].contains("OOO")){
                    res=-1;
                }
            } else {
                if(vectorSmall[i].contains("XXXX")) res=1;
                if(vectorSmall[i].contains("OOOO")) res=-1;
            }

        }
        return res;
    }


    private static void vectorBigReset(){
        for (int i = 0; i <vectorBig.length ; i++) vectorBig[i]="";
    }

    //--------------------------------------------- REFRESH THE BIG VECTOR ------------------------------
    private static void refreshVectorBig(){
        String s="";
        int vectorCounter=0;
        vectorBigReset();
        clearBigVectorIndex();
//Заполним большой вектор
        //Горизонтали
        for (int i = 0; i <MAPSIZE ; i++) {
            for (int j = 0; j <MAPSIZE ; j++) {
                vectorBig[vectorCounter]+=map[i][j];
                vectorBigIndex[vectorCounter]+=i+","+j+" ";
            }
            vectorCounter++;
        }
        //Вертикали
        for (int i = 0; i <MAPSIZE ; i++) {
            for (int j = 0; j <MAPSIZE ; j++) {
                vectorBig[vectorCounter]+=map[j][i];
                vectorBigIndex[vectorCounter]+=j+","+i+" ";
            }
            vectorCounter++;
        }
        //Главная левая  диагональ
        for (int i = 0; i <MAPSIZE ; i++) {
            vectorBig[vectorCounter]+=map[i][i];
            vectorBigIndex[vectorCounter]+=i+","+i+" ";
        }
        vectorCounter++;
        //Главная правая  диагональ
        for (int i = 0; i <MAPSIZE ; i++) {
            vectorBig[vectorCounter]+=map[MAPSIZE-i-1][i];
            vectorBigIndex[vectorCounter]+=(MAPSIZE-i-1)+","+i+" ";
        }
        if(MAPSIZE==3)return;
        vectorCounter++;
        //Левые левые диагонали
        int cntr=1;
        int maxValue=1;
        while (true){
            for (int i = cntr; i < MAPSIZE; i++) {
                vectorBig[vectorCounter]+=map[i][i-maxValue];
                vectorBigIndex[vectorCounter]+=i+","+(i-maxValue)+" ";

            }
            vectorCounter++;
            if((vectorBig[vectorCounter-1].length())==DOTS_TO_WIN) break;
            cntr++;
            maxValue++;
        }
        //Левые правые диагонали
        cntr=0;
        maxValue=1;
        while (true){
            for (int i = cntr; i < MAPSIZE-maxValue; i++) {
                vectorBig[vectorCounter]+=map[i][i+maxValue];
                vectorBigIndex[vectorCounter]+=i+","+(i+maxValue)+" ";

            }
            vectorCounter++;
            if((vectorBig[vectorCounter-1].length())==DOTS_TO_WIN) break;

            maxValue++;
        }
        //Правые левые диагонали

        cntr=MAPSIZE-2;
        maxValue=2;
        while (true){
            for (int i = 0; i <=MAPSIZE-maxValue; i++) {
                vectorBig[vectorCounter]+=map[i][cntr-i];
                vectorBigIndex[vectorCounter]+=i+","+(cntr-i)+" ";
            }
            vectorCounter++;
            if((vectorBig[vectorCounter-1].length())==DOTS_TO_WIN) break;
            cntr--;
            maxValue++;
        }
        //Правые правые диагонали
        cntr=1;
        maxValue=MAPSIZE;
        while (true){
            for (int i = cntr; i < MAPSIZE; i++) {
                vectorBig[vectorCounter]+=map[i][maxValue-i];
                vectorBigIndex[vectorCounter]+=i+","+(maxValue-i)+" ";
            }
            vectorCounter++;
            if((vectorBig[vectorCounter-1].length())==DOTS_TO_WIN) break;
            cntr++;
            maxValue++;
        }


    }



//--------------------------------------------- REFRESH THE SMALL VECTOR ------------------------------
    private static void refreshVectorSmall(){
        int tmpX,tmpY;
        String s1,s2;
        /*
        h-строка
        v-столбец
        l-левая диагональ
        r-правая диагональ
         */
        clearSmallVectorIndex();
        if(DOTS_TO_WIN>0){

            // заполняем горизонталь
            tmpY=y;
            s1="";
            s2="";

            while((tmpY>=0)&&((y-tmpY)<DOTS_TO_WIN)){
               s1=s1+ map[x][tmpY];
                tmpY--;
            }
           s1= new StringBuilder(s1).reverse().toString();

            tmpY=y+1;
            while((tmpY<MAPSIZE)&&(tmpY-y)<DOTS_TO_WIN) {
                s2=s2+ map[x][tmpY];
                tmpY++;
            }
            vectorSmall[0]=s1+s2;
            for (int i = 0; i <MAPSIZE ; i++) {
                vectorSmallIndex[0]+=x+","+i+" ";
            }



            // заполняем вертикаль
            tmpX=x;
            s1="";
            s2="";

            while((tmpX>=0)&&((x-tmpX)<DOTS_TO_WIN)){
                s1=s1+ map[tmpX][y];
                tmpX--;
            }
            s1=new StringBuilder(s1).reverse().toString();

            tmpX=x+1;
            while((tmpX<MAPSIZE)&&(tmpX-x)<DOTS_TO_WIN) {
                s2=s2+ map[tmpX][y];
                tmpX++;
            }
            vectorSmall[1]=s1+s2;
            for (int i = 0; i <MAPSIZE ; i++) {
                vectorSmallIndex[1]+=i+","+y+" ";
            }

            // заполняем левую диагональ
            tmpX=x;
            tmpY=y;

            s1="";
            s2="";

            while((tmpX>=0)&&(tmpY>=0)&&((x-tmpX)<DOTS_TO_WIN)&&((y-tmpY)<DOTS_TO_WIN)){
                s1=s1+ map[tmpX][tmpY];
                tmpX--;
                tmpY--;
            }
            s1=new StringBuilder(s1).reverse().toString();

            tmpX=x+1;
            tmpY=y+1;
            while((tmpX<MAPSIZE)&&(tmpY<MAPSIZE)&&((tmpX-x)<DOTS_TO_WIN)&&((tmpY-y)<DOTS_TO_WIN)) {
                s2=s2+ map[tmpX][tmpY];
                tmpX++;
                tmpY++;
            }
            vectorSmall[2]=s1+s2;

            for (int i = 0; i <MAPSIZE ; i++) {
                vectorSmallIndex[2]+=i+","+i+" ";
            }

            // заполняем правую диагональ
            tmpX=x;
            tmpY=y;

            s1="";
            s2="";

            while((tmpX>=0)&&(tmpY<MAPSIZE)&&((x-tmpX)<DOTS_TO_WIN)&&((tmpY-y)<DOTS_TO_WIN)){
                s1=s1+ map[tmpX][tmpY];
                tmpX--;
                tmpY++;
            }
           s1= new StringBuilder(s1).reverse().toString();

            tmpX=x+1;
            tmpY=y-1;
            while((tmpX<MAPSIZE)&&(tmpY>=0)&&((tmpX-x)<DOTS_TO_WIN)&&((y-tmpY)<DOTS_TO_WIN)) {
                s2=s2+ map[tmpX][tmpY];
                tmpX++;
                tmpY--;
            }
            vectorSmall[3]=s1+s2;
            for (int i = 0; i <MAPSIZE ; i++) {
                for (int j = MAPSIZE-1; j >=0; j--) {
                    vectorSmallIndex[2]+=i+","+j+" ";
                }
            }


        }else{

        }

    }
//----------------------------------------------- CLEAR MAP ------------------------------------------------
    private static void clearMap(){
        for (int i = 0; i < MAPSIZE; i++) {
            for (int j = 0; j < MAPSIZE; j++) {
                map[i][j]=DOT_EMPTY;
            }
        }
    }
//-------------------------------------------------- CLEAR BIG VECTOR INDEX --------------------------
private static void clearBigVectorIndex(){
    for (int i = 0; i < vectorBigIndex.length; i++) {
        vectorBigIndex[i]="";
    }
}
    //-------------------------------------------------- CLEAR SMALL VECTOR INDEX --------------------------
    private static void clearSmallVectorIndex(){
        for (int i = 0; i < vectorSmallIndex.length; i++) {
            vectorSmallIndex[i]="";
        }
    }



    private static void showMap(char[][] arr) {

        println("\t\t\t\t\t\t  "+BOLD+"ИГРОВОЕ ПОЛЕ"+REGUL);
        print("\t\t\t\t\t "+BLUE+BOLD+"Y>"+REGUL+BLUE);
        for (int i = 0; i <MAPSIZE ; i++) {
           print("\t"+(i+1));
        }
        println(RESET);

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

    private static void initTheGame(){
        int tmpVar=0;
        if((MAPSIZE<3)||(MAPSIZE%2==0)){
            println(RED+"Ошибка конфигурации!");
            println("1. Поле должно быть не меньше чем 3 Х 3.");
            println("2. Размер поля должен быть нечетным. Например: 3Х3, 5Х5, 7Х7...");
            System.exit(0);
        }
        if(MAPSIZE==3) DOTS_TO_WIN=3;
        if(MAPSIZE>3) DOTS_TO_WIN=4;
        map=new char[MAPSIZE][MAPSIZE];
        vectorSmall=new String[4];
        vectorSmallIndex=new String[4];

         if(MAPSIZE==3){
            tmpVar=8;
            diaganalCount=2;

        }else {
            diaganalCount=2+4*(MAPSIZE-4);
            tmpVar=MAPSIZE*2+(diaganalCount);
        }
        vectorBig=new String[tmpVar];
         vectorBigIndex=new String[tmpVar];
    }


    private static boolean checkRestart(){
        boolean b=false;
        int result=100;
        while ((result!=0)&&(result!=1)) {

            print("Повторить игру еще раз? 1-да/0-нет:");
            while (!scan.hasNextInt()){
                print(RED+"Введите 1 чтобы продолжить игру, или 0 для выхода"+RESET);
                scan.nextLine();
            }
            result= scan.nextInt();
            scan.nextLine();
        }
        if(result==1) b=true;
        return b;
    }


    private static void showEndMessage(int whoWin){

        print(RESET);
        println("");
        drawHLine2(76);
        println("");
        println("");

        if(whoWin>0){
            drawNSpaces(20);
            println(BOLD + YELLOW + "\u2b50 \u2b50 \u2b50 \u2b50" + GREEN + "  ПОЗДРАВЛЯЮ! ВЫ ВЫИГРАЛИ !!! " + YELLOW + "\u2b50 \u2b50 \u2b50 \u2b50" + RESET + REGUL);
        }else if(whoWin<0){

            println("\u263a \u263a \u263a "+BOLD + RED + "НА ЭТОТ РАЗ НЕ ПОВЕЗЛО. НЕ РАССТРАИВАЙТЕСЬ. ПОПОРБУЙТЕ ЕЩЕ РАЗ." + RESET + REGUL+" \u263a \u263a \u263a");
        }else{
            drawNSpaces(17);
            println("\u263a \u263a \u263a "+BOLD + YELLOW + "НИЧЬЯ. ХОРОШО! ПОПОРБУЙТЕ ЕЩЕ РАЗ." + RESET + REGUL+" \u263a \u263a \u263a");
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
        putTextToFrame("Победит тот, кто выcтроит ряд из "+DOTS_TO_WIN+" крестиков или ноликов.",ALIGN_CENTER,BLACK);
        putTextToFrame("Если никто не смог этого сделать, а ходы закончились, то объявляется НИЧЬЯ.",ALIGN_CENTER,BLACK);



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
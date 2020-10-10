public class Homework_1 {
    private static final String DIV_BY_ZERO_ERR="Нельзя делить на 0!";
    /*
    п.1 Создаем метод main
    */
    public static void main(String[] args) {
        /*
        п.2 Создаем и инициализируем локальные переменные всех типов из урока
         */
        byte byteVar=100;
        short shortVar=10000;
        int intVar=1000000000;
        long longVar=1000000000000000000L;
        float floatVar =100000000000000000000000000000000000000.0f;
        double doubleVar=10000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.0;
        char charVar='C';
        boolean boolVar=true;
        String strVar="Happy new year!";


        /*
        Метод п.3:
         */
        getFloatValue(11,-55,128,0);

        /*
        Метод п.4:
         */
        int numA=10;
        int numB=7;
        System.out.format("4. Результат логического выражения 10<("+(numA+numB)+")<=20 = %b%n",isInRange(numA,numB));

        /*
        Метод п.5:
         */
        isPositive(-7);

        /*
        Метод п.6:
         */
        int testNum=44;
        System.out.println("6. Результат проверки отрицательно ли число "+testNum+" : "+isNegative(testNum));

        /*
        Метод п.7:
         */
        helloName("Иван");

       /*
        Метод п.8:
        */
        int year=2404;
        checkYear(year);

    }



    public static float getFloatValue(int a,int b,int c,int d){
        float result=0f;
        try {
            result= a * (b + (c / d));
            System.out.format("3. Результат вычисления "+a+" * ("+b+" + ("+c+" / "+d+")) = %1.2f%n",result);
        }catch (ArithmeticException e){
            System.out.println("3. "+DIV_BY_ZERO_ERR);
        }
        return result;
    }

    public static boolean isInRange(int a, int b) {
        boolean result=false;
        if((a+b)>10 &&(a+b)<=20) result=true;
        return result;
    }

    public static void isPositive(int a) {
        if(a>=0){
            System.out.println("5. Число "+a+" положительное");
        } else {
            System.out.println("5. Число "+a+" отрицательное");
        }
    }

    public static boolean isNegative(int a) {
        return(a<0);
    }


    public static void helloName(String name) {
        System.out.println("7. Привет,"+name+"!");
    }

    public static void checkYear(int year) {
        boolean result=false;
        String verdict="неопределен";
        if(((year%4==0)&&(year%100!=0))||(year%400==0)) result=true;
        if (result){
            verdict=" високосный";
        }else{
            verdict=" не високосный";
        }
        System.out.println("8. Год "+year+verdict);
    }
}

import java.util.Arrays;

public class Homework_2 {
    public static void main(String[] args) {

        //Задания к Уроку №2 курса Java Core.Базовый уровень.
        // Вывод результатов организован в теле соответствующего метода.
        //---------------------------------------------------------
        Task_1();// Задание 1
        Task_2();// Задание 2
        Task_3();// Задание 3
        Task_4();// Задание 4
        Task_5();// Задание 5

        boolean b=Task_6();// Задание 6.Формально возвращает boolean, однако вывод в данном случае реализован в теле метода.

        // Аргументы для Задания 7
        int[] testArr={0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int shiftN=5;

        Task_7(testArr,shiftN);// Задание 7
        //-----------------------------------------------------------
    }

    /*
     Метод к Заданию 1.
     */
    public static void Task_1(){
        int arr[]={1, 1, 0, 0, 1, 0, 1, 1, 0, 0};

        printLine("Задание 1:");
        printLine("\t\tИсходный массив: \t"+Arrays.toString(arr));

        for(int i=0;i<arr.length;i++) arr[i]=(arr[i]==1)?0:1;

        printLine("\t\tКрайний массив: \t"+Arrays.toString(arr));
    }

    /*
     Метод к Заданию 2.
     */
    public static void Task_2(){
        int arr[]=new int[8];
        for (int i = 0; i <arr.length; i++) arr[i]=i*3;

        printLine("Задание 2:");
        printLine("\t\tКрайний массив: \t"+Arrays.toString(arr));
    }

    /*
     Метод к Заданию 3.
     */

    public static void Task_3(){
        int arr[]={1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};

        printLine("Задание 3:");
        printLine("\t\tИсходный массив: \t"+Arrays.toString(arr));

        for (int i = 0; i < arr.length; i++) {
            if(arr[i]<6) arr[i]*=2;
        }
        printLine("\t\tКрайний массив: \t"+Arrays.toString(arr));
    }

    /*
    Метод к Заданию 4.
    */
    public static void Task_4(){
        int[][] arr = new int[5][5];
        printLine("Задание 4:");
        printLine("\t\tИсходный массив:");
        print2DArr(arr);

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if((i==j)||(i+j==arr.length-1)) arr[i][j]=1;
            }
        }
        printLine("\t\tКрайний массив:");
        print2DArr(arr);
    }

    /*
    Метод к Заданию 5.
    */
    public static void Task_5(){
        int[] arr=new int[5];
        arr=getRandomIntArray(10).clone();

        printLine("Задание 5:");
        printLine("\t\tИсходный массив: \t"+Arrays.toString(arr));

        Arrays.sort(arr);

        printLine("\t\tМинимальное значение: \t"+arr[0]);
        printLine("\t\tМаксимальное значение: \t"+arr[arr.length-1]);
    }

    /*
    Метод к Заданию 6.
    */
    public static boolean Task_6(){
        boolean result=false;
        int arr[]={1, 1, 3, 1, 1, 1, 1, 1, 1, 1};
        int arrSum=0,sum=0;
        String arrString="";

        printLine("Задание 6:");
        printLine("\t\tИсходный массив: \t"+Arrays.toString(arr));

        for(int intVar:arr) arrSum+=intVar;

        for (int i = 0; i < arr.length; i++) {
            sum+=arr[i];
            if(i<arr.length-1){
                arrString+=arr[i]+", ";
            } else{
                arrString+=arr[i];
            }
            if((arrSum-sum)==sum) {
                arrString+="|| ";
                result=true;
            }
        }

        printLine("\t\tКрайний массив: \t["+arrString+"]");
        printLine("\t\tРезультат : \t\t"+result);

        return result;
    }

    /*
    Метод к Заданию 7.
    */
    public static void Task_7(int[] arr,int shift){
        int a=0,b=0;

        printLine("Задание 7:");
        printLine("\t\tИсходный массив: \t"+Arrays.toString(arr));

        if(shift>0){

            for (int i = 0; i < shift; i++) {
                a=arr[arr.length-1];
                for (int j = 0; j < arr.length; j++) {

                        b=arr[j];
                        arr[j]=a;
                        a=b;
                }
            }

            printLine("\t\tКрайний массив: \t"+Arrays.toString(arr));

        }else if(shift<0){

            for (int i = shift; i < 0; i++) {
                a=arr[0];
                for (int j = arr.length-1; j >= 0; j--) {

                    b=arr[j];
                    arr[j]=a;
                    a=b;
                }
            }

            printLine("\t\tКрайний массив: \t"+Arrays.toString(arr));

        } else{
            printLine("\t\tКрайний массив: \t"+Arrays.toString(arr));
        }
    }

    // ----------------------- Методы общего назначения класса --------------------------------

    /*
    Общий, короткий метод вывода в консоль строки
     */
    private static void printLine(String s){
        System.out.println(s);
    }

    /*
    Общий метод заполнения одномерного массива случайными значениями от 0 до 100
     */
    private static int[] getRandomIntArray(int len){
        int[] arr=new int[len];

        for (int i = 0; i < arr.length; i++) {
            arr[i]= (int)(Math.random()*100);
        }
        return arr;
    }

    /*
     Общий метод форматирования и вывода в консоль двумерного массива
      */
    private static void print2DArr(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print("\t\t\t\t\t\t");
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print("\t"+arr[i][j]);
            }
            System.out.println();
        }
    }
}

import java.util.Arrays;

public class Homework_2 {
    public static void main(String[] args) {
        Task_1();
        Task_2();
        Task_3();
        Task_4();
        Task_5();

        boolean b=Task_6();

        int[] testArr={0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int shiftN=5;
        Task_7(testArr,shiftN);

    }

    public static void Task_1(){
        int arr[]={1, 1, 0, 0, 1, 0, 1, 1, 0, 0};

        printLine("Задание 1:");
        printLine("\t\tИсходный массив: \t"+Arrays.toString(arr));

        for(int i=0;i<arr.length;i++) arr[i]=(arr[i]==1)?0:1;

        printLine("\t\tКрайний массив: \t"+Arrays.toString(arr));
    }

    public static void Task_2(){
        int arr[]=new int[8];
        for (int i = 0; i <arr.length; i++) arr[i]=i*3;

        printLine("Задание 2:");
        printLine("\t\tКрайний массив: \t"+Arrays.toString(arr));
    }
    public static void Task_3(){
        int arr[]={1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};

        printLine("Задание 3:");
        printLine("\t\tИсходный массив: \t"+Arrays.toString(arr));

        for (int i = 0; i < arr.length; i++) {
            if(arr[i]<6) arr[i]*=2;
        }
        printLine("\t\tКрайний массив: \t"+Arrays.toString(arr));
    }
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
    public static void Task_5(){
        int[] arr=new int[5];
        arr=getRandomIntArray(10).clone();

        printLine("Задание 5:");
        printLine("\t\tИсходный массив: \t"+Arrays.toString(arr));

        Arrays.sort(arr);

        printLine("\t\tМинимальное значение: \t"+arr[0]);
        printLine("\t\tМаксимальное значение: \t"+arr[arr.length-1]);
    }

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

    public static void printLine(String s){
        System.out.println(s);
    }

    public static int[] getRandomIntArray(int len){
        int[] arr=new int[len];

        for (int i = 0; i < arr.length; i++) {
            arr[i]= (int)(Math.random()*100);
        }
        return arr;
    }

    public static void print2DArr(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print("\t\t\t\t\t\t");
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print("\t"+arr[i][j]);
            }
            System.out.println();
        }
    }
}

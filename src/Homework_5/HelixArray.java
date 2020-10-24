package Homework_5;

import java.util.Arrays;

public class HelixArray {
    private int helixArray[][];
    private int rowCount=0;
    private int colCount=0;
    private boolean isNull=false;
    private static final String BOLD = "\033[1m";
    private static final String RESET= "\033[0m";

    HelixArray(int rowCount,int colCount){
        this.rowCount=rowCount;
        this.colCount=colCount;
        if(rowCount>0&&colCount>0) {
            helixArray = new int[rowCount][colCount];
            initHelixArray();
        }else {
            isNull=true;
        }

    }

    private void initHelixArray(){
        int itemCounter=1,rowCounter=0,colCounter=0;

        if(rowCount!=1&&colCount!=1){
            while(itemCounter<(rowCount*colCount)){
                for (int i=rowCounter; i < rowCount-rowCounter-1; i++) {
                    helixArray[i][colCounter]=itemCounter;
                    itemCounter++;
                }
                for (int i=colCounter; i < colCount-colCounter-1; i++) {
                    helixArray[rowCount-rowCounter-1][i]=itemCounter;
                    itemCounter++;
                }
                for (int i=rowCount-rowCounter-1; i > rowCounter; i--) {
                    helixArray[i][colCount-colCounter-1]=itemCounter;
                    itemCounter++;
                }
                for (int i=colCount-colCounter-1; i > colCounter; i--) {
                    helixArray[rowCounter][i]=itemCounter;
                    itemCounter++;
                }
                rowCounter++;
                colCounter++;
            }
        }else{
            for (int i = 0; i < rowCount; i++) {
                for (int j = 0; j < colCount; j++) {
                    helixArray[i][j]=i+j+1;
                }
            }
        }
    }


    @Override
    public String toString () {
        String result="";

        result += BOLD + "\nHelix Array: \n" + RESET;
        if(!isNull) {
            for (int i = 0; i < helixArray.length; i++) {
                for (int j = 0; j < helixArray[i].length; j++) {
                    result += (helixArray[i][j] + "\t");
                }
                result += "\n";
            }
        }else{
            result+="Ошибка!";
        }
        return result;
    }
}

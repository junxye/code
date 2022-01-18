package main.java;

import java.time.LocalDate;
import java.util.Random;
import java.util.Scanner;

public class Draft {
    public static void main(String[] args) {
        Draft draft=new Draft();
        Scanner in = new Scanner(System.in);
        /*int[] tem={30,60,90};
        int[] days=draft.dailyTemperatures(tem);
        for (int i:days){
            System.out.print(i+" ");
        }*/
        Random random = new Random();
        while(true){

            if(in.nextLine().length()==0) {
                System.out.println(in.nextLine().length());
                System.out.println("Wrong type. Please type again. ");
            }

        }
    }

    public int[] dailyTemperatures(int[] temperatures) {
        int[] days = new int[temperatures.length];
        for(int i=0,j=0;i<temperatures.length-1;i++){
            while(j< (temperatures.length-1) && !(temperatures[i]<temperatures[j])){
                j++;
            }
            if(!(temperatures[i]<temperatures[j])){
                j=i+2;
                days[i]=0;
                continue;
            }
            days[i]=j-i;
            j=i+2;

        }
        days[days.length-1]=0;
        return days;
    }
}

package assignment;

import java.util.Scanner;

public class mark {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int length = in.nextInt();
        int[] scores=new int[length];
        for (int i=0;i<length;i++){
            scores[i]=in.nextInt();
        }
        System.out.println(averageScores(scores));
        System.out.println(maxScores(scores));
    }

    public static double averageScores(int[] scores){
        double average=0.0;
        for (int score : scores) {
            average += score;
        }
        return average/(scores.length);
    }

    public static int maxScores(int[] scores){
        int max=0;
        for (int i=1;i<scores.length;i++){
            if(scores[i-1]<scores[i]){
                max=i+1;
            }
        }
        return max;
    }

}

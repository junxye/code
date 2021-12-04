package assignment;

import java.util.Scanner;
import java.util.concurrent.locks.LockSupport;

public class multiPrint {
    Scanner in;
    int[] arr1;
    int[] arr2;
    Thread t1;
    Thread t2;


    {
        in = new Scanner(System.in);
    }

    public multiPrint() {
        gainArray();
        t1 = new Thread(() -> {
            for (int i = 0;i<arr1.length;i++) {
                System.out.println(arr1[i]); //t1进先打印
                if(arr1.length>arr2.length && i >= arr2.length) t2.interrupt();
                else {
                    LockSupport.unpark(t2); //唤醒t2
                    LockSupport.park(); // t1阻塞
                }
            }
        });
        t2 = new Thread(() -> {
            for (int j = 0;j<arr2.length;j++) {
                if(!(arr1.length< arr2.length && j >= arr1.length-1)) LockSupport.park(); //要先打印数字， 所以这直接阻塞 唤醒t1后来到这里又阻塞， 等打印数字后再唤醒它
                System.out.println(arr2[j]); //被唤醒后打印字母
                if(arr1.length< arr2.length && j >= arr1.length-1) t1.interrupt();
                else LockSupport.unpark(t1); //唤醒t1
            }
        });

        t2.start();
        t1.start();
    }

    public void gainArray(){
        System.out.println("Enter the first array:   ( Input example:1,2,3,4 )");
        String s1 = in.nextLine();
        System.out.println("Enter the second array:");
        String s2 = in.nextLine();
        this.arr1 = getArray(s1);
        this.arr2 = getArray(s2);
    }

    public int[] getArray(String s){
        String count = s.replaceAll("[^,]","");
        int len = count.length()+1;
        int[] num = new int[len];
        for (int i = 0, j, t = 0; t < len;t++){
            j=s.substring(i,s.length()).indexOf(",")+i;
            if(j==-1+i){
                num[t]=Integer.parseInt(s.substring(i,s.length()));
                break;
            }
            int number = Integer.parseInt(s.substring(i,j));
            num[t]=number;
            i=j+1;
        }
        return num;
    }
}

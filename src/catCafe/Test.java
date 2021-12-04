package catCafe;


public class Test {
    public static void main(String[] args) {
        MyCatCafe myCatCafe = new MyCatCafe();
        System.out.println("Input :   1:Default balance ; 2:Enter balance");
        int num = myCatCafe.isInt(1,2);
        if(num == 1) myCatCafe = new MyCatCafe(1000);
        else myCatCafe.beginning();
        if(myCatCafe.balance>=200){
            while(true){
                System.out.println("Begin a new day?  ( 1:Yes , 2:No )");
                int getNum = myCatCafe.isInt(1,2);
                if (getNum == 1) myCatCafe.open();
                else break;
            }
        }
        System.out.println("Game ended.");
    }
}

package catCafe;

public class InsufficientBalanceException extends RuntimeException{
    public InsufficientBalanceException(){
        super();
        System.out.println("You balance is not enough.");

    }
}

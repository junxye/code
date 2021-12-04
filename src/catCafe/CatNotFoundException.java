package catCafe;

public class CatNotFoundException extends RuntimeException{
    public CatNotFoundException(){
        super();
        System.out.println("You do not have cat.");

    }

}

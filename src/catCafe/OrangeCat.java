package catCafe;

public class OrangeCat extends Cat{
    boolean isFat;
    {
        price = 200;
    }

    public boolean isFat() {
        return isFat;
    }

    public void setFat(boolean fat) {
        isFat = fat;
    }

    public OrangeCat(String name, int age, int sex, boolean isFat) {
        super(name, age, sex);
        setFat(isFat);
    }

    @Override
    public String toString(){
        return "Cat's name: "+this.name+"\nCat's age: "+this.age+"\nCat's sex: "+sexGetName()+"\nCat's price: "+this.price+"\nIs fat: "+this.isFat;
    }

}

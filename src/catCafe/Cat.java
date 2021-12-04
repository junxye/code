package catCafe;

public abstract class Cat {
    String name;
    int age;
    int sex;    // 0 female,1 male
    double price;
    public Cat(String name,int age,int sex){
        this.name=name;
        this.age=age;
        this.sex=sex;
    }
    public String sexGetName(){
        if(sex == 0) return "female";
        return "male";
    }
    public String toString() {
        return "Cat's name: "+this.name+"\nCat's age: "+this.age+"\nCat's sex: "+sexGetName()+"\nCat's price: "+this.price;
    }
}

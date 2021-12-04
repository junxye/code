package catCafe;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class MyCatCafe implements CatCafe{

    double balance;
    ArrayList<Cat> cats;
    ArrayList<Customer> customers;
    ArrayList<Customer> todayCustomers;
    Scanner in;
    Random random;
    int catNumber;
    int customerNumber;
    LocalDate today;

    {

        cats = new ArrayList<>();
        customers = new ArrayList<>();
        todayCustomers = new ArrayList<>();
        in = new Scanner(System.in);
        random = new Random();
        catNumber = 0;
        customerNumber = 0;
        today = LocalDate.now();
    }

    public MyCatCafe() {
    }

    public MyCatCafe(double balance){
        this.balance = balance;
    }

    public void purchaseWindow(){
        int kind;
        String name;
        int age;
        int sex;
        while(true){


            // begin to bug cat
            System.out.println("Wanna buy a cat? ( 1:Yes , 2:No , 3:View cat prices )");
            int getIn = isInt(1,3);
            if(getIn == 2) break;
            else if(getIn == 3) {
                viewCatPrices();
                continue;
            }
            System.out.println("What kind of cat do you want to buy?\n" +
                    "( 1:OrangeCat ; 2:BlackCat ; 3:DragonLi ; 4:BritishShorthair ; 5:ScottishHighland )");
            kind = isInt(1,5);
            double need = 0;
        /*
        1 : "OrangeCat" ; 2 : "BlackCat" ; 3 : "DragonLi" ; 4 : "BritishShorthair" ; 5 : "ScottishHighland"
        */
            switch (kind){
                case 1:need = 200;break;
                case 2:need = 350;break;
                case 3:need = 600;break;
                case 4:need = 400;break;
                case 5:need = 500;break;
            }
            try{
                isCanBuy(need);
            }catch (InsufficientBalanceException e)
            {
                e.printStackTrace();
                continue;
            }

            // gain name
            in.nextLine();
            System.out.println("Cat's name?  \n" +
                    "( You can input -1 to generate randomly. )");
            while(true){
                while(true){
                    if(in.hasNextLine()){
                        name = in.nextLine();
                        if(name.length() == 0 || name.matches("^\\s.*") || name.matches(".*$\\s")) System.out.println("Wrong type. Please type again. ");
                        else break;
                    }
                }
                if(name.equals("-1")) name = "cat"+catNumber++;
                if(canUse(name,0)) break;
                else System.out.println("This cat has been added.");
            }

            // gain age
            System.out.println("Cat's age?  \n" +
                    "( Cats are up to 20 years old! You can input -1 to generate randomly. )");
            age = isInt(-1,20);
            if(age == -1) {
                age = random.nextInt(21);
            }

            // gain sex
            System.out.println("Cat's sex?\n" +
                    "( 0 : female , 1 : male. You can input -1 to generate randomly. )");
            sex = isInt(-1,1);
            if(sex == -1) sex = random.nextInt(2);

            buyCat(kind,name,age,sex);

        }
    }

    public int isInt(int a,int b){
        int num;
        while(true){
            if(in.hasNextInt()) {
                num = in.nextInt();
                if(num<a || num>b) System.out.println("Wrong number. Please type again. ");
                else break;
            }
            else {
                if(in.next().length()==0) System.out.println("Error character. Please type again. ");
                System.out.println("Error character. Please type again. ");
                in.nextLine();
            }
        }
        return num;
    }

    @Override
    public void buyCat(int num,String name, int age,int sex) {

        switch (num){
            case 1: System.out.println("Is the cat fat?  ( 1:Yes , 2: No )");
            if(isInt(1,2) == 1) cats.add(new OrangeCat(name,age,sex,true));
            else cats.add(new OrangeCat(name,age,sex,false));
            break;
            case 2:cats.add(new BlackCat(name,age,sex));break;
            case 3:cats.add(new DragonLi(name,age,sex));break;
            case 4:cats.add(new BritishShorthair(name,age,sex));break;
            case 5:cats.add(new ScottishHighland(name,age,sex));break;
        }
        System.out.println("balance = "+this.balance);
    }

    @Override
    public void entertainCustomers(Customer customer) {
        if(canUse(customer.name,1)) customers.add(customer);
        int ruaCat;
        boolean canRua = true;
        try{
            isHasCat();
        }catch (CatNotFoundException e){
            e.printStackTrace();
            canRua = false;
        }
        if(canRua){
            int ruaTime = 1;
            while((customer.ruaNumber-ruaTime+1)>0){
                ruaCat = random.nextInt(cats.size());
                System.out.println("Rua times: "+ruaTime++ +"\n"+cats.get(ruaCat).toString());
                balance += 15;

            }
            todayCustomers.add(customer);
        }
    }

    @Override
    public void close() {
        System.out.println("Today is "+today);
        System.out.println("Today's guests: \n" +
                "========================================");
        int totalRua =0;
        for(Customer customer : todayCustomers){
            System.out.println(customer.toString());
            totalRua += customer.ruaNumber;
        }
        System.out.println("========================================\n"+
                "Today profit: "+totalRua*15);
        System.out.println("Wanna view some data?\n" +
                "( 1:View cat's list , 2:View customer's list , 3:No need )");
        int getIn = isInt(1,3);
        if(getIn == 1) {
            viewCats();
            System.out.println("Wanna view customer's list,too?   ( 1:Yes , 2: No )");
            if(isInt(1,2) == 1) viewCustomers();
        }
        if(getIn == 2) {
            viewCustomers();
            System.out.println("Wanna view cat's list,too?   ( 1:Yes , 2: No )");
            if(isInt(1,2) == 1) viewCats();
        }
        todayCustomers.removeAll(todayCustomers);
    }

    public void addCustomer(){
        String name;
        int ruaNumber;
        LocalDate date = today;
        while(true){
            // begin to add
            System.out.println("Wanna add a customer? ( 1:Yes ; 2:No )");
            if(isInt(1,2)==2) break;

            // gain name
            in.nextLine();
            System.out.println("Customer's name?  \n" +
                    "( You can input -1 to generate randomly. )");
            while(true){
                while(true){
                    if(in.hasNextLine()){
                        name = in.nextLine();
                        if(name.length() == 0 || name.matches("^\\s.*") || name.matches(".*$\\s")) System.out.println("Wrong type. Please type again. ");
                        else break;
                    }
                }
                if(name.equals("-1")) name = "customer"+customerNumber++;
                if(canUse(name,1)) break;
                else System.out.println("This customer has been added.");
            }

            // gain ruaNumber
            System.out.println("How many times wanna rua?  \n" +
                    "( You can input 0 to generate randomly. By the way, no more than 10000 times, the cat will be bald!) ");
            ruaNumber = isInt(0,10000);
            if(ruaNumber==0) ruaNumber = random.nextInt(10000)+1;

            // entertain customer
            Customer customer = new Customer(name,ruaNumber,date);
            entertainCustomers(customer);
        }

    }

    public void repeatCustomer(){
        System.out.println("Turn on repeat customer mode?  ( 1:Yes , 2: No )\n" +
                "Tip: Sometimes the customer may like the cat cafe very much and come back Rua repeatedly.");
        if(isInt(1,2) == 2) return;
        int time = random.nextInt(customers.size())+1;
        for(int i=0;i<time;i++){
            int num = random.nextInt(customers.size());
            System.out.println("=====================\n" +
                    "Customer: "+customers.get(num).name+" come on!");
            entertainCustomers(customers.get(num));
            System.out.println("=====================");
        }
    }

    public boolean canUse(String name, int num){
        if(num == 0){
            for(Cat catName : cats){
                if(name.equals(catName.name)) return false;
            }
        }
        if(num == 1){
            for(Customer cusName : customers){
                if(name.equals(cusName.name)) return false;
            }
        }
        return true;
    }

    public void isCanBuy(double need) throws InsufficientBalanceException{
        if(need <= balance) balance-=need;
        else throw new InsufficientBalanceException();
    }

    public void isHasCat() throws CatNotFoundException{
        if(cats.size()<=0) throw new CatNotFoundException();
    }

    public void open(){
        System.out.println("Today is "+today);
        purchaseWindow();
        addCustomer();
        if(customers.size()>0) repeatCustomer();
        close();
        today = today.plusDays(1);
    }

    public void viewCatPrices(){
        System.out.println("OrangeCat : 200\nBlackCat : 350\nDragonLi : 600\nBritishShorthair : 400\nScottishHighland : 500");
    }

    public void viewCats(){
        System.out.println("=================");
        for(Cat cat:cats) System.out.println(cat.toString());
        System.out.println("=================");
    }

    public void viewCustomers(){
        System.out.println("=================");
        for(Customer customer:customers) System.out.println(customer.toString());
        System.out.println("=================");
    }

    public void beginning(){
        System.out.println("Welcome, player! \n" +
                "Now, enter your opening fund to open your cat coffee:  ( You can type 'see' to view cat prices. )");
        while(true){
            if(in.hasNext()) {
                String s = in.next();
                if(s.matches("[0-9]+$") || s.matches("[0-9]*\\.([0-9]+)$")) balance = Double.parseDouble(s);
                else {
                    if(s.equals("see")){
                        viewCatPrices();
                        System.out.println("Enter your opening fund to open your cat coffee: ");
                        continue;
                    }
                    else System.out.println("Error character. Please type again. ");
                    continue;
                }
            }else {
                System.out.println("Error character. Please type again. ");
                continue;
            }
            if(balance < 200) {
                System.out.println("You don't have enough money to start a cat cafe!  \n" +
                        "Wanna restart?  ( 1:Yes , 2:No )");
                if(isInt(1,2) == 2) break;
                else System.out.println("Enter your opening fund to open your cat coffee: ");
            }
            else break;
        }
    }
}

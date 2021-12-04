package assignment;

import java.util.ArrayList;

public class Booth {
    private static final ArrayList<Long> ids = new ArrayList<>();
    private long id;
    private String name;
    private int total;
    private boolean isClosed;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public boolean getIsClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }

    public String toString() {
        return "ID: "+this.id+"\nname: "+this.name+"\nnumber: "+this.total+ "\ncondition: "+this.isClosed;
    }

    public Booth(long id,String name,int total,boolean isClosed){
        for (long i:ids){
            if(i == id){
                return;
            }
        }
        ids.add(id);
        setId(id);
        setName(name);
        setTotal(total);
        setClosed(isClosed);
    }

    public static void purchase(Booth shop,int number){
        if(number < 0 | shop.isClosed | number>shop.total){
            System.out.println("Transaction failed.");
        }
        else{
            shop.total-=number;
            System.out.println("Purchase succeeded!");
        }
    }

    public void restock(int number){
        if(number > 200){
            System.out.println("Purchase quantity is too large!");
        }
        else if(number < 0) System.out.println("Wrong number!");
        else{
            total+=number;
            System.out.println("Purchase success, the current watermelon total is "+total);
        }
    }

    public static void closeBooths(Booth[] booths){
        for (Booth booth : booths) {
            if (!booth.isClosed) {
                booth.isClosed = true;
            } else {
                System.out.println(booth);
            }
        }
    }

    public static void main(String[] args) {
        Booth[] bo=new Booth[2];
        bo[0]=new Booth(1,"A",120,false);
        bo[1]=new Booth(2,"B",50,true);
        purchase(bo[0],50);
        purchase(bo[0],200);
        purchase(bo[1],50);
        bo[0].restock(50);
        bo[0].restock(240);
        closeBooths(bo);
    }


}

package catCafe;

import java.time.LocalDate;

public class Customer {
    String name;
    int ruaNumber;
    LocalDate date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRuaNumber() {
        return ruaNumber;
    }

    public void setRuaNumber(int ruaNumber) {
        this.ruaNumber = ruaNumber;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Customer(String name,int ruaNumber,LocalDate date){
        this.name=name;
        this.ruaNumber=ruaNumber;
        this.date=date;
    }

    @Override
    public String toString(){
        return "Customer's name: "+this.name+"\nWanna rua: "+this.ruaNumber+"\nArrival time: "+this.date;
    }
}

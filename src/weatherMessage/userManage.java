package weatherMessage;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class userManage {

    public sqlManage sqlManage = new sqlManage();
    public getInformation getInformation = new getInformation();

    public Scanner in = new Scanner(System.in);

    public userManage(){
        try {
            sqlManage.begin();
            getInformation.cityInquire("北京");
            getInformation.cityInquire("上海");
            getInformation.cityInquire("福州");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateBase(){
        try {
            getInformation.weatherInquire(sqlManage.getId("北京"));
            getInformation.weatherInquire(sqlManage.getId("上海"));
            getInformation.weatherInquire(sqlManage.getId("福州"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addCity(){
        System.out.println("Wanna add city? ( Yes: input city name , No: 0 )");
        String s = in.next();
        if (s.equals("0")) return;
        try {
            Map<String,Object> map = getInformation.cityInquire(s);
            if (map != null) getInformation.weatherInquire(sqlManage.getId(s));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void showWeather(){
        System.out.println("See datas?\n" +
                "Input 0 : See all weather datas.\n" +
                "Input 1 : See weather situation only. \n" +
                "Input 2 : See temperature only. \n" +
                "Input 3 : No need.");
        int num = isInt(0,3);

        if (num == 3) return;
        in.nextLine();
        System.out.println("If look the single city, type city name, else type 0 ( 分页查询 ）.");
        String city;
        while(true){
            if(in.hasNextLine()){
                city = in.nextLine();
                if(city.length() == 0 || city.matches("^\\s.*") || city.matches(".*$\\s")) System.out.println("Wrong type. Please type again. ");
                else break;
            }
        }
        if (city.equals("0")) sqlManage.seeAllWeather(num);
        else sqlManage.cityWeather(city,num);

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
}

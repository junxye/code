package weatherMessage;

import java.util.Scanner;

public class weatherEnquirySystem {

    /*
    建表包括在 sqlManage.begin()
    数据库相关在 sqlManage
    api获取在 getInformation
     */
    public static void main(String[] args) {
        userManage user = new userManage();
        user.updateBase();
        Scanner in = new Scanner(System.in);
        do {
            user.addCity();
            user.showWeather();

            System.out.println("Resume? ( Yes: 1 , No: any input )");
            String s = in.next();
            if (!s.equals("1")) break;
        }while(true);
    }
}

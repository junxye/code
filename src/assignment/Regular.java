package assignment;

import java.util.Scanner;

public class Regular {
    String form;
    Scanner in;

    {
        in = new Scanner(System.in);
    }

    public Regular(){
        System.out.println("Enter your e-mail:");
        String mail = in.nextLine();
        System.out.println(input(mail));
    }

    public boolean input(String mail){
        form = "\\w+@\\w+(\\.\\w{2,3})*\\.\\w{2,3}";
        return mail.matches(form);
    }
}

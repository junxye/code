package assignment;

import java.awt.*;

public class Test {
    public static void main(String[] args) throws AWTException {
        Robot r = new Robot();
        multiPrint multi = new multiPrint();
        r.delay(100);
        Regular regular = new Regular();
    }
    /*
    发现多线程有时会有一半输出在正则判断之后，因此加了个延迟
     */
}

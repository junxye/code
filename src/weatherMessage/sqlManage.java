package weatherMessage;

import java.io.IOException;
import java.sql.*;
import java.util.Map;

import static weatherMessage.Demo.sql;

public class sqlManage {

    public static final String url = "jdbc:mysql://127.0.0.1/weather";
    public static final String name = "com.mysql.jdbc.driver";
    public static final String user = "root";
    public static final String password = "SQLjx1413";

    public Connection conn = null;
    public PreparedStatement pst = null;
    public Statement st = null;
    public ResultSet ret = null;


    public void begin(){
        System.out.println("SQL to create..............");

        String create_1 = " CREATE TABLE IF NOT EXISTS `cityData` (                             \n" +
                "   `id` bigint(20) NOT NULL,            \n" +
                "   `city` varchar(20) NOT NULL,                     \n" +
                "   `lat` double(10,10) NOT NULL,                       \n" +
                "   `lon` double(10,10) NOT NULL,                       \n" +
                "   `adm1` varchar(20) NOT NULL,                           \n" +
                "   PRIMARY KEY (`id`)                                  \n" +
                " ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8";
        String create_2 = " CREATE TABLE IF NOT EXISTS `weatherData` (                             \n" +
                "   `id` bigint(20) NOT NULL,\n" +
                "\t `city` varchar(20) NOT NULL,\n" +
                "   `fxDate` varchar(20) NOT NULL,\n" +
                "\t `tempMax` int(5) NOT NULL,\n" +
                "\t `tempMin` int(5) NOT NULL,\n" +
                "\t `textDay` varchar(10) NOT NULL,\n" +
                "\t PRIMARY KEY (`id`)                                  \n" +
                " ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8";

        try{
            weatherUpdate(create_1);
            weatherUpdate(create_2);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            close();
        }



    }

    public ResultSet weatherRecord(String sql) {
        try {
            //Class.forName(name);//指定连接类型
            conn = DriverManager.getConnection(url,user,password);//获取连接
            pst = conn.prepareStatement(sql);//准备执行语句
            ret = pst.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    public void weatherUpdate(String sql){
        try{
            //Class.forName(name);
            conn = DriverManager.getConnection(url,user,password);
            st = conn.createStatement();
            st.executeUpdate(sql);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            close();
        }

    }

    public void close() {
        try {
            if (conn != null) this.conn.close();
            if (pst != null) this.pst.close();
            if (st != null) this.st.close();
            if (ret != null) this.ret.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getId(String city){
        String id = null;
        try{
            ret = weatherRecord("SELECT id FROM cityData WHERE city = '"+ city +"'");
            while(ret.next()){
                id = ret.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close();
        }
        return id;
    }

    public void addCity(Map<String, Object> map){
        String sql = "INSERT IGNORE INTO cityData VALUES ("+
                map.get("id") + ",'" + map.get("name") + "'," + map.get("lat") +
                "," + map.get("lon") + ",'" + map.get("adm1") +"');";
        weatherUpdate(sql);
    }

    public void addWeather(Map<String, Object> map,int id){
        String city = "";
        try {
            ret = weatherRecord("SELECT city FROM cityData WHERE id = "+id);
            while(ret.next()){
                city = ret.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close();
        }
        String date = (String) map.get("fxDate");
        date = date.replace("-","");
        date = "" + id + date;
        String sql = "REPLACE INTO weatherData VALUES ("+
                date + ",'" + city + "','" + map.get("fxDate") +"'," + map.get("tempMax") +
                "," + map.get("tempMin") + ",'" + map.get("textDay") +"');";
        weatherUpdate(sql);
    }

    public void seeAllWeather(int num){
        int length = 0;
        try{
            ret = weatherRecord("SELECT COUNT(*) FROM weatherData;");
            while (ret.next()){
                length = Integer.parseInt(ret.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close();
        }
        String sql = "SELECT * FROM weatherData \n" +
                "ORDER BY id\n" +
                "LIMIT 5 OFFSET ";
        for (int i = 0,j = 1; i < length; i = i+5) {
            String s = sql + i + ";";
            System.out.println("Page : " + j + "\n" +
                    "======================================");
            printWeather(s,num);
            System.out.println("======================================");
            j++;
        }

    }

    public void cityWeather(String city,int num){
        String sql = "SELECT * FROM weatherData \n" +
                "WHERE city = '" +city +"';";
        printWeather(sql,num);
    }

    public void printWeather(String sql,int num){
        try {
            ret = weatherRecord(sql);
            while (ret.next()){
                String city = ret.getString(2);
                String fxDate = ret.getString(3);
                String max = ret.getString(4);
                String min = ret.getString(5);
                String text = ret.getString(6);

                if (num == 2){
                    System.out.println("City : "+ city + "\t Date : " + fxDate + "\n"+
                            "Temperature ：" + min + "~" + max);
                }else if (num == 1){
                    System.out.println("City : "+ city + "\t Date : " + fxDate + "\n"+
                            "Weather situation : " + text);
                }else{
                    System.out.println("City : "+ city + "\t Date : " + fxDate + "\n"+
                            "Temperature ：" + min + "~" + max + "\n "+
                            "Weather situation : " + text);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close();
        }
    }
}

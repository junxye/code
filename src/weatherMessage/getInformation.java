package weatherMessage;

import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;

public class getInformation {

    public sqlManage manage = new sqlManage();


    public Map<String, Object> cityInquire(String Cityid)
            throws IOException, NullPointerException {
        // 连接和风天气的API
        String url1= "https://geoapi.qweather.com/v2/city/lookup?key=fb941a0c8fa74ee196434012e5a6c2ac&location=";

        url1 += java.net.URLEncoder.encode(Cityid,"UTF-8");
        URL url = new URL(url1);
        URLConnection connectionData = url.openConnection();
        connectionData.setConnectTimeout(1000);
        Map<String, Object> information = new HashMap<String, Object>();

        GZIPInputStream gzipInputStream =new GZIPInputStream(connectionData.getInputStream());
        BufferedReader br = new BufferedReader(new
                InputStreamReader(gzipInputStream, StandardCharsets.UTF_8));
        try{

        StringBuilder data=new StringBuilder();
        String line;

        while ((line = br.readLine()) != null) {
            data.append(line);
        }

        String datas = data.toString();

        if (datas.substring(9,12).equals("400")){
            System.out.println("Wrong city name.");
            return null;
        }

            datas = datas.replace("[","");
            datas = datas.replace(datas.substring(datas.indexOf("}"),datas.length()-2),"");
            //System.out.println(datas);
            JSONObject jsonData = JSONObject.fromObject(datas);
            //JSONObject info = jsonData.getJSONObject("location");

            jsonData = jsonData.getJSONObject("location");

            information.put("name", jsonData.getString("name"));
            information.put("id", jsonData.getString("id"));
            information.put("lat", jsonData.getString("lat"));
            information.put("lon", jsonData.getString("lon"));
            information.put("adm1", jsonData.getString("adm1"));

            manage.addCity(information);
            //String week = strToDate(time);
            //map.put("week",week);

            //System.out.println(information);

        } catch (SocketTimeoutException e) {
            System.out.println("连接超时");
        } catch (FileNotFoundException e) {
            System.out.println("加载文件出错");
        } finally {
            //关闭流
            try {
                if(br!=null) br.close();

            } catch ( Exception  e) {
                e.printStackTrace();
            }
        }

        return information;

    }

    /* *
     * 时间获得星期
     * @param strDate
     * @return
     * @throws ParseException
     */
    /*
    public static String strToDate(String strDate) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(formatter.parse(strDate));
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        String week = sdf.format(c.getTime());
        return week;
    }

     */

    public void weatherInquire(String Cityid)
            throws IOException, NullPointerException {
        // 连接和风天气的API
        String url1= "https://devapi.qweather.com/v7/weather/3d?key=fb941a0c8fa74ee196434012e5a6c2ac&location="+Cityid;
        URL url = new URL(url1);
        URLConnection connectionData = url.openConnection();
        connectionData.setConnectTimeout(1000);
        Map<String, Object> information = new HashMap<String, Object>();

        GZIPInputStream gzipInputStream =new GZIPInputStream(connectionData.getInputStream());
        BufferedReader br = new BufferedReader(new
                InputStreamReader(gzipInputStream, StandardCharsets.UTF_8));
        try{

            StringBuilder data=new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                data.append(line);
            }

            String datas = data.toString();


            datas = datas.replace("[","");
            datas = datas.replace("]","");
            String datas_1 = datas.replace(datas.substring(datas.indexOf("}"),datas.length()-2),"");
            String datas_2 = datas.replace(datas.substring(0,datas.indexOf("}")+2),"");
            String datas_3 = datas_2.replace(datas_2.substring(0,datas_2.indexOf("}")+2),"");
            datas_2 = datas_2.replace(datas_2.substring(datas_2.indexOf("}"),datas_2.length()-1),"");
            //datas_3 = datas_3.replace(datas_3.substring(datas_3.length()-1),"");

            //System.out.println(datas_1);
            //System.out.println(datas_2);
            //System.out.println(datas_3);
            JSONObject jsonData_1 = JSONObject.fromObject(datas_1);
            JSONObject jsonData_2 = JSONObject.fromObject(datas_2);
            JSONObject jsonData_3 = JSONObject.fromObject(datas_3);
            //JSONObject info = jsonData.getJSONObject("location");

            jsonData_1 = jsonData_1.getJSONObject("daily");
            //System.out.println(jsonData);

            for (int i = 0; i < 3; i++) {
                JSONObject jsonData;
                if (i == 0) jsonData = jsonData_1;
                else if (i == 1) jsonData = jsonData_2;
                else jsonData = jsonData_3;

                information.put("fxDate", jsonData.getString("fxDate"));
                information.put("tempMax", jsonData.getString("tempMax"));
                information.put("tempMin", jsonData.getString("tempMin"));
                information.put("textDay", jsonData.getString("textDay"));

                manage.addWeather(information,Integer.parseInt(Cityid));
            }



            //String week = strToDate(time);
            //map.put("week",week);

            //System.out.println(information);

        } catch (SocketTimeoutException e) {
            System.out.println("连接超时");
        } catch (FileNotFoundException e) {
            System.out.println("加载文件出错");
        } finally {
            //关闭流
            try {
                if(br!=null) br.close();

            } catch ( Exception  e) {
                e.printStackTrace();
            }
        }

    }
}

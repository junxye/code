package weatherMessage;



import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class read_post {

    public static void main(String[] args) throws IOException
    {/*
        HttpClient client = new HttpClient();
        // 设置代理服务器地址和端口
        //client.getHostConfiguration().setProxy("proxy_host_addr",proxy_port);
        // 使用 GET 方法 ，如果服务器需要通过 HTTPS 连接，那只需要将下面 URL 中的 http 换成 https
        HttpMethod method=new GetMethod("http://java.sun.com");
        //使用POST方法
        //HttpMethod method = new PostMethod("http://java.sun.com");
        client.executeMethod(method);

        //打印服务器返回的状态
        System.out.println(method.getStatusLine());
        //打印返回的信息
        System.out.println(method.getResponseBodyAsString());
        //释放连接
        method.releaseConnection();*/

        HttpURLConnection connection = (HttpURLConnection)new URL("https://geoapi.qweather.com/v2/city/lookup?key=" +
                "fb941a0c8fa74ee196434012e5a6c2ac&location=101010100").openConnection();
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        OutputStream out = connection.getOutputStream();
        out.write("post传递的数据".getBytes());
        out.close();

        InputStream in = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line = null;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        in.close();

        if (connection != null) connection.disconnect();
        if (out != null) out.close();
        if (in != null) in.close();

    }



}

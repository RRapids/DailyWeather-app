package com.WL.Functionalprogramming;

/**
 * @author wl
 * @ClassNameHTTPUtil
 * @Description TODO
 * @Date 2020/2/22
 * @Version 1.0
 */



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class HttpJavaPost {
    public static String sendPost(String url, String param ,String param2) throws IOException {
        StringBuffer stringBuffer =new StringBuffer();
        stringBuffer.append(url);
        stringBuffer.append("?");
        stringBuffer.append("cityname=");
        stringBuffer.append(param);
        stringBuffer.append("&");
        stringBuffer.append("dtype=&format=");
        stringBuffer.append("&");
        stringBuffer.append("key=");
        stringBuffer.append(param2);
        String url1=new String(stringBuffer);
        System.out.println(stringBuffer);
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url1);
            URLConnection conn = realUrl.openConnection();// 设置通用请求的属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "keep-Alive");
            conn.setRequestProperty("Content-Type", "application/json");// 我加的一个头
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 我加的一个头
//            conn.setRequestProperty("", "");

            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
//            out = new PrintWriter(conn.getOutputStream());
//            // 发送请求参数
//            out.print(param);
//            // flush输出流的缓冲
//            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            try {
                while ((line = in.readLine()) != null) {
                    result += line;

                }
            } catch (IOException e) {
                System.out.println("发送POST请求出现异常");
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        finally {
            if (out != null) {
                out.close();
            }
            if (in != null) {
                in.close();
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        //发送 POST请求
        String sr = HttpJavaPost.sendPost("http://v.juhe.cn/weather/index", "北京","40d4ac150df5b8b213c564da46cc5a66");
        System.out.println(sr);

    }

}



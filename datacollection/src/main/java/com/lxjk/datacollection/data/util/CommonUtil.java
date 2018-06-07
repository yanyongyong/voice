package com.lxjk.datacollection.data.util;

import com.lxjk.datacollection.config.ChangeSpringConfig;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

/**
 * @Author: ziv
 * @Date: 2018/6/1 13:34
 * @Description:
 */
@Component
public class CommonUtil {

    public static boolean isUrlExist(String serverUrl) {
        try {
            URL url = new URL(serverUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            return connection.getResponseCode() == 200;
        } catch (IOException e) {
            return false;
        }
    }

    public String getProperChines(String name)throws IOException{
        Properties p = new Properties();
        InputStream inputStream = ChangeSpringConfig.class.getClassLoader().getResourceAsStream("application.properties");
//      直接使用p.get("name")会存在乱码问题 ，需要new String(name.getBytes("ISO-8859-1"),"UTF-8")转换
//      System.out.println(new String(name.getBytes("ISO-8859-1"),"UTF-8"));
//      使用Reader可以正常读取中文字符
        p.load(new InputStreamReader(inputStream, "UTF-8"));
        return (String) p.get(name);
    }

    public static void main(String[] args) {
        System.out.println(isUrlExist("https://blog.csdn.net/classicbear/article/details/7664042"));
    }
}

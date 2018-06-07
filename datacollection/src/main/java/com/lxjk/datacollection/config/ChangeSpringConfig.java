package com.lxjk.datacollection.config;


import lombok.Getter;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.support.XmlWebApplicationContext;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @Author: ziv
 * @Date: 2018/5/30 09:21
 * @Description:
 */
@Slf4j
public class ChangeSpringConfig{

    public static void updateProperties(String fileName,Map<String, String> keyValueMap) {
        /**getResource方法使用了utf-8对路径信息进行了编码，当路径中存在中文和空格时，他会对这些字符进行转换，这样，
        得到的往往不是我们想要的真实路径，在此，调用了URLDecoder的decode方法进行解码，以便得到原始的中文及空格路径。*/
        String filePath = PropertiesUtil.class.getClassLoader().getResource(fileName).getFile();
        Properties props =  new Properties();
        BufferedWriter bw = null;
        try {
//            filePath = URLDecoder.decode(filePath,"UTF-8");
            log.debug("updateProperties propertiesPath:" + filePath);
            FileInputStream fis = new FileInputStream(filePath);
            BufferedReader bf = new BufferedReader(new InputStreamReader(fis,"UTF-8"));
//            BufferedReader bf = new BufferedReader(new InputStreamReader(fis,"Unicode")));
//            props.load(new InputStreamReader(ChangeSpringConfig.class.getClassLoader().getResourceAsStream(fileName), "UTF-8"));
            props.load(bf);
            log.debug("updateProperties old:"+props);
            // 写入属性文件
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath),"UTF-8"));
//            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath),"Unicode"));
            props.clear();// 清空旧的文件
            for (String key : keyValueMap.keySet())
                props.setProperty(key, keyValueMap.get(key));
            props.store(bw, "");
        } catch (IOException e) {
            log.error(e.getMessage());
        } finally {
            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}

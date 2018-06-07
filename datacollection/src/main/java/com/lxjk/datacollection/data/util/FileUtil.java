package com.lxjk.datacollection.data.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @Author: ziv
 * @Date: 2018/5/22 16:22
 * @Description: 文件操作工具类
 */
@Component
public class FileUtil {

    @Value("${mylocal.fileSave}")
    private  String fileSavePath;
    @Value("${mylocal.lineNumber}")
    private String lineNumber;

    /**
     * @Description: 文件压缩（单个文件）
     * @param  dataJsonName 文件名
     */
    public void zipOneFile(String dataJsonName) throws IOException {
        File file = new File(fileSavePath + File.separator + "test.txt");
        File zipFile = new File(fileSavePath + File.separator + "hello.zip");
        InputStream input = new FileInputStream(file);
        ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(
                zipFile));
        zipOut.putNextEntry(new ZipEntry(file.getName()));
        /**设置注释*/
//        zipOut.setComment("hello");
        int temp = 0;
        while((temp = input.read()) != -1){
            zipOut.write(temp);
        }
        input.close();
        zipOut.close();
    }

    /**
     * @Auther: ＾＿－)≡★ yanyong
     * @Description: 写入文件
     */
    public void saveDataToFile(String fileName,String data,Boolean isAppend){
        if (isAppend){
            data = data + "\r\n";
        }
        BufferedWriter writer = null;
        File file = new File(fileSavePath + fileName);
        File fileParent = file.getParentFile();
        if(!fileParent.exists()){
            fileParent.mkdirs();
        }
        /**如果文件不存在，则新建一个*/
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        /**写入*/
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,isAppend), "UTF-8"));
            writer.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(writer != null){
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @Description: 保存读取位置（时间为判断）
     */
    public String readLastLine(String fileName) throws FileNotFoundException {
        File file = new File(fileSavePath + fileName);
        File fileParent = file.getParentFile();
        if(!fileParent.exists()){
            fileParent.mkdirs();
        }
        /**如果文件不存在，则新建一个*/
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Scanner sc = new Scanner(new FileReader(fileSavePath + fileName));
        String line = null;
        while((sc.hasNextLine()&&(line = sc.nextLine())!= null)){
            if(!sc.hasNextLine()){
               return line;
            }
        }
        return null;
    }

    public Boolean fileIsExists(String fileName){
        File file = new File(fileSavePath + fileName);
        /**如果文件不存在，则新建一个*/
        if(!file.exists()){
           return false;
        }
        return true;
    }

}

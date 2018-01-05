package Test;

import java.io.*;

/**
 * Description:
 *
 * @author heyefu
 * Create in: 2017-12-12
 * Time: 13:28
 **/
public class TestIo {

    public static void main(String[] args) {

        String osName = System.getProperties().getProperty("os.name");

        System.out.println("-----操作系统名称是:" + osName);

        System.out.println("测试git config --global credential.helper store");

        File file_1 = null;
        File file_2 = null;

        if (osName.indexOf("Linux") > -1){
            System.out.println("Linux操作系统");
            file_1 = new File("/home","IO.txt");
            file_2 = new File("/home","IO2.txt");
        }else {
            System.out.println("非Linux操作系统");
            file_1 = new File("F:/测试IO.txt");
            file_2 = new File("F:/测试IO2.txt");
        }


        if (!file_1.exists()){
            try {
                file_1.createNewFile();
            } catch (IOException e) {
                System.out.println("创建文件发生错误！");
                e.printStackTrace();
            }
        }else{
            System.out.println("file_1已经存在！");
        }



        FileWriter fileWriter = null;
        FileReader fileReader = null;

        try {
            fileReader = new FileReader(file_1);
        } catch (FileNotFoundException e) {
            System.out.println("没有找到文件file_1!");
            e.printStackTrace();
        }
        char[] testIo = new char[10];
        int len = 0;
        try {
            fileWriter = new FileWriter(file_2);
            while ((len = fileReader.read(testIo)) != -1){
                String string = new String(testIo, 0, len);
                fileWriter.write(string);
                fileWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fileReader.close();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("文件流关闭异常!");
                e.printStackTrace();
            }
        }

    }

}

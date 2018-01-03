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

    public static void main(String[] args) throws IOException {

        String osName = System.getProperties().getProperty("os.name");

        System.out.println("-----操作系统名称是:" + osName);

        System.out.println("测试git config --global credential.helper store");
        File file_1 = null;
        File file_2 = null;

        if (osName.indexOf("Linux") > -1){
            System.out.println("Linux操作系统");
            file_1 = new File("/home","IO.txt");
            file_2 = new File("/home","IO.txt");
        }else {
            System.out.println("非Linux操作系统");
            file_1 = new File("F:/测试IO.txt");
            file_2 = new File("F:/测试IO2.txt");
        }



        FileWriter fileWriter = null;
        FileReader fileReader = null;

        fileReader = new FileReader(file_1);
        fileWriter = new FileWriter(file_2);

        char[] testIo = new char[10];
        int len = 0;
        while ((len = fileReader.read(testIo)) != -1){
            String string = new String(testIo, 0, len);
            fileWriter.write(string);
            fileWriter.flush();
        }
        fileReader.close();
        fileWriter.close();
    }

}

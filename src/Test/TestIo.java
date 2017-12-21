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

        File file_1 = new File("F:/测试IO.txt");
        File file_2 = new File("F:/测试IO2.txt");


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

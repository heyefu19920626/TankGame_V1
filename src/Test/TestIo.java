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

        FileWriter fileWriter = null;


        try {
            fileWriter = new FileWriter("F:/测试IO.txt");
            fileWriter.write("测试IO第二次");
            fileWriter.flush();
            fileWriter.write("1234445阿大纲");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

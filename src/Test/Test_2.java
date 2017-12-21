package Test;

import java.io.*;

/**
 * Description:
 *
 * @author heyefu
 * Create in: 2017-12-21
 * Time: 11:44
 **/
public class Test_2 {

    public static void main(String[] args) {
        File file_2 = new File("F:/测试IO3.txt");
        File file_1 = new File("F:/测试IO.txt");


        InputStream in = null;
        OutputStream out = null;

        try {
            in = new FileInputStream(file_1);
            out = new FileOutputStream(file_2);
            InputStreamReader inReader = new InputStreamReader(in);

            char[] test = new char[12];
            int len = 0;
            while ((len = inReader.read(test,0,6)) != -1){
                String str = new String(test);
//                String str = new String(test, 0, len);
                out.write(str.getBytes());
                out.flush();
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


//        InputStream in = null;
//        OutputStream out = null;
//        try {
//            in = new FileInputStream(file_1);
//            out = new FileOutputStream(file_2);
//            缓冲区过小导致乱码
//            byte[] bytes = new byte[12];
//            int len = 0;
//            while ((len = in.read(bytes)) != -1){
//                String str = new String(bytes);
//                out.write(str.getBytes());
//            }
//        } catch(FileNotFoundException e){
//                e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }finally {
//            try {
//                in.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            try {
//                out.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

    }
}

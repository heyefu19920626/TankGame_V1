package Test;

import java.io.File;

/**
 * Description:
 *
 * @author heyefu
 * Create in: 2018-01-05
 * Time: 下午6:48
 **/
public class Test_IO_List {

    public static void main(String[] args) {
        File fileList = new File("/home/heyefu/");

        if (fileList.isDirectory()){
            File []list = fileList.listFiles();
            for (int i = 0; i < list.length; i++) {
                System.out.println(list[i].getName());
            }
        }

    }


}

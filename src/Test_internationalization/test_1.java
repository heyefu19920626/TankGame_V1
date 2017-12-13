package Test_internationalization;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * Description:
 *
 * @author heyefu
 * Create in: 2017-12-13
 * Time: 16:23
 **/
public class test_1 {
    public static void main(String[] args) {
        //创建一个本地语言环境对象(中文)
        Locale locale = new Locale("zh", "CN");
        Locale locale1 = Locale.US;
        Locale locale2 = Locale.getDefault();

        ResourceBundle res;
        int i = 0;
        //通过ResourceBundle工具类绑定资源文件(包名.文件名(基本名称不包括语言部分和后缀部分))
        if (i == 0)
            res = ResourceBundle.getBundle("info", locale);
        else
            res = ResourceBundle.getBundle("info", locale1);

        //模拟用户登陆
        Scanner in = new Scanner(System.in);

        //从属性文件中根据key获取value值
        String input = res.getString("input");
        String username = res.getString("username");
        String passwd = res.getString("passwd");
        String infoSuccess = res.getString("info.success");
        String infoError = res.getString("info.error");
        System.out.println(input + username);
        String userName = in.next();
        System.out.println(input + passwd);
        String password = in.next();

        if ("admin".equals(userName) && "123".equals(password)) {
            //处理动态文本(模式， 要替换的值……)
            String success = MessageFormat.format(infoSuccess, userName);
            System.out.println(success);
        } else {
            System.out.println(infoError);
        }
    }
}

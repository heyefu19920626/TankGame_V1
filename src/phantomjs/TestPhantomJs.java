package phantomjs;

import jdk.internal.org.xml.sax.SAXException;

import java.io.*;

/**
 * Description:
 * 测试java调用phantomJS执行js脚本
 * @author heyefu
 * Create in: 2018-01-12
 * Time: 16:32
 **/
public class TestPhantomJs {
    // 如果要更换运行环境，请注意exePath最后的phantom.exe需要更改。因为这个只能在window版本上运行。前面的路径名
    // 也需要和exePath里面的保持一致。否则无法调用
    private static String projectPath = System.getProperty("user.dir");
    private static String jsPath = projectPath + File.separator +"src" + File.separator + "js" + File.separator + "huicong.js";
    private static String jsPath_1 = projectPath + File.separator +"src" + File.separator + "js" + File.separator + "baidu.js";
    //    private static String exePath = projectPath + File.separator + "phantomjs" + File.separator + "bin" + File.separator
//            + "phantomjs.exe";
    private static String exePath = projectPath + File.separator + "src"  + File.separator + "tools" + File.separator + "phantomjs.exe";

    public static void main(String[] args) throws IOException, SAXException
    {

        // 测试调用。传入url即可
        System.out.println(TestPhantomJs.projectPath);
        String html = getParseredHtml2("http://huisheng99.b2b.hc360.com/");
        System.out.println("html: " + html);
    }

    // 调用phantomjs程序，并传入js文件，并通过流拿回需要的数据。
    public static String getParseredHtml2(String url) throws IOException
    {
        Runtime rt = Runtime.getRuntime();
        Process p = rt.exec(exePath + " " + jsPath_1 + " " + url);
        InputStream is = p.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuffer sbf = new StringBuffer();
        String tmp = "";
        while ((tmp = br.readLine()) != null)
        {
            sbf.append(tmp);
        }
        System.out.println(sbf.toString());
        String[] result = sbf.toString().split("companyServiceMod");
        String result2 = "";
        if(result.length >= 2)
        {
            result2 = result[1];
            if(result2.length() > 200)
            {
                result2 = result2.substring(0, 200);
            }
        }
        System.out.println("resut2: "+result2);
        return result2;
    }
}

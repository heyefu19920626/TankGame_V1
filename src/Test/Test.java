package Test;

/**
 * Description:
 * 测试&&与||混合使用
 *
 * @author heyefu
 * Create in: 2018-01-10
 * Time: 下午10:34
 **/
public class Test {
    public static void main(String[] args) {
        Test test_1 = new Test();
        System.out.println(test_1.testAndOr());
    }

    public boolean testAndOr() {
        return 1 < 2 && 4 > 5 || 4 < 5 && 7 > 9;
    }
}

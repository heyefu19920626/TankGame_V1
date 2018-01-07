package TankGame_V1;

import javax.swing.*;
import java.awt.*;

/**
 * Description:
 * 坦克大战主面板
 *
 * @author heyefu
 * Create in: 2017-12-07
 * Time: 16:13
 **/
public class MainFrame extends JFrame {

    private BeginPanel beginPanel = null;
    private MainPanel mainPanel = null;

    public MainFrame() {

        beginPanel = new BeginPanel();
        this.add(beginPanel);
//        mainPanel = new MainPanel();
//        Thread t = new Thread(mainPanel);
//        t.start();
////        注册面板的按键事件
//        this.addKeyListener(mainPanel);

//        this.getContentPane().setVisible(false);
//        this.setBackground(Color.green);
        this.getContentPane().setBackground(Color.green);
//        this.add(mainPanel);
        this.setTitle("坦克大战1.0");
        this.setBounds(600, 300, 600, 600);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}

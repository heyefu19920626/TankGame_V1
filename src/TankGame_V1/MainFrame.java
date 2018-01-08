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
public class MainFrame extends JFrame implements Runnable {

    private BeginPanel beginPanel = null;
    private MainPanel mainPanel = null;

    public MainFrame() {

        beginPanel = new BeginPanel();
        Thread t_BeaginPanel = new Thread(beginPanel);
        t_BeaginPanel.start();
        this.add(beginPanel);
        this.addMouseListener(beginPanel);
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
        MainFrame mainFrame = new MainFrame();
        Thread t = new Thread(mainFrame);
        t.start();
    }

    /**
     * Description:
     * 开始游戏，移除开始面板，增加游戏主面板
     *
     * @param
     * @return void
     * @author heyefu 15:16 2018/1/8
     **/
    public void startGame() {
        this.remove(beginPanel);
        beginPanel = null;
        mainPanel = new MainPanel();
        Thread t_MainPanel = new Thread(mainPanel);
        t_MainPanel.start();
        this.add(mainPanel);
        this.setVisible(true);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (this.beginPanel.isBegin) {
                this.startGame();
                break;
            }
        }
    }
}

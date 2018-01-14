package TankGame_V1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Description:
 * 坦克大战主面板
 *
 * @author heyefu
 * Create in: 2017-12-07
 * Time: 16:13
 **/
public class MainFrame extends JFrame implements Runnable, ActionListener {

    //    开始面板
    private BeginPanel beginPanel = null;
    //    主游戏面板
    private MainPanel mainPanel = null;
    //    工具栏
    private JMenuBar menuBar = null;
    //    游戏菜单
    private JMenu game = null;
    //    开始子菜单
    private JMenuItem startGame = null;
    //    保存子菜单
    private JMenuItem saveGame = null;
    //    读取子菜单
    private JMenuItem readGame = null;

    public MainFrame() {

        menuBar = new JMenuBar();
        game = new JMenu("游戏");
        startGame = new JMenuItem("开始游戏");
        saveGame = new JMenuItem("保存游戏");
        readGame = new JMenuItem("读取游戏");

        this.setJMenuBar(menuBar);
        menuBar.add(game);
        game.add(startGame);
        game.add(saveGame);
        game.add(readGame);

        startGame.setActionCommand("startGame");
        startGame.addActionListener(this);
        saveGame.setActionCommand("saveGame");
        saveGame.addActionListener(this);
        readGame.setActionCommand("readGame");
        readGame.addActionListener(this);

        beginPanel = new BeginPanel();
        Thread t_BeaginPanel = new Thread(beginPanel);
        t_BeaginPanel.start();
        this.add(beginPanel);
        this.addMouseListener(beginPanel);

        this.getContentPane().setBackground(Color.green);

        this.setTitle("坦克大战1.0");
        this.setBounds(600, 300, 700, 700);
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
        this.addKeyListener(mainPanel);
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
            if (beginPanel != null) {
                if (this.beginPanel.isBegin) {
                    this.startGame();
                    break;
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("startGame")) {
            if (this.beginPanel == null && mainPanel != null) {
                System.out.println("游戏已经开始!");
                return;
            }
            this.startGame();
        } else if (e.getActionCommand().equals("saveGame")) {
            this.saveGame();
        } else if (e.getActionCommand().equals("readGame")) {
            HashMap<String, ArrayList<String>> record = RecordGame.readRecord();
            this.continueGame(record);
        }

    }


    /**
     * Description:
     * 保存游戏
     *
     * @param
     * @return void
     * @author heyefu 下午8:46 18-1-14
     **/
    private void saveGame() {
        RecordGame.saveGame(mainPanel.myTank, mainPanel.getKillEnemys(), mainPanel.getEnemyCount(), mainPanel.getEnemyTanks());
    }


    /**
     * Description:
     * 继续游戏
     *
     * @param record 读取的存档数据
     * @return void
     * @author heyefu 下午8:45 18-1-14
     **/
    private void continueGame(HashMap<String, ArrayList<String>> record) {

//        取出自己坦克属性
        ArrayList<String> myTank = record.get("myTank");
//        取出击杀坦克数量及剩余坦克数量
        ArrayList<String> number = record.get("number");
//        取出敌方坦克属性
        ArrayList<String> enemy = record.get("enemy");

        if (beginPanel != null) {
            this.remove(beginPanel);
            this.beginPanel = null;
        } else if (mainPanel != null) {
            this.remove(mainPanel);
            this.mainPanel = null;
        }


        mainPanel = new MainPanel(myTank, number, enemy);
        Thread t = new Thread(mainPanel);
        t.start();

        this.add(mainPanel);
        this.addKeyListener(mainPanel);
        this.setVisible(true);
    }
}

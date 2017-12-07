package TankGame_V1;

import javax.swing.*;
import java.awt.*;

/**
 * Description:
 * 坦克游戏活动面板
 * @author heyefu
 * Create in: 2017-12-07
 * Time: 16:38
 **/
public class MainPanel extends JPanel {
    /**
    * Description: 活动面板宽度
    */
    final int width = 500;
    /**
    * Description: 活动面板高度
    */
    final int height = 500;
    /**
    * Description: 我的坦克
    */
    MyTank myTank;

    public MainPanel() {
        this.setSize(500,500);
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLACK);
        g.fillRect(0,0,500,500);
    }
    
    /**
    *Description:
     * @param g
     * @param x
     * @param y
     * @param direction
     * @param who
    *@return void
    *@author heyefu 22:51 2017/12/7
    **/
    public void paintTank(Graphics g, int x,int y,int direction,int who){
        
    }
}
package TankGame_V1;

import com.sun.org.apache.xpath.internal.SourceTree;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Description:
 * 坦克活动面板
 * @author heyefu
 * Create in: 2017-12-07
 * Time: 16:38
 **/
public class MainPanel extends JPanel implements KeyListener{
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
        myTank = new MyTank(200,200,(int)(Math.random()*4));
        this.setSize(500,500);
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLACK);
        g.fillRect(0,0,500,500);
        paintTank(g,myTank.getX(),myTank.getY(),myTank.getDirection(),(int)(Math.random()*2));
    }
/**
    *Description:
     * @param g
     * @param x
     * @param y
     * @param direction
     * @param type
    *@return void
    *@author heyefu 13:13 2017/12/9
    **/
    public void paintTank(Graphics g, int x,int y,int direction,int type){
        switch (type){
            case 0:
                g.setColor(Color.cyan);
                break;
            default:
                g.setColor(Color.red);
        }
        switch (direction){
//            坦克方向向上
            case 0:
//                画出左边矩形
                g.fill3DRect(x,y,5,30,false);
//                画出右边矩形
                g.fill3DRect(x+15,y,5,30,false);
//                画出中间矩形
                g.fill3DRect(x+5,y+5,10,20,false);
//                画出中间圆
                g.fillOval(x+5,y+10,10,10);
//                画出炮管
                g.drawLine(x+10,y+15,x+10,y);
                break;
//            坦克方向向右
            case 1:
                g.fill3DRect(x,y,30,5,false);
                g.fill3DRect(x,y+15,30,5,false);
                g.fill3DRect(x+5,y+5,20,10,false);
                g.fillOval(x+10,y+5,10,10);
                g.drawLine(x+15,y+10,x+30,y+10);
                break;
//            坦克方向向下
            case 2:
                g.fill3DRect(x,y,5,30,false);
                g.fill3DRect(x+15,y,5,30,false);
                g.fill3DRect(x+5,y+5,10,20,false);
                g.fillOval(x+5,y+10,10,10);
                g.drawLine(x+10,y+15,x+10,y+30);
                break;
//            坦克方向向左
            case 3:
                g.fill3DRect(x,y,30,5,false);
                g.fill3DRect(x,y+15,30,5,false);
                g.fill3DRect(x+5,y+5,20,10,false);
                g.fillOval(x+10,y+5,10,10);
                g.drawLine(x+15,y+10,x,y+10);
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
    /**
    *Description: 监听按键移动坦克
     * @param e
    *@return void
    *@author heyefu 17:02 2017/12/9
    **/
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP){
            myTank.moveUp();
            myTank.setDirection(0);
        }else if (e.getKeyCode() == KeyEvent.VK_DOWN){
            myTank.moveDown();
            myTank.setDirection(2);
        }else if (e.getKeyCode() == KeyEvent.VK_LEFT){
            myTank.moveLeft();
            myTank.setDirection(3);
        }else if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            myTank.moveRight();
            myTank.setDirection(1);
        }

        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
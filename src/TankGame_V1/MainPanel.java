package TankGame_V1;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Description:
 * 坦克活动面板
 *
 * @author heyefu
 * Create in: 2017-12-07
 * Time: 16:38
 **/
public class MainPanel extends JPanel implements KeyListener, Runnable {
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
        myTank = new MyTank(200, 200, (int) (Math.random() * 4));
        this.setSize(500, 500);
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 500, 500);
        paintTank(g, myTank.getX(), myTank.getY(), myTank.getDirection(), 0);
//        if (myTank.bullets.size() > 0) {
//            for (Bullet bullet : myTank.bullets
//                    ) {
//                if (bullet.isLive == true) {
//                    paintBullet(g, bullet);
//                }
//                if (bullet.isLive == false){
//                    myTank.bullets.remove(bullet);
//                }
//            }
//        }
        for (int i = 0; i < myTank.bullets.size(); i++) {
            Bullet bullet = myTank.bullets.get(i);
            if (bullet.isLive == true && bullet != null) {
                paintBullet(g, bullet);
            }

            if (bullet.isLive == false) {
                myTank.bullets.remove(i);
            }
        }
    }

    /**
     * Description:
     *
     * @param g
     * @param x
     * @param y
     * @param direction
     * @param type
     * @return void
     * @author heyefu 13:13 2017/12/9
     **/
    public void paintTank(Graphics g, int x, int y, int direction, int type) {
        switch (type) {
            case 0:
                g.setColor(Color.cyan);
                break;
            default:
                g.setColor(Color.red);
        }
        switch (direction) {
//            坦克方向向上
            case 0:
//                画出左边矩形
                g.fill3DRect(x - 10, y - 15, 5, 30, false);
//                画出右边矩形
                g.fill3DRect(x + 5, y - 15, 5, 30, false);
//                画出中间矩形
                g.fill3DRect(x - 5, y - 10, 10, 20, false);
//                画出中间圆
                g.fillOval(x - 5, y - 5, 10, 10);
//                画出炮管
                g.drawLine(x, y, x, y - 15);
                break;
//            坦克方向向右
            case 1:
                g.fill3DRect(x - 15, y - 10, 30, 5, false);
                g.fill3DRect(x - 15, y + 5, 30, 5, false);
                g.fill3DRect(x - 10, y - 5, 20, 10, false);
                g.fillOval(x - 5, y - 5, 10, 10);
                g.drawLine(x, y, x + 15, y);
                break;
//            坦克方向向下
            case 2:
                g.fill3DRect(x - 10, y - 15, 5, 30, false);
                g.fill3DRect(x + 5, y - 15, 5, 30, false);
                g.fill3DRect(x - 5, y - 10, 10, 20, false);
                g.fillOval(x - 5, y - 5, 10, 10);
                g.drawLine(x, y, x, y + 15);
                break;
//            坦克方向向左
            case 3:
                g.fill3DRect(x - 15, y - 10, 30, 5, false);
                g.fill3DRect(x - 15, y + 5, 30, 5, false);
                g.fill3DRect(x - 10, y - 5, 20, 10, false);
                g.fillOval(x - 5, y - 5, 10, 10);
                g.drawLine(x, y, x - 15, y);
                break;
        }
    }

    /**
     * Description: 画出子弹
     *
     * @param g
     * @return void
     * @author heyefu 19:26 2017/12/9
     **/
    public void paintBullet(Graphics g, Bullet bullet) {
        g.setColor(Color.red);
        g.drawOval(bullet.getX(), bullet.getY(), 1, 1);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Description: 监听按键移动坦克
     *
     * @param e
     * @return void
     * @author heyefu 17:02 2017/12/9
     **/
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            myTank.moveUp();
            myTank.setDirection(0);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            myTank.moveDown();
            myTank.setDirection(2);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            myTank.moveLeft();
            myTank.setDirection(3);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            myTank.moveRight();
            myTank.setDirection(1);
        }

        if (e.getKeyCode() == KeyEvent.VK_J) {
            this.myTank.shot(myTank.getX(), myTank.getY(), myTank.getDirection());
        }

        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        while (true) {

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            this.repaint();
        }
    }

}
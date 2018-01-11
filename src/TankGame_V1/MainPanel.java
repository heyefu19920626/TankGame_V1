package TankGame_V1;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

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
    /**
     * Description: 敌人的坦克
     */
    Vector<EnemyTank> enemyTanks = new Vector<EnemyTank>();
    /**
     * Description:  敌人坦克的数量
     */
    private int enemyCount = 5;
    /**
     * Description: 爆炸
     */
    Vector<Bomb> bombs = new Vector<Bomb>();
    /**
     * Description: 击杀的敌方坦克数量
     */
    private int killEnemys = 0;

    public MainPanel() {
        myTank = new MyTank(200, 200, (int) (Math.random() * 4));
        for (int i = 0; i < enemyCount; i++) {
            EnemyTank enemyTank = new EnemyTank((int) (Math.random() * width), (int) (Math.random() * height), (int) (Math.random() * 4));
            //将面板中的敌人坦克传递给创建的每个坦克
            enemyTank.setEnemyTanks(enemyTanks);
            Thread t = new Thread(enemyTank);
            t.start();
            enemyTanks.add(enemyTank);
        }
        this.setSize(500, 500);
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);
//        画出自己的坦克
        if (myTank != null) {
            if (myTank.isLive)
                paintTank(g, myTank.getX(), myTank.getY(), myTank.getDirection(), 0);
            else
                myTank = null;
        }
//        画出敌人的坦克
        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemyTank = enemyTanks.get(i);
//            画出敌人的子弹
            for (int j = 0; j < enemyTank.bullets.size(); j++) {
                Bullet bullet = enemyTank.bullets.get(j);
                if (bullet.isLive) {
                    paintBullet(g, bullet);
                } else {
                    enemyTank.bullets.remove(bullet);
                }
            }
            if (enemyTank.isLive) {
                paintTank(g, enemyTank.getX(), enemyTank.getY(), enemyTank.getDirection(), 1);
            }
        }

//        画出敌人坦克标志
        paintTank(g, 230, 550, 0, 1);
        g.setFont(new Font("华文行楷", Font.BOLD, 24));
//        画出剩余敌人坦克数量
        g.drawString(Integer.toString(enemyCount), 225, 600);

//        画出消灭的敌方坦克标志
        paintTank(g, 550, 250, 0, 1);
//        画出消灭的敌方坦克总数
        g.drawString(String.valueOf(killEnemys), 545, 300);


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
//        画出自己坦克的子弹
        if (myTank != null) {
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

//        画出爆炸效果
        for (int i = 0; i < bombs.size(); i++) {
            Bomb bomb = bombs.get(i);
            paintBomb(g, bomb);
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
     * @param bullet
     * @return void
     * @author heyefu 14:45 2017/12/12
     **/
    public void paintBullet(Graphics g, Bullet bullet) {
        g.setColor(Color.red);
        g.drawOval(bullet.getX(), bullet.getY(), 1, 1);
    }

    /**
     * Description: 画出坦克被击中的爆炸效果
     *
     * @param g
     * @param bomb
     * @return void
     * @author heyefu 22:12 2017/12/11
     **/
    public void paintBomb(Graphics g, Bomb bomb) {
        if (bomb.image_life > 6) {
            g.drawImage(bomb.bomb_1, bomb.x, bomb.y, 20, 30, null);
        } else if (bomb.image_life > 3) {
            g.drawImage(bomb.bomb_2, bomb.x, bomb.y, 20, 30, null);
        } else {
            g.drawImage(bomb.bomb_3, bomb.x, bomb.y, 20, 30, null);
        }
        bomb.image_life--;
        if (bomb.image_life <= 0)
            bombs.remove(bomb);
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
        if (myTank == null)
            return;

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            myTank.setDirection(0);
            if (myTank.getY() - 15 > 0)
                myTank.moveUp();
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            myTank.setDirection(2);
            if (myTank.getY() + 15 < height)
                myTank.moveDown();
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            myTank.setDirection(3);
            if (myTank.getX() - 15 > 0)
                myTank.moveLeft();
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            myTank.setDirection(1);
            if (myTank.getX() + 15 < width)
                myTank.moveRight();
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

            if (myTank != null && enemyTanks.size() > 0) {
                judgeEnemyIsLive();
                judgeMyTankIsLive();
            }


            this.repaint();
        }
    }

    /**
     * Description: 判断敌人坦克是否还存活
     *
     * @param
     * @return void
     * @author heyefu 14:02 2017/12/10
     **/
    public void judgeEnemyIsLive() {
//        取出我的坦克的每一颗子弹
        for (int i = 0; i < myTank.bullets.size(); i++) {
            Bullet bullet = myTank.bullets.get(i);
            int bulletX = bullet.getX();
            int bulletY = bullet.getY();
            for (int j = 0; j < enemyTanks.size(); j++) {
                EnemyTank enemyTank = enemyTanks.get(j);
                int enemyTankX = enemyTank.getX();
                int enemyTankY = enemyTank.getY();
                if (enemyTank.getDirection() == 0 || enemyTank.getDirection() == 2) {
                    if (enemyTankY + 15 >= bulletY && bulletY >= enemyTankY - 15 && enemyTankX - 10 <= bulletX && bulletX <= enemyTankX + 10) {
                        enemyTank.isLive = false;
                        Bomb bomb = new Bomb(enemyTank.getX() - 15, enemyTank.getY() - 15);
                        bombs.add(bomb);
                        enemyTanks.remove(enemyTank);
                        enemyCount--;
                        killEnemys++;
                        bullet.isLive = false;
                        myTank.bullets.remove(bullet);
                    }
                } else {
                    if (enemyTankY + 10 >= bulletY && bulletY >= enemyTankY - 10 && enemyTankX - 15 <= bulletX && bulletX <= enemyTankX + 15) {
                        enemyTank.isLive = false;
                        Bomb bomb = new Bomb(enemyTank.getX() - 15, enemyTank.getY() - 15);
                        bombs.add(bomb);
                        enemyTanks.remove(enemyTank);
                        enemyCount--;
                        killEnemys++;
                        bullet.isLive = false;
                        myTank.bullets.remove(bullet);
                    }
                }
            }
        }

    }

    /**
     * Description: 判断自己的坦克是否还存活
     *
     * @param
     * @return void
     * @author heyefu 14:32 2017/12/12
     **/
    public void judgeMyTankIsLive() {
//        取出每一辆敌人坦克及其子弹
        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemyTank = enemyTanks.get(i);
            for (int j = 0; j < enemyTank.bullets.size(); j++) {
                Bullet bullet = enemyTank.bullets.get(j);
                int bulletX = bullet.getX();
                int bulletY = bullet.getY();
                if (myTank.direction == 0 || myTank.direction == 2) {
                    if (bulletX < myTank.x + 10 && bulletX > myTank.x - 10 && bulletY > myTank.y - 15 && bulletY < myTank.y + 15) {
                        Bomb bomb = new Bomb(myTank.x - 15, myTank.y - 15);
                        bombs.add(bomb);
                        myTank.isLive = false;
                        bullet.isLive = false;
                        enemyTank.bullets.remove(bullet);
                    }
                } else {
                    if (bulletX < myTank.x + 15 && bulletX > myTank.x - 15 && bulletY > myTank.y - 10 && bulletY < myTank.y + 10) {
                        Bomb bomb = new Bomb(myTank.x - 15, myTank.y - 15);
                        bombs.add(bomb);
                        myTank.isLive = false;
                        bullet.isLive = false;
                        enemyTank.bullets.remove(bullet);
                    }
                }
            }
        }
    }

    public Vector<EnemyTank> getEnemyTanks() {
        return enemyTanks;
    }
}
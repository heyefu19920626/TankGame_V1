package TankGame_V1;

import java.util.Vector;

/**
 * Description:
 * 坦克父类
 *
 * @author heyefu
 * Create in: 2017-12-07
 * Time: 22:31
 **/
public abstract class Tank {
    /**
     * Description: 坦克中心位置横坐标
     */
    protected int x;
    /**
     * Description: 坦克中心位置纵坐标
     */
    protected int y;
    /**
     * Description: 坦克的方向
     */
    protected int direction;

    /**
     * Description: 坦克的速度
     */
    protected int speed = 5;
    /**
     * Description: 坦克是否存活
     */
    public boolean isLive = true;
    /**
     * Description: 坦克的子弹
     */
    public Vector<Bullet> bullets = new Vector<Bullet>();


    public Tank(int x, int y, int direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    /**
     * Description: 向上移动
     *
     * @param
     * @return void
     * @author heyefu 16:42 2017/12/9
     **/
    public void moveUp() {
        this.y -= this.speed;
    }

    /**
     * Description: 向下移动
     *
     * @param
     * @return void
     * @author heyefu 16:44 2017/12/9
     **/
    public void moveDown() {
        this.y += this.speed;
    }

    /**
     * Description: 向左移动
     *
     * @param
     * @return void
     * @author heyefu 16:46 2017/12/9
     **/
    public void moveLeft() {
        this.x -= this.speed;
    }

    /**
     * Description: 向右移动
     *
     * @param
     * @return void
     * @author heyefu 17:52 2017/12/9
     **/
    public void moveRight() {
        this.x += this.speed;
    }

    /**
     * Description:
     *
     * @param x
     * @param y
     * @param direction
     * @return void
     * @author heyefu 13:44 2017/12/10
     **/
    public void shot(int x, int y, int direction) {
        Bullet bullet = new Bullet(this.x, this.y, this.direction);
        bullets.add(bullet);
        Thread t = new Thread(bullet);
        t.start();
    }

}

/**
 * Description: 玩家操作的坦克类
 *
 * @author heyefu 13:38 2017/12/10
 **/
class MyTank extends Tank {

    public MyTank(int x, int y, int direction) {
        super(x, y, direction);
    }
}

/**
 * Description:
 * 敌人的坦克类
 *
 * @author heyefu 13:42 2017/12/10
 **/
class EnemyTank extends Tank implements Runnable {

    /**
     * Description:计数器,使坦克变换方向不那么频繁
     */
    private int temp = 0;
    /**
     * Description:外面的坦克向量
     */
    private Vector<EnemyTank> enemyTanks = null;


    public EnemyTank(int x, int y, int direction) {
        super(x, y, direction);
    }

    public void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        this.enemyTanks = enemyTanks;
    }

    @Override
    public void run() {
        while (this.isLive) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (Math.random() < 0.05)
                this.shot(this.x, this.y, this.direction);

//            一秒钟换一次方向
            if (this.temp > 5) {
                this.direction = (int) (Math.random() * 4);
                this.temp = 0;
            }

            this.temp++;

//            移动坦克
            switch (this.direction) {
                case 0:
                    if (this.y - 15 > 0 && !this.collideOtherTank()) {
                        moveUp();
                    } else {
                        this.direction = (int) (Math.random() * 4);
                    }
                    break;
                case 1:
                    if (this.x + 15 < 500 && !this.collideOtherTank()) {
                        moveRight();
                    } else {
                        this.direction = (int) (Math.random() * 4);
                    }
                    break;
                case 2:
                    if (this.y + 15 < 500 && !this.collideOtherTank()) {
                        moveDown();
                    } else {
                        this.direction = (int) (Math.random() * 4);
                    }
                    break;
                case 3:
                    if (this.x - 15 > 0 && !this.collideOtherTank()) {
                        moveLeft();
                    } else {
                        this.direction = (int) (Math.random() * 4);
                    }
                    break;
            }
        }
    }

    /**
     * Description:
     * 判断前方是否有其他敌方坦克
     *
     * @param
     * @return boolean
     * @author heyefu 下午11:18 18-1-10
     **/
    public boolean collideOtherTank() {
        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank otherTank = enemyTanks.get(i);
//            如果不是自己
            if (otherTank != this) {
                switch (this.direction) {
//                    如果自己朝上
                    case 0:
//                        如果其他坦克朝上或者朝下
                        if (otherTank.direction == 0 || otherTank.direction == 2) {
//                            当自己的上边线在其他坦克上下边线之间且自己左边线或者右边线在其他坦克左右边线之间时，两个坦克为碰撞状态
//                            返回真
                            if ((this.y - 15 < otherTank.y + 15 && this.y - 15 > otherTank.y - 15
                                    && this.x - 10 > otherTank.x - 10 && this.x - 10 < otherTank.x + 10)
                                    || (this.y - 15 < otherTank.y + 15 && this.y - 15 > otherTank.y - 15
                                    && this.x + 10 > otherTank.x - 10 && this.x + 10 < otherTank.x + 10)) {
                                return true;
                            }
                        } else {
                            if (this.y - 15 < otherTank.y + 10 && this.y - 15 > otherTank.y - 10
                                    && (this.x + 10 > otherTank.x - 15 && this.x + 10 < otherTank.x + 15
                                    || this.x - 10 > otherTank.x - 15 && this.x - 10 < otherTank.x + 15)) {
                                return true;
                            }
                        }
                        break;
                    case 1:
//                        当自己的右边线在其他坦克的左右边线之间且自己上边线或者下边线在其他坦克上下边线之前时
//                        返回真
                        if (otherTank.direction == 0 || otherTank.direction == 2) {
                            if ((this.y - 10 < otherTank.y + 15 && this.y - 10 > otherTank.y - 15
                                    || this.y + 10 < otherTank.y + 15 && this.y + 10 > otherTank.y - 15)
                                    && this.x + 15 > otherTank.x - 10 && this.x + 15 < otherTank.x + 10) {
                                return true;
                            }
                        } else {
                            if ((this.y - 10 < otherTank.y + 10 && this.y - 10 > otherTank.y - 10
                                    || this.y + 10 < otherTank.y + 10 && this.y + 10 > otherTank.y - 10)
                                    && this.x + 15 > otherTank.x - 15 && this.x + 15 < otherTank.x + 15) {
                                return true;
                            }
                        }
                        break;
                    case 2:
                        if (otherTank.direction == 0 || otherTank.direction == 2) {
                            if (this.y + 15 < otherTank.y + 15 && this.y + 15 > otherTank.y - 15
                                    && (this.x - 10 > otherTank.x - 10 && this.x - 10 < otherTank.x + 10
                                    || this.x + 10 > otherTank.x - 10 && this.x + 10 < otherTank.x + 10)) {
                                return true;
                            }
                        } else {
                            if (this.y + 15 < otherTank.y + 10 && this.y + 15 > otherTank.y - 10
                                    && (this.x - 10 > otherTank.x - 15 && this.x - 10 < otherTank.x + 15
                                    || this.x + 10 > otherTank.x - 15 && this.x + 10 < otherTank.x + 15)) {
                                return true;
                            }
                        }
                        break;
                    case 3:
                        if (otherTank.direction == 0 || otherTank.direction == 2) {
                            if ((this.y - 10 < otherTank.y + 15 && this.y - 10 > otherTank.y - 15
                                    || this.y + 10 < otherTank.y + 15 && this.y + 10 > otherTank.y - 15)
                                    && this.x - 15 > otherTank.x - 10 && this.x - 15 < otherTank.x + 10) {
                                return true;
                            }
                        } else {
                            if ((this.y - 10 < otherTank.y + 10 && this.y - 10 > otherTank.y - 10
                                    || this.y + 10 < otherTank.y + 10 && this.y + 10 > otherTank.y - 10)
                                    && this.x - 15 > otherTank.x - 15 && this.x - 15 < otherTank.x + 15) {
                                return true;
                            }
                        }
                        break;
                }
            }
        }
//         如果以上都为没有返回真，则前方没有其他坦克，返回假
        return false;
    }
}

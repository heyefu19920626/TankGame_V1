package TankGame_V1;

/**
 * Description:
 * 子弹类
 *
 * @author heyefu
 * Create in: 2017-12-09
 * Time: 17:44
 **/
public class Bullet implements Runnable {
    /**
     * Description: 子弹的横坐标
     */
    private int x;
    /**
     * Description: 子弹的纵坐标
     */
    private int y;
    /**
     * Description: 子弹的方向
     */
    private int direction;
    /**
     * Description: 子弹的速度
     */
    private final int speed = 5;
    /**
     * Description: 子弹的状态
     */
    public Boolean isLive = true;

    public Bullet(int x, int y, int direction) {
        this.direction = direction;
        switch (direction) {
            case 0:
                this.x = x;
                this.y = y - 15;
                break;
            case 1:
                this.x = x + 15;
                this.y = y;
                break;
            case 2:
                this.x = x;
                this.y = y + 15;
                break;
            case 3:
                this.x = x - 15;
                this.y = y;
                break;
        }
    }

    @Override
    public void run() {
        while (isLive) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            switch (direction) {
                case 0:
                    this.y -= this.speed;
                    break;
                case 1:
                    this.x += this.speed;
                    break;
                case 2:
                    this.y += this.speed;
                    break;
                case 3:
                    this.x -= this.speed;
                    break;
            }

            if (this.x > 500 || this.x < 0 || this.y > 500 || this.y < 0) {
                this.isLive = false;
                break;
            }

        }
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

    public Boolean getLive() {
        return isLive;
    }

    public void setLive(Boolean live) {
        isLive = live;
    }
}

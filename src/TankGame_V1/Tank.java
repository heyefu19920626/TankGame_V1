package TankGame_V1;

/**
 * Description:
 * 坦克父类
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
    *Description: 向上移动
     * @param
    *@return void
    *@author heyefu 16:42 2017/12/9
    **/
    public void moveUp(){
        this.y -= this.speed;
    }
    /**
    *Description: 向下移动
     * @param
    *@return void
    *@author heyefu 16:44 2017/12/9
    **/
    public void moveDown(){
        this.y += this.speed;
    }
    /**
    *Description: 向左移动
     * @param
    *@return void
    *@author heyefu 16:46 2017/12/9
    **/
    public void moveLeft(){
        this.x -= this.speed;
    }
    public void  moveRight(){
        this.x += this.speed;
    }
}


class MyTank extends Tank{

    public MyTank(int x, int y, int direction) {
        super(x, y, direction);
    }
}
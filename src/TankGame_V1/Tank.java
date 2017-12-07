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
    * Description: 坦克初始位置横坐标
    */
    protected int x;
    /**
    * Description: 坦克初始位置纵坐标
    */
    protected int y;

    /**
    *Description:
     * 设置坦克初始位置
     * @param x
     * @param y
    *@return void
    *@author heyefu 22:44 2017/12/7
    **/
    public void setInitLocation(int x, int y){
        this.x = x;
        this.y = y;
    }
}


class MyTank extends Tank{

}
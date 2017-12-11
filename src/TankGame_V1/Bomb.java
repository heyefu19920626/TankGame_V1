package TankGame_V1;

import javax.swing.*;
import java.awt.*;

/**
 * Description:
 * 爆炸类，保存爆炸图片
 * @author heyefu
 * Create in: 2017-12-11
 * Time: 16:17
 **/
public class Bomb {
    /**
     * Description: 爆炸图片1
     */
    Image bomb_1 = new ImageIcon("img/1.jpg").getImage();
    /**
     * Description: 爆炸图片2
     */
    Image bomb_2 = new ImageIcon("img/2.jpg").getImage();
    /**
     * Description: 爆炸图片3
     */
    Image bomb_3 = new ImageIcon("img/3.jpg").getImage();
    /**
     * Description: 图片的生命值
     */
    int image_life = 9;
    /**
    * Description: 爆炸的生命值
    */
    int bomb_life = 3;
}

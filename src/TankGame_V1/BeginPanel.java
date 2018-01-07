package TankGame_V1;

import javax.swing.*;
import java.awt.*;

/**
 * Description:
 * 开始面板
 * @author heyefu
 * Create in: 2018-01-07
 * Time: 下午9:45
 **/
public class BeginPanel extends JPanel{
    private String startGame = "Start";
    private int width = 500;
    private int height = 500;
    public static void main(String[] args) {

    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.black);
        g.fillRect(0,0,width,height);
        g.setColor(Color.BLUE);
        g.setFont(new Font("华文行楷",Font.CENTER_BASELINE,24));
        g.drawString(startGame, 230, 245);
    }
}

package TankGame_V1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Description:
 * 开始面板
 *
 * @author heyefu
 * Create in: 2018-01-07
 * Time: 下午9:45
 **/
public class BeginPanel extends JPanel implements Runnable, MouseListener {
    private String startGame = "Start";
    private int width = 500;
    private int height = 500;
    /**
     * Description:
     * 字体闪烁间隔
     */
    private static int glintInterval = 0;
    /**
     * Description:
     * 判断游戏是否开始
     */
    public boolean isBegin = false;


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.black);
        g.fillRect(0, 0, width, height);
        if (glintInterval < 2) {
            g.setColor(Color.BLUE);
            g.setFont(new Font("华文行楷", Font.CENTER_BASELINE, 24));
            g.drawString(startGame, 230, 245);
        } else {
            glintInterval = 0;
        }
    }


    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            repaint();
            glintInterval++;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if ((240 < e.getX()) && e.getX() < 290 && e.getY() > 260 && e.getY() < 275) {
            this.isBegin = true;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

package program.graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
//only for learning
public class Game extends JPanel implements ActionListener, KeyListener {
    Timer t = new Timer(5,this);
    double x = 0, y = 0, velX = 0, velY = 0;

    public Game(){
        t.start();
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.fill(new Rectangle2D.Double(x, y, 20, 20) {
        });

    }
    public void actionPerformed(ActionEvent e){
        repaint();
        x += velX;
        y += velY;
    }
    public void up(){
        velY = -1.5;
        velX = 0;
    }
    public void down(){
        velY = 1.5;
        velX = 0;
    }
    public void right(){
        velY = 0;
        velX = 1.5;
    }
    public void left(){
        velY = 0;
        velX = -1.5;
    }
    public void keyPressed(KeyEvent e){
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W)
            up();
        if (code == KeyEvent.VK_S)
            down();
        if (code == KeyEvent.VK_A)
            left();
        if (code == KeyEvent.VK_D)
            right();
    }
    public void keyTyped(KeyEvent e){}

    public void keyReleased(KeyEvent e){}

}

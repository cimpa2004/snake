package program.Items;

import program.Coord;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;


public class Snake extends JPanel implements ActionListener, KeyListener {
    Timer t = new Timer(5,this);
    double x = 0, y = 0, velX = 0, velY = 0;
    private LinkedList<Coord> body;
    private Direction direction;
    private int size = 3;
    private Direction headed = Direction.UP;

    public Snake(){
        t.start();
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

    }


    public boolean selfCollision() {
        Coord head = getHead();
        return body.contains(head);
    }

    public boolean hitWall() {
        Coord head = getHead();
        return head.x < 0 || head.x >= 1500 || head.y < 0 || head.y >= 1000;
    }


    public int getSizeOfSnake() {
        return body.size();
    }

    public Coord getHead() {
        return body.getFirst();
    }

    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }


    //rewrite for snake
    public void paintComponent(Graphics gr){
        super.paintComponent(gr);
        Graphics2D g2 = (Graphics2D) gr;
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
        if (code == KeyEvent.VK_A){
            if (headed == Direction.DOWN){
                headed= Direction.RIGHT;
                right();
            }else if (headed == Direction.UP){
                headed = Direction.LEFT;
                left();
            }else if (headed == Direction.LEFT){
                headed = Direction.DOWN;
                down();
            }else if (headed == Direction.RIGHT){
                headed = Direction.UP;
                up();
            }
        }else if (code == KeyEvent.VK_D){
            if (headed == Direction.DOWN){
                headed= Direction.LEFT;
                left();
            }else if (headed == Direction.UP){
                headed = Direction.RIGHT;
                right();
            }else if (headed == Direction.LEFT){
                headed = Direction.UP;
                up();
            }else if (headed == Direction.RIGHT){
                headed = Direction.DOWN;
                down();
            }
        }




    }
    public void keyTyped(KeyEvent e){}

    public void keyReleased(KeyEvent e){}
}


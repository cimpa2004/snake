package program.Items;

import program.Coord;
import program.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.LinkedList;


public class Snake extends JPanel implements ActionListener, KeyListener {
    double  velX = 0, velY = 0;
    private ArrayList<Coord> body = new ArrayList<>();
    private int size = 30;
    private Direction headed = Direction.UP;
    Timer t = new Timer(5,this);

    public Snake() {
        t.start();
        initializeSnake();
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }

    private void initializeSnake() {
        body.clear();
        
        for (int i = 0; i < size; i++) {
            body.add(new Coord(20, 20 + i * 20));
        }
    }

    //not good
    public boolean selfCollision() {
        Coord head = getHead();
        //ne
        return false;
        /*
        // Start from the second element (index 1) since the head is at index 0
        for (int i = 1; i < getSizeOfSnake(); i++) {
            Coord bodyPart = body.get(i);
            if (head.equals(bodyPart)) {
                return true;  // Collision detected
            }
        }
        return false;  // No collision */
    }


    // nem tokéletes
    public boolean hitWall() {
        Coord head = getHead();
        if (head.x < 0){
            return true;
        }else if (head.x >= 1500) {
            return true;
        } else if (head.y < 0) {
            return true;
        } else if (head.y >= 750) {
            return true;
        }
        return false;
    }


    public int getSizeOfSnake() {
        return size;
    }

    public Coord getHead() {
        return body.get(0);
    }

    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }


    //rewrite for snake
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        for (Coord part : body) {
            g2.fill(new Rectangle2D.Double(part.x, part.y, 20, 20));
        }
        //itt a selfColison is vege-t jelent de az még nincs kész
        if (hitWall() || selfCollision()){
            System.err.println("Vegeeeeeeeeeeee");
            Main.exit();
        }

    }
    //minden body part újra rajzolása
    public void actionPerformed(ActionEvent e) {
        repaint();

        Coord head = new Coord(body.get(0).x + velX, body.get(0).y + velY);
        LinkedList<Coord> newBody = new LinkedList<>();
        newBody.add(head);

        for (int i = 1; i < getSizeOfSnake(); i++) {
            newBody.add(body.get(i - 1));
        }

        body = new ArrayList<>(newBody);
        if (hitWall() || selfCollision()){
            System.err.println("Vegeeeeeeeeeeee");
            Main.exit();
        }
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


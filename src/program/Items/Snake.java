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
    public double velcoity = 2;
    private ArrayList<Coord> body = new ArrayList<>();
    private int size = 30;
    private Direction headed = Direction.UP;
    Timer t = new Timer(5,this);
    private boolean moved = false;

    private ArrayList<Coord> headRoute = new ArrayList<>();



    public void addSize(int siz) {
        size += siz;

        // Add new Coord objects to the body for the increased size
        Coord lastBodyPart = body.get(body.size() - 1);
        for (int i = 0; i < siz; i++) {
            body.add(new Coord(lastBodyPart.x, lastBodyPart.y + 20)); // Assuming 20 is the size of each rectangle
        }

        repaint();
    }
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
        //return false;
        // Start from the second element (index 1) since the head is at index 0
        for (int i = 1; i < getSizeOfSnake(); i++) {
            Coord bodyPart = body.get(i);
            if (head.equals(bodyPart)) {
                return true;  // Collision detected
            }
        }
        return false;  // No collision
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
        if (moved) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            int i = 0;
            for (Coord part : body) {
                if (i==0)
                    g2.setColor(Color.GREEN);
                else
                    g2.setColor(Color.BLACK);
                g2.fill(new Rectangle2D.Double(part.x, part.y, 20, 20));
                i++;

            }
            if (hitWall() || selfCollision()) {
                System.err.println("Vegeeeeeeeeeeee");
                Main.exit();
            }
        }
    }


    //minden body part újra rajzolása uj helyen
    @Override
    public void actionPerformed(ActionEvent e) {
        if (moved) {
            if (hitWall() || selfCollision()) {
                System.err.println("Vegeeeeeeeeeeee");
                Main.exit();
                return;
            }

            Coord head = new Coord(body.get(0).x + velX, body.get(0).y + velY);

            updateBody(head);

            repaint();
        }
    }

    private void updateBody(Coord head) {
        headRoute.add(0, new Coord(head.x, head.y));

        while (headRoute.size() > size) {
            headRoute.remove(headRoute.size() - 1);
        }

        for (int i = 1; i < getSizeOfSnake(); i++) {
            if (headRoute.size() > i) {
                Coord routePosition = headRoute.get(i);

                body.set(i, new Coord(routePosition.x, routePosition.y));
            }
        }

        body.set(0, head);
    }



    public void up(){
        velY = -1*velcoity;
        velX = 0;
        moved = true;

    }
    public void down(){
        velY = velcoity;
        velX = 0;
        moved = true;
    }
    public void right(){
        velY = 0;
        velX = velcoity;
        moved = true;
    }
    public void left(){
        velY = 0;
        velX = -1*velcoity;
        moved = true;
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
        }else if (code == KeyEvent.VK_L){//cheat
            addSize(10);
        }




    }
    public void keyTyped(KeyEvent e){}

    public void keyReleased(KeyEvent e){}
}


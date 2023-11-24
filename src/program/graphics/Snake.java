package program.graphics;

import program.Coord;
import program.Items.*;
import program.Rangletra;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

//collison map meret 950*690
public class Snake extends JPanel implements ActionListener, KeyListener {
    double  velX = 0, velY = 0;
    public double velcoity = 3;
    private ArrayList<Coord> body = new ArrayList<>();
    private double size = 30; //1 =10
    private Integer points = 0;
    private Direction headed = Direction.UP;
    Timer t = new Timer(0,this); //a rajzolások gyakorisága
    private boolean moved = false;
    private ArrayList<Coord> headRoute = new ArrayList<>();
    private String currentPlayer;
    private SnakeWindow window = null;

    //items vars and methods

    private int hasBeenDownFor = 0; //updated in actionPerformed

    private boolean itemIsDown = false;

    public boolean isItemDown(){
        return itemIsDown;
    }

    private Item[] foods = new Item[4];


    private void initItems(){
        foods[0] = new BlueItem(-50,-50);
        foods[1] = new PinkItem(-50,-50);
        foods[2] = new RedItem(-50,-50);
        foods[3] = new CyanItem(-50,-50);

    }

    public boolean isCoordinateOccupied(int x, int y) {
        for (Coord bodyPart : body) {
            if (bodyPart.x == x && bodyPart.y == y) {
                return true;  
            }
        }
        return false;
    }


    private boolean ateFood() {
        Coord head = getHead();
        int margin = 5;

        for (int i = 0; i < foods.length; i++) {
            Item food = foods[i];
            int foodX = food.getXcord();
            int foodY = food.getYcord();
            if (head.x + 20 >= foodX - margin && head.x <= foodX + 20 + margin &&
                    head.y + 20 >= foodY - margin && head.y <= foodY + 20 + margin) {
                if (food.equals(foods[0])){//kek
                    points +=foods[0].getPointsadded();
                    addSize(10);

                    double lastVel = velcoity;
                    //stack overflow, de nem teljes másolat: https://stackoverflow.com/questions/1519091/scheduledexecutorservice-with-variable-delay
                    velcoity = velcoity + foods[0].getSpeedmodifyer()*velcoity;
                    ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
                    scheduler.schedule(() -> {
                        velcoity = lastVel;
                        scheduler.shutdown();
                    }, 8, TimeUnit.SECONDS);
                    //stack overflow end
                } else if (food.equals(foods[1])){ //pink
                    points +=foods[1].getPointsadded();
                    addSize(20);

                    double lastVel = velcoity;
                    velcoity = velcoity + foods[1].getSpeedmodifyer()*velcoity;
                    ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
                    scheduler.schedule(() -> {
                        velcoity = lastVel;
                        scheduler.shutdown();
                    }, 5, TimeUnit.SECONDS);
                }else if (food.equals(foods[2])){//piros
                    points +=foods[2].getPointsadded();
                    addSize(10);
                }else if (food.equals(foods[3])){//cyan
                    points += foods[3].getPointsadded();
                    addSize(10);

                    double lastVel = velcoity;
                    velcoity = velcoity + foods[3].getSpeedmodifyer()*velcoity;
                    ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
                    scheduler.schedule(() -> {
                        velcoity = lastVel;
                        scheduler.shutdown();
                    }, 7, TimeUnit.SECONDS);
                }
                food.setXcord(-50);
                food.setYcord(-50);
                return true;
            }
        }
        return false;
    }



    //snake:
    public void addSize(int siz) {
        size += siz;

        Coord lastBodyPart = body.get(body.size() - 1);
        for (int i = 0; i < siz; i++) {
            body.add(new Coord(lastBodyPart.x, lastBodyPart.y + 20));
        }

        repaint();
    }
    public Snake(String currentPlayer, SnakeWindow window){
        body = new ArrayList<>();
        t.start();
        initializeSnake();
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        initItems();
        System.out.println(currentPlayer);
        System.out.println(this.getHead().x + " " +  this.getHead().y);
        this.currentPlayer = currentPlayer;
        this.window = window;
    }

    private void initializeSnake() {
        body.clear();
        
        for (int i = 0; i < size; i++) {
            body.add(new Coord(20, 20 + i * 20));
        }
    }

    public boolean selfCollision() {
        Coord head = getHead();
        for (int i = 1; i < getSizeOfSnake(); i++) {
            Coord bodyPart = body.get(i);
            if (head.equals(bodyPart)) {
                System.out.println("self col");
                return true;
            }
        }
        return false;
    }


    public boolean hitWall() {
        Coord head = getHead();
        if (head.x < 0){
            System.out.println("outside x");
            return true;
        }else if (head.x >= 950) {
            System.out.println("outside x");
            return true;
        } else if (head.y < 0) {
            System.out.println("outside y");
            return true;
        } else if (head.y >= 690) {
            System.out.println("outside y");
            return true;
        }
        return false;
    }


    public double getSizeOfSnake() {
        return size;
    }

    public Coord getHead() {
        return body.get(0);
    }

    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }


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
            g2.setColor(Color.blue);
            g2.fill(new Rectangle2D.Double(foods[0].getXcord(),foods[0].getYcord(),20,20));
            g2.setColor(Color.pink);
            g2.fill(new Rectangle2D.Double(foods[1].getXcord(),foods[1].getYcord(),20,20));
            g2.setColor(Color.red);
            g2.fill(new Rectangle2D.Double(foods[2].getXcord(),foods[2].getYcord(),20,20));
            g2.setColor(Color.cyan);
            g2.fill(new Rectangle2D.Double(foods[3].getXcord(),foods[3].getYcord(),20,20));
            g2.setColor(Color.black);

            if (hitWall() || selfCollision()) {
                //System.err.println("Vegeeeeeeeeeeee");
                Rangletra uj = new Rangletra(currentPlayer,points);
                window.endOfGame(uj,points);
                return;
            }

        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (moved) {
            Coord head = new Coord(body.get(0).x + velX, body.get(0).y + velY);

            updateBody(head);


            //items
            if (!isItemDown()) {
                Random random = new Random();
                int randomX, randomY;

                do {
                    randomX = random.nextInt(850);
                    randomY = random.nextInt(600);
                } while (isCoordinateOccupied(randomX, randomY));
                int randomNumber = random.nextInt(100);
                if (randomNumber <= 35 && randomNumber >= 1) { //35%
                    foods[2].setXcord(randomX);
                    foods[2].setYcord(randomY);
                    itemIsDown = true;
                } else if (randomNumber <= 70) { // de nagyobb mint 35 //35%
                    foods[0].setXcord(randomX);
                    foods[0].setYcord(randomY);
                    itemIsDown = true;
                } else if (randomNumber <= 90) { //de nagyobb mint 70 //20%
                    foods[3].setXcord(randomX);
                    foods[3].setYcord(randomY);
                    itemIsDown = true;
                } else {//maradek 10%
                    foods[1].setXcord(randomX);
                    foods[1].setYcord(randomY);
                    itemIsDown = true;
                }

            }


            hasBeenDownFor++;
            if (hasBeenDownFor >= 500){//100 ha debugolsz //500 ha nem
                hasBeenDownFor = 0;
                itemIsDown = false;
                for (int i = 0; i<4; i++){
                    foods[i].setXcord(-50);
                    foods[i].setYcord(-50);
                }
                //remove item from board
            }
            repaint();
            if (ateFood()){
                //System.out.println("nyam nyam " +size +" " + points);
                System.out.println("Points: " + points);
                itemIsDown = false;
                hasBeenDownFor = 0;
            }
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
        }else if (code == KeyEvent.VK_L){//cheat to be removed
            addSize(10);
        }else if (code == KeyEvent.VK_O){//cheat to be removed
            velcoity += 2;
        } else if (code == KeyEvent.VK_P){//cheat to be removed
            velcoity -= 2;
        }




    }
    public void keyTyped(KeyEvent e){}

    public void keyReleased(KeyEvent e){}
}


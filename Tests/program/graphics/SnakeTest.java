package program.graphics;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import program.Coord;
import program.Data;

import static org.junit.Assert.*;


public class SnakeTest{
    Snake snake = null;

    @Before
    public void Init(){
        snake = new Snake("Nev",null);

    }

    @Test
    public void isCoordinateOccupiedTest(){
        int x = 20;
        int y = 20;
        assertTrue(snake.isCoordinateOccupied(x,y));
        y=40;
        assertTrue(snake.isCoordinateOccupied(x,y));
        x=21;
        y=21;
        assertFalse(snake.isCoordinateOccupied(x,y));
    }
    @Test
    public void addSizeTest(){
        int oldSize = snake.getSizeOfSnake();
        int toadd = 0;
        snake.addSize(toadd);
        assertEquals(oldSize, snake.getSizeOfSnake());
        toadd = 3;
        snake.addSize(toadd);
        assertEquals(oldSize+3, snake.getSizeOfSnake());
    }

    @Test
    public void hitWallTest(){
        assertFalse(snake.hitWall());
        snake.setHead(-1,10);
        assertTrue(snake.hitWall());
        snake.setHead(950,1);
        assertTrue(snake.hitWall());
        snake.setHead(1,-1);
        assertTrue(snake.hitWall());
        snake.setHead(10,690);
    }

    @Test
    public void selfCollisionTest(){
        assertFalse(snake.selfCollision());
        snake.setHead(20,40);
        assertTrue(snake.selfCollision());
    }

    @Ignore("Not good")
    @Test
    public void ateFoodTest(){
        int old = snake.getSizeOfSnake();
        snake.moveAFood(20,20,0);
        snake.setHead(20,20);

        assertEquals(old+10,snake.getSizeOfSnake());
    }


}
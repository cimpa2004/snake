package program.graphics;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
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


}
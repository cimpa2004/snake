package program;

import org.junit.Test;

import static org.junit.Assert.*;

public class CoordTest {
    @Test
    public void constuctorTest(){
        Coord temp = new Coord(10,10);
        assertTrue(temp.x == 10);
        assertTrue(temp.y == 10);
    }
}
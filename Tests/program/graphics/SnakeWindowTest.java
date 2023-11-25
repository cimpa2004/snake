package program.graphics;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import program.Data;
import program.Rangletra;

import static org.junit.Assert.*;

public class SnakeWindowTest {
    private Rangletra elso = null;
    private Rangletra masodik= null;
    private Rangletra harmadik = null;
    private Rangletra negyedik = null;
    private Rangletra otodik = null;
    private Data test = null;
    private Data test2 = null;
    private Rangletra kulonbseg = null;
    SnakeWindow window = null;

    @Before
    public void init(){
        elso = new Rangletra("Elso",99);
        masodik= new Rangletra("masodik",98);
        harmadik = new Rangletra("harmadik",97);
        negyedik = new Rangletra("negyedik",96);
        otodik = new Rangletra("otodik",95);
        kulonbseg = new Rangletra("dif",300);
        test = new Data();
        test2 = new Data();
        test.addIfNeded(elso);
        test.addIfNeded(masodik);
        test.addIfNeded(harmadik);
        test.addIfNeded(negyedik);
        test.addIfNeded(otodik);
        test.addIfNeded(elso);
        test.addIfNeded(masodik);
        test.addIfNeded(harmadik);
        test.addIfNeded(negyedik);
        test2.addIfNeded(kulonbseg);
        window = new SnakeWindow("window", test);
    }
    @Test
    public void UpdatehighScoresTest(){
        Data old = window.getRangletra();
        assertEquals(old,window.getRangletra());
        window.UpdatehighScores(test2);
        assertNotEquals(old,window.getRangletra());
    }
    @Test
    public void constuctorTest(){ //showMenuTest
        assertTrue(window.isMenuVisible());
        assertFalse(window.isNevMegadVisible());
        assertFalse(window.isRangletraVisible());
        assertFalse(window.isUjJatekVisible());
    }
    @Test(expected = NullPointerException.class)
    public void constructorTestForGameFrame(){
        assertFalse(window.isGameVisible());
    }

    @Test
    public void showRangletraTest(){
        window.showRangletra();
        assertTrue(window.isRangletraVisible());
        assertFalse(window.isMenuVisible());
    }

    @Test
    public void showGameTest(){
        window.showGame();
        assertTrue(window.isGameVisible());
        assertFalse(window.isNevMegadVisible());
    }
    @Test
    public void showNevmegadTest(){
        window.showSetName();
        assertTrue(window.isNevMegadVisible());
        assertFalse(window.isMenuVisible());
    }

    @Test
    public void showEndGamePanelTest(){
        window.showGame();
        window.showEndGamePanel();
        assertFalse(window.isGameVisible());
        assertTrue(window.isUjJatekVisible());
        window.showMenu();
        assertFalse(window.isUjJatekVisible());
        assertTrue(window.isMenuVisible());
    }



    @Test
    public void endOfGameTest(){
        window.showGame();
        Rangletra elem = new Rangletra("NEv", 1000);
        window.endOfGame(elem);
        assertFalse(window.isGameVisible());
        assertTrue(window.isUjJatekVisible());
    }



}
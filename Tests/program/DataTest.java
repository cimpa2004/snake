package program;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class DataTest {
    private Rangletra nemkerulbe = null;
    private Rangletra bekerul = null;
    private Rangletra elso = null;
    private Rangletra masodik= null;
    private Rangletra harmadik = null;
    private Rangletra negyedik = null;
    private Rangletra otodik = null;
    private Data test = null;

    @Before
    public void init(){

        nemkerulbe = new Rangletra("NEM",0);
        bekerul = new Rangletra("IGEN",100);
        elso = new Rangletra("Elso",99);
        masodik= new Rangletra("masodik",98);
        harmadik = new Rangletra("harmadik",97);
        negyedik = new Rangletra("negyedik",96);
        otodik = new Rangletra("otodik",95);
        test = new Data();
        test.addIfNeded(elso);
        test.addIfNeded(masodik);
        test.addIfNeded(harmadik);
        test.addIfNeded(negyedik);
        test.addIfNeded(otodik);

    }

    @Test
    public void addIfNededTest(){
        assertTrue(test.countains(elso));
        assertTrue(test.countains(masodik));
        assertTrue(test.countains(harmadik));
        assertTrue(test.countains(negyedik));
        assertTrue(test.countains(otodik));
        test.addIfNeded(nemkerulbe);
        assertFalse(test.countains(nemkerulbe));
        test.addIfNeded(bekerul);
        assertTrue(test.countains(bekerul));
        assertFalse(test.countains(otodik));
    }



}
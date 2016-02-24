package pack1;

import org.junit.Test;

import static org.junit.Assert.*;

/*
 * Created by raugust on 2/24/2016.
 */
public class BalloonTest {

    @org.junit.Test
    public void testGetColor() throws Exception {
        // Create a default balloon
        Balloon balloon1 = new Balloon();
        // check color
        assertEquals("blue", balloon1.getColor());
        // Create custom balloons
        Balloon balloon2 = new Balloon(6, "red", true);
        Balloon balloon3 = new Balloon(8, "green", true);
        Balloon balloon4 = new Balloon(12, "yellow", false);
        // check colors
        assertEquals("red", balloon2.getColor());
        assertEquals("green", balloon3.getColor());
        assertEquals("yellow", balloon4.getColor());
    }

    @org.junit.Test
    public void testGetSize() throws Exception {
        // Create a default balloon
        Balloon balloon1 = new Balloon();
        // check size
        assertEquals(10, balloon1.getSize());
        // Create custom balloons
        Balloon balloon2 = new Balloon(6, "red", true);
        Balloon balloon3 = new Balloon(8, "green", true);
        Balloon balloon4 = new Balloon(12, "yellow", false);
        // check sizes
        assertEquals(6, balloon2.getSize());
        assertEquals(8, balloon3.getSize());
        assertEquals(12, balloon4.getSize());
    }

    @org.junit.Test
    public void testGetQuantity() throws Exception {
        //Created balloon array and objects, and tested to make sure the quantity went up proportionally
        Balloon[] balloonList = new Balloon[5];
        balloonList[Balloon.getQuantity()] = new Balloon();
        assertEquals(1, Balloon.getQuantity());
        balloonList[Balloon.getQuantity()] = new Balloon();
        assertEquals(2, Balloon.getQuantity());
        balloonList[Balloon.getQuantity()] = new Balloon();
        assertEquals(3, Balloon.getQuantity());
        balloonList[Balloon.getQuantity()] = new Balloon();
        assertEquals(4, Balloon.getQuantity());
        balloonList[Balloon.getQuantity()] = new Balloon();
        assertEquals(5, Balloon.getQuantity());
        //Destroyed balloon objects, and tested to make sure the quantity went down proportionally
        Balloon.destruct(2, balloonList);
        assertEquals(4, Balloon.getQuantity());
        Balloon.destruct(4, balloonList);
        assertEquals(3, Balloon.getQuantity());
        Balloon.destruct(1, balloonList);
        assertEquals(2, Balloon.getQuantity());
        Balloon.destruct(5, balloonList);
        assertEquals(1, Balloon.getQuantity());
        Balloon.destruct(3, balloonList);
        assertEquals(0, Balloon.getQuantity());
    }

    @org.junit.Test
    public void testDestruct() throws Exception {
        Balloon[] balloonList = new Balloon[5];
        balloonList[Balloon.getQuantity()] = new Balloon();
        balloonList[Balloon.getQuantity()] = new Balloon();
        balloonList[Balloon.getQuantity()] = new Balloon();
        balloonList[Balloon.getQuantity()] = new Balloon();
        balloonList[Balloon.getQuantity()] = new Balloon();
        //Destroyed balloon objects, and tested to make sure the quantity went down proportionally
        Balloon.destruct(2, balloonList);
        assertEquals(4, Balloon.getQuantity());
        Balloon.destruct(4, balloonList);
        assertEquals(3, Balloon.getQuantity());
        Balloon.destruct(1, balloonList);
        assertEquals(2, Balloon.getQuantity());
        Balloon.destruct(5, balloonList);
        assertEquals(1, Balloon.getQuantity());
        Balloon.destruct(3, balloonList);
        assertEquals(0, Balloon.getQuantity());
    }

    @org.junit.Test
    public void testToString() throws Exception {
        Balloon balloon1 = new Balloon();
        Balloon balloon2 = new Balloon(6, "red", true);
        Balloon balloon3 = new Balloon(8, "green", true);
        Balloon balloon4 = new Balloon(12, "yellow", false);
        String toString1 = balloon1.toString();
        String toString2 = balloon2.toString();
        String toString3 = balloon3.toString();
        String toString4 = balloon4.toString();
        assertTrue(toString1.contains("size = 10,"));
        assertTrue(toString2.contains("size = 6,"));
        assertTrue(toString3.contains("size = 8,"));
        assertTrue(toString4.contains("size = 12,"));
        assertTrue(toString1.contains("color = blue,"));
        assertTrue(toString2.contains("color = red,"));
        assertTrue(toString3.contains("color = green,"));
        assertTrue(toString4.contains("color = yellow,"));
        assertTrue(toString1.contains(", not inflated,"));
        assertTrue(toString2.contains(", inflated"));
        assertTrue(toString3.contains(", inflated"));
        assertTrue(toString4.contains(", not inflated"));

    }

    @org.junit.Test
    public void testSetInflated() throws Exception {

    }

    @org.junit.Test
    public void testIsInflated() throws Exception {

    }
}
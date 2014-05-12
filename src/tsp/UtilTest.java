package tsp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class UtilTest {

    @Before
    public void setUp() throws Exception {
    }
      
    @Test
    public void testCityDistance() {
        
        City a = new City(0, 5);
        City b = new City(0, 10);
        
        assertEquals(0, (int)a.distance(a));
        assertEquals(5, (int)a.distance(b));
        assertEquals(5, (int)b.distance(a));
        
        City c = new City(10, 0);
        City d = new City(5, 0);
        
        assertEquals(0, (int)c.distance(c));
        assertEquals(5, (int)c.distance(d));
        assertEquals(5, (int)d.distance(c));
        
        
    }
    
    @Test
    public void testTourDistance() {
        
        City a = new City(0, 5);
        City b = new City(0, 10);
        City c = new City(0, 15);
        City d = new City(0, 20);
        City e = new City(0, 25);
        City f = new City(0, 25);
        
        City[] city = new City[6];
        city[0] = a;
        city[1] = b;
        city[2] = c;
        city[3] = d;
        city[4] = e;
        city[5] = f;
        
//        TSP tsp = new TSP();
//        tsp.cities = city;
//        Tour t = Tour.makeTour();
//        int[] intA = {0, 1, 2, 3, 4, 5};
//        t.index = intA;
//        
//        assertEquals(20, (int)t.distance());
        
        
    }
    
    @Test
    public void testCrossover() {
        
        
        TSP tsp = new TSP();
        Tour t1 = Tour.makeTour();
        Tour t2 = Tour.makeTour();
        
        int[] intA = {0, 1, 2, 3, 5, 4};
        int[] intB = {5, 3, 2, 1, 4, 0};
        t1.index = intA;
        t2.index = intB;
        
        Tour co = t1.crossOver(t2, 1, 4);
        assertEquals(0, co.index[0]);
        assertEquals(3, co.index[1]);
        assertEquals(2, co.index[2]);
        assertEquals(1, co.index[3]);
        assertEquals(5, co.index[4]);
        
        co = t1.crossOver(t2, 1, 5);
        assertEquals(0, co.index[0]);
        assertEquals(5, co.index[5]);
        
        co = t1.crossOver(t2, 0, 2);
        assertEquals(1, co.index[0]);
        assertEquals(0, co.index[1]);
        assertEquals(2, co.index[2]);    
        
        
    }
    
    
    
    @Test
    public void testRndInt() {
        for (int i = 0; i < 20; i++) {
            int a = Util.rndInt(0, 5);
            assertTrue(a < 5 && a >= 0);
        }
        
        assertEquals(1, Util.rndInt(1, 2));
        assertEquals(2, Util.rndInt(2, 3));
        
        try {
            Util.rndInt(1, 0);
            assert false;
        } catch (IllegalArgumentException e) {
            assert true;
        }
                
        try {
            Util.rndInt(0, 0);
            assert false;
        } catch (IllegalArgumentException e) {
            assert true;
        }

    }

    @Test
    public void testCount() {
        
        int[] a = {1, 1, 1, 2, 3, 2, 3, 3};
        assertEquals(3, count(a,1,0,7));
        assertEquals(3, count(a,1,0,6));
        assertEquals(3, count(a,1,0,5));
        assertEquals(3, count(a,1,0,4));
        assertEquals(3, count(a,1,0,3));
        assertEquals(3, count(a,1,0,2));
        assertEquals(2, count(a,1,0,1));
        
        assertEquals(2, count(a,2,0,7));
        assertEquals(3, count(a,3,0,7));
        
        try {
            Util.count (a,1,1,0);
            assert false;
        } catch (IllegalArgumentException e) {
            assert true;
        }
        
        try {
            Util.count (null,1,1,0);
            assert false;
        } catch (NullPointerException e) {
            assert true;
        }
        
    }
    
    // count the number of occurrence of key in a from start to end
    static int count(int[] a, int key, int start, int end) {
        int count = 0;
        for (int i = start; i <= end; i++)
            if (a[i] == key) count += 1;
        //TODO make this work 
        return count;
    }

}

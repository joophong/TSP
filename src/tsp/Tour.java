package tsp;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Tour implements Comparable {

	static TSP tsp;

	int[] index; // indices into the array of cities in TSP.java

	double distance;
	
    /**
     * Constructs a Tour
     * 
     */
	private Tour() {
		this.index = new int[tsp.cities.length];
	}
	
    /**
     * Constructs a Tour and copy the route from a given Tour.
     * 
     */
    Tour(Tour tr) {
        this();
        for (int i = 0; i < tr.length(); i++) {
            this.index[i] = tr.index[i];
        }
        this.distance = distance();
    }
	
    /**
     * Returns a Tour which has its index array set to null.
     * This is a factory method for testing purpose.
     * 
     * @return a new Tour with nullified index array
     */
    static public Tour makeTour() {
        Tour t = new Tour();
        t.index = null;
        return t;
    }
    
    /**
     * Returns length of the array that effectively represents
     * the tour, i.e. the order of cities to visit.
     * 
     * @returnr length of the tour
     */
	int length() {
		return index.length;
	}
	
    /**
     * Compares this Tour's distance to that of the other tour.
     * 
     * @param the other Tour for comparing
     * @return result of comparing: 1 if this one is longer, 0 if equal, -1 if the other one is longer
     */
	public int compareTo(Object obj) {
		Tour tr = (Tour) obj;
		if (this.distance < tr.distance) {
			return -1;
		} else if (this.distance == tr.distance) {
			return 0;
		} else {
			return +1;
		}
	}



    /**
     * Returns a consecutive order Tour.
     * 
     * @return a Tour that visits cities in ascending order of the city's index in the array of cities
     */
	static Tour sequence() {
		Tour tr = new Tour();
		for (int i = 0; i < tr.length(); i++) {
			tr.index[i] = i;
		}
		tr.distance = tr.distance();
		return tr;
	}

    /**
     * Returns a randomized order Tour.
     * 
     * @return a Tour that visits cities in a randomly set order
     */
	static Tour rndTour() {
		Tour tr = Tour.sequence();
		tr.randomize();
		tr.distance = tr.distance();
		return tr;
	}
	
    /**
     * Returns a overall randomized order Tour.
     * 
     */
	void randomize() {
		randomize(1, this.length());
	}

    /**
     * Returns a Tour that is randomized between given indices.
     * 
     * @param i i the index from the city visiting order is shuffled
     * @param j j the index until the city visiting order is shuffled
     */
	void randomize(int i, int j) {
		for (int k = 0; k < (j - i + 1); k++) {
			rndSwapTwo(i, j);
		}
		this.distance = distance();
	}
	
    /**
     * Randomly select two Cities among all Cities and change their order.
     * 
     */
	void rndSwapTwo() {
		// index 0 has the start city and doesn't change
		// hence the random#s are from 1 onwards
		rndSwapTwo(1, this.length());
	}

    /**
     * Within the given range, randomly select two Cities and change their order.
     * 
     * @param i the index from which the random pick can be made
     * @param j the index until which the random pick can be made
     */
	void rndSwapTwo(int i, int j) {
		int a, b;
		int tmp;

		a = Util.rndInt(i, j);
		b = Util.rndInt(i, j);
		tmp = index[a];
		index[a] = index[b];
		index[b] = tmp;
	}
	
    /**
     * Returns City at a given index.
     * 
     * @param i the index of this City in TSP's City array
     * @return  City at the given index
     */
	City city(int i) {
		return tsp.cities[this.index[i]];
	}

    /**
     * Returns aggregate of all distances from unit tours in this Tour, i.e. tour between two Cities
     * 
     * @return  the distance of the whole tour
     */
	double distance() {
	    double dist_total = 0;
	    for (int i = 0; i < index.length - 1; i++) {
	        dist_total += city(i).distance(city(i + 1));
	    }
	    
	    return dist_total;
	}

    /**
     * Returns string representation of this Tour.
     * 
     * @return  string representation of this Tour
     */
	public String toString() {
		String s = String.format("%6.1f :", this.distance);
		for (int i = 0; i < this.length(); i++) {
			s += String.format("%3d", index[i]);
		}
		return s;
	}
	
    /**
     * Carries out mutation with 10% chance.
     * When mutation happens, two randomly picked Cities exchange their order.
     * 
     */
	void mutate() {
		if (Math.random() < TSP.p_mutate) {
			// randomly flip two cities
			rndSwapTwo();
			this.distance = distance();
		}
	}
	
    /**
     * Returns a hybrid Tour made of this Tour and the other designated tour.
     * 
     * @param p2    the other Tour with which this Tour will carry out crossover
     * @param x1    first index of the crossover segment
     * @param x2    last index of the crossover segment
     * @return  a hybrid tour with a crossover segment
     */
	Tour crossOver(Tour p2, int x1, int x2) {
		// make an empty tour and then fill it in
		Tour child = new Tour();

		// copy the 1st segment of (this) to the child
		for (int i = 0; i < x1; i++) {
		    child.index[i] = this.index[i];
		}
		
		for (int i = x1; i <= x2; i++) {
		    child.index[i] = p2.index[i];
		}
		// copy the cross-over portion of p2 to the child
		
        for (int i = x2 + 1; i < index.length; i++) {
            child.index[i] = this.index[i];
        }		
		// copy the last segment of (this) to the child

		// Now we need to correct the child for any duplicate cities
		
		// First find out the unique elements of the cross-over segment
		// i.e., those elements of the cross-over segment of
		// p1 that are not in p2
		int[] uniq = new int[x2 - x1 + 1];
		int k = 0;
		boolean found = false;
        for (int i = x1; i <= x2; i++) {
            for (int j = x1; j <= x2; j++) {
                if (this.index[i] == p2.index[j]) {
                    found = true;
                    break;
                }
            }
            if (found) {
                found = false;
                continue;
            }
            uniq[k] = this.index[i];
            k++;
        }
        System.out.println();
		// *** TODO ***
        
        k--;
        found = false;
        for (int i = x1; i <= x2; i++) {
            for (int j = 0; j < x1; j++) {
                if (child.index[i] == child.index[j]) {
                    child.index[i] = uniq[k];
                    k--;
                    found = true;
                    break;
                }
            }
            
            if (found) {
                found = false;
                continue;
            }
            
            for (int j = x2 + 1; j < index.length; j++) {
                if (child.index[i] == child.index[j]) {
                    child.index[i] = uniq[k];
                    k--;
                    break;
                }
            }
        }
		// scan the two portions of p1 that have been crossed into the
		// the child for any duplicates in the crossed-over 
		// segment and if so replace with an element from the uniq list
		
		child.distance = child.distance();
		return child;
	}
	
    /**
     * Displays this Tour through Map.
     * 
     * @param m    the map that effectively projects this tour 
     */
	void display(Map m) {
		m.tour = this;
		m.update(m.getGraphics());
	}
    
    /**
     * Displays this Tour.
     * 
     * @param g   graphics 
     */
	void display(Graphics g) {
		final int SIZE = 2 * Map.CITY_SIZE;
		// plot the tour
		int len = this.length();
		int x1, y1, x2, y2;
		int i;
		for (i = 0; i < len - 1; i++) {
			x1 = city(i).x;
			y1 = city(i).y;
			x2 = city(i + 1).x;
			y2 = city(i + 1).y;
			g.setColor(Color.CYAN);
			g.drawLine(x1, y1, x2, y2);
			g.setColor(Color.RED);
			g.setFont(new Font("SansSerif", Font.BOLD, 10));
			g.drawString(Integer.toString(i), x1 - SIZE, y1 + SIZE);
		}
		// close the loop
		g.setColor(Color.CYAN);
		x1 = city(len - 1).x;
		y1 = city(len - 1).y;
		x2 = city(0).x;
		y2 = city(0).y;
		g.drawLine(x1, y1, x2, y2);
		g.setColor(Color.RED);
		g.drawString(Integer.toString(i), x1 - SIZE, y1 + SIZE);

	}
}

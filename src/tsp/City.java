package tsp;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

class City {

	int x, y;
	String state;
	static final int CITY_SIZE = 10; // size of the circle plotted on the map
	final Color CITY_COLOR = Color.GREEN;

    /**
     * Constructs a city.
     * 
     * @return length of the tour
     */
	City(int x, int y) {
		this.x = x;
		this.y = y;
	}

    /**
     * Returns distance between this City and the designated other City
     * 
     * @param c the other City
     * @return distance between this City and the other City
     */
	double distance(City c) {
		// TODO return distance to city c. Euclidean distance
	    return Math.sqrt(Math.pow(x - c.x, 2)+ Math.pow(y - c.y, 2));
	    
	}
	
    /**
     * String representation of a City
     * 
     * @return length of the tour
     */
	public String toString() {
		return String.format("<City: %3d, %3d>", this.x, this.y);
	}
	
	
	
    /**
     * Displays this City.
     * 
     * @param g   graphics 
     */
    void display(Graphics g) {
        g.setColor(CITY_COLOR);
        g.fillOval(x - CITY_SIZE / 2, y - CITY_SIZE / 2, CITY_SIZE, CITY_SIZE);
        g.setColor(Color.YELLOW);
        g.setFont(new Font("SansSerif", Font.BOLD, 10));
        g.drawString(state, x + CITY_SIZE / 2, y - CITY_SIZE / 2);   
    }

}

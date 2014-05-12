package tsp;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;

public class Map extends JFrame {

	TSP tsp;

	Tour tour = null;

	int gen = 0; // which generation does 'tour' below to
	static final int WIDTH = 600;

	Map(TSP tsp) {
        this.tsp = tsp;
		this.setSize(700, 700);
		this.setTitle("Map");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public void paint(Graphics g) {
		// update(g);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		if (tour != null) {
			tour.display(g);
		}
		// display the cities
		// we display the cities after the tour so that the circles
		// will appear on top of the tour lines
		
		if (TSP.fiftyStatesMode) {
	        for (int i = 0; i < tsp.numCities(); i++) {
	            tsp.cities[i].display(g);
	        }
		} else {
    		for (int i = 0; i < tsp.numCities(); i++) {
    			displayCity(g, tsp.cities[i], i);
    		}
		}


		String title = String.format("Map [%3d] %s", gen,
				(this.tour == null ? "<null>" : tour.toString()));
		this.setTitle(title);
	}
	
	static final int CITY_SIZE = 10; // size of the circle plotted on the map

	final Color CITY_COLOR = Color.GREEN;
	
	void displayCity(Graphics g, City c, int n) {
		g.setColor(CITY_COLOR);
		g.fillOval(c.x - CITY_SIZE / 2, c.y - CITY_SIZE / 2, CITY_SIZE, CITY_SIZE);
		g.setColor(Color.YELLOW);
		g.setFont(new Font("SansSerif", Font.BOLD, 10));
		g.drawString(Integer.toString(n), c.x + CITY_SIZE / 2, c.y - CITY_SIZE / 2);
		
	}
}
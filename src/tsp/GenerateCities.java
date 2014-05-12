package tsp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GenerateCities {
    
    /**
     * Generates array of fifty state capital cities.
     * 
     * @return array of 50 Cities
     */
    static City[] fiftyStates() {
        
        City[] cities = new City[50];
       
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("uscap.txt"));
            String line;
            String[] coord;
            String delim = "[ ]+";
            int i = 0;
            while ((line = br.readLine()) != null) {
                coord = line.split(delim, 2);
                cities[i] = new City(lonToX(Float.parseFloat(coord[1])), latToY(Float.parseFloat(coord[0])));
                i++;
            }            
            br.close();
            br = new BufferedReader(new FileReader("uscap_name.txt"));
            delim = "[, ][ ]";
            i = 0;
            while ((line = br.readLine()) != null) {
                coord = line.split(delim, 2);
                cities[i].state = coord[1];
                i++;
            }
                     
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cities;
    }
    
    
    /**
     * Generates array of N x 4 Cities. Graphical representation of these Cities form an enclosing square,
     * with N Cities evenly spread across each edge. 
     * 
     * @return array N x 4 Cities
     */
	static City[] square(int N) {
	    
		 // N is the # of cities per side of the square
		
		City[] cities = new City[4 * N];

		// top-left corner of the plotted square of cities
		int x0 = 50;
		int y0 = 60;

		// width of the square of cities
		int width = Map.WIDTH;
		

		int[] horz = new SplitInterval(x0, x0 + width, N).getIntervals();
		int[] vert = new SplitInterval(y0, y0 + width, N).getIntervals();

		int i = 0;

		// top row
		for (int j = 0; j < horz.length; j++) {
			cities[i] = new City(horz[j], y0);
			i++;
			}
		// right column
		// note that j starts from 1 and not 0 so that we don't
		// recomputer the top-right corner
		for (int j = 1; j < vert.length; j++) {
			cities[i] = new City(x0 + width, vert[j]);
			i++;
		}
		// bottom row
		for (int j = horz.length - 1 - 1; j >= 0; j--) {
			cities[i] = new City(horz[j], y0 + width);
			i++;
		}
		// left columns
		// note that j>0 so that we don't redo (x0,y0)
		for (int j = vert.length - 1 - 1; j > 0; j--) {
			cities[i] = new City(x0, vert[j]);
			i++;
		}
		return cities;
	}
	
    /**
     * Normalizes latitude value of a city so that it can be shown in 600 x 600 pixel representation.
     * 
     * @return normalized latitude
     */
	private static int latToY(double latitude) {
        // us min latitude : 25
        // us max latitude : 60
	    return (int) (Map.WIDTH * (60 - latitude) / 35);	    
	}
	
    /**
     * Normalizes longitude value of a city so that it can be shown in 600 x 600 pixel representation.
     * 
     * @return normalized longitude
     */
    private static int lonToX(double longitude) {
        // us max longitude : - 65
        // us min longitude : - 165
        return (int) (Map.WIDTH * (longitude + 165) / 100);
    }
    
    
	public static void main(String[] args) {
		City[] cities = GenerateCities.square(5);
//		City[] cities = GenerateCities.fiftyStates();

		for (int i = 0; i < cities.length; i++) {
			System.out.printf("%3d -> %s\n", i, cities[i]);
		}
	}

}

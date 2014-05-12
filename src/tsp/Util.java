package tsp;

import java.util.Random;
public class Util {
    
    /**
     * Generates random integer within the designated range
     * 
     * @param m first index of the range
     * @param n last index of the range
     * @return  random integer within the range
     * @throws  IllegalArgumentException if designated starting index m is greater than the end index n
     */
	static int rndInt(int m, int n) {
	    if (m > n) throw new IllegalArgumentException();    
	    Random random = new Random();

	    return random.nextInt(n - m) + m;
	}

    /**
     * Counts the number of occurrence of a value in the array within the designated range
     * 
     * @param a     the array to search through
     * @param key   the value to look for
     * @param start first index of the range
     * @param end   last index of the range
     * @return  number of occurence of the key in the array
     * @throws NullPointerException if the provided array is null
     * @throws IllegalArgumentException if designated starting index is greater than the end index
     */
	static int count(int[] a, int key, int start, int end) {
	    if (a == null) throw new NullPointerException();
	    if (start > end) throw new IllegalArgumentException();
	    
	    int count = 0;
		for (int i = start; i <= end; i++)
		    if (a[i] == key) count += 1;

		return count;
	}
	
    /**
     * Puts the thread to sleep
     * 
     * @param n time to sleep in miliseconds
     */
	static void pause(int n) {
		try {
			Thread.sleep(n);
		} catch (Exception e) {

		}
	}

}

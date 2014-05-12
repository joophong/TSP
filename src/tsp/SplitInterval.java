package tsp;

/***
 * Split an line interval into segments of approximately the same length
 *
 */
class SplitInterval {

	int start, end;

	int num_intervals;

	int[] v;
	
    /**
     * Constructs a SplitInterval
     * 
     * @param start first index of the array
     * @param end   last index of the array
     * @param n number of segments to divide the array into
     */
	SplitInterval(int start, int end, int n) {
		this.start = start;
		this.end = end;
		this.num_intervals = n;

		// make an array of points one more than the number of intervals.
		// Three intervals and 4 points:
		// x-------x-------x-------x

		v = new int[n + 1];
		v[0] = start;
		v[v.length - 1] = end;

		split();
	}
	
    /**
     * Sets interval points in the index array.
     * 
     */
	void split() {
		int incr = (end - start) / num_intervals;
		int rem = (end - start) % num_intervals;
		int c = start;

		// the below loop runs up to one less than the last array element
		// as the last array element has the end point in it

		for (int i = 1; i <= v.length - 1 - 1; i++) {
			c += incr;
			// bump up the earlier intervals for more even distribution
			if (i <= rem) {
				c++;
			}
			v[i] = c;
		}
	}
	
    /**
     * Returns array of indices.
     * 
     * @return the array of indices that represents an array split
     */
	int[] getIntervals() {
		return this.v;
	}

	
	public static void main(String[] args) {

		SplitInterval si = new SplitInterval(15, 33, 4);
		int[] p = si.getIntervals();
		for (int i = 0; i < p.length; i++) {
			System.out.printf("%d : %d\n", i, p[i]);
		}

	}

}

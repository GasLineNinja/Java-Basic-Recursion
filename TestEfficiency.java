import junit.framework.TestCase;

public class TestEfficiency extends TestCase {
	
	MovieCollection coll = new MovieCollection(5000);
	Movie[] sortedArray = new Movie[1000000];
	Movie[] reverseArray = new Movie[1000000];
	Movie[] randomArray = new Movie[1000000];
	
	public void setUp() {
		for(int i = 0; i<1000000;i++) {
			String s = ""+(10000000+i);
			sortedArray[i] = new Movie(s,2000,1);
			reverseArray[999999-i] = new Movie(s,2000,1);
			randomArray[i] = new Movie(Double.toString(Math.random()),2000,1);
		}
	}
	
	//testing sorting via static method
	public void test01() {
		MovieCollection.sortMovies(randomArray);
		assertTrue(isSorted(randomArray));
	}

	public void test02() {
		MovieCollection.sortMovies(reverseArray);
		assertTrue(isSorted(reverseArray));
	}

	public void test03() {
		MovieCollection.sortMovies(sortedArray);
		assertTrue(isSorted(sortedArray));
	}
	
	//testing sorting via adding
	public void test04() {
		for(int i=0; i<5000; i++) {
			coll.addMovie(new Movie(Double.toString(Math.random()),2000,1));
		}
		assertTrue(isSorted(coll.getNewestMovies(5000)));
	}
	
	//testing binary search via find after adding
	public void test05() {
		for(int i = 0; i<5000; i++) {
			coll.addMovie(new Movie(Double.toString(Math.random()),2000,1));
		}
		for(int i=0; i<2000000; i++)
			coll.findMovie(new Movie(Double.toString(Math.random()),2000,1));
	}

	private boolean isSorted(Movie[] movies) {
		for(int i=0; i<movies.length-1; i++)
			if(movies[i].compareTo(movies[i+1])>0)
				return false;
		return true;
	}
}
	
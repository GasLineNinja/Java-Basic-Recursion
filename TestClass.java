import static org.junit.Assert.assertArrayEquals;

import junit.framework.TestCase;

public class TestClass extends TestCase {

	MovieCollection test1 = new MovieCollection();
	MovieCollection test2 = new MovieCollection();
	MovieCollection blank = new MovieCollection();
	
	
	Movie cars2 = new Movie("Cars 2", 2011,6.1);
	
	Movie TGGNew = new Movie("The Great Gatsby", 2013,7.2);
	Movie TGGOld = new Movie("The Great Gatsby", 1974,6.4);
	
	Movie parasite = new Movie("Parasite",2019,9.3);
	
	Movie[] test1DT = {new Movie("Star Wars", 1977,8.8),new Movie("Frozen 2", 2019,7.6),new Movie("Parasite",2019,9.3),null,null,null,null,null,null,null};
	
	Movie[] test1R = {new Movie("Frozen 2", 2019,7.6),new Movie("Star Wars", 1977,8.8),new Movie("Parasite",2019,9.3),null,null,null,null,null,null,null};
	

	Movie[] t2 = {TGGOld,TGGNew,null,null,null,null,null,null,null,null};
	
	Movie[] tt2 = {TGGNew, TGGOld,null,null,null,null,null,null,null,null};
	
	Movie[] emptyArr = {null,null,null,null,null,null,null,null,null,null};
	
	
	Movie[] SWF = { new Movie("Star Wars", 1977,8.8),new Movie("Frozen 2", 2019,7.6),null,null,null,null,null,null,null,null};
	
	public void setUp() {
		test1.addMovie(new Movie("Parasite",2019,9.3));
		test1.addMovie(new Movie("Frozen 2", 2019,7.6));
		test1.addMovie(new Movie("Star Wars", 1977,8.8));
		
		test2.addMovie(TGGNew);
		test2.addMovie(TGGOld);
		
	}
	
	
	//testing adding and finding
	public void test00() {
		MovieCollection movies = new MovieCollection(0);
		assertFalse(movies.findMovie(parasite));
		assertFalse(movies.findMovie(null));
	}
	
	public void test01() {
		MovieCollection movies = new MovieCollection(4);
		assertFalse(movies.findMovie(parasite));
		assertFalse(movies.findMovie(null));
		
		assertTrue(movies.addMovie(TGGNew));
		assertTrue(movies.addMovie(TGGOld));
		assertTrue(movies.addMovie(cars2));
		assertTrue(movies.addMovie(parasite));
		
		assertTrue(movies.findMovie(TGGNew));
		assertTrue(movies.findMovie(TGGOld));
		assertTrue(movies.findMovie(cars2));
		assertTrue(movies.findMovie(parasite));
		
		Movie blank = new Movie("Blank", 1950, 5);
		assertFalse(movies.addMovie(blank));
		assertFalse(movies.findMovie(blank));
	}
	
	public void test02() {
		MovieCollection movies = new MovieCollection(3);
		assertFalse(movies.findMovie(parasite));
		assertFalse(movies.findMovie(null));
		
		assertTrue(movies.addMovie(parasite));
		assertTrue(movies.addMovie(cars2));
		assertTrue(movies.addMovie(TGGOld));
		
		assertTrue(movies.findMovie(TGGOld));
		assertTrue(movies.findMovie(cars2));
		assertTrue(movies.findMovie(parasite));
		assertFalse(movies.findMovie(TGGNew));
		
		Movie blank = new Movie("Blank", 1950, 5);
		assertFalse(movies.addMovie(blank));
		assertFalse(movies.findMovie(blank));
	}
	
	public void test03() {
		MovieCollection movies = new MovieCollection(100);
		Movie blank = new Movie("Blank", 1950, 5);
		
		assertFalse(movies.findMovie(parasite));
		assertFalse(movies.findMovie(null));

		assertTrue(movies.addMovie(TGGNew));
		assertTrue(movies.addMovie(parasite));
		assertTrue(movies.addMovie(cars2));
		assertTrue(movies.addMovie(TGGOld));
		assertTrue(movies.addMovie(blank));
		
		assertTrue(movies.findMovie(TGGOld));
		assertTrue(movies.findMovie(cars2));
		assertTrue(movies.findMovie(parasite));
		assertTrue(movies.findMovie(TGGNew));
		assertTrue(movies.findMovie(blank));
		
		assertFalse(movies.findMovie(new Movie("Parasite",2019,9.2)));
		assertFalse(movies.findMovie(new Movie("Blank", 1951, 5)));
		assertFalse(movies.findMovie(new Movie("XXX", 2002, 5.9)));
	}
	
	public void test04() {
		MovieCollection movies = new MovieCollection(2);
		movies.addMovie(cars2);
		assertTrue(movies.findMovie(new Movie("Cars 2", 2011,6.1)));
	}
	
	
	//testing sortedness
	public void test06() {
		assertArrayEquals(test1DT,test1.movies);
		
		assertArrayEquals(t2,test2.movies);
		
		MovieCollection one = new MovieCollection(1);
		one.addMovie(cars2);
		
		MovieCollection zero = new MovieCollection(0);
		zero.sortMovies();
		
		MovieCollection five = new MovieCollection(5);
		five.addMovie(cars2);
		five.addMovie(cars2);
		five.addMovie(cars2);
		five.addMovie(cars2);
		five.addMovie(cars2);
		five.sortMovies();
	}

	
	//testing sorting with the static sort method
	//this test is random, so you may want to run it multiple times
	public void test07() {
		Movie[] movies = new Movie[(int)(Math.random() * 12)];
		for(int i=0; i<movies.length; i++)
			movies[i] = new Movie("Name", 1950 + (int)(Math.random() * 70), 5);
		MovieCollection.sortMovies(movies);
		assertTrue(isSorted(movies));
	}
	
	//testing sortedness with getNewestMovies
	public void test08() {
		test1.addMovie(new Movie("XXX", 2002, 5.9));
		test1.addMovie(new Movie("Koyaanisqatsi", 1982, 8.3));
		test1.addMovie(new Movie("The Last Unicorn", 1982, 7.5));
		assertTrue(isSorted(test1.getNewestMovies(test1.size())));
	}
	
	//longer test
	public void test11() {
		MovieCollection longer = new MovieCollection(11);
		longer.addMovie(new Movie("a", 2000, 0));
		longer.addMovie(new Movie("b", 2010, 10));
		longer.addMovie(new Movie("c", 2005, 5));
		longer.addMovie(new Movie("d", 2004, 3));
		longer.addMovie(new Movie("e", 2006, 7));
		longer.addMovie(new Movie("f", 2004, 4));
		longer.addMovie(new Movie("g", 2001, 1));
		longer.addMovie(new Movie("h", 2002, 2));
		longer.addMovie(new Movie("i", 2005, 6));
		longer.addMovie(new Movie("j", 2008, 9));
		longer.addMovie(new Movie("c", 2008, 8));
		Movie[] sorted = longer.getNewestMovies(11);
		for (int i=0; i<11; i++) {
			assertEquals(i, Math.round(sorted[i].getRating()));
		}
	}
	
	private boolean isSorted(Movie[] movies) {
		for(int i=0; i<movies.length-1; i++)
			if(movies[i].compareTo(movies[i+1])>0)
				return false;
		return true;
	}
}

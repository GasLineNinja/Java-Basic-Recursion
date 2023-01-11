/*
 * A class that stores a number of movies in an array
 * If there are n movies, they are stored in the range [0, n-1]
 * Everything else will be null
 */

public class MovieCollection {
	private static final int DEFAULT_SIZE = 10;

	Movie[] movies;
	int spotsFilled;


	public MovieCollection() {
		this(DEFAULT_SIZE);
	}

	/**
	 * create an empty collection of movies of a certain size
	 * @param size the size that the collection can hold
	 * @throws IllegalArgumentException if size is negative
	 */
	public MovieCollection(int size) {
		if(size <0) throw new IllegalArgumentException("size cannot be negative");
		movies = new Movie[size];
		spotsFilled = 0;
	}


	/**
	 * This method does the same thing as addMovie from HW9, but also calls sortMovies()
	 * at the end to maintain the sorted array at all times.
	 * @param movie. Must not be null. otherwise throw an IllegalArgumentException
	 * @return true if a movie was added, false if it was full already
	 * @throws IllegalArgumentException for a null movie
	 */
	public boolean addMovie(Movie movie) {
		if(movie==null)
			throw new IllegalArgumentException("movies cannot be null");
		if(movies.length == spotsFilled)
			return false;
		movies[spotsFilled] = movie;
		spotsFilled++;
		sortMovies();
		return true;
	}



	/**
	 * uses binary search to find the movie.
	 * @param movie to be found
	 * @return true if it the movie is in the collection. Returns false if not, or if movie is null
	 */
	public boolean findMovie(Movie movie) {
		if(movie == null)
			return false;
		return (binaryMovieSearch(movie,0,spotsFilled) >=0);
	}




	/**
	 * This is the shell of a recursive version of binary search. It takes in a movie to look for, and the start and end index of where to look for it.
	 * When you are designing a recursive algorithm, think about what a typical iteration will look like, and what the base case will look like.
	 * @param search the movie to search for
	 * @param low the first (lowest) index of the Movies array to look for it (inclusively).
	 * @param high the last (highest) index of the Movies array to look for it (exclusively).
	 * @return The index of the movie if it was found, or -1 if it was not inside the array.
	 */
	private int binaryMovieSearch(Movie search, int low, int high) { 
		//TODO
		//1. base case: if the subarray is empty, return -1
		//2. compute a midpoint
		//3. if the element at the midpoint is what we are looking for
		//		return that midpoint
		//4. if the element at the midpoint is larger
		//		recursively call on the lower half
		//		with the appropriate parameters
		//5. if the element at the midpoint is smaller
		//		recursively call on the upper half
		//		with the appropriate parameters
		//example: if low is 5 and high is 8,
		//		then we are searching over indices 5, 6, and 7
		if (low >= high) {
			return -1;
		}

		int midIndex = (low+high)/2;
		Movie midMovie = movies[midIndex];


		if (search.equals(midMovie)) {
			return midIndex;
		}

		if (search.compareTo(midMovie)==-1) {
			return binaryMovieSearch(search, low, midIndex);
		}
		
		else {
			return binaryMovieSearch(search, midIndex+1, high);
		}

	}




	/**
	 * now that the movies array must be sorted at all times, we can use binary search to find the element faster.
	 * The caveat is that we also must make sure it stays sorted after an element is removed.
	 * You can look back on how the removeMovieSlow method in HW9 did this. 
	 * @param movie. Must not be null. Otherwise throw an IllegalArgumentException
	 * @return false if the movie is not in the array, true if it was found and subsequently removed.
	 */
	public boolean removeMovie(Movie movie) {
		if(movie==null)
			throw new IllegalArgumentException("movies cannot be null");

		int found = binaryMovieSearch(movie, 0, spotsFilled);
		if(found == -1)
			return false;

		for(int i = found+1; i<spotsFilled;i++) {
			movies[i-1] = movies[i];
		}
		movies[spotsFilled-1] = null;
		spotsFilled--;
		return true;
	}

	/**
	 * This is just a simple public way to access the sorting method.
	 * It's not really necessary anymore because the movies should always be sorted.
	 * But it is needed by addMovie.
	 */
	public void sortMovies() {
		quickMovieSort(movies, 0, spotsFilled);
	}

	/**
	 * A static method that takes an array of movies and sorts it
	 * Uses the same helper methods as the instance method for sorting
	 * @param movies the array of movies to be sorted
	 */
	public static void sortMovies(Movie[] movies) {
		quickMovieSort(movies, 0, movies.length);
	}


	/**
	 * The recursive method for the quickSort operation.
	 * @param toSort, the array to be sorted
	 * @param start the starting index for the movies to be sorted
	 * @param num the number of movies, starting from the start index, to include in this sort.
	 */
	private static void quickMovieSort(Movie[] toSort, int start, int num ) {
		//TODO
		//1. base case: if the subarray is empty or very small
		//		it doesn't need to be sorted (just return)
		//2. partition this subarray around a pivot (call partition)
		//3. recursively call on the partition left of the pivot
		//4. recursively call on the partition right of the pivot
		if (start>=num-1) {
			return;
		}

		int pivot = partition(toSort, start, num);
		
		if(start<pivot-1)
		quickMovieSort(toSort, start, pivot);
		
		if(pivot<num-1)
		quickMovieSort(toSort, pivot, num);
	}


	/**
	 * Private helper method used by quickMovieSort
	 * It only works on a subsection of the array, given by start and num
	 * It should choose a pivot, and move elements
	 * so that everything smaller is to the left
	 * and everything larger is to the right of the pivot
	 * @param toSort, the array to be partitioned
	 * @param start the index of the first element to look at 
	 * @param num how many elements we are looking at
	 * @return the index of the pivot
	 */
	private static int partition(Movie[] toSort, int start, int num)
	{
		//TODO
		//1. choose a pivot value
		//		(if you pick poorly, you may not pass testEfficiency)
		//2. loop over the elements in the subarray defined by start and num
		//		keeping track of the lowest index you have not swapped into yet
		//3. when you find an element smaller than the pivot
		//		swap it with the lowest index you have not swapped into yet
		//		if this results in swapping the pivot, keep track of where the pivot is
		//4. after the loop is over, swap the pivot into the lowest index you have not swapped into yet
		//		this puts everything lower (the stuff already swapped) to its left
		//		and everything higher to its right
		//5. return the ending index of the pivot

		// Middle pivot code
		
		int pivot = (start+num-1)/2;
		int i = start;
		int j = num-1;
		Movie temp;
	
		while(i<=j) {
			while(toSort[i].compareTo(toSort[pivot])==-1) {
				i++;
			}
			while(toSort[j].compareTo(toSort[pivot])==1) {
				j--;
			}
			if(i<=j) {
				temp = toSort[i];
				toSort[i] = toSort[j];
				toSort[j] = temp;
				i++;
				j--;
			}
		}
		return i;
		
		/*for(int i=start; j<=pivot; j++) {
			for(int j=num-1; k>=pivot; k--) {
				if(toSort[j].compareTo(toSort[pivot])<=0) {
					
				}
				if(toSort[k].compareTo(toSort[pivot])>=0) {
					
				}
				if(toSort[j].compareTo(toSort[k])==1) {
					temp = toSort[j];
					toSort[j] = toSort[k];
					toSort[k] = temp;
					openIndex++;
				}
			}
		}
		return openIndex;*/

		// Far right pivot code **WORKS**
		/*int pivot = num;
		int i = start-1;


		for (int j=start; j<num; j++) {
			if (toSort[j].compareTo(toSort[pivot]) == -1) {
				i++;
				Movie temp = toSort[i];
				toSort[i] = toSort[j];
				toSort[j] = temp;
			}	
		}
		Movie temp = toSort[i+1];
		toSort[i+1] = toSort[pivot];
		toSort[pivot] = temp;
		return (i+1);*/

	}



	/**
	 * Return the newest n Movies in a new array.
	 * @param n the number of Movies to return
	 * @return the array of the newest n movies (in the same order they were in)
	 * @throws IllegalArgumentException if n is not valid
	 */
	public Movie[] getNewestMovies(int n) {
		if(n<0 || n>spotsFilled)
			throw new IllegalArgumentException("n has to be greater than 0");
		Movie[] output = new Movie[n];
		for(int i = 0; i<n; i++) {
			output[i] = movies[spotsFilled-n+i];
		}
		return output;
	}





	/**
	 * MovieCollection’s toString should call each movie’s toString method on a different line. 
	 */
	public String toString() {
		String output = "";
		for(int i=0; i<spotsFilled; i++) {
			output = output + movies[i].toString() + "\n";
		}

		return output;

	}

	public int getCapacity() {
		return movies.length;
	}

	public int size() {
		return spotsFilled;
	}

	public boolean isEmpty() {
		return spotsFilled == 0;
	}






}

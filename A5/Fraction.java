package A5;

import java.util.Arrays;

/**
 * @brief  Class Fraction for fractions (rational numbers)
 * 
*/
class Fraction {
    

	private int numerator; // numeraterator
	private int denominator; // denominator

	public Fraction() {
		numerator = 0;
		denominator = 1;
	}

	public Fraction(int num) {
		numerator = num;
		denominator = 1;
	}

	public Fraction(int num, int den) {
		numerator = num;
		denominator = den;
		if (denominator == 0) {
			numerator = 0;
			denominator = 1;
		}
	}

	/**
	 * @return the numerator
	 */
	public int getNumerator() {
		return numerator;
	}

	/**
	 * setter method for field numerator
	 * 
	 * @param numerator the numerator to set
	 */
	public void setNumerator(int numerator) {
		this.numerator = numerator;
	}

	/**
	 * @return the denominator
	 */
	public int getDenominator() {
		return denominator;
	}

	/**
	 * @param denominator the denominator to set
	 */
	public void setDenominator(int denominator) {
		if (denominator != 0) {
			this.denominator = denominator;
		}
	}

	/**
	 * The floating point value of the fraction
	 * 
	 * @return
	 */
	public double value() {
		if (denominator != 0) {

			return (double) numerator / denominator;
		} else {
			return numerator;
		}
	}

	/**
	 * Expands the fraction by x
	 * 
	 * @param x
	 */
	public void expand(int x) {
		numerator *= x;
		if (x != 0) {
			denominator *= x;
		}
	}

	/**
	 * 
	 * @param a
	 * @param b
	 * @return the ggt
	 */
	private static int ggt(int a, int b) {
		int ggt = 0;
		if (a == b) {
			ggt = b;
		} else {
			while (b != 0) {
				if (a > b) {
					a = a - b;

				} else {
					b = b - a;
				}
			}
			ggt = a;
		}
		return ggt;
	}

	/**
	 * shortens the fraction
	 */
	public void shorten() {
		// minus
		if (numerator < 0 && denominator < 0) {
			numerator = Math.abs(numerator);
			denominator = Math.abs(denominator);
		} else if (denominator < 0) {
			denominator = -denominator;
			numerator = -numerator;
		}
		// ggt see exercise 2
		int ggtValue = ggt(Math.abs(numerator), denominator);
		numerator /= ggtValue;
		denominator /= ggtValue;
	}

	/**
	 * @return a String representation of this Fraction
	 */
	public String toString() {
		return (numerator + " / " + denominator);
	}

	
	/**
	 * Addition operations, shortens the Fraction. 
	 * @param x add this fraction to this
	 *
	 */
	public void addMut(final Fraction x) {

		// expand
		numerator *= x.denominator;
		int otherN = x.numerator * denominator;
		denominator *= x.denominator;
		// add
		numerator += otherN;
		// shorten
		shorten();
	}

	
	/** 
	 * @param x multiplier for inplace multiply
	 */
	public void multMut(final Fraction x) {
		numerator *= x.numerator;
		denominator *= x.denominator;
	}

	
	/** 
	 * @param x the Fraction this Fraction is compared to
	 * @return int  0 if x == this, 1 if x > this , -1 if x < this
	 */
	public int compareTo(final Fraction x) {
		double v = value();
		double xv = x.value();
		if (xv < v) {
			return -1;
		}
		if (xv > v) {
			return 1;
		}
		// if(v == xv)
		return 0;
	}

	// HIER GEHT'S LOS
	public static void mutSwap(Fraction a, Fraction b){
		// Ring-exchange / swap. whatever...
		Fraction ring = new Fraction(b.getNumerator(), b.getDenominator());

		b.setDenominator(a.getDenominator());
		b.setNumerator(a.getNumerator());

		a.setDenominator(ring.getDenominator());
		a.setNumerator(ring.getNumerator());
	}
	public static void sortFractions(Fraction[] list){
		mergeSort(list, 0, list.length-1);
	}

	private static void merge(Fraction arr[], int left, int middle, int right) {
		int low = middle - left + 1;                    //size of the left subarray
		int high = right - middle;                      //size of the right subarray

		Fraction L[] = new Fraction[low];                             //create the left and right subarray
		Fraction R[] = new Fraction[high];

		int i = 0, j = 0;

		for (i = 0; i < low; i++)                               //copy elements into left subarray
		{
			L[i] = arr[left + i];
		}
		for (j = 0; j < high; j++)                              //copy elements into right subarray
		{
			R[j] = arr[middle + 1 + j];
		}


		int k = left;                                           //get starting index for sort
		i = 0;                                             //reset loop variables before performing merge
		j = 0;

		while (i < low && j < high)                     //merge the left and right subarrays
		{
			if (L[i].value() <= R[j].value())
			{
				arr[k] = L[i];
				i++;
			}
			else
			{
				arr[k] = R[j];
				j++;
			}
			k++;
		}

		while (i < low)                             //merge the remaining elements from the left subarray
		{
			arr[k] = L[i];
			i++;
			k++;
		}

		while (j < high)                           //merge the remaining elements from right subarray
		{
			arr[k] = R[j];
			j++;
			k++;
		}
	}


	private static void mergeSort(Fraction arr[], int left, int right)       //helper function that creates the sub cases for sorting
	{
		int middle;
		if (left < right) {                             //sort only if the left index is lesser than the right index (meaning that sorting is done)
			middle = (left + right) / 2;

			mergeSort(arr, left, middle);                    //left subarray
			mergeSort(arr, middle + 1, right);               //right subarray

			merge(arr, left, middle, right);                //merge the two subarrays
		}
	}

	// HIER SIND WIR DURCH

	public static void main(String[]args){
		Fraction[] test = new Fraction[]{new Fraction(1, 2), new Fraction(2, 2), new Fraction(3, 2), new Fraction(1, 3)};
		sortFractions(test);
		System.out.println(Arrays.toString(test));

	}
}

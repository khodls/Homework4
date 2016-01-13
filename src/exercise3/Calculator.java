package exercise3;

import java.util.List;

/**
 * A basic calculator class. It is able to compute basic integer math in
 * addition to a unique function called the "Sum Difference".
 * 
 * @author Jordan Longato(longatoj@msoe.edu)
 *
 */
public class Calculator implements CalculatorInterface {

	/**
	 * Adds the integers in the list and returns their sum.
	 * 
	 * @param numbers
	 *            The list of integers to add
	 * @return Integer which is the result of adding all the numbers in the list
	 */
	public int add(List<Integer> numbers) {
		int sum = 0;
		for (Integer i : numbers) {
			sum = Math.addExact(i, sum);
		}
		return sum;
	}

	/**
	 * Subtracts the integers in the list and returns the resulting integer.
	 * 
	 * @param numbers
	 *            The list of integers to subtract
	 * @return Integer which is the result of subtracting the numbers in order
	 *         of the list. Returns 0 if list contains no numbers.
	 */
	public int subtract(List<Integer> numbers) {
		int result = 0;
		// Start subtraction at the first number in the list
		// if there is one
		if (numbers.size() != 0) {
			result = numbers.get(0);
		}
		//Start subtraction at second number in list
		for (int i = numbers.size()-1; i > 0; i--) {
			Integer current = numbers.get(i);
			result = Math.subtractExact(result,current );
		}
		return result;
	}

	/**
	 * Returns the product of the integers in the list
	 * 
	 * @param numbers
	 *            The list of integers to find the product of
	 * @return Integer which is the product of all the numbers. Returns 0 if
	 *         list contains no numbers
	 */
	public int product(List<Integer> numbers) {
		int result = 1;//For multiplication start at 1

		// If list is empty set result to zero
		if (numbers.size() == 0) {
			result = 0;
		}
		//Loop through all numbers and multiple them together.
		for (Integer i : numbers) {
			result = Math.multiplyExact(i, result);
		}
		return result;
	}

	/**
	 * Returns the quotient of all the numbers in the list starting with the
	 * first and second numbers in the list. If the list contains less than 2
	 * numbers or any number but the first number is zero this method will throw
	 * an ArithmaticException.
	 * 
	 * @param numbers
	 *            The list of integers to find the quotient of
	 * @return Integer representing the quotient of the numbers in order of the
	 *         list.
	 */
	public int quotient(List<Integer> numbers) {
		int result = 0;
		// Start quotient at the first number in the list
		// if there is one
		if(numbers.size() == 1){
			throw new ArithmeticException("To few numbers for quotient!");
		}
		if (numbers.size() != 0) {
			result = numbers.get(0);
		}
		//Starting at second number in list find the quotient of all numbers.
		for (int i = 1; i < numbers.size(); i++) {
			Integer current = numbers.get(i);
			result /= current;
		}
		return result;
	}

	/**
	 * First, adds both lists to find the sum of each list(S1 and S2), then find
	 * the difference of S1 - S2 and returns the resulting "Sum Difference"
	 * 
	 * @param numbers1
	 *            First list of integers to compute sumDifference of (S1)
	 * @param numbers2
	 *            Second list of integers to compute sumDifference of (S2)
	 * @return The sum difference of both lists of integers
	 */
	public int sumDifference(List<Integer> numbers1, List<Integer> numbers2) {
		int sumOfSet1 = 0;
		int sumOfSet2 = 0;
		int result = 0;
		sumOfSet1 = this.add(numbers1);
		sumOfSet2 = this.add(numbers2);
		result = sumOfSet1 - sumOfSet2;
		return result;
	}

}

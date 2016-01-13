package exercise3;

import java.util.List;

public interface CalculatorInterface {

	public int add(List<Integer> numbers);
	public int subtract(List<Integer> numbers);
	public int product(List<Integer> numbers);
	public int quotient(List<Integer> numbers);
	public int sumDifference(List<Integer> numbers1, List<Integer> numbers2);
}

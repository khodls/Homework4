package exercise3.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import exercise3.CalcDriver;

/**
 * 
 * @author khodls, longatoj
 *
 */
public class CalcDriverTest {
	CalcDriver driver;
	double DELTA = .001;
	@Before
	public void setup(){
		driver = new CalcDriver();
	}
	@Test
	public void testPraseList(){
		String input = "add 5 0 ";
		List<Integer> expected = new ArrayList<Integer>();
		expected.add(5);
		expected.add(0);
		List<Integer> ints = driver.parseList(input);
		assertEquals(ints,expected);
	}
	
	@Test
	public void testPerformOpAdd(){
		String operation = "add";
		List<Integer> numbers = new ArrayList<Integer>();
		numbers.add(4);
		numbers.add(8);
		int expected = 4 + 8;
		int result = driver.performOp(operation, numbers);
		assertEquals(result, expected);
		
	}
	@Test
	public void testPerformOpSub(){
		String operation = "sub";
		List<Integer> numbers = new ArrayList<Integer>();
		numbers.add(4);
		numbers.add(8);
		int expected = 4 - 8;
		int result = driver.performOp(operation, numbers);
		assertEquals(result, expected);
	}
	@Test
	public void testPerformOpMul(){
		String operation = "mul";
		List<Integer> numbers = new ArrayList<Integer>();
		numbers.add(4);
		numbers.add(8);
		int expected = 4  * 8;
		int result = driver.performOp(operation, numbers);
		assertEquals(result, expected);
	}
	@Test
	public void testPerformOpDiv(){
		String operation = "div";
		List<Integer> numbers = new ArrayList<Integer>();
		numbers.add(4);
		numbers.add(8);
		int expected = 4 / 8;
		int result = driver.performOp(operation, numbers);
		assertEquals(result, expected);
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void testPerformOpBadOperation(){
		String operation = "Bang";
		List<Integer> numbers = new ArrayList<Integer>();
		numbers.add(4);
		numbers.add(8);
		int expected = 4 - 8;
		int result = driver.performOp(operation, numbers);
		assertEquals(result, expected);
	}
	@Test
	public void testPerformDiffSum(){
		List<Integer> numbers1 = new ArrayList<Integer>();
		numbers1.add(4);
		numbers1.add(8);
		List<Integer> numbers2 = new ArrayList<Integer>();
		numbers2.add(9);
		numbers2.add(10);
		double expected = (4 + 8) - (9 + 10);
		double result = driver.performDiffSum(numbers1, numbers2);
		assertEquals(result, expected, DELTA);
	}
	@Test
	public void testParseOp(){
		String expected = "div";
		String input = "div 2 3";
		String result = driver.parseOp(input);
		assertEquals(result, expected);
	}
	
	@Test
	public void testBranchHistoryOnParseList(){
		
		String operation = "div";
		List<Integer> expected = new ArrayList<Integer>();
		expected.add(5);
		
		List<Integer> numbers = new ArrayList<Integer>();
		numbers.add(4);
		numbers.add(8);
		int operationResult = driver.performOp(operation, numbers);
		expected.add(operationResult);
		
		List<Integer> result = driver.parseList(" 5 !1");
		assertEquals(expected, result);
	}
}

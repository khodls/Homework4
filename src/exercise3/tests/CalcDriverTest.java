package exercise3.tests;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
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
	  private ByteArrayOutputStream sysout;
	    private ByteArrayOutputStream syserr;
	    private ByteArrayInputStream sysin;
	    
	    
	double DELTA = .001;
	@Before
	public void setup() throws Exception {
		driver = new CalcDriver();
		
		 sysout = new ByteArrayOutputStream();
	        syserr = new ByteArrayOutputStream();

	        System.setOut(new PrintStream(sysout, true, "UTF8"));
	        System.setErr(new PrintStream(syserr, true, "UTF8"));
	}
	
	@After
	public void tearDown() throws Exception{
		System.setOut(System.out);
		System.setIn(System.in);
		System.setErr(System.err);
	}
	/**
	 * Try to parse the list and get the numbers back
	 */
	@Test
	public void testPraseList(){
		String input = "add 5 0 ";
		List<Integer> expected = new ArrayList<Integer>();
		expected.add(5);
		expected.add(0);
		List<Integer> ints = driver.parseList(input);
		assertEquals(ints,expected);
	}
	/**
	 * Try to perform the add operation
	 */
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
	/**
	 * Try to perfor the subtraction operation
	 */
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
	/**
	 * Try to perform the multiply operation
	 */
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
	/**
	 * Try to perform the division operation
	 */
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
	/**
	 * Try to call a operation that does not exist
	 */
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
	/**
	 * Try to call the diffsum operation
	 */
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
	/**
	 * Try to call the div operation
	 */
	@Test
	public void testParseOp(){
		String expected = "div";
		String input = "div 2 3";
		String result = driver.parseOp(input);
		assertEquals(result, expected);
	}
	/**
	 * Try to test the potential if statement of when there is a history item being called in the operation
	 */
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
	
	
	@Test
	public void testInitNormalOperation() throws Exception{
		String newLine = System.getProperty("line.separator");
	        driver.init();
	        String console = sysout.toString();
	       String expected = "To use this calculator Enter a operation followed by a list of numbers"
					+ "\nNumbers need to be separated by a single space, to include a second list when using diffsum ensure a space is added after the colon" + newLine;
	        
	        assertEquals(expected, console);
	}
	
	@Test
	public void testSingleAddQuit() throws Exception{
		String newLine = System.getProperty("line.separator");
		String input = "add 1 2 3" + newLine + "0";
		sysin = new ByteArrayInputStream(input.getBytes("UTF8"));
	    System.setIn(sysin);
	    
	    driver.interact();
	    String console = sysout.toString();
	    String expected = "\nEnter Operation and Numbers\n" + newLine + "Result was: 6\n" + newLine + "Enter 0 to Exit, enter any key to continue\n" + newLine;
	    assertEquals(expected, console);
	}
	
	@Test
	public void testHistOperation() throws Exception{
		String newLine = System.getProperty("line.separator");
		List<Integer> numbers = new ArrayList<Integer>();
		numbers.add(4);
		numbers.add(8);
		driver.performOp("add",numbers );
		
		String input = "hist" + newLine + "0";
		sysin = new ByteArrayInputStream(input.getBytes("UTF8"));
	    System.setIn(sysin);
	    
	    driver.interact();
	    String console = sysout.toString();
	    String expected = "\nEnter Operation and Numbers\n" + newLine + "1: 12.0\n" + newLine + "Enter 0 to Exit, enter any key to continue\n" + newLine;
	    assertEquals(expected, console);
	}
	
	@Test
	public void testClearOperation() throws Exception{
		String newLine = System.getProperty("line.separator");
		List<Integer> numbers = new ArrayList<Integer>();
		numbers.add(4);
		numbers.add(8);
		driver.performOp("add",numbers );
		
		String input = "clear" + newLine + "0";
		sysin = new ByteArrayInputStream(input.getBytes("UTF8"));
	    System.setIn(sysin);
	    
	    driver.interact();
	    String console = sysout.toString();
	    String expected = "\nEnter Operation and Numbers\n" + newLine + "Cleared History\n" + newLine + "Enter 0 to Exit, enter any key to continue\n" + newLine;
	    assertEquals(expected, console);
	}
	
	@Test
	public void testDiffSumOperation() throws Exception{
		String newLine = System.getProperty("line.separator");
		
		String input = "diffsum 1 2 5: 5 6 8" + newLine + "0";
		sysin = new ByteArrayInputStream(input.getBytes("UTF8"));
	    System.setIn(sysin);
	    
	    driver.interact();
	    String console = sysout.toString();
	    String expected = "\nEnter Operation and Numbers\n" + newLine + "Result was : -11.0\n" + newLine + "Enter 0 to Exit, enter any key to continue\n" + newLine;
	    assertEquals(expected, console);
	}
	
	@Test
	public void testInvalidInput() throws Exception{
		String newLine = System.getProperty("line.separator");
		
		String input = "adfasdfa1 2 5: 5 6 8" + newLine + "0";
		sysin = new ByteArrayInputStream(input.getBytes("UTF8"));
	    System.setIn(sysin);
	    
	    driver.interact();
	    String console = sysout.toString();
	    String expected = "\nEnter Operation and Numbers\n" + newLine + "Unable to perform the operation due to invalid input by the user" + newLine + "Enter 0 to Exit, enter any key to continue\n" + newLine;
	    assertEquals(expected, console);
	}
	
	@Test
	public void testNoSpacesInput() throws Exception{
		String newLine = System.getProperty("line.separator");
		
		String input = "add 1a25658" + newLine + "0";
		sysin = new ByteArrayInputStream(input.getBytes("UTF8"));
	    System.setIn(sysin);
	    
	    driver.interact();
	    String console = sysout.toString();
	    String expected = "\nEnter Operation and Numbers\n" + newLine + "An unknown error occurred, please try again" + newLine + "Enter 0 to Exit, enter any key to continue\n" + newLine;
	    assertEquals(expected, console);
	}
}

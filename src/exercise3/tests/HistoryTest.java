package exercise3.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import exercise3.History;

/**
 * Set of tests for History.java
 * @author Jordan Longato(longatoj@msoe.edu)
 *
 */
public class HistoryTest {
	private History hist;
	private final double DELTA = .001;
	@Before
	public void before(){
		hist = new History();
	}
	/**
	 * Normal test case for add method.
	 */
	@Test
	public void testHistoryAdd() {
		double result = hist.add(20);
		assertEquals(1, result, .01);
	}
	/**
	 * Trying to add extremely large double to the history.
	 */
	@Test
	public void testHistoryAddMaxValue(){
		double result = hist.add(Double.MAX_VALUE);
		assertEquals(1, result, DELTA);
	}
	/**
	 * Trying to add extremely small double to the history.
	 */
	@Test
	public void testHistoryAddMinValue(){
		double result = hist.add(Double.MIN_VALUE);
		assertEquals(1, result, DELTA);
	}
	/**
	 * Trying to add a null value to the history.
	 */
	@Test(expected = NullPointerException.class)
	public void testHistoryNullValue(){
		Double temp = null;
		double result = hist.add(temp);
		fail();
	}
	/**
	 * Trying to get a result normally.
	 */
	@Test
	public void testGet(){
		double expectedResult = 20;
		hist.add(20);//first
		hist.add(-30);//second
		hist.add(expectedResult);//third
		double result = hist.get(3);//Get the third result
		//COmment by sean: 
		//this is returning 20 because it is returning the item that was the result three operations ago. 
		//so it is operating as expected, this is primarily for operations using ! Ex: !3 in this case. 
		assertEquals(expectedResult, result, DELTA);
	}
	/**
	 * Trying to get a history result that is out of the bounds of the history. Expected result is null
	 */
	@Test(expected = Exception.class)
	public void testGetNull(){
		hist.add(20);//first
		hist.add(-30);//second
		hist.add(50);//third
		double result = hist.get(4);//Get the fourth result
	}
	/**
	 * Trying to get the first thing in a list of 1 size
	 */
	@Test
	public void testGetZero(){
		double expected = 20;
		hist.add(expected);
		double result = hist.get(0);
		assertEquals(expected, result, DELTA);
	}
	/**
	 * Trying to remove and entry and then check if it was properly removed by trying to get it back.
	 */
	@Test
	public void testRemove(){
		double remover = 123;
		double expected = -70;
		hist.add(20);//first
		hist.add(-70);
		hist.add(remover);//third
		hist.add(expected);//Will become third once above is removed
		hist.add(908);
		hist.remove(3);//Remove the third number in the history
		double result = hist.get(3);//Get the third number
		assertEquals(expected, result, DELTA);
		
	}
	/**
	 * Trying to remove at null. Expected a null pointer exception
	 */
	@Test(expected = NullPointerException.class)
	public void testRemoveNull(){
		Integer index = null;
		hist.add(20);//first
		hist.add(-70);
		hist.remove(index);
		
	}
	/**
	 * Trying to remove at an index that is out of bounds of the history.
	 */
	@Test(expected = Exception.class)
	public void testRemoveOutOfBounds(){
		int removeIndex = 123;
		hist.add(20);//first
		hist.add(-70);
		hist.add(908);
		hist.remove(removeIndex);//Remove the third number in the history
	}
	/**
	 * Trying to clear the history, then checks if cleared by getting a certain history item. Null is 
	 * expected if the item does not exist. 
	 */
	@Test
	public void testClear(){
		hist.add(20);
		hist.add(80);
		hist.add(-20);
		hist.clear();
		Double s = hist.get(0);//Trying to get a history item that does not exsit.
		assertNull(s);
	}
	/**
	 * Test the expected output of printing the history.
	 */
	@Test
	public void testPrintAll(){
		String expected = "1: 20.0\n";
		hist.add(20);
		String result = hist.printAll();
		assertEquals(expected, result);
	}
}

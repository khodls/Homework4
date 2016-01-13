package exercise3;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * Holds the history for the calculator. 
 * @author khodls
 *
 */
public class History implements HistoryInterface {
	
	
	//the stack holding the history in order. 
	public Stack<Double> hist = new Stack<Double>();
	
	//the stack used to implement flowing back through the history without losing data. 
	public Stack<Double> buffer  = new Stack<Double>();

	/**
	 * clear both stacks to start history from scratch
	 */
	public void clear(){
		hist.clear();
		buffer.clear();
	}
	
	/**
	 * returns the specified history item. Goes back the number of times specified by revNum
	 * @param index
	 * @return
	 */
	public Double get(int index){
		
		double retVal;
		
		if(index == 0)
			try{
			retVal = hist.peek();
			}catch(EmptyStackException e){
				return null;
			}
		else{
			
			//flow back through hist to get desired result
			for(int i = 0; i<index-1; i++){
				try{
					buffer.push(hist.pop());
				}catch(EmptyStackException e){
					return null;
				}
				
			}
			
			retVal = hist.pop();
			buffer.push(retVal);
			
			//push everything back on to the hist stack that was removed. 
			for(int i = 0; i< index; i++){
				hist.push(buffer.pop());
			}
		}
		
		return retVal;
	}
	
	/**
	 * simply push the result to the history
	 * @param result
	 * @return return 1 if successful, 
	 */
	public int add(double result){
		hist.push(result);
		return 1;
	}
	
	/**
	 * removes the history at the specified index. 
	 */
	public double remove(int index){
		
		double retVal;
		
		for(int i = 0; i<index-1; i++){
			buffer.push(hist.pop());
		}
		
		retVal = hist.pop();
		
		//push everything back on to the hist stack that was removed. 
		for(int i = 0; i< index-1; i++){
			hist.push(buffer.pop());
		}
		
		return retVal;
	}
	
	/**
	 * Print all of the current history to the console. 
	 */
	public String printAll(){
		String retVal = "";
		int index = 1; 
		while(!hist.isEmpty()){
			double cur = hist.pop();
			buffer.push(cur);
			retVal += (index + ": " + cur +"\n");
			index++;
		}
		
		//reset history after printing. 
		while(!buffer.isEmpty()){
			hist.push(buffer.pop());	
			}
		
		return retVal;
	}
}

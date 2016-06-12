/*
 * Copyright 2016 turingcat.com
 * All right reserved.
 * @author Chen Guanghua E-mail: richard@turingcat.com
 * Created：Mar 5, 2016 10:00:02 AM 
 */
package com.turingcat.ttest;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;




/**
 * @author Chen Guanghua (richard@turingcat.com)
 *
 */
public class T1 {
	
	public static int distinctElementCount(int arr1[], int arr2[])
	  {
	    // INSERT YOUR CODE HERE
		//<value,count>
		Map<Integer,Integer> iMap=new HashMap<Integer,Integer>();
		for (int i = 0; i < arr1.length; i++) {
				iMap.put(arr1[i],1);					
		}
		
		for (int i = 0; i < arr2.length; i++) {
			Integer count=iMap.get(arr2[i]);
			if (count==null) {
				iMap.put(arr2[i],1);	
			}else{
				count++;
				iMap.put(arr2[i],count);	
			}					
		}
		
		int count=0;
		for(Map.Entry<Integer, Integer> entry: iMap.entrySet()){
			if (entry.getValue()==1) {
				count ++;
			}			
		}
		
		return count;    

	  }
	
	public static void main(String[] args) {
		
		ByteBuffer  b=ByteBuffer.allocateDirect(1024);
		
		

	}

}

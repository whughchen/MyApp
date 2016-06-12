/*
 * Copyright 2016 turingcat.com
 * All right reserved.
 * @author Chen Guanghua E-mail: richard@turingcat.com
 * Created：Mar 5, 2016 10:00:39 AM 
 */
package com.turingcat.ttest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


/**
 * @author Chen Guanghua (richard@turingcat.com)
 *
 */
public class T2 {
	
	public static int beautifulHomes(int[][] grid)
	  {
	    // INSERT YOUR CODE HERE

		List<TreeSet<String>> beautifulList=new ArrayList<TreeSet<String>>();
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j]==1) {
					TreeSet<String> s=new TreeSet<String>();
					    s.add(i+"_"+j);
					    beautifulList.add(s);					
						for (TreeSet<String> set : beautifulList) {
						if (set.contains(i+"_"+j+1) ||set.contains(i+"_"+(j-1))||set.contains(i+1+"_"+j) ||set.contains(i-1+"_"+j)) {
							set.add(i+"_"+j);							
						}else{
							TreeSet<String> tset=new TreeSet<String>();
							tset.add(i+"_"+j);
							beautifulList.add(tset);							
						}
					}
				}
			}			
		}		
		return beautifulList.size(); 
	    
	  }
	



	public static void main(String[] args) {
		int[][] a={{0,0,0,0,0},{0,1,1,0,0},{0,0,0,0,0},{0,0,1,1,1},{0,0,0,0,0}};
		int b=beautifulHomes(a);
		System.out.println(b);
		

	}

}

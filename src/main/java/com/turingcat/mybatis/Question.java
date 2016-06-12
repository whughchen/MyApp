/*
 * Copyright 2016 turingcat.com
 * All right reserved.
 * @author Chen Guanghua E-mail: richard@turingcat.com
 * Created：Apr 8, 2016 4:12:57 PM 
 */
package com.turingcat.mybatis;

import java.util.Date;
import java.util.List;

import com.turingcat.mybatis.QuestionDaoImpl;


/**
 * @author Chen Guanghua (richard@turingcat.com)
 *
 */
public class Question {
	private int ctrolID;
	private Date time;
	private String question;
	List<String> words;
	


	
	public int getCtrolID() {
		return ctrolID;
	}
	public void setCtrolID(int ctrolID) {
		this.ctrolID = ctrolID;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public List<String> getWords() {
		return words;
	}
	public void setWords(List<String> words) {
		this.words = words;
	}

	public Question() {	}
	public Question(int ctrolID, Date time, String question,List<String> words
			) {
		this.ctrolID = ctrolID;
		this.time = time;
		this.question = question;
		this.words = words;
	}
	


	
	public boolean isTuringcatQuestion(){
		if (QuestionDaoImpl.reservedKeyWords!=null) {
			for (int i = 0; i < this.words.size(); i++) {
				for (String word:QuestionDaoImpl.reservedKeyWords) {
					if (words.get(i).equals(word)) {
						return true;
					}
				}
			}
		}		
		return false;		
	}

	
	
}

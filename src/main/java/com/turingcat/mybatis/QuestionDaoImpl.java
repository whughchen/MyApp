/*
 * Copyright 2016 turingcat.com
 * All right reserved.
 * @author Chen Guanghua E-mail: richard@turingcat.com
 * Created：Apr 8, 2016 4:15:56 PM 
 */
package com.turingcat.mybatis;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.turingcat.mybatis.Question;




/**
 * @author Chen Guanghua (richard@turingcat.com)
 *
 */
public class QuestionDaoImpl  implements ISiriDao {
	public static List<String> reservedKeyWords=null;
	static SqlSession session;
	static{
        String resource = "conf.xml";
        InputStream is = Question.class.getClassLoader().getResourceAsStream(resource);
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        session = sessionFactory.openSession();
        
		reservedKeyWords=getReservedKeyWords();
	}


	@Override
	public int save(Question obj) {
        String statement = "com.turingcat.mybatis.questionMapping.saveUnknowQuestion";
        int count = session.insert(statement);
        return count; 
	}


	@Override
	public Object get(Question obj) {
		return null;
	}
	
	
	private static  List<String> getReservedKeyWords(){
        String statement = "com.turingcat.mybatis.questionMapping.getKeyWords";
        List<String> keyList = session.selectList(statement);
        return keyList; 
	}
	
	
	public static void main(String[] args){
		List<String> a = QuestionDaoImpl.getReservedKeyWords();
		System.out.println(a);
	}
	

}

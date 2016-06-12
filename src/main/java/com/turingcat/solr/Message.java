/*
 * Copyright 2016 turingcat.com
 * All right reserved.
 * @author Chen Guanghua E-mail: richard@turingcat.com
 * Created：19 May 2016 17:00:05 
 */
package com.turingcat.solr;


import java.util.Arrays;

/**
 * @author Chen Guanghua (richard@turingcat.com)
 *
 */
import org.apache.solr.client.solrj.beans.Field;  

/** 
 * 在变量的set方法上注解上lucene内部的字段名称 
 */  
public class Message {  
    private String id;  
    private String title;  
    private String content[];  
      
      
    public Message() {  
        super();  
    }  
  
    public Message(String id, String title, String[] content) {  
        super();  
        this.id = id;  
        this.title = title;  
        this.content = content;  
    }  
  
    public String getId() {  
        return id;  
    }  
      
    @Field  
    public void setId(String id) {  
        this.id = id;  
    }  
    public String getTitle() {  
        return title;  
    }  
      
    @Field("name")  
    public void setTitle(String title) {  
        this.title = title;  
    }  
    public String[] getContent() {  
        return content;  
    }  
      
    @Field("description")  
    public void setContent(String[] content) {  
        this.content = content;  
    }

	@Override
	public String toString() {
		return "Message [id=" + id + ", title=" + title + ", content=" + Arrays.toString(content) + "]";
	}  
    
    
}  
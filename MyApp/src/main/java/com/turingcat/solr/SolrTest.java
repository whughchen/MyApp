/*
 * Copyright 2016 turingcat.com
 * All right reserved.
 * @author Chen Guanghua E-mail: richard@turingcat.com
 * Created：19 May 2016 17:01:21 
 */
package com.turingcat.solr;


/**
 * @author Chen Guanghua (richard@turingcat.com)
 *
 */
import java.io.IOException;  
import java.net.MalformedURLException;  
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;  
import org.apache.solr.client.solrj.SolrServerException;  
import org.apache.solr.client.solrj.impl.HttpSolrClient; 
import org.apache.solr.client.solrj.response.FacetField.Count;  
import org.apache.solr.client.solrj.response.QueryResponse;  
import org.apache.solr.common.SolrDocument;  
import org.apache.solr.common.SolrDocumentList;  
import org.apache.solr.common.SolrInputDocument;
import org.junit.Before;
import org.junit.Test;  
 
@SuppressWarnings("deprecation")  
public class SolrTest {  
	static{  Logger.getLogger("org.apache.http").setLevel(Level.INFO);
			 Logger.getLogger("org.apache.solr").setLevel(Level.INFO);
	}
	 static int count=1;
    //指定solr服务器的地址  
    private final static String URL = "http://172.16.35.16:8983/solr/new_core";  
    
    @Before
    public void info(){
    	System.out.println("\n\n# ----------------      Start a test "+ (count++) +"  ----------------  #");
    }
      
    @Test  
    public void test1(){  
        try {  
        	HttpSolrClient server = new HttpSolrClient(URL);  
             
            SolrInputDocument doc = new SolrInputDocument();  
            //id是必填的，并且是String类型的   id是唯一的主键，当多次添加的时候，最后添加的相同id会覆盖前面的域  
            //<field name="id" type="string" indexed="true" stored="true" required="true" />  
            doc.addField("id", "1");  
            doc.addField("name", "这是我的第一个solrj程序");  
            doc.addField("description", "solr程序的运行");  
            server.add(doc);
            System.out.println("add document : "+doc);
            server.commit();  
            
        } catch (MalformedURLException e) {  
            e.printStackTrace();  
        } catch (SolrServerException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
      
    /** 
     * 基于列表的添加 
     * @throws SolrServerException 
     * @throws IOException 
     */  
    @Test  
    public void test2() throws SolrServerException, IOException{  
        List<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();  
        SolrInputDocument doc = new SolrInputDocument();  
        doc.addField("id", "2");  
        doc.addField("name", "很好，solr可以工作了");  
        doc.addField("description", "solr总算可以正式工作了");           
        docs.add(doc);
        System.out.println("add document : "+doc);
         
        
        doc = new SolrInputDocument();  
        doc.addField("id", "3");  
        doc.addField("name", "测试以下solr的添加");  
        doc.addField("description", "看看能不能添加一个列表信息");  
          
        docs.add(doc);  
        System.out.println("add document : "+doc);
        HttpSolrClient server = new HttpSolrClient(URL);  
        server.add(docs);  
        server.commit();  
    }  
      
    /** 
     * 基于javabean的添加 
     * @throws SolrServerException 
     * @throws IOException 
     */  
    @Test  
    public void test3() throws SolrServerException, IOException{  
        List<Message> msgs = new ArrayList<Message>();  
        //多值域的添加使用数组  
        msgs.add(new Message("4","基于javabean的添加", new String[]{"javabean的添加附件","javabean的添加附件1"}));  
        msgs.add(new Message("5","基于javabean的列表数据的添加", new String[]{"通过对象完成添加的附件","通过对象完成添加的附件1"}));  
          
        HttpSolrClient server = new HttpSolrClient(URL);  
        server.addBeans(msgs); 
        System.out.println("add document : "+msgs.get(0).toString());
        server.commit();  
    }  
      
    @Test  
    public void test4() throws SolrServerException, IOException{  
    	HttpSolrClient server = new HttpSolrClient(URL);  
        //定义查询字符串  
        SolrQuery query = new SolrQuery("description:*会议*");  
        //实现分页的查询  
        query.setStart(0);  
        query.setRows(5);  
        QueryResponse res = server.query(query);  
        //查询出来的结果都保存在SolrDocumentList中  
        SolrDocumentList sdl = res.getResults();  
        System.out.println(" ---------- 查询到的总数 ："+sdl.getNumFound());  
        for(SolrDocument sd : sdl){  
            System.out.println(sd.get("id")+" # "+sd.get("name")+" # "+sd.get("description"));  
        }  
    }  
      
    @Test  
    public void test5() throws SolrServerException, IOException{  
    	HttpSolrClient server = new HttpSolrClient(URL);  
        //相当于QueryParser  
        SolrQuery query = new SolrQuery("*:*");  
        query.setStart(1);  
        query.setRows(5);  
        QueryResponse res = server.query(query);  
        //可以直接查询相应的bean对象，但是不是很常用  
        //使用这种方式无法获取总数量  
        List<Message> list = res.getBeans(Message.class);  
        System.out.println(" ---------- 查询到的当前总数 ："+list.size());  
        for(Message msg : list){  
            System.out.println(msg.getId()+"#"+msg.getTitle()+"#"+msg.getContent());  
        }  
    }  
     
    /**高亮显示 */
    @Test  
    public void test6() throws SolrServerException, IOException{  
    	HttpSolrClient server = new HttpSolrClient(URL);  
        SolrQuery query = new SolrQuery("description:*solr*");  
        query.setHighlight(true).setHighlightSimplePre("<span class='red'>").setHighlightSimplePost("</span>")  
        .setStart(0).setRows(10);  
        //hl.fl表示高亮的field，也就是高亮的区域  
        query.setParam("hl.fl", "name,description");  
        QueryResponse res = server.query(query);  
          
        SolrDocumentList sdl = res.getResults();  
        System.out.println(" ---------- 查询到的总数 ："+sdl.getNumFound());  
        for(SolrDocument sd : sdl){  
//          System.out.println(sd.get("id")+"#"+sd.get("name")+"#"+sd.get("description"));  
            String id = (String) sd.get("id");  
            //在solr这里对需要加高亮的字段必须要在索引中的store=true才行  
            System.out.println(id+" # "+res.getHighlighting().get(id).get("description"));;  
              
        }  
    }  
      
    /** 
     * 测试分组  
         * 输出结果： 
         *  上海#5290 
		            深圳#2763 
		            广州#2504 
		            北京#1962 
		            东莞#1764 
		            杭州#1713 
		            苏州#1661 
		            南京#1529 
         */  
    @Test  
    public void test7() throws SolrServerException, IOException{  
    	HttpSolrClient server = new HttpSolrClient(URL);  
        SolrQuery query = new SolrQuery("*:*");  
        query.setIncludeScore(false);  
        query.setFacet(true);  
        query.addFacetField("description");  
        //query.setFacetSort(sort)  
        QueryResponse res = server.query(query);  
        List<Count> countList = res.getFacetField("description").getValues();  
        System.out.println(" ---------- 查询到的总数 ："+countList.size()); 
        for(Count count : countList){  
            System.out.println(count.getName()+"     # "+count.getCount());  
        }  
  
    }  
  
      
      
}

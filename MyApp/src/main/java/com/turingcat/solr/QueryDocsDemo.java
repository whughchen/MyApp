/*
 * Copyright 2016 turingcat.com
 * All right reserved.
 * @author Chen Guanghua E-mail: richard@turingcat.com
 * Created：19 May 2016 14:41:38 
 */
package com.turingcat.solr;


/**
 * @author Chen Guanghua (richard@turingcat.com)
 *
 */
import java.io.IOException;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.BinaryRequestWriter;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;

public class QueryDocsDemo {
	static{  Logger.getLogger("org.apache.http").setLevel(Level.INFO);}
  public static final String SOLR_URL = "http://172.16.35.16:8983/solr/new_core";

  @SuppressWarnings("deprecation")
public static void main(String[] args) throws SolrServerException, IOException {
	  HttpSolrClient server = new HttpSolrClient(SOLR_URL);
    server.setMaxRetries(1);
    server.setMaxRetries(1); // defaults to 0. > 1 not recommended.
    server.setConnectionTimeout(5000); // 5 seconds to establish TCP
    //正常情况下，以下参数无须设置
    //使用老版本solrj操作新版本的solr时，因为两个版本的javabin incompatible,所以需要设置Parser
    server.setParser(new XMLResponseParser());
    server.setSoTimeout(1000); // socket read timeout
    server.setDefaultMaxConnectionsPerHost(100);
    server.setMaxTotalConnections(100);
    server.setFollowRedirects(false); // defaults to false
    // allowCompression defaults to false.
    // Server side must support gzip or deflate for this to have any effect.
    server.setAllowCompression(true);
    
    
    //使用SolrQuery传递参数，SolrQuery的封装性更好
    server.setRequestWriter(new BinaryRequestWriter());
    SolrQuery query = new SolrQuery();
    query.setQuery("video");
    query.setFields("id","name","price","score");
    query.setSort("price", ORDER.asc);
    query.setStart(0);
    query.setRows(2);
//		query.setRequestHandler("/select");
    QueryResponse response = server.query( query );
    
    
    
    // 搜索得到的结果数
    System.out.println("Find:" + response.getResults().getNumFound());
    // 输出结果
    int iRow = 1;
    for (SolrDocument doc : response.getResults()) {
      System.out.println("----------" + iRow + "------------");
      System.out.println("id: " + doc.getFieldValue("id").toString());
      System.out.println("name: " + doc.getFieldValue("name").toString());
      System.out.println("price: "
          + doc.getFieldValue("price").toString());
      System.out.println("score: " + doc.getFieldValue("score"));
      iRow++;
    }
  }
}
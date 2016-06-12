/*
 * Copyright 2016 turingcat.com
 * All right reserved.
 * @author Chen Guanghua E-mail: richard@turingcat.com
 * Created：19 May 2016 14:42:10 
 */
package com.turingcat.solr;


/**
 * @author Chen Guanghua (richard@turingcat.com)
 *
 */
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.beans.DocumentObjectBinder;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

public class QueryBeanDemo {
	  static{  Logger.getLogger("org.apache.http").setLevel(Level.INFO);}
  public static final String SOLR_URL = "http://172.16.35.16:8983/solr/new_core";

  public static void main(String[] args) throws SolrServerException,
      IOException {
	
    // http://172.168.63.233:8983/solr/collection1/select?q=description%3A%E6%80%BB%E7%9B%AE%E6%A0%87&facet=true&facet.field=author_s
    HttpSolrServer server = new HttpSolrServer(SOLR_URL);
    server.setMaxRetries(1);
    server.setMaxRetries(1); // defaults to 0. > 1 not recommended.
    server.setConnectionTimeout(5000); // 5 seconds to establish TCP

    SolrQuery query = new SolrQuery();
    query.setQuery("description:*改革*");
    query.setStart(0);
    query.setRows(2);
    query.setFacet(true);
    query.addFacetField("author_s");

    QueryResponse response = server.query(query);
    // 搜索得到的结果数
    System.out.println("Find Number of result :" + response.getResults().getNumFound());
    // 输出结果
    int iRow = 1;
    
    //response.getBeans存在BUG,将DocumentObjectBinder引用的Field应该为 org.apache.solr.client.solrj.beans.Field
    SolrDocumentList list = response.getResults();
    DocumentObjectBinder binder = new DocumentObjectBinder();
    List<NewsBean> beanList=binder.getBeans(NewsBean.class, list);
    for(NewsBean news:beanList){
      System.out.println("get result, ID="+news.getId());
      System.out.println("get result, name="+news.getName());
    }

    for (SolrDocument doc : response.getResults()) {
      System.out.println("----------" + iRow + "------------");
      System.out.println("id: " + doc.getFieldValue("id").toString());
      System.out.println("name: " + doc.getFieldValue("name").toString());
      iRow++;
    }
    for (FacetField ff : response.getFacetFields()) {
      System.out.println(ff.getName() + "," + ff.getValueCount() + ","
          + ff.getValues());
    }
  }
}
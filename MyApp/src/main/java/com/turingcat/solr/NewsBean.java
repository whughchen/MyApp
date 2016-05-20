/*
 * Copyright 2016 turingcat.com
 * All right reserved.
 * @author Chen Guanghua E-mail: richard@turingcat.com
 * Created：19 May 2016 12:43:42 
 */
package com.turingcat.solr;

/**
 * @author Chen Guanghua (richard@turingcat.com)
 *
 */
import java.util.List;

import org.apache.solr.client.solrj.beans.Field;

class NewsBean {
  @Field
  private String id;

  @Field
  private String name;

  @Field
  private String author;

  @Field
  private String description;

  @Field("relatedLinks")
  private List<String> relatedLinks;
  
  public NewsBean(){
    
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<String> getRelatedLinks() {
    return relatedLinks;
  }

  public void setRelatedLinks(List<String> relatedLinks) {
    this.relatedLinks = relatedLinks;
  }

@Override
public String toString() {
	return "NewsBean [id=" + id + ", name=" + name + ", author=" + author + ", description=" + description
			+ ", relatedLinks=" + relatedLinks + "]";
}
  

}
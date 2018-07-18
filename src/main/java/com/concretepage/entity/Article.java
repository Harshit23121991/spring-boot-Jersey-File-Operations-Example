package com.concretepage.entity;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
@Entity
@Table(name="articles")
public class Article implements Serializable { 
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="article_id")
    private int articleId;  
	@Column(name="title")
    private String title;
	@Column(name="category")	
	private String category;
	@Column(name = "document_type", nullable = false, length = 32)
	private String documentType;

	@Column(name = "file_name", nullable = false, length = 255)
	private String filename;

	@Column(name = "document_content", nullable = false, length = 10 * 1024 * 1024)
	@Type(type = "org.hibernate.type.MaterializedBlobType")
	private byte[] documentContent;
	
	
	public int getArticleId() {
		return articleId;
	}
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDocumentType() {
		return documentType;
	}
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public byte[] getDocumentContent() {
		return documentContent;
	}
	public void setDocumentContent(byte[] documentContent) {
		this.documentContent = documentContent;
	}
} 
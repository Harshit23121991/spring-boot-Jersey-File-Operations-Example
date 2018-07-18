package com.concretepage.dao;

import java.util.List;

import com.concretepage.entity.Article;
import com.concretepage.entity.CustomerDocument;

public interface CustomerDao {
	
	List<CustomerDocument> getAllDocument();
    CustomerDocument getDocumentById(int id);
    void addDocument(CustomerDocument customerDocument);

}

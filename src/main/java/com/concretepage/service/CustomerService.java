package com.concretepage.service;

import java.util.List;

import com.concretepage.entity.Article;
import com.concretepage.entity.CustomerDocument;

public interface CustomerService {
     List<CustomerDocument> getAllDocument();
     CustomerDocument getDocumentById(int articleId);
     boolean addDocument(CustomerDocument article);
   
}

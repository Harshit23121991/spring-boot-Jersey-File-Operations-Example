package com.concretepage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.concretepage.dao.CustomerDao;
import com.concretepage.dao.IArticleDAO;
import com.concretepage.entity.Article;
import com.concretepage.entity.CustomerDocument;
@Service
public class CustomerServiceImpl implements CustomerService{
	@Autowired
	private CustomerDao customerDao;
	
	@Override
	public List<CustomerDocument> getAllDocument() {
		return customerDao.getAllDocument();
	}
	@Override
	public CustomerDocument getDocumentById(int id) {
		CustomerDocument obj = customerDao.getDocumentById(id);
		return obj;
	}
	@Override
	public boolean addDocument(CustomerDocument customerDocument) {
		
		
		 customerDao.addDocument(customerDocument);
  	   return true;
	       /*if (customerDao.customerExists(customerDocument.getFilename(), customerDocument.())) {
	    	   return false;
	       } else {
	    	   customerDao.addDocument(customerDocument);
	    	   return true;
	       }*/
		}
}

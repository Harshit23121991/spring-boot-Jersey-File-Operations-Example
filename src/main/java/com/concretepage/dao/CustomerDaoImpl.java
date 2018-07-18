package com.concretepage.dao;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamSource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.concretepage.entity.CustomObj;
import com.concretepage.entity.CustomerDocument;

@Transactional
@Repository
public class CustomerDaoImpl implements CustomerDao {
	
	@PersistenceContext	
	private EntityManager entityManager;	

	@Override
	public List<CustomerDocument> getAllDocument() {
		String hql = "FROM CustomerDocument as atcl ORDER BY atcl.oid";
		return (List<CustomerDocument>) entityManager.createQuery(hql).getResultList();
	}

	@Override
	public CustomerDocument getDocumentById(int id) {
		return entityManager.find(CustomerDocument.class, id);
	}

	@Override
	public void addDocument(CustomerDocument customerDocument) {
		byte[] b;
		try {
			b = test();
			customerDocument.setDocumentType("html");
			customerDocument.setFilename("Test.html");
			customerDocument.setDocumentContent(b);
			entityManager.persist(customerDocument);
		} catch (TransformerFactoryConfigurationError | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private byte[] test() throws TransformerFactoryConfigurationError, IOException {
		try (ByteArrayOutputStream b = new ByteArrayOutputStream();ObjectOutputStream oos = new ObjectOutputStream(b);) {
			File initialFile = new File("D:\\\\PBB\\\\SPRINT-6_16.11.2017\\\\TEST\\\\Sample.xsl");
			InputStream targetStream = new FileInputStream(initialFile);
			CustomObj CustomObj = new CustomObj("Java4", "http://Java4");

			oos.writeObject(CustomObj);
			InputStream is = new ByteArrayInputStream(b.toByteArray());
			TransformerFactory tFactory = TransformerFactory.newInstance();

			//Transformer transformer = tFactory.newTransformer(new javax.xml.transform.stream.StreamSource(is));
			Transformer transformer = tFactory.newTransformer(new javax.xml.transform.stream.StreamSource("D:\\\\PBB\\\\SPRINT-6_16.11.2017\\\\TEST\\\\Sample.xsl"));
			Source src = new StreamSource(is);
			transformer.transform(src, new javax.xml.transform.stream.StreamResult(new FileOutputStream("index.html")));
			return b.toByteArray();
		} catch (TransformerConfigurationException e1) {
			e1.printStackTrace();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (TransformerException e1) {
			e1.printStackTrace();
		}
		return null;
	}
	
	public ByteArrayOutputStream getByteArrayOutputStream(InputStream inputStream) {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		int b;
		try {
			while ((b = inputStream.read()) != -1) {
				byteArrayOutputStream.write(b);
			}
		} catch (IOException e1) {
			//throw new TechException("There was an error while fetching the document. Please try again", e1);
		}

		return byteArrayOutputStream;
	}

}

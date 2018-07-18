package com.concretepage.dao;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.concretepage.entity.Article;
@Transactional
@Repository
public class ArticleDAO implements IArticleDAO {
	@PersistenceContext	
	private EntityManager entityManager;	
	@Override
	public Article getArticleById(int articleId) {
		return entityManager.find(Article.class, articleId);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Article> getAllArticles() {
		String hql = "FROM Article as atcl ORDER BY atcl.articleId";
		return (List<Article>) entityManager.createQuery(hql).getResultList();
	}	
	@Override
	public void addArticle(Article article) {
		Path path = Paths.get("C:\\Users\\27285\\Desktop\\Test1.pdf");
		try {
			byte[] data = Files.readAllBytes(path);
			File initialFile = new File("C:\\Users\\27285\\Desktop\\Test.txt");
		    InputStream targetStream = new FileInputStream(initialFile);
		    ByteArrayOutputStream b = getByteArrayOutputStream(targetStream);
			//byte[] b = String.getBytes(Charset.forName("UTF-8"));
			
			article.setDocumentType("pdf");
			article.setFilename("Test.pdf");
			//article.setDocumentContent(b.toByteArray());
			article.setDocumentContent(data);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		entityManager.persist(article);
	}
	@Override
	public void updateArticle(Article article) {
		Article artcl = getArticleById(article.getArticleId());
		artcl.setTitle(article.getTitle());
		artcl.setCategory(article.getCategory());
		entityManager.flush();
	}
	@Override
	public void deleteArticle(int articleId) {
		entityManager.remove(getArticleById(articleId));
	}
	@Override
	public boolean articleExists(String title, String category) {
		String hql = "FROM Article as atcl WHERE atcl.title = ? and atcl.category = ?";
		int count = entityManager.createQuery(hql).setParameter(1, title)
		              .setParameter(2, category).getResultList().size();
		return count > 0 ? true : false;
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

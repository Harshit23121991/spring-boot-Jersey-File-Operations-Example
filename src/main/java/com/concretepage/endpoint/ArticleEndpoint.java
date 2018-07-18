package com.concretepage.endpoint;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.concretepage.entity.Article;
import com.concretepage.service.IArticleService;

@Component
@Path("/article")
public class ArticleEndpoint {
	private static final Logger logger = LoggerFactory.getLogger(ArticleEndpoint.class);	
	@Autowired
	private IArticleService articleService;
	@GET
	@Path("/details1")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getArticleDetails() {
		List<Article> list = articleService.getAllArticles(); 
		return Response.ok(list).build();
	}
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getArticleById(@PathParam("id") Integer id) throws IOException {
		Article article = articleService.getArticleById(id);
		//return Response.ok(article).build();
		//System.out.println(new String(article.getDocumentContent(), "UTF-8"));
		/*File file = new File(article.getFilename());
		FileInputStream fin = new FileInputStream(file);
		fin.read(article.getDocumentContent());*/
		 String s = new String(article.getDocumentContent());
		 System.out.println(s);
		 return Response.ok(article.getDocumentContent(), MediaType.APPLICATION_OCTET_STREAM).header("content-disposition","filename="+article.getFilename()).build();
	}
	@POST
	@Path("/add1")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addArticle(Article article) {
        boolean isAdded = articleService.addArticle(article);
        if (!isAdded) {
        	logger.info("Article already exits.");
	        return Response.status(Status.CONFLICT).build();
        }
        return Response.created(URI.create("/spring-app/article/"+ article.getArticleId())).build();
       
	}	
	@PUT
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)	
	public Response updateArticle(Article article) {
		articleService.updateArticle(article);
		return Response.ok(article).build();
	}
	@DELETE
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)		
	public Response deleteArticle(@PathParam("id") Integer id) {
		articleService.deleteArticle(id);
		return Response.noContent().build();
	}	
}
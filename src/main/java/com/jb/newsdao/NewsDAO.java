package com.jb.newsdao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.jb.newsmodel.News;

public class NewsDAO {	
	private boolean operationStatus;
	EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();
	EntityTransaction tx = entity.getTransaction();
	
	public News edit(int id) {
		News news = new News();
		news = entity.find(News.class, id);
		return news;
	}

	@SuppressWarnings("unchecked")
	public List<News> getNews(String title, String orderby) {
		String sql = "SELECT n FROM News n ";
		if (title != null && title != "") {
			sql += "WHERE n.title LIKE ?1 ";
		} 
		if (orderby != null && orderby != "") {
			if (orderby.equals("title")) {
				NewsAscDesc.setDateAscDesc();
				sql += "ORDER BY n.title ";
				sql += NewsAscDesc.getTitleAscDesc();
			} else {
				NewsAscDesc.setTitleAscDesc();
				sql += "ORDER BY n.date_created ";
				sql += NewsAscDesc.getDateAscDesc();
			}
		}
		List<News> listNews = new ArrayList<>();
		Query query = entity.createQuery(sql);
		if (title != null && title != "") {
			String like = "%" + title + "%";
			query.setParameter(1, like);
		}
		listNews = query.getResultList(); 
		return listNews;
	}
	
	public void update(News news) {
		News newsUpdate = new News();
		newsUpdate = entity.find(News.class, news.getNews_id());
		newsUpdate.setNews(news.getNews());;
		tx.begin();
		entity.merge(newsUpdate);
		tx.commit();
	}

	@SuppressWarnings("unchecked")
	public String add(News news) {
		String addStatus = "already exists";
		List<News> listNews = new ArrayList<>();
		Query query = entity.createQuery("SELECT n FROM News n WHERE n.title = ?1");
		query.setParameter(1, news.getTitle());
		listNews = query.getResultList(); 
		if (listNews.isEmpty()) {
			tx.begin();
			entity.persist(news);
			tx.commit();
			addStatus = "OK";
		}
		return addStatus;
	}
	
	public boolean delete(int id) {
		operationStatus = false;
		News news = new News();
		news = entity.find(News.class, id);
		tx.begin();
		entity.remove(news);
		tx.commit();
		return operationStatus;
	}

}

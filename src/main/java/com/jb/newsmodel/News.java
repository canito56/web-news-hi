package com.jb.newsmodel;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="news")
public class News {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id_news;
	@Column
	private String title;
	@Column
	private String news;
	@CreationTimestamp
//	@UpdateTimestamp
	private Timestamp date_created;

	public int getNews_id() {
		return id_news;
	}

	public void setNews_id(Integer id_news) {
		this.id_news = id_news;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNews() {
		return news;
	}

	public void setNews(String news) {
		this.news = news;
	}

	public Timestamp getDate_created() {
		return date_created;
	}

	public void setDate_created(Timestamp date_created) {
		this.date_created = date_created;
	}

	@Override
	public String toString() {
		return "News [id_news=" + id_news + ", title=" + title + ", news=" + news + 
				", date_created=" + date_created + "]";
	}

}

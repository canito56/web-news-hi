package com.jb.newsmodel;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_enabled")
public class UserEnabled {
	@Id
	private String user_enabled_id;

	public String getUser_enabled_id() {
		return user_enabled_id;
	}

	public void setUser_enabled_id(String user_enabled_id) {
		this.user_enabled_id = user_enabled_id;
	}

}

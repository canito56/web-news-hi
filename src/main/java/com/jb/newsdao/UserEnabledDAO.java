package com.jb.newsdao;

import javax.persistence.EntityManager;
import com.jb.newsmodel.UserEnabled;

public class UserEnabledDAO {	
	
	private boolean userEnabled;
	EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();	
	
	public boolean getUserEnabled(String user_enabled_id) {
		userEnabled = false;
		UserEnabled ue = new UserEnabled();
		ue = entity.find(UserEnabled.class, user_enabled_id);
		if (ue != null) {
			userEnabled = true;
		} else {
			userEnabled = false;
		}
		
		return userEnabled;		
	}
	
}

package com.jb.newsdao;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.persistence.EntityManager;

import com.jb.newsmodel.User;

public class UserDAO {
	
	private User user;
	private boolean userOK;
	private static String salt = "vamos river todavia CARAJO boquita puto!";
	EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();	
	 
	public boolean getUser(String puser, String pssw) {				
		userOK = false;
		user = new User();
		user = entity.find(User.class, puser);
		if (user != null) {
			String generatedPassword = getSecurePassword(pssw, salt);
			if (user.getUser_password().equals(generatedPassword)) {
				userOK = true;
			} else {
				userOK = false;
			}
		} else {
			userOK = false;
		}	
		
		return userOK;
	}

	public boolean setUser(User newUser) {		
		boolean signupStatus = false;
		user = new User();
		user = entity.find(User.class, newUser.getUser_id());
		if (user == null) {
			String generatedPassword = getSecurePassword(newUser.getUser_password(), salt);
			newUser.setUser_password(generatedPassword);
			entity.getTransaction().begin();
			entity.persist(newUser);
			entity.getTransaction().commit();
			signupStatus = true;
		}	
		
		return signupStatus;
	}
	
	public String setPassword(String chgpwdUser, String pwdold, String pwdnew1, String pwdnew2) {		
		String pwdMsg = null;
		if (pwdnew1.equals(pwdnew2)) {
			user = new User();
			user = entity.find(User.class, chgpwdUser);
			if (user != null) {
				String hashpwdold = getSecurePassword(pwdold, salt);
				if (user.getUser_password().equals(hashpwdold)) {
					String hashpwdnew = getSecurePassword(pwdnew1, salt);						
					if (!hashpwdold.equals(hashpwdnew)) {
						user.setUser_password(hashpwdnew);
						entity.getTransaction().begin();
						entity.merge(user);
						entity.getTransaction().commit();
						pwdMsg = "Password changed successfully, login with new password";
					} else {
						pwdMsg = "The password to change is the same as the original";
					}
				} else {
					pwdMsg = "Invalid password";
				}

			} else {
				pwdMsg = "Invalid user name or password";
			}			
		} else {
			pwdMsg = "New passwords are different";
		}	
			
		return pwdMsg;
	}
	
	public String getSecurePassword(String passwordToHash, String salt){		
		String generatedPassword = null;
		   try {
		         MessageDigest md = MessageDigest.getInstance("SHA-512");
		         md.update(salt.getBytes(StandardCharsets.UTF_8));
		         byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
		         StringBuilder sb = new StringBuilder();
		         for(int i=0; i< bytes.length ;i++){
		            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		         }
		         generatedPassword = sb.toString();
		   } catch (NoSuchAlgorithmException e){
	    	   e.printStackTrace();
	       }
		    
		return generatedPassword;
	}

}

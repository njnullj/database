package com.zju.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.zju.utils.Property;
import com.zju.dao.UserDao;
import com.zju.model.User;

@Service("UserService")
public class UserService {

	private UserDao userDao = new UserDao();

	public Map<String, Object> login(String email, String password) {
		// TODO Auto-generated method stub
		Map<String, Object> ret = new HashMap<String, Object>();
		// 1 empty check
		if (email == null || email.length() <= 0) {
			ret.put("status", Property.ERROR_EMAIL_EMPTY);
			return ret;
		}
		if (password == null || password.length() <= 0) {
			ret.put("status", Property.ERROR_PWD_EMPTY);
			return ret;
		}
		// 3 email exist?
		User user = userDao.getUserByEmail(email);
		if (user == null) {
			ret.put("status", Property.ERROR_EMAIL_NOT_REG);
			return ret;
		}
		System.out.println(user.getPassword() + "    " + password + "   " + !user.getPassword().equals(password)+"     "+user.getId());
		// 5 password validate
		if (!user.getPassword().equals(password)) {
			ret.put("status", Property.ERROR_PWD_DIFF);
			return ret;
		}
		ret.put("status", Property.SUCCESS_ACCOUNT_LOGIN);
		ret.put("user", user);

		return ret;
	}

	public Map<String,Integer> getCounterOfFollowAndShortPost(String email) {
		// TODO Auto-generated method stub
		Map<String,Integer> res = new HashMap<String,Integer>();
		User user = userDao.getUserByEmail(email);
		int followers=user.getFollowers();
		int following=user.getFollowing();
		int spost=user.getPosts();
		
		res.put("follower",followers);
		res.put("following",following);
		res.put("spost",spost);
		return res;
	}

	public String register(String username, String email, String password, String conformPwd, Map<String, String> map) {
		// TODO Auto-generated method stub
		//1 empty check
		if(email == null || email.length() <= 0) {
			return Property.ERROR_EMAIL_EMPTY;		
		}else{
			//5 email exist?
			User user = userDao.getUserByEmail(email);
			if(user.getId()!=0) {
				System.out.println(user.getId()!=0);
				return Property.ERROR_ACCOUNT_EXIST; 
			}
		}
		if(username == null || username.length() == 0) 
			return Property.ERROR_USERNAME_EMPTY;

		if(password == null || password.length() <= 0)
			return Property.ERROR_PWD_EMPTY;
		
		if(conformPwd == null || conformPwd.length() <= 0)
			return Property.ERROR_CFMPWD_EMPTY;
		if(!password.equals(conformPwd))
			return Property.ERROR_CFMPWD_NOTAGREE;
		
		User user = new User();
		user.setName(username);
		user.setEmail(email);
		user.setPassword(password);
		
		int id = userDao.save(user);
		
		map.put("id", String.valueOf(id));
		
		return Property.SUCCESS_ACCOUNT_REG;
	}

	public User getUserByEmail(String email) {
		// TODO Auto-generated method stub
		User user =userDao.getUserByEmail(email);
		return user;
	}

	public void updateUsernameAndDesc(String email, String user_name, String user_desc) {
		// TODO Auto-generated method stub
		userDao.UpdateUser(email,user_name,user_desc);
		
	}

	/**
	 * 修改密码
	 * @param email
	 * @param old_pwd
	 * @param new_pwd
	 * @return
	 */
	public String changePassword(String email, String old_pwd, String new_pwd){
		if( old_pwd == null || old_pwd.length() == 0){
			return Property.ERROR_PWD_EMPTY;
		}
		if( new_pwd == null || new_pwd.length() == 0){
			return Property.ERROR_PWD_EMPTY;
		}
		if(new_pwd.equals(old_pwd)){
			return Property.ERROR_CFMPWD_SAME;
		}
				
		
		String current_pwd = userDao.getPwdByEmail(email);
		if(!current_pwd.equals(old_pwd)){
			return Property.ERROR_PWD_NOTAGREE;
		}
		
		userDao.updatePassword(email, new_pwd);
		return Property.SUCCESS_PWD_CHANGE;
	}

	public List<User> findAllbyIDs(List<Long> ids) {
		return userDao.getUsersByIDs(ids);
	}

}

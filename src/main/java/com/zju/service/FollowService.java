package com.zju.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zju.dao.FollowDao;
import com.zju.model.Follower;
import com.zju.utils.Property;

@Service("FollowService")
public class FollowService {
	
	private FollowDao followDao = new FollowDao();
	
	public Map<String, Object> newFollowing(long user_id, String user_name, int following_user_id, String following_user_name) {
		Map<String, Object> map = new HashMap<String, Object>();
//		Following following = new Following();
//		
//		following.setUser_id(user_id);
//		following.setUser_name(user_name);
//		following.setFollowing_user_id(following_user_id);
//		following.setFollowing_user_name(following_user_name);
//		int id = followDao.saveFollowing(following);
//		if(id == 0) {
//			map.put("status", Property.ERROR_FOLLOW);
//			return map;
//		}
//		map.put("following", following);
//		
		Follower follower = new Follower();
//		follower.setUser_id(following_user_id);
//		follower.setUser_name(following_user_name);
//		follower.setFollower_user_id(user_id);
//		follower.setFollower_user_name(user_name);
//		followDao.saveFollower(follower);
//		if(id == 0) {
//			map.put("status", Property.ERROR_FOLLOW);
//			return map;
//		}
		map.put("follower", follower);
		map.put("status", Property.SUCCESS_FOLLOW);
		return map;
	}

	public List<Long> getFollowerIDs(long id) {
		// TODO Auto-generated method stub
		return followDao.getFollowerIDs(id);
	}
	
	
//	public Map<String, Object> undoFollow(int user_id, int following_user_id) {
//		Map<String, Object> map = new HashMap<String, Object>();
//		Following following = new Following();
//		following.setUser_id(user_id);
//		following.setFollowing_user_id(following_user_id);
//		if(followDao.delFollowing(following) == 0) {
//			map.put("status", Property.ERROR_FOLLOW_UNDO);
//			return map;
//		}
//		map.put("following", following);
//		
//		Follower follower = new Follower();
//		follower.setUser_id(following_user_id);
//		follower.setFollower_user_id(user_id);
//		if(followDao.delFollower(follower) > 0) {
//			map.put("status", Property.SUCCESS_FOLLOW_UNDO);
//			map.put("follower", follower);
//		} else {
//			map.put("status", Property.ERROR_FOLLOW_UNDO);
//		}
//		return map;
	}
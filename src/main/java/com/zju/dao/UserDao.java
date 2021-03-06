package com.zju.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;

import com.zju.model.User;

public class UserDao {

	public int save(User user) {
		int n = (int) (Math.random() * Math.pow(2, 30));
		final String cql = "create (n:person{name:\"" + user.getName() + "\",email:\"" + user.getEmail() + "\",pwd:\""
				+ user.getPassword() + "\",id:" + n + "})";
		Driver driver = GraphDatabase.driver("bolt://47.106.233.132:7687", AuthTokens.basic("neo4j", "s302"));
		Session session = driver.session();
		StatementResult result = session.run(cql);
		session.close();
		driver.close();
		return n;
	}

	public long getUserIdByEmail(String email) {
		// TODO Auto-generated method stub
		long id = 0;
		String cql = "match (n:person{email:\"" + email + "\"}) return n.id";
		Driver driver = GraphDatabase.driver("bolt://47.106.233.132:7687", AuthTokens.basic("neo4j", "s302"));
		Session session = driver.session();
		StatementResult result = session.run(cql);
		while (result.hasNext()) {
			Record record = result.next();
			System.out.println(record.get("n.id").toString() + " " + record.get("n.pwd").asString());
			id = record.get("n.id").asInt();
		}
		session.close();
		driver.close();

		return id;
	}

	public User getUserByEmail(String email) {
		User user = new User();

		String cql = "match (n:person{email:\"" + email
				+ "\"}) return n.id,n.email,n.name,n.pwd,n.desc,n.followers,n.following,n.weibo_num,n.avatar";
		Driver driver = GraphDatabase.driver("bolt://47.106.233.132:7687", AuthTokens.basic("neo4j", "s302"));
		Session session = driver.session();
		StatementResult result = session.run(cql);
		while (result.hasNext()) {
			Record record = result.next();
			user.setId(record.get("n.id").asNumber().longValue());
			user.setName(record.get("n.name").asString());
			user.setEmail(record.get("n.email").asString());
			user.setPassword(record.get("n.pwd").asString());
			user.setDesc(record.get("n.desc").asString());
			user.setFollowers(record.get("n.followers").asNumber().intValue());
			user.setFollowing(record.get("n.following").asNumber().intValue());
			user.setPosts(record.get("n.weibo_num").asNumber().intValue());
			user.setAvatar(record.get("n.avatar").asString());
		}
		session.close();
		driver.close();

		return user;
	}

	public String getPwdByEmail(String email) {
		String cql = "match (n:person{email:\"" + email + "\"}) return n.pwd";
		Driver driver = GraphDatabase.driver("bolt://47.106.233.132:7687", AuthTokens.basic("neo4j", "s302"));
		Session session = driver.session();
		StatementResult result = session.run(cql);
		String password = null;
		if (result.hasNext()) {
			Record record = result.next();
			password = record.get("n.pwd").asString();
			System.out.println(record.get("n.pwd").asString());
		}
		session.close();
		driver.close();
		return password;
	}

	public void UpdateUser(String email, String user_name, String user_desc) {
		// TODO Auto-generated method stub
		String cql = "match (n:person{email:\"" + email + "\"}) set n.name =\"" + user_name + "\",n.desc=\"" + user_desc
				+ "\"";
		Driver driver = GraphDatabase.driver("bolt://47.106.233.132:7687", AuthTokens.basic("neo4j", "s302"));
		Session session = driver.session();
		session.run(cql);
		session.close();
		driver.close();
	}

	public void updatePassword(String email, String new_pwd) {
		// TODO Auto-generated method stub
		String cql = "match (n:person{email:\"" + email + "\"}) set n.pwd =\"" + new_pwd + "\" return n";
		Driver driver = GraphDatabase.driver("bolt://47.106.233.132:7687", AuthTokens.basic("neo4j", "s302"));
		Session session = driver.session();
		session.run(cql);
		session.close();
		driver.close();

	}

	public List<User> getUsersByIDs(List<Long> ids) {
		// TODO Auto-generated method stub
		List<User> users = new ArrayList<User>();
		
		Driver driver = GraphDatabase.driver("bolt://47.106.233.132:7687", AuthTokens.basic("neo4j", "s302"));
		Session session = driver.session();

		for (long id : ids) {
			String cql = "match (n:person{id:" + id
					+ "}) return n.id,n.email,n.name,n.pwd,n.desc,n.followers,n.following,n.weibo_num,n.avatar";
			
			StatementResult result = session.run(cql);
			while (result.hasNext()) {
				Record record = result.next();
				User user = new User();
				user.setId(record.get("n.id").asNumber().longValue());
				user.setName(record.get("n.name").asString());
				user.setEmail(record.get("n.email").asString());
				user.setPassword(record.get("n.pwd").asString());
				user.setDesc(record.get("n.desc").asString());
				user.setFollowers(record.get("n.followers").asNumber().intValue());
				user.setFollowing(record.get("n.following").asNumber().intValue());
				user.setPosts(record.get("n.weibo_num").asNumber().intValue());
				user.setAvatar(record.get("n.avatar").asString());
				System.out.println(cql+"     "+record.get("n.name").asString());
				users.add(user);
			}
		}
		
		return users;
	}
	
//	public Map<User,User> findUserByIDs(List<Long> ids){
//		Map<User,User> followers = new HashMap<User,User>();
//		
//		Driver driver = GraphDatabase.driver("bolt://47.106.233.132:7687", AuthTokens.basic("neo4j", "s302"));
//		Session session = driver.session();
//
//		for (long id : ids) {
//			String cql = "match (n:person{id:" + id
//					+ "}) return n.id,n.email,n.name,n.pwd,n.desc,n.followers,n.following,n.weibo_num";
//			
//			StatementResult result = session.run(cql);
//			while (result.hasNext()) {
//				Record record = result.next();
//				User user = new User();
//				user.setId(record.get("n.id").asNumber().longValue());
//				user.setName(record.get("n.name").asString());
//				user.setEmail(record.get("n.email").asString());
//				user.setPassword(record.get("n.pwd").asString());
//				user.setDesc(record.get("n.desc").asString());
//				user.setFollowers(record.get("n.followers").asNumber().intValue());
//				user.setFollowing(record.get("n.following").asNumber().intValue());
//				user.setPosts(record.get("n.weibo_num").asNumber().intValue());
//				System.out.println(cql+"     "+record.get("n.name").asString());
//				followers.put(user);
//			}
//		}
//		
//		return followers;
//		
//	}
}

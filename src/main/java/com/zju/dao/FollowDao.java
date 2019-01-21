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

public class FollowDao {

	public List<Long> getFollowerIDs(long id) {
		// TODO Auto-generated method stub
		List<Long> ids = new ArrayList<Long>();

		String cql = "match (na:person{id:"+id+"})-[r:friends]->(nb:person)-[re:friends]->(nc:person) return nc.id limit 100";
		Driver driver = GraphDatabase.driver("bolt://47.106.233.132:7687", AuthTokens.basic("neo4j", "s302"));
		Session session = driver.session();
		StatementResult result = session.run(cql);
		while (result.hasNext()) {
			Record record = result.next();
			ids.add(record.get("nc.id").asNumber().longValue());
		}
		session.close();
		driver.close();
		return ids;
	}

	public List<Long> getFollowingIDs(long id) {
		// TODO Auto-generated method stub
		List<Long> ids = new ArrayList<Long>();

		String cql = "match (na:person{id:"+id+"})-[r:friends]->(n:person) return n.id limit 25";
		Driver driver = GraphDatabase.driver("bolt://47.106.233.132:7687", AuthTokens.basic("neo4j", "s302"));
		Session session = driver.session();
		StatementResult result = session.run(cql);
		while (result.hasNext()) {
			Record record = result.next();
			ids.add(record.get("n.id").asNumber().longValue());
		}
		session.close();
		driver.close();
		return ids;
	}

	public Map<String, List<User>> getUserAndFollowers(long id) {
		// TODO Auto-generated method stub
		Map<String,List<User>> res =new HashMap<String,List<User>>();
		Driver driver = GraphDatabase.driver("bolt://47.106.233.132:7687", AuthTokens.basic("neo4j", "s302"));
		Session session = driver.session();
		List<Long> ids = getFollowingIDs(id);//获取粉丝的id
		for(Long d:ids) { //查询粉丝的粉丝
			String cql = "match (n:person{id:" + d
					+ "}) return n.id,n.email,n.name,n.pwd,n.desc,n.followers,n.following,n.weibo_num,n.avatar";//获取该粉丝的信息
			StatementResult result = session.run(cql);
			while (result.hasNext()) {
				Record record = result.next();
				User follower = new User();//粉丝信息
				follower.setId(record.get("n.id").asNumber().longValue());
				follower.setName(record.get("n.name").asString());
				follower.setEmail(record.get("n.email").asString());
				follower.setPassword(record.get("n.pwd").asString());
				follower.setDesc(record.get("n.desc").asString());
				follower.setFollowers(record.get("n.followers").asNumber().intValue());
				follower.setFollowing(record.get("n.following").asNumber().intValue());
				follower.setPosts(record.get("n.weibo_num").asNumber().intValue());
				follower.setAvatar(record.get("n.avatar").asString());
				System.out.println(cql+"     "+record.get("n.name").asString());
				String next = "match (na:person{id:"+d+"})-[r:friends]->(nb:person) where nb.id <> "+d+"return nb.name, nb.avatar limit 25";//获取粉丝的粉丝的信息
				StatementResult next_result = session.run(next);
				List<User> users = new ArrayList<User>();//记录粉丝的粉丝
				while(next_result.hasNext()) {
					User user = new User();
					Record next_record = next_result.next();
					user.setName(next_record.get("nb.name").asString());
					user.setAvatar(next_record.get("nb.avatar").asString());
					users.add(user);
				}
				res.put(follower.getName(), users);
				
				for (String key : res.keySet()) {// 遍历map中的值
					   System.out.print("Key = " + key+"   的粉丝有");
					   for(User user:res.get(key)) {
						   System.out.println(user.getName()+"     ");
					   }
					   System.out.println("");
				}
				
			}
			

				  
		}
		
		return res;
	}

}

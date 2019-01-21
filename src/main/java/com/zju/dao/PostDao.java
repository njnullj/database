package com.zju.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;

import com.zju.model.Post;
import com.zju.model.User;

public class PostDao {

	public int savePost(Post post) {
		// TODO Auto-generated method stub
		int id = (int) (Math.random() * Math.pow(2, 30));
		Date now = new Date();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String time = f.format(now);
		System.out.println(time);
		final String cql = "create (n:weibo{weibo_id:" + id + ",publish_time:\"" + time + "\",author:"
				+ post.getPost_author() + ",publish_content:\"" + post.getPost_content() + "\"})";
		final String match = "MATCH (na:person),(nb:weibo) WHERE na.id = " + post.getPost_author() + " AND nb.weibo_id = "
				+ id + " CREATE (na)-[r:publish]->(nb) RETURN r";
		System.out.println(post.getPost_author() + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + id);
		Driver driver = GraphDatabase.driver("bolt://47.106.233.132:7687", AuthTokens.basic("neo4j", "s302"));
		Session session = driver.session();
		session.run(cql);
		session.run(match);
		session.close();
		driver.close();
		return id;
	}

	public Map<String,Post> getShortPosts() {
		// TODO Auto-generated method stub
		Map<String,Post> shortPosts = new HashMap<String,Post>();

		String cql = "match (n:person)-[r:publish]->(nb:weibo) return n.name,nb.publish_content,nb.publish_time order by nb.publish_time desc limit 50";
		Driver driver = GraphDatabase.driver("bolt://47.106.233.132:7687", AuthTokens.basic("neo4j", "s302"));
		Session session = driver.session();
		StatementResult result = session.run(cql);
		while(result.hasNext()) {
			Post post = new Post();
			User user = new User();
			Record record = result.next();
			user.setName(record.get("n.name").asString());
			post.setPost_content(record.get("nb.publish_content").asString());
			post.setDate(record.get("nb.publish_time").asString());

			System.out.println(user.getName()+"在"+post.getDate()+"的时候发送了一条消息"+post.getPost_content());
			shortPosts.put(user.getName(), post);
		}
		
		return shortPosts;
	}

}

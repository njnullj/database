package com.zju.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;

import com.zju.model.Post;

public class PostDao {

	public int savePost(Post post) {
		// TODO Auto-generated method stub
		int id = (int) (Math.random() * Math.pow(2, 30));
		Date now = new Date();
<<<<<<< HEAD
		SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
=======
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
>>>>>>> 276c5ccba0a395d616820b303149a11548e83306
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

}

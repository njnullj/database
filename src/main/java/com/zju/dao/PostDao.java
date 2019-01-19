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
		int id=(int) (Math.random() * Math.pow(2,30));
		Date now = new Date();
		SimpleDateFormat f=new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		String time = f.format(now);
		final String cql="create (n:weibo{weibo_id:"+id+",publish_time:\""+time+"\",author:"+post.getPost_author()+",publish_content:\""+post.getPost_content()+"\"})";
		System.out.println(post.getPost_author()+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+id);
		Driver driver=GraphDatabase.driver("bolt://47.106.233.132:7687",AuthTokens.basic("neo4j","s302"));
		 Session session = driver.session();
	     session.run(cql);
	    session.close();
	    driver.close();
		return id;
	}

}

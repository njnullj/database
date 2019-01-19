package com.zju.dao;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;

public class FollowDao {

	public List<Long> getFollowerIDs(long id) {
		// TODO Auto-generated method stub
		List<Long> ids = new ArrayList<Long>();

		String cql = "match (na:person{id:"+id+"})-[r:friends]->(n:person) return n.id limit 25";
		Driver driver = GraphDatabase.driver("bolt://47.106.233.132:7687", AuthTokens.basic("neo4j", "s302"));
		Session session = driver.session();
		StatementResult result = session.run(cql);
		while (result.hasNext()) {
			Record record = result.next();
			ids.add(record.get("n.id").asNumber().longValue());
			System.out.println(record.get("n.id").asNumber().longValue());
		}
		
		return ids;
	}

}

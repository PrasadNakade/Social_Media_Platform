package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepo extends JpaRepository<Posts, Integer> {

	List<Posts> findByUseridOrderByIdDesc(int userid);

//	Query@(value = "select p.id,p.content,p.date,p.likecount,u.name from posts p join user u on  p.userid=u.id\r\n"
//			+ "where p.userid in(select user2id from connection where userid?1 and status=2")\r\n"+
//					+  "order by p.id desc \r\n"
//			+ "limit 50;* , nativeQuery = true)"
	
	@Query(value = "SELECT p.id, p.content, p.date, p.likecount, u.username " +
            "FROM posts p " +
            "JOIN user u ON p.userid = u.id " +
            "WHERE p.userid IN (SELECT userid2 FROM connection WHERE userid1 = ?1 AND connectionstatus = 2) " +
            "ORDER BY p.id DESC " +
            "LIMIT 50", nativeQuery = true)
	List<PostsData> getPostsFromMyConnections(int userid);
}

package com.zju.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zju.dao.PostDao;
import com.zju.model.Post;
import com.zju.model.User;
import com.zju.utils.Property;

@Service("PostService")
public class PostService {

	public static final int POST_STATUS_PUB = 0; // 公开
	public static final int POST_STATUS_PRV = 1; // 私密
	public static final int POST_STATUS_SAVED = 2; // 保存
	public static final int POST_STATUS_EDIT = 3; // 编辑

	public static final int COMMENT_STATUS_ALLOWED = 0; // 允许评论
	public static final int COMMENT_STATUS_NOTALLOWED = 1; // 不允许评论

	public static final int POST_SUMMARY_LENGTH = 200;


	PostDao postDao = new PostDao();

	public Map<String, Object> newPost(long author, String title, String content, int post_status, int comment_status) {
		Map<String, Object> map = new HashMap<String, Object>();

		// 1 field check
		if (author != 0 || title == null || title.length() == 0 || content == null || content.length() == 0) {
			map.put("status", Property.ERROR_POST_EMPTY);
			return map;
		}

		/*
		 * if(post_status == null) post_status = POST_STATUS_PUB; if(post_status < 0 ||
		 * post_status > 3) { map.put("status", Property.ERROR_POST_STATUS); return map;
		 * }
		 * 
		 * if(comment_status == null) post_status = COMMENT_STATUS_ALLOWED;
		 * if(comment_status != 0 && comment_status != 1) { map.put("status",
		 * Property.ERROR_COMMENT_STATUS); }
		 */

		// 2 save post
		Post post = new Post();
		post.setPost_author(author);
		post.setTitle(title);
		post.setPost_content(content);
		post.setLike_count(0);
		post.setForward(0);
		post.setComment_count(0);

		/*
		 * //3 save tags if(param_tags != null && param_tags.length() != 0) {
		 * //此处会为tag建立index Map<String, Object> tagsmap =
		 * tagService.newTags(tagService.toList(param_tags));
		 * 
		 * post.setPost_tags_list((List<Tag>)tagsmap.get("tags")); int id =
		 * savePost(post); post.setId(id);
		 * 
		 * //4 save post tag relation for(Tag tag: (List<Tag>)tagsmap.get("tags")) {
		 * Map<String, Object> relmap = relationService.newRelation(
		 * RelationService.RELATION_TYPE_POST, post.getId(), tag.getId() ); }
		 * map.put("tags", tagsmap.get("tags")); } else {
		 */
		int id = savePost(post);
		post.setId(id);
		// map.put("tags", new ArrayList<Tag>());
		// }

		map.put("post", post);
		map.put("status", Property.SUCCESS_POST_CREATE);
		return map;
	}

	private int savePost(Post post) {
		// TODO Auto-generated method stub
		int id = postDao.savePost(post);
		return id;
	}

	public Map<String, Object> newShortPost(long author , String content) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		if(content == null || content.length() == 0){
			map.put("status", Property.ERROR_POST_EMPTY);
			return map;
		}
		Post spost = new Post();
		spost.setPost_author(author);
		spost.setPost_content(content);
		spost.setId(savePost(spost));
		map.put("spost", spost);
		map.put("status", Property.SUCCESS_POST_CREATE);
		return map;
	}

	public Map<String,Post> getShortPosts() {
		// TODO Auto-generated method stub
		return postDao.getShortPosts();
	}

}

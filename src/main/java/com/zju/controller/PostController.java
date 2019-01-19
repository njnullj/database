package com.zju.controller;

import java.util.Map;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zju.model.User;
import com.zju.service.PostService;

@Controller
@RequestMapping("/post")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public String createPost() {
		return "post/create";
	}
	
	@ResponseBody
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public Map<String, Object> createPost(					
						@RequestParam("title") String title,
						@RequestParam("content") String content,
						@RequestParam("post_status") int post_status,
						@RequestParam("comment_status") int comment_status,
						@RequestParam("tags") String param_tags,
						HttpSession session) {
				
		User user = (User)session.getAttribute("user");
		//String post_cover = (String) session.getAttribute("post_cover");
		//session.removeAttribute("post_cover");
		//1 save post
		Map<String, Object> map = postService.newPost(user.getId(), 
													  title, 
													  content, 
													  post_status, 
													  comment_status);
//		String status = (String)map.get("status");
//		Post post = (Post)map.get("post");
		
		
		/*
		//2 add event 
		if(Property.SUCCESS_POST_CREATE.equals(status)) {
			int event_id = eventService.newEvent(Dic.OBJECT_TYPE_POST, post);
			
			//3 push to followers
			List<Integer> followers = followService.getFollowerIDs(user.getId());
			followers.add(user.getId());
			feedService.push(followers, event_id);
			
			//4 push to users who follow the tags in the post
			List<Tag> tags = (ArrayList<Tag>)map.get("tags");
			//push to users who follow the tags
			Set<Integer> followers_set = new HashSet<Integer>();
			for(Tag tag : tags) {
				List<Integer> i_users = interestService.getUsersInterestedInTag(tag.getId());
				for(int u: i_users) {
					if(u != user.getId())
						followers_set.add(u);
				}
							
				//cache feeds to tag list
				feedService.cacheFeed2Tag(tag.getId(), event_id);
			}
			feedService.push(new ArrayList<Integer>(followers_set), event_id);
			
		}
		*/
		return map;
		
	}

}

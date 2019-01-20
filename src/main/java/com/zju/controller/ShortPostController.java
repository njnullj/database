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
@RequestMapping("/spost")
public class ShortPostController {

	@Autowired
	private PostService postService;

//	@Autowired
//	private UserService userService;

	@ResponseBody
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public Map<String, Object> createPost(@RequestParam("content") String content, HttpSession session) {
		User user = (User) session.getAttribute("user");
		Map<String, Object> map = postService.newShortPost(user.getId(), content);
		
//		List<Integer> followers = followService.getFollowerIDs(user.getId());
//		followers.add(user.getId());
//		feedService.push(followers, event_id);

		// map.put("avatar", userService.getUser(user.getId()).getUser_avatar());
		map.put("author_name", user.getName());

		return map;
	}

}

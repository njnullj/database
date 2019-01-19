package com.zju.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zju.model.User;
import com.zju.service.FollowService;
import com.zju.service.UserService;
import com.zju.utils.Dic;

@Controller
@RequestMapping("/explore")
public class ExploreController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private FollowService followService;
	
	@RequestMapping("")
	public ModelAndView explore(HttpSession session){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("explore");
		
		User user = (User) session.getAttribute("user");
		
		
		
//		List<User> rec_users = userService.getRecommendUsers(user==null?0:user.getId(), 4);
//		mav.addObject("isFollowings", followService.isFollowing(user==null?0:user.getId(), rec_users));
		
		
		mav.addObject("dic", new Dic());
		return mav;
	}

}

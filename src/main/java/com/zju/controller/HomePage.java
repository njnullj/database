package com.zju.controller;

import java.util.HashMap;
import java.util.Map;

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
public class HomePage {

	@Autowired
	private UserService userService;
	
	@Autowired
	private FollowService followService;
	
	@RequestMapping("/index")
	public ModelAndView showHomePage(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		User user = (User)session.getAttribute("user");
		if(user == null) {
			return mav;
		}

		Map<String,Integer> res =new HashMap<String,Integer>();
		res=userService.getCounterOfFollowAndShortPost(user.getEmail());
		
		mav.addObject("counter",res);
		mav.addObject("dic",new Dic());
		//List<Event>
		return mav;
	}
	
	@RequestMapping("/welcome")
	public ModelAndView welcome(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		if(session.getAttribute("user")!=null) {
			mav.setViewName("redirect:/index");
			return mav;
		}
		mav.setViewName("welcome");
		mav.addObject("dic",new Dic());
		return mav;
	}
	
	@RequestMapping("/followers")
	public ModelAndView getFollowers(HttpSession session){
		ModelAndView mav = new ModelAndView();
		mav.addObject("followers", userService.findAllbyIDs(
								   followService.getFollowerIDs(
										   ((User)session.getAttribute("user")).getId())
								   ));
		
		mav.setViewName("follower");
		return mav;
	}
	
	@RequestMapping("/followings")
	public ModelAndView getFollowings(HttpSession session){
		ModelAndView mav = new ModelAndView();
		mav.addObject("followings", userService.findAllbyIDs(
									followService.getFollowingIDs(
											((User)session.getAttribute("user")).getId())
									));
		
		mav.setViewName("following");
		return mav;
	}
	
	
	@RequestMapping("/404")
	public ModelAndView pageNotFound() {
		return new ModelAndView("404");
	}
	
	@RequestMapping("/500")
	public ModelAndView error500() {
		return new ModelAndView("500");
	}

}

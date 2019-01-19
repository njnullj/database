package com.zju.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zju.model.User;
import com.zju.service.UserService;
import com.zju.utils.Dic;

@Controller
public class HomePage {

	@Autowired
	private UserService userService;
	
	
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
			System.out.println("我一直在执行!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!22222222222222");
			return mav;
		}
		mav.setViewName("welcome");
//		List
		mav.addObject("dic",new Dic());
		return mav;
	}
}

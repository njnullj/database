package com.zju.controller;

import java.util.HashMap;

import java.util.Map;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zju.utils.Property;
import com.zju.model.User;
import com.zju.service.UserService;

@Controller
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	private UserService userService;
	
	/**
	 * 返回account/login.jsp页面
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login(HttpSession session) {
		if(session.getAttribute("user") != null)
			return "redirect:/";
		return "account/login";
	} 
	
	@RequestMapping(value="/setting/info", method=RequestMethod.GET)
	public String settingInfoPage(HttpSession session){		
		return "account/setting/info";
	}
	
	@ResponseBody
	@RequestMapping(value="/setting/info", method=RequestMethod.POST)
	public Map<String, Object> settingInfo(@RequestParam("user_name") String user_name, 
							  @RequestParam("user_desc") String user_desc,
							  HttpSession session){	
		User me = (User)session.getAttribute("user");
		Map<String, Object> map = new HashMap<String, Object>();
		
		String status = null;
		if(user_name == null || user_name.length() == 0){
			user_name = me.getName();
		} else {
			User user = userService.getUserByEmail(me.getEmail());
			
			if(user.getName() == user_name){
				status = Property.ERROR_USERNAME_EXIST;
				map.put("status", status);
				return map;
			} 
		}
		
		
		//username is ok, but return a error status
		status = Property.ERROR_USERNAME_NOTEXIST;
		userService.updateUsernameAndDesc(me.getEmail(), 
										  user_name,
										  user_desc);
		//update session
		me.setName(user_name);
		me.setDesc(user_desc);
	
		map.put("status", status);
		return map;
	}
	
	
	
	@RequestMapping(value="/resetpwd", method=RequestMethod.GET)
	public ModelAndView resetpwdPage(@RequestParam("key") String key, 
						   @RequestParam("email") String email,
						   HttpSession session){
		ModelAndView mav = new ModelAndView();
		//set user login
		User user = userService.getUserByEmail(email);
		session.setAttribute("user", user);
		String status = null;
		status = Property.SUCCESS_PWD_RESET_ALLOWED;

		mav.addObject("status", status);
		mav.addObject("SUCCESS_PWD_RESET_ALLOWED", Property.SUCCESS_PWD_RESET_ALLOWED);
		mav.addObject("ERROR_PWD_RESET_NOTALLOWED", Property.ERROR_PWD_RESET_NOTALLOWED);
		mav.setViewName("account/resetpwd");
		return mav;
	}
	
	//@RequestMapping
	
	/**
	 *ResponseBody可以把返回的对象转换成json
	 * 负责处理ajax请求
	 * @param email
	 * @param password
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public Map<String, Object> login(@RequestParam("email") String email,
					    @RequestParam("password") String password,
					    HttpSession session) {
		
		Map<String ,Object> ret = new HashMap<String, Object>();
		ret = userService.login(email,password);
		String status = (String) ret.get("status");
		if(Property.SUCCESS_ACCOUNT_LOGIN.equals(status)){
			session.setAttribute("user", (User)ret.get("user"));
			System.out.println(((User)ret.get("user")).getId());
			System.out.println(((User)(session.getAttribute("user"))).getId());
			System.out.println("登陆成功");
		}
		return ret;
	}
	
	@RequestMapping(value="/register",method=RequestMethod.GET)
	public String register() {
		return "account/register";
	}
	
	@ResponseBody
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public Map<String, String> register(@RequestParam("username") String username,
										@RequestParam("email") String email,
						 				@RequestParam("password") String password,
						 				@RequestParam("cfmPwd") String cfmPwd) {
		System.out.println("resister....");
		Map<String, String> map = new HashMap<String, String>();
		String status = userService.register(username, email, password, cfmPwd, map);

		map.put("status", status);
		return map;
	}
	
	@RequestMapping(value="/setting/security")
	public String settingSecurity(HttpSession session){
		return "account/setting/security";
	}
	
	@ResponseBody
	@RequestMapping(value="/changepwd", method=RequestMethod.POST)
	public Map<String, Object> changepwd(@RequestParam("old_pwd") String old_pwd, 
										@RequestParam("new_pwd") String new_pwd,
										HttpSession session){
		Map<String, Object> map = new HashMap<String, Object>();
		User user = (User)session.getAttribute("user");
		map.put("status", userService.changePassword(user.getEmail(), old_pwd, new_pwd));
		return map;
	}
	
}

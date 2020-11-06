package com.shiro.user.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.pack.entity.ReturnData;
import com.shiro.user.entity.User;
import com.shiro.user.ser.UserSer;

@RestController
@RequestMapping(value = "/user")
public class UserController {
	@Autowired
	private UserSer userSer;

	@PostMapping(value = "/register.g")
	public ModelAndView register(User user) {
		// 使用用户名当作盐值
		Object salt = ByteSource.Util.bytes(user.getName());
		user.setPassword(new SimpleHash("MD5", user.getPassword(), salt, 1024).toString());
		userSer.save(user);
		return new ModelAndView("login");
	}
	
	@PostMapping(value = "/login.g")
	public ModelAndView login(User user) {
		ModelAndView mav = new ModelAndView();
		Subject currentUser = SecurityUtils.getSubject();
		if (!currentUser.isAuthenticated()) {
			UsernamePasswordToken token = new UsernamePasswordToken(user.getName(), user.getPassword());
			token.setRememberMe(true);
			try {
				System.out.println("1. " + token.hashCode());
				currentUser.login(token);
			} catch (IncorrectCredentialsException e) {
				mav.addObject("msg", "认证失败");
				mav.setViewName("login");
				return mav;
			} catch (AuthenticationException e) {
				mav.addObject("msg", e.getMessage());
				mav.setViewName("login");
				return mav;
			}
		}
		mav.addObject(new ReturnData("登陆成功", 2, 1, null));
		mav.setViewName("redirect:/templates/main.html");
		return mav;
	}
}

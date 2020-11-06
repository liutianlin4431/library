package com.shiro.comp.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.shiro.user.entity.User;

@RestController
@RequestMapping(value = "/comp")
public class CompController {
	@RequiresRoles("sys")
	@GetMapping(value = "/list.g")
	public ModelAndView list(User user) {

		return new ModelAndView("comp");
	}

}

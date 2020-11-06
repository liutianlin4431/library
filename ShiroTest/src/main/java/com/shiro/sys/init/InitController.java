package com.shiro.sys.init;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class InitController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String init() {
		return "login";
	}

	@RequestMapping(value = "templates/{action}")
	public String commonController(@PathVariable String action) {
		return action;
	}

	@RequestMapping(value = "templates/{action}/{action1}")
	public String commonController(@PathVariable String action, @PathVariable String action1) {
		return action + "/" + action1;
	}

	@RequestMapping(value = "templates/{action}/{action1}/{action2}")
	public String commonController(@PathVariable String action, @PathVariable String action1,
			@PathVariable String action2) {
		return action + "/" + action1 + "/" + action2;
	}
}

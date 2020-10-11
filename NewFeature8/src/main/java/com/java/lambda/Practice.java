package com.java.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class Practice {
	List<User> userS = Arrays.asList(new User("孟首作", 27, 177.0), new User("王勇", 21, 183.0), new User("张兴", 27, 175.0),
			new User("刘天林", 26, 165.0));

	// 方法1 lambda 表达式
	@Test
	public void test01() {
		List<User> uL = this.find(userS, (u) -> u.getAge() > 21);
//		for (User user : uL) {
//			System.out.println(user);
//		}
		uL.forEach(System.out::println);

	}

	public List<User> find(List<User> uL, Rules<User> r) {
		List<User> nL = new ArrayList<User>();
		for (User user : uL) {
			if (r.compare(user)) {
				nL.add(user);
			}
		}
		return nL;
	}

	// 方法2 stream API
	@Test
	public void test02() {
		// 不需要find方法支持
		userS.stream().filter((u) -> u.getHeight() > 165).forEach(System.out::println);
	}
}

package com.java.lambda.basis;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.junit.Test;

import com.java.lambda.User;

/*
 * 一、方法引用：若 Lambda 体中的功能，已经有方法提供了实现，可以使用方法引用
 * 			  （可以将方法引用理解为 Lambda 表达式的另外一种表现形式）
 * 
 * 1. 对象的引用 :: 实例方法名
 * 
 * 2. 类名 :: 静态方法名
 * 
 * 3. 类名 :: 实例方法名
 * 
 * 注意：
 * 	 ①方法引用所引用的方法的参数列表与返回值类型，需要与函数式接口中抽象方法的参数列表和返回值类型保持一致！
 * 	 ②若Lambda 的参数列表的第一个参数，是实例方法的调用者，第二个参数(或无参)是实例方法的参数时，格式： ClassName::MethodName
 * 
 * 二、构造器引用 :构造器的参数列表，需要与函数式接口中参数列表保持一致！
 * 
 * 1. 类名 :: new
 * 
 * 三、数组引用
 * 
 * 	类型[] :: new;
 * 
 * 
 */
@SuppressWarnings("unused")
public class TestMethodRef {
	// 数组引用
	@Test
	public void test8() {
		Function<Integer, String[]> fun = (args) -> new String[args];
		String[] strs = fun.apply(10);
		System.out.println(strs.length);

		System.out.println("--------------------------");

		Function<Integer, User[]> fun2 = User[]::new;
		User[] emps = fun2.apply(20);
		System.out.println(emps.length);
	}

	// 构造器引用
	@Test
	public void test7() {
		Function<String, User> fun = User::new;

		BiFunction<String, Integer, User> fun2 = User::new;
	}

	@Test
	public void test6() {
		Supplier<User> sup = () -> new User();
		System.out.println(sup.get());

		System.out.println("------------------------------------");

		Supplier<User> sup2 = User::new;
		System.out.println(sup2.get());
	}

	// 类名 :: 实例方法名
	@Test
	public void test5() {
		BiPredicate<String, String> bp = (x, y) -> x.equals(y);
		System.out.println(bp.test("abcde", "abcde"));

		System.out.println("-----------------------------------------");

		BiPredicate<String, String> bp2 = String::equals;
		System.out.println(bp2.test("abc", "abc"));

		System.out.println("-----------------------------------------");

		Function<User, String> fun = (e) -> e.show();
		System.out.println(fun.apply(new User()));

		System.out.println("-----------------------------------------");

		Function<User, String> fun2 = User::show;
		System.out.println(fun2.apply(new User()));

	}

	// 类名 :: 静态方法名
	@Test
	public void test4() {
		Comparator<Integer> com = (x, y) -> Integer.compare(x, y);

		System.out.println("-------------------------------------");

		Comparator<Integer> com2 = Integer::compare;
	}

	@Test
	public void test3() {
		BiFunction<Double, Double, Double> fun = (x, y) -> Math.max(x, y);
		System.out.println(fun.apply(1.5, 22.2));

		System.out.println("--------------------------------------------------");

		BiFunction<Double, Double, Double> fun2 = Math::max;
		System.out.println(fun2.apply(1.2, 1.5));
	}

	// 对象的引用 :: 实例方法名
	@Test
	public void test2() {
		User emp = new User("孟首作", 27, 177.0);

		Supplier<String> sup = () -> emp.getName();
		System.out.println(sup.get());

		System.out.println("----------------------------------");

		Supplier<String> sup2 = emp::getName;
		System.out.println(sup2.get());
	}

	@Test
	public void test1() {
		PrintStream ps = System.out;
		Consumer<String> con = (str) -> ps.println(str);
		con.accept("Hello World！");

		System.out.println("--------------------------------");

		Consumer<String> con2 = ps::println;
		con2.accept("Hello Java8！");

		Consumer<String> con3 = System.out::println;
	}

}

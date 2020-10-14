package com.java.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

/**
 * 基础操作
 * 
 * @author ltl
 *
 */
@SuppressWarnings("unused")
public class Basis {
	List<Student> sL = Arrays.asList(new Student(1, "张三", 11, Sex.girl), new Student(2, "李四", 25, Sex.male),
			new Student(3, "王五", 18, Sex.male), new Student(4, "赵六", 5, Sex.girl), new Student(5, "田七", 35, Sex.girl));

	/**
	 * 创建方式
	 * 
	 * @author ltl
	 */
	@Test
	public void test01() {
		// 1.Collection【集合接口】 提供两种方法
		// stream() 串行
		// parallelStream() 并行
		List<String> list = new ArrayList<>();
		Stream<String> stream = list.stream(); // 获取一个顺序流
		Stream<String> parallelStream = list.parallelStream(); // 获取一个并行流

		// 2. 通过 Arrays 中的 stream() 获取一个数组流
		Integer[] nums = new Integer[10];
		Stream<Integer> stream1 = Arrays.stream(nums);

		// 3. 通过 Stream 类中静态方法 of()
		Stream<Integer> stream2 = Stream.of(1, 2, 3, 4, 5, 6);

		// 4. 创建无限流
		// 迭代------0逐次增加2并取前10条
		Stream<Integer> stream3 = Stream.iterate(0, (x) -> x + 2).limit(10);
		stream3.forEach(System.out::println);

		// 生成
		Stream<Double> stream4 = Stream.generate(Math::random);
		stream4.forEach(System.out::println);
	}

	/**
	 * 中间操作-------- 筛选与切片
	 * 
	 * @author ltl
	 */
	@Test
	public void test02() {
		// filter 从流中排除某些数据
		// 输出学生年龄大于18岁的
		sL.stream().filter((s) -> s.getAge() > 18).forEach(System.out::println);
		// limit 截断流获取指定条数数据
		// 获取前两条
		sL.stream().limit(2).forEach(System.out::println);
		// skip 跳过流，跳过指定个数
		// 跳过前两条数据
		sL.stream().skip(2).forEach(System.out::println);
		// distinct 去重数据------通过流所生成元素的 hashCode() 和 equals() 去除重复元素
		sL.stream().distinct().forEach(System.out::println);
		// map 将元素转换成其他形式或提取元素中某个信息；该函数会被应用到每个元素上，并将其映射成一个新的元素。[{a},{b}]
		// 提取学生姓名
		// flatMap 接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流[a,b]
		sL.stream().map(Student::getName).forEach(System.out::println);
	}

	/**
	 * 终止操作--------匹配操作
	 * 
	 * @author ltl
	 */
	@Test
	public void test03() {
		// allMatch 检查所有元素中是否全部包括指定元素
		// 查看学生中是否存在年龄超过50的
		boolean boo = sL.stream().allMatch((s) -> s.getAge() > 50);
		System.out.println(boo);
		// anyMatch 查看所有元素中是否包含至少一次指定元素
		boo = sL.stream().anyMatch((s) -> s.getAge() > 18);
		System.out.println(boo);
		// noneMatch 检查是否没有匹配的元素
		boo = sL.stream().noneMatch((s) -> s.getAge().equals(18));
		System.out.println(boo);
	}

	/**
	 * 终止操作--------返回元素
	 * 
	 * @author ltl
	 */
	@Test
	public void test04() {
		// findFirst 获取流中的第一条数据
		// Optional 防止空指针错误 orElse方法：如值为null时使用代替值
		List<String> strL = new ArrayList<String>();
		strL.add("本身值");
		Optional<String> os = strL.stream().findFirst();
		System.out.println(os.orElse("代替值"));
		// findAny 返回流中任意值
		Optional<Student> oStu = sL.stream().findAny();
		System.out.println(oStu);
		// count 返回流中元素的总个数
		// 统计学生中学号大于2的个数
		sL.stream().filter((s) -> s.getId() > 2).count();
		// max 返回流中最大值
		// 获取年龄最大的学生
		sL.stream().max((s1, s2) -> Double.compare(s1.getAge(), s2.getAge()));
		// 获取最大的年龄
		sL.stream().map(Student::getAge).max(Double::compare);
		// min 返回流中最小值
		// 获取年龄最小的学生
		sL.stream().min((s1, s2) -> Double.compare(s1.getAge(), s2.getAge()));
		// 获取最小的年龄
		sL.stream().map(Student::getAge).min(Double::compare);
		// 查找学号等于1的学生信息
		List<Student> newSL = sL.stream().filter((s) -> s.getId().equals(1)).collect(Collectors.toList());
		System.out.println(newSL);
	}

	/**
	 * 终止操作--------归约
	 * 
	 * @author ltl
	 */
	@Test
	public void test05() {
		// reduce(T identity, BinaryOperator) / reduce(BinaryOperator)
		// ——可以将流中元素反复结合起来，得到一个值。
		// 查询学生年纪总和
		sL.stream().map(Student::getAge).reduce(Integer::sum);
	}
}

package com.java.date;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

import org.junit.Test;

public class DateTest {
	/**
	 * 时间戳
	 */
	@Test
	public void test01() {
		Instant instant = Instant.now();// 时间以UTC为准
		System.out.println(instant);

		OffsetDateTime odt = instant.atOffset(ZoneOffset.ofHours(8));// 增加8个小时时差，为中国时间
		System.out.println(odt);
		// 计算差值
		System.out.println("所耗费时间为：" + Duration.between(instant, Instant.now()).toMillis());
	}

	/**
	 * 本地时间 LocalDate、LocalTime、LocalDateTime
	 */
	@Test
	public void test02() {
		// 本地日期
		LocalDate ld1 = LocalDate.now();
		System.out.println(ld1);
		// 本地时间
		LocalTime lt1 = LocalTime.now();
		System.out.println(lt1);
		// 本地日期时间
		LocalDateTime ldt1 = LocalDateTime.now();
		System.out.println(ldt1);
		// 计算时间相差多久
		LocalDateTime ldt2 = LocalDateTime.of(2015, 12, 02, 15, 30, 20);
		System.out.println(Duration.between(ldt2, ldt1).toHours());
	}

	/**
	 * 时间校准器
	 */
	@Test
	public void test3() {
		LocalDateTime ldt = LocalDateTime.now();
		System.out.println(ldt);
		// 修正日期为10号
		LocalDateTime ldt2 = ldt.withDayOfMonth(10);
		System.out.println(ldt2);
		// 下一个礼拜日
		LocalDateTime ldt3 = ldt.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
		System.out.println(ldt3);
		// 自定义：下一个工作日
		LocalDateTime ldt5 = ldt.with((l) -> {
			LocalDateTime ldt4 = (LocalDateTime) l;
			DayOfWeek dow = ldt4.getDayOfWeek();
			if (dow.equals(DayOfWeek.FRIDAY)) {
				return ldt4.plusDays(3);
			} else if (dow.equals(DayOfWeek.SATURDAY)) {
				return ldt4.plusDays(2);
			} else {
				return ldt4.plusDays(1);
			}
		});
		System.out.println(ldt5);

	}

	/**
	 * 格式化日期
	 */
	@Test
	public void test4() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss E");
		LocalDateTime ldt = LocalDateTime.now();
		String strDate = ldt.format(dtf);
		System.out.println(strDate);
		// 将指定格式字符串转换成本地时间
		LocalDateTime newLdt = LocalDateTime.parse(strDate, dtf);
		System.out.println(newLdt);
	}

	/**
	 * ZonedDate、ZonedTime、ZonedDateTime ： 带时区的时间或日期
	 */
	@Test
	public void test5() {
		// 查看有哪些时区
//		Set<String> set = ZoneId.getAvailableZoneIds();
//		set.forEach(System.out::println);
		// 上海时区时间
		LocalDateTime ldt = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
		System.out.println(ldt);
	}

}

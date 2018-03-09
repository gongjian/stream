package org.james.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

import org.james.lambda.Student;

public class StreamOperation {

	public static void main(String[] args) {
		// testMap();
		//testFilter();
		//testFlatMap();
		//testSorted();
		//testSorted2();
		//testSorted3();
		testOthers();
	}

	/**
	 * map把一种类型的流转换为另外一种类型的流 将String数组中字母转换为大写
	 */
	public static void testMap() {
		String[] arr = { "yes", "YES", "no", "NO" };
		Arrays.stream(arr).map(x -> x.toLowerCase()).forEach(System.out::println);
		Arrays.stream(arr).map(String::toUpperCase).forEach(System.out::println);

		Arrays.stream(arr).map(x -> {
			System.out.println(x);
			return x.toUpperCase();
		}).forEach(System.out::println);

	}

	/**
	 * 过滤流，过滤流中的元素
	 */
	public static void testFilter() {
		Integer[] arr = new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		Arrays.stream(arr).filter(x -> x > 3 && x < 8).forEach(System.out::println);

		Arrays.stream(arr).filter(Calculator::cal).forEach(System.out::println);
	}

	/**
	 * 拆解流，将流中每一个元素拆解成一个流
	 */
	public static void testFlatMap() {
		String[] arr1 = {"a", "b", "c", "d"};
	    String[] arr2 = {"e", "f", "c", "d"};
	    String[] arr3 = {"h", "j", "c", "d"};
	    
	    //Stream.of(arr1, arr2, arr3).flatMap(x -> Arrays.stream(x)).forEach(System.out::println);
	    Stream.of(arr1, arr2, arr3).flatMap(Arrays::stream).forEach(System.out::println);
	}
	
	/**
	 * 对流进行排序
	 */
	public static void testSorted() {	
		String[] arr1 = {"abc","a","bc","abcd"};
		
		Arrays.stream(arr1).sorted((x, y) -> x.compareTo(y)).forEach(System.out::println);
		Arrays.stream(arr1).sorted(String::compareTo).forEach(System.out::println);
		Arrays.stream(arr1).sorted(Comparator.naturalOrder()).forEach(System.out::println);
		Arrays.stream(arr1).sorted(Comparator.comparing(String::length)).forEach(System.out::println);
	}
	
	/**
	 * 倒序
	 * reversed(),java8泛型推导的问题，所以如果comparing里面是非方法引用的lambda表达式就没办法直接使用reversed()
	 * Comparator.reverseOrder():也是用于翻转顺序，用于比较对象（Stream里面的类型必须是可比较的）
	 * Comparator. naturalOrder()：返回一个自然排序比较器，用于比较对象（Stream里面的类型必须是可比较的）
	 */
	
	public static void testSorted2(){
		String[] arr1 = {"abc","a","bc","abcd"};
	    Arrays.stream(arr1).sorted(Comparator.comparing(String::length).reversed()).forEach(System.out::println);
	    Arrays.stream(arr1).sorted(Comparator.reverseOrder()).forEach(System.out::println);
	    Arrays.stream(arr1).sorted(Comparator.naturalOrder()).forEach(System.out::println);
	}
	
	/**
	 * thenComparing
	 * 先按照首字母排序
	 * 之后按照分数排序
	 */
	public static void testSorted3(){
		Student[] arr1 = {new Student("a", 19d), new Student("a", 13d), new Student("a", 6d), new Student("b", 88d), new Student("b", 66d)};
	    Arrays.stream(arr1).sorted(Comparator.comparing(Student::getName).thenComparing(Student::getScore)).forEach(System.out::println);
	}
	
	
	/**
	 * 提取流和组合流 
	 */
	public static void testOthers() {
		//limit，限制从流中获得前n个数据
		Stream.iterate(1, x -> x+1).limit(10).forEach(System.out::println);
		
		//skip，跳过前n个数据
		Stream.iterate(1, x -> x + 1).skip(3).limit(5).forEach(System.out::println);
		
		//
	}

}

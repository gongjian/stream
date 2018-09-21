package org.james.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.james.lambda.Student;

public class StreamOperation {

  public static void main(String[] args) {
    // testMap();
    // testFilter();
    // testFlatMap();
    // testSorted();
    // testSorted2();
    // testSorted3();
    // testOthers1();
    // testOthers2();
    // testOptional();
    testResult();
    // testGroupAndPiece();
    // testPeek();
    // testPeekPal();
  }

  /**
   * map把一种类型的流转换为另外一种类型的流 将String数组中字母转换为大写
   */
  public static void testMap() {
    String[] arr = {"yes", "YES", "no", "NO"};
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
    Integer[] arr = new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
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

    // Stream.of(arr1, arr2, arr3).flatMap(x ->
    // Arrays.stream(x)).forEach(System.out::println);
    Stream.of(arr1, arr2, arr3).flatMap(Arrays::stream).forEach(System.out::println);
  }

  /**
   * 对流进行排序
   */
  public static void testSorted() {
    String[] arr1 = {"abc", "a", "bc", "abcd"};

    Arrays.stream(arr1).sorted((x, y) -> x.compareTo(y)).forEach(System.out::println);
    Arrays.stream(arr1).sorted(String::compareTo).forEach(System.out::println);
    Arrays.stream(arr1).sorted(Comparator.naturalOrder()).forEach(System.out::println);
    Arrays.stream(arr1).sorted(Comparator.comparing(String::length)).forEach(System.out::println);
  }

  /**
   * 倒序 reversed(),java8泛型推导的问题，所以如果comparing里面是非方法引用的lambda表达式就没办法直接使用reversed()
   * Comparator.reverseOrder():也是用于翻转顺序，用于比较对象（Stream里面的类型必须是可比较的） Comparator.
   * naturalOrder()：返回一个自然排序比较器，用于比较对象（Stream里面的类型必须是可比较的）
   */

  public static void testSorted2() {
    String[] arr1 = {"abc", "a", "bc", "abcd"};
    Arrays.stream(arr1).sorted(Comparator.comparing(String::length).reversed())
        .forEach(System.out::println);
    Arrays.stream(arr1).sorted(Comparator.reverseOrder()).forEach(System.out::println);
    Arrays.stream(arr1).sorted(Comparator.naturalOrder()).forEach(System.out::println);
  }

  /**
   * thenComparing 先按照首字母排序 之后按照分数排序
   */
  public static void testSorted3() {
    Student[] arr1 = {new Student("a", 19d), new Student("a", 13d), new Student("a", 6d),
        new Student("b", 88d), new Student("b", 66d)};
    Arrays.stream(arr1)
        .sorted(Comparator.comparing(Student::getName).thenComparing(Student::getScore))
        .forEach(System.out::println);
  }

  /**
   * 提取流和组合流
   */
  public static void testOthers1() {
    // limit，限制从流中获得前n个数据
    Stream.iterate(1, x -> x + 1).limit(10).forEach(System.out::println);

    // skip，跳过前n个数据
    Stream.iterate(1, x -> x + 1).skip(3).limit(5).forEach(System.out::println);

    // 可以把两个stream合并成一个stream（合并的stream类型必须相同）, 只能两两合并
    String[] arr1 = new String[] {"a", "b", "c", "d"};
    String[] arr2 = new String[] {"d", "e", "f", "g"};

    Stream<String> stream1 = Arrays.stream(arr1);
    Stream<String> stream2 = Arrays.stream(arr2);
    Stream.concat(stream1, stream2).distinct().forEach(System.out::println);
  }

  /**
   * 聚合操作
   */
  public static void testOthers2() {
    String[] arr = new String[] {"b", "ab", "abc", "abcd", "abcde"};

    // 最大最小值
    Stream.of(arr).max(Comparator.comparing(String::length)).ifPresent(System.out::println);
    Stream.of(arr).min(Comparator.comparing(String::length)).ifPresent(System.out::println);

    // 计算数量
    System.out.println(Stream.of(arr).count());

    // 查找第一个
    String first = Stream.of(arr).parallel().filter(x -> x.length() > 3).findFirst().orElse("null");
    System.out.println(first);

    /**
     * findAny 找到所有匹配的元素 对并行流十分有效 只要在任何片段发现了第一个匹配元素就会结束整个运算
     */
    Optional<String> optional = Stream.of(arr).parallel().filter(x -> x.length() > 3).findAny();
    optional.ifPresent(System.out::println);

    /**
     * anyMatch 是否含有匹配元素
     */
    Boolean aBoolean = Stream.of(arr).anyMatch(x -> x.startsWith("a"));
    System.out.println(aBoolean);

    // reduce 操作可以实现从Stream中生成一个值，其生成的值不是随意的，而是根据指定的计算模型
    Optional<Integer> op = Stream.of(1, 2, 3).filter(x -> x > 1).reduce((x, y) -> x + y);
    System.out.println(op.get());
  }

  public static void testOptional() {
    List<String> list = new ArrayList<>();
    list.add("user1");
    list.add("user2");
    Optional<String> opt = Optional.of("user3");
    // Optional<String> opt = Optional.ofNullable(null);
    opt.ifPresent(list::add);
    list.forEach(System.out::println);
  }

  /**
   * 收集结果
   */
  public static void testResult() {
    Student[] students = new Student[100];
    for (int i = 0; i < 30; i++) {
      Student student = new Student("user", i);
      students[i] = student;
    }
    for (int i = 30; i < 60; i++) {
      Student student = new Student("user" + i, i);
      students[i] = student;
    }
    for (int i = 60; i < 100; i++) {
      Student student = new Student("user" + i, i);
      students[i] = student;
    }

    // 生成List
    List<Student> list = Arrays.stream(students).collect(Collectors.toList());
    List<String> list1 = Arrays.stream(students).map(x -> x.getName()).collect(Collectors.toList());
    list.forEach(System.out::println);
    list1.forEach(System.out::println);

    // 生成Set
    Set<Student> set = Arrays.stream(students).collect(Collectors.toSet());
    set.forEach(System.out::println);

    Arrays.stream(students).map(x -> x.getName()).collect(Collectors.toList());

    // 生成Map
    // 如果包含相同的key，则需要提供第三个参数，否则报错
    Map<String, Double> map = Arrays.stream(students)
        .collect(Collectors.toMap(Student::getName, Student::getScore, (s, a) -> s + a));
    map.forEach((x, y) -> System.out.println(x + "->" + y));

    // 生成数组
    Student[] s = Arrays.stream(students).toArray(Student[]::new);
    for (int i = 0; i < s.length; i++) {
      System.out.println(s[i]);
    }

    // 指定生成的类型
    HashSet<Student> ss = Arrays.stream(students).collect(Collectors.toCollection(HashSet::new));
    ss.forEach(System.out::println);

    // 统计
    DoubleSummaryStatistics summaryStatistics =
        Arrays.stream(students).collect(Collectors.summarizingDouble(Student::getScore));
    System.out.println("getAverage->" + summaryStatistics.getAverage());
    System.out.println("getMax->" + summaryStatistics.getMax());
    System.out.println("getMin->" + summaryStatistics.getMin());
    System.out.println("getCount->" + summaryStatistics.getCount());
    System.out.println("getSum->" + summaryStatistics.getSum());
  }

  /**
   * 分组和分片的意义是，将collect的结果集展示位Map<key,val>的形式
   */
  public static void testGroupAndPiece() {
    Student[] students = new Student[100];

    for (int i = 0; i < 30; i++) {
      Student student = new Student("user1", i);
      students[i] = student;
    }
    for (int i = 30; i < 60; i++) {
      Student student = new Student("user2", i);
      students[i] = student;
    }
    for (int i = 60; i < 100; i++) {
      Student student = new Student("user3", i);
      students[i] = student;
    }

    Map<String, List<Student>> map =
        Arrays.stream(students).collect(Collectors.groupingBy(Student::getName));
    map.forEach((x, y) -> System.out.println(x + "->" + y));

    // 如果只有两类，使用partitioningBy会比groupingBy更有效率
    Map<Boolean, List<Student>> map1 =
        Arrays.stream(students).collect(Collectors.partitioningBy(x -> x.getScore() > 50));
    map1.forEach((x, y) -> System.out.println(x + "->" + y));

    // downstream指定类型
    Map<String, Set<Student>> map2 = Arrays.stream(students)
        .collect(Collectors.groupingBy(Student::getName, Collectors.toSet()));
    map2.forEach((x, y) -> System.out.println(x + "->" + y));

    // downstream 聚合操作
    /**
     * counting
     */
    Map<String, Long> map11 = Arrays.stream(students)
        .collect(Collectors.groupingBy(Student::getName, Collectors.counting()));
    map11.forEach((x, y) -> System.out.println(x + "->" + y));
    /**
     * summingInt
     */
    System.out.println("-----------------");
    Map<String, Double> map12 = Arrays.stream(students).collect(
        Collectors.groupingBy(Student::getName, Collectors.summingDouble(Student::getScore)));
    map12.forEach((x, y) -> System.out.println(x + "->" + y));
    /**
     * maxBy
     */
    System.out.println("=================");
    Map<String, Optional<Student>> map13 = Arrays.stream(students).collect(Collectors
        .groupingBy(Student::getName, Collectors.maxBy(Comparator.comparing(Student::getScore))));
    map13.forEach((x, y) -> System.out.println(x + "->" + y));
    /**
     * mapping
     */
    Map<String, Set<Double>> map14 = Arrays.stream(students).collect(Collectors
        .groupingBy(Student::getName, Collectors.mapping(Student::getScore, Collectors.toSet())));
    map14.forEach((x, y) -> System.out.println(x + "->" + y));


  }

  /**
   * peek，监控方法 串行流和并行流的执行顺序
   */
  public static void testPeek() {
    Stream<Integer> stream = Stream.iterate(1, x -> x + 1).limit(10);
    stream.peek(StreamOperation::peek1).filter(x -> x > 5).peek(StreamOperation::peek2)
        .filter(x -> x < 8).peek(StreamOperation::peek3).forEach(System.out::println);
  }

  public static void testPeekPal() {
    Stream<Integer> stream = Stream.iterate(1, x -> x + 1).limit(10).parallel();
    stream.peek(StreamOperation::peek1).filter(x -> x > 5).peek(StreamOperation::peek2)
        .filter(x -> x < 8).peek(StreamOperation::peek3).forEach(System.out::println);
  }

  public static void peek1(int x) {
    System.out.println(Thread.currentThread().getName() + ":->peek1->" + x);
  }

  public static void peek2(int x) {
    System.out.println(Thread.currentThread().getName() + ":->peek2->" + x);
  }

  public static void peek3(int x) {
    System.out.println(Thread.currentThread().getName() + ":->final result->" + x);
  }

}

package org.james.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.UnaryOperator;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.james.lambda.Student;

public class StreamCreation {

  public static void main(String[] args) {

    // 1. 通过数组创建流
    int[] arr = {1, 2, 3, 4, 5};
    IntStream stream1 = Arrays.stream(arr);
    Student[] studentArr = {new Student("1", 11d), new Student("2", 22d)};
    Stream<Student> stream2 = Arrays.stream(studentArr);

    Stream<Integer> stream3 = Stream.of(1, 2, 3, 4, 5);

    stream1.forEach(System.out::println);
    stream2.forEach(System.out::println);
    stream3.forEach(System.out::println);

    // 2. 通过集合创建流
    List<String> strs = Arrays.asList("a1", "a2", "a3");
    Stream<String> stream4 = strs.stream();
    Stream<String> stream5 = strs.parallelStream();

    stream4.forEach(System.out::println);
    stream5.forEach(System.out::println);

    // 3. 创建空的流
    Stream<Integer> stream = Stream.empty();
    stream.forEach(System.out::println);

    // 4. 创建无限流
    Stream.generate(() -> "number" + new Random().nextInt()).limit(100)
        .forEach(System.out::println);
    Stream.generate(() -> new Student("aaa", 55d)).limit(20).forEach(System.out::println);

    // 5. 创建规律的无限流
    Stream.iterate(0, x -> x + 1).limit(10).forEach(System.out::println);
    Stream.iterate(0, x -> x).limit(10).forEach(System.out::println);
    Stream.iterate(0, UnaryOperator.identity()).limit(10).forEach(System.out::println);
  }

}

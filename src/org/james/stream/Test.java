package org.james.stream;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.james.lambda.Student;

public class Test {

	public static void main(String[] args) {
		List<Student> studentList = new ArrayList<>();

		Random random = new Random();
		for (int i = 0; i < 100; i++) {
			studentList.add(new Student("student" + i, random.nextDouble()*100));
		}

		List<String> list = studentList.stream()
				.filter(x -> x.getScore() > 60)
				.sorted(Comparator.comparing(Student::getScore).reversed())
				.map(Student::getName)
				.collect(Collectors.toList());
		
		System.out.println(list);

	}
}

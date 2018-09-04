package org.james.lambda.functioninterface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.james.lambda.Student;

public class InnerNormalTest {

  public static void main(String[] args) {
    List<Student> studentList = new ArrayList<>();
    studentList.add(new Student("stu1", 100.0));
    studentList.add(new Student("stu2", 97.0));
    studentList.add(new Student("stu3", 96.0));
    studentList.add(new Student("stu4", 95.0));

    Collections.sort(studentList, new Comparator<Student>() {

      @Override
      public int compare(Student s1, Student s2) {
        return s1.getScore().compareTo(s2.getScore());
      }
    });

    System.out.println(studentList);
  }

}

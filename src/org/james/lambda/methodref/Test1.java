package org.james.lambda.methodref;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Test1 {

  public static void main(String[] args) {
    List<String> strList = new ArrayList<>();
    strList.add("stu3");
    strList.add("stu2");
    strList.add("stu3");
    strList.add("stu4");

    Collections.sort(strList, String::compareToIgnoreCase);

    strList.forEach(System.out::println);



  }

}

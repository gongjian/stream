package org.james.lambda.functioninterface;

public class ThreadLambdaTest {

  public static void main(String[] args) {

    new Thread(() -> System.out.println("321")).start();
  }

}

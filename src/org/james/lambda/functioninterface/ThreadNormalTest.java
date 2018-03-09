package org.james.lambda.functioninterface;

public class ThreadNormalTest {

	public static void main(String[] args) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("123");
				
			}
		}).start();

	}

}

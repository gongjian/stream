package org.james.lambda.functioninterface;

import java.util.function.DoubleToIntFunction;

public class MyFunctionalInterfaceTest {

	public static void main(String[] args) {
		testMyFI(x -> System.out.println(x));

		System.out.println(testDoubleToInt(2D, x -> (int) x + 1));

	}

	public static void testMyFI(MyFunctionalInterface functionalInterface) {
		functionalInterface.single("msg");
	}

	public static int testDoubleToInt(Double d, DoubleToIntFunction doubleToIntFunction) {
		return doubleToIntFunction.applyAsInt(d);
	}

}

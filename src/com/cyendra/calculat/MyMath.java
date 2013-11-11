package com.cyendra.calculat;

import java.math.BigDecimal;

public class MyMath {
	
	public static final int DEFAULT_SCALE = 20;
	
	private static BigDecimal getBigDecimal(double number) {
		return new BigDecimal(number);
	}
	
	static double add(double num1,double num2){
		BigDecimal a=getBigDecimal(num1);
		BigDecimal b=getBigDecimal(num2);
		BigDecimal c=a.add(b);
		return c.doubleValue();
	}
	
	static double subtract(double num1,double num2){
		BigDecimal a=getBigDecimal(num1);
		BigDecimal b=getBigDecimal(num2);
		BigDecimal c=a.subtract(b);
		return c.doubleValue();
	}
	
	static double multiply(double num1,double num2){
		BigDecimal a=getBigDecimal(num1);
		BigDecimal b=getBigDecimal(num2);
		BigDecimal c=a.multiply(b);
		return c.doubleValue();
	}
	
	static double divide(double num1,double num2){
		BigDecimal a=getBigDecimal(num1);
		BigDecimal b=getBigDecimal(num2);
		BigDecimal c=a.divide(b,DEFAULT_SCALE,BigDecimal.ROUND_HALF_UP);
		return c.doubleValue();
	}
	
}

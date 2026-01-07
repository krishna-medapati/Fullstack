package com.klu.demo;
import org.springframework.context.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class Mainapp {
	public static void main(String[] args) {
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		Car car=(Car)context.getBean("car");
		car.drive();
		
		Bike bike = (Bike)context.getBean("bike");
		bike.ride();
	}
}
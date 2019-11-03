 package com.tem.mavenDemo;

import static org.junit.Assert.*;

import org.junit.Test;

public class AddTest {

	@Test
	public void testAdd() {
		//fail("Not yet implemented");
		JunitClass junit=new JunitClass();
		int result=junit.add(300, 200);
		assertEquals(500,result);		
	}
/*
	@Test
	public void testMul() {
//		fail("Not yet implemented");
		JunitClass junit=new JunitClass();
		int result=junit.mul(3, 2);
		assertEquals(6,result);		
	
	}
*/
}

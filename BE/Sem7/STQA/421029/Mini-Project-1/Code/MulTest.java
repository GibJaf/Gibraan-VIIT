package com.tem.mavenDemo;

import static org.junit.Assert.*;

import org.junit.Test;

public class MulTest {

	@Test
	public void testMul() {
//		fail("Not yet implemented");
		JunitClass junit=new JunitClass();
		int result=junit.mul(3, 2);
		assertEquals(6,result);		
	
	}

}

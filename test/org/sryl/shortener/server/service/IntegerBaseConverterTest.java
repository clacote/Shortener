/**
 * 
 */
package org.sryl.shortener.server.service;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Cyril Lacote <cyril.lacote@gmail.com>
 */
public class IntegerBaseConverterTest {

	/**
	 * Test method for {@link org.sryl.shortener.server.service.ShortenerServiceImpl#shorten(java.lang.String)}.
	 */
	@Test
	public void testMaxValue() {
		assertTrue(Integer.MAX_VALUE < Math.pow(IntegerBaseConverter.MAX_RADIX, ShortenerServiceImpl.MAX_SHORTENED_LENGTH));
	}

	/**
	 * Test method for {@link org.sryl.shortener.server.service.ShortenerServiceImpl#shorten(java.lang.String)}.
	 */
	@Test
	public void testBinary() {
		final int value = 764565345;
		assertEquals(Integer.toBinaryString(value), IntegerBaseConverter.toString(value, 2));
	}

	/**
	 * Test method for {@link org.sryl.shortener.server.service.ShortenerServiceImpl#shorten(java.lang.String)}.
	 */
	@Test
	public void testOctal() {
		final int value = 542763754;
		assertEquals(Integer.toOctalString(value), IntegerBaseConverter.toString(value, 8));
	}

	/**
	 * Test method for {@link org.sryl.shortener.server.service.ShortenerServiceImpl#shorten(java.lang.String)}.
	 */
	@Test
	public void testHexadecimal() {
		final int value = 542763754;
		assertEquals(Integer.toHexString(value), IntegerBaseConverter.toString(value, 16));
	}

	/**
	 * Test method for {@link org.sryl.shortener.server.service.ShortenerServiceImpl#shorten(java.lang.String)}.
	 */
	@Test
	public void testMax() {
		final int value = 563652432;
		assertNotNull(IntegerBaseConverter.toString(value, IntegerBaseConverter.MAX_RADIX));
	}
}

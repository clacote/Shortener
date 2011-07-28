/**
 * 
 */
package org.sryl.shortener.server.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * @author Cyril Lacote <cyril.lacote@gmail.com>
 */
// FIXME To be Guicized
public class ShorternerServiceImplTest {

	private ShortenerService shortenerService = new ShortenerServiceImpl();

	/**
	 * Test method for {@link org.sryl.shortener.server.service.ShortenerServiceImpl#shorten(java.lang.String)}.
	 */
	@Test
	public void testShortenNull() {
		assertNull(shortenerService.shorten(null));
	}

	/**
	 * Test method for {@link org.sryl.shortener.server.service.ShortenerServiceImpl#shorten(java.lang.String)}.
	 */
	@Test
	public void testShortenEmpty() {
		assertEquals("", shortenerService.shorten(""));
	}

	/**
	 * Test method for {@link org.sryl.shortener.server.service.ShortenerServiceImpl#shorten(java.lang.String)}.
	 */
	@Test
	public void testShortenShort() {
		final String shortData = "abcd";
		assertEquals(shortData, shortenerService.shorten(shortData));
	}

	/**
	 * Test method for {@link org.sryl.shortener.server.service.ShortenerServiceImpl#shorten(java.lang.String)}.
	 */
	@Test
	public void testShortenOK() {
		assertShortened(shortenerService.shorten("abcdefghijklmnopkrstuvwxyz"));
	}

	/**
	 * Test method for {@link org.sryl.shortener.server.service.ShortenerServiceImpl#shorten(java.lang.String)}.
	 */
	@Test
	public void testShortenUrl() {
		assertShortened(shortenerService.shorten("https://plus.google.com/?tab=yX"));
	}

	private void assertShortened(final String key) {
		System.out.println(key);
		assertNotNull(key);
		assertTrue(key.length() > 0);
		assertTrue(key.length() <= ShortenerServiceImpl.MAX_SHORTENED_LENGTH);
	}
}

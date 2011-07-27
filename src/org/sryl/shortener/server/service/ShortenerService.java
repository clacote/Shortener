package org.sryl.shortener.server.service;


public interface ShortenerService {

	/**
	 * Shorten a given data (i.e., an URL) to a shortened String key.
	 * @param data Data to be shortened
	 * @return Shortened key
	 */
	String shorten(String data);
	
	/**
	 * Retrieve data corresponding to give key
	 * @param key 
	 * @return Corresponding data, or null if unknown key
	 */
	String retrieve(String key);
}

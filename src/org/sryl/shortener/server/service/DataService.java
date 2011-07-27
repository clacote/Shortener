package org.sryl.shortener.server.service;

/**
 * Persistence service for shortened data
 * @author Cyril Lacote <cyril.lacote@gmail.com>
 */
public interface DataService {

	String get(String key);
	void put(String key, String value);
}

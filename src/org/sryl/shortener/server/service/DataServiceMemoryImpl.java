/**
 * 
 */
package org.sryl.shortener.server.service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.inject.Singleton;

/**
 * @author Cyril Lacote <cyril.lacote@gmail.com>
 */
@Singleton
public class DataServiceMemoryImpl implements DataService {

	ConcurrentMap<String, String> map = new ConcurrentHashMap<String, String>();
	
	/**
	 * @see org.sryl.shortener.server.service.DataService#get(java.lang.String)
	 */
	@Override
	public String get(String key) {
		return map.get(key);
	}

	/**
	 * @see org.sryl.shortener.server.service.DataService#put(java.lang.String, java.lang.String)
	 */
	@Override
	public void put(String key, String value) {
		map.putIfAbsent(key, value);
	}

}

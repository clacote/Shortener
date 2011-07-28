package org.sryl.shortener.server.service;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Cyril Lacote <cyril.lacote@gmail.com>
 */
@Singleton
public class ShortenerServiceImpl implements ShortenerService {

	/** Max length of a shortened data key */
	public static final int MAX_SHORTENED_LENGTH = 6;

	@Inject
	private DataService dataService;
	
	/**
	 * @see org.sryl.shortener.server.service.ShortenerService#shorten(java.net.URL)
	 */
	@Override
	public String shorten(String data) {
		String result = null;

		if (data != null) {
			if (data.length() > MAX_SHORTENED_LENGTH) {
				final int hash = data.hashCode();
				result = IntegerBaseConverter.toString(hash, IntegerBaseConverter.MAX_RADIX);
				
			} else {
				result = data;
			}
			
			// Storing pair (Shortened key, data)
			dataService.put(result, data);
		}

		return result;
	}

	@Override
	public String retrieve(String key) {
		return dataService.get(key);
	}

}

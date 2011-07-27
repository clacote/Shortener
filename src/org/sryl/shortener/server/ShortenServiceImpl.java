package org.sryl.shortener.server;

import org.sryl.shortener.client.ShortenService;
import org.sryl.shortener.server.service.ShortenerService;
import org.sryl.shortener.server.service.ShortenerServiceImpl;
import org.sryl.shortener.shared.FieldVerifier;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class ShortenServiceImpl extends RemoteServiceServlet implements
		ShortenService {

	// FIXME Inject
	private ShortenerService shortenerService = new ShortenerServiceImpl();
	
	public String shorten(String url) throws IllegalArgumentException {
		// Verify that the input is valid. 
		if (!FieldVerifier.isValidUrl(url)) {
			// If the input is not valid, throw an IllegalArgumentException back to
			// the client.
			throw new IllegalArgumentException(
					"Not a valid URL");
		}

		return shortenerService.shorten(url);
	}

	@Override
	public String retrieve(String key) throws IllegalArgumentException {

		return shortenerService.retrieve(key);
	}
}

package org.sryl.shortener.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("shorten")
public interface ShortenService extends RemoteService {
	String shorten(String url) throws IllegalArgumentException;
	String retrieve(String key) throws IllegalArgumentException;
}

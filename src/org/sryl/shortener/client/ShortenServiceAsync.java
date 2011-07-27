package org.sryl.shortener.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>ShortenService</code>.
 */
public interface ShortenServiceAsync {
	void shorten(String data, AsyncCallback<String> callback) throws IllegalArgumentException;
	void retrieve(String key, AsyncCallback<String> callback) throws IllegalArgumentException;
}

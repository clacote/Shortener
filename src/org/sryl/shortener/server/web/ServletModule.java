package org.sryl.shortener.server.web;

import org.sryl.shortener.server.ShortenServiceImpl;

public class ServletModule extends com.google.inject.servlet.ServletModule {
	@Override
	protected void configureServlets() {
		serve("/shortener/shorten").with(ShortenServiceImpl.class);
	}
}

package org.sryl.shortener.server.service;

import com.google.inject.AbstractModule;

public class ServiceModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(DataService.class).to(DataServiceMemoryImpl.class);
		bind(ShortenerService.class).to(ShortenerServiceImpl.class);
	}
}

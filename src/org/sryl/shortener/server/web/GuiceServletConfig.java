package org.sryl.shortener.server.web;

import org.sryl.shortener.server.service.ServiceModule;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class GuiceServletConfig extends
		com.google.inject.servlet.GuiceServletContextListener {

	@Override
	protected Injector getInjector() {
		return Guice.createInjector(
				new ServletModule(),
				new ServiceModule());
	}

}

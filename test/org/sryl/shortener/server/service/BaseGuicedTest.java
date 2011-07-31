package org.sryl.shortener.server.service;

import org.junit.Before;

import com.google.inject.Guice;

/**
 * Base class for JUnit tests class with Guice injection.
 * Inject all dependencies in setUp() phase.
 * @author Cyril Lacote <cyril.lacote@gmail.com>
 */
public abstract class BaseGuicedTest {

	@Before
	public void setUp() {
		Guice.createInjector(new ServiceModule()).injectMembers(this);
	}
}
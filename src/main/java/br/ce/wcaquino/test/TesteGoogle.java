package br.ce.wcaquino.test;
import org.junit.Test;

import br.ce.wcaquino.core.DriverFactory;

public class TesteGoogle {
		
	@Test
	public void testes() {
        DriverFactory.getDriver().get("http://www.google.com");
	}
}

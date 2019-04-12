package br.ce.wcaquino.test;
import static br.ce.wcaquino.core.DriverFactory.getDriver;
import static br.ce.wcaquino.core.DriverFactory.killDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import br.ce.wcaquino.core.DSL;
import br.ce.wcaquino.core.DriverFactory;

public class TestePrine {
	
	private DSL dsl;
	
	@Before
	public void inicializa() {
		DriverFactory.getDriver();
		dsl = new DSL();
	}
	
	@After
	public void finalizar() {
		killDriver();
	}
	
//	@Test
	public void deveInteragirComRadioPrime() {
		getDriver().get("https://www.primefaces.org/showcase/ui/input/oneRadio.xhtml");
		dsl.clicarRadio(By.xpath("//*[@id=\"j_idt698:console\"]/tbody/tr/td[1]/label"));
		Assert.assertTrue(dsl.isRadioMarcado("j_idt698:console:0"));
		dsl.clicarRadio(By.xpath("//*[@id=\"j_idt698:console\"]/tbody/tr/td[2]/label"));
		Assert.assertTrue(dsl.isRadioMarcado("j_idt698:console:1"));
	}
	
	@Test
	public void deveInteragirComSelectPrime() {
		getDriver().get("https://www.primefaces.org/showcase/ui/input/oneMenu.xhtml");
		dsl.selecionarComboPrime("j_idt698:console", "PS4");
		Assert.assertEquals("PS4", dsl.obterTexto("j_idt698:console"));
	}
	

}

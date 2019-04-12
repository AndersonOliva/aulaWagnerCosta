package br.ce.wcaquino.test;
import static br.ce.wcaquino.core.DriverFactory.getDriver;
import static br.ce.wcaquino.core.DriverFactory.killDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.ce.wcaquino.core.DSL;

public class TesteAjax {
	
	private WebDriver driver;
	private DSL dsl;
	
	@Before
	public void inicializa() {
		getDriver();
		dsl = new DSL();
	}
	
	@After
	public void finalizar() {
		killDriver();
	}
	
	@Test
	public void testAjax() {
		getDriver().get("https://www.primefaces.org/showcase/ui/ajax/basic.xhtml");
		dsl.escreve("j_idt697:name", "Teste");
		dsl.clicarBotao("j_idt697:j_idt700");
		WebDriverWait wait =  new WebDriverWait(driver, 30);
//		wait.until(ExpectedConditions.textToBe(By.id("j_idt697:display"), "Teste"));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("j_idt712_start")));
		Assert.assertEquals("Teste", dsl.obterTexto("j_idt697:display"));
	}

}

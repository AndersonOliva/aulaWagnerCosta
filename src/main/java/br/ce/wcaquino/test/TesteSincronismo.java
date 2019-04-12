package br.ce.wcaquino.test;
import static br.ce.wcaquino.core.DriverFactory.getDriver;
import static br.ce.wcaquino.core.DriverFactory.killDriver;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.ce.wcaquino.core.DSL;

public class TesteSincronismo {
	
	private DSL dsl;
	
	@Before
	public void inicializa() {
		getDriver().get("file://C:/Users/aoliva/Desktop/AUTOMA%C3%87%C3%83O/Cursos/componentes.html");
		dsl = new DSL();
	}
	
	@After
	public void finalizar() {
		killDriver();
	}
	
	@Test
	public void deveUltilizarEsperaFixa() throws InterruptedException {
		dsl.clicarBotao("buttonDelay");
		Thread.sleep(5000);
		dsl.escreve("novoCampo", "Deu certo?");
	}
	
	@Test
	public void deveUtilizaEsperaImplicita() {
		getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		dsl.clicarBotao("buttonDelay");
		dsl.escreve("novoCampo", "Deu certo?");
		getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	}
	
	@Test
	public void deveUtilizaEsperaExplicita() {
		dsl.clicarBotao("buttonDelay");
		//Deve esperar até o ID do campo aparece na tela
		WebDriverWait wait = new WebDriverWait(getDriver(), 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("novoCampo")));
		dsl.escreve("novoCampo", "Deu certo?");
	}
	
}

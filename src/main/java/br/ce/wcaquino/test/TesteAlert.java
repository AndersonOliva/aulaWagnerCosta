package br.ce.wcaquino.test;
import static br.ce.wcaquino.core.DriverFactory.getDriver;
import static br.ce.wcaquino.core.DriverFactory.killDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.ce.wcaquino.core.DSL;

public class TesteAlert {
	
	private DSL dsl;
	
	@Before
	public void inicializa() {
		getDriver().get("file://C:/Users/aoliva/Desktop/AUTOMA%C3%87%C3%83O/Cursos/componentes.html");
	}
	
	@After
	public void finalizar() {
		killDriver();
	}
	
	@Test
	public void deveInteragirComAlertSimples() {
		dsl.clicarBotao("alert");
		String texto = dsl.alertaObterTextoEAceitar();
		
		Assert.assertEquals("Alert Simples", texto);
		dsl.escreve("elementosForm:nome", texto);
	}
	
	@Test
	public void deveInteragirComAlertConfirm() {
		dsl.clicarBotao("confirm");
		Assert.assertEquals("Confirm Simples", dsl.alertaObterTextoEAceitar());
		Assert.assertEquals("Confirmado", dsl.alertaObterTextoEAceitar());
		
		dsl.clicarBotao("confirm");
		Assert.assertEquals("Confirm Simples", dsl.alertaObterTextoENega());
		Assert.assertEquals("Negado", dsl.alertaObterTextoENega());

		
	}
	
	@Test
	public void deveInteragirComAlertPrompt() {
		dsl.clicarBotao("prompt");
		Assert.assertEquals("Digite um numero", dsl.alertaObterTexto());
		dsl.alertaEscreve("12");
		Assert.assertEquals("Era 12?", dsl.alertaObterTextoEAceitar());
		Assert.assertEquals(":D", dsl.alertaObterTextoEAceitar());;
	}
}

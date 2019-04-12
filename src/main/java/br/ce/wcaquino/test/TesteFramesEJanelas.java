package br.ce.wcaquino.test;
import static br.ce.wcaquino.core.DriverFactory.getDriver;
import static br.ce.wcaquino.core.DriverFactory.killDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import br.ce.wcaquino.core.DSL;

public class TesteFramesEJanelas {
	
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
	public void deveInteragirComFrames() {
		dsl.entrarFrame("frame1");
		dsl.clicarBotao("frameButton");
		String msg = dsl.alertaObterTextoEAceitar();
		Assert.assertEquals("Frame OK!", msg);	
		
		dsl.sairFrame();
		dsl.escreve("elementosForm:nome", msg);
	}
	
	@Test
	public void deveInteragirComFrameEscondido() {
		WebElement frame = getDriver().findElement(By.id("frame2"));
		dsl.executarJS("window.scrollBy(0, arguments[0])", frame.getLocation().y);
		dsl.entrarFrame("frame2");
		dsl.clicarBotao("frameButton");
		String msg = dsl.alertaObterTextoEAceitar();
		Assert.assertEquals("Frame OK", msg);
	}
	
	@Test
	public void deveInteragirComJanelas() {
		getDriver().findElement(By.id("buttonPopUpEasy")).click();
		getDriver().switchTo().window("Popup");
		getDriver().findElement(By.tagName("textarea")).sendKeys("Deu certo?");
		getDriver().close();
		getDriver().switchTo().window("");
		getDriver().findElement(By.tagName("textarea")).sendKeys("e agora?");
	}
	
	@Test
	public void deveInteragirComJanelaSemTitulo() {	
		getDriver().findElement(By.id("buttonPopUpEasy")).click();
		//Descobrir o ID da janela popup
		System.out.println(getDriver().getWindowHandle());
		System.out.println(getDriver().getWindowHandles());
		//Pegar o valor da posição 2 do array e preencher no campo
		getDriver().switchTo().window((String)getDriver().getWindowHandles().toArray()[1]);
		getDriver().findElement(By.tagName("textarea")).sendKeys("Deu certo?");
		//Pegar o valor da posição 1 do array e preencher no campo
		getDriver().switchTo().window((String)getDriver().getWindowHandles().toArray()[0]);
		getDriver().findElement(By.tagName("textarea")).sendKeys("e agora?");
	}
	

}

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteGoogle {
		
	@Test
	public void testes() {
		System.setProperty("webdriver.chrome.driver", "I:\\portalr7\\QA\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://www.google.com");
	}
}

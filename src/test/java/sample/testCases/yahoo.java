package sample.testCases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class yahoo {
	
	public static WebDriver driver;
	public static String URl = "https://www.yahoo.com/";
	public static String email = "mh.hasan1928@yahoo.com";
	public static String pass = ";qwertyuio@$";
	public static String recipient = "mh.hassann19@gmail.com";
	public static String emailSubject = "Hello";
	public static String emailBody = "Hello World";
	public static WebElement element;
	
	@BeforeSuite
	public static void setBrowserEnv(){
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\DELL\\eclipse-workspace\\chromedriver.exe");
		driver = new ChromeDriver();
		
		System.out.println("setBrowserEnv() method executed");
	}
	
	@BeforeClass
	public static void Login() throws InterruptedException{
		driver.get(URl);
		driver.manage().timeouts().implicitlyWait(05, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		driver.findElement(By.xpath("//a[@class='_yb_12px8']")).click();
		driver.findElement(By.xpath("//input[@id='login-username']")).sendKeys(email);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@id='login-passwd']")).sendKeys(pass);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[@id='login-signin']")).click();
		Thread.sleep(1000);
		
		System.out.println("Yahoo Login() method executed");
	}
	
	@Test(priority = 1)
	public static void writeMessage() throws InterruptedException{
		
		driver.findElement(By.xpath("//a[@class='_yb_codjp']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[normalize-space()='Compose']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[contains(@id,'message-to-field')]")).sendKeys(recipient);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@placeholder='Subject']")).sendKeys(emailSubject);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@aria-label='Message body']")).sendKeys(emailBody);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[contains(.,'Send')]")).click();
		Thread.sleep(5000);
		
		System.out.println("Yahoo writeMessage() mehtod executed & also Message sent succesfully");
	}
	
	@Test(priority = 2)
	public static void validateSent() throws InterruptedException{
		
		driver.findElement(By.xpath("//div[@id='mail-app-container']//li[.='Sent']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@class='D_F ab_FT em_N ek_BB iz_A H_6D6F']//li[3]")).click();
		element = driver.findElement(By.xpath("//span[@data-test-id='message-group-subject-text']"));
		Thread.sleep(1000);
		String actualSubject = element.getText();
		System.out.println("Actual subject UI = " + actualSubject);
		Thread.sleep(1000);
		element = driver.findElement(By.xpath("//div[@dir='ltr']"));
		String actualBody = element.getText();
		System.out.println("Actual Body UI = " + actualBody);
		
		if((actualSubject.equals(emailSubject)) && (actualBody.equals(emailBody))) {
			AssertJUnit.assertTrue(true);
			System.out.println("Email Sending Successful from yahoo");
		}else {
			AssertJUnit.fail();
			System.out.println("Email sending fail from yahoo");
		}
		
	
	}
	
	
	@AfterClass
	public static void Logout() throws InterruptedException{
		driver.findElement(By.xpath("//div[@class='_yb_5q1mr']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[@class='_yb_tdi30 _yb_po6e2 _yb_m78yl _yb_16g61 _yb_1delc']")).click();
		Thread.sleep(2000);
		System.out.println("Yahoo Logout() method executed");
	}
	
	@AfterSuite
	public static void browserClose() throws InterruptedException{
		driver.close();
		driver.quit();
		System.out.println("browserClose() method executed");
	}
	
	
}

package sample.testCases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class gmail {
	
	public static WebDriver driver;
	public static String URl = "https://gmail.com/";
	public static String email = "mh.hassann19@gmail.com";
	public static String pass = ";qwertyuio@$";
	public static String recipient = "mh.hasan1928@yahoo.com";
	public static String emailSubject = "Hello";
	public static String emailBody = "I am confirming that I receive your email.";
	public static WebElement element;
	/*
	@BeforeSuite
	public static void setBrowserEnv() throws InterruptedException{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\DELL\\eclipse-workspace\\chromedriver.exe");
		driver = new ChromeDriver();
		System.out.println("setBrowserEnv() method executed");
	}
	*/
	@BeforeClass
	public static void Login() throws InterruptedException{
		driver.get(URl);
		driver.manage().timeouts().implicitlyWait(05, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys(email);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[contains(@class, 'VfPpkd-LgbsSe VfPpkd-LgbsSe-OWXEXe-k8QpJ VfPpkd-LgbsSe-OWXEXe-dgl2Hf nCP5yc AjY5Oe DuMIQc qIypjc TrZEUc')]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(pass);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[@class='VfPpkd-LgbsSe VfPpkd-LgbsSe-OWXEXe-k8QpJ VfPpkd-LgbsSe-OWXEXe-dgl2Hf nCP5yc AjY5Oe DuMIQc qIypjc TrZEUc']")).click();
		Thread.sleep(1000);
		
		System.out.println("Gmail Login() method executed");
	}
	
	@Test(priority=1)
	public static void validateInbox() throws InterruptedException{
	
		//click to message
		driver.findElement(By.xpath("//td[@id=':2l']//span[@class='y2']//ancestor::tr[1]")).click();
		Thread.sleep(1000);
		//getting Email Subject
		element = driver.findElement(By.xpath("//h2[@class='hP']"));
		Thread.sleep(1000);
		String actualSubject = element.getText();
		System.out.println("Actual Subject UI = " +actualSubject);
		
		//getting Email Body
		element = driver.findElement(By.xpath("//div[contains(text(),'Hello World')]"));
		Thread.sleep(1000);
		String actualBody = element.getText();
		System.out.println("Actual Body UI = " +actualBody);
		
		// Match with emailSubject & emailBody
		if((actualSubject.equals(emailSubject)) && (actualBody.equals(emailBody))) {
			AssertJUnit.assertTrue(true);
			System.out.println("Email Matches");	
		}else {
			AssertJUnit.fail();
			System.out.println("Email does not match");
		}
		
	}
	
	@Test(priority=2)
	public static void writeMessage() throws InterruptedException{
		driver.findElement(By.xpath("//div[text()='Compose']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//textarea[@name='to']")).sendKeys(recipient);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@name='subjectbox']")).sendKeys(emailSubject);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@role='textbox']")).sendKeys(emailBody);
		Thread.sleep(2000);
		
		//click to send button 
		driver.findElement(By.xpath("//div[@class='dC']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[@class='TO nZ aiq']")).click();
		Thread.sleep(3000);
		
		System.out.println("Gmail writeMessage() mehtod executed & also Message sent succesfully");
	}
	
	
	@AfterClass
	public static void Logout() throws InterruptedException{
		driver.findElement(By.xpath("//a[@role='button']//img[@class='gb_Da gbii']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[normalize-space()='Sign out']")).click();
		Thread.sleep(2000);
		System.out.println("Gmail Logout() method executed");
	}
	/*
	@AfterSuite
	public static void browserClose() throws InterruptedException{
		driver.close();
		driver.quit();
		System.out.println("browserClose() method executed");
	}
	*/
	
}

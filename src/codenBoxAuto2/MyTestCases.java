package codenBoxAuto2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
//import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MyTestCases {

	WebDriver driver = new ChromeDriver();
	String theUrl = ("https://codenboxautomationlab.com/");

	Connection con;

	Statement stmt;

	ResultSet rs;

	String firstName;

	String lastName;

	String phone;
	
	String customerName;

	Random rand = new Random();

	@BeforeTest
	public void runTheWebsite() throws SQLException {
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "123456");

		driver.get(theUrl);
		driver.manage().window().maximize();
	}

	@Test(priority = 1, enabled = false)
	public void radioButton() {
//change the xpath !!
		WebElement containerRadioButton = driver.findElement(By.xpath("input"));
		containerRadioButton.findElements(By.tagName("input")).get(2);
	}

	@Test(priority = 2, enabled = false)
	public void automComplete() throws InterruptedException {

		String[] Countries = { "Jor", "Syr", "Ira" };

		WebElement CountryInput = driver.findElement(By.id("autocomplete"));
		CountryInput.sendKeys(Countries[1]);

		Thread.sleep(2000);

		CountryInput.sendKeys(Keys.chord(Keys.ARROW_DOWN, Keys.ENTER));
	}

	@Test(priority = 3, enabled = false)
	public void selectTag() throws InterruptedException {

		WebElement mySelectTag = driver.findElement(By.id("dropdown-class-example"));

		Select mySelect = new Select(mySelectTag);
//		
		mySelect.selectByValue("option2");

		mySelect.selectByIndex(1);
//		
		mySelect.selectByVisibleText("API");
	}

	@Test(priority = 4, enabled = false)
	public void checkBoxes() {

		WebElement checkBoxContainer = driver.findElement(By.xpath(""));
//		when i need selest al items ,thie code:

		List<WebElement> allCheckBoxes = checkBoxContainer.findElements(By.tagName("input"));
		for (int i = 0; 1 < allCheckBoxes.size(); i++) {
			allCheckBoxes.get(i).click();
		}
//		checkBoxContainer.findElements(By.tagName("input")).get(1).click();

	}

	@Test(priority = 5, enabled = true)
	public void switchWindow() {

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("window.scrollTo(0,700)");

		driver.findElement(By.id("openwindow")).click();

		System.out.println(driver.getTitle());

		Set<String> handels = driver.getWindowHandles();

		List<String> allTabs = new ArrayList<>(handels);

		driver.switchTo().window(allTabs.get(1));

	}

	@Test(enabled = false)

	public void switchTab() throws InterruptedException {

		driver.findElement(By.id("opentab")).click();

		Set<String> handels = driver.getWindowHandles();
		List<String> allTabs = new ArrayList<String>(handels);

		driver.switchTo().window(allTabs.get(1));

		Thread.sleep(2000);
		System.out.println(driver.getTitle());
		driver.switchTo().window(allTabs.get(0));

	}

	@Test(enabled = true)
	public void alertAndConfirm() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeAsyncScript("windowscrollTo(0,600)");

		String myName = "Ahmad";

		driver.findElement(By.id("name")).sendKeys(myName);
		;

		driver.findElement(By.id("confirmbtn")).click();
		Thread.sleep(2000);

		System.out.println(driver.switchTo().alert().getText().contains(myName));
		boolean actualValue = driver.switchTo().alert().getText().contains(myName);
		Assert.assertEquals(actualValue, true);

		driver.switchTo().alert().dismiss();

	}

	@Test(enabled = false)
	public void theTabl() {
		WebElement theTable = driver.findElement(By.id("product"));
		List<WebElement> allData = theTable.findElements(By.tagName("td"));
		for (int i = 2; i < allData.size(); i = i + 3)

			System.out.println(allData.get(i).getText());
		driver.findElement(By.id("name")).sendKeys(allData.get(0).getText());

	}

	@Test(enabled = false)
	public void mouseHover() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeAsyncScript("windowscrollTo(0,1900)");

		Thread.sleep(4000);

		WebElement mouseHover = driver.findElement(By.id("mousehover"));

		Actions action = new Actions(driver);

		action.moveToElement(mouseHover).build().perform();

//		driver.findElement(By.linkText("Top")).click();

		driver.findElement(By.linkText("Relod")).click();
		;

	}

	@Test(enabled = false)
	public void calender() throws InterruptedException, SQLException {
		driver.findElement(By.linkText("Booking Calender")).click();

		Set<String> handels = driver.getWindowHandles();
		List<String> allTabs = new ArrayList<>(handels);
		driver.switchTo().window(allTabs.get(1));
		driver.findElement(By.linkText("25")).click();

		Thread.sleep(3000);

		// SQL CONNECTION
		int randId = rand.nextInt(145,150);
//		String queryToRead = "select * from customer where customerNumber=103";
		String queryToRead = "select * from customer where customerNumber="+randId ;

		stmt = con.createStatement();

		rs = stmt.executeQuery(queryToRead);

		while (rs.next()) {
			firstName = rs.getString("contactFirstName");
			lastName = rs.getString("contactLastName");
			phone = rs.getNString("phone");
			customerName = rs.getNString("customerName");
		}
//		System.out.println(firstName);
//		System.out.println(lastName);
		int randomNmber = rand.nextInt(6000);
		driver.findElement(By.id("name1")).sendKeys(firstName);
		driver.findElement(By.id("secondname1")).sendKeys(lastName);
		driver.findElement(By.id("email1")).sendKeys(firstName + lastName + randomNmber + "@gmail.com");
		driver.findElement(By.id("phone")).sendKeys(phone);
		driver.findElement(By.id("details1")).sendKeys(customerName);

	}

}
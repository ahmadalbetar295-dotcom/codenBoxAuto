package codenBoxAuto;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
//import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MyTestCases<SET> {

	WebDriver driver = new ChromeDriver();
	String website = ("https://codenboxautomationlab.com/practice/");

	Connection con;

	Statement stmt;

	ResultSet rs;
//	int rs;

	String firstName;

	String lastName;

	
	String phone;
	
	int randomId;
	
	String customerName;

	Random rand = new Random();

	@BeforeTest
	public void runTheWebsite() throws SQLException {

		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "123456");
		driver.get(website);
		driver.manage().window().maximize();

	}

	@Test(priority = 1, enabled = false)

	public void radioButton() {

		WebElement containerRadioButton = driver.findElement(By.xpath("//*[@id=\"radio-btn-example\"]/fieldset"));
		containerRadioButton.findElements(By.tagName("input")).get(1).click();
	}

	@Test(priority = 2, enabled = false)

	public void automComplete() throws InterruptedException {

		String[] Countries = { "Jor", "Syr", "Ira" };

		WebElement CountryInput = driver.findElement(By.id("autocomplete"));

		CountryInput.sendKeys(Countries[0]);
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

		WebElement checkBoxContainer = driver.findElement(By.xpath("//div[@id='checkbox-example']//fieldset"));
		
		
//		when i need select all items ,this code:

		List<WebElement> allCheckBoxes = checkBoxContainer.findElements(By.tagName("input"));
		for (int i = 0; i < allCheckBoxes.size(); i++) {
			allCheckBoxes.get(i).click();
		}
		
//		when i need select one item ,this code:
//		checkBoxContainer.findElements(By.tagName("input")).get(1).click();

	}

	@Test(priority = 5, enabled = false)

	public void switchWindow() throws InterruptedException {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,700)");

		driver.findElement(By.id("openwindow")).click();
		System.out.println(driver.getTitle());

		Set<String> handels = driver.getWindowHandles();
		List<String> allTabs = new ArrayList<>(handels);

		driver.switchTo().window(allTabs.get(1));
		Thread.sleep(5000);
		driver.switchTo().window(allTabs.get(0));
	}

	@Test(priority = 6, enabled = false)

	public void switchTab() throws InterruptedException {

		driver.findElement(By.id("opentab")).click();

		Set<String> handels = driver.getWindowHandles();

		List<String> allTabs = new ArrayList<String>(handels);

		driver.switchTo().window(allTabs.get(1));
		Thread.sleep(2000);
		System.out.println(driver.getTitle());

		driver.switchTo().window(allTabs.get(0));

	}

	@Test(priority = 7, enabled = false)

	public void alertAndConfirm() throws InterruptedException {

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("window.scrollTo(0,500)");

		String myName = "Ahmad";
		driver.findElement(By.id("name")).sendKeys(myName);

		driver.findElement(By.id("confirmbtn")).click();
		Thread.sleep(2000);

		System.out.println(driver.switchTo().alert().getText().contains(myName));
		boolean actualValue = driver.switchTo().alert().getText().contains(myName);
		Assert.assertEquals(actualValue, true);

		driver.switchTo().alert().dismiss();

	}

	@Test(priority = 8, enabled = false)

	public void theTabl() {

		WebElement theTable = driver.findElement(By.id("product"));

		List<WebElement> allData = theTable.findElements(By.tagName("td"));
		for (int i = 2; i < allData.size(); i = i + 3)

			System.out.println(allData.get(i).getText());
		driver.findElement(By.id("name")).sendKeys(allData.get(0).getText());

	}

	@Test(priority = 9, enabled = false)

	public void mouseHover() throws InterruptedException {

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("window.scrollTo(0,1700)");
		Thread.sleep(3000);

		WebElement mouseHover = driver.findElement(By.id("mousehover"));

		Actions action = new Actions(driver);

		action.moveToElement(mouseHover).build().perform();

//		driver.findElement(By.linkText("Top")).click();

		driver.findElement(By.linkText("Reload")).click();

	}

	@Test(priority = 1)
	

	public void addData() throws SQLException {
		randomId = rand.nextInt(5441,6010);
		System.out.println(randomId);

		String queryToAdd =  "INSERT INTO customers (" + "customerNumber, " + "customerName, " + "contactLastName, "
				+ "contactFirstName, " + "phone, " + "addressLine1, " + "addressLine2, " + "city, " + "state, "
				+ "postalCode, " + "country, " + "salesRepEmployeeNumber, " + "creditLimit" + ") VALUES (" + randomId
				+ "," + "'Tech Solutions Ltd.', " + "'Smith', " + "'John', " + "'+1 800 555 1234', "
				+ "'123 Tech Park', " + "'Suite 400', " + "'San Francisco', " + "'CA', " + "'94107', " + "'USA', "
				+ "1166, " + "100000.00" + ");";

		stmt = con.createStatement();

		int rowInserted = stmt.executeUpdate(queryToAdd);

		System.out.println(rowInserted);

	}

	@Test(priority = 2)
	public void updateData() throws SQLException {
		String queryToUpdate = "UPDATE customers\r\n" + "SET contactFirstName = 'ahmad',\r\n"
				+ "    contactLastName = 'albetar'\r\n" + "WHERE customerNumber = " + randomId;
		stmt = con.createStatement();

		int updatedRow = stmt.executeUpdate(queryToUpdate);

		System.out.println(updatedRow);
	}

	
	@Test(priority = 3, enabled = true)

	public void calender() throws InterruptedException, SQLException {

		driver.findElement(By.linkText("Booking Calendar")).click();

		Set<String> handels = driver.getWindowHandles();

		List<String> allTabs = new ArrayList<>(handels);

		driver.switchTo().window(allTabs.get(1));
		Thread.sleep(7000);

		driver.findElement(By.linkText("28")).click();


		// SQL CONNECTION

		int randomId = rand.nextInt(144, 147);

		String queryToRead = "select * from customers where customerNumber=" +randomId;
//		String queryToRead = "select * from customer where customerNumber=103";

		stmt = con.createStatement();

		rs = stmt.executeQuery(queryToRead);

		while (rs.next()) {

			firstName = rs.getString("contactFirstName");
			lastName = rs.getString("contactLastName");

			phone = rs.getNString("phone");
			customerName = rs.getNString("customerName");
		}


		int randomNmber = rand.nextInt(6000);
		driver.findElement(By.id("name1")).sendKeys(firstName);
		driver.findElement(By.id("secondname1")).sendKeys(lastName);
		driver.findElement(By.id("email1")).sendKeys(firstName + lastName + randomNmber + "@gmail.com");
		driver.findElement(By.id("phone1")).sendKeys(phone);

		driver.findElement(By.id("details1")).sendKeys(customerName);
//		driver.navigate().to("https://www.booking.com");

	}
	
	
	
	@Test(priority = 4, enabled = false)
	
	
	public void deleteData() throws SQLException {
		
	
		
		String queryToDelete = "DELETE FROM customers\r\n" + "WHERE customerNumber =" +randomId;

		
		stmt = con.createStatement();

		
		int deletedRow = stmt.executeUpdate(queryToDelete);

		
		System.out.println(deletedRow);
	}

	@Test(priority = 5, enabled = false ,invocationCount = 1)
	
	public void takeAscreenShot() throws SQLException, IOException, InterruptedException {
		
		
//  For First ScreenShots
		
		Date timestamp = new Date();
		
		Thread.sleep(2000);

		System.out.println(timestamp);
		
		String newtimestamp = timestamp.toString().replace(":", "-");

		
		
		TakesScreenshot ts = (TakesScreenshot) driver;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		
		File file = ts.getScreenshotAs(OutputType.FILE);
		
		FileUtils.copyFile(file, new File("./ScreenShot_Folder/" + newtimestamp + ".jpg"));
		
		
		
		
		
	//  For Second ScreenShots
	
		Date timestamp2 = new Date();
		
		Thread.sleep(2000);

		System.out.println(timestamp2);
		
		
		String newtimestamp2 = timestamp2.toString().replace(":", "-");

		js.executeScript("window.scrollTo(0,600)");
		
		
		
		File file2 = ts.getScreenshotAs(OutputType.FILE);
		
		FileUtils.copyFile(file2, new File("./ScreenShot_Folder/" + newtimestamp2 + ".jpg"));
	}
}
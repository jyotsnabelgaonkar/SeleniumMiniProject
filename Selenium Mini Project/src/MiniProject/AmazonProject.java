package MiniProject;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class AmazonProject {
	static ArrayList<String> ar = new ArrayList<String> ();
	static void excelFile() {

		try {
			File file = new File("D:\\eclipse\\Java TQ\\str\\Selenium Mini Project\\Excel\\OrderProducts.xls");
			FileInputStream inputStream = new FileInputStream(file);
			HSSFWorkbook wb = new HSSFWorkbook(inputStream);
			HSSFSheet sheet = wb.getSheet("Product Details");

			Iterator<Row> itr = sheet.iterator();

			// Iterating over Excel file in Java
			while (itr.hasNext()) {
				Row row = itr.next();

				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();

					switch (cell.getCellType()) {
						case STRING:

							String get = cell.getStringCellValue();
							ar.add(get);
							break;

						default:

					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(ar);
	}

	static void login() 
	{
		
	}

	static void web() throws IOException {

		System.setProperty("webdriver.chrome.driver", "D:\\Java jars TQ\\Chromedriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.amazon.in/");
		driver.findElement(By.xpath("//span[@id='nav-link-accountList-nav-line-1']")).click();

		driver.findElement(By.xpath("//input[@id='ap_email']")).sendKeys("8766830803");
		driver.findElement(By.xpath("//input[@id='continue']")).click();
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("infotechnology");
		driver.findElement(By.xpath("//input[@id='signInSubmit']")).click();

		for (int i = 0; i<ar.size(); i++) {
			driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).clear();
			driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys(ar.get(i));
			driver.findElement(By.xpath("//input[@id='nav-search-submit-button']")).click();

			List<WebElement> results = driver.findElements(By.xpath("//div[@class='s-expand-height s-include-content-margin s-latency-cf-section s-border-bottom']"));
			List<WebElement> links;

			System.out.println("\n" + ar.get(i) + "- \n");

			int counter = 0;
			File file = new File("C:\\Users\\DELL\\Desktop\\Data.txt");
			FileWriter f1 = new FileWriter(file, true);
			BufferedWriter wr = new BufferedWriter(f1);
			wr.newLine();
			wr.append("\n " + ar.get(i) + "- \n");
			for (WebElement element: results) {
				links = element.findElements(By.tagName("a"));

				for (int j = 0; j<links.size(); j++) {
					if (links.get(j).getText().length() > 20) {
						counter++;

						System.out.println("result " + counter + "-" + links.get(j).getText());

						wr.append("result " + counter + "-" + links.get(j).getText() + "\n");

						//wr.newLine();

					}
					if (counter >= 5)
						break;
				}

			}
			wr.close();
		}
	}
	
	public static void main(String[] args) throws IOException {
		excelFile();
		web();
	}

}


/*
-------------------Two Step Authentication code--------------------

 public class TwoStepAuth {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Totp totp=new Totp("j7fo bnqp brbi lh57 sdv6 5tjm rn5p thea");
		String twoFactorCode=totp.now();
		System.out.println("Two factor code is :"+twoFactorCode);
	}

}

------------------Proxy Code--------------------------------------

public class proxy2 {
	
public static void main(String[] args) {
	//System.setProperty("webdriver.gecko.driver","c://worksoft//gd//geckodriver.exe");
	//WebDriver driver=new FirefoxDriver();
	System.setProperty("webdriver.chrome.driver","c://worksoft//chromedriver//chromedriver.exe");	


String proxy = "127.0.0.1:5000";
ChromeOptions options = new ChromeOptions().addArguments("--proxy-server=http://" + proxy);
WebDriver driver = new ChromeDriver(options);
//WebDriver driver = new ChromeDriver();
driver.get("https://www.google.co.in/");

	}
}

*/
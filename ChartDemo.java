        import java.text.ParseException;
		import java.text.SimpleDateFormat;
		import java.util.Date;
		import java.util.List;
		import java.util.concurrent.TimeUnit;

		import org.openqa.selenium.By;
		import org.openqa.selenium.WebDriver;
		import org.openqa.selenium.WebElement;
		import org.openqa.selenium.chrome.ChromeDriver;
		import org.openqa.selenium.interactions.Action;
		import org.openqa.selenium.interactions.Actions;
		import org.openqa.selenium.support.ui.ExpectedConditions;
		import org.openqa.selenium.support.ui.WebDriverWait;

		public class ChartDemo {
		     public static void main (String []args) throws InterruptedException
		     {
		    	 //driver initialization 
		    	 System.setProperty("webdriver.chrome.driver","./Drivers/chromedriver.exe");
		    	 WebDriver driver =new ChromeDriver();
		    	 WebDriverWait wait = new WebDriverWait(driver,100);
		    	 Actions builder = new Actions(driver);
		    	 driver.get("https://www.highcharts.com/demo/line-ajax");
		    	 driver.manage().window().maximize();
		    	 // cookies handle code
		    	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"CybotCookiebotDialogBodyLevelButtonAccept\"]")));
		    	 WebElement COOKIES=driver.findElement(By.xpath("//*[@id=\"CybotCookiebotDialogBodyLevelButtonAccept\"]"));
		    	 //I choose the thread sleep option at the end
		    	 Thread.sleep(5000);
		    	 try {
		    		 driver.findElement(By.xpath("//*[@id=\"CybotCookiebotDialogBodyLevelButtonAccept\"]")).click();
		    	 }
		    	 catch (Exception e) {
		    		 builder.moveToElement(COOKIES).click().perform();
				}
		    	 //Chart code start
		    	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@fill,'#434348')]")));
		    	 wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//*[contains(@fill,'#434348')]"),30));
		    	 List<WebElement> graphPath = driver.findElements(By.xpath("//*[contains(@fill,'#434348')]"));
		    	 System.out.println(graphPath.size());
		    	 //date logic
		    	 SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy");
		    	 String ChartStartDate = "18/12/2017";
		    	 String DateWeAreTesting = "06/01/2018";
		    	 int diffInDays=18;//default for date 05/01/2018
		    	 try {
		    		    Date date1 = myFormat.parse(ChartStartDate);
		    		    Date date2 = myFormat.parse(DateWeAreTesting);
		    		    long diff = date2.getTime() - date1.getTime();
		    		    diffInDays=(int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
		    		} catch (ParseException e) {
		    		    e.printStackTrace();
		    		}
				WebElement codinate=graphPath.get(diffInDays);
		    	 
		         Action mouseOverHome = builder
		        		 .moveToElement(codinate)
		        		 .build();
		         
		         mouseOverHome.perform();
		         //Tooltip logic
		         String SessionValueBeforeClick=driver.findElement(By.xpath("//*[@dx='0' and contains(@style,'bold')][2]")).getText();
		         SessionValueBeforeClick.trim();
		         System.out.println(SessionValueBeforeClick);
		         //Highlighted windows logic
		         builder.moveToElement(codinate).click().perform();
		         String test=driver.findElement(By.xpath("//div[@class='highslide-maincontent']")).getText();
		         String[] SessionValueAfterClick = test.split(":",2)[1].split(" ");
		         System.out.println(SessionValueAfterClick[0]);
		         //String Validation logic
		         if (SessionValueBeforeClick.equals(SessionValueAfterClick[0].trim())) {
					System.out.println("Value match successfull");
				} else {
					System.out.println("Value matching failed");
				}
		         //Close Highlighted window close logic
		         wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='Close (esc)']")));
		         driver.findElement(By.xpath("//a[@title='Close (esc)']")).click();
		         driver.close();
		         driver.quit();
		     }
		
	}



package SeleniumChallenge;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Part3 
{

	public static void main(String[] args)
	{
		
		System.setProperty("webdriver.chrome.driver", "./chromedriver");
		WebDriver driver=new ChromeDriver();
		
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));
		
		driver.get("https://www.saucedemo.com");
		
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).click();
		
		
		
		List<WebElement> elements=driver.findElements(By.xpath("//div[@class='pricebar']//div[@class='inventory_item_price']"));		
		List<WebElement> cartElements=driver.findElements(By.xpath("//div[@class=\"pricebar\"]//button[contains(text(),'Add to cart')]"));
		double max,temp=0.0;
		WebElement cartEle=null;
		max=0.0;
		int count=1;
		for(int i=0;i<elements.size();i++)
		{
			for(int j=i+1;j<elements.size();j++)
			{
			
		
		
				Double a=Double.parseDouble(elements.get(i).getText().replace("$",""));
				Double b=Double.parseDouble(elements.get(j).getText().replace("$",""));
		   
				
				cartEle=cartElements.get(i);
				if(a.compareTo(b)==1 && a>max)
				{
					
						max=a;
						
						System.out.println("max= "+max);
						System.out.println("count= "+count);
						count=j;

					
				}
				else if(a.compareTo(b)==-1 && b>max)
				{
					
					max=b;
				
					System.out.println("max= "+max);
					System.out.println("count= "+count);
					count=j;
					
				}
				
			}
		}
		
		System.out.println("Maximum= "+max);
		String newValue="$"+String.valueOf(max);
		System.out.println("newValue= "+newValue);
		System.out.println("Clicking on the element.....");
		count++;
		cartEle=driver.findElement(By.xpath("(//div[@class='pricebar']//button[contains(text(),'Add to cart')])"+"["+count+"]"));
		System.out.println(cartEle);
		cartEle.click();
		
		
	}
}

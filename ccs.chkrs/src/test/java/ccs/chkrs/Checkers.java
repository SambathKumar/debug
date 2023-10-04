package ccs.chkrs;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
/*import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;*/

public class Checkers {

	public static void main(String[] args) throws InterruptedException {

		ChromeOptions co=new ChromeOptions();
		WebDriver wd=new ChromeDriver(co);
		final int SLEEPSEC=3000;
		try {
			Checkers ch=new Checkers();
			wd.get("https://www.gamesforthebrain.com/game/checkers/");
			if(wd.getTitle().equals("Checkers - Games for the Brain")) {
				System.out.println("Checkers page launched successfully");
			} else {
				System.out.println("Error in launching checkers page");
			}
			wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			wd.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(100));
			wd.manage().window().maximize();
			ch.clickOrange(wd, "62");
			ch.moveOrange(wd, "53");
			Thread.sleep(SLEEPSEC);
			if(ch.getOrangeCount(wd, "53")==1) {
				System.out.println("Frsom 6,2 row moved successfully");
			} else {
				System.err.println("From 6,2 row is not moved successfully");
				System.exit(1);
			}
				
			/*WebDriverWait wait=new WebDriverWait(wd, Duration.ofSeconds(20));
			wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("message"), "Make a move."));*/
				
			ch.clickOrange(wd, "53");
			ch.moveOrange(wd, "44");
			Thread.sleep(SLEEPSEC);
			//wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("message"), "Make a move."));
			if(ch.getOrangeCount(wd, "44")==1 || ch.getMeCount(wd, "53")==1) {
				System.out.println("From 5,3 row moved successfully");	
			} else {
				System.err.println("From 5,3 row is not moved successfully");
				System.exit(1);
			}
			ch.clickOrange(wd, "42");
			ch.moveOrange(wd, "33");
			Thread.sleep(SLEEPSEC);
			if(ch.getOrangeCount(wd, "33")==1 || ch.getMeCount(wd, "42")==1) {
				System.out.println("From 4,2 row moved successfully");	
			} else {
				System.err.println("From 4,2 row is not moved successfully");
				System.exit(1);
			}
			ch.clickOrange(wd, "22");
			ch.moveOrange(wd, "33");
			Thread.sleep(SLEEPSEC);
			if(ch.getOrangeCount(wd, "33")==1 || ch.getMeCount(wd, "22")==1) {
				System.out.println("From 2,2 row moved successfully");	
			} else {
				System.err.println("From 2,2 row is not moved successfully");
				System.exit(1);
			}
			ch.clickOrange(wd, "02");
			ch.moveOrange(wd, "13");
			Thread.sleep(SLEEPSEC);
			if(ch.getOrangeCount(wd, "13")==1 || ch.getMeCount(wd, "02")==1) {
				System.out.println("From 0,2 row moved successfully");	
			} else {
				System.err.println("From 0,2 row is not moved successfully");
				System.exit(1);
			}
			wd.findElement(By.partialLinkText("Restart...")).click();
				
			int zerothCountrow=ch.getOrangeCountOnRow(wd, "0");
			int firstCountrow=ch.getOrangeCountOnRow(wd, "1");
			int secondCountrow=ch.getOrangeCountOnRow(wd, "2");
			int ThirdCountrow=ch.getOrangeCountOnRow(wd, "3");
				
			if(zerothCountrow==4 && firstCountrow==4 && secondCountrow==4 && ThirdCountrow==0) {
				System.out.println("Restarted successfully");
			} else {
				System.out.println("Restart is not successful");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//wd.close();
			wd.quit();
		}

	}
	
	public int getOrangeCountOnRow(WebDriver wd, String columnNumber) {
		List<WebElement> elems=wd.findElements(By.cssSelector("img[src='you1.gif'][name$='"+ columnNumber +"']"));
		return elems.size(); 
	}

	public void clickOrange(WebDriver wd, String columnValue) {
		 wd.findElement(By.cssSelector("img[src='you1.gif'][name='space"+ columnValue +"']")).click();
	}
	
	public void moveOrange(WebDriver wd, String columnValue) {
		 wd.findElement(By.cssSelector("img[name='space"+ columnValue +"']")).click();
	}
	
	public int getOrangeCount(WebDriver wd, String columnValue) {
		List<WebElement> elems=wd.findElements(By.cssSelector("img[src='you1.gif'][name='space"+ columnValue +"']"));
		int elemCount=elems.size();
		if(elemCount ==1) {
			return elemCount;
		}else {
			return 0;
		}
	}
	
	
	public int getMeCount(WebDriver wd, String columnValue) {
		List<WebElement> elems= wd.findElements(By.cssSelector("img[src^='me'][src$='.gif'][name='space"+ columnValue +"']"));
		return elems.size();
	}
}

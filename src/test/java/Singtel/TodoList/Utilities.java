package Singtel.TodoList;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Random;

import org.apache.commons.exec.util.StringUtils;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import junit.framework.Assert;
import net.bytebuddy.utility.RandomString;



public class Utilities {
	
	  public static WebDriver driver;
	  public static String task1;
	  public static int position;
	  public static int count_completed;
	  public static int count_active;
	  public static int count_total_task;
	  public static int actual_number;
	  public static int count_beforeRemove;
	  public static String driverpath= System.getProperty("user.dir") + "//" + "src//test//resources//"	+ "chromedriver96.exe";
	
	public static void retrive_count_completedTask()  {
		 try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  count_completed = 0;
		  String errormsg = null;
		  List < WebElement > ls_task = driver.findElements(By.xpath("//*/section/section/ul/li"));
		  count_total_task = ls_task.size();
		  if(ls_task.size()>0) {
			  for (int i = 1; i < ls_task.size(); i++) {
					  if(driver.findElement(By.xpath("/html/body/section/section/ul/li[" + i + "]/div/input")).isSelected()) {
						//  errormsg= (driver.findElement(By.xpath("/html/body/section/section/ul/li[" + i + "]/div/input")).getText());
						  count_completed = count_completed + 1;
					  }
		  }
	}
		  
	}
	
	public static void retrive_count_activeTask()  {
		
		  count_active = 0;
		  String errormsg = null;
		  List < WebElement > ls_task = driver.findElements(By.xpath("//*/section/section/ul/li"));
		  count_total_task = ls_task.size();
		  if(ls_task.size()>0) {
			  for (int i = 1; i < ls_task.size(); i++) {
					  if(!driver.findElement(By.xpath("/html/body/section/section/ul/li[" + i + "]/div/input")).isSelected()) {
						  errormsg= (driver.findElement(By.xpath("/html/body/section/section/ul/li[" + i + "]/div/input")).getText());
						  count_active = count_active + 1;
					  }
		  }
	}
		  
	}
	
	public static void complete_Randomtask() throws Throwable {
		// driver.findElement(By.xpath("//*/section/footer/ul/li[1]/a")).click();
		 Thread.sleep(5000);	 
			  
		  List < WebElement > ls_task = driver.findElements(By.xpath("//*/section/section/ul/li"));
		  System.out.println("Total number of task added " + ls_task.size());
		  
		  Random rand = new Random(); //instance of random class
		  int lowerbound = 1;
		  int upperbound = ls_task.size();
		  int int_random ;
		  int_random =lowerbound + rand.nextInt(upperbound) ;		  
		  System.out.println("Random Number" + int_random);
		  driver.findElement(By.xpath("/html/body/section/section/ul/li["+ int_random +"]/div/input")).click();  
		  Assert.assertEquals(true,  driver.findElement(By.xpath("/html/body/section/section/ul/li["+ int_random +"]/div/input")).isSelected());
	}
	
	public static void validate_notcompleteTask() throws Throwable {
		  Thread.sleep(5000);
		   driver.findElement(By.xpath("//*/section/footer/ul/li[1]/a")).click();
		   Thread.sleep(5000);
		     int  actual_completed =0;
			  List < WebElement > ls_task = driver.findElements(By.xpath("//*/section/section/ul/li"));
		      System.out.println("Count in Task list after completed" +ls_task.size());
		      
			  for (int i = 1; i < ls_task.size(); i++) {
				  System.out.println("Position count in loop" +position);
				  if(driver.findElement(By.xpath("/html/body/section/section/ul/li[" + i + "]/div/input")).isSelected()) {
					 System.out.println(driver.findElement(By.xpath("/html/body/section/section/ul/li[" + i + "]/div/input")).getText());
					 actual_completed = actual_completed + 1;
				  }
				  else {
					  String failTaskname =(driver.findElement(By.xpath("/html/body/section/section/ul/li[" + i + "]/div/input")).getText());
					  Assert.fail("Not Completed Task " + failTaskname + "is getting listed under Completed Tab");
				  }
			  }
	}
}




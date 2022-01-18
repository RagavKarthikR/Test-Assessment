package Singtel.TodoList;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

public class Stepdef extends Utilities {

  // ******Scenario 1 : Verify the landing page of todomvc

  @Given("^Initiate the chrome driver$")
  public void initiate_the_chrome_driver() {
    System.setProperty("webdriver.chrome.driver", "C://Users//Harshitha//Downloads//Softwares//chromedriver96.exe");
    driver = new ChromeDriver();
  }

  @When("^Navigate to Todolist url$")
  public void navigate_to_Todolist_url() {
    driver.get("https://todomvc.com/examples/vue/#/all");
    driver.manage().window().maximize();

  }
  @Then("^Verify the landing page of Todolist$")
  public void verify_the_landing_page_of_Todolist() {
    String lpage_title = driver.getTitle();
    Assert.assertEquals(lpage_title, "Vue.js • TodoMVC");
  }
  @Then("^Verify whether input text box is visible$")
  public void verify_whether_input_text_box_is_visible() {
    Assert.assertEquals(true, driver.findElement(By.xpath("//*/section/header/input")).isDisplayed());

  }

  //******Scenario 2 : Verify whether the user is able to add the task to the Todolist

  @Given("^Input text box visible$")
  public void input_text_box_visible() {
    Assert.assertEquals(true, driver.findElement(By.xpath("//*/section/header/input")).isDisplayed());
  }

  @When("^Enter the tasklists \"([^\"]*)\" in input text box$")
  public void enter_the_tasklists_in_input_text_box(String taskname) {
    task1 = taskname;
    driver.findElement(By.xpath("/html/body/section/header/input")).sendKeys(task1);
    driver.findElement(By.xpath("/html/body/section/header/input")).sendKeys(Keys.ENTER);
  }

  @Then("^Verify the footer section$")
  public void verify_the_footer_section() {
    Assert.assertEquals(true, driver.findElement(By.xpath("//*/section/footer/ul")).isDisplayed());

    //Verify the footer fields

    if (driver.findElement(By.xpath("//*/section/footer/ul")).isDisplayed()) {
      List < WebElement > ls_footer = driver.findElements(By.xpath("//*/section/footer/ul/li"));
      Assert.assertEquals(ls_footer.size(), 3);
      if (ls_footer.size() == 3) {
        String txt_footer_all = driver.findElement(By.xpath("//*/section/footer/ul/li[1]")).getText();
        String txt_footer_active = driver.findElement(By.xpath("//*/section/footer/ul/li[2]")).getText();
        String txt_footer_completed = driver.findElement(By.xpath("//*/section/footer/ul/li[3]")).getText();
        Assert.assertEquals(txt_footer_all, "All");
        Assert.assertEquals(txt_footer_active, "Active");
        Assert.assertEquals(txt_footer_completed, "Completed");
      }
    }

  }

  @Then("^Verify the task in the task list$")
  public void verify_the_task_in_the_task_list() {
    Assert.assertEquals(true, driver.findElement(By.xpath("//*/section/section/ul/li")).isDisplayed());

    //Verify the Addtask in the list

    if (driver.findElement(By.xpath("//*/section/section/ul/li")).isDisplayed()) {
      List < WebElement > ls_task = driver.findElements(By.xpath("//*/section/section/ul/li"));
      int count = 0;
      for (int i = 0; i < ls_task.size(); i++) {
        String actual = ls_task.get(i).getText();
        if (actual.equals(task1)) {
          count = count + 1;
        }
      }
      if (count < 1) {
        Assert.fail("Added Task is not visible in list");

      }

    }
  }

  //******Scenario 3 : Verify that the user is able to perform the complete action to the task and that the task is being updated

  @Given("^Check for existing active task else add the new tasklist \"([^\"]*)\"$")
  public void checkforActiveTask(String taskname) {
    if (driver.findElement(By.xpath("//*/section/section/ul/li")).isDisplayed()) {
      List < WebElement > ls_task = driver.findElements(By.xpath("//*/section/section/ul/li"));
      if (ls_task.size() < 0) {
        enter_the_tasklists_in_input_text_box(taskname);
        verify_the_task_in_the_task_list();
      }
    }
  }

  @When("^Select the radio button next to the task$")
  public static void select_the_radio_button_next_to_the_task() {
    if (driver.findElement(By.xpath("//*/section/section/ul/li")).isDisplayed()) {
      List < WebElement > ls_task = driver.findElements(By.xpath("//*/section/section/ul/li"));

      // driver.findElement(By.xpath("/html/body/section/section/ul/li[1]/div/input")).click();

      for (int i = 0; i < ls_task.size(); i++) {
        String actual = ls_task.get(i).getText();

        if (actual.contentEquals(task1)) {
          position = i + 1;
          driver.findElement(By.xpath("/html/body/section/section/ul/li[" + position + "]/div/input")).click();
          System.out.println("Record not present");
        }
      }

    }
  }

  @SuppressWarnings("deprecation") @Then("^Verify the selected task is marked as completed and the clear complete button is added to the footer$")
  public void verify_completedtask() {
    Assert.assertEquals(true, driver.findElement(By.xpath("/html/body/section/section/ul/li[" + position + "]/div/input")).isSelected());
    if (driver.findElement(By.xpath("//*/section/footer/button")).isDisplayed()) {
      String txt_footer_clear = driver.findElement(By.xpath("//*/section/footer/button")).getText();
      Assert.assertEquals("Clear completed", txt_footer_clear);
    } else {
      Assert.fail("Clear completed is not getting displayed in footer");
    }
  }

  //******Scenario 4 : Verify whether the Active tab displays the Active tasks

  @Given("^There are some completed tasks and some active tasks$")
  public void check_completeTask() {

    // Add few more active task
    enter_the_tasklists_in_input_text_box("Reminder for connection");
    enter_the_tasklists_in_input_text_box("Reminder for refund");

  }

  @When("^Click on Active button from the footer$")
  public static void click_on_Active_button_from_the_footer() throws InterruptedException {
    retrive_count_completedTask();
    driver.findElement(By.xpath("//*/section/footer/ul/li[2]/a")).click();

  }

  @SuppressWarnings("deprecation")
  @Then("^Check that only active tasks are listed$")
  public void check_that_only_active_tasks_are_listed() {
    count_active = 0;
    List < WebElement > ls_task = driver.findElements(By.xpath("//*/section/section/ul/li"));

    for (int i = 1; i < ls_task.size(); i++) {
      if (!driver.findElement(By.xpath("/html/body/section/section/ul/li[" + i + "]/div/input")).isSelected()) {
        System.out.println(driver.findElement(By.xpath("/html/body/section/section/ul/li[" + i + "]/div/input")).getText());
        count_active = count_active + 1;
      } else {
        String failTaskname = (driver.findElement(By.xpath("/html/body/section/section/ul/li[" + i + "]/div/input")).getText());
        Assert.fail("Completed Task " + failTaskname + "is getting listed under Active Tab");
      }
    }

    int expected_count = count_total_task - count_completed;
    String res_itemcount = driver.findElement(By.xpath("/html/body/section/footer/span[1]/strong[1]")).getText();
    actual_number = Integer.parseInt(res_itemcount);
    System.out.println("No of items in footer :" + actual_number);
    Assert.assertEquals(expected_count, actual_number);

  }

  //******Scenario 5 : Verify whether the Completed tab displays only the Completed tasks

  @Given("^Create new task and completed task$")
  public void create_new_task_and_completed_task() throws Throwable {
    // Add few more active task and completed task 

    driver.findElement(By.xpath("//*/section/footer/ul/li[1]/a")).click();
    enter_the_tasklists_in_input_text_box("Change Connection");
    enter_the_tasklists_in_input_text_box("PWC meeting");
     complete_Randomtask();
	 Thread.sleep(10000);

  }

  @When("^Click on Completed button from the footer$")
  public void click_on_Completed_button_from_the_footer() throws InterruptedException {

    retrive_count_completedTask();
    System.out.println("Total completed task from function :" + count_completed);
    Thread.sleep(10000);
    driver.findElement(By.xpath("//*/section/footer/ul/li[3]/a")).click();

  }

  @Then("^Check that Completed active tasks are listed$")
  public void check_that_Completed_active_tasks_are_listed() throws Throwable {

    //validate_notcompleteTask();
	

    int expected_count = count_total_task - count_completed;
    String res_itemcount = driver.findElement(By.xpath("/html/body/section/footer/span[1]/strong[1]")).getText();
    actual_number = Integer.parseInt(res_itemcount);
    System.out.println("No of items in footer :" + actual_number);
    Assert.assertEquals(expected_count, actual_number);

  }
  
  //******Scenario 6 : Verify whether the Completed tab displays only the Completed tasks
  
  @Given("^Exiting Completed task lists are available$")
  public void exiting_Completed_task_lists_are_available() {
	      driver.findElement(By.xpath("/html/body/section/footer/ul/li[3]/a")).click();
	      retrive_count_completedTask();
		  if(count_completed > 0) {
			Assert.assertEquals(true, driver.findElement(By.xpath("/html/body/section/footer/button")).isDisplayed());
			  
		  }
  }

  @When("^Click on the Clear complete button$")
  public void click_on_the_Clear_complete_button() throws Throwable {
	  Thread.sleep(5000);
	  driver.findElement(By.xpath("/html/body/section/footer/button")).click();
	  Thread.sleep(5000);
	  driver.findElement(By.xpath("/html/body/section/footer/ul/li[1]/a")).click();
  }

  @SuppressWarnings("deprecation")
@Then("^Verify all the completed tasks are removed form the list$")
  public void verify_all_the_completed_tasks_are_removed_form_the_list() {
	       retrive_count_completedTask();
	       System.out.println("Result for final "+ count_completed);
		  if(count_completed>0) {
			  Assert.fail("Cleared Task  still exists in the Task list" );
		  }
	  
  }
  
  //******Scenario 7 : Verify whether the user is able to mark the all the active task into completed by clicking on the dropdown icon 
  
  @Given("^Exiting Active task lists are available$")
  public void exiting_Active_task_lists_are_available() {

    driver.findElement(By.xpath("/html/body/section/footer/ul/li[1]/a")).click();
    retrive_count_activeTask();
    if (count_active < 1) {
      enter_the_tasklists_in_input_text_box("Deliverables");
      enter_the_tasklists_in_input_text_box("TimeSheet");

    }
  }
  

  @When("^click on the select all check box$")
  public void click_on_the_select_all_check_box() throws Throwable {
    Assert.assertEquals(true, driver.findElement(By.xpath("/html/body/section/section/label[1]")).isDisplayed());

    if (driver.findElement(By.xpath("/html/body/section/section/label[1]")).isDisplayed()) {
      driver.findElement(By.xpath("/html/body/section/section/label[1]")).click();

    } else {
      Assert.fail("Select drop down is not visible");
    }
  }

  @Then("^Verify all the active tasks are marked as completed$")
  public void verify_all_the_active_tasks_are_marked_as_completed() {
    driver.findElement(By.xpath("/html/body/section/footer/ul/li[1]/a")).click();
    retrive_count_activeTask();
    if (count_active > 0) {
      Assert.fail("All the tasks are not marked as Completed");
    } else {
      int expected_count = 0;
      String res_itemcount = driver.findElement(By.xpath("/html/body/section/footer/span[1]/strong[1]")).getText();
      actual_number = Integer.parseInt(res_itemcount);
      System.out.println("No of items in footer" + actual_number);
      Assert.assertEquals(expected_count, actual_number);
    }
  }

  //******Scenario 8 : Verify Whether the user is able to change the completed task into active
  
  @Given("^Exiting Complete task lists are available$")
  public void exiting_Complete_task_lists_are_available() throws Throwable {

    retrive_count_completedTask();
    
    if (count_completed < 0) {
      complete_Randomtask();
      driver.findElement(By.xpath("//*/section/footer/ul/li[3]/a")).click();
      Thread.sleep(5000);
    }

  }

  @When("^Change the status from completed to active$")
  public void change_the_status_from_completed_to_active() {
	    String res_itemcount = driver.findElement(By.xpath("/html/body/section/footer/span[1]/strong[1]")).getText();
	    actual_number = Integer.parseInt(res_itemcount);
	    count_beforeRemove = actual_number; 
	    System.out.println("Count Beferoe" +count_beforeRemove);
	    driver.findElement(By.xpath("/html/body/section/section/ul/li[1]/div/input")).click();
  }
	
  @Then("^Verify completed  task list$")
  public void verify_completed_task_list() {
	   retrive_count_completedTask();
	   String res_itemcount = driver.findElement(By.xpath("/html/body/section/footer/span[1]/strong[1]")).getText();
	   int count_afterRemove = Integer.parseInt(res_itemcount);
	   System.out.println("Count After" +count_afterRemove);
	   int expectedActiveItem = count_beforeRemove + 1;
	   Assert.assertEquals(expectedActiveItem, count_afterRemove);
	   
  }
  
  @Given("^Application open$")
  public void application_open() {
	    String lpage_title = driver.getTitle();
	    Assert.assertEquals(lpage_title, "Vue.js • TodoMVC");
  }

  @When("^Close Driver$")
  public void close_Driver()  {
	  driver.close();
  }

  @Then("^Browser Close$")
  public void browser_Close()  {
	  //
   }
  

  

  
} //End of Class
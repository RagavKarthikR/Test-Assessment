package Singtel.TodoList;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "C://Users//Harshitha//eclipse-workspace//TodoList//src//test//resources//Fetature//todolist.feature"
		,plugin = { "pretty" }
		)

public class RunTest {
}


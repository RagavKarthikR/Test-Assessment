$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("todolist.feature");
formatter.feature({
  "line": 1,
  "name": "Singtel Todolist Validation",
  "description": "",
  "id": "singtel-todolist-validation",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 3,
  "name": "Verify the landing page of todomvc",
  "description": "",
  "id": "singtel-todolist-validation;verify-the-landing-page-of-todomvc",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 4,
  "name": "Initiate the chrome driver",
  "keyword": "Given "
});
formatter.step({
  "line": 5,
  "name": "Navigate to Todolist url",
  "keyword": "When "
});
formatter.step({
  "line": 6,
  "name": "Verify the landing page of Todolist",
  "keyword": "Then "
});
formatter.step({
  "line": 7,
  "name": "Verify whether input text box is visible",
  "keyword": "And "
});
formatter.match({
  "location": "Stepdef.initiate_the_chrome_driver()"
});
formatter.result({
  "duration": 2118809300,
  "status": "passed"
});
formatter.match({
  "location": "Stepdef.navigate_to_Todolist_url()"
});
formatter.result({
  "duration": 393805900,
  "status": "passed"
});
formatter.match({
  "location": "Stepdef.verify_the_landing_page_of_Todolist()"
});
formatter.result({
  "duration": 6773700,
  "status": "passed"
});
formatter.match({
  "location": "Stepdef.verify_whether_input_text_box_is_visible()"
});
formatter.result({
  "duration": 76837200,
  "status": "passed"
});
});
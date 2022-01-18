Feature: Singtel Todolist Validation

  Scenario: Verify the landing page of todomvc
    Given Initiate the chrome driver
    When Navigate to Todolist url
    Then Verify the landing page of Todolist
    And Verify whether input text box is visible

  Scenario: Verify whether the user is able to add the task to the Todolist
    Given Input text box visible
    When Enter the tasklists "Bill Payment" in input text box
    Then Verify the footer section
    And Verify the task in the task list

  Scenario: Verify that the user is able to perform the complete action to the task and that the task is being updated
    Given Check for existing active task else add the new tasklist "Reminder for service"
    When Select the radio button next to the task
    Then Verify the selected task is marked as completed and the clear complete button is added to the footer

  Scenario: Verify whether the Active tab displays the Active tasks
    Given There are some completed tasks and some active tasks
    When Click on Active button from the footer
    Then Check that only active tasks are listed

  Scenario: Verify whether the Completed tab displays only the Completed tasks
    Given Create new task and completed task
    When Click on Completed button from the footer
    Then Check that Completed active tasks are listed

  Scenario: Verify whether the completed tasks are getting removed from the Todolist when the user clicks the Clear completed button
    Given Exiting Completed task lists are available
    When Click on the Clear complete button
    Then Verify all the completed tasks are removed form the list

  Scenario: Verify whether the user is able to mark the all the active task into completed by clicking on the dropdown icon
    Given Exiting Active task lists are available
    When click on the select all check box
    Then Verify all the active tasks are marked as completed

  Scenario: Verify Whether the user is able to change the completed task into active
    Given Exiting Complete task lists are available
    When Change the status from completed to active
    Then Verify completed  task list

  Scenario: Close Browser
    Given Application open
    When Close Driver
    Then Browser Close

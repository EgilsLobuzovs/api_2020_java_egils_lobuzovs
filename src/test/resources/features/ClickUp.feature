Feature: Exam

  Scenario: Add folder
    When  User creates a new folder with id "2572572"
    And Check if that folder is created
    Then Delete created folder

  @NewFolder
  Scenario: Add folder and list
    When User creates a new folder with id "2572572" and name "My New Folder"
    And Check if that folder is created
    And Create list in folder with name "My New List"
    Then Check if that list is created


  @NewFolder
  @SpaceNameChanged
  Scenario: Update space name
    When User updates space with id "2572572" name to "My New Space"
    And User creates new folder with name "Some New Folder"
    And Check if that folder is created
    And Create list in folder with name "My New List"
    And Check if that list is created
    Then Verify Space Id and Name
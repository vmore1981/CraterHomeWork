@regression @items_management
Feature: Items Management

  @create_items @smoke_tests
  Scenario: As a user, I am able to create an item or service
    Given As an entity user, I am logged in
    And I navigate to Items tab
    When I am click on Add Item button
    Then I should be on New Item Page
    When I provide Item name "Books" and price 1800 unit "pc" and description "a good book"
    And I click Save Item button
    Then The item is added to the item list table

  @update_items @smoke_tests
  Scenario: As a user, I am able to update an item or service
    Given As an entity user, I am logged in
    And I navigate to Items tab
    When I select the item "Books"
    Then I should be on item details page
    When I update the item price to 3000 dollars
    And I click Update Item button
    Then the Item price is updated to 3000 dollars

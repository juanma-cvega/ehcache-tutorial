Feature: Crud operations from cache work

  Scenario: A customer is stored in the cache
    Given customer 1 is not in the system
    And a new customer 1 is created
    When the customer is stored
    Then customer 1 should be in the cache
    And customer 1 should be in the system

  Scenario: A customer is fetched from cache
    Given a new customer 1 is created
    When customer 1 is fetched from the system
    Then customer 1 should be returned
    And customer 1 should have been retrieved from cache
    And store should not have been accessed

  Scenario: A customer is fetched from store and cached updated
    Given a new customer 1 is created
    And customer 1 is not cached
    When customer 1 is fetched from the system
    Then customer 1 should be returned
    And cache should have been updated with customer 1
    And customer 1 should have been retrieved from store

  Scenario: A customer is removed from system
    Given customer 1 exists in the system
    And customer 1 is cached
    When customer 1 is removed
    Then customer 1 should be removed from cache
    And customer 1 should be removed from store

  Scenario: A customer is removed when he doesn't exist on the system
    Given customer 1 is not in the system
    When customer 1 is removed
    Then customer 1 should not be in the system
    And customer 1 should not be in the cache
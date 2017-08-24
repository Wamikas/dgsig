Feature: Procedure tests

  Scenario Outline: Procedure EGUREC216
    Given the procedure has "<record_id>"
    When it runs with the corresponding input values from the file
    Then the result is successful


    Examples:
      |    record_id       |
      |20170731163239056674|
      |20170731163240422114|
      |20170731165601942078|
      |20170731165603348385|
      |20170731165603348340|
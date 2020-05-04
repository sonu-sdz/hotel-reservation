@challenge
Feature: Challenge

  @challenge01
  Scenario Outline: GET geo-suggest
    Given testing environment is set
    When I set headers as below
      | Content-Type | application/json |
    And I pass query parameters as below
      | query | <place> |
    And I invoke GET request on resource "/api/hotel/ahs/v2/geo-suggest"
    Then I get response status as "200"
    And response body is not null

    Examples:
      | place   |
      | paris   |
      | newyork |

  @challenge02
  Scenario Outline: POST  search request
    Given testing environment is set
    When I set headers as below
      | Content-Type | application/json |
    And I pass query parameters as below
      | query | <place> |
    And I add below fields to request body
      | placeId     | ChIJD7fiBh9u5kcRYJSMaMOCCwQ |
      | destination | paris                       |
    And I add an object "dates" with below fields to request body
      | checkin  | 11-09-2020 |
      | checkout | 13-09-2020 |
    And I add a room array item as below to request body
      | guestType | age |
      | ADT       |     |
      | ADT       |     |
      | CHD       | 3   |
      | ADT       |     |
      | ADT       |     |

    Examples:
      | place   |
      | paris   |
      | newyork |
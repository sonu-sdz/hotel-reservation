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
    And response body contains
      | <place> |

    Examples:
      | place   |
      | paris   |
      | newyork |

  #custom implementation for search request body
  @challenge02
  Scenario Outline: POST search request
    Given testing environment is set
    When I set headers as below
      | Content-Type | application/json |
    And I add below fields to request body
      | placeId     | ChIJD7fiBh9u5kcRYJSMaMOCCwQ |
      | destination | <place>                     |
    And I add an object "dates" with below fields to request body
      | checkin  | <checkin>  |
      | checkout | <checkout> |
    And I add a room array item as below to request body
      | guestType | age |
      | ADT       |     |
      | ADT       |     |
      | CHD       | 3   |
    And I add a room array item as below to request body
      | guestType | age |
      | ADT       |     |
      | CHD       | 3   |
    And I invoke POST request on resource "/api/hotel/ahs/search/request"
    Then I get response status as "200"
    And response body contains
      | <place>    |
      | <checkin>  |
      | <checkout> |

    Examples:
      | place   | checkin    | checkout   |
      | paris   | 11-09-2020 | 13-09-2020 |
      | newyork | 14-09-2020 | 16-09-2020 |
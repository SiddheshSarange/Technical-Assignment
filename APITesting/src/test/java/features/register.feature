Feature: Verify User Registration, Login, Create, delete and Get list of users
@URegistrationAPI
Scenario Outline: To verify registration is successful with valid credentials  
Given Add payload with email "<Email>" password "<Password>"
When User calls registerAPI
Then API response with "<status>" and <code>
And response id and token

Examples:
     		|Email             |Password|status|code|
			|eve.holt@reqres.in|pistol  |OK	   |200 |
			
@LoginAPI
Scenario Outline: To verify Login is successful with valid credentials  
Given Add payload with email "<Email>" password "<Password>"
When User calls loginAPI
Then API response with "<status>" and <code>
And response id and token

Examples:
     		|Email             |Password|status|code|
			|eve.holt@reqres.in|pistol  |OK	   |200 |	

@CreateAPI			
Scenario Outline: To verify User able to create name and job entry
Given Add create payload with name "<Name>" and job "<Job>"
When User calls createAPI
Then API response with "<status>" and <code>
And response name "<Name>" job "<Job>" and createAt

Examples:
     		|Name    |Job   |status     |code|
			|morpheus|leader|Created    |201 |

@GetAPI
Scenario Outline: To verify list of users on given page number
Given Add get request with page number <PageNo>
When User calls getAPI
Then API response with "<status>" and <code>
And response page <PageNo> perPage <perPg> and user details

Examples:
     		|PageNo|status|code|perPg|
			|2     |OK    |200 |6    |

@DeleteAPI			
Scenario Outline: To verify User able to delete entry
Given Add delete request with user Id <IdNo>
When User calls getAPI
Then API response with "<status>" and <code>

Examples:
     		|IdNo|status    |code|
			|2   |No Content|204 |
			

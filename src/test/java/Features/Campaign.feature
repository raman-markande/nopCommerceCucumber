Feature: Campaign 

@Campaign 
Scenario: Add New Campaign 
	Given User is on Campaign page 
	When User clicks on Add button and enters below list of campaigns details 
		|Name|Subject|Body|
		|Camp1|Election|Election body|
		|Camp2|Blood Donation|Blood Donation body|
		|Camp3|Collage|Collage body|
	Then Above Campaigns should be created successfully
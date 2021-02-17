Feature: Giftcard 

Background: Common steps 
	Given User is on Giftcard page 
	
	
@Giftcard
Scenario: Add New Giftcard 
	When User clicks on Add button and enters below giftcard details 
		|OrderType|RecipientName|SenderName|
		|Physical|Virat|Dhoni|
	Then Above Giftcard should be created successfully 
	
	
@Giftcard
Scenario: Edit Giftcard 
	When User selects existing Giftcard with Recipient Name as "Brenda Lindgren" to edit 
	And updates Giftcard details as below 
		|OrderType|RecipientName|
		|Virtual|Gayle|
	Then Giftcard should be updated successfully with new details

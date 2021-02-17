Feature: Category 

@Category
Scenario Outline: Add New Category 
	Given User is on Category page 
	When User provide below list of "<CategoryName>" , "<ParentCategory>" as input data 
	Then below list of Categories should be created 
	
	Examples: 
		|CategoryName|ParentCategory|
		|RealMe Mobile|Electronics|
		|Cucumber BDD Framework|Books|
		|Tanishq|Jewelry|
		|Call of Duty-Xbox One X|Digital downloads|
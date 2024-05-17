# US006 - Register a vehicle 

## 3. Design - User Story Realization 

### 3.1. Rationale

_**Note that SSD - Alternative One is adopted.**_

| Interaction ID | Question: Which class is responsible for...   | Answer                    | Justification (with patterns)                                                                     |
|:---------------|:----------------------------------------------|:--------------------------|:--------------------------------------------------------------------------------------------------|
| Step 1  		     | 	... interacting with the actor?              | RegisterVehicleUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
| Step 2  		     | 							                                       |                           |                                                                                                   |
| Step 3         |                                           |                                |                                                                                                   |
| Step 4         |                       |                       |
| Step 5 	  		   | 	... coordinating the US?                     | RegisterVehicleController | Controller                                                                                        |
| 			  		        | 	... instantiating a new Vehicle?             | VehicleRepository         | Creator (Rule 6): the Organization registers the vehicle.                                         |
|                | 	...saving the inputted data?                 | Vehicle                   | IE: object created in step 3 has its own data.                                                    |
| 		             | 	... validating all data (local validation)?  | Vehicle                   | IE: owns its data.                                                                                | 
| 			  		        | 	... validating all data (global validation)? | VehicleRepository         | IE: knows all its vehicles.                                                                       | 
| 			  		        | 	... saving the vehicle?                      | VehicleRepository         | IE: owns all its vehicles.                                                                        | 
| Step 6  		     | 	... informing operation success?             | RegisterVehicleUI         | IE: is responsible for user interactions.                                                         | 

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

* VehicleRepository
* Vehicle

Other software classes (i.e. Pure Fabrication) identified: 

* RegisterVehicleUI  
* RegisterVehicleController


## 3.2. Sequence Diagram (SD)

_**Note that SSD - Alternative Two is adopted.**_

### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](svg/us006-sequence-diagram-full.svg)

### Split Diagrams

The following diagram shows the same sequence of interactions between the classes involved in the realization of this user story, but it is split in partial diagrams to better illustrate the interactions between the classes.

It uses Interaction Occurrence (a.k.a. Interaction Use).

![Sequence Diagram - split](svg/us006-sequence-diagram-split.svg)

**Register Vehicle**

![Sequence Diagram - Partial - Register Vehicle](svg/us006-sequence-diagram-partial-register-vehicle.svg)

## 3.3. Class Diagram (CD)

![Class Diagram](svg/us006-class-diagram.svg)
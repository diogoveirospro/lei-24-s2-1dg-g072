# US006 - Register a vehicle 

## 3. Design - User Story Realization 

### 3.1. Rationale

_**Note that SSD - Alternative One is adopted.**_

| Interaction ID | Question: Which class is responsible for...   | Answer                    | Justification (with patterns)                                                                                 |
|:---------------|:----------------------------------------------|:--------------------------|:--------------------------------------------------------------------------------------------------------------|
| Step 1  		     | 	... interacting with the actor?              | RegisterVehicleUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
| 			  		        | 	... coordinating the US?                     | RegisterVehicleController | Controller                                                                                                    |
| 			  		        | 	... instantiating a new Vehicle Maintenance? | VehicleRepository         | Creator (Rule 6): the Organization registers the vehicle.                                                     |
| Step 2  		     | 							                                       |                           |                                                                                                               |
| Step 3  		     | 	...saving the inputted data?                 | Vehicle                   | IE: object created in step 1 has its own data.                                                                |
| Step 4  		     | 	... validating all data (local validation)?  | Vehicle                   | IE: owns its data.                                                                                            | 
| 			  		        | 	... validating all data (global validation)? | VehicleRepository         | IE: knows all its vehicles.                                                                                   | 
| 			  		        | 	... saving the vehicle maintenance?          | VehicleRepository         | IE: owns all its vehicles.                                                                                    | 
| Step 5  		     | 	... informing operation success?             | RegisterVehicleUI         | IE: is responsible for user interactions.                                                                     | 

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

![Sequence Diagram - Full](svg/us026-sequence-diagram-full.svg)

## 3.3. Class Diagram (CD)

![Class Diagram](svg/us026-class-diagram.svg)
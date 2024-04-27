# US007 - Register a Vehicle’s Maintenance

## 3. Design - User Story Realization 

### 3.1. Rationale

_**Note that SSD - Alternative One is adopted.**_


| Interaction ID | Question: Which class is responsible for...   | Answer                               | Justification (with patterns)                                                                                 |
|:---------------|:----------------------------------------------|:-------------------------------------|:--------------------------------------------------------------------------------------------------------------|
| Step 1  		     | 	... interacting with the actor?              | RegisterVehicleMaintenanceUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
| 			  		        | 	... coordinating the US?                     | RegisterVehicleMaintenanceController | Controller                                                                                                    |
| 			  		        | 	... instantiating a new Vehicle Maintenance? | RegisterVehicleMaintenanceRepository | Creator (Rule 7): the Organization registers the maintenance of a vehicle.                                    |
| Step 2  		     | 							                                       |                                      |                                                                                                               |
| Step 3  		     | 	...saving the inputted data?                 | Maintenance                          | IE: object created in step 1 has its own data.                                                                |
| Step 4  		     | 	... validating all data (local validation)?  | Maintenance                          | IE: owns its data.                                                                                            | 
| 			  		        | 	... validating all data (global validation)? | VehicleMaintenanceRepository         | IE: knows all its vehicles.                                                                                   | 
| 			  		        | 	... saving the vehicle maintenance?          | VehicleMaintenanceRepository         | IE: owns all its vehicles.                                                                                    | 
| Step 5  		     | 	... informing operation success?             | RegisterVehicleMaintenanceUI         | IE: is responsible for user interactions.                                                                     | 

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

* VehicleMaintenanceRepository
* Maintenance

Other software classes (i.e. Pure Fabrication) identified: 

* RegisterVehicleMaintenanceUI  
* RegisterVehicleMaintenanceController


## 3.2. Sequence Diagram (SD)

_**Note that SSD - Alternative One is adopted.**_

### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](svg/us007-sequence-diagram-full.svg)

### Split Diagrams

The following diagram shows the same sequence of interactions between the classes involved in the realization of this user story, but it is split in partial diagrams to better illustrate the interactions between the classes.

It uses Interaction Occurrence (a.k.a. Interaction Use).

![Sequence Diagram - split](svg/us007-sequence-diagram-split.svg)


**Register Maintenance**


![Sequence Diagram - Partial - Register Maintenance](svg/us007-sequence-diagram-partial-register-maintenance.svg)

## 3.3. Class Diagram (CD)

![Class Diagram](svg/us007-class-diagram.svg)

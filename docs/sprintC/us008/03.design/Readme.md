# US008 - List vehicles that need Maintenance 

## 3. Design - User Story Realization 

### 3.1. Rationale

_**Note that SSD - Alternative One is adopted.**_

| Interaction ID | Question: Which class is responsible for...         | Answer                    | Justification (with patterns)                                                                                                                                         |
|:---------------|:----------------------------------------------------|:--------------------------|:----------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Step 1  		     | 	... interacting with the actor?                    | ListMaintenanceUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model.                                                         |
| Step 2  		     | 	 						                                            |                           |                                                                                                                                                                       |
| Step 3	        | 	... coordinating the US?                           | ListMaintenanceController | Controller: coordinates the interactions related to creating the maintenance list in the user interface (UI) and executes the logic needed to process these requests. |
| 		             | 	...getting the Vehicle list?                       | VehicleRepository         | IE: owns all of its Vehicles.                                                                                                                                         |
|                | ... validate all of the vehicles(local validation)? | Maintenance               | IE: knows the last maintenance.                                                                                                                                       |
|                | ... change the vehicle list?                        | MaintenanceRepository     | IE: knows all the last maintenances.                                                                                                                                  |
|                | ... create a list with all the data of vehicleList? | vehicleMapper             | IE: Transforms all of its data.                                                                                                                                       |
|                | ... transform a vehicle into data?                  | vehicleDto                | IE: Transforms all of is data.                                                                                                                                        |
| Step 4  		     | 	...showing the maintenance list?                   | ListMaintenanceUI         | IE: is responsible for user interactions.                                                                                                                             |


### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

* VehicleRepository
* MaintenanceRepository
* Maintenance
* VehicleMapper
* VehicleDto

Other software classes (i.e. Pure Fabrication) identified: 

* ListMaintenanceUI  
* ListMaintenanceController


## 3.2. Sequence Diagram (SD)

_**Note that SSD - Alternative One is adopted.**_

### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](svg/us008-sequence-diagram-full.svg)

### Split Diagrams

The following diagram shows the same sequence of interactions between the classes involved in the realization of this user story, but it is split in partial diagrams to better illustrate the interactions between the classes.

It uses Interaction Occurrence (a.k.a. Interaction Use).

![Sequence Diagram - split](svg/us008-sequence-diagram-split.svg)

**Get Vehicle List Partial SD**

![Sequence Diagram - Partial - Get Vehicle List](svg/us008-sequence-diagram-partial-get-vehicle-list.svg)

**Create a Copy of Vehicle List Partial SD**

![Sequence Diagram - Partial - Create Copy of Vehicle List](svg/us008-sequence-diagram-partial-create-a-copy-of-vehicle-list.svg)
## 3.3. Class Diagram (CD)

![Class Diagram](svg/us008-class-diagram.svg)


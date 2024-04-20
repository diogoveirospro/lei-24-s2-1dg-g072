# US008 - List vehicles that need Maintenance 

## 3. Design - User Story Realization 

### 3.1. Rationale

_**Note that SSD - Alternative One is adopted.**_

| Interaction ID | Question: Which class is responsible for... | Answer                    | Justification (with patterns)                                                                                                                                         |
|:---------------|:--------------------------------------------|:--------------------------|:----------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Step 1  		     | 	... interacting with the actor?            | ListMaintenanceUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model.                                                         |
| 		             | 	... coordinating the US?                   | ListMaintenanceController | Controller: coordinates the interactions related to creating the maintenance list in the user interface (UI) and executes the logic needed to process these requests. |
| Step 2  		     | 	 						                                    |                           |                                                                                                                                                                       |
| Step 3  		     | 	...getting the Maintenance list?           | MaintenanceRepository     | IE: owns all of its Maintenances                                                                                                                                      |
| Step 4  		     | 	...showing the maintenance list?           | ListMaintenanceUI         | IE: is responsible for user interactions.                                                                                                                             |


### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

* MaintenanceRepository

Other software classes (i.e. Pure Fabrication) identified: 

* ListMaintenanceUI  
* ListMaintenanceController


## 3.2. Sequence Diagram (SD)

_**Note that SSD - Alternative Two is adopted.**_

### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](svg/us008-sequence-diagram-full.svg)

### Split Diagrams

The following diagram shows the same sequence of interactions between the classes involved in the realization of this user story, but it is split in partial diagrams to better illustrate the interactions between the classes.

It uses Interaction Occurrence (a.k.a. Interaction Use).

![Sequence Diagram - split](svg/us008-sequence-diagram-split.svg)

**Get Vehicle Maintenance List Partial SD**

![Sequence Diagram - Partial - Get Task Category List](svg/us008-sequence-diagram-partial-get-vehicle-maintenance-list.svg)


## 3.3. Class Diagram (CD)

![Class Diagram](svg/us008-class-diagram.svg)
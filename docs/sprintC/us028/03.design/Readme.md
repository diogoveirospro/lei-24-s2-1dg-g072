# US028 - Consult Assigned Tasks

## 3. Design - User Story Realization 

### 3.1. Rationale

_**Note that SSD - Alternative One is adopted.**_

| Interaction ID | Question: Which class is responsible for...      | Answer                 | Justification (with patterns)                                                                                                                                   |
|:---------------|:-------------------------------------------------|:-----------------------|:----------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Step 1  		     | 	... interacting with the actor?                 | ListTasksUI            | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model.                                                   |
| Step 2  		     | 	 						                                         |                        |                                                                                                                                                                 |
| Step 3	        | 	... coordinating the US?                        | ListTasksController    | Controller: coordinates the interactions related to creating the tasks list in the user interface (UI) and executes the logic needed to process these requests. |
|                | ... knows all of its collaborators?              | CollaboratorRepository | IE: has all of the collaborators.                                                                                                                               |
|                | ... validate collaborator?                       | CollaboratorRepository | IE: knows all of its  collaborators.                                                                                                                            |
| Step 4         |                                                  |                        |                                                                                                                                                                 |
| Step 5         | ... saving the selected dates?                   | Date                   | IE: Knows all of its dates.                                                                                                                                     |
| Step 6         | ... getting a list of all status?                | TaskRepository         | IE: Knows all types of status.                                                                                                                                  |
| Step 7         |                                                  |                        |                                                                                                                                                                 |
| Step 8         |                                                  |                        |                                                                                                                                                                 |
| Step 9		       | 	...getting the Task list?                       | TaskRepository         | IE: owns all of its tasks.                                                                                                                                      |
|                | ... create a list with all the data of taskList? | TaskMapper             | IE: Transforms all of its data.                                                                                                                                 |
|                | ... transform a task into data?                  | TaskDto                | IE: Transforms all of is data.                                                                                                                                  |
| Step 10  	     | 	...showing the task list?                       | ListTasksUI            | IE: is responsible for user interactions.                                                                                                                       |

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

* CollaboratorRepository
* Date
* TaskRepository
* TaskMapper
* TaskDto

Other software classes (i.e. Pure Fabrication) identified: 

* ListTaskUI  
* ListTaskController


## 3.2. Sequence Diagram (SD)

_**Note that SSD - Alternative One is adopted.**_

### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](svg/us028-sequence-diagram-full.svg)

### Split Diagrams

The following diagram shows the same sequence of interactions between the classes involved in the realization of this user story, but it is split in partial diagrams to better illustrate the interactions between the classes.

It uses Interaction Occurrence (a.k.a. Interaction Use).

![Sequence Diagram - split](svg/us028-sequence-diagram-split.svg)

**Register Vehicle**

![Sequence Diagram - Partial - Register Vehicle](svg/us028-sequence-diagram-partial-register-vehicle.svg)

## 3.3. Class Diagram (CD)

![Class Diagram](svg/us028-class-diagram.svg)


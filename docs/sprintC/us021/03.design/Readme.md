# US021 - Add a New Entry to the To-Do List

## 3. Design - User Story Realization

### 3.1. Rationale

_**Note that SSD - Alternative One is adopted.**_

| Interaction ID | Question: Which class is responsible for...                       | Answer                     | Justification (with patterns)                                                                                                                                     |
|:---------------|:------------------------------------------------------------------|:---------------------------|:------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Step 1         | ... interacting with the actor?                                   | AddToDoListEntryUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model.                                                     |
|                | ... coordinating the US?                                          | AddToDoListEntryController | Controller: coordinates the interactions related to adding a to-do list entry in the user interface (UI) and executes the logic needed to process these requests. |
|                | ... knowing the To-Do list Entries?                               | ToDoList                   | IE: knows how to access the data of To-Do list entries.                                                                                                           |
|                | ... create a list with all the data of toDoList?                  | ToDoListMapper             | IE: Transforms all of its data.                                                                                                                                   |
|                | ... creating the To-Do list DTO?                                  | ToDoListDto                | Pure Fabrication: a simple data structure to hold the list of To-Do entries for transfer.                                                                         |
|                | ... returning To-Do list entries DTOs to the UI?                  | AddToDoListEntryController | Controller: coordinates the interaction and data flow between the UI and the model.                                                                               |
| Step 2         |                                                                   |                            |                                                                                                                                                                   |
| Step 3         | ... knowing the task associated with the To-Do List entry?        | ToDoListEntry              | IE: ToDoListEntry is the most knowledgeable about its associated Task.                                                                                            |
|                | ... knowing the green space associated with the To-Do List entry? | ToDoListEntry              | IE: ToDoListEntry is the most knowledgeable about its associated GreenSpace.                                                                                      |
|                | ... instantiating a new To-Do List Entry?                         | ToDoList                   | Creator (Rule 2): the To-Do list registers a ToDoListEntry instance.                                                                                              |
|                | ... saving the inputted data?                                     | ToDoListEntry              | IE: processes user input and generates a to-do list entry based on that information.                                                                              |
|                | ... marking the status as pending?                                | ToDoListEntry              | IE: owns the information necessary to manage its status.                                                                                                          |
|                | ... validating all data (local validation)?                       | ToDoListEntry              | IE: owns its data.                                                                                                                                                |
| Step 4         |                                                                   |                            |                                                                                                                                                                   |
| Step 5         | ... validating all data (global validation)?                      | ToDoList                   | IE: knows all its to-do list entries.                                                                                                                             |
|                | ... saving the created to-do list entry?                          | ToDoList                   | IE: owns all its to-do list entries.                                                                                                                              |
| Step 6         | ... informing operation success?                                  | AddToDoListEntryUI         | IE: is responsible for user interactions.                                                                                                                         |

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are:

* CollaboratorRepository
* GreenSpaceRepository
* GreenSpaceRepositoryMapper
* GreenSpace
* ToDoList
* ToDoListMapper
* ToDoListEntry
* ToDoListDto
* GreenSpaceDto

Other software classes (i.e. Pure Fabrication) identified:

* AddToDoListEntryUI
* AddToDoListEntryController

## 3.2. Sequence Diagram (SD)

_**Note that SSD - Alternative One is adopted.**_

### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![us021-sequence-diagram-full.svg](svg%2Fus021-sequence-diagram-full.svg)

## 3.3. Class Diagram (CD)

![us021-class-diagram.svg](svg%2Fus021-class-diagram.svg)
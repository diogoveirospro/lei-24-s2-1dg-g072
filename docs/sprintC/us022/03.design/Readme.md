# US022 - Add a new Entry in the Agenda

## 3. Design - User Story Realization 

### 3.1. Rationale

_**Note that SSD - Alternative One is adopted.**_

| Interaction ID | Question: Which class is responsible for...                       | Answer                   | Justification (with patterns)                                                                                                                            |
|:---------------|:------------------------------------------------------------------|:-------------------------|:---------------------------------------------------------------------------------------------------------------------------------------------------------|
| Step 1         | ... interacting with the actor?                                   | AddAgendaEntryUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model.                                            |
|                | ... coordinating the US?                                          | AddAgendaEntryController | Controller: coordinates the interactions related to add agenda entry in the user interface (UI) and executes the logic needed to process these requests. |
|                | ... knowing the To-Do list Entries?                               | ToDoList                 | IE: knows how to access the data of To-Do list entries.                                                                                                  |
|                | ... create a list with all the data of toDoList?                  | ToDoListMapper           | IE: Transforms all of its data.                                                                                                                          |
|                | ... creating the To-Do list DTO?                                  | ToDoListDto              | Pure Fabrication: a simple data structure to hold the list of To-Do entries for transfer.                                                                |
|                | ... returning To-Do list entries DTOs to the UI?                  | AddAgendaEntryController | Controller: coordinates the interaction and data flow between the UI and the model.                                                                      |
| Step 2         |                                                                   |                          |                                                                                                                                                          |
| Step 3         | ... knowing the task associated with the To-Do List entry?        | ToDoListEntry            | IE: ToDoListEntry is the most knowledgeable about its associated Task.                                                                                   |
|                | ... knowing the green space associated with the To-Do List entry? | ToDoListEntry            | IE: ToDoListEntry is the most knowledgeable about its associated GreenSpace.                                                                             |
|                | ... instantiating a new Agenda Entry?                             | Agenda                   | Creator (Rule 2): the Agenda registers a EntryAgenda instance..                                                                                          |
|                | ... saving the inputted data?                                     | AgendaEntry              | IE: processes user input and generates a agenda entry based on that information.                                                                         |
|                | ... marking the status as scheduled?                              | AgendaEntry              | IE: owns the information necessary to manage its status.                                                                                                 |
|                | ... validating all data (local validation)?                       | AgendaEntry              | IE: owns its data.                                                                                                                                       |
| Step 4         |                                                                   |                          |                                                                                                                                                          |
| Step 5         | ... validating all data (global validation)?                      | Agenda                   | IE: knows all its agenda entries.                                                                                                                        |
|                | ... saving the created agenda entry?                              | Agenda                   | IE: owns all its agenda entries.                                                                                                                         |
| Step 6         | ... informing operation success?                                  | AddAgendaEntryUI         | IE: is responsible for user interactions.                                                                                                                |

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

* ToDoList
* ToDoListMapper
* ToDoListEntry
* Agenda
* AgendaEntry

Other software classes (i.e. Pure Fabrication) identified: 

* AddAgendaEntryUI
* ToDoListDto
* AddAgendaEntryController


## 3.2. Sequence Diagram (SD)

_**Note that SSD - Alternative Two is adopted.**_

### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](svg/us022-sequence-diagram-full.svg)

### Split Diagrams

The following diagram shows the same sequence of interactions between the classes involved in the realization of this user story, but it is split in partial diagrams to better illustrate the interactions between the classes.

It uses Interaction Occurrence (a.k.a. Interaction Use).

![Sequence Diagram - split](svg/us022-sequence-diagram-split.svg)

**Get To-Do List Entries with DTO**

![Sequence Diagram - Partial - Get To-Do List Entries with DTO](svg/us022-sequence-diagram-partial-get-to-do-list-entries-with-dto.svg)

**Get Task**

![Sequence Diagram - Partial - Get Task](svg/us022-sequence-diagram-partial-get-task.svg)

**Get Green Space**

![Sequence Diagram - Partial - Get Green Space](svg/us022-sequence-diagram-partial-get-green-space.svg)

**Create Agenda Entry**

![Sequence Diagram - Partial - Create Agenda Entry](svg/us022-sequence-diagram-partial-create-agenda-entry.svg)

**Add Agenda Entry**

![Sequence Diagram - Partial - Add Agenda Entry](svg/us022-sequence-diagram-partial-add-agenda-entry.svg)



## 3.3. Class Diagram (CD)

![Class Diagram](svg/us022-class-diagram.svg)
# US029 - Record the Completion of a Task

## 3. Design - User Story Realization

### 3.1. Rationale

_**Note that SSD - Alternative One is adopted.**_

| Interaction ID | Question: Which class is responsible for...                | Answer                 | Justification (with patterns)                                                                                                                     |
|:---------------|:-----------------------------------------------------------|:-----------------------|:--------------------------------------------------------------------------------------------------------------------------------------------------|
| Step 1         | ... interacting with the actor?                            | RecordTaskCompletionUI | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model.                                    |
|                | ... coordinating the US?                                   | RecordTaskCompletionController | Controller: coordinates the interactions related to recording task completion in the user interface (UI) and executes the logic needed to process these requests. |
|                | ... knowing the To-Do list Entries?                        | TaskRepository         | IE: knows how to access the data of tasks.                                                                                                      |
|                | ... create a list with all the data of tasks?              | TaskRepository         | IE: Transforms all of its data.                                                                                                                  |
|                | ... creating the Task DTO?                                 | TaskDto                | Pure Fabrication: a simple data structure to hold the task data for transfer.                                                                     |
|                | ... returning task DTOs to the UI?                         | RecordTaskCompletionController | Controller: coordinates the interaction and data flow between the UI and the model.                                                               |
| Step 2         |                                                            |                        |                                                                                                                                                   |
| Step 3         | ... knowing the task associated with the To-Do List entry? | Task                   | IE: Task is the most knowledgeable about its associated information.                                                                              |
|                | ... marking the task as completed?                         | Task                   | IE: owns the information necessary to manage its status.                                                                                         |
|                | ... validating the completion data (local validation)?     | Task                   | IE: owns its data.                                                                                                                               |
| Step 4         |                                                            |                        |                                                                                                                                                   |
| Step 5         | ... validating the completion data (global validation)?    | TaskRepository         | IE: knows all its tasks.                                                                                                                        |
|                | ... saving the updated task entry?                         | TaskRepository         | IE: owns all its tasks.                                                                                                                         |
| Step 6         | ... informing operation success?                           | RecordTaskCompletionUI | IE: is responsible for user interaction.                                                                                                         |

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are:

* Task
* TaskRepository
* RecordTaskCompletionController
* CollaboratorRepository

Other software classes (i.e., Pure Fabrication) identified:

* RecordTaskCompletionUI
* RecordTaskCompletionController
* TaskDto

## 3.2. Sequence Diagram (SD)

_**Note that SSD - Alternative Two is adopted.**_

### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![us029-sequence-diagram-full.svg](svg%2Fus029-sequence-diagram-full.svg)

### Split Diagrams

The following diagram shows the same sequence of interactions between the classes involved in the realization of this user story, but it is split in partial diagrams to better illustrate the interactions between the classes.

It uses Interaction Occurrence (a.k.a. Interaction Use).

![us029-sequence-diagram-split.svg](svg%2Fus029-sequence-diagram-split.svg) 

**Get Collaborator By Email**

![us029-sequence-diagram-partial-get-collaborator-by-email.svg](svg%2Fus029-sequence-diagram-partial-get-collaborator-by-email.svg)

**Get Task List**

![us029-sequence-diagram-partial-get-tasks-list.svg](svg%2Fus029-sequence-diagram-partial-get-tasks-list.svg)

**Mark Task As Completed**

![us029-sequence-diagram-partial-mark-task-as-completed.svg](svg%2Fus029-sequence-diagram-partial-mark-task-as-completed.svg)![Sequence Diagram - Partial - Get Green Space](svg/us022-sequence-diagram-partial-get-green-space.svg)

## 3.3. Class Diagram (CD)

![us029-class-diagram.svg](svg%2Fus029-class-diagram.svg)![Class Diagram](svg/us022-class-diagram.svg)
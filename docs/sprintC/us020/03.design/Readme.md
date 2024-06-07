# US020 -Register a green space

## 3. Design - User Story Realization

### 3.1. Rationale

_**Note that SSD - Alternative One is adopted.**_

| Interaction ID | Question: Which class is responsible for...                       | Answer                       | Justification (with patterns)                                                                                                                                     |
|:---------------|:------------------------------------------------------------------|:-----------------------------|:------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Step 1         | ... interacting with the actor?                                   | RegisterGreenSpaceUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model.                                                     |
|                | ... coordinating the US?                                          | RegisterGreenSpaceController | Controller: coordinates the interactions related to registering a green space in the user interface (UI) and executes the logic needed to process these requests. |
|                | ... knowing the list of Green Spaces?                             | GreenSpaceRegistry           | IE: knows how to access the data of green spaces.                                                                                                                 |
|                | ... create a list with all the data of green spaces?              | GreenSpaceMapper             | IE: Transforms all of its data.                                                                                                                                   |
|                | ... creating the Green Space DTO?                                 | GreenSpaceDto                | Pure Fabrication: a simple data structure to hold the list of green spaces for transfer.                                                                          |
|                | ... returning Green Space DTOs to the UI?                         | RegisterGreenSpaceController | Controller: coordinates the interaction and data flow between the UI and the model.                                                                               |
| Step 2         | ... instantiating a new Green Space?                              | GreenSpace                   | Creator: GreenSpace class is responsible for creating and managing its instances.                                                                                 |
|                | ... saving the inputted data?                                     | GreenSpaceRegistry           | IE: processes user input and generates a green space instance based on that information.                                                                          |
|                | ... validating all data (local validation)?                       | GreenSpace                   | IE: owns its data.                                                                                                                                                |
| Step 3         | ... validating all data (global validation)?                      | GreenSpaceRegistry           | IE: knows all its green spaces and ensures no duplicates.                                                                                                         |
|                | ... saving the created green space?                               | GreenSpaceRegistry           | IE: owns all its green spaces.                                                                                                                                    |
| Step 4         | ... informing operation success?                                  | RegisterGreenSpaceUI         | IE: is responsible for user interactions.                                                                                                                         |

### Systematization

According to the taken rationale, the conceptual classes promoted to software classes are:

* GreenSpace
* GreenSpaceMapper
* GreenSpaceRegistry
* GreenSpaceRepository


Other software classes (i.e. Pure Fabrication) identified:

* RegisterGreenSpaceUI
* RegisterGreenSpaceController

## 3.2. Sequence Diagram (SD)

_**Note that SSD - Alternative One is adopted.**_

### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![us020-sequence-diagram-full.svg](svg%2Fus020-sequence-diagram-full.svg)

## 3.3. Class Diagram (CD)

![us020-class-diagram.svg](svg%2Fus020-class-diagram.svg)

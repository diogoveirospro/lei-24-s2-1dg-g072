# US003 - Registration of an Employee

## 3. Design - User Story Realization

### 3.1. Rationale

_**Note that SSD - Alternative One is adopted.**_

| Interaction ID | Question: Which class is responsible for... | Answer                         | Justification (with patterns)                                                                                                                                     |
|:---------------|:--------------------------------------------|:-------------------------------|:------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Step 1         | ... interacting with the actor?             | RegisterCollaboratorUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model.                                                     |
|                | ... coordinating the US?                    | RegisterCollaboratorController | Controller: coordinates the interactions related to registering collaborators in the user interface (UI) and executes the logic needed to process these requests. |
|                | ... instantiating a new Job?                | CollaboratorRepository         | Creator (Rule 2): the Organisation registers a Skill instance.                                                                                                    |
| Step 2         |                                             |                                |                                                                                                                                                                   |
| Step 3         | ... saving the inputted data?               | Collaborator                   | IE: object created in step 1 has its own data.                                                                                                                    |
| Step 4         |                                             |                                |                                                                                                                                                                   |
| Step 5         | ... validating all data (local validation)? | Collaborator                   | IE: owns its data.                                                                                                                                                |
|                | ... validate all data (global validation)?  | CollaboratorRepository         | IE: knows all its collaborators.                                                                                                                                  |
|                | ... saving the created job?                 | CollaboratorRepository         | IE: owns all its collaborators.                                                                                                                                   |
| Step 6         | ... informing operation success?            | RegisterCollaboratorUI         | IE: is responsible for user interactions.                                                                                                                         |

### Systematization ##


According to the taken rationale, the conceptual classes promoted to software classes are:

* CollaboratorRepository
* Collaborator

Other software classes (i.e. Pure Fabrication) identified:

* RegisterCollaboratorUI
* RegisterCollaboratorController

## 3.2. Sequence Diagram (SD)


_**Note that SSD - Alternative Two is adopted.**_

### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](svg/us003-sequence-diagram-full.svg)

### Split Diagrams

The following diagram shows the same sequence of interactions between the classes involved in the realization of this user story, but it is split into partial diagrams to better illustrate the interactions between the classes.

It uses Interaction Occurrence (a.k.a. Interaction Use)

![Sequence Diagram - split](svg/us003-sequence-diagram-split.svg)

**Register Collaborator**

![Sequence Diagram - Partial - Register Collaborator](svg/us003-sequence-diagram-partial-register-collaborator.svg)


## 3.3. Class Diagram (CD)

![Class Diagram](svg/us003-class-diagram.svg)

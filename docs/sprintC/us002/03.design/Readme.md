# US002 - Register a Job 

## 3. Design - User Story Realization 

### 3.1. Rationale

_**Note that SSD - Alternative One is adopted.**_

| Interaction ID | Question: Which class is responsible for... | Answer                | Justification (with patterns)                                                                                                                            |
|:---------------|:--------------------------------------------|:----------------------|:---------------------------------------------------------------------------------------------------------------------------------------------------------|
| Step 1         | ... interacting with the actor?             | RegisterJobUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model.                                            |
| Step 2         |                                             |                       |                                                                                                                                                          |
| Step 3         |                                             |                       |                                                                                                                                                          |
| Step 4         |                                             |                       |                                                                                                                                                          |
| Step 5         | ... coordinating the US?                    | RegisterJobController | Controller: coordinates the interactions related to registering jobs in the user interface (UI) and executes the logic needed to process these requests. |
|                | ... instantiating a new Job?                | JobRepository         | Creator (Rule 2): the JobRepository registers a Job instance.                                                                                            |
|                | ... saving the inputted data?               | Job                   | IE: object created in step 1 has its own data.                                                                                                           |
|                | ... validating all data (local validation)? | Job                   | IE: owns its data.                                                                                                                                       |
|                | ... validate all data (global validation)?  | JobRepository         | IE: knows all its jobs.                                                                                                                                  |
|                | ... saving the created job?                 | JobRepository         | IE: owns all its jobs.                                                                                                                                   |
| Step 6         | ... informing operation success?            | RegisterJobUI         | IE: is responsible for user interactions.                                                                                                                |

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

* JobRepository
* Job

Other software classes (i.e. Pure Fabrication) identified: 

* RegisterJobUI  
* RegisterJobController


## 3.2. Sequence Diagram (SD)

_**Note that SSD - Alternative Two is adopted.**_

### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](svg/us002-sequence-diagram-full.svg)

### Split Diagrams

The following diagram shows the same sequence of interactions between the classes involved in the realization of this user story, but it is split in partial diagrams to better illustrate the interactions between the classes.

It uses Interaction Occurrence (a.k.a. Interaction Use).

![Sequence Diagram - split](svg/us002-sequence-diagram-split.svg)

**Register Job**

![Sequence Diagram - Partial - Register Job](svg/us002-sequence-diagram-partial-register-job.svg)

## 3.3. Class Diagram (CD)

![Class Diagram](svg/us002-class-diagram.svg)
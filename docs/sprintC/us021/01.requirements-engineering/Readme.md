# US006 - Add a New Entry to the To-Do List


## 1. Requirements Engineering

### 1.1. User Story Description

As a GSM, I want to add a new entry to the To-Do List.

### 1.2. Customer Specifications and Clarifications 

> The To-Do List comprises all the tasks required to be done in order to assure the proper functioning of the parks. These tasks can be regular (e.g., pruning trees) or occasional (e.g., repairing a broken piece of equipment). They may also require a multi-disciplinary team, and the length of the task can vary from a few minutes (e.g., replacing a light bulb) to weeks (e.g., installing an irrigation system).
>
> The entries in this list describe the required task, the degree of urgency (High, Medium, and Low), and the approximate expected duration.

**From the client clarifications:**

> **Question:** 
> 
> **Answer:** 
 
> **Question:** 
> 
> **Answer:** 


### 1.3. Acceptance Criteria

* **AC1:** The new entry must be associated with a green space managed by the GSM.
* **AC2:** The green space for the new entry should be chosen from a list presented to the GSM.

### 1.4. Found out Dependencies

There is a dependency on **US020 - Register a Green Space and its Respective Area**, since the To-Do List entries must be associated with a green space.

### 1.5 Input and Output Data

**Input Data:**

* Typed data:
  * Task description
  * Degree of urgency (High, Medium, Low)
  * Approximate expected duration
  * The green space associated with the task

**Output Data:**

* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)

**_Other alternatives might exist._**

#### Alternative One

![System Sequence Diagram - Alternative One](svg/us021-system-sequence-diagram-alternative-one-System_Sequence_Diagram__SSD____Alternative_One.svg)

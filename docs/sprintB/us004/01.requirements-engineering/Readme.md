# US004 - Assign one or more Skills to a Collaborator 


## 1. Requirements Engineering

### 1.1. User Story Description

As an HRM, I want to assign one or more skills to a collaborator.

### 1.2. Customer Specifications and Clarifications

**From the client clarifications:**

> **Question:** Can a collaborator have no skills assigned?
>
> **Answer:** Yes.

> **Question:** Can any skill be registered to any collaborator/job? Or should they be associated in categories in association with a specific job?
>
> **Answer:** There is no association, it totally depends on the CV of the collaborator.

> **Question:** Should it be possible to add the same skill to a collaborator multiple times?
> 
> **Answer:** That does not make sense.

> **Question:** Is there any certification/proof needed to register a skill to a collaborator?
> 
> **Answer:** No.

> **Question:** Is there a minimum and maximum number of skills?
>
> **Answer:** No.

> **Question:** Are there any special characteristics that collaborators need to have to be given these skills?
>
> **Answer:** No 


### 1.3. Acceptance Criteria

* **AC1:** The system should display a list of available competences for selection.
* **AC2:** HRM should be able to select one or more competences from the list and assign them to the collaborator.
* **AC3:** HRM should be able to remove assigned competences from the collaborator's profile if necessary.
* **AC4:** Once competences are assigned or removed, the system should update the collaborator's profile with the changes.
* **AC5:** The system should provide feedback to HRM confirming the success or failure of the competence assignment operation

### 1.4. Found out Dependencies

* Dependency on US001: Requires competences to be previously registered in the system to be available for assignment.
* Dependency on US003: Collaborator must be registered in the system before competences can be assigned to them.

### 1.5 Input and Output Data

**Input Data:**

* Selected data:
    * skills to be assigned to the collaborator

**Output Data:**

* Updated collaborator profile
* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)

**_Other alternatives might exist._**

#### Alternative One

![System Sequence Diagram - Alternative One](svg/us004-system-sequence-diagram-alternative-one.svg)

#### Alternative Two

![System Sequence Diagram - Alternative Two](svg/us004-system-sequence-diagram-alternative-two.svg)

### 1.7 Other Relevant Remarks
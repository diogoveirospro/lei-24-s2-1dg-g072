# US004 - Assigning competences to an employee 


## 1. Requirements Engineering

### 1.1. User Story Description

As an HRM, I want to assign one or more skills to a collaborator.

### 1.2. Customer Specifications and Clarifications 

**From the specifications document:**

>	

>	

**From the client clarifications:**

> **Question:** Na US04 é relativa à associação de skills a um colaborador. A minha questão é: 1. se há um número mínimo e máximo de número de skills; 2. se há alguma característica especial que seja necessária o colaborar ter para que lhe sejam adicionadas estas skills.

>
> **Answer:** 1. Não; 2. Não.

> **Question:* *1 -Is there a maximum number of skills a collaborator can be added to?
2- Can more skills be added over time?
3- Can there be collaborators without associated skills?
>
> **Answer:** 

> **Question:** Regarding registering a skill, we have a couple of questions: Can any skill be registered to any collaborator/job? Or should they be associated in categories in association with a specific job; Should it be possible to add the same skill to a collaborator multiple times?; Is there any certification/proof needed to register a skill to a colaborator?
>
> **Answer:** -There is no association, it totally depends of the CV of the collaborator.
-That does not make sense -no.

> **Question:**
>
> **Answer:**



### 1.3. Acceptance Criteria

* **AC1:** All required fields must be filled in.
* **AC2:** The task reference must have at least 5 alphanumeric characters.
* **AC3:** When creating a task with an existing reference, the system must reject such operation and the user must be able to modify the typed reference.

### 1.4. Found out Dependencies

* There is a dependency on "US003 - Create a task category" as there must be at least one task category to classify the task being created.

### 1.5 Input and Output Data

**Input Data:**

* Typed data:
    * a reference
    * a designation 
    * an informal description
    * a technical description
    * an estimated duration
    * an estimated cost
	
* Selected data:
    * a task category 

**Output Data:**

* List of existing task categories
* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)

**_Other alternatives might exist._**

#### Alternative One

![System Sequence Diagram - Alternative One](svg/us006-system-sequence-diagram-alternative-one.svg)

#### Alternative Two

![System Sequence Diagram - Alternative Two](svg/us006-system-sequence-diagram-alternative-two.svg)

### 1.7 Other Relevant Remarks

* The created task stays in a "not published" state in order to distinguish from "published" tasks.
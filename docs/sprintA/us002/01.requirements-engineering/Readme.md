# US002 - Register a Profession 


## 1. Requirements Engineering

### 1.1. User Story Description

As a GRH I want to register a profession.

### 1.2. Customer Specifications and Clarifications 

**From the specifications document:**

>	Each task is characterized by having a unique reference per organization, a designation, an informal and a technical description, an estimated duration and cost, as well as a task category. 

>	As long as it is not published, access to the task is exclusive to the employees of the respective organization. 

**From the client clarifications:**

> **Question:** Which is the unit of measurement used to estimate duration?
>
> **Answer:** Duration is estimated in days.

> **Question:** Monetary data is expressed in any particular currency?
>
> **Answer:** Monetary data (e.g. estimated cost of a task) is indicated in POT (virtual currency internal to the platform).

### 1.3. Acceptance Criteria

* **AC1:** All required fields must be filled in.
* **AC2:** The task reference must have at least 5 alphanumeric characters.
* **AC3:** When creating a task with an existing reference, the system must reject such operation and the user must be able to modify the typed reference.

### 1.4. Found out Dependencies

* There is a dependency on "US001 - Create a competence because there has to be at least one competence to assign to the profession we are creating

### 1.5 Input and Output Data

**Input Data:**

* Typed data:
    * a designation 
    * an informal description
    * a technical description
    * a medium salary
	
* Selected data:
    * a level of education required
    * required competences
    * a sector of activity

**Output Data:**

* List of all registered professions
* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)

**_Other alternatives might exist._**

#### Alternative One

![System Sequence Diagram - Alternative One](svg/us002-system-sequence-diagram-alternative-one.svg)

#### Alternative Two

![System Sequence Diagram - Alternative Two](svg/us002-system-sequence-diagram-alternative-two.svg)

#### Alternative Three

![System Sequence Diagram - Alternative Three](svg/us002-system-sequence-diagram-alternative-three.svg)

### 1.7 Other Relevant Remarks


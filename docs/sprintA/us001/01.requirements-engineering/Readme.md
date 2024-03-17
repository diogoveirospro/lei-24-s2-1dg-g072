# US001 - Register skills


## 1. Requirements Engineering

### 1.1. User Story Description

As a HRM I want to record competences that can be attributed to an employee.

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

* **AC1:** The HRM needs to write at least one competence.
* **AC2:** It must be a String.
* **AC3:** If it is not a String ask again for competences.
### 1.4. Found out Dependencies

* There are no dependencies in other US.

### 1.5 Input and Output Data

**Input Data:**

* Typed data:
    * a competence
  
	
* Selected data:
    * array with the information of the competences

**Output Data:**

* Information of the competences that were recorded!
* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)



#### Alternative One

![System Sequence Diagram - Alternative One](svg/us001-system-sequence-diagram-alternative-one.svg)

#### Alternative Two

![System Sequence Diagram - Alternative Two](svg/us001-system-sequence-diagram-alternative-two.svg)


### 1.7 Other Relevant Remarks

* The data acquired in this US (competences), will be saved in an array so then it can be used later in  other US.
# US008 - List vehicles that need to be serviced 


## 1. Requirements Engineering

### 1.1. User Story Description

As an FM, I want to list the vehicles needing the check-up.

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

* **AC1:** There must be atleast one vehicle that is listed to do a check-up.


### 1.4. Found out Dependencies

* There is a dependency on "US007 - Register a vehicle's check-up" as there must be at least one vehicle registered to being in need of a check-up.

### 1.5 Input and Output Data

**Input Data:**

* Typed data:
    
  
	
* Selected data:
    * Vehicles that need a check-up. 

**Output Data:**

* List of cars to the check-up
* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)


#### Alternative One

![System Sequence Diagram - Alternative One](svg/us008-system-sequence-diagram-alternative-one.svg)

### 1.7 Other Relevant Remarks

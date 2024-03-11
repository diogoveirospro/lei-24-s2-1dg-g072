# US007 - Register the inspection of a vehicle 


## 1. Requirements Engineering

### 1.1. User Story Description

The VPM wants to register the inspection of a vehicle.

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
* **AC2:** The chosen vehicle must be a registered vehicle.

### 1.4. Found out Dependencies

* There is a dependency on "US006 - Register a vehicle" as there must be at least one vehicle registered so that it can be inspected.

### 1.5 Input and Output Data

**Input Data:**

* Typed data:
    * a designation of the registered vehicle
    * an estimated cost

**Output Data:**

* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)

**_Other alternatives might exist._**

#### Alternative One

![System Sequence Diagram - Alternative One](svg/us007-system-sequence-diagram-alternative-one.svg)

#### Alternative Two

![System Sequence Diagram - Alternative Two](svg/us007-system-sequence-diagram-alternative-two.svg)

### 1.7 Other Relevant Remarks

* The created task stays in a "not published" state in order to distinguish from "published" tasks.
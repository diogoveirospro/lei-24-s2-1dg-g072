# US005 - Generate a Team Proposal 


## 1. Requirements Engineering

### 1.1. User Story Description

As GRH I want to automatically generate a team proposal.

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

* **AC1:** The desired number of employees must be provided by the GRH.
* **AC2:** The desired set of competences must be provided by the GRH.

### 1.4. Found out Dependencies

* There is a dependency on **"US003 - Registration of an employee with profession and attributes"** since it needs information about the employees, such as experience and skills. This allows the system to select the appropriate employees to form the team.
* There is a dependency on **"US004 - Assigning competences to an employee"** since competences need to be assigned to employees in the system. The system will have to take the relevant competences into account when forming teams.

### 1.5 Input and Output Data

**Input Data:**

* Typed data:
    * a designation
    * a detailed task description
    * an estimated duration
    * a deadline
	
* Selected data:
    * a task category
    * task requirements

**Output Data:**

* (In)Success of the operation
* A team recommendation

### 1.6. System Sequence Diagram (SSD)

**_Other alternatives might exist._**

#### Alternative One

![System Sequence Diagram - Alternative One](svg/us005-system-sequence-diagram-alternative-one.svg)

#### Alternative Two

![System Sequence Diagram - Alternative Two](svg/us005-system-sequence-diagram-alternative-two.svg)

#### Alternative Three

![System Sequence Diagram - Alternative Three](svg/us005-system-sequence-diagram-alternative-three.svg)

### 1.7 Other Relevant Remarks

* The GRH can change the team's recommendation and will be notified if none of the members have the necessary skills to carry out the task.
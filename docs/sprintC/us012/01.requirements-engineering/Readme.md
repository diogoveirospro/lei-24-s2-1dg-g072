# US012 - Import .csv file  

## 1. Requirements Engineering

### 1.1. User Story Description

As a GSM, I want to import a .csv file containing lines with Water Point X, Water Point Y, and Distance so that I can have all possible routes that can be opened to lay pipes between each pair of water points, including their respective installation costs.

### 1.2. Customer Specifications and Clarifications

**From the client clarifications:**

> **Question:**  
>
> **Answer:**  

> **Question:**  
>
> **Answer:** 

> **Question:**  
>
> **Answer:**  

### 1.3. Acceptance Criteria

* **AC1:** The system must be able to import a CSV file with the specified format.
* **AC2:** Any duplicate or malformed entries should be reported to the user.
* **AC3:** Valid entries should be stored in a unique data structure optimized for further operations like calculating the optimal routing

### 1.4. Found out Dependencies

* US010 - Equipment Usage Analysis: Provides insights into the usage patterns of different equipment in the park, influencing decisions related to water consumption and irrigation system installation.
* US011 - Park Use Data Collection: Collects demographic data about park users, including age groups and visit frequency, which can be correlated with water consumption data to identify usage trends and preferences.

### 1.5. Input and Output Data

**Input Data:**

* A CSV file with entries formatted as:
    * Water Point X, Water Point Y, Distance

**Output Data:**

* A confirmation message about the successful import or details about any issues encountered during the process.
* A data structure containing all validated routes and their associated costs.

### 1.6. System Sequence Diagram (SSD)

**_Other alternatives might exist._**

#### Alternative One

![System Sequence Diagram - Alternative One](svg/us0012-system-sequence-diagram-alternative-one.svg)

#### Alternative Two

![System Sequence Diagram - Alternative Two](svg/us005-system-sequence-diagram-alternative-two.svg)

#### Alternative Three

![System Sequence Diagram - Alternative Three](svg/us005-system-sequence-diagram-alternative-three.svg)

### 1.7 Other Relevant Remarks

* The HRM can change the team's recommendation and will be notified if none of the members have the necessary skills to carry out the task.
# US010 - Find which piece of equipment is used each day

## 1. Requirements Engineering

### 1.1. User Story Description

As a GSM (Garden Services Manager), I want to know which piece(s) of equipment are used each day so that I can understand the users’ preferences.

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

* **AC1:** The system must allow users to select the equipment they used from a list on an electronic device at the park exit.
* **AC2:** The system must be able to record and store these selections in a file named "EquipmentUsed.csv".
* **AC3:** The system should be able to generate a pie chart from "EquipmentUsed.csv" showing the percentage usage of each piece of equipment.

### 1.4. Found Dependencies

* **US09** - Provides context for understanding water usage patterns, which may influence equipment usage analysis in US10
* **US12** - Necessary if analyzing water distribution routes based on equipment usage patterns.  
* **US13** - Required for optimizing water distribution routes, ensuring efficient resource allocation based on equipment usage.   

### 1.5. Input and Output Data

**Input Data:**

* Selections by users from the electronic device:
  * Walking paths
  * Children’s playground
  * Picnic area
  * Exercise machines (gymnastics equipment)

**Output Data:**

* Updated records in "EquipmentUsed.csv".
* A pie chart representing the usage percentages for each piece of equipment.


### 1.6. System Sequence Diagram (SSD)

**_Other alternatives might exist._**

#### Alternative One

![System Sequence Diagram - Alternative One](svg/us005-system-sequence-diagram-alternative-one.svg)

#### Alternative Two

![System Sequence Diagram - Alternative Two](svg/us005-system-sequence-diagram-alternative-two.svg)

#### Alternative Three

![System Sequence Diagram - Alternative Three](svg/us005-system-sequence-diagram-alternative-three.svg)

### 1.7 Other Relevant Remarks

* Ensuring the accuracy and reliability of the data collection via the electronic device is crucial for generating meaningful analytics.
* The system might need to handle instances where the device is offline or malfunctioning, possibly queueing data to be sent when connectivity is restored.
# US013 - Algorithm that returns routes to be opened 


## 1. Requirements Engineering

### 1.1. User Story Description

As a GSM, I want to apply an algorithm that returns the routes
to be opened and pipes needed to be laid with a minimum accumulated
cost, ensuring that all points are adequately supplied.

### 1.2. Customer Specifications and Clarifications 

**From the client clarifications:**

> **Question:** 
>
> **Answer:** 


### 1.3. Acceptance Criteria

* **AC1:** All implemented procedures must only use primitive operations, and not existing functions in JAVA libraries.

### 1.4. Found out Dependencies

* There is a dependency on **US012 - Import .csv file** since it needs information about all possible routes that can be
  opened to lay pipes between each pair of water points, and their respective installation costs. This allows the system to select the pipes needed to be laid with a minimum accumulated
  cost, ensuring that all points are adequately supplied.

### 1.5 Input and Output Data

* Input data:	
* Selected data:
    * All possible routes that can be
      opened to lay pipes between each pair of water points, and their respective installation costs

**Output Data:**

* (In)Success of the operation
*  A topographic survey, of the best and least expensive pipelines between water points 

### 1.6. System Sequence Diagram (SSD)

**_Other alternatives might exist._**

#### Alternative One

![System Sequence Diagram - Alternative One](svg/us013-system-sequence-diagram-alternative-one.svg)


### 1.7 Other Relevant Remarks


# US014 - Run tests for the inputs of variable size 


## 1. Requirements Engineering

### 1.1. User Story Description

As a QAM, I want to run tests for inputs of variable size, to
observe the asymptotic behavior of the execution time of the US13
algorithm.

### 1.2. Customer Specifications and Clarifications 

**From the client clarifications:**

> **Question:** 
> 
> **Answer:**


### 1.3. Acceptance Criteria

* **AC1:** The graphic referring to the asymptotic behavior of the
  execution running time tests should be presented in a time unit
  that allows to distinguish the running times of all tested examples.

### 1.4. Found out Dependencies

* There is a dependency on **US013 - Algorithm that returns routes to be opened** since it needs the algorithm to apply those tests, as he will use it to find the time that was taken to create the topographic survey .
* There is a dependency on **US012 - Import .csv file** since it needs multiple files with the inputs to visualize the time taken, to different amounts of inputs. 
### 1.5 Input and Output Data

**Input Data:**
  * File with all possible routes that can be
  opened to lay pipes between each pair of water points, and their respective installation costs.
* Selected data:

**Output Data:**

* (In)Success of the operation
* Graph of the time taken by size of the topographic survey

### 1.6. System Sequence Diagram (SSD)

**_Other alternatives might exist._**

#### Alternative One

![System Sequence Diagram - Alternative One](svg/us014-system-sequence-diagram-alternative-one.svg)



### 1.7 Other Relevant Remarks


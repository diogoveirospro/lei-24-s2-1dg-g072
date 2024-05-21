# US020 -Register a green space

## 1. Requirements Engineering

### 1.1. User Story Description

As a Green Space Manager (GSM), I want to register a green
space (garden, medium-sized park or large-sized park) and its respective
area

### 1.2. Customer Specifications and Clarifications 

**From the project statement document:**

>	

**From the client clarifications:**

> **Question:** 
> 
> **Answer:** 
 
> **Question:** 
> 
> **Answer:** 


### 1.3. Acceptance Criteria

* **AC1:** The system must allow the GSM to register a green space with its name.
* **AC2:** The system must prompt the GSM to provide the area of the green space in square meters during the registration process.

### 1.4. Found out Dependencies

* There is a dependency on **US027 - List GreenSpaces**  since the green spaces need to be registered before they can be listed.


### 1.5 Input and Output Data

**Input Data:**

* Typed data:
    * Name of the Green Space
    * Type of Green Space (garden, medium-sized park, large-sized park)
    * Area (in square meters)

* Selected data:
    * ... 

**Output Data:**

* (In)Success of the operation
* List of all registered jobs

### 1.6. System Sequence Diagram (SSD)

**_Other alternatives might exist._**

#### Alternative One

![System Sequence Diagram - Alternative One](svg/us020-system-sequence-diagram-alternative-one.svg)

# US025 - Cancel entry in the agenda 


## 1. Requirements Engineering

### 1.1. User Story Description

The GSM wants to cancel an entry in the Agenda.

### 1.2. Customer Specifications and Clarifications 

**From the project statement document:**

>	The vehicle as the following attributes: Plate number, Brand, Model, Type, Tare, Gross weight, Current Kms, Registration Date, Acquisition Date and Service Frequency (in Kms). 

**From the client clarifications:**

> **Question:** When a vehicle is registered, are there specific requirements for accepting the brand? For example, does the system need to check if the brand is on a predetermined list? Does this also apply to the model or any other characteristics?
> 
> **Answer:** No; one can consider a list of brands and a list of models previously inserted in the system, no need to go through validations.
 
> **Question:** For the application to work does the FM need to fill all the attributes of the vehicle?
> 
> **Answer:** Yes.


### 1.3. Acceptance Criteria

* **AC1:** A canceled task should not be deleted but rather change its state.

### 1.4. Found out Dependencies

* One dependence with US022 has there must be an entry to cancel.

### 1.5 Input and Output Data

**Input Data:**

* Typed data:
  * 
   
* Selected data:
  * the entry to cancel.

**Output Data:**

* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)

**_Other alternatives might exist._**

#### Alternative One

![System Sequence Diagram - Alternative One](svg/us006-system-sequence-diagram-alternative-one.svg)

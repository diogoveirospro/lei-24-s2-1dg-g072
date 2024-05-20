# US029 - Register a Vehicle 


## 1. Requirements Engineering

### 1.1. User Story Description

The VFM wants to register a vehicle.

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

* **AC1:** The attributes used to describe a vehicle are: Plate number, Brand, Model, Type, Tare, Gross weight, Current Kms, Registration Date, Acquisition Date and Service Frequency (in Kms).
* **AC2:** All required fields must be filled.

### 1.4. Found out Dependencies

* No dependencies were found.

### 1.5 Input and Output Data

**Input Data:**

* Typed data:
    * a designation for the vehicle (plate number)
    * the attributes of the vehicle

**Output Data:**

* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)

**_Other alternatives might exist._**

#### Alternative One

![System Sequence Diagram - Alternative One](svg/us006-system-sequence-diagram-alternative-one.svg)

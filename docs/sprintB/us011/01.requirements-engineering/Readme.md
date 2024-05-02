# US011 - Collect data from user portal 


## 1. Requirements Engineering

### 1.1. User Story Description

The GSM wants to collect data from the user portal about the use of the park, in order to 
understand the use of the park by different age groups.

### 1.2. Customer Specifications and Clarifications 

**From the project statement document:**

>   There's three age groups, child (up to 15 years old), adult (between 16 and 65 years old), senior (over 65 years old).
>   There's three questions, age range, would the user recommend the park to others and how many times does the user visit the park per month.


**From the client clarifications:**

> **Question:** Vai nos ser fornecido algum ficheiro por exemplo Inquiry.csv com os dados do inquérito e apartir daí fazer o que está no enunciado. Ou é suposto também o programa coletar a informação (os dados do inquérito)?
>
> **Answer:** Os resultados do inquérito já estão registados num ficheiro .csv.


### 1.3. Acceptance Criteria

* **AC1:** 
* **AC2:** 

### 1.4. Found out Dependencies

* No dependencies were found. 

### 1.5 Input and Output Data

**Input Data:**

* Typed data:
    * Number of times that the user visits the park per month.
    * The answer to age range (1 to 3)
    * If the user recommends the park to others (Y/N)


**Output Data:**

* (In)Success of the operation
* Inquiry.csv file with the responses

### 1.6. System Sequence Diagram (SSD)

**_Other alternatives might exist._**

#### Alternative One

![System Sequence Diagram - Alternative One](svg/us011-system-sequence-diagram-alternative-one.svg)
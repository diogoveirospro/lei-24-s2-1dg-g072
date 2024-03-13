# US003 - Registration of an employee

## 1. Requirements Engineering

### 1.1. User Story Description

As an HRM, I want to register a collaborator with a job and fundamental characteristics.

### 1.2. Customer Specifications and Clarifications

**From the specifications document:**

> ...

**From the client clarifications:**

> **Question:** Na User Story 03 é mencionado o termo 'características fundamentais'. A minha questão é, precisamente, quais são estas características.
>
> **Answer:** Os dados essenciais do colaborador mínimos serão nome, data de nascimento, data de admissão, morada, contacto (telefonico e email), documento de identificação e o seu número. Podem considerar outros que entendam ser relevantes.
>
> **Question:** When creating a collaborator with an existing name ... What the system do? What characteristics are important to success the register?
>
> **Answer:** ...

### 1.3. Acceptance Criteria

* **AC1:** All required fields must be filled in.
* **AC2:** The task reference must have at least 5 alphanumeric characters.
* **AC3:** When creating a task with an existing reference, the system must reject such operation and the user must be able to modify the typed reference.

### 1.4. Found out Dependencies

* There is a dependency on "US003 - Create a task category" as there must be at least one task category to classify the task being created.

### 1.5 Input and Output Data

**Input Data:**

* Typed data:
    * a reference
    * a designation 
    * an informal description
    * a technical description
    * an estimated duration
    * an estimated cost
	
* Selected data:
    * a task category 

**Output Data:**

* List of existing task categories
* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)

**_Other alternatives might exist._**

#### Alternative One

![System Sequence Diagram - Alternative One](svg/us006-system-sequence-diagram-alternative-one.svg)

#### Alternative Two

![System Sequence Diagram - Alternative Two](svg/us006-system-sequence-diagram-alternative-two.svg)

### 1.7 Other Relevant Remarks

* The created task stays in a "not published" state in order to distinguish from "published" tasks.
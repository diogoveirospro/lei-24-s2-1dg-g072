# US026 - Assign one or more Vehicles to an entry 


## 1. Requirements Engineering

### 1.1. User Story Description

The GSM wants to assign one or more vehicles to an entry int the agenda.

### 1.2. Customer Specifications and Clarifications 

**From the project statement document:**


**From the client clarifications:**

> **Question:** Should each GSM only be able to assign vehicles to its own entries or every GSM can assign vehicles to every entry, even if the green space associated with the task is not registered with their email?
> 
> **Answer:** For the sake of simplicity, you can assume that GSM will only manage its Agenda Entries.

> **Question:** Is the number of vehicles to be assigned provided by the Green Spaces Manager?
> 
> **Answer:** There is no specification concerning the number of vehicles, is upt to GSM decide what vehicles the task needs.

> **Question:** Todos os veículos da empresa devem estar disponíveis para os atribuir a uma entrada da agenda, ou só os veículos com a manutenção em dia?
>
> **Answer:** Todos os veiculos que não estejam assignados a uma tarefa no mesmo periodo.



### 1.3. Acceptance Criteria

* **AC1:** The vehicle mustn't be assigned to another entry in the same period.

### 1.4. Found out Dependencies

* One dependence with US022 has there must be an entry to assign vehicles.

### 1.5 Input and Output Data

**Input Data:**

* Typed data:
* Selected data:
  * The entry to assign a vehicle 
  * Vehicles to assign

**Output Data:**

* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)

**_Other alternatives might exist._**

#### Alternative One

![System Sequence Diagram - Alternative One](svg/us026-system-sequence-diagram-alternative-one.svg)

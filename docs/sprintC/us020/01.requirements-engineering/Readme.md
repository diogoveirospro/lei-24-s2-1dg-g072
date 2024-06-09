# US020 -Register a green space

## 1. Requirements Engineering

### 1.1. User Story Description

As a Green Space Manager (GSM), I want to register a green
space (garden, medium-sized park or large-sized park) and its respective
area

### 1.2. Customer Specifications and Clarifications 

**From the project statement document:**

> The green space (garden, medium-sized park, or large-sized park) must have an associated name and area.

**From the client clarifications:**

> **Question:** To register a green space, what is the criteria needed to classify it as a medium-sized park or a large-sized park?
> 
> **Answer:** It's a GSM responsability to decide the classification.

> **Question:** In the registration of a green space, should a green space's name be allowed to contain digits and special characters, or just letters and whitespaces?
> 
> **Answer:** Same rules for other names in the business, letters, spaces and dashes.

> **Question:** In view of the description of GreenSpaces does it make sense to ask for optional mind for the different types this data? 
>
> **Answer:** In the current version, it is sufficient to define a park using name, size classification, area (hectare) and address.

> **Question:** Should the Green Space have any field in which it would store the GSM who created it, meaning that green space is managed by that GSM?
>
> **Answer:** I have no knowledge about data models.

> **Question:** I would like to know between what ranges of hectares a green space is classified as garden, medium or large, or if it is possible to register 2 green spaces with the same area but in different typology, depending on the GSM it registers.
>
> **Answer:** The classification is not automatic, it's up to GSM decide about it.

> **Question:** In which unit should the area be measured in? Can the GSM register multiple green spaces at once?
>
> **Answer:** Usually, areas are measured in hectares. That's a matter of UX/UI, each dev team can decide about it.

### 1.3. Acceptance Criteria

* **AC1:** The green space must have a unique name.
* **AC2:** The green space must have a defined area entered as a numeric value.
* **AC3:** The type of green space must be selected from a predetermined list (garden, medium-sized park, large-sized park).

### 1.4. Found out Dependencies

* There is a dependency on **US027 - List GreenSpaces**  since the green spaces need to be registered before they can be listed.


### 1.5 Input and Output Data

**Input Data:**

* Typed data:
    * Name of the Green Space
    * Type of Green Space (garden, medium-sized park, large-sized park)
    * Area (in square meters)

**Output Data:**

* (In)Success of the operation
* List of all registered jobs

### 1.6. System Sequence Diagram (SSD)

**_Other alternatives might exist._**

#### Alternative One

![System Sequence Diagram - Alternative One](svg/us020-system-sequence-diagram-alternative-one.svg)

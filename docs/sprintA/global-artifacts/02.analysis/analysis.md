# OO Analysis

The construction process of the domain model is based on the client specifications, especially the nouns (for _concepts_) and verbs (for _relations_) used.

## Rationale to identify domain conceptual classes
To identify domain conceptual classes, start by making a list of candidate conceptual classes inspired by the list of categories suggested in the book "Applying UML and Patterns: An Introduction to Object-Oriented Analysis and Design and Iterative Development."


### _Conceptual Class Category List_

**Business Transactions**

* Job

---

**Transaction Line Items**

* 

---

**Product/Service related to a Transaction or Transaction Line Item**

* Skill

---

**Transaction Records**

* 

---  

**Roles of People or Organizations**

* Collaborator
* Vehicle and Equipment Fleet Manager (VFM)
* Green Spaces Manager (GSM)
* Green Spaces User (GSU)
* Human Resources Manager (HRM)

---

**Places**

* Green Space
* Garden
* Medium-sized park
* Large-sized park

---

**Noteworthy Events**

* 

---

**Physical Objects**

* Vehicle
* Machine
* Equipment

---

**Descriptions of Things**

* 

---

**Catalogs**

* 

---

**Containers**

* Team
* Vehicles Check-Up

---

**Elements of Containers**

* 

---

**Organizations**

* Organization

---

**Other External/Collaborating Systems**

* 

---

**Records of finance, work, contracts, legal matters**

* 

---

**Financial Instruments**

* 

---

**Documents mentioned/used to perform some work/**

* 

---


## Rationale to identify associations between conceptual classes

An association is a relationship between instances of objects that indicates a relevant connection, and that is worth of remembering, or it is derivable from the List of Common Associations:

- **_A_** is physically or logically part of **_B_**
- **_A_** is physically or logically contained in/on **_B_**
- **_A_** is a description for **_B_**
- **_A_** known/logged/recorded/reported/captured in **_B_**
- **_A_** uses or manages or owns **_B_**
- **_A_** is related with a transaction (item) of **_B_**
- etc.


| Concept (A) 		                            | Association   	 |       Concept (B) |
|-------------------------------------------|:---------------:|------------------:|
| Human Resources Manager (HRM)             |      is a       |      Collaborator |
| Human Resources Manager (HRM)             |    creates a    |             Skill |
| Human Resources Manager (HRM)             |    creates a    |               Job |
| Human Resources Manager (HRM)             |    creates a    |      Collaborator |
| Human Resources Manager (HRM)             |     assigns     |             Skill |
| Collaborator                              |       has       |            Skills |
| Human Resources Manager (HRM)             |    creates a    |              Team |
| Collaborator                              |      has a      |               Job |
| Collaborator                              |    works for    |      Organization |
| Team                                      |       has       |     Collaborators |
| Green Space                               |       has       |     Collaborators |
| Organization                              |      owns       |       Green Space |
| Garden                                    |      is a       |       Green Space |
| Medium-sized park                         |      is a       |       Green Space |
| Large-sized park                          |      is a       |       Green Space |
| Vehicle and Equipment Fleet Manager (VFM) |      is a       |      Collaborator |
| Vehicle and Equipment Fleet Manager (VFM) |   register a    |           Vehicle |
| Organization                              |      owns       |          Vehicles |
| Green Space                               |       has       |          Vehicles |
| Vehicle and Equipment Fleet Manager (VFM) |     chooses     |          Vehicles |
| Vehicle and Equipment Fleet Manager (VFM) |      lists      | Vehicles Check-Up |
| Vehicles Check-Up                         |       has       |          Vehicles |
| Green Spaces Manager (GSM)                |      is a       |      Collaborator |
| Green Spaces User (GSU)                   |      is a       |      Collaborator |



## Domain Model


![Domain Model](svg/project-domain-model.svg)
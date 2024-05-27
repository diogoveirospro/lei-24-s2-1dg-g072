# OO Analysis

The construction process of the domain model is based on the client specifications, especially the nouns (for _concepts_) and verbs (for _relations_) used.

## Rationale to identify domain conceptual classes
To identify domain conceptual classes, start by making a list of candidate conceptual classes inspired by the list of categories suggested in the book "Applying UML and Patterns: An Introduction to Object-Oriented Analysis and Design and Iterative Development."


### _Conceptual Class Category List_

**Business Transactions**

* Job
* Skill
* Vehicle
* Task

---

**Transaction Line Items**

* Maintenance
* Entry

---

**Product/Service related to a Transaction or Transaction Line Item**

* AgendaEntry
* ToDoListEntry


---

**Transaction Records**

* Agenda
* ToDoList

---  

**Roles of People or Organizations**

* Collaborator
* Vehicle and Equipment Fleet Manager (VFM)
* Green Spaces Manager (GSM)
* Human Resources Manager (HRM)

---

**Places**

* Green Space

---

**Noteworthy Events**

* AgendaEntry
* ToDoListEntry
* Task
* Entry

---

**Physical Objects**

* Vehicle

---

**Descriptions of Things**

* Skill
* Job
* Green Space
* Vehicle
* Maintenance

---

**Catalogs**

* Agenda
* ToDoList
* Maintenance

---

**Containers**

* Team
* Maintenance
* Agenda
* ToDoList

---

**Elements of Containers**

* Collaborator
* Task
* ToDoListEntry
* AgendaEntry
* Vehicle

---

**Organizations**


---

**Other External/Collaborating Systems**

* 

---

**Records of finance, work, contracts, legal matters**

* Maintenance
* Task
* ToDoListEntry
* AgendaEntry

---

**Financial Instruments**

* 

---

**Documents mentioned/used to perform some work/**

* 

---

**Please note that some classes may fit into multiple categories depending on the context and interpretation. The above categorization is based on the provided PlantUML diagram and the existing categories in the table.**
## Rationale to identify associations between conceptual classes

An association is a relationship between instances of objects that indicates a relevant connection, and that is worth of remembering, or it is derivable from the List of Common Associations:

- **_A_** is physically or logically part of **_B_**
- **_A_** is physically or logically contained in/on **_B_**
- **_A_** is a description for **_B_**
- **_A_** known/logged/recorded/reported/captured in **_B_**
- **_A_** uses or manages or owns **_B_**
- **_A_** is related with a transaction (item) of **_B_**
- etc.

| Concept (A)                               |   Association   |       Concept (B) |
|-------------------------------------------|:---------------:|------------------:|
| Human Resources Manager (HRM)             |      is a       |      Collaborator |
| Human Resources Manager (HRM)             |   registers a   |             Skill |
| Human Resources Manager (HRM)             |   registers a   |               Job |
| Human Resources Manager (HRM)             |   registers a   |      Collaborator |
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
| Vehicle and Equipment Fleet Manager (VFM) |   registers a   |           Vehicle |
| Organization                              |      owns       |          Vehicles |
| Green Space                               |       has       |          Vehicles |
| Vehicle and Equipment Fleet Manager (VFM) |     chooses     |          Vehicles |
| Vehicle and Equipment Fleet Manager (VFM) |      lists      | Vehicles Check-Up |
| Vehicles Check-Up                         |       has       |          Vehicles |
| Green Spaces Manager (GSM)                |      is a       |      Collaborator |
| Green Spaces Manager (GSM)                |   registers a   |       Green Space |
| Green Spaces Manager (GSM)                |    manages a    |       Green Space |
| Green Spaces Manager (GSM)                |    assigns a    |              Team |
| Green Spaces Manager (GSM)                |      lists      |          Vehicles |
| Green Spaces Manager (GSM)                |      adds       |       AgendaEntry |
| Green Spaces Manager (GSM)                |    postpones    |       AgendaEntry |
| Green Spaces Manager (GSM)                |     cancels     |       AgendaEntry |
| Green Space                               | associated with |     ToDoListEntry |
| ToDoListEntry                             |       has       |              Task |
| ToDoList                                  |    includes     |     ToDoListEntry |
| Agenda                                    |    includes     |       AgendaEntry |
| Green Spaces Manager (GSM)                |      adds       |       AgendaEntry |
| Team                                      |       has       |       AgendaEntry |
| AgendaEntry                               |      is a       |             Entry |
| Collaborator                              |      lists      |              Task |
| Collaborator                              |    completes    |              Task |






## Domain Model


![Domain Model](svg/project-domain-model.svg)

# US012 - Import .csv file  

## 1. Requirements Engineering

### 1.1. User Story Description

As a Facilities Manager, I want to efficiently schedule resources to ensure optimal use and minimize downtime.

### 1.2. Customer Specifications and Clarifications

**From the client clarifications:**

> **Question:** What types of resources need scheduling?
>
> **Answer:** Meeting rooms, projectors, and company vehicles.

> **Question:** What are the peak times for resource use?
>
> **Answer:** Peak times are generally from 9 AM to 2 PM on weekdays.

> **Question:** Are there any preferences for certain departments or teams regarding resource allocation?
>
> **Answer:** Yes, the sales department has priority on the first Monday of every month for meeting rooms and projectors.

### 1.3. Acceptance Criteria

* **AC1:** The system must allow the scheduling of specified resources: meeting rooms, projectors, and vehicles.
* **AC2:** The system must prioritize resource requests based on departmental needs as specified.
* **AC3:** The system should provide a visual calendar view of resource schedules.

### 1.4. Found out Dependencies

* This feature relies on the availability of an accurate database of resources including their status (available or booked).
* Integration with a departmental hierarchy system is needed to manage priority rules effectively.

### 1.5. Input and Output Data

**Input Data:**

* Resource type (meeting room, projector, vehicle)
* Desired date and time for booking
* Department making the booking

**Output Data:**

* Booking confirmation
* Updated resource schedule in visual calendar format


### 1.6. System Sequence Diagram (SSD)

**_Other alternatives might exist._**

#### Alternative One

![System Sequence Diagram - Alternative One](svg/us005-system-sequence-diagram-alternative-one.svg)

#### Alternative Two

![System Sequence Diagram - Alternative Two](svg/us005-system-sequence-diagram-alternative-two.svg)

#### Alternative Three

![System Sequence Diagram - Alternative Three](svg/us005-system-sequence-diagram-alternative-three.svg)

### 1.7 Other Relevant Remarks

* The HRM can change the team's recommendation and will be notified if none of the members have the necessary skills to carry out the task.
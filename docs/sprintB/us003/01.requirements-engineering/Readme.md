# US003 - Register a Collaborator

## 1. Requirements Engineering

### 1.1. User Story Description

As an HRM, I want to register a collaborator with a job and fundamental characteristics.

### 1.2. Customer Specifications and Clarifications

**From the client clarifications:**

> **Question:** What should be the accepted format for the emails? Should only specific email services be accepted?
> 
> **Answer:** A valid email address consists of an email prefix and an email domain, both in acceptable formats.
The prefix appears to the left of the @ symbol. The domain appears to the right of the @ symbol.
For example, in the address example@mail.com, "example" is the email prefix, and "mail.com" is the email domain.

> **Question:** User Story 03 mentions the term 'fundamental characteristics'. My question is precisely what are these characteristics?
>
> **Answer:** The minimum essential employee data will name, date of birth, date of employment, address, contact details (telephone and email), ID card and its number. You can consider any others you feel are relevant.

> **Question:** Does this number have a particular format? How is it supposed to be created? Automatically by the system or manually by the HRM?
>
> **Answer:** It makes no sense to generate a number that represents a person's identification. People have identification documents (CC, ID, Passport) whose numbers have already been assigned.

> **Question:** What is needed for the address? Street, zipcode and a city?
>
> **Answer:** That would be enough.

> **Question:** Should the system able the HRM to insert multiple collaborators in one interaction before saving them?
>
> **Answer:** It's not required to do so.

> **Question:** Is there any limitation regarding the length of the name of the collaborator?
>
> **Answer:** According to the Portuguese law, a name should contain at maximum six words.

> **Question:** Should we consider valid only the birthdate in which the collaborator has more than 18 years?
>
> **Answer:** Yes.

> **Question:** What should be the format for the phone number? 9 numbers?
>
> **Answer:** Validating 9 digits will be acceptable.

> **Question:** What is the format for the numbers from the id doc types?
>
> **Answer:** Validating with international format would be excellent.

> **Question:** Are there any other business rules?
> 
> **Answer:** Each doc type has specific formats like taxpayer number, Citizen Card ou passport.

> **Question:** Which information is mandatory to insert a collaborator in the program (fundamental characteristics)? Does the HRM select the job from a list that we display?
>
> **Answer:** Name, birthdate, admission date, address, contact info (mobile and email), taxpayer number, ID doc type and respective number - displaying or not, It's a matter of UX, the dev team should decide about it, but the valid jobs are the ones created within the US02.

> **Question:** When creating a collaborator with an existing name ... What the system does?
>
> **Answer:** It's not common and most improbable to have different individual with the same name in the same context, however, its ID documentation number should be unique for sure.

> **Question:** What characteristics are important to success the register?
>
> **Answer:** I believe that question was already answered, name, birthdate, admission date, id doc type, id doc number, contact info (email, mobile), address.


### 1.3. Acceptance Criteria

* **AC1:** All mandatory fields for the collaborator registration must be completed, including name, date of birth, date of admission, address, contact information (phone and email), identification document, and document number.
* **AC2:** The collaborator's reference must contain at least 5 alphanumeric characters and be unique within the system.
* **AC3:** If a collaborator with an existing name is being registered, the system should handle this scenario by prompting the user to modify the name to ensure uniqueness.
* **AC4:** Upon successful registration, the system should provide a confirmation message indicating the success of the operation.
* **AC5:** In case of failure during registration due to missing or invalid data, the system should display appropriate error messages indicating the specific issues that need to be addressed.

### 1.4. Found out Dependencies

* Dependency on US001: Requires at least one competence to assign to the collaborator's job.
* Dependency on US002: Relies on predefined jobs within the system to assign to collaborators.

### 1.5 Input and Output Data

**Input Data:**

* Typed data:
    * Collaborator's personal information (e.g. name, email, phone number)
    * Job-related information (e.g. job title, department)
    * Fundamental characteristics (e.g., skills, competences)

* Selected data:
    * Job selection from predefined options

**Output Data:**

* (In)Success of the operation
* Updated collaborator database

### 1.6. System Sequence Diagram (SSD)

**_Other alternatives might exist._**

#### Alternative One

![System Sequence Diagram - Alternative One](svg/us003-system-sequence-diagram-alternative-one.svg)


### 1.7 Other Relevant Remarks

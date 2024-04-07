# Green Space Management Application User Manual


## Glossary

To access the glossary, click [here](01.requirements-engineering/glossary.md).

## Introduction
## System Overview

The main goal of the system developed under this project is to offer an integrated solution for the effective 
management of green spaces for collective use, such as gardens and parks, to be used by town and parish councils.
This application is designed to optimize operations related to the maintenance and planning of these spaces, facilitating 
the management of multidisciplinary teams, the allocation of resources, the control of fleets and equipment, the 
optimization of irrigation and lighting systems, and the production of statistical indicators that make it possible to 
assess the performance of the activities carried out.

### System Structure

The structure of the application is organized around several main modules that interact with each other to offer 
cohesive and integrated management of green spaces:

1. **Collaborator Management**: This module is responsible for registering and managing collaborators, including their 
competences, functions, and assignment to teams. It allows the Human Resources Manager (HRM) to create and manage
collaborators' profiles, record their skills and associate them with specific tasks.


2. **Team Management**: Facilitates the creation and management of multidisciplinary teams, allowing the HRM to compose 
teams based on the skills required to carry out the tasks in the various green spaces.


3. **Vehicle and Equipment Management**: This module allows the Vehicle and Equipment Fleet Manager (FM) to register 
and control vehicles and equipment, ensuring their proper maintenance and availability for scheduled tasks.


### Main Features

- Flexible Resource Management: The application provides flexible management of collaborators, teams, vehicles, and 
equipment, adapting to the dynamic needs of green space management.

### Structural Diagram

![Domain Model](02.analysis/svg/project-domain-model.svg)

This diagram illustrates the modular organization of the application so far and the key interactions between the 
different components of the system, offering a clear view of its structure and integrated operation.


## Features
> ### 1. Register skills | HRM
> 

> ### 2. Register a Profession | HRM
> 

> ### 3. Registration of an employee | HRM
> 

> ### 4. Assigning skills to an employee | HRM
>

> ### 5. Generate a Team Proposal | HRM
> **Purpose:** To enable the Human Resources Manager (HRM) to automatically generate a team proposal based on the 
minimum and maximum size of the team and the set of skills required for a given task.
> 
> **Instructions**:
> - Log in to your Human Resources Manager account;
> - Navigate to the section where you can create a team;
> - Enter the desired minimum and maximum team size;
> - Select the necessary skills from the available list (skills already registered by an HRM);
> - Click on "Generate Team" or a similar option to automatically create a team proposal based on the criteria specified.

> ### 6. Register a vehicle | VFM
>

> ### 7. Register a vehicle's check-up | VFM
>

> ### 8. List vehicles that need to be serviced | VFM
> 

# US004 - Assigning competences to an employee 

## 4. Tests 

**Test 1:** Test 1: Ensure that it is not possible to assign a skill with a null collaborator.



	@Test(expected = IllegalArgumentException.class)
        public void testAssignSkillWithNullCollaborator() {
        RegisterSkillController controller = new RegisterSkillController();
        controller.assignSkill(null, "Java Programming");
}



**Test 2:** Check that it is not possible to assign a skill with a null skill name.

java


	@Test(expected = IllegalArgumentException.class)
    public void testAssignSkillWithNullSkillName() {
    Collaborator collaborator = new Collaborator("John Doe", LocalDate.of(1990, 1, 1), LocalDate.of(2020, 1, 1),
        "Address", "123456789", "john.doe@example.com", "123456789", "IDType", "123456");
    RegisterSkillController controller = new RegisterSkillController();
    controller.assignSkill(collaborator, null);
    }


_It is also recommended to organize this content by subsections._ 


## 5. Construction (Implementation)

### Class CreateTaskController 

```java
public Task createTask(String reference, String description, String informalDescription, String technicalDescription,
                       Integer duration, Double cost, String taskCategoryDescription) {

	TaskCategory taskCategory = getTaskCategoryByDescription(taskCategoryDescription);

	Employee employee = getEmployeeFromSession();
	Organization organization = getOrganizationRepository().getOrganizationByEmployee(employee);

	newTask = organization.createTask(reference, description, informalDescription, technicalDescription, duration,
                                      cost,taskCategory, employee);
    
	return newTask;
}
```

### Class Organization

```java
public Optional<Task> createTask(String reference, String description, String informalDescription,
                                 String technicalDescription, Integer duration, Double cost, TaskCategory taskCategory,
                                 Employee employee) {
    
    Task task = new Task(reference, description, informalDescription, technicalDescription, duration, cost,
                         taskCategory, employee);

    addTask(task);
        
    return task;
}
```


## 6. Integration and Demo 

* A new option on the Employee menu options was added.

* For demo purposes some tasks are bootstrapped while system starts.


## 7. Observations

n/a
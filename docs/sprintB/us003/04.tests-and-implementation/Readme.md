# US003 - Registration of an employee

## 4. Tests

**Test 1:** Test of creating a collaborator with valid arguments.

**Test 1.1:** Successful scenario.

    @Test
    public void testConstructorWithValidArguments() {
        // Arrange
        String name = "João Silva";
        Date birthDate = new Date();
        Date admissionDate = new Date();
        String address = "Address";
        int mobile = 123456789;
        String email = "joao.silva@example.com";
        int taxpayerNumber = 123456789;
        String idDocType = "ID";
        int idDocNumber = 987654321;
        String jobName = "Gardener";

        // Act
        Collaborator collaborator = new Collaborator(name, birthDate, admissionDate, address, mobile, email, taxpayerNumber, idDocType, idDocNumber, jobName);

        // Assert
        assertEquals(name, collaborator.getName());
        assertEquals(birthDate, collaborator.getBirthDate());
        assertEquals(admissionDate, collaborator.getAdmissionDate());
        assertEquals(address, collaborator.getAddress());
        assertEquals(mobile, collaborator.getMobile());
        assertEquals(email, collaborator.getEmail());
        assertEquals(taxpayerNumber, collaborator.getTaxpayerNumber());
        assertEquals(idDocType, collaborator.getIdDocType());
        assertEquals(idDocNumber, collaborator.getIdDocNumber());
        assertEquals(jobName, collaborator.getJob().getName());
    }

**Test 1.2:** Scenario where an invalid job name is provided - AC01.

    @Test
    public void testConstructorWithInvalidJobName() {
        // Arrange
        String name = "João Silva";
        Date birthDate = new Date();
        Date admissionDate = new Date();
        String address = "Address";
        int mobile = 123456789;
        String email = "joao.silva@example.com";
        int taxpayerNumber = 123456789;
        String idDocType = "ID";
        int idDocNumber = 987654321;
        String invalidJobName = "NonexistentJob";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new Collaborator(name, birthDate, admissionDate, address, mobile, email, taxpayerNumber, idDocType, idDocNumber, invalidJobName));
    }

**Test 2:** Test of assigning a skill to a collaborator.

**Test 2.1:** Successful scenario.

    @Test
    public void testAssignSkill() {
        // Arrange
        Collaborator collaborator = new Collaborator("João Silva", new Date(), new Date(), "Address", 123456789, "joao.silva@example.com", 123456789, "ID", 987654321, "Gardener");
        Skill skill = new Skill("Java");

        // Act
        collaborator.assignSkill(skill);

        // Assert
        assertTrue(collaborator.getSkillSet().contains(skill));
    }



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
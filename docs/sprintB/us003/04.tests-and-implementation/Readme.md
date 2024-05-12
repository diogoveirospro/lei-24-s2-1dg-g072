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
        ass### Class CreateTaskController 
ertEquals(idDocType, collaborator.getIdDocType());
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

### Class RegisterCollaboratorController

```java
public void registerCollaborator(String name, Date birthDate, Date admissionDate, String address, int mobile,
                                 String email, int taxpayerNumber, String idDocType, int idDocNumber, String jobName) {
    Collaborator collaborator = new Collaborator(name, birthDate, admissionDate, address, mobile, email,
            taxpayerNumber, idDocType, idDocNumber, jobName);
    collaboratorRepository.addCollaborator(collaborator);
}

```

### Class CollaboratorRepository

```java
public void addCollaborator(Collaborator newCollaborator){
    if (!validateCollaborator(newCollaborator)) {
        throw new IllegalArgumentException("Collaborator already exists!");
    }
    if (newCollaborator == null) {
        throw new IllegalArgumentException("Collaborator cannot be null!");
    }

    collaborators.add(newCollaborator);
}

private boolean validateCollaborator(Collaborator collaborator){
    return !collaborators.contains(collaborator);
}
```


## 6. Integration and Demo 

* A new option "Register Collaborator" was added to the Employee menu options to facilitate the registration of new collaborators.

During system initialization, a few sample collaborators may be bootstrapped into the system for demonstration purposes.


## 7. Observations

n/a
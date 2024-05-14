# US001 - Register skills 

## 4. Tests 

**Test 1:** Test of adding skills to the repository.
**Test 1.1:** Successful scenario.

	 @Test
    public void testAddSkillSucess() {
        Skill skill1 = new Skill("AAA");
        repository.addSkill(skill1);
        List<Skill> skills = repository.listSkills();
        assertTrue(skills.contains(skill1));
    }
**Test 1.2:** Scenario where skills added are null, the HRM needs to write at least one skill - AC01.

    @Test
    public void testAddSkillNull() {
        Exception exception = assertThrows(IllegalArgumentException.class,()->repository.addSkill(null));
        assertEquals("Skill cannot be null",exception.getMessage());
    }
**Test 1.3:** Scenario where skills added already exist.

    @Test
    public void testAddSkillAlreadyExists(){
        Skill skill1 = new Skill("AAA");
        repository.addSkill(skill1);
        Exception exception = assertThrows(IllegalArgumentException.class,()-> repository.addSkill(skill1));
        assertEquals("This skill already exists " + skill1.getName(),exception.getMessage());

    }
	

**Test 2:** A skill can't have special characters or digits - AC2. 

	public void testSpecialCharacters(){
        Skill skill = new Skill("AA+");
        assertFalse(skill.validateSkill(skill.getName()));
    }

_It is also recommended to organize this content by subsections._ 


## 5. Construction (Implementation)

### Class RegisterSkillController

```java
/**
 * Registers a new skill with the specified name in the system. This involves
 * creating a Skill object and adding it to the repository.
 *
 * @param name The name of the skill to addSkill. It must not be null or empty.
 */

public void registerSkill (String name){
    Skill skill = new Skill(name);
    try {
        skillRepository.addSkill(skill);
    }catch (IllegalArgumentException e){
        System.out.println(e.getMessage());
    }
}
```

### Class SkillRepository

```java
/**
 * This method adds a skill to the repository
 *
 * @param skill to be added
 */
public void addSkill(Skill skill){
    if(skill == null){
        throw new IllegalArgumentException("Skill cannot be null");
    }
    if (skills.contains(skill)){
        throw new IllegalArgumentException("This skill already exists "+skill.getName());
    }
    if (!skill.validateSkill(skill.getName())){
        throw new IllegalArgumentException("Skill can't have special characters");
    }
    skills.add(skill);
}
```


## 6. Integration and Demo 

* A new option on the UI to add more skills

* For demo purposes some skills are bootstrapped while system starts.


## 7. Observations

n/a
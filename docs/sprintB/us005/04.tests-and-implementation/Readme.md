# US005 - Generate a Team Proposal 

## 4. Tests 

### Class TeamRepositoryTest 

**Test 1:** Test to verify the behavior of generateTeamProposal method in GenerateTeamProposalController. 
Generates a team proposal with specified skills and collaborators and verifies the generated team members. 
Ensures that the generated team meets the criteria of having a specific number of members with required skills.

    @Test
    public void testGenerateTeamProposal1() {

        GenerateTeamProposalController controller = new GenerateTeamProposalController();
        CollaboratorRepository collaboratorsRepository = new CollaboratorRepository();
        SkillRepository skillRepository = new SkillRepository();

        collaboratorsRepository.addCollaborator(c1);
        collaboratorsRepository.addCollaborator(c2);
        collaboratorsRepository.addCollaborator(c3);
        collaboratorsRepository.addCollaborator(c4);

        List<Collaborator> collaborators = collaboratorsRepository.getCollaborators();

        Skill skill1 = new Skill("Landscape Design");
        Skill skill2 = new Skill("Gardening");
        Skill skill3 = new Skill("Agriculture");
        Skill skill4 = new Skill("Sustainable Land Management");
        Skill skill5 = new Skill("Ecological Restoration");

        skillRepository.addSkill(skill1);
        skillRepository.addSkill(skill2);
        skillRepository.addSkill(skill3);
        skillRepository.addSkill(skill4);
        skillRepository.addSkill(skill5);

        List<Skill> skills = new ArrayList<>();
        skills.add(skill1);
        skills.add(skill2);
        skills.add(skill1);

        c1.assignSkill(skill1);
        c1.assignSkill(skill2);
        c2.assignSkill(skill3);
        c2.assignSkill(skill4);
        c3.assignSkill(skill1);
        c4.assignSkill(skill5);
        c4.assignSkill(skill2);
        c4.assignSkill(skill5);


        List<Collaborator> teamMembers = controller.generateTeamProposal(2, 3, skills, collaborators);

        assertEquals(3, teamMembers.size());
        assertTrue(teamMembers.contains(collaborators.get(0)));
        assertTrue(teamMembers.contains(collaborators.get(2)));
        assertTrue(teamMembers.contains(collaborators.get(3)));
    }

**Test 2:** Test to verify the behavior of generateTeamProposal method in GenerateTeamProposalController when no 
collaborators have the required skills. Adds collaborators with specific skills to the repository, attempts to generate
a team proposal with specified skills, and expects an IllegalArgumentException to be thrown with a message indicating 
the lack of collaborators with the required skill. 

    @Test
    public void testGenerateTeamProposal2() {
        GenerateTeamProposalController controller = new GenerateTeamProposalController();
        CollaboratorRepository collaboratorsRepository = new CollaboratorRepository();
        SkillRepository skillRepository = new SkillRepository();

        collaboratorsRepository.addCollaborator(c1);
        collaboratorsRepository.addCollaborator(c2);

        List<Collaborator> collaborators = collaboratorsRepository.getCollaborators();

        Skill skill1 = new Skill("Landscape Design");
        Skill skill2 = new Skill("Gardening");

        skillRepository.addSkill(skill1);
        skillRepository.addSkill(skill2);

        List<Skill> skills = new ArrayList<>();
        skills.add(skill1);
        skills.add(skill2);

        c1.assignSkill(skill1);

        try {
            controller.generateTeamProposal(2, 5, skills, collaborators);
            fail("The method should throw an IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("There are no collaborators with the specified skill: "));
        }
    }

**Test 3:** Test to verify the behavior of generateTeamProposal method in GenerateTeamProposalController when there are 
not enough collaborators with required skills. Adds a single collaborator with a specific skill to the repository, 
attempts to generate a team proposal with specified skills, and expects an IllegalArgumentException to be thrown with a 
message indicating the lack of sufficient collaborators with required skills.

    @Test
    public void testGenerateTeamProposal3() {
        GenerateTeamProposalController controller = new GenerateTeamProposalController();
        CollaboratorRepository collaboratorsRepository = new CollaboratorRepository();
        SkillRepository skillRepository = new SkillRepository();

        collaboratorsRepository.addCollaborator(c1);

        List<Collaborator> collaborators = collaboratorsRepository.getCollaborators();

        Skill skill1 = new Skill("Landscape Design");
        Skill skill2 = new Skill("Gardening");

        skillRepository.addSkill(skill1);
        skillRepository.addSkill(skill2);

        List<Skill> skills = new ArrayList<>();

        c1.assignSkill(skill1);

        try {
            controller.generateTeamProposal(2, 5, skills, collaborators);
            fail("The method should throw an IllegalArgumentException");
        }catch (IllegalArgumentException e){
            assertEquals("There aren't enough collaborators with the specified skills to create a team!", e.getMessage());
        }
    }

**Test 4:** Test to verify the behavior of createTeam method in TeamRepository. 
Creates a team with a list of members, retrieves the created team, and verifies that the retrieved team matches the 
expected team with the same members.

    @Test
    void testCreateTeam(){

        TeamRepository teamRepository = new TeamRepository();
        List<Collaborator> members = new ArrayList<>();

        members.add(c1);
        members.add(c2);

        Team team = teamRepository.createTeam(members);
        Team expectedTeam = new Team(members);

        assertEquals(expectedTeam, team);

    }

**Test 5:** Test to verify the behavior of addTeam method in TeamRepository when adding a new team.
Creates a team with a list of members, adds the team to the repository, retrieves all teams from the repository, 
and verifies that the added team is present in the list of teams.

    @Test
    void testAddTeam1(){

        TeamRepository teamRepository = new TeamRepository();
        List<Collaborator> members = new ArrayList<>();

        members.add(c1);
        members.add(c2);

        Team team = new Team(members);

        teamRepository.addTeam(team);

        List<Team> teams = teamRepository.getTeams();

        assertTrue(teams.contains(team));

    }

**Test 6:** Test to verify the behavior of addTeam method in TeamRepository when adding an empty team. 
Creates an empty team, adds the team to the repository, retrieves all teams from the repository, and verifies 
that the empty team is not present in the list of teams.

    @Test
    void testAddTeam2(){

        TeamRepository teamRepository = new TeamRepository();
        List<Collaborator> members = new ArrayList<>();

        Team team = new Team(members);

        teamRepository.addTeam(team);

        List<Team> teams = teamRepository.getTeams();

        assertFalse(teams.contains(team));

    }

**Test 7:** Test to verify the behavior of validateTeam method in TeamRepository with an empty team. 
Creates an empty team and checks if the repository correctly validates that the team is invalid.

    @Test
    void testValidateTeam1(){

        TeamRepository teamRepository = new TeamRepository();
        List<Collaborator> members = new ArrayList<>();

        Team team = new Team(members);

        assertFalse(teamRepository.validateTeam(team));
    }

**Test 8:** Test to verify the behavior of validateTeam method in TeamRepository with a null team. 
Checks if the repository correctly validates that a null team is invalid.

    @Test
    void testValidateTeam2(){

        TeamRepository teamRepository = new TeamRepository();

        assertFalse(teamRepository.validateTeam(null));
    }


## 5. Construction (Implementation)

### Class GenerateTeamProposalController 

```java
public List<Collaborator> generateTeamProposal(int minimumSize, int maximumSize, List<Skill> skills,
                                               List<Collaborator> collaborators){
    return teamRepository.generateTeamProposal(minimumSize, maximumSize, skills, collaborators);
}

public Team createTeam(List<Collaborator> members){
    return teamRepository.createTeam(members);
}
```

### Class TeamRepository

```java
public List<Collaborator> generateTeamProposal(int minimumSize, int maximumSize, List<Skill> skills, List<Collaborator> collaborators) {
    List<Collaborator> members = new ArrayList<>();
    List<Collaborator> collaboratorsClone = new ArrayList<>(collaborators);
    List<Skill> remainingSkills = new ArrayList<>(skills);

    selectMembersForSkills(maximumSize, members, collaboratorsClone, remainingSkills);

    if (members.size() < minimumSize) {
        throw new IllegalArgumentException("There aren't enough collaborators with the specified skills to create a team!");
    }

    fillUpToMaximumSize(maximumSize, members, collaboratorsClone);

    return members;
}

private void selectMembersForSkills(int maximumSize, List<Collaborator> members, List<Collaborator> collaboratorsClone, List<Skill> remainingSkills) {
    while (!remainingSkills.isEmpty() && members.size() < maximumSize && !collaboratorsClone.isEmpty()) {
        Skill skill = remainingSkills.get(0);
        List<Collaborator> qualifiedCollaborators = getQualifiedCollaborators(skill, collaboratorsClone);

        if (qualifiedCollaborators.isEmpty()) {
            throw new IllegalArgumentException("There are no collaborators with the specified skill: " + skill);
        }

        addCollaboratorToTeam(members, qualifiedCollaborators, collaboratorsClone);
        remainingSkills.remove(skill);
    }
}

private List<Collaborator> getQualifiedCollaborators(Skill skill, List<Collaborator> collaboratorsClone) {
    List<Collaborator> qualifiedCollaborators = new ArrayList<>();
    for (Collaborator collaborator : collaboratorsClone) {
        if (collaborator.analyseCollaborator(skill)) {
            qualifiedCollaborators.add(collaborator);
        }
    }
    return qualifiedCollaborators;
}

private void addCollaboratorToTeam(List<Collaborator> members, List<Collaborator> qualifiedCollaborators, List<Collaborator> collaboratorsClone) {
    Collaborator selectedCollaborator = qualifiedCollaborators.get(0);
    members.add(selectedCollaborator);
    selectedCollaborator.setHasTeam(true);
    collaboratorsClone.remove(selectedCollaborator);
}

private void fillUpToMaximumSize(int maximumSize, List<Collaborator> members, List<Collaborator> collaboratorsClone) {
    while (members.size() < maximumSize && !collaboratorsClone.isEmpty()) {
        Collaborator additionalCollaborator = collaboratorsClone.get(0);
        members.add(additionalCollaborator);
        additionalCollaborator.setHasTeam(true);
        collaboratorsClone.remove(0);
    }
}

public Team createTeam(List<Collaborator> members){
    return new Team(members);
}

public void addTeam(Team team) {
    if (validateTeam(team)) {
        teams.add(team);
    }
}

public boolean validateTeam(Team team){
    return team != null && !team.getTeam().isEmpty();

}
```
### Team Class

```java
public Team(List<Collaborator> members){
        team = members;
}
```


## 6. Integration and Demo 

* A new option has been added to the HRM menu options.


## 7. Observations

n/a
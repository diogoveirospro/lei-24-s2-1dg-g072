package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SkillRepositoryTest {
    private SkillRepository repository;

    @BeforeEach
    void setUp() {
        this.repository = new SkillRepository();
    }

    @Test
    public void testAddSkillSucess() {
        Skill skill1 = new Skill("AAA");
        repository.addSkill(skill1);
        List<Skill> skills = repository.listSkills();
        assertTrue(skills.contains(skill1));
    }

    @Test
    public void testAddSkillNull() {
        try {
            repository.addSkill(null);
            fail("The method should throw an IllegalArgumentException");
        } catch (IllegalArgumentException e){
            assertTrue(e.getMessage().contains("Skill cannot be null"));
        }
    }

    @Test
    public void testAddSkillAlreadyExists(){
        Skill skill1 = new Skill("AAA");
        repository.addSkill(skill1);

        try {
            repository.addSkill(skill1);
            fail("The method should throw an IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("This skill already exists "));
        }

    }

    @Test
    public void testAddSkillNotValid(){
        Skill skill1 = new Skill("AAA+");

        try {
            repository.addSkill(skill1);
            fail("The method should throw an IllegalArgumentException");
        } catch (IllegalArgumentException e){
            assertTrue(e.getMessage().contains("Skill can't have special characters"));
        }
    }
    @Test
    public void testListSkillsWorks(){
        Skill skill1 = new Skill("AAA");
        repository.addSkill(skill1);
        List<Skill> skills = repository.listSkills();
        assertThrows(UnsupportedOperationException.class,()-> skills.add(new Skill("BBB")));

    }

}

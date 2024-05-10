package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import java.util.List;
import static org.junit.Assert.*;

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
        Exception exception = assertThrows(IllegalArgumentException.class,()->repository.addSkill(null));
        assertEquals("Skill cannot be null",exception.getMessage());
    }

    @Test
    public void testAddSkillAlreadyExists(){
        Skill skill1 = new Skill("AAA");
        repository.addSkill(skill1);
        Exception exception = assertThrows(IllegalArgumentException.class,()-> repository.addSkill(skill1));
        assertEquals("AAA",exception.getMessage());

    }
    @Test
    public void testListSkillsWorks(){
        Skill skill1 = new Skill("AAA");
        repository.addSkill(skill1);
        List<Skill> skills = repository.listSkills();
        assertThrows(UnsupportedOperationException.class,()-> skills.add(new Skill("BBB")));

    }

}

package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SkillTest {

    @Test
    public void testGetName(){
        Skill skill1 = new Skill("AAA");
        assertEquals("AAA",skill1.getName());
    }

    @Test
    public void testSetName(){
        Skill skill1 = new Skill("AAA");
        skill1.setName("BBB");
        assertEquals("BBB",skill1.getName());
    }

    @Test
    public void testEquals(){
        Skill skill1 = new Skill("AAA");
        Skill skill2 = new Skill("AAA");
        Skill skill3 = new Skill("BBB");

        assertTrue(skill1.equals(skill2));
        assertFalse(skill2.equals(skill3));
    }

    @Test
    public void testToString (){
        Skill skill1 = new Skill("AAA");
        assertEquals("AAA",skill1.toString());
    }

    @Test
    public void testSetNameNull (){
        assertThrows(IllegalArgumentException.class,()-> new Skill(null));
    }

    @Test
    public void testSetNameEmpty(){
        assertThrows(IllegalArgumentException.class,()-> new Skill(""));
    }

    @Test
    public void testSetNameWhiteSpace (){
        assertThrows(IllegalArgumentException.class,()-> new Skill("   "));
    }

}
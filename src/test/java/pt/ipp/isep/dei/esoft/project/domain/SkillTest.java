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
    public void testSetName1(){
        Skill skill1 = new Skill("AAA");
        skill1.setName("BBB");
        assertEquals("BBB",skill1.getName());
    }

    @Test
    public void testSetName2(){
        Skill skill1 = new Skill("AAA");

        try {
            skill1.setName("");
            fail("The method should throw an IllegalArgumentException");
        }catch (IllegalArgumentException e){
            assertTrue(e.getMessage().contains("Name cannot be empty or null"));
        }

    }

    @Test
    public void testSetName3(){
        Skill skill1 = new Skill("AAA");

        try {
            skill1.setName(null);
            fail("The method should throw an IllegalArgumentException");
        }catch (IllegalArgumentException e){
            assertTrue(e.getMessage().contains("Name cannot be empty or null"));
        }
    }

    @Test
    public void testEquals(){
        Skill skill1 = new Skill("AAA");
        Skill skill2 = new Skill("AAA");
        Skill skill3 = new Skill("BBB");
        Job job1 = new Job("Landscape Designer");

        assertEquals(skill1, skill1);
        assertNotEquals(skill1, null);
        assertNotEquals(skill1, job1);
        assertEquals(skill1, skill2);
        assertNotEquals(skill2, skill3);
    }

    @Test
    public void testToString (){
        Skill skill1 = new Skill("AAA");
        assertEquals("AAA",skill1.toString());
    }

    @Test
    public void testSpecialCharacters(){
        Skill skill = new Skill("AA+");
        assertFalse(skill.validateSkill(skill.getName()));
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
    public void testSetNameWhiteSpace(){
        assertThrows(IllegalArgumentException.class,()-> new Skill("   "));
    }

    @Test
    public void testCompareTo(){
        Skill skill1 = new Skill("AAA");
        Skill skill2 = new Skill("BBB");
        Skill skill3 = new Skill("AAA");

        assertTrue(skill1.compareTo(skill2) < 0);
        assertEquals(0, skill1.compareTo(skill3));
        assertTrue(skill2.compareTo(skill1) > 0);
    }

}
package com.mycompany.app;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import java.util.List;
import java.util.ArrayList;

public class TableTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public TableTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( TableTest.class );
    }

    /**
     * Fundamental Behavior 1: verifies space
     * Test that when a group is added to a table, the table remainingSize is reduced.
     */
    public void testTableHasSpace()
    {
        Table t = new Table("A",2);
        Group g = new Group("Adams",2,"");
        boolean result = t.hasSeatsFor(g);
        assertTrue(result);
        assertEquals(2,t.getSize());
    }
    public void testTableHasNoSpace()
    {
        Table t = new Table("A",0);
        Group g = new Group("Adams",1,"");
        boolean result = t.hasSeatsFor(g);
        assertFalse(result);
        assertEquals(0,t.getSize());
    }
    public void testTableLacksSpace()
    {
        Table t = new Table("A",2);
        Group g = new Group("Adams",3,"");
        boolean result = t.hasSeatsFor(g);
        assertFalse(result);
        assertEquals(2,t.getSize());
    }

    /**
     * Fundamental Behavior 2: add group changes table size;
     * Test that when a group is added to a table, the table remainingSize is reduced.
     */
    public void testAddGroupChangesTableSize()
    {
        Table t = new Table("A",2);
        Group g = new Group("Adams",1,"");
        t.addGroup(g);
        assertEquals(2,t.getSize());
        assertEquals(1,t.getRemainingSize());
    }

}

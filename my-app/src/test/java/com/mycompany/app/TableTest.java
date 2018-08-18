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
     * Fundamental Behavior 1: add group changes table size;
     * Test that when a group is added to a table, the table remainingSize is reduced.
     */
    public void testAddGroupChangesSize() // move this to TableTest
    {
        Table t = new Table("A",2);
        Group g = new Group("Adams",1,"");
        t.addGroup(g);
        assertEquals(2,t.size);
        assertEquals(1,t.remainingSize);
    }
}

package com.mycompany.app;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import java.util.List;
import java.util.ArrayList;

public class GroupTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public GroupTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( GroupTest.class );
    }

    /**
     * Fundamental Behavior 1: group knows if it dislikes table
     */
    public void testGroupDislikesTableOfOne()
    {
    	// set up table
        Table t = new Table("A",2);
        Group g1 = new Group("Adams",1,"Zedd");
        t.addGroup(g1);
        
        // try to add disliked member
        Group gDisliked = new Group("Zedd",1,"");
        boolean result = gDisliked.aGroupAtTableDislikesThisGroup(t);

        assertTrue(result);
        assertEquals(2,t.getSize());
        assertEquals(1,t.getRemainingSize());
    }
    public void testGroupDislikesTableOfTwo()
    {
    	// set up table
        Table t = new Table("A",2);
        Group g1 = new Group("Adams",1,"Jones");
        t.addGroup(g1);
        Group g2 = new Group("Smith",1,"Zedd");
        t.addGroup(g2);
        
        // try to add disliked member
        Group gDisliked = new Group("Zedd",1,"");
        boolean result = gDisliked.aGroupAtTableDislikesThisGroup(t);

        assertTrue(result);
        assertEquals(2,t.getSize());
        assertEquals(0,t.getRemainingSize());
    }

    /**
     * Fundamental Behavior 2: group knows if table dislikes it
     */
    public void testTableDislikesGroupOne()
    {
    	// set up table
        Table t = new Table("A",2);
        Group g1 = new Group("Adams",1,"");
        t.addGroup(g1);
        
        // try to add disliked member
        Group gDislikes = new Group("Zedd",1,"Adams");
        boolean result = gDislikes.thisGroupDislikesOneOfTableGroups(t);

        assertTrue(result);
        assertEquals(2,t.getSize());
        assertEquals(1,t.getRemainingSize());
    }
    public void testTableDislikesGroupTwo()
    {
    	// set up table
        Table t = new Table("A",2);
        Group g1 = new Group("Adams",1,"");
        t.addGroup(g1);
        Group g2 = new Group("Barnes",1,"");
        t.addGroup(g2);
        
        // try to add disliked member
        Group gDislikes = new Group("Zedd",1,"Barnes");
        boolean result = gDislikes.thisGroupDislikesOneOfTableGroups(t);

        assertTrue(result);
        assertEquals(2,t.getSize());
        assertEquals(0,t.getRemainingSize());
    }

    /**
     * Fundamental Behavior 3: happy together
     */
    public void testTableWithGroupAreHappyTogether()
    {
    	// set up table
        Table t = new Table("A",2);
        Group g1 = new Group("Adams",1,"");
        t.addGroup(g1);
        
        // try to add disliked member
        Group g2 = new Group("Zedd",1,"");
        boolean result = g2.happyTogether(t);

        assertTrue(result);
        assertEquals(2,t.getSize());
        assertEquals(1,t.getRemainingSize());
    }
}

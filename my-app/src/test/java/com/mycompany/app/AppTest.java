package com.mycompany.app;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import java.util.List;
import java.util.ArrayList;

public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class);
    }

    /**
     * Fundamental Behavior 2: table loop starts from front for each group.
     * Test that when two groups are added to two table, both groups fit in first table.
     */
    public void testAddBothToSameTable()
    {
		List<Table> tables = new ArrayList<Table>();
		tables.add(new Table("A",2));
		tables.add(new Table("B",2));
        List<Group> groups = new ArrayList<Group>();
        Group g1 = new Group("Davidson",1,"");
		groups.add(g1);
        Group g2 = new Group("Howardson",1,"");
		groups.add(g2);
		
		App app = new App();
		Boolean success = app.assignGroupsToTables(tables, groups);

		assertTrue(success);
		assertEquals(0,tables.get(0).getRemainingSize());
		assertEquals(2,tables.get(1).getRemainingSize());
		app.print(tables);
	}
    /**
     * Fundamental Behavior 3: table loop skips table because of size.
     * Test that group is added where it fits, second table has space.
     */
    public void testAddGroupToSpaciousTable()
    {
		List<Table> tables = new ArrayList<Table>();
		tables.add(new Table("A",1));
		tables.add(new Table("B",2));
        List<Group> groups = new ArrayList<Group>();
        Group g1 = new Group("Thornton",2,"");
		groups.add(g1);
        Group g2 = new Group("Howser",1,"");
		groups.add(g2);
		
		App app = new App();
		Boolean success = app.assignGroupsToTables(tables, groups);

		assertTrue(success);
		assertEquals(0,tables.get(0).getRemainingSize());
		assertEquals(0,tables.get(1).getRemainingSize());
		app.print(tables);
	}
    /**
     * Fundamental Behavior 4: table loop skips table because of dislike.
     * Test that when two groups are added to two table, both groups fit in first table, but dislike puts them on two tables.
     */
    public void testAddBothToSameTableDislike()
    {
		List<Table> tables = new ArrayList<Table>();
		tables.add(new Table("A",2));
		tables.add(new Table("B",2));
        List<Group> groups = new ArrayList<Group>();
		groups.add(new Group("Thornton",1,"Smith"));
		groups.add(new Group("Smith",1,"Thornton"));

		App app = new App();
		Boolean success = app.assignGroupsToTables(tables, groups);

		assertTrue(success);
		assertEquals(1,tables.get(0).getRemainingSize());
		assertEquals(1,tables.get(1).getRemainingSize());
		app.print(tables);
	}
    /**
     * Fundamental Behavior 4: table loop skips table because of dislike.
     * Test that when two groups are added to two table, both groups fit in first table, but dislike puts them on two tables.
     */
    public void testAddBothToSameTableDislike1()
    {
		List<Table> tables = new ArrayList<Table>();
		tables.add(new Table("A",2));
		tables.add(new Table("B",2));
        List<Group> groups = new ArrayList<Group>();
		groups.add(new Group("Thornton",1,"Smith"));
		groups.add(new Group("Smith",1,""));

		App app = new App();
		Boolean success = app.assignGroupsToTables(tables, groups);

		assertTrue(success);
		assertEquals(1,tables.get(0).getRemainingSize());
		assertEquals(1,tables.get(1).getRemainingSize());
		app.print(tables);
	}
    /**
     * Fundamental Behavior 4: table loop skips table because of dislike.
     * Test that when two groups are added to two table, both groups fit in first table, but dislike puts them on two tables.
     */
    public void testAddBothToSameTableDislike2()
    {
		List<Table> tables = new ArrayList<Table>();
		tables.add(new Table("A",2));
		tables.add(new Table("B",2));
        List<Group> groups = new ArrayList<Group>();
		groups.add(new Group("Thornton",1,""));
		groups.add(new Group("Smith",1,"Thornton"));

		App app = new App();
		Boolean success = app.assignGroupsToTables(tables, groups);

		assertTrue(success);
		assertEquals(1,tables.get(0).getRemainingSize());
		assertEquals(1,tables.get(1).getRemainingSize());
		app.print(tables);
	}
}

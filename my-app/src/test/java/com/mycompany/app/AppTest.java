package com.mycompany.app;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

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

    private void stageData(List<Table> tables, List<Group> groups){
		tables.add(new Table("A",4));
		tables.add(new Table("B",5));
		tables.add(new Table("C",6));
		groups.add(new Group("Thornton",2,"Smith"));
		groups.add(new Group("Smith",2,""));
		groups.add(new Group("Howser",3,"Smith"));
		groups.add(new Group("Jones",4,""));
		groups.add(new Group("Kidd",3,""));
		groups.add(new Group("Swanson",1,"Howser"));
    }
    
    public void testCodingChallenge() {
		List<Table> tables = new ArrayList<Table>();
        List<Group> groups = new ArrayList<Group>();
		
		stageData(tables, groups);
		App app = new App();
		Collections.sort(tables, new DescendingTableSizeSort());
		Collections.sort(groups, new DescendingGroupSizeSort());
		boolean success = app.assignGroupsToTables(tables, groups);
		assertTrue(success);
		Collections.sort(tables, new AscendingTableNameSort());
		assertEquals("Table A Kidd, party of 3 Swanson, party of 1",tables.get(0).display().trim().replace("\n"," "));
		assertEquals("Table B Howser, party of 3 Smith, party of 2",tables.get(1).display().trim().replace("\n"," "));
		assertEquals("Table C Jones, party of 4 Thornton, party of 2",tables.get(2).display().trim().replace("\n"," "));
	}
    
    /**
     * When two groups are added to two table, both groups fit in first table.
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
	}
    /**
     * Group is added where it fits, second table has space.
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
	}
    /**
     * When two groups are added to two tables, both groups fit in first table, but dislike puts them on two tables.
     * Both groups dislike each other here.
     */
    public void testAddBothToSameTableBothDislike()
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
	}
    /**
     * When two groups are added to two tables, both groups fit in first table, but dislike puts them on two tables.
     * First group dislikes second group.
     */
    public void testAddBothToSameTableDislikeLeft()
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
	}
    /**
     * When two groups are added to two table, both groups fit in first table, but dislike puts them on two tables.
     * Second group dislikes first group.
     */
    public void testAddBothToSameTableDislikeRight()
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
	}
}

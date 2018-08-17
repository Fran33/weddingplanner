package com.mycompany.app;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        List<Table> tables = new ArrayList<Table>();
		tables.add(new Table(4));
		tables.add(new Table(5));
		tables.add(new Table(6));
        List<Group> groups = new ArrayList<Group>();
		groups.add(new Group("Thornton",2,"Smith"));
		groups.add(new Group("Smith",2,""));
		groups.add(new Group("Howser",3,"Smith"));
		groups.add(new Group("Jones",4,""));
		groups.add(new Group("Kidd",3,""));
		groups.add(new Group("Swanson",1,"Howser"));
		
		App app = new App();
		app.assignGroupsToTables(tables, groups);

    }
    public void print(List<Table> tables){
		for(Table t : tables){
			t.display();
		}
    }
    public void assignGroupsToTables(List<Table> tables, List<Group> groups){
		Iterator<Group> groupIter = groups.iterator();
		while(groupIter.hasNext()){
			Group g = groupIter.next();
			boolean seated = false;
			Iterator<Table> tableIter = tables.iterator();
			while(tableIter.hasNext() && !seated){
				Table t = tableIter.next();
				if(t.canAddWithinSize(g) && g.happyTogether(t)){
					t.addGroup(g);
					seated = true;
				} else {
					// try next table
				}
			}
		}
	}    	
}
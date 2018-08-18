package com.mycompany.app;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.io.File;
import java.util.Collections;

import org.apache.log4j.*;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class App 
{
	final static Logger logger = Logger.getLogger(App.class);

	public static void main( String[] args )
    {
    	PropertyConfigurator.configure("log4j.properties");  
        List<Table> tables = new ArrayList<Table>();
        List<Group> groups = new ArrayList<Group>();
		
		App app = new App();
		FileReader reader = new FileReader();

		reader.parseFile(args[0],tables, groups);
		Collections.sort(tables, new AscendingTableSize());
		Collections.sort(groups, new AscendingGroupSize());
		app.assignGroupsToTables(tables, groups);
		app.print(tables);
    }
    public void assignGroupsToTables(List<Table> tables, List<Group> groups){
		Iterator<Group> groupIter = groups.iterator();
		while(groupIter.hasNext()){
			Group g = groupIter.next();
			boolean seated = false;
			Iterator<Table> tableIter = tables.iterator();
			Table dislikeChoice = null;
			while(tableIter.hasNext() && !seated){
				Table t = tableIter.next();
				if(t.canAddWithinSize(g)){
					if(g.happyTogether(t)){
						t.addGroup(g);
						seated = true;
					} else {
						// size fits but dislike this choice, save for later
						if(dislikeChoice == null){
							dislikeChoice = t;
						}
					}
				} else {
					// try next table
				}
			}
			if(!seated && dislikeChoice != null){
				dislikeChoice.addGroup(g);
				seated = true;
			}				
		}
	}    	
    public void print(List<Table> tables){
		for(Table t : tables){
			t.display();
		}
    }
}
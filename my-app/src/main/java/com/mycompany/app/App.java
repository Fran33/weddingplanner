package com.mycompany.app;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.io.File;

public class App 
{
    public static void main( String[] args )
    {
        List<Table> tables = new ArrayList<Table>();
        List<Group> groups = new ArrayList<Group>();
		
		App app = new App();

		app.parseFile("testData1.txt",tables, groups);
		app.assignGroupsToTables(tables, groups);
		app.print(tables);
    }
    public void stageData(List<Table> tables, List<Group> groups){
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
    public void parseFile(String filename, List<Table> tables, List<Group> groups){
    	if(filename == null){
    		stageData(tables, groups);
    		return;
    	}
    	try{
			Scanner scanner = new Scanner(new File(filename));
			scanner.useDelimiter("\\n");
			int ntoken = 0;
			while (scanner.hasNext()) {
				String token = scanner.next();
				token = token.trim();
				token = token.replace(",","");
				token = token.replace(":","");
				
				if(token.indexOf("tables") >= 0){
					// parse tables line
					token = token.replace("tables","");
					token = token.trim();
					String[] tokens = token.split(" ");
					for(int i=0;i<tokens.length;i++){
						System.out.println(tokens[i]);
						String[] pairs = tokens[i].split("-");
						String tableName = pairs[0].trim();
						String tableSize = pairs[1].trim();
						System.out.println("table " + tableName + " size " + tableSize);
						tables.add(new Table(tableName,Integer.parseInt(tableSize)));
					}
				} else if(token.indexOf("party of") >= 0){
					// parse group line
					String[] tokens = token.split("party of");
					String groupName = tokens[0].trim();
					String sizeLikes = tokens[1].trim();
					String[] sizeLikeTokens = sizeLikes.split("dislikes");
					String size = sizeLikeTokens[0].trim();
					String dislikes = "";
					if(sizeLikeTokens.length > 1){
						dislikes = sizeLikeTokens[1].trim();
						System.out.println("group " + groupName + " size " + size + " dislikes " + dislikes);
					} else {
						System.out.println("group " + groupName + " size " + size );
					}
					groups.add(new Group(groupName,Integer.parseInt(size),dislikes));
				}
				
				ntoken++;
				System.out.printf("%3d) %s%n", ntoken, token);
			}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
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
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

public class FileReader
{
	final static Logger logger = Logger.getLogger(FileReader.class);

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
			while (scanner.hasNext()) {
				String token = scanner.next();
				logger.debug(token);
				token = token.trim();
				token = token.replace(",","");
				token = token.replace(":","");
				
				if(token.indexOf("tables") >= 0){
					// parse tables line
					token = token.replace("tables","");
					token = token.trim();
					String[] tokens = token.split(" ");
					for(int i=0;i<tokens.length;i++){
						String[] pairs = tokens[i].split("-");
						String tableName = pairs[0].trim();
						String tableSize = pairs[1].trim();
						logger.info("table " + tableName + " size " + tableSize);
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
						logger.info("group " + groupName + " size " + size + " dislikes " + dislikes);
					} else {
						logger.info("group " + groupName + " size " + size );
					}
					groups.add(new Group(groupName,Integer.parseInt(size),dislikes));
				}
			}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
}
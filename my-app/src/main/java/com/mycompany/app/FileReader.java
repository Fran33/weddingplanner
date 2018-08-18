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

    public boolean parseFile(String filename, List<Table> tables, List<Group> groups){
    	if(filename == null){
    		return false;
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
			return true;
    	}catch(Exception e){
    		e.printStackTrace();
    		return false;
    	}
    }
}
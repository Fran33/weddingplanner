package com.mycompany.app;

import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

import org.apache.log4j.Logger;

class AscendingTableSize implements Comparator<Table>
{
    public int compare(Table t1, Table t2){
    	return t2.size - t1.size;
    }
}
public class Table
{
	final static Logger logger = Logger.getLogger(Table.class);
	public String name;
    public int size;
    public int remainingSize;
    public List<Group> groups;
    public String groupNamesCsv;
    public Table(String name, int size) {
    	this.name = name;
    	this.groups = new ArrayList<Group>();
    	this.size = size;
    	this.remainingSize = size;
    	this.groupNamesCsv = "";
    }
    public void display(){
    	int totalSize = 0;
    	String resultLine = "table " + this.name + " " + this.size + "=";
    	for(Group g : groups){
    		resultLine += g.name + "," + g.size+"; ";
    		totalSize += g.size;
    	}
    	logger.info(resultLine);
    }
    public void addGroup(Group group){
		this.groups.add(group);
		this.remainingSize -= group.size;
		this.groupNamesCsv +=","+group.name;
    }
    public boolean canAddWithinSize(Group group){
    	logger.debug("trying to add " + group.name + "," + group.size + " to table of remaining size " + this.remainingSize);
    	int newSize = this.remainingSize - group.size;
    	if(newSize >= 0){
    		logger.debug("can add with remaining size " + newSize);
    		return true;
    	} else {
    		logger.debug("cannot add, not enough room " + newSize);
    		return false;
    	}
	}
}

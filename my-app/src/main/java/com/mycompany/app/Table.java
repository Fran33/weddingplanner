package com.mycompany.app;

import java.util.List;
import java.util.ArrayList;

import org.apache.log4j.Logger;

public class Table
{
	final static Logger logger = Logger.getLogger(Table.class);
	private String name;
    private int size;
    private int remainingSize;
    private List<Group> groups;
    public String getName(){
    	return this.name;
    }
    public int getSize() {
    	return this.size;
    }
    public int getRemainingSize() {
    	return this.remainingSize;
    }
    public List<Group> getGroups() {
    	return this.groups;
    }
    public Table(String name, int size) {
    	this.name = name;
    	this.groups = new ArrayList<Group>();
    	this.size = size;
    	this.remainingSize = size;
    }
    public String display(){
    	int totalSize = 0;
    	String resultLine = "Table " + this.name + "\n";
    	for(Group group : groups){
    		resultLine += group.display() + "\n";
    	}
    	return resultLine;
    }
    public void addGroup(Group group){
		this.groups.add(group);
		this.remainingSize -= group.getSize();
    }
    public boolean hasSeatsFor(Group group){
    	logger.debug("trying to add " + group.getName() + "," + group.getSize() + " to table of remaining size " + this.remainingSize);
    	int newSize = this.remainingSize - group.getSize();
    	if(newSize >= 0){
    		logger.debug("can add with remaining size " + newSize);
    		return true;
    	} else {
    		logger.debug("cannot add, not enough room " + newSize);
    		return false;
    	}
	}
}

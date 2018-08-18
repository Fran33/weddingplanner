package com.mycompany.app;

import java.util.List;
import java.util.ArrayList;

public class Table
{
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
		System.out.print("table " + this.name + " " + this.size + "=");
    	for(Group g : groups){
    		System.out.print(g.name + "," + g.size+";");
    		totalSize += g.size;
    	}
    	System.out.println("");
    }
    public void addGroup(Group group){
		this.groups.add(group);
		this.remainingSize -= group.size;
		this.groupNamesCsv +=","+group.name;
    }
    public boolean canAddWithinSize(Group group){
    	System.out.println("trying to add " + group.name + "," + group.size + " to table of remaining size " + this.remainingSize);
    	int newSize = this.remainingSize - group.size;
    	if(newSize >= 0){
    		System.out.println("can add with remaining size " + newSize);
    		return true;
    	} else {
    		System.out.println("cannot add, not enough room " + newSize);
    		return false;
    	}
	}
}

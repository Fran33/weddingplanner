package com.mycompany.app;

import java.util.List;
import java.util.ArrayList;

import org.apache.log4j.Logger;

public class Group
{
	final static Logger logger = Logger.getLogger(Group.class);
    private String name;
    private int size;
    private String dislikesCsv;
    public String getName() {
    	return this.name;
    }
    public int getSize() {
    	return this.size;
    }
    public String getDislikes() {
    	return this.dislikesCsv;
    }
    public Group(String name, int size, String dislikesCsv){
    	this.name = name;
    	this.size = size;
    	this.dislikesCsv = dislikesCsv;
    }
    public boolean happyTogether(Table t) {
    	return !thisGroupDislikesOneOfTableGroups(t) && !aGroupAtTableDislikesThisGroup(t);
    }
    public boolean thisGroupDislikesOneOfTableGroups(Table t){
    	// loop over table groups, checking names
		for(Group g : t.getGroups()){ 
    		if(this.dislikesCsv.indexOf(g.name) >= 0){
    			logger.debug("this group " + this.name + " dislikes table group " + g.name + " so cannot be added to table");
    			return true;
    		}
    	}
    	return false;
    }
    public boolean aGroupAtTableDislikesThisGroup(Table t){
    	// loop over table groups, checking dislikes
		for(Group g : t.getGroups()){ 
    		if(g.dislikesCsv.indexOf(this.name) >= 0){
    			logger.debug("this table group " + g.name + " dislikes this group " + this.name + " so " + this.name + " cannot be added to table");
    			return true;
    		}
    	}
    	return false;
    }
}

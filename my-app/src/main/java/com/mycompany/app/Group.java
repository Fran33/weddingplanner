package com.mycompany.app;

import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

class AscendingGroupSize implements Comparator<Group>
{
    public int compare(Group g1, Group g2){
    	return g2.size - g1.size;
    }
}
public class Group
{
    public String name;
    public int size;
    public String dislikesCsv;
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
		for(Group g : t.groups){ 
    		if(this.dislikesCsv.indexOf(g.name) >= 0){
    			System.out.println("this group " + this.name + " dislikes table group " + g.name + " so cannot be added to table");
    			return true;
    		}
    	}
    	return false;
    }
    public boolean aGroupAtTableDislikesThisGroup(Table t){
    	// loop over table groups, checking dislikes
		for(Group g : t.groups){ 
    		if(g.dislikesCsv.indexOf(this.name) >= 0){
    			System.out.println("this table group " + g.name + " dislikes this group " + this.name + " so " + this.name + " cannot be added to table");
    			return true;
    		}
    	}
    	return false;
    }
}

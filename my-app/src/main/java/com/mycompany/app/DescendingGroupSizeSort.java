package com.mycompany.app;

import java.util.Comparator;

class DescendingGroupSizeSort implements Comparator<Group>
{
    public int compare(Group g1, Group g2){
    	return g2.getSize() - g1.getSize();
    }
}

package com.mycompany.app;

import java.util.Comparator;

class AscendingTableNameSort implements Comparator<Table>
{
    public int compare(Table t1, Table t2){
    	return t1.getName().compareTo(t2.getName());
    }
}

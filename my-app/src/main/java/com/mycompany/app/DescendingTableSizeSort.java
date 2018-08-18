package com.mycompany.app;

import java.util.Comparator;

class DescendingTableSizeSort implements Comparator<Table>
{
    public int compare(Table t1, Table t2){
    	return t2.getSize() - t1.getSize();
    }
}

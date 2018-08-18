# weddingplanner

The goal of Wedding Planner is to arrange each group of guests at a table such that 
all guests have seats and all guests avoid sitting with guests they dislike.

Wedding Planner expects the name of a text file which contains table sizes and group sizes and preferences.
Tables are all on one line, and Groups each on their own line to contain name, size, and names of disliked Groups;
Wedding Planner produces a list of table assignments for the groups.

Example command line:
java -cp target/my-app-1.0-SNAPSHOT.jar com.mycompany.app.App testData1.txt

Per Group, the Tables are considered one at a time for size and for disliked seated Groups.
If there is not enough remaining size, the next Table is considered.
If there is a disliked Group, then that table is saved for consideration.
If a table is found with size and no dislikes, the Group is seated.
If a table is not found, and a disliked table is found, then that disliked table is used.
If a table is not found including dislikes, the program reports an error.

The tables and groups are sorted by descending size, to fit the tightest constraints soonest.
With the sort, the planner proceeds with iteration over Groups, then Tables per Group.
In a future version there could be scanning prior to choosing, to either randomize or backtrack the assignments.

Fran Mahoney
fran.mahoney@gmail.com
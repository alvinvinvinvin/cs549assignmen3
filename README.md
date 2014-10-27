cs549assignmen3
===============
/**
* Han Chen
* cs 549 assignment 3 - competition
* 10/27/2014
*/


C-S 449/549 - Advances in Software Engineering
 Fall 2014
 Assignment 4
 Due date: October 30, 2014



This assignment must REUSE the code from the previous assignment (received from another student).

The Wisconsin Music Association for Youngsters (WMAY) wants to develop a software product to maintain information about music competitions conducted by the association. There are several levels of competitions; these levels are given below:
•Violin (Solo) for beginners
•Violin (Solo) for intermediates
•Violin (Solo) for experts
•Piano (Solo) for beginners
•Piano (Solo) for intermediates
•Piano (Solo) for experts
•Piano (duet)

You can assume that there is only one competition at any one time but every competition will include all the levels as stated above. The competitors come from different schools in Wisconsin. Each participant should indicate his/her name, the level of competition he/she wants to participate, and his/her school. For simplicity, it is assumed that each student is uniquely identified by his/her name. A student may participate in more than one level. The data that the software handles include the competitions at various levels, the participants in each level, the score for each participant in each level, the winner of a competition in each level, and the winning school of a competition at each level. The winning school of a competition at a particular level is decided by the sum of the scores of all students from the same school in the same level.

Following is the list of minimum functionalities to be implemented by the software product:
•Add/delete a participant information. This is equivalent to registration with the system. Notice that a participant may be present in the system but does not take part in the competition.
•Add/delete a participant to the competition. Notice that there is no separate functionality to add/delete a school to the competition. It is part of the information of the participants. 
•View the participants' details of any/all levels in the competition.
•Conduct the competition. This process should randomly generate scores for each participant in each level in the competition.
•Determine the winner of the competition at each level
•Determine the winning school for the competition at each level
•View the students and their scores of the winning school at any given level.
For practical considerations, the number of participants at each level is limited to 20. However, there is no limit for the total number of participants. 
The participants data can be stored in a file or a database but it is not necessary for the assignment. The worst part of not storing the data is that the testing process will be harder because you need to add/re-create participants every time you change the code. 

Submission requirements:

Send your code to the instructor at or before the class time on the due date.

The code must be very well-commented and must use reasonable and meaningful names for the variables, methods and classes. It should be self-documented and hence anyone who reads the code must be able to understand it. It must REUSE the code from the previous assignment. However, you get the code from another student. In your code, indicate which portions of the previous code you are reusing.

In addition to submitting your code, you must also include a report on the cost of reuse. It should include the number of entities (classes, methods, statements etc.) that you directly used, changed and/or added. Also, include a brief paragraph explaining whether or not the reuse process for this assignment is cost effective.

How to use:
1. Keep the files location where they were.
2. Use terminal go to directory where Start.java(the main method) is.
3. Input:
    javac *.java
    java -classpath ".;sqlite-jdbc-3.8.7.jar" Start
4. Enjoy.

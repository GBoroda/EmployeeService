# EmployeeService
REST, Spring, Spring MVC, Hibernate, MySQL.

HOW TO RUN:

1. Download and open this project from  github. 
2. use "mmvn idea:idea" for creating project in Intellij Idea
3. Create Database with name "test", username - "root" and password - "132435"
4. Insert test data to database in console. You can find it in EmployeeService/src/main/resources/testDataForDatabase.txt
5. create Tomcat user with name:"gboroda" password:"132435" and roles:"manager-gui, manager-script"
6. use "mvn tomcat7:deploy" for deploying webapp into tomcat and open in Tomcat Web Application Manager application named "EmployeeService"
7. or open project in Idea.
8. Create MySQL Dataabase connection.
9. Create Tomcat server for that project in your IDE and run it.
10. Open next links for work with programm
   /   - for main page
   /employees - to see List with all employees
   /departments - to see List with all departments name

[![screen](https://github.com/GBoroda/EmployeeService/blob/master/src/main/resources/screen.png)](https://github.com/GBoroda/EmployeeService/blob/master/src/main/resources/screen.png)

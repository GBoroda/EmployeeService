# EmployeeService
REST, Spring, Spring MVC, Hibernate, MySQL and my soul

HOW TO RUN:

1. Download and open this project from  github. 
2. use "mmvn idea:idea" for creating project in Intellij Idea
3. Create Database with name "test", username - "root" and password - "132435"
4. Insert test data to database in console. You can find it in EmployeeService/src/main/resources/testDataForDatabase.txt
5. Use "mvn tomcat7:redeploy -Dtomcat.username=YOUR_USERNAME -Dtomcat.password=YOUR_PASSWORD" to deploy this app in tomcat server. Or deploy .war file from tomcat manager page. 
7. or open project in Idea.
8. Create MySQL Dataabase connection.
9. Create Tomcat server for that project in your IDE and run it.
10. Open next links for work with programm
   /main   - for main page;
   /employees - to see List with all employees;
   /departments - to see List with all departments name.


What we got:
[![screen](https://github.com/GBoroda/EmployeeService/blob/master/src/main/resources/screen.png)](https://github.com/GBoroda/EmployeeService/blob/master/src/main/resources/screen.png)

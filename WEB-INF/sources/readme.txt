
1. Modify the war file name to Fabflix.war. Then deploy the war file on Tomcat.

2. Recompile:
        1) Go to /var/lib/tomcat7/webapps/Fabflix/WEB-INF/sources/com/battleborn/webapps , then run:

        sudo javac -cp .:/var/lib/tomcat7/webapps/Fabflix/WEB-INF/lib/servlet-api.jar:/var/lib/tomcat7/webapps/Fabflix/WEB-INF/lib/javax.json-1.0.jar *.java

        2) Copy all class files to /var/lib/tomcat7/webapps/Fabflix/WEB-INF/classes/com/battleborn/webapps

        3) Reload the Fabflix on Tomcat. Then go to https://ec2-35-161-212-83.us-west-2.compute.amazonaws.com:8443/Fabflix

3. The address of you AWS instance:

ec2-35-161-212-83.us-west-2.compute.amazonaws.com

4. The username and password of the tomcat manager app on your AWS instance.
User name: cs122b
Password: mypassword

5. The package for Task 5 is com.parse.xmldata
	The main file is located in Main.java.
	How to run:
	go to the directory where the java file of package com.parse.xmldata is located. (/Fabflix/WebContent/WEB-INF/sources/com/parse/xmldata)
	javac -cp .:../../../../lib/servlet-api.jar:../../../../lib/mysql-connector-java-5.1.40-bin.jar *.java
	cd ../../../
	java com.parse.xmldata.Main
	
	The files actors63.xml, mains243.xml and casts124.xml are included in the .war file.
	The following optimizations have been included:
	1. The insert statements have been executed in batches of the size of the entire list
	2. Autocommit is turned off before execution
	3. Duplicate values have been checked against the database and the newly inserted values using a HashSet for movies and stars
	4. The table with foreign key constraints stars_in_movies has been updated with the corresponding starIDs and movieIDs using a HashMap. This data is obtained from the database in a single operation.
	
	




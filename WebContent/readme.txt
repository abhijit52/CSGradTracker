============ readme =============================
GSTracker Beta
Team: Shrike
=================================================

Tools required: Eclipse, Tomcat server.
External depenedent library: "mysql-connector-java-5.1.22-bin.jar"

The project is run on Tomcat server through Eclipse. The initial page should show a login screen. 
As we are using Tomcat based Authentication, "mysql-connector-java-5.1.22-bin.jar" is needed to copy to the local host Apache Home/bin directory.
In this project, we save that file in /WebContent/WEB-INF/lib directory.
The sample user name and password for users with different roles are mentioned here:

Username: student
password: student

username: sdf
password: faculty

username: secretary
password: secretary

Upon successful login, the dashboard is viewed based on user's role. 


For student:
=============
 Student home page, Edit basic information, Change photo, Change password, add/view/edit course plan, Add course and grade for approval, View the course status. 
 These options can be navigated through the menus. 

 For Faculty:
 ============
 Faculty home page, Edit profile and photo, View/add/remove publications, see the list of advisee, Search for a specific students (search criteria include any field. i.e: first name, last name, email)
 See the approval requests (For course, and Grade), Change password, and logout.
 
 For Secretary:
 =============
 
Secretary  home page, change picture, edit information, change password, add student/faculty, search for a student or faculty. 
   
  Also password can be changed using forgot password feature. It verifies, user-id, email, birth day and birth month and prompts for new password. 
Upon successful database update, it redirects to the login page and display the successful message. There is also
an option to return to login screen from these two pages. 

For testing Forgot password the following information is provided below for the given user:

Username: ddasgupt
Email: ddasgupt@memphis.edu
Birth day : 4
Birth Month : 8
  
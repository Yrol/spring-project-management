# Spring Project Management
A simple Project Management app develped using Spring Boot, Thyemleaf and Bootstrap. 

MySQL has been used for data management.

### Create Artifacts in IntelliJ and running the project locally.

+ **Step 1** : File &rarr; Project Structure
  <p><img src="https://i.imgur.com/rTo35dE.png"></img></p>

+ **Step 2** : Add (+) &rarr; JAR &rarr; From modules with dependencies
  <p><img src="https://i.imgur.com/auX21p8.png"></img></p>

+ **Step 3** : Create JAR from Modules
  - Module: proeject-management
  - Main Class: `com.yrol.pma.ProjectManagementApplication`
  - JAR files from libraries: copy to output directory and link via manifest
  <p><img src="https://i.imgur.com/H8zsmZw.png"></img></p>
 
+ **Step 4** : Project Structure
  - Name: project-management:jar
  - Type: Other
  - Include in project build: - &#9745;
  - Apply & ok
  <p><img src="https://i.imgur.com/X8Lp26W.png"></img></p>
  
  Notice: Once this is done
  
 + **Step 5** : Build &rarr; Build Artifacts.... Then select Build.
    <p><img src="https://i.imgur.com/mwwzG02.png"></img></p>
    <p><img src="https://i.imgur.com/oDGVJv3.png"></img></p>
    
    Notice: Once this step is completed, the Artifacts folder should have the folders resources generated.
    <p><img src="https://i.imgur.com/vGB2d1P.png"></img></p>
    
 + **Running the artifact** :
    - Go to the directory: out &rarr; artifacts &rarr; project_management_jar
    - Run the command: `java -jar project-management.jar`
    
    The Project should run in `http://localhost:8080/`.
    
 
   

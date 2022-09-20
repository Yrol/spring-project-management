# Spring Project Management
A simple Project Management app developed using Spring Boot, Thymeleaf and Bootstrap. 

PostgreSQL has been used for data management. Create a DB called `pma-springbootdb` and run the schema define in `src/test/resources/schema.sql` as a prerequisite.

### Running the project locally in IntelliJ CE
+ **Step 1:** Go to Edit Configurations &rarr; Add New &rarr; Application
  <p><img src="https://i.imgur.com/WDPIdls.png"></img></p>
  <p><img src="https://i.imgur.com/0wZl0ON.png"></p>
  
+ **Step 2:** In the newly opened window, select Modify Options > Add VM Options. This step will allow us to specify a local server with a port.
  <p><img src="https://i.imgur.com/0KVgpg2.png"></p>
  
  Add following details in the configuration window.

  - **Name:** `<project name>`

  - **VM Options:** `-Dserver.port=8080`

  - **Java:** `java 15`

  - **Main class:** `com.yrol.pma.ProjectManagementApplication`
  
  <p><img src="https://i.imgur.com/UErxhVN.png"></p>

+ **Step 3:** Running the project. We should now see the new run option as below.
  <p><img src="https://i.imgur.com/aHd60US.png"></p>
  
  Click the run button to run the project
  <p><img src="https://i.imgur.com/e0C2POL.png"></p>

---
### Building and running the project in Docker
The docker run files are located in `docker` directory. The artifacts' directory - `docker/artifacts` 
consist of JAR files that get copied over to the `/usr/local/bin/` location of the docker container. Make sure to 
replace these files whenever it's necessary by regenerating them as explained in [Create Artifacts](#-create-artifacts-/-jar-in-intelliJ-and-run-project-locally) section below.

+ **Step 1:** Building a docker image. Go to `docker` directory in the project and run:
  ```
  docker image build -t project-management
  ```

+ **Step 2:** Running the docker image in a container by mapping to port `8080`:
  ```
  docker run -p 8080:8080 project-management 
  ```  

---


### Create Artifacts / JAR in IntelliJ and run project locally

+ **Step 1:** File &rarr; Project Structure
  <p><img src="https://i.imgur.com/rTo35dE.png"></img></p>

+ **Step 2:** Add (+) &rarr; JAR &rarr; From modules with dependencies
  <p><img src="https://i.imgur.com/auX21p8.png"></img></p>

+ **Step 3:** Create JAR from Modules
  - Module: project-management
  - Main Class: `com.yrol.pma.ProjectManagementApplication`
  - JAR files from libraries: copy to output directory and link via manifest
  <p><img src="https://i.imgur.com/H8zsmZw.png"></img></p>
 
+ **Step 4:** Project Structure
  - Name: project-management:jar
  - Type: Other
  - Include in project build: &#9745;
  - Apply & ok
  <p><img src="https://i.imgur.com/X8Lp26W.png"></img></p>
  
 + **Step 5:** Build &rarr; Build Artifacts.... Then select Build.
    <p><img src="https://i.imgur.com/mwwzG02.png"></img></p>
    <p><img src="https://i.imgur.com/oDGVJv3.png"></img></p>
    
    Notice: Once this step is completed, the Artifacts folder should have the folders resources generated along with `project-management.jar`.
    <p><img src="https://i.imgur.com/vGB2d1P.png"></img></p>
    
 + **Running the artifact:**
    - Go to the directory: out &rarr; artifacts &rarr; project_management_jar
    - Run the command: `java -jar project-management.jar`

    Or select and run the `project-management.jar` file directly from IntelliJ.
    
    The Project should run in `http://localhost:8080/`
    
 
   

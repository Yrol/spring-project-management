# Spring Project Management
A simple Project Management app developed using Spring Boot, Thymeleaf and Bootstrap. Features are as below.

  - User authentication -  Register and login
  - CRUD operations on employees and projects
  - UI with Thyemeleaf
  - RESTful API endpoints
  - Validation rules

### Prerequisites
+ **PostgreSQL** has been used for data management. Create a DB called `pma-springbootdb` 
  and run the schema define in `src/test/resources/schema.sql` as a prerequisite 
  (ex: using [pgAdmin](https://www.pgadmin.org/) tool or any other).

### Running the project locally in IntelliJ CE
+ **Step 1:** Go to Edit Configurations &rarr; Add New &rarr; Application
  <p><img src="https://i.imgur.com/WDPIdls.png"></img></p>
  <p><img src="https://i.imgur.com/0wZl0ON.png"></p>
  
+ **Step 2:** In the newly opened window, select Modify Options &rarr; Add VM Options. This step will allow us to specify a local server with a port.
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
### Web and RESTful endpoints
Postman collection with payloads can be found in [Postman_collection](https://github.com/Yrol/spring-project-management/tree/master/Postman_collection) directory of the project.

#### Web
| Feature / page  | URL|
|---|---|
|  Register | `http://localhost:<port>/register`  |
|  Login | `http://localhost:<port>/login`  |
|  Home | `http://localhost:<port>`  |
| All employees  | `http://localhost:<port>/employees`  |
| Create employee  | `http://localhost:<port>/employees/new` |
| Update employee  | `http://localhost:<port>/employees/update/<id>`  |
| Delete employee  | `http://localhost:<port>/employees/delete/<id>` |
| All projects  | `http://localhost:<port>/projects`  |
| Create project  | `http://localhost:<port>/projects/new`  |
  

#### REST
| Feature  | Endpoint| Type | 
|---|---|---|
| Create employees  | `http://localhost:<port>/app-api/employees`  | POST |
| All employees  | `http://localhost:<port>/app-api/employees`  | GET |
| All employees paginated | `http://localhost:<port>/app-api/employees?page=<page>&size=<size>`  | GET |
| Employee by ID  | `http://localhost:<port>/app-api/employees/<id>`  | GET |
| Delete employee by ID  | `http://localhost:<port>/app-api/employees/<id>`  | DELETE |
| Update employee | `http://localhost:<port>/app-api/employees/<id>`  | PATCH |
||||
| Create project  | `http://localhost:<port>/app-api/projects`  | POST |
| All projects  | `http://localhost:<port>/app-api/projects`  | GET |
| All projects paginated | `http://localhost:<port>/app-api/projects?page=<page>&size=<size>`  | GET |
| Project by ID  | `http://localhost:<port>/app-api/projects/<id>`  | GET |
| Delete project by ID  | `http://localhost:<port>/app-api/projects/<id>`  | DELETE |
| Update project | `http://localhost:<port>/app-api/projects/<id>`  | PATCH |


---
### Building and running the project in Docker
The docker run files are located in `docker` directory. The artifacts directory - `docker/artifacts` 
consist of JAR files that get copied over to the `/usr/local/bin/` location of the docker container. Make sure to 
replace these files whenever it's needed 
(to regenerate follow [Creating Artifacts](#creating-artifacts--jar-file-in-intellij-and-running-project-locally) section below).

To run the project in docker, go to the `docker` directory in the project and run:

```
docker-compose build
docker-compose up -d
```

The DB schemas are located in `/docker/docker-sources/postgres/dbscripts/` directory which will execute automatically during `docker-compose`. Make sure to update it when new schema changed are introduced.

---


### Package and run project as a JAR file

+ **Step 1:** Building the project.
  <p>In the Maven tab, select `clean` and `install` options and click on `Run Maven Build` option. JAR file will be created in the `taeget` directory.</p>  
  <p><img src="https://i.imgur.com/7bTEO6z.png"></img></p>
      
+ **Step 2: Running the project using JAR:**
  <p>Once the project is build and JAR is generated successfully, expand the `target` folder and run the project.</p>
   <p><img src="https://i.imgur.com/fHHskQi.png"></img></p>
  

  The Project should run in `http://localhost:8080/`

 
   

insert into employee (first_name, last_name, email) values ('John', 'Warton', 'warton@gmail.com'); /* employee_id: 1 */
insert into employee (first_name, last_name, email) values ('Mike', 'Lanister', 'lanister@gmail.com'); /* employee_id: 2 */
insert into employee (first_name, last_name, email) values ('Steve', 'Reeves', 'reeves@gmail.com'); /* employee_id: 3 */
insert into employee (first_name, last_name, email) values ('Roland', 'Carson', 'carson@gmail.com'); /* employee_id: 4 */
insert into employee (first_name, last_name, email) values ('Honor', 'Miles', 'miles@gmail.com'); /* employee_id: 5 */
insert into employee (first_name, last_name, email) values ('Tony', 'Ferguson', 'ferguson@gmail.com'); /* employee_id: 6 */


insert into project(name, stage, description) values ('The MBQ Project', 'NOTSTARTED', 'This is the MBQ project'); /* project_id: 1 */
insert into project(name, stage, description) values ('The BMW Project', 'INPROGRESS', 'This is the BMW project'); /* project_id: 2 */
insert into project(name, stage, description) values ('The Toyota Project', 'COMPLETED', 'This is the Toyota project'); /* project_id: 3 */


insert into project_employee (employee_id, project_id) values (1, 1);
insert into project_employee (employee_id, project_id) values (1, 2);
insert into project_employee (employee_id, project_id) values (1, 3);
insert into project_employee (employee_id, project_id) values (2, 1);
insert into project_employee (employee_id, project_id) values (2, 3);
insert into project_employee (employee_id, project_id) values (3, 3);
insert into project_employee (employee_id, project_id) values (4, 3);
insert into project_employee (employee_id, project_id) values (5, 2);
insert into project_employee (employee_id, project_id) values (5, 3);
insert into project_employee (employee_id, project_id) values (6, 2);
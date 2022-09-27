-- This SQL has been used to seed the H2 DB. The SQL has been commented out since conflicting with SpringTest SQL inserts.
insert into employee (employee_id, first_name, last_name, email) values (1, 'John', 'Warton', 'warton@gmail.com'); /* employee_id: 1 */
insert into employee (employee_id, first_name, last_name, email) values (2, 'Mike', 'Lanister', 'lanister@gmail.com'); /* employee_id: 2 */
insert into employee (employee_id, first_name, last_name, email) values (3, 'Steve', 'Reeves', 'reeves@gmail.com'); /* employee_id: 3 */
insert into employee (employee_id, first_name, last_name, email) values (4, 'Roland', 'Carson', 'carson@gmail.com'); /* employee_id: 4 */
insert into employee (employee_id, first_name, last_name, email) values (5, 'Honor', 'Miles', 'miles@gmail.com'); /* employee_id: 5 */
insert into employee (employee_id, first_name, last_name, email) values (6, 'Tony', 'Ferguson', 'ferguson@gmail.com'); /* employee_id: 6 */


insert into project(project_id, name, stage, description) values (1, 'The MBQ Project', 'NOTSTARTED', 'This is the MBQ project'); /* project_id: 1 */
insert into project(project_id, name, stage, description) values (2, 'The BMW Project', 'INPROGRESS', 'This is the BMW project'); /* project_id: 2 */
insert into project(project_id, name, stage, description) values (3, 'The Toyota Project', 'COMPLETED', 'This is the Toyota project'); /* project_id: 3 */


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
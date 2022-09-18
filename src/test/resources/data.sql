insert into employee (employee_id, first_name, last_name, email) values (nextval('employee_seq'),'John', 'Warton', 'warton@gmail.com');
insert into employee (employee_id, first_name, last_name, email) values (nextval('employee_seq'), 'Mike', 'Lanister', 'lanister@gmail.com');
insert into employee (employee_id, first_name, last_name, email) values (nextval('employee_seq'), 'Steve', 'Reeves', 'reeves@gmail.com');
insert into employee (employee_id, first_name, last_name, email) values (nextval('employee_seq'), 'Roland', 'Carson', 'carson@gmail.com');
insert into employee (employee_id, first_name, last_name, email) values (nextval('employee_seq'), 'Honor', 'Miles', 'miles@gmail.com');
insert into employee (employee_id, first_name, last_name, email) values (nextval('employee_seq'), 'Tony', 'Ferguson', 'ferguson@gmail.com');


insert into project(project_id, name, stage, description) values (nextval('project_seq'), 'The MBQ Project', 'NOTSTARTED', 'This is the MBQ project TD');
insert into project(project_id, name, stage, description) values (nextval('project_seq'), 'The BMW Project', 'INPROGRESS', 'This is the BMW project TD');
insert into project(project_id, name, stage, description) values (nextval('project_seq'), 'The Toyota Project', 'COMPLETED', 'This is the Toyota project TD');


insert into project_employee (employee_id, project_id) select e.employee_id, p.project_id from employee e, project p where e.last_name = 'Warton' AND p.name='The MBQ Project TD';
insert into project_employee (employee_id, project_id) select e.employee_id, p.project_id from employee e, project p where e.last_name = 'Warton' AND p.name='This is the BMW project TD';
insert into project_employee (employee_id, project_id) select e.employee_id, p.project_id from employee e, project p where e.last_name = 'Warton' AND p.name='This is the Toyota project TD';
insert into project_employee (employee_id, project_id) select e.employee_id, p.project_id from employee e, project p where e.last_name = 'Lanister' AND p.name='The MBQ Project TD';
insert into project_employee (employee_id, project_id) select e.employee_id, p.project_id from employee e, project p where e.last_name = 'Lanister' AND p.name='This is the Toyota project TD';
insert into project_employee (employee_id, project_id) select e.employee_id, p.project_id from employee e, project p where e.last_name = 'Reeves' AND p.name='This is the Toyota project TD';
insert into project_employee (employee_id, project_id) select e.employee_id, p.project_id from employee e, project p where e.last_name = 'Carson' AND p.name='This is the Toyota project TD';
insert into project_employee (employee_id, project_id) select e.employee_id, p.project_id from employee e, project p where e.last_name = 'Miles' AND p.name='This is the BMW project TD';
insert into project_employee (employee_id, project_id) select e.employee_id, p.project_id from employee e, project p where e.last_name = 'Miles' AND p.name='This is the Toyota project TD';
insert into project_employee (employee_id, project_id) select e.employee_id, p.project_id from employee e, project p where e.last_name = 'Ferguson' AND p.name='This is the BMW project TD';
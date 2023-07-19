create database crm_app;
USE crm_app;

CREATE TABLE IF NOT EXISTS roles (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(100),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS users (
    id INT NOT NULL AUTO_INCREMENT,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    fullname VARCHAR(100) NOT NULL,
    avatar VARCHAR(100),
    role_id INT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS status (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS jobs (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    start_date DATE,
    end_date DATE,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS tasks (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    start_date DATE,
    end_date DATE,
    user_id INT NOT NULL,
    job_id INT NOT NULL,
    status_id INT NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE users ADD FOREIGN KEY (role_id) REFERENCES roles (id)  ON DELETE CASCADE;
ALTER TABLE tasks ADD FOREIGN KEY (user_id) REFERENCES users (id)  ON DELETE CASCADE;
ALTER TABLE tasks ADD FOREIGN KEY (job_id) REFERENCES jobs (id)  ON DELETE CASCADE;
ALTER TABLE tasks ADD FOREIGN KEY (status_id) REFERENCES status (id)  ON DELETE CASCADE;

INSERT INTO roles( name, description ) VALUES ("ROLE_ADMIN", "Quản trị hệ thống");
INSERT INTO roles( name, description ) VALUES ("ROLE_MANAGER", "Quản lý");
INSERT INTO roles( name, description ) VALUES ("ROLE_USER", "Nhân viên");

INSERT INTO status( name ) VALUES ("Chưa thực hiện");
INSERT INTO status( name ) VALUES ("Đang thực hiện");
INSERT INTO status( name ) VALUES ("Đang thực hiện");

INSERT INTO crm_app.jobs (name,start_date,end_date) VALUES
	 ('crm_project','2023-07-01','2023-08-13'),
	 ('Jira_project','2023-07-13','2023-09-13');
INSERT INTO crm_app.tasks (name,start_date,end_date,user_id,job_id,status_id) VALUES
	 ('edit_update','2023-07-30','2023-07-31',7,1,2),
	 ('delete_User','2023-07-05','2023-07-07',1,1,3),
	 ('Edit_User','2023-07-08','2023-07-10',1,1,2),
	 ('updateUser','2023-01-01','2023-01-01',1,2,1);
INSERT INTO crm_app.users (email,password,fullname,avatar,role_id) VALUES
	 ('tantai204.10@gmail.com','123456','Nguyen Van B','123',2),
	 ('tantai205.10@gmail.com','password','tyalia','234',2),
	 ('tantai206.10@gmail.com','passwordak0724','tyalia','512',3),
	 ('tantai207.10@gmail.com','passwordeawe','tyalia','124',2),
	 ('tyalia@gmail.com','456789','tyalia',NULL,1),
	 ('Hunghia@gmaail.com','34567','Huu nghia',NULL,2),
	 ('thuan@gmail.com','thuantroi','Thiên Thuận',NULL,2);

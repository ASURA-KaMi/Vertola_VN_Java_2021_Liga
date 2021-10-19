create table school
(
    id UUID not null primary key,
    name varchar(255) not null,
    address varchar(255) not null
);

create table teacher
(
    id UUID not null primary key,
    name varchar(255) not null,
    sex varchar(10) not null,
    age integer not null,
    school_id integer
);

create table student
(
    id UUID not null primary key,
    name varchar(255) not null,
    sex varchar(10) not null,
    age int not null,
    school_id integer
);

create table subject
(
    id UUID not null primary key,
    name varchar(255) not null,
);

create table teacher_subject
(
    teacher_id UUID not null,
    subject_id UUID not null
);

create table student_subject
(
    student_id UUID not null,
    subject_id UUID not null
);

alter table teacher
	add foreign key	(school_id) references school (id);

alter table student
	add foreign key	(school_id) references school (id);

alter table student_subject
	add foreign key (subject_id) references subject (id),
		foreign key (student_id) references student (id),
		primary key (student_id, subject_id);

alter table teacher_subject
	add foreign key (subject_id) references subject (id),
		foreign key (teacher_id) references teacher (id),
		primary key (teacher_id, subject_id);
create table application (
    id  bigserial not null,
    creation_time TIMESTAMP,
    status varchar(255),
    time TIMESTAMP,
    user_id int8,
    primary key (id)
);
create table role (
    id  bigserial not null,
    name varchar(255) not null,
    primary key (id)
);
    create table users (
        id  bigserial not null,
        name varchar(255),
        password varchar(255) not null,
        username varchar(255),
        primary key (id)
);
    create table users_roles (
        user_id int8 not null,
        roles_id int8 not null
);
    alter table
        users add constraint UK_r43af9ap4edm43mmtq01oddj6 unique (username);
    alter table
        application add constraint FKawte0mbtubellxed1dvpoxhdj foreign key (user_id) references users;
    alter table
        users_roles add constraint FK15d410tj6juko0sq9k4km60xq foreign key (roles_id) references role;
    alter table
        users_roles add constraint FK2o0jvgh89lemvvo17cbqvdxaa foreign key (user_id) references users;
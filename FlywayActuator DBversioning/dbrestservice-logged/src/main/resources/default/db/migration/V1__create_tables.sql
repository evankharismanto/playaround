create table ${database_dest_name}.m_paint(
 id int(11) unsigned not null auto_increment,
 color varchar(50),
 type varchar(50),
 litre int,
 primary key (id)
);

create table ${database_dest_name}.m_address(
 id int unsigned not null auto_increment,
 line1 varchar(100),
 line2 varchar(100),
 line3 varchar(100),
 primary key(id)
);

create table ${database_dest_name}.m_customer(
 id int unsigned not null auto_increment,
 name varchar(100),
 phone_number varchar(100),
 address_id int unsigned default null,
 primary key (id),
 foreign key (address_id) references m_address(id)
);

create table ${database_dest_name}.t_order(
 id int unsigned not null auto_increment,
 date datetime,
 delivery tinyint,
 address_id int unsigned default null,
 customer_id int unsigned default null,
 primary key (id),
 foreign key (customer_id) references m_customer(id),
 foreign key (address_id) references m_address(id)
);

create table ${database_dest_name}.t_item(
 id int unsigned not null auto_increment,
 amount int,
 order_id int unsigned default null,
 paint_id int unsigned default null,
 primary key (id),
 foreign key (order_id) references t_order(id),
 foreign key (paint_id) references m_paint(id)
);
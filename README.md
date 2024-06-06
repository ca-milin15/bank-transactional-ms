# bank-transactional-ms

## Creacion de contenedor de la DB de transactional:

docker run --name pichincha-postgres-transactional-instance -e POSTGRES_PASSWORD=root -e POSTGRES_USER=root -e POSTGRES_DB=transactional -p 5433:5432 -d postgres


CREATE SEQUENCE IF NOT EXISTS tbl_account_id_seq START WITH 1;

create table tbl_account(
id SERIAL PRIMARY KEY,
customer_identification varchar(25) not null,
number bigint not null,
type char(2) not null,
initial_amount float not null,
balance float not null,
status boolean not null
);


CREATE SEQUENCE IF NOT EXISTS tbl_transaction_id_seq START WITH 1;

create table tbl_transaction(
id SERIAL PRIMARY KEY,
account_id SERIAL not null,
created_at timestamp not null,
type char(3) not null,
amount float not null,
balance float not null,
foreign key (account_id) references tbl_account(id)
);
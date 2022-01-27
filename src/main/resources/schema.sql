drop table if exists eiscremesorte CASCADE;
drop table if exists eiscremesorte_zutaten CASCADE;
drop sequence if exists eiscremesortedb_seq;

create sequence eiscremesortedb_seq start with 1 increment by 1;

create table eiscremesorte (
       id bigint not null,
        kategorie varchar(255) not null,
        kcal integer not null,
        name varchar(255) not null,
        preis decimal(19,2) not null,
        primary key (id)
    );

create table eiscremesorte_zutaten (
       eiscremesorte_id bigint not null,
        zutaten varchar(255) not null
    );

alter table eiscremesorte_zutaten
       add constraint fk_eiscreme_zutaten_eiscremesorte
       foreign key (eiscremesorte_id)
       references eiscremesorte;
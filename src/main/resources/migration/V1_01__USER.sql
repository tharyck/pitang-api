create table TB_USER (
    ID         BIGINT auto_increment primary key,
    BIRTHDAY   TIMESTAMP              not null,
    CREATED_AT TIMESTAMP,
    EMAIL      CHARACTER VARYING(255) not null
        constraint UK_EMAIL unique,
    FIRST_NAME CHARACTER VARYING(255) not null,
    LAST_LOGIN TIMESTAMP,
    LAST_NAME  CHARACTER VARYING(255) not null,
    LOGIN      CHARACTER VARYING(255) not null
        constraint UK_LOGIN unique,
    PASSWORD   CHARACTER VARYING(255) not null,
    PHONE      CHARACTER VARYING(255) not null
);


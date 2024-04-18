create table TB_CAR (
    ID            BIGINT auto_increment primary key,
    COLOR         CHARACTER VARYING(255) not null,
    LICENSE_PLATE CHARACTER VARYING(255) not null
        constraint UK_PLATE unique,
    MODEL         CHARACTER VARYING(255) not null,
    USED          INTEGER,
    USER_ID       BIGINT,
    YEAR_MODEL    INTEGER                not null,
    CAR_ID        BIGINT,
    constraint FKF042B0GWJI8XLNRGT4YV3CJ1N
        foreign key (CAR_ID) references TB_USER
);


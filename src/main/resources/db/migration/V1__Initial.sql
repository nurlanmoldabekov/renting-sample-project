CREATE SEQUENCE public.main_sequence
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;

CREATE TABLE public.t_users
(
    id bigint PRIMARY KEY DEFAULT nextval('main_sequence'::regclass),
    vname character varying(255) NOT NULL,
    vemail character varying(255) NOT NULL UNIQUE,
    vrole character varying(20) NOT NULL
);
CREATE TABLE public.t_flats
(
    id bigint PRIMARY KEY DEFAULT nextval('main_sequence'::regclass),
    vaddress character varying(255) NOT NULL,
    nuserid bigint NOT NULL UNIQUE,
    CONSTRAINT flats_userid FOREIGN KEY (nuserid)
        REFERENCES public.t_users (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE CASCADE
);
CREATE TABLE public.t_applications
(
    id bigint PRIMARY KEY DEFAULT nextval('main_sequence'::regclass),
    vtext character varying(255),
    vstatus character varying(15) NOT NULL,
    ntimeslot integer NOT NULL,
    ddate date NOT NULL,
    dcreatedate timestamp without time zone NOT NULL,
    nnewuserid bigint NOT NULL,
    nolduserid bigint NOT NULL,
    CONSTRAINT applications_newuserid FOREIGN KEY (nnewuserid)
        REFERENCES public.t_users (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE CASCADE,
    CONSTRAINT applications_olduserid FOREIGN KEY (nolduserid)
        REFERENCES public.t_users (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE CASCADE
);

create index t_flats_nuserid_index
    on t_flats (nuserid);
create index t_applications_nnewuserid_index
    on t_applications (nnewuserid);
create index t_applications_nolduserid_index
    on t_applications (nolduserid);
ALTER TABLE t_applications ADD COLUMN nflatid bigint;
ALTER TABLE t_applications ADD     CONSTRAINT applications_flatid FOREIGN KEY (nflatid)
    REFERENCES public.t_flats (id) MATCH SIMPLE
    ON UPDATE NO ACTION ON DELETE CASCADE;

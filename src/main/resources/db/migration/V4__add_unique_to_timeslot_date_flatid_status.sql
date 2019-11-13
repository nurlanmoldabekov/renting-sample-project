ALTER TABLE t_applications
    ADD UNIQUE (ntimeslot, ddate, nflatid, vstatus);